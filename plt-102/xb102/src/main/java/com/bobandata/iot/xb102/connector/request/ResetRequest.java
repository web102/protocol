package com.bobandata.iot.xb102.connector.request;

import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.xb102.frame.format.FixedLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResetRequest extends Iec102SendRequest {
    private static final Logger logger = LoggerFactory.getLogger(ResetRequest.class);

     ResetRequest(TaskParam taskParam, IMasterProtocol protocol) {
         super(taskParam, protocol);
     }

    public void sendTask() throws Exception {
        ControlDomain_C controlDomain = new ControlDomain_C((byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0);
        LinkAddress linkAddress = new LinkAddress("0100");
        FixedLengthFrame requestFrame = new FixedLengthFrame(controlDomain, linkAddress);
        //响应为固定帧长
        sendMsg(requestFrame);
    }
}

