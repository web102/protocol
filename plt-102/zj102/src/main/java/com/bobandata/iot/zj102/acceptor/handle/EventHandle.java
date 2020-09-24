package com.bobandata.iot.zj102.acceptor.handle;

import com.bobandata.iot.entity.his.HisEvent;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.type.ListAsdu;
import com.bobandata.iot.zj102.frame.asdu.info.EventInfo;
import com.bobandata.iot.zj102.frame.asdu.info.Info;
import com.bobandata.iot.zj102.util.Ti;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/11/29 13:38
 * @Description:
 */

public class EventHandle extends DataHandle {
    public EventHandle(Channel channel, HandleParams params) {
        super(channel, params);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void responseData() {
        AsduHead asduHead = (AsduHead) params.getParameter("asduHead");
        List<HisEvent> data = null;
        //如果有缓存说明上次已经读完,这次直接返回结束针

        switch (asduHead.getTi()){
            case Ti.singleInfo:
                Date startDate = (Date) params.getParameter("startDate");
                Date endDate = (Date) params.getParameter("endDate");
                data = getDataList(startDate, endDate,0,0);
                break;
            case Ti.timeLimitSingleInfo:
                data = getDataList(null,null,0,0);
                break;
        }

        if (data == null || data.size() == 0) {
            return;
        }

        List<Info> infoFrames = new ArrayList<>();
        for (HisEvent event : data) {
            EventInfo energyInfo = new EventInfo(event.getEventType(), event.getMeterId().intValue(), event.getEventFlag(), event.getOccurTime());
            infoFrames.add(energyInfo);
        }

        sendMsg(new ListAsdu(null,infoFrames));
    }
}