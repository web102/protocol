package com.bobandata.iot.xb102.acceptor.handle;

import com.bobandata.iot.xb102.acceptor.SlaveProtocol;
import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.type.InitAsdu;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.xb102.frame.format.VariableLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import com.bobandata.iot.xb102.util.Ti;
import io.netty.channel.Channel;

/**
 * Created by 拓 on 2017/10/27.
 */
public class InitEndHandle extends Iec102Handle{

    public InitEndHandle(Channel channel, HandleParams params){
        super(channel,params);
    }

    @Override
    public void responseData() {
        //创建控制域
        ControlDomain_C controlDomain = new ControlDomain_C((byte)0,(byte)0,(byte)0,(byte)0,(byte)8);
  	    //创建链路地址
  	    LinkAddress linkAddress = new LinkAddress("0100");
  	    //asdu的信息头
  	    AsduHead asduHead = new AsduHead(Ti.returnInitEnd, (byte)0x01, (byte)0x04, "0100", (byte)0x0b);

        InitAsdu initAsdu = new InitAsdu(asduHead, (byte)0x01);
        VariableLengthFrame response = new VariableLengthFrame(controlDomain, linkAddress, initAsdu);

        channel.attr(responsekey).set(response);
        SlaveProtocol.returnMsg(channel, response);
    }
}