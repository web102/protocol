package com.bobandata.iot.zj102.frame.asdu.type;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:获取终端时间
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 13:48 2018/8/29.
 */
public class LongTimeAsdu extends Asdu {
    private Date dataTime;
    private static int dateTimeLength = 7;

    public LongTimeAsdu() {
    }
    public LongTimeAsdu(AsduHead asduHead, Date dataTime) {
        setAsduHead(asduHead);
        this.dataTime = dataTime;
        getAsduHead();
        setAsduLength(getAsduHead().getAsduHeadLength() + dateTimeLength);
    }

    public int encode(ByteBuf buffer) throws Exception {
        getAsduHead().encode(buffer);
        DateUtil.getBufferByTimestamp(buffer, getDataTime());
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        AsduHead asduHead = new AsduHead();
        asduHead.decode(buffer);
        setAsduHead(asduHead);
        setDataTime(DateUtil.getTimestampByBuffer(buffer));
        return 0;
    }
    public Date getDataTime() {
        return this.dataTime;
    }
    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    @Override
    public String toExplain() {
        StringBuffer sb = new StringBuffer();
        sb.append(getAsduHead().toExplain()).append("\n");
        sb.append("数据体{时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(this.dataTime)
                +"}");
        return sb.toString();
    }
}
