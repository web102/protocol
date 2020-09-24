package com.bobandata.iot.zj102.frame.asdu;

import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.frame.util.IUtilFrame;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class AsduHead implements IUtilFrame {
    private int ti;     //标志类型
    private byte vsq;   //可变结构限定词
    private byte cot;   //传输原因
    private Integer pad; //公共地址/终端地址
    private byte rad;   //记录地址
    private int asduHeadLength = 6;
    private String hexDump;//用来记录Asdu头部的16进制字符串
    //可变结构限定词{8,7-1}
    private int vsqSQ;
    private int vsqT;
    //传输原因{8,7,6-1}
    private int cotT;
    private int cotPN;
    private int cotS;
    public AsduHead() {}

    public AsduHead(int ti, byte vsq, byte cot, Integer pad, byte rad) {
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
        buffer.writeBytes(HexUtils.numberToBytes(getPad(),2,true));
        buffer.writeByte(getRad());
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setTi(buffer.readByte());
        setVsq(buffer.readByte());
        setCot(buffer.readByte());
        byte[] bytes = new byte[2];
        buffer.readBytes(bytes);
        setPad((int) HexUtils.bytesToLong(bytes,true));
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
