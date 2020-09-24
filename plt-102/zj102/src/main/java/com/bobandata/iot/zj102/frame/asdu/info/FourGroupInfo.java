package com.bobandata.iot.zj102.frame.asdu.info;

import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.util.NumberUtil;
import com.bobandata.iot.zj102.frame.asdu.type.DataAsdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import lombok.Data;

/**
 * @Author: lizhipeng
 * @Description:费率
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:28 2018/8/20.
 */
@Data
public class FourGroupInfo extends Info {
    private double tineValue;
    private double peakValue;
    private double flatValue;
    private double ravineValue;
    private static int energyInfoLength = 19;
    public FourGroupInfo() {
    }

    public FourGroupInfo(int infoAddress, double tineValue, double peakValue, double flatValue, double ravineValue, int dateNum) {
        setInfoAddress(infoAddress);
        this.tineValue = tineValue;
        this.peakValue = peakValue;
        this.flatValue = flatValue;
        this.ravineValue = ravineValue;
        setDataNum(dateNum);
        setInfoLength(energyInfoLength);
    }


    public int encode(ByteBuf buffer) throws Exception {
        ByteBuf energyBuffer = Unpooled.buffer(energyInfoLength);

        energyBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));
        long longValue;

        longValue = NumberUtil.divide(getTineValue(),0.0001);
        DataAsdu.encodeValue(energyBuffer, longValue);

        longValue = NumberUtil.divide(getPeakValue(),0.0001);
        DataAsdu.encodeValue(energyBuffer, longValue);

        longValue = NumberUtil.divide(getFlatValue(),0.0001);
        DataAsdu.encodeValue(energyBuffer, longValue);

        longValue = NumberUtil.divide(getRavineValue(),0.0001);
        DataAsdu.encodeValue(energyBuffer, longValue);
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
        long longValue;
        buffer.readBytes(valueBytes);
        longValue = Long.parseLong(HexUtils.encodeHexStr(valueBytes, 4),16);
        setTineValue(NumberUtil.multiply(longValue,0.0001));

        buffer.readBytes(valueBytes);
        longValue = Long.parseLong(HexUtils.encodeHexStr(valueBytes, 4),16);
        setPeakValue(NumberUtil.multiply(longValue,0.0001));

        buffer.readBytes(valueBytes);
        longValue = Long.parseLong(HexUtils.encodeHexStr(valueBytes, 4),16);
        setFlatValue(NumberUtil.multiply(longValue,0.0001));

        buffer.readBytes(valueBytes);
        longValue = Long.parseLong(HexUtils.encodeHexStr(valueBytes, 4),16);
        setRavineValue(NumberUtil.multiply(longValue,0.0001));

        setDataNum(HexUtils.byteToInt(buffer.readByte()));
        setChecksum(HexUtils.byteToInt(buffer.readByte()));
        return 0;
    }
    @Override
    public String toExplain() {
        return "\n"+"{信息体地址:"+getInfoAddress()+","
                +"尖:"+NumberUtil.strFormat(this.tineValue)+","
                +"峰:"+NumberUtil.strFormat(this.peakValue)+","
                +"平:"+NumberUtil.strFormat(this.flatValue)+","
                +"谷:"+NumberUtil.strFormat(this.ravineValue)+","
                +"数据状态位:"+getDataNum()+","
                +"校验位:"+getChecksum()
                +"}";
    }
}
