package com.bobandata.iot.xb102.acceptor.handle;

import com.bobandata.iot.xb102.acceptor.SlaveProtocol;
import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.type.LongTimeAsdu;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_M;
import com.bobandata.iot.xb102.frame.format.VariableLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import com.bobandata.iot.xb102.util.Ti;
import io.netty.channel.Channel;

import java.util.Date;

/**
 * Created by 拓 on 2017/10/24.
 */
public class SynchClockHandle extends Iec102Handle{

    public SynchClockHandle(Channel channel, HandleParams params){
        super(channel,params);
    }

    @Override
    public void responseData() {
        ControlDomain_M controlDomain_M = new ControlDomain_M((byte)0,(byte)0,(byte)0,(byte)0,(byte)8);
        LinkAddress linkAddress = (LinkAddress)params.getParameter("linkAddress");
        AsduHead asduHead = (AsduHead)params.getParameter("asduHead");
        
        AsduHead asHead = new AsduHead();
        asHead.setRad(asduHead.getRad());
        asHead.setTi(Ti.returnTimeAsyn);
        asHead.setPad(asduHead.getPad());
        asHead.setCot((byte)0x30);
        asHead.setVsq((byte)0x01);
        asHead.setAsduHeadLength(asduHead.getAsduHeadLength());

        LongTimeAsdu ertuTimeAsdu = new LongTimeAsdu(asHead, new Date());
        VariableLengthFrame response = new VariableLengthFrame(controlDomain_M, linkAddress, ertuTimeAsdu);

        channel.attr(responsekey).set(response);
        SlaveProtocol.returnMsg(channel,response);
    }

}
