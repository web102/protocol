package com.bobandata.iot.xb102.connector;

import com.bobandata.iot.transport.connector.SocketAdapter;
import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.transport.util.WebMessage;
import com.bobandata.iot.xb102.connector.codec.MasterDecoder;
import com.bobandata.iot.xb102.connector.codec.MasterEncoder;
import com.bobandata.iot.xb102.connector.request.Iec102SendRequest;
import io.netty.channel.ChannelPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MasterProtocol extends IMasterProtocol {
    private static final Logger logger = LoggerFactory.getLogger(MasterProtocol.class);
    private Session session;

    public String getName() {
        return "102主站规约";
    }

    public String getVersion() {
        return "1.0";
    }

    public MasterProtocol() {
    }

    public MasterProtocol(TaskParam taskParam, Session session) throws Exception {
        this.session = session;
        SocketAdapter client = new SocketAdapter(taskParam.getIpAddress(), taskParam.getIpPort(), this);
        taskParam.setChannel(client);
        setTaskParam(taskParam);
        client.connect();
    }

    public void installProtocolFilter(ChannelPipeline pipeline) {
        pipeline.addLast("decoder", new MasterDecoder());
        pipeline.addLast("encoder", new MasterEncoder());
    }

    public void executeTask(TaskParam taskParam) throws Exception {
        setTaskParam(taskParam);
        Iec102SendRequest iec102SendRequest = new Iec102SendRequest(taskParam, this);
        //链路请求
        iec102SendRequest.sendLinkTask();
        //发送送数据请求
        iec102SendRequest.sendTask();
        //发送召唤一级数据(续帧请求)
        iec102SendRequest.sendLinkIncrease();
        taskParam.getChannel().disconnect();
    }

    @Override
    public IFrame sendMsg(IFrame request) {
        IFrame response = null;

        try {
            response = (IFrame) getTaskParam().getChannel().getBody(request);
            logger.info("主站:" + request.toHexString());
            logger.info("从站:" + response.toHexString());
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            this.session.getBasicRemote().sendText((new WebMessage(request, response)).toJson());
        } catch (Exception e) {
            logger.error("E004-发送前段消息异常！");
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void isClose() {
        if (this.session != null) {
            try {
                this.session.getBasicRemote().sendText((new WebMessage(WebMessage.Code.CLOSE.getCode(), "关闭")).toJson());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String hostname = "127.0.0.1";
        int port = 2000;
        MasterProtocol protocol = new MasterProtocol();
        SocketAdapter client = new SocketAdapter(hostname, port, protocol);
        client.connect();
        TaskParam taskParam = new TaskParam();
        taskParam.setStartMark(1);
        taskParam.setEndMark(3);
        taskParam.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-10-10 15:10:10"));
        taskParam.setEndDate(new Date());
        taskParam.setTaskType((byte)120);
        taskParam.setRad(0);
        taskParam.setChannel(client);
        protocol.executeTask(taskParam);
        client.disconnect();
    }

}
