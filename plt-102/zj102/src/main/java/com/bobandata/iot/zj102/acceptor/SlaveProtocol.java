package com.bobandata.iot.zj102.acceptor;

import com.bobandata.iot.transport.acceptor.Acceptor;
import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.protocol.ISlaveProtocol;
import com.bobandata.iot.zj102.acceptor.codec.SlaveDecoder;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.acceptor.codec.SlaveEncoder;
import com.bobandata.iot.zj102.util.Ti;
import com.bobandata.iot.zj102.util.FunCodeConst;
import com.bobandata.iot.zj102.acceptor.handle.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlaveProtocol implements ISlaveProtocol {
    private static final Logger logger = LoggerFactory.getLogger(SlaveProtocol.class);

    public String getName() {
        return "102从站规约";
    }

    public String getVersion() {
        return "1.0";
    }

    //把规约编解码类和通道绑定
    public void installProtocolFilter(ChannelPipeline pipeline) {
        pipeline.addLast("decoder", new SlaveDecoder());
        pipeline.addLast("encoder", new SlaveEncoder());
    }

    @Override
    public void channelRegister(Channel channel) {
        logger.info(channel.remoteAddress()+" register success");
    }

    //监听消息的方法，只要主站收到消息都会执行这个方法
    public void channelRead(Channel channel, Object msg) {
        IFrame frame = (IFrame) msg;
        logger.info("下行：" + frame.toHexString());
        HandleParams params = new HandleParams((IFrame) msg);
        handleRequest(channel, params);

    }

    public static void  returnMsg(Channel channel, IFrame frame) {
        logger.info("上行：" + frame.toHexString());
        channel.write(frame);
    }

    public void handleRequest(Channel channel,HandleParams params){
        //获取请求功能码
        byte funcode = (Byte)params.getParameter("funcode");
        switch (funcode){
            //链路复位
            case FunCodeConst.resetLink:
                new LinkResetHandle(channel,params).handle();
                break;
            //链路请求
            case FunCodeConst.requestLink:
                new LinkRequestHandle(channel,params).handle();
                break;
            //数据请求
            case FunCodeConst.dataRequest:
                handleDataRequest(channel,params);
                break;
            //数据请求续增指令
            case FunCodeConst.dataNext:
                HandleParams firstRequest = Iec102Handle.getRequestKey(channel);
                //将续增命令的fcb赋值给数据请求
                if(firstRequest == null){
                    new InitEndHandle(channel, params).handle();
                    break;
                }else{
                    firstRequest.setParameter("fcb", params.getParameter("fcb"));
                    handleDataRequest(channel, firstRequest);
                    break;
                }
        }
    }

    //处理数据请求帧
    public void handleDataRequest(Channel channel, HandleParams params) {
        AsduHead asduHead = (AsduHead)params.getParameter("asduHead");
        int typeIdentifier = asduHead.getTi();
        //获取到消息类型
        switch (typeIdentifier) {
            //电量数据
            case Ti.view:
            case Ti.billView:
            case Ti.dayView:
            case Ti.cycleView:
            case Ti.monthView:
            case Ti.cycleBillView:
                this.executeHandle(new ViewHandle(channel, params));
                break;
            //费率数据
            case Ti.tariff:
            case Ti.dayTariff:
            case Ti.monthTariff:
                this.executeHandle(new TariffHandle(channel, params));
                break;
            //需量数据
            case Ti.dayDemand:
            case Ti.monthDemand:
                this.executeHandle(new DemandHandle(channel, params));
                break;
            //获取终端时间
            case Ti.getTerminalTime:
                this.executeHandle(new GetTimeHandle(channel, params));
                break;
            //同步
            case Ti.timeAsyn:
                this.executeHandle(new SynchClockHandle(channel, params));
                break;
            //读制造厂和产品规范
            case Ti.factory:
                this.executeHandle(new FactoryHandle(channel, params));
                break;
            //读取时钟差值
            case Ti.getDeviation:
                this.executeHandle(new GetDeviationHandle(channel, params));
                break;
            //设置时钟差值
            case Ti.setDeviation:
                this.executeHandle(new SetDeviationHandle(channel, params));
                break;
            //四象限無功
            case Ti.rpView:
            case Ti.dayRpView:
            case Ti.monthRpView:
                this.executeHandle(new RpViewHandle(channel, params));
                break;
            case Ti.singleInfo:
            case Ti.timeLimitSingleInfo:
                this.executeHandle(new EventHandle(channel, params));
                break;
                default:
                    logger.error("---------------Not Found Type----------------");
                    break;
        }
    }

    public void executeHandle(Iec102Handle iec102Handle) {
        iec102Handle.handle();
    }

    public static void main(String[] args) throws Exception {
        logger.info("开始启动TCP服务器...");
        SlaveProtocol slaveProtocol = new SlaveProtocol();
        Acceptor acceptor = new Acceptor("127.0.0.1", 2000, slaveProtocol);
        Thread.sleep(100000000);
    }
}