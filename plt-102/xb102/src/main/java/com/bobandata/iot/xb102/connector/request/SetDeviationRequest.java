package com.bobandata.iot.xb102.connector.request;

import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.type.TwoByteAsdu;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.xb102.frame.format.VariableLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/11/15 18:27
 * @Description:
 */

public class SetDeviationRequest extends Iec102SendRequest {
    private static final Logger logger = LoggerFactory.getLogger(SetDeviationRequest.class);

    public SetDeviationRequest(TaskParam taskParam, IMasterProtocol protocol) {
        super(taskParam, protocol);
    }

    public void sendDataTask() throws Exception {
        // 控制域 73
        ControlDomain_C controlDomain = new ControlDomain_C((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 3);
        //地址 0100
        LinkAddress linkAddress = new LinkAddress("0100");
        //1类型标识
        //2可变结构限定词
        //3传输原因 请求或被请求
        //4应用服务单元公共地址
        //5记录地址
        AsduHead asduHead = new AsduHead((byte) taskParam.getTaskType(), (byte) 0, (byte) 5, "0100", (byte) 0);
        TwoByteAsdu twoByteAsdu = new TwoByteAsdu(asduHead, 256);
        VariableLengthFrame requestFrame = new VariableLengthFrame(controlDomain, linkAddress, twoByteAsdu);
        //响应为单字节
        sendMsg(requestFrame);
    }
}