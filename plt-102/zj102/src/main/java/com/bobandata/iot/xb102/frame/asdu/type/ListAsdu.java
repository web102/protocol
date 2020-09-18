package com.bobandata.iot.xb102.frame.asdu.type;

import com.bobandata.iot.xb102.frame.asdu.Asdu;
import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.info.Info;
import com.bobandata.iot.xb102.frame.asdu.info.SingleByteAndDateNoInfo;
import com.bobandata.iot.xb102.util.Ti;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/11/14 18:08
 * @Description:
 */

public class ListAsdu extends Asdu {
    private List<Info> infoFrames;

    public ListAsdu() {
    }

    public ListAsdu(AsduHead asduHead, List<Info> infoFrames) {
        if (!infoFrames.isEmpty()) {
            int infosLength = ((Info) infoFrames.get(0)).getInfoLength() * infoFrames.size();
            setAsduHead(asduHead);
            this.infoFrames = infoFrames;
            getAsduHead();
            setAsduLength(AsduHead.getAsduHeadLength() + infosLength);
        } else {
            int infosLength = 0;
            setAsduHead(asduHead);
            getAsduHead();
            setAsduLength(AsduHead.getAsduHeadLength() + infosLength );
        }
    }

    public int encode(ByteBuf buffer) throws Exception {
        getAsduHead().encode(buffer);
        if (this.infoFrames != null) {
            for (Info info : this.infoFrames) {
                info.encode(buffer);
            }
        }
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        AsduHead asduHead = new AsduHead();
        asduHead.decode(buffer);
        setAsduHead(asduHead);
        this.infoFrames = new ArrayList();
        switch (asduHead.getTi()) {
            case Ti.returnSingleInfo:
                for (int i = 0; i < asduHead.getVsq(); i++) {
                    SingleByteAndDateNoInfo energyInfo = new SingleByteAndDateNoInfo();
                    energyInfo.decode(buffer);
                    this.infoFrames.add(energyInfo);
                }
                break;
        }
        return 0;
    }

    public List<Info> getInfoFrames() {
        return this.infoFrames;
    }
    public void setInfoFrames(List<Info> infoFrames) {
        this.infoFrames = infoFrames;
    }

    @Override
    public String toExplain() {
        StringBuffer sb = new StringBuffer();
        sb.append(getAsduHead().toExplain()+"\n");
        sb.append("数据体{");
        for (Info info:infoFrames){
            sb.append(info.toExplain());
        }
        sb.append("}");
        return sb.toString();
    }
}