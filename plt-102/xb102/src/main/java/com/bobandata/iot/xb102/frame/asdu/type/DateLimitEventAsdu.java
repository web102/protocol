package com.bobandata.iot.xb102.frame.asdu.type;

import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.Asdu;
import com.bobandata.iot.xb102.frame.asdu.info.EventInfo;
import com.bobandata.iot.xb102.frame.asdu.info.Info;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 拓 on 2017/10/20.
 * 检测端的事件类型的ADSU,编解码
 */
public class DateLimitEventAsdu extends Asdu {

    //数据体集合
    private List<Info> infoFrames;

    public DateLimitEventAsdu() {
		// TODO Auto-generated constructor stub
	}
    
    public DateLimitEventAsdu(AsduHead asduHead, List<Info> infoFrames){
    	int infosLength = 0;
    	if(!infoFrames.isEmpty()){
    		infosLength = (infoFrames.get(0).getInfoLength())*infoFrames.size();
    	}
        this.setAsduHead(asduHead);
        this.infoFrames = infoFrames;
        this.setAsduLength(this.getAsduHead().getAsduHeadLength()+infosLength);
    }

    @Override
    public int encode(ByteBuf ioBuffer) throws Exception {
        this.getAsduHead().encode(ioBuffer);
        for (Info info:infoFrames) {
            info.encode(ioBuffer);
        }
        return 0;
    }

    @Override
    public int decode(ByteBuf ioBuffer) throws Exception {
    	AsduHead asduHead = new AsduHead();
    	asduHead.decode(ioBuffer);
    	this.setAsduHead(asduHead);
    	infoFrames = new ArrayList<Info>();
    	for(int i = 0; i< asduHead.getVsq(); i++){
    		EventInfo eventInfo = new EventInfo();
    		eventInfo.decode(ioBuffer);
    		infoFrames.add(eventInfo);
    	}
        return 0;
    }

    @Override
    public String toHexString() {
        return null;
    }

    @Override
    public String toExplain() {
        return null;
    }

    public List<Info> getInfoFrames() {
        return infoFrames;
    }

    public void setInfoFrames(List<Info> infoFrames) {
        this.infoFrames = infoFrames;
    }

}
