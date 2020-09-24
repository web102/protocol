package com.bobandata.iot.zj102.frame.controldomain;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

//从站
public class ControlDomain_C extends ControlDomain {
    private byte acd;
    private byte dfc;

    public ControlDomain_C() {
    }

    public ControlDomain_C(byte nfb, byte prm, byte acd, byte dfc, byte funcode) {
        this.nfb = nfb;
        this.prm = prm;
        this.acd = acd;
        this.dfc = dfc;
        this.funcCode = funcode;
    }

    public byte getAcd() {
        return this.acd;
    }

    public void setAcd(byte acd) {
        this.acd = acd;
    }

    public byte getDfc() {
        return this.dfc;
    }

    public void setDfc(byte dfc) {
        this.dfc = dfc;
    }

    public int decode(ByteBuf buffer) throws Exception {byte b = buffer.readByte();
        this.funcCode = ((byte) (b & 0xF));
        this.dfc = ((byte) (b >> 4 & 0x1));
        this.acd = ((byte) (b >> 5 & 0x1));
        this.prm = ((byte) (b >> 6 & 0x1));
        this.nfb = ((byte) (b >> 7 & 0x1));
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 1;
    }

    public int encode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        byte b = (byte) (this.funcCode | this.dfc << 4 | this.acd << 5 | this.prm << 6 | this.nfb << 7);
        buffer.writeByte(b);
        return 1;
    }

    @Override
    public String toExplain() {
        return "控制域{"
                +"nfb:"+this.nfb+","
                +"prm:"+this.prm+","
                +"acd:"+this.acd+","
                +"dfc:"+this.dfc+","
                +"功能码:"+this.funcCode+"}";
    }
}
