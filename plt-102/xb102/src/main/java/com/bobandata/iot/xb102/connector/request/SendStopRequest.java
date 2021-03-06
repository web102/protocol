package com.bobandata.iot.xb102.connector.request;


import net.njcp.ias.data.TaskParam;
import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.type.NullAsdu;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.xb102.frame.format.VariableLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import net.njcp.ias.protocol.IAsynProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/12/6 11:19
 * @Description:
 */

public class SendStopRequest extends Iec102SendRequest{
    private static final Logger logger = LoggerFactory.getLogger(SendStopRequest.class);

    public SendStopRequest(TaskParam taskParam, IAsynProtocol protocol) {
        super(taskParam, protocol);
    }


    public void sendTask() throws Exception {
        // 控制域 73
        ControlDomain_C controlDomain = new ControlDomain_C((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 3);
        //地址 0100
        LinkAddress linkAddress = new LinkAddress("0100");
        //1类型标识
        //2可变结构限定词
        //3传输原因 请求或被请求
        //4应用服务单元公共地址
        //5记录地址
        AsduHead asduHead = new AsduHead(taskParam.getTaskItem().getTaskType(),(byte)0, (byte)8, "0100", (byte)0);
        NullAsdu nullAsdu = new NullAsdu(asduHead);
        VariableLengthFrame requestFrame = new VariableLengthFrame(controlDomain, linkAddress, nullAsdu);
        //响应为单字节
        sendMsg(requestFrame);
    }
}