package com.bobandata.iot.zj102.frame.format;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.connector.MasterProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 单字节数据帧
 * @Param:
 * @Throws:
 * @Return:
 * @Author:liutuo
 * @Date:15:19 2018/8/17
 */
public class SingleByteFrame implements IFrame {
    private static final Logger logger = LoggerFactory.getLogger(MasterProtocol.class);
    private ByteBuf buffer;
    private String hexDump;
    private byte singleByte;

    public SingleByteFrame(ByteBuf buffer) {
        this.buffer = Unpooled.buffer(1);

        buffer.readBytes(this.buffer, 1);

        this.hexDump = ByteBufUtil.hexDump(this.buffer);
        try {
            this.decode();
        } catch (Exception e) {
            logger.error("E010-单字节帧解码异常！");
        }
    }

    public SingleByteFrame() {
        this.buffer = Unpooled.buffer(1);
        this.singleByte = -27;
        try {
            this.encode();
        } catch (Exception e) {
            logger.error("E009-单字节帧编码异常！");
        }
    }

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }

    public String toExplain() {
        return toHexString();
    }

    public int encode() throws Exception {
        this.buffer.writeByte(this.singleByte);
        this.hexDump = ByteBufUtil.hexDump(this.buffer);
        return 0;
    }

    public int decode() throws Exception {
        this.singleByte = this.buffer.getByte(0);
        return 0;
    }

    public ByteBuf getBuffer() {
        return this.buffer;
    }

    public byte getSingleByte() {
        return this.singleByte;
    }

    public void setSingleByte(byte singleByte) {
        this.singleByte = singleByte;
    }
}
