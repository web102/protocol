package com.bobandata.iot.zj102.frame.asdu.info;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.util.NumberUtil;
import com.bobandata.iot.zj102.frame.asdu.type.DataAsdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:需量
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:31 2018/8/28.
 */
@Data
public class SingleGroupAndDateInfo extends Info {
    private double markValue;
    private Date date;
    private static int energyInfoLength = 12;

    public SingleGroupAndDateInfo() {
    }
    public SingleGroupAndDateInfo(int infoAddress, double markValue, Date date, int dataNum) {
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
        long longValue;

        longValue = NumberUtil.divide(getMarkValue(),0.0001);
        DataAsdu.encodeValue(energyBuffer, longValue);

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
        long longValue = Long.parseLong(HexUtils.encodeHexStr(valueBytes, 4),16);
        setMarkValue(NumberUtil.multiply(longValue,0.0001));
        setDate(DateUtil.getDateByBuffer(buffer));
        setDataNum(HexUtils.byteToInt(buffer.readByte()));
        setChecksum(HexUtils.byteToInt(buffer.readByte()));
        return 0;
    }

    @Override
    public String toExplain() {
        return "\n"+"{信息体地址:"+getInfoAddress()+","
                +"数据值:"+NumberUtil.strFormat(this.markValue)+","
                +"时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date)+","
                +"数据状态位:"+getDataNum()+","
                +"校验位:"+getChecksum()
                +"}";
    }
}
