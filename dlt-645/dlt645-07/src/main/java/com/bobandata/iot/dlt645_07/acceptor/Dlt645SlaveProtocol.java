package com.bobandata.iot.dlt645_07.acceptor;

import com.bobandata.iot.dlt645_07.acceptor.codec.Dlt645SlaveDecoder;
import com.bobandata.iot.dlt645_07.acceptor.codec.Dlt645SlaveEncoder;
import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.dlt645_07.frame.data.DataRequestArea;
import com.bobandata.iot.dlt645_07.frame.data.DataReturnArea;
import com.bobandata.iot.dlt645_07.frame.data.DataValue;
import com.bobandata.iot.dlt645_07.util.FunCodeConst;
import com.bobandata.iot.transport.acceptor.RxtxAcceptor;
import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.protocol.ISlaveProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;

public class Dlt645SlaveProtocol implements ISlaveProtocol {
    private static final Logger logger = LoggerFactory.getLogger(Dlt645SlaveProtocol.class);
    @Override
    public String getName() {
        return "102从站规约";
    }
    @Override
    public String getVersion() {
        return "1.0";
    }

    //把规约编解码类和通道绑定
    @Override
    public void installProtocolFilter(ChannelPipeline pipeline) {
        pipeline.addLast("decoder", new Dlt645SlaveDecoder());
        pipeline.addLast("encoder", new Dlt645SlaveEncoder());
    }

    @Override
    public void channelRegister(Channel channel) {
    }

    //监听消息的方法，只要终端收到消息都会执行这个方法
    @Override
    public void channelRead(Channel channel, Object msg) {
        IFrame frame = (IFrame) msg;
        logger.info("下行：" + frame.toHexString());
        //Long postalAddress, byte funCode, DataArea dataArea
        Dlt645Frame reqFrame = (Dlt645Frame)frame;
        DataRequestArea dataRequestArea = (DataRequestArea) reqFrame.getDataArea();
        //构造响应的数据体
        DataValue dataValue = new DataValue("12.11");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        dataValue.setTimestamp(timestamp);
        dataValue.setValue1("12.1");
        dataValue.setValue2("12.1");
        dataValue.setValue3("12.1");
        dataValue.setValue4("12.1");
        dataValue.setValue5("12.1");
        DataReturnArea dataReturnArea = new DataReturnArea(dataRequestArea.getDataType(),dataValue);
        Dlt645Frame dlt645Frame = new Dlt645Frame(reqFrame.getPostalAddress().getAddress(), FunCodeConst.RES_NORMAL, dataReturnArea);
        channel.writeAndFlush(dlt645Frame);
        logger.info("上行：" + dlt645Frame.toHexString());
    }

    public static void main(String[] args) throws Exception {
        logger.info("开始启动Rxtx服务器...");
        Dlt645SlaveProtocol slaveProtocol = new Dlt645SlaveProtocol();
        RxtxAcceptor rxtxAcceptor = new RxtxAcceptor("COM1",2400, (short)8,(short)2,(short)1,slaveProtocol);
        rxtxAcceptor.connect();
        Thread.sleep(100000000);
    }
}