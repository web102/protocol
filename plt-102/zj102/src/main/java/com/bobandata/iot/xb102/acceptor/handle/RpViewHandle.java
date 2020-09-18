package com.bobandata.iot.xb102.acceptor.handle;

import com.bobandata.iot.entity.his.HisRpView;
import com.bobandata.iot.xb102.frame.asdu.type.DataAsdu;
import com.bobandata.iot.xb102.frame.asdu.info.Info;
import com.bobandata.iot.xb102.frame.asdu.info.SingleGroupInfo;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/11/28 15:37
 * @Description:
 */

public class RpViewHandle extends DataHandle{
    public RpViewHandle(Channel channel, HandleParams params) {
        super(channel,params);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void responseData() {
        int startMark = (Integer) params.getParameter("startMark");
        int endMark = (Integer) params.getParameter("endMark");
        Date startDate = (Date) params.getParameter("startDate");
        Date endDate = (Date) params.getParameter("endDate");
        List<HisRpView> data = getDataList(startDate,endDate,startMark,endMark);
        if (data == null || data.size()==0) return;
        List<Info> infoFrames = new ArrayList<>();
        int i=0;
        for (HisRpView hisView : data) {
            SingleGroupInfo energyInfo = new SingleGroupInfo(i++, hisView.getRp1Value().intValue(), 0);
            infoFrames.add(energyInfo);
            //正向无功
            energyInfo = new SingleGroupInfo(i++, hisView.getRp2Value().intValue(), 0);
            infoFrames.add(energyInfo);
            //反向有功
            energyInfo = new SingleGroupInfo(i++, hisView.getRp3Value().intValue(), 0);
            infoFrames.add(energyInfo);
            //反向无功
            energyInfo = new SingleGroupInfo(i++, hisView.getRp4Value().intValue(), 0);
            infoFrames.add(energyInfo);
        }
        sendMsg(new DataAsdu(null,infoFrames,dateTime));
    }
}