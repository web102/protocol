package com.bobandata.iot.xb102.connector.codec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xb102MasterEncoder extends ProtocolEncoderAdapter {
    private static final Logger logger = LoggerFactory.getLogger(Xb102MasterEncoder.class);

    private static Log log = LogFactory.getLog(Xb102MasterEncoder.class);

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) {
        IoBuffer ioBuffer = IoBuffer.wrap((byte[]) message);
//		log.info("请求：" + ioBuffer.getHexDump());
        out.write(ioBuffer);
    }
}
