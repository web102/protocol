package com.bobandata.iot.dlt645_07.acceptor.codec;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dlt645SlaveEncoder extends MessageToByteEncoder {
    private static final Logger logger = LoggerFactory.getLogger(Dlt645SlaveEncoder.class);

    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if ((o instanceof IFrame)) {
            IFrame iframe = (IFrame) o;
            byteBuf.writeBytes(iframe.getBuffer());
        }
    }
}
