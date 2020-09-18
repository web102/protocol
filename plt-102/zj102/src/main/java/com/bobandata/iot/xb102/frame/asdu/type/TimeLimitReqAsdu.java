package com.bobandata.iot.xb102.frame.asdu.type;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.Asdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:单点信息
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 14:34 2018/8/30.
 */
public class TimeLimitReqAsdu extends Asdu {
    private Date startDate;
    private Date endDate;
    private static int dataRequestAsdu = 10;

    public TimeLimitReqAsdu(AsduHead asduHead, Date startDate, Date endDate) {
        setAsduHead(asduHead);
        this.startDate = startDate;
        this.endDate = endDate;
        getAsduHead();
        setAsduLength(AsduHead.getAsduHeadLength() + dataRequestAsdu);
    }

    public TimeLimitReqAsdu() {
        setAsduHead(new AsduHead());
        getAsduHead();
        setAsduLength(AsduHead.getAsduHeadLength() + dataRequestAsdu);
    }

    public int encode(ByteBuf buffer) throws Exception {

        getAsduHead().encode(buffer);

        DateUtil.getBufferByDate(buffer, getStartDate());

        DateUtil.getBufferByDate(buffer, getEndDate());

        setHexDump(ByteBufUtil.hexDump(buffer));

        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));

        getAsduHead().decode(buffer);

        setStartDate(DateUtil.getDateByBuffer(buffer));

        setEndDate(DateUtil.getDateByBuffer(buffer));

        return 0;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toExplain() {
        StringBuffer sb = new StringBuffer();
        sb.append(getAsduHead().toExplain()+"\n");
        sb.append("数据体{ 开始时间:"+this.startDate+","
                +"结束时间:"+this.endDate
                +"}");
        return sb.toString();
    }
}
