package com.bobandata.iot.zj102.frame.util;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

public class VariableLengthCheckSum implements IUtilFrame {
    private byte checkSum;
    private byte countCs;//主站通过报文计算出的校验位
    private String hexDump;//

    public int encode(ByteBuf buffer) throws Exception {
        this.checkSum = HexUtils.cs(buffer, 4);
        this.countCs = this.checkSum;
        buffer.writeByte(this.checkSum);
        this.hexDump = ByteBufUtil.hexDump(buffer);
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        this.hexDump = ByteBufUtil.hexDump(buffer);
        this.countCs=HexUtils.countCs(buffer);
        this.checkSum = buffer.readByte();
        return this.checkSum;
    }

    public byte getCheckSum() {
        return this.checkSum;
    }
    public void setCheckSum(byte checkSum) {
        this.checkSum = checkSum;
    }

    public String toExplain() {
        return "报文校验位:"+(this.checkSum&0xFF)+",计算校验位:"+(this.countCs&0xFF);
    }

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }
}