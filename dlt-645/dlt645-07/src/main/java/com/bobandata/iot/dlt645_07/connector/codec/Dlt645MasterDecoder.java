package com.bobandata.iot.dlt645_07.connector.codec;

import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.dlt645_07.frame.LoginFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.dlt645_07.util.FinalConst;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Dlt645MasterDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(Dlt645MasterDecoder.class);

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object decoded = decode(channelHandlerContext, byteBuf);
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
        //判断为心跳报文
        if (start == (byte) 0xE5) {
            logger.debug("收到电表心跳报文！");
            in.readByte();
        }
        //判断为dtu登陆报文
//        if(start==FinalConst.STARTBYTE && last==FinalConst.ENDBYTE){
//            return new LoginFrame(in);
//        }
        //判断为电表应答报文
        if((start== FinalConst.STARTBYTE||start==FinalConst.FEBYTE) && last==FinalConst.ENDBYTE){
            while(ConvertUtil.byte2hex(in.getByte(count)).equals("FE")){
                in.readByte();
                count ++;
            }
            //获取长度字节，9是消息头部分+个FE填充位
            int lengthLow = HexUtils.byteToInt(in.getByte(9+count));
            if(lengthLow+ 12 == length-count){
                return new Dlt645Frame(in);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
