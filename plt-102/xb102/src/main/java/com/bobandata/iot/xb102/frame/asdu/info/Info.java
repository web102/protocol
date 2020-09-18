package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.util.IUtilFrame;
import io.netty.buffer.ByteBuf;

/**
 * @Author: 李志鹏
 * @Date: 2019/12/1 12:35
 * @Desc:
 **/
public abstract class Info implements IUtilFrame {
    private int infoAddress;

    private String hexDump;//报文info部分的16进制字符串
    public abstract   String toExplain();
    public abstract int encode(ByteBuf buffer) throws Exception;


    public int getInfoAddress() {
        return this.infoAddress;
    }
    public void setInfoAddress(int infoAddress) {
        this.infoAddress = infoAddress;
    }

    public abstract int getInfoLength();
    public abstract void setInfoLength(int infoLength);

    public String getHexDump() {return hexDump;}
    public void setHexDump(String hexDump) {this.hexDump = hexDump;}

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(getHexDump().toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }
}
