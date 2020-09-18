package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.asdu.type.DataAsdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

/**
 * @Author: lizhipeng
 * @Description:费率
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:28 2018/8/20.
 */
public class FourGroupInfo extends DataInfo {
    private double tineValue;
    private double peakValue;
    private double flatValue;
    private double ravineValue;
    private  int infoLength = 19;
    public FourGroupInfo() {
    }

    public FourGroupInfo(int infoAddress, int tineValue, int peakValue, int flatValue, int ravineValue, int dateNum) {
        setInfoAddress(infoAddress);
        this.tineValue = tineValue;
        this.peakValue = peakValue;
        this.flatValue = flatValue;
        this.ravineValue = ravineValue;
        setStatus(dateNum);
        setInfoLength(infoLength);
    }

    public double getTineValue() {
        return tineValue;
    }
    public void setTineValue(double tineValue) {
        this.tineValue = tineValue;
    }
    public double getPeakValue() {
        return peakValue;
    }
    public void setPeakValue(double peakValue) {
        this.peakValue = peakValue;
    }
    public double getFlatValue() {
        return flatValue;
    }
    public void setFlatValue(double flatValue) {
        this.flatValue = flatValue;
    }
    public double getRavineValue() {
        return ravineValue;
    }
    public void setRavineValue(double ravineValue) {
        this.ravineValue = ravineValue;
    }

    public int encode(ByteBuf buffer) throws Exception {
        ByteBuf energyBuffer = Unpooled.buffer(infoLength);

        energyBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));
        DataAsdu.encodeValue(energyBuffer, getTineValue());
        DataAsdu.encodeValue(energyBuffer, getPeakValue());
        DataAsdu.encodeValue(energyBuffer, getFlatValue());
        DataAsdu.encodeValue(energyBuffer, getRavineValue());
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
        this.infoLength=infoLength;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));

        setInfoAddress(HexUtils.byteToInt(buffer.readByte()));
        double accuracy = 0.01;
        byte[] valueBytes = new byte[4];
        buffer.readBytes(valueBytes);
        setTineValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4),16)*accuracy);
        buffer.readBytes(valueBytes);
        setPeakValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4), 16)*accuracy);
        buffer.readBytes(valueBytes);
        setFlatValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4), 16)*accuracy);
        buffer.readBytes(valueBytes);
        setRavineValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4), 16)*accuracy);

        setStatus(HexUtils.byteToInt(buffer.readByte()));

        setChecksum(HexUtils.byteToInt(buffer.readByte()));
        return 0;
    }
    @Override
    public String toExplain() {
        return "\n"+"信息体{信息体地址:"+getInfoAddress()+","
                +"尖:"+this.tineValue+","
                +"峰:"+this.peakValue+","
                +"平:"+this.flatValue+","
                +"谷:"+this.ravineValue+","
                +"数据状态位:"+ getStatus()+","
                +"校验位:"+getChecksum()
                +"}";
    }
}
