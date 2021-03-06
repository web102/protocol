package com.bobandata.iot.zj102.connector.request;

import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.type.NullAsdu;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_M;
import com.bobandata.iot.zj102.frame.format.VariableLengthFrame;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: LiZhiPeng
 * @Emil: 799425065@qq.com
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 9:42 2018/9/21.
 */
public class NullRequest extends Iec102SendRequest {
    private static final Logger logger = LoggerFactory.getLogger(NullRequest.class);

    public NullRequest(TaskParam taskParam, IMasterProtocol protocol) {
        super(taskParam, protocol);
    }

    public void sendDataTask() throws Exception {
        // 控制域 73
        ControlDomain_M controlDomain = new ControlDomain_M((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 3);
        //地址 0100
        LinkAddress linkAddress = new LinkAddress("0100");
        //1类型标识
        //2可变结构限定词
        //3传输原因 请求或被请求
        //4应用服务单元公共地址
        //5记录地址
        AsduHead asduHead = new AsduHead((byte) taskParam.getTaskType(), (byte) 0, (byte) 5, 1, (byte) 0);
        NullAsdu nullAsdu = new NullAsdu(asduHead);
        VariableLengthFrame requestFrame = new VariableLengthFrame(controlDomain, linkAddress, nullAsdu);
        //响应为单字节
        sendMsg(requestFrame);
    }
}
