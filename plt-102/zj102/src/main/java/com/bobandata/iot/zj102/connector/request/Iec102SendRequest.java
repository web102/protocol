package com.bobandata.iot.zj102.connector.request;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_M;
import com.bobandata.iot.zj102.frame.format.FixedLengthFrame;
import com.bobandata.iot.zj102.frame.format.VariableLengthFrame;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import com.bobandata.iot.zj102.util.Ti;
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
        ControlDomain_M controlDomain_5A = new ControlDomain_M((byte) 0, (byte) 1, (byte) 0, (byte) 1, (byte) 10);
        ControlDomain_M controlDomain_7A = new ControlDomain_M((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 10);
        ControlDomain_C controlDomain_c = null;

        //如果收到08结束死循环、如果没收到就序增
        while (!isEnd) {
            if (controlDomainStr.equals("5A")) {
                controlDomain_c = getReturnFrame(controlDomain_5A);
                if(controlDomain_c ==null){
                    isEnd = true;
                }else if(controlDomain_c.getAcd() == 0){
                    isEnd = true;
                }
            } else {
                controlDomain_c = getReturnFrame(controlDomain_7A);
                if(controlDomain_c ==null){
                    isEnd = true;
                }else if(controlDomain_c.getAcd() == 0){
                    isEnd = true;
                }
            }
//            if(stop){
//                new SendStopRequest(this.taskParam, this.protocol).sendTask();
//                isEnd = true;
//            }
        }
    }

    //重发
    private ControlDomain_C getReturnFrame(ControlDomain_M controlDomain) throws Exception {
        LinkAddress linkAddress = new LinkAddress("0100");
        FixedLengthFrame requestFrame = new FixedLengthFrame(controlDomain, linkAddress);
        VariableLengthFrame returnFrame =null;
        Asdu asdu =null;

//        for (int i = 0; i <= 3; i++) {
            returnFrame = (VariableLengthFrame) sendMsg(requestFrame);
//            if(returnFrame==null){
//                Thread.sleep(3000);
//            }else{
//                asdu = returnFrame.getAsdu();
//                break;
//            }
//        }

        try {
            controlDomainStr = controlDomainStr.equals("5A") ? "7A" : "5A";
            return (ControlDomain_C) returnFrame.getControlDomain();
        } catch (Exception e){
            logger.error("Read SalveProtocol massage fail");
            e.getStackTrace();
            this.taskParam.getChannel().disconnect();
        }
        return null;
    }

    public void sendTask() throws Exception {
        int taskType = this.taskParam.getTaskType()&0xff;
        switch (Ti.findByRequest(taskType)) {
            case FACTORY:
            case SINGLEINFO:
            case GETTERMINALTIME:
            case GET_DEVIATION:
                new NullRequest(this.taskParam,this.protocol).sendDataTask();
                break;
            case TIMELIMITSINGLEINFO:
                new TimeLimitSingleInfoRequest(this.taskParam,this.protocol).sendDataTask();
                break;
            case TIMEASYN:
                new TimeAsynRequest( this.taskParam,this.protocol).sendDataTask();
                break;
            case SET_DEVIATION:
                new SetDeviationRequest( this.taskParam,this.protocol).sendDataTask();
                break;

            case RPVIEW:
            case DAY_RPVIEW:
            case MONTH_RPVIEW:
            case TARIFF:
            case DAY_TARIFF:
            case MONTH_TARIFF:
            case VIEW:
            case DAY_VIEW:
            case MONTH_VIEW:
            case DAY_DEMAND:
            case MONTH_DEMAND:

            case FIRST_VIEW     :
            case FIRST_ADDR_VIEW:
            case LIMIT_VIEW     :
            case LIMIT_ADDR_VIEW:

            case CYCLEVIEW:
            case FIRST_CYCLEVIEW     :
            case FIRST_ADDR_CYCLEVIEW:
            case LIMIT_CYCLEVIEW     :
            case LIMIT_ADDR_CYCLEVIEW:
            case BILLVIEW:
            case FIRST_BILLVIEW     :
            case FIRST_ADDR_BILLVIEW:
            case LIMIT_BILLVIEW     :
            case LIMIT_ADDR_BILLVIEW:

            case CYCLEBILLVIEW:
            case FIRST_CYCLEBILLVIEW:
            case FIRST_ADDR_CYCLEBILLVIEW:
            case LIMIT_CYCLEBILLVIEW     :
            case LIMIT_ADDR_CYCLEBILLVIEW:

                new DataRequest(this.taskParam,this.protocol).sendDataTask();
                break;
                default:
                    logger.error("---------------Not Found Type----------------");
                    break;
        }
    }

    public IFrame sendMsg(IFrame message) throws Exception {
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

