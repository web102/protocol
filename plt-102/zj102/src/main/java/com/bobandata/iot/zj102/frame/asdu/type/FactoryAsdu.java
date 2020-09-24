package com.bobandata.iot.zj102.frame.asdu.type;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:制造厂和产品规范
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:27 2018/8/29.
 */

@Data
public class FactoryAsdu extends Asdu {
    private Date standardDate;
    private  int manufacturer;
    private  String product;
    private static int standardDateLength = 1;
    private static int manufacturerLength = 1;
    private static int productLength = 4;

    public FactoryAsdu() {
    }

    public FactoryAsdu(AsduHead asduHead, Date standardDate , int manufacturer, String product) {
        setAsduHead(asduHead);
        this.standardDate = standardDate;
        this.manufacturer = manufacturer;
        this.product = product;
        getAsduHead();
        setAsduLength(getAsduHead().getAsduHeadLength() +  standardDateLength + manufacturerLength + productLength);
    }

    public int encode(ByteBuf buffer) throws Exception {
        getAsduHead().encode(buffer);
        DateUtil.getBufferByStandardDate(buffer, getStandardDate());
        buffer.writeByte((byte) getManufacturer());
        buffer.writeBytes(getProduct().getBytes());
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        AsduHead asduHead = new AsduHead();
        asduHead.decode(buffer);
        setAsduHead(asduHead);
        setStandardDate(DateUtil.getStandardDateByBuffer(buffer));
        setManufacturer(buffer.readByte());
        byte[] valueBytes = new byte[4];
        buffer.readBytes(valueBytes);
        setProduct(new String(valueBytes));
        return 0;
    }

    @Override
    public String toExplain() {
        StringBuffer sb = new StringBuffer();
        sb.append(getAsduHead().toExplain()+"\n");
        sb.append("数据体{时间:"+new SimpleDateFormat("yyyy年MM月").format(this.standardDate)+","
                +"制造厂编码:"+this.manufacturer+","
                +"产品编码:"+this.product
                +"}");
        return sb.toString();
    }

}
