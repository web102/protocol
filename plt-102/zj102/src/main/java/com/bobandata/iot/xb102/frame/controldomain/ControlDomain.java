package com.bobandata.iot.xb102.frame.controldomain;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.util.IUtilFrame;

public abstract class ControlDomain implements IUtilFrame {
    protected byte nfb;
    protected byte prm;
    protected byte funcCode;

    private String hexDump;//控制域部分16进制字符串

    public byte getNfb() {
        return this.nfb;
    }
    public void setNfb(byte nfb) {
        this.nfb = nfb;
    }
    public byte getPrm() {
        return this.prm;
    }
    public void setPrm(byte prm) {
        this.prm = prm;
    }
    public byte getFuncCode() {
        return this.funcCode;
    }
    public void setFuncCode(byte funcCode) {
        this.funcCode = funcCode;
    }
    public String getHexDump() {return hexDump;}
    public void setHexDump(String hexDump) {this.hexDump = hexDump;}

    public abstract String toExplain();

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }
}

