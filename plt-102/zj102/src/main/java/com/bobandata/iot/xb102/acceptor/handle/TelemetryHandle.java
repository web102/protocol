package com.bobandata.iot.xb102.acceptor.handle;

import com.bobandata.iot.xb102.acceptor.SlaveProtocol;
import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.type.DataAsdu;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_M;
import com.bobandata.iot.xb102.frame.format.VariableLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import com.bobandata.iot.xb102.frame.asdu.info.Info;
import com.bobandata.iot.xb102.frame.asdu.info.SingleGroupInfo;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 拓 on 2017/10/24.
 */
public class TelemetryHandle extends Iec102Handle{

    public TelemetryHandle(Channel channel, HandleParams params){
        super(channel,params);
    }

    @Override
    public void responseData() {

        //应该还要判断查询数据是否完结
        ControlDomain_M controlDomain_M = new ControlDomain_M((byte)0,(byte)0,(byte)1,(byte)0,(byte)8);
        LinkAddress linkAddress = (LinkAddress)params.getParameter("linkAddress");
        AsduHead asduHead = (AsduHead)params.getParameter("asduHead");
        SingleGroupInfo energyInfo = new SingleGroupInfo(1,200,0);
        List<Info> infoFrames = new ArrayList<Info>();
        infoFrames.add(energyInfo);
        DataAsdu dataAsdu = new DataAsdu(asduHead,infoFrames,new Date());
        VariableLengthFrame response = new VariableLengthFrame(controlDomain_M, linkAddress, dataAsdu);

        channel.attr(responsekey).set(response);
        SlaveProtocol.returnMsg(channel,response);
    }

}
