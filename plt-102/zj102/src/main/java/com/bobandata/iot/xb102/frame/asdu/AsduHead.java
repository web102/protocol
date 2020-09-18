package com.bobandata.iot.xb102.frame.asdu;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.util.IUtilFrame;
import io.netty.buffer.ByteBuf;

public class AsduHead implements IUtilFrame {
    private int ti;     //标志类型
    private byte vsq;   //可变结构限定词
    private byte cot;   //传输原因
    private String pad; //公共地址
    private byte rad;   //记录地址
    private static int asduHeadLength = 6;
    private String hexDump;//用来记录Asdu头部的16进制字符串
    //可变结构限定词{8,7-1}
    private int vsqSQ;
    private int vsqT;
    //传输原因{8,7,6-1}
    private int cotT;
    private int cotPN;
    private int cotS;
    public AsduHead() {}

    public AsduHead(int ti, byte vsq, byte cot, String pad, byte rad) {
        this.ti = ti;
        this.vsq = vsq;
        this.cot = cot;
        this.pad = pad;
        this.rad = rad;
        csqDecode();
        cotDecode();
    }

    public int encode(ByteBuf buffer) throws Exception {
        buffer.writeByte((byte)getTi());
        buffer.writeByte(getVsq());
        buffer.writeByte(getCot());
        buffer.writeBytes(HexUtils.decodeHex(getPad().toCharArray()));
        buffer.writeByte(getRad());
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setTi(buffer.readByte());
        setVsq(buffer.readByte());
        setCot(buffer.readByte());
        byte[] bytes = new byte[2];
        buffer.readBytes(bytes);
        setPad(HexUtils.encodeHexStr(bytes));
        setRad(buffer.readByte());
        csqDecode();
        cotDecode();
        return 0;
    }

    public void csqDecode(){
        String vsq = ConvertUtil.bytesTo8bin(new byte[]{this.vsq});
        this.vsqSQ =Integer.parseInt(vsq.substring(0,1),2);
        this.vsqT = Integer.parseInt(vsq.substring(1),2);
    }
    public void cotDecode(){
        String cot = ConvertUtil.bytesTo8bin(new byte[]{this.cot});
        this.cotT = Integer.parseInt(cot.substring(0,1),2);
        this.cotPN = Integer.parseInt(cot.substring(1,2),2);
        this.cotS = Integer.parseInt(cot.substring(2),2);
    }

    public int getTi() {
        return this.ti&0xff;
    }
    public void setTi(int ti) {
        this.ti = ti&0xff;
    }
    public byte getVsq() {
        return this.vsq;
    }
    public void setVsq(byte vsq) {this.vsq = vsq;}
    public byte getCot() {
        return this.cot;
    }
    public void setCot(byte cot) {
        this.cot = cot;
    }
    public String getPad() {
        return this.pad;
    }
    public void setPad(String pad) {
        this.pad = pad;
    }
    public byte getRad() {
        return this.rad;
    }
    public void setRad(byte rad) {
        this.rad = rad;
    }
    public static int getAsduHeadLength() {
        return asduHeadLength;
    }
    public static void setAsduHeadLength(int asduHeadLength) {asduHeadLength = asduHeadLength;}

    public String toExplain() {
        return "数据头{数据类型:"+(this.ti &0xFF)+","
                +"可变结构限定词:{SQ:"+this.vsqSQ+",number:"+this.vsqT+"},"
                +"传输原因:{实验:"+this.cotT+",否定确认:"+this.cotPN+",原因："+this.cotS+"},"
                +"终端地址:"+this.pad +","
                +"记录地址:"+this.rad
                +"}";
    }

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }
}
