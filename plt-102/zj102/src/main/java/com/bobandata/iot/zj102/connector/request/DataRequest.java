package com.bobandata.iot.zj102.connector.request;

import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.type.DataReqAsdu;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_M;
import com.bobandata.iot.zj102.frame.format.VariableLengthFrame;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Author: zhanglingzhi
 * @Description: 电能累积量
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:51 2018/8/28.
 */
public class DataRequest extends Iec102SendRequest {
    private static final Logger logger = LoggerFactory.getLogger(DataRequest.class);

    public DataRequest(TaskParam taskParam, IMasterProtocol protocol) {
        super(taskParam, protocol);
    }

    public void sendDataTask() throws Exception {
        ControlDomain_M controlDomain = new ControlDomain_M((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 3);

        LinkAddress linkAddress = new LinkAddress("0100");

        int ertuAddr = taskParam.getStartMark()/256+1;

        AsduHead asduHead = new AsduHead((byte) taskParam.getTaskType(), (byte) 1, (byte) 6, ertuAddr, (byte) 11);


        Date endDate = taskParam.getEndDate();
        Date startDate = taskParam.getStartDate();
        int startMark = taskParam.getStartMark()%256;
        int endMark = taskParam.getEndMark()%256;
        DataReqAsdu dataRequestAsdu = new DataReqAsdu(asduHead, startMark, endMark, startDate, endDate);
        VariableLengthFrame requestFrame = new VariableLengthFrame(controlDomain, linkAddress, dataRequestAsdu);
        //响应为单字节
        sendMsg(requestFrame);
    }
}