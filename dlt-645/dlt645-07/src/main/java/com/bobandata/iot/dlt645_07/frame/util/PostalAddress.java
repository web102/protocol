package com.bobandata.iot.dlt645_07.frame.util;

import com.bobandata.iot.transport.util.ConvertUtil;
import io.netty.buffer.ByteBuf;

/**
 * @Author: liutuo
 * @Description: 通讯地址
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 19:28 2018/9/1.
 */
public class PostalAddress implements IUtilFrame{

    private long address;

    public PostalAddress(){

    }

    public PostalAddress(long address){
        this.address = address;
    }

    @Override
    public int encode(ByteBuf paramByteBuf) throws Exception {
        if(address != 0){
            byte[] value = new byte[6];
            value[0] = (byte) ConvertUtil.transBinayToBcd((int)(address%100));
            value[1] = (byte) ConvertUtil.transBinayToBcd((int)((address/100)%100));
            value[2] = (byte) ConvertUtil.transBinayToBcd((int)((address/10000)%100));
            value[3] = (byte) ConvertUtil.transBinayToBcd((int)((address/1000000)%100));
            value[4] = (byte) ConvertUtil.transBinayToBcd((int)((address/100000000)%100));
            value[5] = (byte) ConvertUtil.transBinayToBcd((int)((address/10000000000l)%100));
            paramByteBuf.writeBytes(value);
        }else{
            for (int i=0;i<6;i++) {
                paramByteBuf.writeByte(0xAA);
            }
        }
        return 0;
    }

    @Override
    public int decode(ByteBuf paramByteBuf) throws Exception {
        byte[] code = new byte[6];
        paramByteBuf.readBytes(code);
        long tempAddr = 0;
        tempAddr =  (long) ConvertUtil.transBcdToBinay(code[0]) +
                (long)ConvertUtil.transBcdToBinay(code[1]) * 100 +
                (long)ConvertUtil.transBcdToBinay(code[2]) * 10000 +
                (long)ConvertUtil.transBcdToBinay(code[3]) * 1000000 +
                (long)ConvertUtil.transBcdToBinay(code[4]) * 100000000 +
                (long)ConvertUtil.transBcdToBinay(code[5]) * 10000000000l;
        this.address = tempAddr;
        return 0;
    }

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }
}
