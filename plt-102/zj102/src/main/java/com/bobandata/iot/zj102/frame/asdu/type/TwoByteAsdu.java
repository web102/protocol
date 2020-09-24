package com.bobandata.iot.zj102.frame.asdu.type;

import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/11/15 18:00
 * @Description:
 */

public class TwoByteAsdu extends Asdu {
    private int dateValue;

    public TwoByteAsdu() {
    }

    public TwoByteAsdu(AsduHead asduHead, int dateValue) {
            setAsduHead(asduHead);
            this.dateValue = dateValue;
            getAsduHead();
            setAsduLength(getAsduHead().getAsduHeadLength() + 2);

    }

    public int encode(ByteBuf buffer) throws Exception {
        getAsduHead().encode(buffer);
        encodeValue(buffer, dateValue);
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        AsduHead asduHead = new AsduHead();
        asduHead.decode(buffer);
        setAsduHead(asduHead);
        byte[] valueBytes = new byte[2];
        buffer.readBytes(valueBytes);
        setDateValue(Integer.parseInt(HexUtils.encodeHexStr(valueBytes, 2), 16));
        return 0;
    }

    public static byte[] encodeValue(ByteBuf buffer, int value) {
        String hexString = Integer.toHexString(value);

        switch (4 - hexString.length()) {
            case 0:
                hexString = hexString;
                break;
            case 1:
                hexString = "0" + hexString;
                break;
            case 2:
                hexString = "00" + hexString;
                break;
            case 3:
                hexString = "000" + hexString;
                break;
            case 4:
                hexString = "0000" + hexString;
                break;
        }

        byte[] valueByte = HexUtils.decodeHex(hexString.toCharArray());
        buffer.writeByte(valueByte[1]);
        buffer.writeByte(valueByte[0]);
        return valueByte;
    }

    public int getDateValue() {
        return dateValue;
    }

    public void setDateValue(int dateValue) {
        this.dateValue = dateValue;
    }

    @Override
    public String toExplain() {
        StringBuffer sb = new StringBuffer();
        sb.append(getAsduHead().toExplain()+"\n");
        sb.append("数据体{");
        sb.append("时间阀:"+dateValue);
        sb.append("}");
        return sb.toString();
    }
}