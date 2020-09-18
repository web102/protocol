package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.asdu.type.DataAsdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:需量
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:31 2018/8/28.
 */
public class SingleGroupAndDateInfo extends Info {
    private int markValue;
    private Date date;
    private static int energyInfoLength = 12;

    public SingleGroupAndDateInfo() {
    }
    public SingleGroupAndDateInfo(int infoAddress, int markValue, Date date, int dataNum) {
        setInfoAddress(infoAddress);
        this.markValue = markValue;
        this.date= date;
        setDataNum(dataNum);
        setInfoLength(energyInfoLength);

    }

    @Override
    public int encode(ByteBuf buffer) throws Exception {
        ByteBuf energyBuffer = Unpooled.buffer(energyInfoLength);

        energyBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));

        DataAsdu.encodeValue(energyBuffer, getMarkValue());

        DateUtil.getBufferByDate(energyBuffer, this.date);

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
        setDate(DateUtil.getDateByBuffer(buffer));
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
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toExplain() {
        return "\n\t"+"信息体{信息体地址:"+getInfoAddress()+","
                +"数据值:"+this.markValue+","
                +"时间:"+this.date+","
                +"数据状态位:"+getDataNum()+","
                +"校验位:"+getChecksum()
                +"}";
    }
}
