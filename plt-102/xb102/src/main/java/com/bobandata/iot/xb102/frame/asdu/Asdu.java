package com.bobandata.iot.xb102.frame.asdu;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.util.IUtilFrame;

public abstract class Asdu implements IUtilFrame {
    private int asduLength;
    private AsduHead asduHead;

    private String hexDump;//用来记录Asdu部分的16进制报文字符串

    public AsduHead getAsduHead() {
        return this.asduHead;
    }
    public void setAsduHead(AsduHead asduHead) {
        this.asduHead = asduHead;
    }
    public int getAsduLength() {
        return this.asduLength;
    }
    public void setAsduLength(int asduLength) {
        this.asduLength = asduLength;
    }
    public String getHexDump() {return hexDump;}
    public void setHexDump(String hexDump) {this.hexDump = hexDump;}

    public abstract String toExplain();

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }
}
