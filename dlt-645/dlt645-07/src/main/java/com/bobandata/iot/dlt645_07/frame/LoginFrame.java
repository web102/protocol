package com.bobandata.iot.dlt645_07.frame;

import com.bobandata.iot.dlt645_07.frame.util.PostalAddress;
import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import io.netty.buffer.ByteBuf;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 20:41 2018/11/6.
 */
public class LoginFrame implements IFrame {

    //开始字符
    private byte startByte;

    //地址编码
    private PostalAddress postalAddress;

    //结束字符
    private byte endByte;

    private ByteBuf buffer;

    public LoginFrame(ByteBuf in){
        this.postalAddress = new PostalAddress();
        this.buffer = in;
        try {
            this.decode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int encode() throws Exception {
        buffer.writeByte(startByte);
        postalAddress.encode(buffer);
        buffer.writeByte(endByte);
        return 0;
    }

    @Override
    public int decode() throws Exception {
        buffer.readByte();
        //***postalAddress解码 start
        long tempAddr = 0L;
        byte[] code = new byte[6];
        buffer.readBytes(code);
        tempAddr = ConvertUtil.getInt(code[0]) +
                ConvertUtil.getInt(code[1]) * 100L +
                ConvertUtil.getInt(code[2]) * 10000L +
                ConvertUtil.getInt(code[3]) * 1000000L +
                ConvertUtil.getInt(code[4]) * 100000000L +
                ConvertUtil.getInt(code[5]) * 10000000000L;
        postalAddress.setAddress(tempAddr);
        //***postalAddress解码 end
        buffer.readByte();
        return 0;
    }

    public byte getStartByte() {
        return startByte;
    }

    public void setStartByte(byte startByte) {
        this.startByte = startByte;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    public byte getEndByte() {
        return endByte;
    }

    public void setEndByte(byte endByte) {
        this.endByte = endByte;
    }

    @Override
    public String toHexString() {
        return null;
    }

    @Override
    public String toExplain() {
        return null;
    }

    @Override
    public ByteBuf getBuffer() {
        return null;
    }
}
