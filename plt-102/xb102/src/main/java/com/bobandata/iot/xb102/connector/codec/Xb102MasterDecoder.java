package com.bobandata.iot.xb102.connector.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xb102MasterDecoder extends CumulativeProtocolDecoder {
    private static final Logger logger = LoggerFactory.getLogger(Xb102MasterDecoder.class);

    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {
        if(ioBuffer.get(0)==0x68&&ioBuffer.limit() < (ioBuffer.get(1) & 0xff) + 6){
            return false;
        }
//		log.info("响应：" + ioBuffer.getHexDump());
        byte[] bytes = new byte[ioBuffer.limit()];
        ioBuffer.get(bytes);
        out.write(bytes);
        return true;
    }
}
