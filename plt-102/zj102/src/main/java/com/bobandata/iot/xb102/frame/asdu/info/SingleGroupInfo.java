package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.asdu.type.DataAsdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

public class SingleGroupInfo extends Info {
    private int markValue;
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

        DataAsdu.encodeValue(energyBuffer, getMarkValue());

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
        setMarkValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 4), 16));

        setDataNum(HexUtils.byteToInt(buffer.readByte()));

        setChecksum(HexUtils.byteToInt(buffer.readByte()));
        return 0;
    }

    public int getMarkValue() {
        return this.markValue;
    }

    public void setMarkValue(int markValue) {
        this.markValue = markValue;
    }

    @Override
    public String toExplain() {
        return "\n\t"+"信息体{信息体地址:"+getInfoAddress()+","
                +"数据值:"+this.markValue+","
                +"数据状态位:"+getDataNum()+","
                +"校验位:"+getChecksum()
                +"}";
    }
}

