package com.bobandata.iot.zj102.frame.asdu.type;

import com.bobandata.iot.transport.util.DateUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import com.bobandata.iot.zj102.frame.asdu.info.FiveGroupInfo;
import com.bobandata.iot.zj102.frame.asdu.info.Info;
import com.bobandata.iot.zj102.frame.asdu.info.SingleGroupAndDateInfo;
import com.bobandata.iot.zj102.frame.asdu.info.SingleGroupInfo;
import com.bobandata.iot.zj102.util.Ti;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataAsdu extends Asdu {
    private List<Info> infoFrames;
    private Date dataTime;
    private static int dateTimeLength = 5;

    public DataAsdu() {
    }

    public DataAsdu(AsduHead asduHead, List<Info> infoFrames, Date dataTime) {
        if (!infoFrames.isEmpty()) {
            int infosLength = ((Info) infoFrames.get(0)).getInfoLength() * infoFrames.size();
            setAsduHead(asduHead);
            this.infoFrames = infoFrames;
            this.dataTime = dataTime;
            getAsduHead();
            setAsduLength(getAsduHead().getAsduHeadLength() + infosLength + dateTimeLength);
        } else {
            int infosLength = 0;
            setAsduHead(asduHead);
            this.dataTime = dataTime;
            getAsduHead();
            setAsduLength(getAsduHead().getAsduHeadLength() + infosLength + dateTimeLength);
        }
    }

    public int encode(ByteBuf buffer) throws Exception {
        getAsduHead().encode(buffer);
        if (this.infoFrames != null) {
            for (Info info : this.infoFrames) {
                info.encode(buffer);
            }
        }
        DateUtil.getBufferByDate(buffer, this.dataTime);
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        AsduHead asduHead = new AsduHead();
        asduHead.decode(buffer);
        setAsduHead(asduHead);
        this.infoFrames = new ArrayList();

        int responseTi = asduHead.getTi();
        switch (Ti.findByResponse(responseTi)) {
            case VIEW:
            case DAY_VIEW:
            case MONTH_VIEW:

            case FIRST_VIEW     :
            case FIRST_ADDR_VIEW:
            case LIMIT_VIEW     :
            case LIMIT_ADDR_VIEW:

            case CYCLEVIEW:
            case FIRST_CYCLEVIEW     :
            case FIRST_ADDR_CYCLEVIEW:
            case LIMIT_CYCLEVIEW     :
            case LIMIT_ADDR_CYCLEVIEW:

            case BILLVIEW:
            case FIRST_BILLVIEW     :
            case FIRST_ADDR_BILLVIEW:
            case LIMIT_BILLVIEW     :
            case LIMIT_ADDR_BILLVIEW:

            case CYCLEBILLVIEW:
            case FIRST_CYCLEBILLVIEW:
            case FIRST_ADDR_CYCLEBILLVIEW:
            case LIMIT_CYCLEBILLVIEW     :
            case LIMIT_ADDR_CYCLEBILLVIEW:

            case RPVIEW:
            case DAY_RPVIEW:
            case MONTH_RPVIEW:
                for (int i = 0; i < asduHead.getVsq(); i++) {
                    SingleGroupInfo energyInfo = new SingleGroupInfo();
                    energyInfo.decode(buffer);
                    this.infoFrames.add(energyInfo);
                }
                break;
            case TARIFF:
            case DAY_TARIFF:
            case MONTH_TARIFF:
                for (int i = 0; i < asduHead.getVsq(); i++) {
                    FiveGroupInfo FiveGroupInfo = new FiveGroupInfo();
                    FiveGroupInfo.decode(buffer);
                    this.infoFrames.add(FiveGroupInfo);
                }
                break;

            case DAY_DEMAND:
            case MONTH_DEMAND:
                for (int i = 0; i < asduHead.getVsq(); i++) {
                    SingleGroupAndDateInfo singleGroupAndDateInfo = new SingleGroupAndDateInfo();
                    singleGroupAndDateInfo.decode(buffer);
                    this.infoFrames.add(singleGroupAndDateInfo);
                }
                break;
        }

        setDataTime(DateUtil.getDateByBuffer(buffer));
        return 0;
    }

    public static byte[] encodeValue(ByteBuf buffer, long value) {
        String hexString = Long.toHexString(value);

        switch (8 - hexString.length()) {
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
            case 5:
                hexString = "00000" + hexString;
                break;
            case 6:
                hexString = "000000" + hexString;
                break;
            case 7:
                hexString = "0000000" + hexString;
                break;
            case 8:
                hexString = "00000000" + hexString;
        }

        byte[] valueByte = HexUtils.decodeHex(hexString.toCharArray());
        buffer.writeByte(valueByte[3]);
        buffer.writeByte(valueByte[2]);
        buffer.writeByte(valueByte[1]);
        buffer.writeByte(valueByte[0]);
        return valueByte;
    }

    public List<Info> getInfoFrames() {
        return this.infoFrames;
    }
    public void setInfoFrames(List<Info> infoFrames) {
        this.infoFrames = infoFrames;
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
        sb.append(getAsduHead().toExplain()+"\n");
        sb.append("数据体{");
        for (Info info:infoFrames){
            sb.append(info.toExplain());
        }
        sb.append("\n时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.dataTime));
        sb.append("}");
        return sb.toString();
    }
}