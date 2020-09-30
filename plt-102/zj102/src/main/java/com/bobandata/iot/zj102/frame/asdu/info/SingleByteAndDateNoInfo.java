package com.bobandata.iot.zj102.frame.asdu.info;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/11/13 11:03
 * @Description:
 */

public class SingleByteAndDateNoInfo extends Info {
    private int determiner;
    private Date date;
    private static int energyInfoLength = 9;
    public SingleByteAndDateNoInfo() {
    }
    public SingleByteAndDateNoInfo(int infoAddress, int determiner , Date date) {
        setInfoAddress(infoAddress);
        this.determiner = determiner;
        this.date= date;
        setInfoLength(energyInfoLength);

    }

    @Override
    public int encode(ByteBuf buffer) throws Exception {
        ByteBuf energyBuffer = Unpooled.buffer(energyInfoLength);
        energyBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));
        energyBuffer.writeByte(HexUtils.intToByte(getDeterminer()));
        DateUtil.getBufferByTimestamp(energyBuffer, this.date);
        buffer.writeBytes(energyBuffer.array());
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        setInfoAddress(HexUtils.byteToInt(buffer.readByte()));
        setDeterminer(HexUtils.byteToInt(buffer.readByte()));
        setDate(DateUtil.getTimestampByBuffer(buffer));
        return 0;
    }

    public int getDeterminer() {
        return determiner;
    }

    public void setDeterminer(int determiner) {
        this.determiner = determiner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toExplain() {
        int a = this.determiner&0b1;
        return "\n"+"{SPA:"+getInfoAddress()+","
                +"SPI:"+(this.determiner&0b1)+","
                +"SPQ:"+(this.determiner>>1)+","
                +"时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date)
                +"}";
    }
}