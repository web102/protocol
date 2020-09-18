package com.bobandata.iot.xb102.acceptor.handle;

import com.bobandata.iot.entity.his.HisView;
import com.bobandata.iot.xb102.frame.asdu.type.DataAsdu;
import com.bobandata.iot.xb102.frame.asdu.info.Info;
import com.bobandata.iot.xb102.frame.asdu.info.SingleGroupInfo;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 拓 on 2017/10/24.
 */
public class ViewHandle extends DataHandle {
    private static Log log = LogFactory.getLog(ViewHandle.class);

    public ViewHandle(Channel channel, HandleParams params) {
        super(channel,params);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void responseData() {
        int startMark = (Integer) params.getParameter("startMark");
        int endMark = (Integer) params.getParameter("endMark");
        Date startDate = (Date) params.getParameter("startDate");
        Date endDate = (Date) params.getParameter("endDate");

        List<HisView> data = getDataList(startDate, endDate ,startMark ,endMark);
        if (data == null || data.size()==0) return;
        List<Info> infoFrames = new ArrayList<>();
        int i = 0;
        for (HisView hisView:data) {
            SingleGroupInfo energyInfo = new SingleGroupInfo(i++, hisView.getPapValue().intValue(), 0);
            infoFrames.add(energyInfo);
            //正向无功
            energyInfo = new SingleGroupInfo(i++, hisView.getPapValue().intValue(), 0);
            infoFrames.add(energyInfo);
            //反向有功
            energyInfo = new SingleGroupInfo(i++, hisView.getPapValue().intValue(), 0);
            infoFrames.add(energyInfo);
            //反向无功
            energyInfo = new SingleGroupInfo(i++, hisView.getPapValue().intValue(), 0);
            infoFrames.add(energyInfo);
        }
        sendMsg(new DataAsdu(null,infoFrames,dateTime));
    }
}
