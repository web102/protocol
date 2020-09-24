package com.bobandata.iot.zj102.frame.util;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

public class VariableLengthHead implements IUtilFrame {
    private int dataLength;
    private static int controlAreaLength = 1;
    private static int linkAddressLength = 2;
    private String hexDump;//

    public VariableLengthHead() {
    }

    public VariableLengthHead(int asduLength) {
        this.dataLength = (controlAreaLength + linkAddressLength + asduLength);
    }

    public int encode(ByteBuf buffer) throws Exception {
        buffer.writeByte(104);
        buffer.writeByte(HexUtils.intToByte(getDataLength()));
        buffer.writeByte(HexUtils.intToByte(getDataLength()));
        buffer.writeByte(104);
        this.hexDump = ByteBufUtil.hexDump(buffer);
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        this.hexDump = ByteBufUtil.hexDump(buffer);
        buffer.readByte();
        setDataLength(HexUtils.byteToInt(buffer.readByte()));
        setDataLength(HexUtils.byteToInt(buffer.readByte()));
        buffer.readByte();
        return 0;
    }

    public String toExplain() {
        return "消息头{开始字符:68,"
                +"消息长度:"+this.dataLength+","
                +"开始字符:68"
                +"}";
    }

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }

    public int getDataLength() {
        return this.dataLength;
    }
    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }
}
