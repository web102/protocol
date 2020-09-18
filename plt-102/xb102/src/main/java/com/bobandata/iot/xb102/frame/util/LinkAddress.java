package com.bobandata.iot.xb102.frame.util;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

public class LinkAddress implements IUtilFrame {
    private String linkAddress;
    private String hexDump;//

    public LinkAddress() {
    }

    public LinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public int encode(ByteBuf buffer) throws Exception {
        buffer.writeBytes(HexUtils.decodeHex(getLinkAddress().toCharArray()));
        this.hexDump = ByteBufUtil.hexDump(buffer);
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        this.hexDump = ByteBufUtil.hexDump(buffer);
        byte[] bytes = new byte[2];
        buffer.readBytes(bytes);
        setLinkAddress(HexUtils.encodeHexStr(bytes));
        return 0;
    }

    public String getLinkAddress() {
        return this.linkAddress;
    }
    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }
    public String getHexDump() {return hexDump;}
    public void setHexDump(String hexDump) {this.hexDump = hexDump;}

    public String toExplain() {
        return "链路地址:"+this.linkAddress;
    }

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }
}