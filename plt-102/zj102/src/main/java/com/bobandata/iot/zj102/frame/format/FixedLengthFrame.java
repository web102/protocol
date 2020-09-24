package com.bobandata.iot.zj102.frame.format;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain;
import com.bobandata.iot.zj102.frame.util.FixedLengthCheckSum;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FixedLengthFrame implements IFrame {
    private static final Logger logger = LoggerFactory.getLogger(FixedLengthFrame.class);
    private ByteBuf buffer;
    private String hexDump;
    private byte startByte = (byte) 0x10;
    private ControlDomain controlDomain;
    private LinkAddress linkAddress;
    private FixedLengthCheckSum fixedLengthCheckSum;
    private byte endByte = (byte) 0x16;

    public FixedLengthFrame(ByteBuf buffer, ControlDomain controlDomain) {
        this.buffer = buffer;

        this.hexDump = ByteBufUtil.hexDump(this.buffer);
        this.controlDomain = controlDomain;
        this.linkAddress = new LinkAddress();
        this.fixedLengthCheckSum = new FixedLengthCheckSum();
        try {
            this.decode();
        } catch (Exception e) {
            logger.error("E008-固定帧长解码码异常！");
        }
    }

    public FixedLengthFrame(ControlDomain controlDomain, LinkAddress linkAddress) {
        this.buffer = Unpooled.buffer(6);
        this.controlDomain = controlDomain;
        this.linkAddress = linkAddress;
        this.fixedLengthCheckSum = new FixedLengthCheckSum();
        try {
            this.encode();
        } catch (Exception e) {
            logger.error("E007-固定帧长编码异常！");
        }
    }

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }
    public String toExplain() {
        String start = "开始字符：10";
        String controlDomain = this.controlDomain.toExplain();
        String linkAddress = this.linkAddress.toExplain();
        String checkSum = this.fixedLengthCheckSum.toExplain();
        String end = "结束符:16";
        return toHexString()+" \n"+ start +","+ controlDomain +","+ linkAddress +","+ checkSum +","+ end;
    }

    public int encode() throws Exception {
        this.buffer.writeByte(this.startByte);
        this.controlDomain.encode(this.buffer);
        this.linkAddress.encode(this.buffer);
        this.fixedLengthCheckSum.encode(this.buffer);
        this.buffer.writeByte(this.endByte);
        this.hexDump = ByteBufUtil.hexDump(this.buffer);
        return 0;
    }

    public int decode() throws Exception {
        this.buffer.readByte();

        this.controlDomain.decode(this.buffer);

        this.linkAddress.decode(this.buffer);

        this.fixedLengthCheckSum.decode(this.buffer);

        this.buffer.readByte();
        return 0;
    }

    public ByteBuf getBuffer() {
        return this.buffer;
    }

    public byte getStartByte() {
        return this.startByte;
    }

    public void setStartByte(byte startByte) {
        this.startByte = startByte;
    }

    public ControlDomain getControlDomain() {
        return this.controlDomain;
    }

    public void setControlDomain(ControlDomain controlDomain) {
        this.controlDomain = controlDomain;
    }

    public LinkAddress getLinkAddress() {
        return this.linkAddress;
    }

    public void setLinkAddress(LinkAddress linkAddress) {
        this.linkAddress = linkAddress;
    }

    public FixedLengthCheckSum getFixedLengthCheckSum() {
        return this.fixedLengthCheckSum;
    }

    public void setFixedLengthCheckSum(FixedLengthCheckSum fixedLengthCheckSum) {
        this.fixedLengthCheckSum = fixedLengthCheckSum;
    }

    public byte getEndByte() {
        return this.endByte;
    }

    public void setEndByte(byte endByte) {
        this.endByte = endByte;
    }
}
