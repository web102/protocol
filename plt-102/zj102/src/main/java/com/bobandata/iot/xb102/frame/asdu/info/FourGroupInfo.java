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
public class FourGroupInfo extends Info {
    private int tineValue;
    private int peakValue;
    private int flatValue;
    private int ravineValue;
    private static int energyInfoLength = 19;
    public FourGroupInfo() {
    }

    public FourGroupInfo(int infoAddress, int tineValue, int peakValue, int flatValue, int ravineValue, int dateNum) {
        setInfoAddress(infoAddress);
        this.tineValue = tineValue;
        this.peakValue = peakValue;
        this.flatValue = flatValue;
        this.ravineValue = ravineValue;
        setDataNum(dateNum);
        setInfoLength(energyInfoLength);
    }

    public int getTineValue() {
        return tineValue;
    }
    public void setTineValue(int tineValue) {
        this.tineValue = tineValue;
    }
    public int getPeakValue() {
        return peakValue;
    }
    public void setPeakValue(int peakValue) {
        this.peakValue = peakValue;
    }
    public int getFlatValue() {
        return flatValue;
    }
    public void setFlatValue(int flatValue) {
        this.flatValue = flatValue;
    }
    public int getRavineValue() {
        return ravineValue;
    }
    public void setRavineValue(int ravineValue) {
        this.ravineValue = ravineValue;
    }

    public int encode(ByteBuf buffer) throws Exception {
        ByteBuf energyBuffer = Unpooled.buffer(energyInfoLength);

        energyBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));
        DataAsdu.encodeValue(energyBuffer, getTineValue());
        DataAsdu.encodeValue(energyBuffer, getPeakValue());
        DataAsdu.encodeValue(energyBuffer, getFlatValue());
        DataAsdu.encodeValue(energyBuffer, getRavineValue());
        energyBuffer.writeByte(HexUtils.intToByte(getDataNum()));

        byte sumCs = HexUtils.cs(energyBuffer, 0);
        energyBuffer.writeByte(sumCs);

        buffer.writeBytes(energyBuffer.array());
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));

        setInfoAddress(HexUtils.byteToInt(buffer.readByte()));

        byte[] valueBytes = new byte[4];
        buffer.readBytes(valueBytes);
        setTineValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4),16));
        buffer.readBytes(valueBytes);
        setPeakValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4), 16));
        buffer.readBytes(valueBytes);
        setFlatValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4), 16));
        buffer.readBytes(valueBytes);
        setRavineValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4), 16));

        setDataNum(HexUtils.byteToInt(buffer.readByte()));

        setChecksum(HexUtils.byteToInt(buffer.readByte()));
        return 0;
    }
    @Override
    public String toExplain() {
        return "\n\t"+"信息体{信息体地址:"+getInfoAddress()+","
                +"尖:"+this.tineValue+","
                +"峰:"+this.peakValue+","
                +"平:"+this.flatValue+","
                +"谷:"+this.ravineValue+","
                +"数据状态位:"+getDataNum()+","
                +"校验位:"+getChecksum()
                +"}";
    }
}
