package com.bobandata.iot.zj102.acceptor.handle;

import com.bobandata.iot.zj102.acceptor.SlaveProtocol;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.zj102.frame.format.FixedLengthFrame;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import io.netty.channel.Channel;

/**
 * Created by 拓 on 2017/10/24.
 */
public class LinkRequestHandle extends Iec102Handle{

    public LinkRequestHandle(Channel channel, HandleParams params) {
        super(channel,params);
    }

    @Override
    public void responseData() {
        ControlDomain_C controlDomain_C = new ControlDomain_C((byte)0,(byte)0,(byte)1,(byte)0,(byte)11);
        FixedLengthFrame response = new FixedLengthFrame(controlDomain_C,new LinkAddress("0001"));
        response.setControlDomain(controlDomain_C);
        response.setLinkAddress((LinkAddress)params.getParameter("linkAddress"));
        SlaveProtocol.returnMsg(channel, response);
    }

    @Override
    public void handle() {
    	// TODO Auto-generated method stub
    	this.responseData();
    }
}
