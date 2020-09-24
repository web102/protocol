package com.bobandata.iot.zj102.frame.asdu.info;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.frame.util.IUtilFrame;

public abstract class Info
        implements IUtilFrame {
    private int infoAddress;
    private int dataNum;
    private int checksum;
    private int infoLength;

    private String hexDump;//报文info部分的16进制字符串

    public int getInfoAddress() {
        return this.infoAddress;
    }
    public void setInfoAddress(int infoAddress) {
        this.infoAddress = infoAddress;
    }
    public int getDataNum() {
        return this.dataNum;
    }
    public void setDataNum(int dataNum) {
        this.dataNum = dataNum;
    }
    public int getChecksum() {
        return this.checksum;
    }
    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }
    public int getInfoLength() {return this.infoLength;}
    public void setInfoLength(int infoLength) {
        this.infoLength = infoLength;
    }
    public String getHexDump() {return hexDump;}
    public void setHexDump(String hexDump) {this.hexDump = hexDump;}

    public abstract String toExplain();

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(getHexDump().toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }
}
