package com.bobandata.iot.xb102.connector.request;


import net.njcp.ias.data.TaskParam;
import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.type.DataReqAsdu;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.xb102.frame.format.VariableLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import net.njcp.ias.protocol.IAsynProtocol;
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

    public DataRequest(TaskParam taskParam, IAsynProtocol protocol) {
        super(taskParam, protocol);
    }

    public void sendDataTask() throws Exception {
        ControlDomain_C controlDomain = new ControlDomain_C((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 3);

        LinkAddress linkAddress = new LinkAddress("0100");

        AsduHead asduHead = new AsduHead(taskParam.getTaskItem().getTaskType(), (byte) 1, (byte) 6, "0100", taskParam.getRad().byteValue());

        Date endDate = taskParam.getEndTime();
        Date startDate = taskParam.getStartTime();
        int startMark = taskParam.getStartAddr();
        int endMark = taskParam.getEndAddr();
        DataReqAsdu dataRequestAsdu = new DataReqAsdu(asduHead, startMark, endMark, startDate, endDate);
        VariableLengthFrame requestFrame = new VariableLengthFrame(controlDomain, linkAddress, dataRequestAsdu);
        //响应为单字节
        sendMsg(requestFrame);
    }
}