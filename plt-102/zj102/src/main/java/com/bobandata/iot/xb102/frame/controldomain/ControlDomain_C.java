package com.bobandata.iot.xb102.frame.controldomain;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

//主站
public class ControlDomain_C extends ControlDomain {
    public static final byte RESET_CU = 0;
    public static final byte SEND_DATA = 3;
    public static final byte CALL_LINK_STATUS = 9;
    public static final byte REQUEST_CLASS1_DATA = 10;
    public static final byte REQUEST_CLASS2_DATA = 11;
    private byte fcb;
    private byte fcv;

    public ControlDomain_C() {
    }

    public ControlDomain_C(byte nfb, byte prm, byte fcb, byte fcv, byte funcode) {
        this.nfb = nfb;
        this.prm = prm;
        this.fcb = fcb;
        this.fcv = fcv;
        this.funcCode = funcode;
    }

    public byte getFcb() {
        return this.fcb;
    }

    public void setFcb(byte fcb) {
        this.fcb = fcb;
    }

    public byte getFcv() {
        return this.fcv;
    }

    public void setFcv(byte fcv) {
        this.fcv = fcv;
    }

    public void setFuncCode(byte funcCode) {
        super.setFuncCode(funcCode);
        if ((funcCode == 3) || (funcCode == 10) || (funcCode == 11))
            this.fcv = 1;
        else
            this.fcv = 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        byte b = buffer.readByte();
        this.funcCode = ((byte) (b & 0xF));
        this.fcv = ((byte) (b >> 4 & 0x1));
        this.fcb = ((byte) (b >> 5 & 0x1));
        this.prm = ((byte) (b >> 6 & 0x1));
        this.nfb = ((byte) (b >> 7 & 0x1));
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 1;
    }

    public int encode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        byte b = (byte) (this.funcCode | this.fcv << 4 | this.fcb << 5 | this.prm << 6 | this.nfb << 7);
        buffer.writeByte(b);
        return 1;
    }
    @Override
    public String toExplain() {
        return "控制域{nfb:"+this.nfb+","
                +"prm:"+this.prm+","
                +"fcb:"+this.fcb+","
                +"fcv:"+this.fcv+","
                +"功能码:"+this.funcCode+"}";
    }
}
