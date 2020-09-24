package com.bobandata.iot.zj102.connector.codec;

import com.bobandata.iot.zj102.util.CommCoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MasterDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(MasterDecoder.class);

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object decoded = decode(channelHandlerContext, byteBuf);
        if (decoded != null) {
            list.add(decoded);
            byteBuf.skipBytes(byteBuf.readableBytes());
        }
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        return CommCoder.masterDecoder(in);
    }

}
