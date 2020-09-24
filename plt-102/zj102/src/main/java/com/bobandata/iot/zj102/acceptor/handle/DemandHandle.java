package com.bobandata.iot.zj102.acceptor.handle;

import com.bobandata.iot.entity.his.HisDemand;
import com.bobandata.iot.zj102.frame.asdu.type.DataAsdu;
import com.bobandata.iot.zj102.frame.asdu.info.Info;
import com.bobandata.iot.zj102.frame.asdu.info.SingleGroupAndDateInfo;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 拓 on 2017/10/24.
 */
public class DemandHandle extends DataHandle{

    public DemandHandle(Channel channel, HandleParams params) {
        super(channel,params);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void responseData() {
        int startMark = (Integer) params.getParameter("startMark");
        int endMark = (Integer) params.getParameter("endMark");
        Date startDate = (Date) params.getParameter("startDate");
        Date endDate = (Date) params.getParameter("endDate");

        List<HisDemand> data= getDataList(startDate, endDate ,startMark ,endMark);
        if (data == null || data.size()==0) return;
        List<Info> infoFrames = new ArrayList<>();
        int i=0;
        for (HisDemand hisDemand:data) {
            SingleGroupAndDateInfo demandInfo = new SingleGroupAndDateInfo(i++, hisDemand.getPapValue().intValue(), hisDemand.getOccurTime(), 0);
            infoFrames.add(demandInfo);
            //正向无功
            demandInfo = new SingleGroupAndDateInfo(i++, hisDemand.getPapValue().intValue(), hisDemand.getOccurTime(), 0);
            infoFrames.add(demandInfo);
            //反向有功
            demandInfo = new SingleGroupAndDateInfo(i++, hisDemand.getPapValue().intValue(), hisDemand.getOccurTime(), 0);
            infoFrames.add(demandInfo);
            //反向无功
            demandInfo = new SingleGroupAndDateInfo(i++, hisDemand.getPapValue().intValue(), hisDemand.getOccurTime(), 0);
            infoFrames.add(demandInfo);
        }
        sendMsg(new DataAsdu(null,infoFrames,dateTime));
    }
}
