package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.asdu.type.DataAsdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.text.DecimalFormat;

public class SingleGroupInfo extends DataInfo {
    private double markValue;
    private  int infoLength = 7;

    public SingleGroupInfo() {
    }

    public SingleGroupInfo(int infoAddress, int markValue, int dataNum) {
        setInfoAddress(infoAddress);
        this.markValue = markValue;
        setStatus(dataNum);
        setInfoLength(infoLength);
    }

    public int encode(ByteBuf buffer) throws Exception {
        ByteBuf energyBuffer = Unpooled.buffer(infoLength);

        energyBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));

        DataAsdu.encodeValue(energyBuffer, getMarkValue());

        energyBuffer.writeByte(HexUtils.intToByte(getStatus()));

        byte sumCs = HexUtils.cs(energyBuffer, 0);
        energyBuffer.writeByte(sumCs);

        buffer.writeBytes(energyBuffer.array());
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    @Override
    public int getInfoLength() {
        return infoLength;
    }

    @Override
    public void setInfoLength(int infoLength) {
        this.infoLength = infoLength;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        setInfoAddress(HexUtils.byteToInt(buffer.readByte()));

        byte[] valueBytes = new byte[4];
        buffer.readBytes(valueBytes);
        setMarkValue((float) (Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4), 16)*0.0001));

        setStatus(HexUtils.byteToInt(buffer.readByte()));

        setChecksum(HexUtils.byteToInt(buffer.readByte()));
        return 0;
    }

    public double getMarkValue() {
        return this.markValue;
    }

    public void setMarkValue(double markValue) {
        this.markValue = markValue;
    }

    @Override
    public String toExplain() {
        DecimalFormat format = new DecimalFormat("0.0000");
        return "\n"+"信息体{信息体地址:"+getInfoAddress()+","
                +"数据值:"+format.format(this.markValue)+","
                +"数据状态位:"+ getStatus()+","
                +"校验位:"+getChecksum()
                +"}";
    }
}

