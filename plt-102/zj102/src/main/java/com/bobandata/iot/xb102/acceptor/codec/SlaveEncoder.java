package com.bobandata.iot.xb102.acceptor.codec;

import com.bobandata.iot.transport.frame.IFrame;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlaveEncoder extends MessageToByteEncoder {
    private static final Logger logger = LoggerFactory.getLogger(SlaveEncoder.class);

    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if ((o instanceof IFrame)) {
            IFrame iframe = (IFrame) o;
            byteBuf.writeBytes(iframe.getBuffer());
        }
    }
}
