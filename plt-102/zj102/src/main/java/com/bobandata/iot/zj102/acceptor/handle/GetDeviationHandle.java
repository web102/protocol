package com.bobandata.iot.zj102.acceptor.handle;

import com.bobandata.iot.zj102.acceptor.SlaveProtocol;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.type.TwoByteAsdu;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.zj102.frame.format.VariableLengthFrame;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import com.bobandata.iot.zj102.util.Ti;
import io.netty.channel.Channel;

/**
 * Created by 拓 on 2017/10/27.
 */
public class GetDeviationHandle extends Iec102Handle{

    public GetDeviationHandle(Channel channel, HandleParams params){
        super(channel,params);
    }

    @Override
    public void responseData() {
        ControlDomain_C controlDomain_C = new ControlDomain_C((byte)0,(byte)0,(byte)0,(byte)0,(byte)8);
        LinkAddress linkAddress = (LinkAddress)params.getParameter("linkAddress");
        AsduHead asduHead = (AsduHead)params.getParameter("asduHead");

        AsduHead asHead = new AsduHead();
        asHead.setRad(asduHead.getRad());
        asHead.setTi(Ti.returnDeviation);
        asHead.setPad(asduHead.getPad());
        asHead.setCot((byte)0x05);
        asHead.setVsq((byte)0x01);
        asHead.setAsduHeadLength(asduHead.getAsduHeadLength());

        TwoByteAsdu deviationAsdu = new TwoByteAsdu(asHead, 500);
        VariableLengthFrame response = new VariableLengthFrame(controlDomain_C, linkAddress, deviationAsdu);

        channel.attr(responsekey).set(response);
        SlaveProtocol.returnMsg(channel,response);
    }

}
