package com.bobandata.iot.zj102.frame.asdu.type;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataReqAsdu extends Asdu {
    private int startMark;
    private int endMark;
    private Date startDate;
    private Date endDate;
    private static int dataRequestAsdu = 12;

    public DataReqAsdu(AsduHead asduHead, int startMark, int endMark, Date startDate, Date endDate) {
        setAsduHead(asduHead);
        this.startMark = startMark;
        this.endMark = endMark;
        this.startDate = startDate;
        this.endDate = endDate;
        getAsduHead();
        setAsduLength(getAsduHead().getAsduHeadLength() + dataRequestAsdu);
    }

    public DataReqAsdu() {

        setAsduHead(new AsduHead());

        getAsduHead();
        setAsduLength(getAsduHead().getAsduHeadLength() + dataRequestAsdu);
    }

    public int encode(ByteBuf buffer) throws Exception {

        getAsduHead().encode(buffer);

        buffer.writeByte((byte) getStartMark());

        buffer.writeByte((byte) getEndMark());

        DateUtil.getBufferByDate(buffer, getStartDate());

        DateUtil.getBufferByDate(buffer, getEndDate());

        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));

        getAsduHead().decode(buffer);

        setStartMark(buffer.readByte());

        setEndMark(buffer.readByte());

        setStartDate(DateUtil.getDateByBuffer(buffer));

        setEndDate(DateUtil.getDateByBuffer(buffer));

        return 0;
    }

    public int getStartMark() {
        return this.startMark;
    }
    public void setStartMark(int startMark) {
        this.startMark = startMark;
    }
    public int getEndMark() {
        return this.endMark;
    }
    public void setEndMark(int endMark) {
        this.endMark = endMark;
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
        sb.append("数据体{开始地址:"+this.startMark+","
                +"结束地址:"+this.endMark+","
                +"开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.startDate)+","
                +"结束时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.endDate)
                +"}");
        return sb.toString();
    }
}
