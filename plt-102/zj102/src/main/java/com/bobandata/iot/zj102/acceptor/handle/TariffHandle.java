package com.bobandata.iot.zj102.acceptor.handle;

import com.bobandata.iot.entity.his.HisTariff;
import com.bobandata.iot.zj102.frame.asdu.type.DataAsdu;
import com.bobandata.iot.zj102.frame.asdu.info.FiveGroupInfo;
import com.bobandata.iot.zj102.frame.asdu.info.Info;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 拓 on 2017/10/24.
 */
public class TariffHandle extends DataHandle {

    public TariffHandle(Channel channel, HandleParams params) {
        super(channel, params);
    }

	@SuppressWarnings("unchecked")
	@Override
	public void responseData() {
		int startMark = (Integer) params.getParameter("startMark");
		int endMark = (Integer) params.getParameter("endMark");
		Date startDate = (Date) params.getParameter("startDate");
		Date endDate = (Date) params.getParameter("endDate");
		List<HisTariff> data = getDataList(startDate, endDate ,startMark ,endMark);
		if (data == null || data.size()==0) return;
		List<Info> infoFrames = new ArrayList<>();
		int i=0;
		for(HisTariff hisTariff:data){
			//正向有功总尖峰平谷
			int pap1Value = 0;
			int pap2Value = 0;
			int pap3Value = 0;
			int pap4Value = 0;
			int pap5Value = 0;
			if(hisTariff.getPap1Value() != null){
				pap1Value = hisTariff.getPap1Value().intValue();
			}
			if(hisTariff.getPap2Value() != null){
				pap2Value = hisTariff.getPap2Value().intValue();
			}
			if(hisTariff.getPap3Value() != null){
				pap3Value = hisTariff.getPap3Value().intValue();
			}
			if(hisTariff.getPap4Value() != null){
				pap4Value = hisTariff.getPap4Value().intValue();
			}
			if(hisTariff.getPap5Value() != null){
				pap5Value = hisTariff.getPap5Value().intValue();
			}
			FiveGroupInfo tariffInfo = new FiveGroupInfo(i++, pap1Value, pap2Value, pap3Value, pap4Value, pap5Value, 0);
			infoFrames.add(tariffInfo);

			//正向无功总尖峰平谷
			int prp1Value = 0;
			int prp2Value = 0;
			int prp3Value = 0;
			int prp4Value = 0;
			int prp5Value = 0;
			if(hisTariff.getPrp1Value() != null){
				prp1Value = hisTariff.getPrp1Value().intValue();
			}
			if(hisTariff.getPrp2Value() != null){
				prp2Value = hisTariff.getPrp2Value().intValue();
			}
			if(hisTariff.getPrp3Value() != null){
				prp3Value = hisTariff.getPrp3Value().intValue();
			}
			if(hisTariff.getPrp4Value() != null){
				prp4Value = hisTariff.getPrp4Value().intValue();
			}
			if(hisTariff.getPrp5Value() != null){
				prp5Value = hisTariff.getPrp5Value().intValue();
			}
			tariffInfo = new FiveGroupInfo(i++, prp1Value, prp2Value, prp3Value, prp4Value, prp5Value, 0);
			infoFrames.add(tariffInfo);

			//反向有功总尖峰平谷
			int rap1Value = 0;
			int rap2Value = 0;
			int rap3Value = 0;
			int rap4Value = 0;
			int rap5Value = 0;
			if(hisTariff.getRap1Value() != null){
				rap1Value = hisTariff.getRap1Value().intValue();
			}
			if(hisTariff.getRap2Value() != null){
				rap2Value = hisTariff.getRap2Value().intValue();
			}
			if(hisTariff.getRap3Value() != null){
				rap3Value = hisTariff.getRap3Value().intValue();
			}
			if(hisTariff.getRap4Value() != null){
				rap4Value = hisTariff.getRap4Value().intValue();
			}
			if(hisTariff.getRap5Value() != null){
				rap5Value = hisTariff.getRap5Value().intValue();
			}
			tariffInfo = new FiveGroupInfo(i++, rap1Value, rap2Value, rap3Value, rap4Value, rap5Value, 0);
			infoFrames.add(tariffInfo);
			//反向无功总尖峰平谷
			int rrp1Value = 0;
			int rrp2Value = 0;
			int rrp3Value = 0;
			int rrp4Value = 0;
			int rrp5Value = 0;
			if(hisTariff.getRrp1Value() != null){
				rrp1Value = hisTariff.getRrp1Value().intValue();
			}
			if(hisTariff.getRrp2Value() != null){
				rrp2Value = hisTariff.getRrp2Value().intValue();
			}
			if(hisTariff.getRrp3Value() != null){
				rrp3Value = hisTariff.getRrp3Value().intValue();
			}
			if(hisTariff.getRrp4Value() != null){
				rrp4Value = hisTariff.getRrp4Value().intValue();
			}
			if(hisTariff.getRrp5Value() != null){
				rrp5Value = hisTariff.getRrp5Value().intValue();
			}
			tariffInfo = new FiveGroupInfo(i++, rrp1Value, rrp2Value, rrp3Value, rrp4Value, rrp5Value, 0);
			infoFrames.add(tariffInfo);
		}
		sendMsg(new DataAsdu(null,infoFrames,dateTime));
	}
}