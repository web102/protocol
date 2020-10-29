package com.bobandata.iot.zj102.connector;

import com.bobandata.iot.transport.connector.SocketAdapter;
import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.transport.util.WebMessage;
import com.bobandata.iot.zj102.connector.codec.MasterDecoder;
import com.bobandata.iot.zj102.connector.codec.MasterEncoder;
import com.bobandata.iot.zj102.connector.request.Iec102SendRequest;
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
    private SocketAdapter client;

    public String getName() {
        return "102主站规约";
    }

    public String getVersion() {
        return "1.0";
    }

    public MasterProtocol() {
    }


    @Override
    public void init(TaskParam taskParam) throws Exception {
        this.session =  taskParam.getWebSession();
        this.client = new SocketAdapter(taskParam.getIpAddress(), taskParam.getIpPort(), this);
        taskParam.setChannel(client);
        this.taskParam = taskParam;
        client.connect();
    }

    public void installProtocolFilter(ChannelPipeline pipeline) {
        pipeline.addLast("decoder", new MasterDecoder());
        pipeline.addLast("encoder", new MasterEncoder());
    }

    @Override
    public void executeTask() throws Exception {
        Iec102SendRequest iec102SendRequest = new Iec102SendRequest(taskParam, this);
        //链路请求
        try {
            iec102SendRequest.sendLinkTask();
            //发送送数据请求
            iec102SendRequest.sendTask();
            //发送召唤一级数据(续帧请求)
            iec102SendRequest.sendLinkIncrease();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.client.disconnect();
    }

    @Override
    public IFrame sendMsg(IFrame request) throws Exception{
        IFrame response = null;

        try {
            for (int i = 0; i<3&&response==null;i++){
                this.session.getBasicRemote().sendText((new WebMessage(WebMessage.Code.REQUEST_INFO,request)).toJson());
                response = (IFrame) taskParam.getChannel().getBody(request);
            }
            logger.info("主站:" + request.toHexString());
            logger.info("从站:" + response.toHexString());
            this.session.getBasicRemote().sendText((new WebMessage(WebMessage.Code.RESPONSE_INFO,response)).toJson());
        }catch (IOException e){
            logger.error("E004-发送前段消息异常！");
        }catch (NullPointerException e2){
            this.session.getBasicRemote().sendText((new WebMessage(WebMessage.Code.ERROR,"终端无响应！")).toJson());
            throw e2;
        }

        return response;
    }

    @Override
    public void isClose() {
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
        taskParam.setChannel(client);
        protocol.init(taskParam);
        protocol.executeTask();
        client.disconnect();
    }

}
