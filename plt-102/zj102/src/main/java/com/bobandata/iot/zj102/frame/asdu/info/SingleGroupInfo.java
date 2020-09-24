package com.bobandata.iot.zj102.frame.asdu.info;

import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.util.NumberUtil;
import com.bobandata.iot.zj102.frame.asdu.type.DataAsdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import lombok.Data;

@Data
public class SingleGroupInfo extends Info {
    private double markValue;
    private static int energyInfoLength = 7;

    public SingleGroupInfo() {
    }

    public SingleGroupInfo(int infoAddress, int markValue, int dataNum) {
        setInfoAddress(infoAddress);
        this.markValue = markValue;
        setDataNum(dataNum);
        setInfoLength(energyInfoLength);
    }

    public int encode(ByteBuf buffer) throws Exception {
        ByteBuf energyBuffer = Unpooled.buffer(energyInfoLength);

        energyBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));

        long longValue;

        longValue = NumberUtil.divide(getMarkValue(),0.0001);
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
        buffer.readBytes(valueBytes);
        long longValue = Long.parseLong(HexUtils.encodeHexStr(valueBytes, 4),16);
        setMarkValue(NumberUtil.multiply(longValue,0.0001));

        setDataNum(HexUtils.byteToInt(buffer.readByte()));

        setChecksum(HexUtils.byteToInt(buffer.readByte()));
        return 0;
    }


    @Override
    public String toExplain() {
        return "\n"+"{信息体地址:"+getInfoAddress()+","
                +"数据值:"+NumberUtil.strFormat(this.markValue)+","
                +"数据状态位:"+getDataNum()+","
                +"校验位:"+getChecksum()
                +"}";
    }
}

