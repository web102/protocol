package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

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
        String value = Integer.toBinaryString(this.determiner);
        switch (value.length()){
            case 0:value="00000000";break;
            case 1:value="0000000"+value;break;
            case 2:value="000000"+value;break;
            case 3:value="00000"+value;break;
            case 4:value="0000"+value;break;
            case 5:value="000"+value;break;
            case 6:value="00"+value;break;
            case 7:value="0"+value;break;
        }
        return "\n\t"+"信息体{信息体地址SPA:"+getInfoAddress()+","
                +"SPI:"+value.substring(7,8)+","
                +"SPQ:"+Integer.parseInt(value.substring(0,7),2)+","
                +"时间:"+this.date
                +"}";
    }
}