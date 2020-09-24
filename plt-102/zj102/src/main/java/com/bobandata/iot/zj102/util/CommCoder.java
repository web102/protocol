package com.bobandata.iot.zj102.util;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_M;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.zj102.frame.format.FixedLengthFrame;
import com.bobandata.iot.zj102.frame.format.SingleByteFrame;
import com.bobandata.iot.zj102.frame.format.VariableLengthFrame;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:36 2018/11/6.
 */
public class CommCoder {

    private static final Logger logger = LoggerFactory.getLogger(CommCoder.class);

    //主站解析方法
    public static Object masterDecoder(ByteBuf in){
        ControlDomain_C controlDomain_c = new ControlDomain_C();
        try {
            return decode(in, controlDomain_c);
        } catch (Exception e) {
            logger.error("主站解析方法异常！");
            e.printStackTrace();
        }
        return null;
    }

    //从站解析方法
    public static Object slaveDecoder(ByteBuf in){
        ControlDomain_M controlDomain_m = new ControlDomain_M();
        try {
            return decode(in, controlDomain_m);
        } catch (Exception e) {
            logger.error("从站解析方法异常！");
            e.printStackTrace();
        }
        return null;
    }

    //公共解析方法
    public static Object commDocode(byte[] contents){
        //初始化字节缓冲区
        ByteBuf byteBuf = Unpooled.buffer(contents.length);
        //将报文写入缓冲区
        byteBuf.writeBytes(contents);
        return masterDecoder(byteBuf);
    }

    protected  static Object decode(ByteBuf in, ControlDomain controlDomain) throws Exception {
        int length = in.readableBytes();
        if (length > 0) {
            byte start = in.getByte(0);
            byte last = 0;
            if (start == (byte) 0x68) {
                last = in.getByte(length - 1);
                int lengthLow = HexUtils.byteToInt(in.getByte(1));
                if ((last == 22) && (length == lengthLow + 6)) {
                    VariableLengthFrame variableLengthFrame = new VariableLengthFrame(in, controlDomain);
                    return variableLengthFrame;
                }else {
                    logger.error("________帧长异常_________");
                }
                return null;
            }
            if (start == (byte) 0x10) {
                last = in.getByte(length - 1);
                if ((last == 22) && (length == 6)) {
                    //logger.info(ConvertUtil.bytes2hex(in.));
                    FixedLengthFrame fixedLengthFrame = new FixedLengthFrame(in, controlDomain);
                    return fixedLengthFrame;
                }
                return null;
            }
            if (start == (byte) 0xE5) {
                //logger.info(ConvertUtil.bytes2hex(in.array()));
                SingleByteFrame singleByteFrame = new SingleByteFrame(in);
                return singleByteFrame;
            }
            return null;
        }
        return null;
    }

    public static void main(String[] args){
        String str = "68 10 10 68 73 01 00 80 01 30 01 00 00 00 4C 34 09 06 0B 12 D2 16";
        //将16进值报文转成字节
        byte[] contents = ConvertUtil.hexstr2bytes(str.replaceAll(" ", ""));
        //调用解析方法
        IFrame iFrame = (IFrame)commDocode(contents);
        if(iFrame instanceof VariableLengthFrame) {
            VariableLengthFrame result = (VariableLengthFrame)iFrame;
        }
    }

}
