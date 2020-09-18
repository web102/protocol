package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 拓 on 2017/10/27.
 */
public class EventInfo extends Info{
    
    private int spi;
    private Date timestamp;
    //单点事件信息的长度
    private int infoLength = 7;

    public EventInfo(){
    	
    }
    
    public EventInfo(int spi, Date timestamp){
        this.spi = spi;
        this.timestamp = timestamp;
        this.setInfoLength(infoLength);
    }



    @Override
    public int encode(ByteBuf ioBuffer) throws Exception {
        ioBuffer.writeByte(HexUtils.intToByte(getInfoAddress()));
        ioBuffer.writeByte(spi);
        DateUtil.getBufferByDate(ioBuffer,timestamp);
        return 0;
    }

    @Override
    public int getInfoLength() {
        return infoLength;
    }

    @Override
    public void setInfoLength(int infoLength) {
        this.infoLength = infoLength;
    }

    public int decode(ByteBuf ioBuffer) throws Exception {
        setInfoAddress(HexUtils.byteToInt(ioBuffer.readByte()));
    	this.setSpi(HexUtils.byteToInt(ioBuffer.readByte()));
    	this.setTimestamp(DateUtil.getDateByBuffer(ioBuffer));;
        return 0;
    }

    @Override
    public String toHexString() {
        return null;
    }

    @Override
    public String toExplain() {
        int meterNo = getInfoAddress();
        boolean valid = ((spi&0xFF)>>7)==1;
        int eventType = (spi&0xFF)&0x7F;
        String a = "\n" + "信息体{";

        String event;
        if(!valid){
            switch (eventType){
                case 0:event="开机";break;
                case 1:event="关机";break;
                case 2:event="保存参数";break;
                case 3:event="校时";break;
                default:
                    event="未知";break;
            }
        }else {
            switch (eventType){
                case 6:event="电表通信中断";break;
                case 7:event="电表通信恢复";break;
                case 50:event="三相欠压发生";break;
                case 51:event="三相欠压恢复";break;
                case 52:event="Ａ相欠压发生";break;
                case 53:event="Ａ相欠压恢复";break;
                case 54:event="Ｂ相欠压发生";break;
                case 55:event="Ｂ相欠压恢复";break;
                case 56:event="Ｃ相欠压发生";break;
                case 57:event="Ｃ相欠压恢复";break;
                case 58:event="三相过压发生";break;
                case 59:event="三相过压恢复";break;
                case 60:event="Ａ相过压发生";break;
                case 61:event="Ａ相过压恢复";break;
                case 62:event="Ｂ相过压发生";break;
                case 63:event="Ｂ相过压恢复";break;
                case 64:event="Ｃ相过压发生";break;
                case 65:event="Ｃ相过压恢复";break;
                default:
                    event="未知";break;
            }
        }

        a=a+"电表序号:"+meterNo+",序号生效:"+valid+",事件类型:"+eventType+"  --"+event+"--  ";

        return a+"时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp) + "}";
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
}
