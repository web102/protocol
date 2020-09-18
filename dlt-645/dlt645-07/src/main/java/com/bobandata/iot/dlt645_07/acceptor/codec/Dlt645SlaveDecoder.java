package com.bobandata.iot.dlt645_07.acceptor.codec;

import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.dlt645_07.util.FinalConst;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dlt645SlaveDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(Dlt645SlaveDecoder.class);

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list){
        Object decoded = null;
        try {
            decoded = decode(channelHandlerContext, byteBuf);
        } catch (Exception e) {
            logger.error("-------------decoded---------");
            e.printStackTrace();
        }
        if (decoded != null) {
            list.add(decoded);
            byteBuf.skipBytes(byteBuf.readableBytes());
        }
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int count = 0 ;
        byte start=in.getByte(0);
        int length = in.readableBytes();
        byte last=in.getByte(length-1);
        //此处是为了处理接收到的数据帧断掉的情况，判断接受到的数据帧是否完整
        if((start== FinalConst.FEBYTE||start == FinalConst.STARTBYTE)&&last==FinalConst.ENDBYTE){
            while(ConvertUtil.byte2hex(in.getByte(count)).equals("FE")){
                in.readByte();
                count ++;
            }
            //获取长度字节，9是消息头部分+个FE填充位
            int lengthLow = HexUtils.byteToInt(in.getByte(9+count));
            if(lengthLow+ 12 == length-count){
                Dlt645Frame dlt645Frame = new Dlt645Frame(in);
                return dlt645Frame;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
