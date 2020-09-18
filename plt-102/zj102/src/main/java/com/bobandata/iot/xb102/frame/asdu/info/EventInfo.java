package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;

import java.util.Date;

/**
 * Created by 拓 on 2017/10/27.
 */
public class EventInfo extends Info{

    private int spa;
    private int spq;
    private int spi;
    private Date timestamp;
    //单点事件信息的长度
    private static int eventInfoLength = 9;

    public EventInfo(){
    	
    }
    
    public EventInfo(int spa, int spq, int spi, Date timestamp){
        this.spa = spa;
        this.spq = spq;
        this.spi = spi;
        this.timestamp = timestamp;
        this.setInfoLength(eventInfoLength);
    }

    @Override
    public int encode(ByteBuf ioBuffer) throws Exception {
        ioBuffer.writeByte(HexUtils.intToByte(spa));
        byte b = (byte) (spi << 0 | spq << 1);
        ioBuffer.writeByte(b);
        DateUtil.getBufferByTimestamp(ioBuffer,timestamp);
        return 0;
    }

    @Override
    public int decode(ByteBuf ioBuffer) throws Exception {
    	this.setSpa(HexUtils.byteToInt(ioBuffer.readByte()));
    	byte b = ioBuffer.readByte();
    	this.setSpi(b >> 0);
    	this.setSpq(b >> 1);
    	this.setTimestamp(DateUtil.getTimestampByBuffer(ioBuffer));;
        return 0;
    }

    @Override
    public String toHexString() {
        return null;
    }

    @Override
    public String toExplain() {
        return null;
    }


    public int getSpa() {
        return spa;
    }

    public void setSpa(int spa) {
        this.spa = spa;
    }

    public int getSpq() {
        return spq;
    }

    public void setSpq(int spq) {
        this.spq = spq;
    }

    public int getSpi() {
        return spi;
    }

    public void setSpi(int spi) {
        this.spi = spi;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static int getEventInfoLength() {
        return eventInfoLength;
    }

    public static void setEventInfoLength(int eventInfoLength) {
        EventInfo.eventInfoLength = eventInfoLength;
    }
}
