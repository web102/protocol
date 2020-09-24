package com.bobandata.iot.zj102.acceptor.handle;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.zj102.acceptor.SlaveProtocol;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.zj102.frame.format.SingleByteFrame;
import com.bobandata.iot.zj102.frame.format.VariableLengthFrame;
import io.netty.util.AttributeKey;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public abstract class Iec102Handle{
    private static Log logger = LogFactory.getLog(Iec102Handle.class);

    protected Channel channel;
    protected HandleParams params;

    static final AttributeKey<HandleParams> requestkey = AttributeKey.valueOf("request");
    static final AttributeKey<Byte> fcbkey = AttributeKey.valueOf("fcb");
    static final AttributeKey<IFrame> responsekey = AttributeKey.valueOf("response");
    static final AttributeKey<List> hisAcqs = AttributeKey.valueOf("hisAcqs");

    Iec102Handle(Channel channel, HandleParams params) {
        this.channel = channel;
        this.params = params;
    }

    //确认帧
    public void confirm(Channel channel, HandleParams params){
        //发确认应答，并保存请求
        SingleByteFrame response = new SingleByteFrame();
        response.setSingleByte((byte)0xE5);
        channel.attr(requestkey).set(params);
        SlaveProtocol.returnMsg(channel, response);
    }
    //镜像帧
    public void image(){
        VariableLengthFrame response = (VariableLengthFrame)params.getiFrame();
        ControlDomain_C controlDomain_C = new ControlDomain_C((byte)0,(byte)0,(byte)1,(byte)0,(byte)8);
        //镜像传输原因07 确认激活
        response.getAsdu().getAsduHead().setCot((byte)0x07);
        channel.attr(fcbkey).set((Byte) params.getParameter("fcb"));
        response=new VariableLengthFrame(controlDomain_C,response.getLinkAddress(),response.getAsdu());
        SlaveProtocol.returnMsg(channel, response);
    }

    //特殊帧
    public  void specialHandle(byte COT){
        VariableLengthFrame response = (VariableLengthFrame) channel.attr(requestkey).get().getiFrame();
        ControlDomain_C controlDomain_C = new ControlDomain_C((byte)0,(byte)0,(byte)0,(byte)0,(byte)8);
//        3 	自发/突发
//        4	    初始化
//        5	    请求或被请求
//        7	    激活确认
//        9	    停止激活确认
//        10	激活终止
//        13	无所请求数据记录
//        14	无所请求应用服务单元
//        15	主站发送记录序号不可知
//        16	主站发送地址说明不可知
        response.getAsdu().getAsduHead().setCot(COT);
        response=new VariableLengthFrame(controlDomain_C,response.getLinkAddress(),response.getAsdu());
        SlaveProtocol.returnMsg(channel, response);;
    }

    //续增标识不变，重新发
    public void reissue(){
        VariableLengthFrame response = (VariableLengthFrame) channel.attr(responsekey).get();
        response=new VariableLengthFrame(response.getControlDomain(),response.getLinkAddress(),response.getAsdu());
        SlaveProtocol.returnMsg(channel, response);
    }
    public void handle(){
        //判断缓存中有没有请求，如果没有那么肯定是一个新请求流程
        if(channel.attr(requestkey).get() == null){
            this.confirm(channel,params);
        } else if(channel.attr(fcbkey).get() != null) {
            //先判断缓存有没有fcb，如果没有,发镜像帧

            byte fcb = channel.attr(fcbkey).get();
            AsduHead asduHead = (AsduHead) params.getParameter("asduHead");

            //如果传输原因是8,就发送确认停止针
            //否则,如果fcb没变就重发
            if(asduHead.getCot()==8){
                specialHandle((byte)9);
            }else if(fcb == (Byte)params.getParameter("fcb")){
                this.reissue();
            }else {
                this.responseData();
            }
        }else {
            //镜像
            this.image();
        }
    }

    public abstract void responseData();

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public HandleParams getParams() {
        return params;
    }

    public void setParams(HandleParams params) {
        this.params = params;
    }

    public  static  HandleParams getRequestKey(Channel channel){
        return channel.attr(requestkey).get();
    }
}
