package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.asdu.type.DataAsdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.text.DecimalFormat;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/11/28 10:58
 * @Description:
 */

public class FiveGroupInfo extends TariffInfo{
    private double totalValue;
    private byte totalStatus;
    private double tineValue;
    private byte tineStatus;
    private double peakValue;
    private byte peakStatus;
    private double flatValue;
    private byte flatStatus;
    private double ravineValue;
    private byte ravineStatus;
    private int infoLength = 25+2;
    public FiveGroupInfo() {
    }


    public FiveGroupInfo(int infoAddress, double totalValue, byte totalStatus, double tineValue, byte tineStatus, double peakValue, byte peakStatus, double flatValue, byte flatStatus, double ravineValue, byte ravineStatus) {
        setInfoAddress(infoAddress);
        this.totalValue = totalValue;
        this.totalStatus = totalStatus;
        this.tineValue = tineValue;
        this.tineStatus = tineStatus;
        this.peakValue = peakValue;
        this.peakStatus = peakStatus;
        this.flatValue = flatValue;
        this.flatStatus = flatStatus;
        this.ravineValue = ravineValue;
        this.ravineStatus = ravineStatus;
        setInfoLength(infoLength);
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public byte getTotalStatus() {
        return totalStatus;
    }

    public void setTotalStatus(byte totalStatus) {
        this.totalStatus = totalStatus;
    }

    public double getTineValue() {
        return tineValue;
    }

    public void setTineValue(double tineValue) {
        this.tineValue = tineValue;
    }

    public byte getTineStatus() {
        return tineStatus;
    }

    public void setTineStatus(byte tineStatus) {
        this.tineStatus = tineStatus;
    }

    public double getPeakValue() {
        return peakValue;
    }

    public void setPeakValue(double peakValue) {
        this.peakValue = peakValue;
    }

    public byte getPeakStatus() {
        return peakStatus;
    }

    public void setPeakStatus(byte peakStatus) {
        this.peakStatus = peakStatus;
    }

    public double getFlatValue() {
        return flatValue;
    }

    public void setFlatValue(double flatValue) {
        this.flatValue = flatValue;
    }

    public byte getFlatStatus() {
        return flatStatus;
    }

    public void setFlatStatus(byte flatStatus) {
        this.flatStatus = flatStatus;
    }

               public double getRavineValue() {
        return ravineValue;
    }

    public void setRavineValue(double ravineValue) {
        this.ravineValue = ravineValue;
    }

    public byte getRavineStatus() {
        return ravineStatus;
    }

    public void setRavineStatus(byte ravineStatus) {
        this.ravineStatus = ravineStatus;
    }

    public int encode(ByteBuf buffer) throws Exception {
        ByteBuf energyBuffer = Unpooled.buffer(infoLength);

        energyBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));
        DataAsdu.encodeValue(energyBuffer, getTotalValue());
        energyBuffer.writeByte(getTotalStatus());
        DataAsdu.encodeValue(energyBuffer, getTineValue());
        energyBuffer.writeByte(getTineStatus());
        DataAsdu.encodeValue(energyBuffer, getPeakValue());
        energyBuffer.writeByte(getPeakStatus());
        DataAsdu.encodeValue(energyBuffer, getFlatValue());
        energyBuffer.writeByte(getFlatStatus());
        DataAsdu.encodeValue(energyBuffer, getRavineValue());
        energyBuffer.writeByte(getRavineStatus());

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
        this.infoLength=infoLength;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        setInfoAddress(HexUtils.byteToInt(buffer.readByte()));
        byte[] valueBytes = new byte[4];
        double accuracy = 0.0001;
        buffer.readBytes(valueBytes);
        double value = Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4),16)*accuracy;
        setTotalValue(value);
        setTotalStatus(buffer.readByte());
        buffer.readBytes(valueBytes);
        value = Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4),16)*accuracy;
        setTineValue(value);
        setTineStatus(buffer.readByte());
        buffer.readBytes(valueBytes);
        value = Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4),16)*accuracy;
        setPeakValue(value);
        setPeakStatus(buffer.readByte());
        buffer.readBytes(valueBytes);
        value = Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4),16)*accuracy;
        setFlatValue(value);
        setFlatStatus(buffer.readByte());
        buffer.readBytes(valueBytes);
        value = Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4),16)*accuracy;
        setRavineValue(value);
        setRavineStatus(buffer.readByte());

        setChecksum(HexUtils.byteToInt(buffer.readByte()));
        return 0;
    }
    @Override
    public String toExplain() {
        DecimalFormat format = new DecimalFormat("0.0000");
        return "\n"+"信息体{信息体地址:"+getInfoAddress()+","
                +"总:"+format.format(this.totalValue)+","
                +"状态:"+this.totalStatus+","
                +"尖:"+format.format(this.tineValue)+","
                +"状态:"+this.tineStatus+","
                +"峰:"+format.format(this.peakValue)+","
                +"状态:"+this.peakStatus+","
                +"平:"+format.format(this.flatValue)+","
                +"状态:"+this.flatStatus+","
                +"谷:"+format.format(this.ravineValue)+","
                +"状态:"+this.ravineStatus+","
                +"校验位:"+getChecksum()
                +"}";
    }
}