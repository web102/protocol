package com.bobandata.iot.zj102.acceptor.handle;

import com.bobandata.iot.zj102.acceptor.SlaveProtocol;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.type.FactoryAsdu;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.zj102.frame.format.VariableLengthFrame;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import com.bobandata.iot.zj102.util.Ti;
import io.netty.channel.Channel;

import java.util.Date;

/**
 * Created by 拓 on 2017/10/27.
 */
public class FactoryHandle extends Iec102Handle{

    public FactoryHandle(Channel channel, HandleParams params){
        super(channel,params);
    }

    @Override
    public void responseData() {
        ControlDomain_C controlDomain_C = new ControlDomain_C((byte)0,(byte)0,(byte)0,(byte)0,(byte)8);
        LinkAddress linkAddress = (LinkAddress)params.getParameter("linkAddress");
        AsduHead asduHead = (AsduHead)params.getParameter("asduHead");
        
        AsduHead asHead = new AsduHead();
        asHead.setRad(asduHead.getRad());
        asHead.setTi(Ti.returnFactory);
        asHead.setPad(asduHead.getPad());
        asHead.setCot((byte)0x05);
        asHead.setVsq((byte)0x01);
        
        FactoryAsdu factoryInfoAsdu = new FactoryAsdu(asHead,new Date(), 11, "2");
        VariableLengthFrame response = new VariableLengthFrame(controlDomain_C, linkAddress, factoryInfoAsdu);

        channel.attr(responsekey).set(response);
        SlaveProtocol.returnMsg(channel,response);
    }

}
