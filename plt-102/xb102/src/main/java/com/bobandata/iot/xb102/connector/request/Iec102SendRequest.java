package com.bobandata.iot.xb102.connector.request;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_M;
import com.bobandata.iot.xb102.frame.format.FixedLengthFrame;
import com.bobandata.iot.xb102.frame.format.SingleByteFrame;
import com.bobandata.iot.xb102.frame.format.VariableLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import com.bobandata.iot.xb102.util.Ti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Iec102SendRequest {
    private static final Logger logger = LoggerFactory.getLogger(Iec102SendRequest.class);
    protected TaskParam taskParam;
    protected IMasterProtocol protocol;
    private String controlDomainStr = "5A";
    private boolean isEnd = false;
    private boolean stop = false;

    public Iec102SendRequest(TaskParam taskParam, IMasterProtocol protocol) {
        this.taskParam = taskParam;
        this.protocol = protocol;
    }

    //链路请求
    public void sendLinkTask() throws Exception {
        new LinkRequest(this.taskParam, this.protocol).sendTask();
        new ResetRequest(this.taskParam, this.protocol).sendTask();
        new LinkRequest(this.taskParam, this.protocol).sendTask();
    }

    //发送召唤一级数据(续帧请求)
    public void sendLinkIncrease() throws Exception {
        ControlDomain_C controlDomain_5A = new ControlDomain_C((byte) 0, (byte) 1, (byte) 0, (byte) 1, (byte) 10);
        ControlDomain_C controlDomain_7A = new ControlDomain_C((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 10);
        ControlDomain_M controlDomain_m = null;

        //如果收到08结束死循环、如果没收到就序增
        while (!isEnd) {
            controlDomain_m = controlDomainStr.equals("5A")?getReturnFrame(controlDomain_5A):getReturnFrame(controlDomain_7A);
            if(stop){
                new SendStopRequest(this.taskParam, this.protocol).sendTask();
                isEnd = true;
            }
        }
    }

    //重发
    private ControlDomain_M getReturnFrame(ControlDomain_C controlDomain) throws Exception {
        LinkAddress linkAddress = new LinkAddress("0100");
        FixedLengthFrame requestFrame = new FixedLengthFrame(controlDomain, linkAddress);
        IFrame returnFrame = sendMsg(requestFrame);

        if (returnFrame == null||returnFrame instanceof SingleByteFrame) {
            isEnd = true;
            return null;
        } else if (returnFrame instanceof VariableLengthFrame) {
            if (((ControlDomain_M) (((VariableLengthFrame) returnFrame).getControlDomain())).getAcd() == 0) {
                isEnd = true;
                return null;
            }

            try {
                controlDomainStr = controlDomainStr.equals("5A") ? "7A" : "5A";
                return (ControlDomain_M) ((VariableLengthFrame) returnFrame).getControlDomain();
            } catch (Exception e) {
                logger.error("Read SalveProtocol massage fail");
                this.taskParam.getChannel().disconnect();
                isEnd = true;
                return null;
            }
        }
        return null;
    }

    public void sendTask() throws Exception {
        int taskType = this.taskParam.getTaskType()&0xff;
        switch (taskType) {
            case Ti.singleInfo:
            case Ti.getTerminalTime:
                new NullRequest(this.taskParam,this.protocol).sendDataTask();
                break;
            case Ti.timeLimitSingleInfo:
                new TimeLimitSingleInfoRequest(this.taskParam,this.protocol).sendDataTask();
                break;
            case Ti.timeAsyn:
                new TimeAsynRequest( this.taskParam,this.protocol).sendDataTask();
                break;

            case Ti.tariff:
            case Ti.data:
                new DataRequest(this.taskParam,this.protocol).sendDataTask();
                break;
                default:
                    logger.error("---------------Not Found Type----------------");
                    break;
        }
    }

    public IFrame sendMsg(IFrame message){
        return this.protocol.sendMsg(message);
    }

    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public boolean getStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}

