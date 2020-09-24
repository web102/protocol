package com.bobandata.iot.zj102.acceptor.handle;

import com.bobandata.iot.entity.his.*;
import com.bobandata.iot.zj102.acceptor.SlaveProtocol;
import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.zj102.frame.format.VariableLengthFrame;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import io.netty.channel.Channel;
import com.bobandata.iot.util.HttpDataManager;
import com.bobandata.iot.zj102.util.Ti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2019/1/28 16:51
 * @Description:
 */

public abstract class DataHandle extends Iec102Handle{
    private static final Logger logger = LoggerFactory.getLogger(DataHandle.class);
    private int vsq=4;       //可变结构
    Timestamp dateTime;          //末尾的时间B

    public DataHandle(Channel channel, HandleParams params){
        super(channel,params);
    }

    public List getDataList(Date startDate, Date endDate, long startMark, long endMark) {
        AsduHead asduHead = (AsduHead) params.getParameter("asduHead");
        //记录数据读取的位置
        List<? extends IData> allDate = new ArrayList<>();    //总数据条数
        List<Object> sendDate = new ArrayList<>();

        if(channel.attr(hisAcqs).get() == null) {
            switch (asduHead.getTi()) {
                case Ti.monthView:
                case Ti.billView:
                case Ti.cycleBillView:
                case Ti.dayView:
                case Ti.cycleView:
                case Ti.view:
                    allDate = HttpDataManager.getData(HisView.class, startDate, endDate, startMark, endMark);
                    break;
                case Ti.rpView:
                case Ti.dayRpView:
                case Ti.monthRpView:
                    allDate = HttpDataManager.getData(HisRpView.class, startDate, endDate, startMark, endMark);
                    break;
                case Ti.tariff:
                case Ti.dayTariff:
                case Ti.monthTariff:
                    allDate = HttpDataManager.getData(HisTariff.class, startDate, endDate, startMark, endMark);
                    break;
                case Ti.dayDemand:
                case Ti.monthDemand:
                    allDate = HttpDataManager.getData(HisDemand.class, startDate, endDate, startMark, endMark);
                    break;
                case Ti.singleInfo:
                    allDate = HttpDataManager.getData(HisEvent.class);
                    break;
                case Ti.timeLimitSingleInfo:
                    allDate = HttpDataManager.getData(HisEvent.class,startDate,endDate);
                    break;
                default:
                    logger.error("---------------Not Found Type----------------");
                    break;
            }
            //如果数据为空，则返回无数据帧
            if(allDate.size()==0){
                specialHandle((byte)0x0D);
                return null;
            }
        }else {
            allDate = channel.attr(hisAcqs).get();
            //如果数据为空，则返回完毕帧
            if(allDate.size()==0){
                specialHandle((byte)0x0A);
                return null;
            }
        }

        dateTime = allDate.get(0).getOccurTime();
        //如果时间差在一分钟内的在同一条帧里面
        for (IData data : allDate) {
            long l =Math.abs(dateTime.getTime() - data.getOccurTime().getTime());
            if (l<1000*60) {
                sendDate.add(data);
            }
        }
        allDate.removeAll(sendDate);
        this.vsq = sendDate.size()*this.vsq;

        channel.attr(fcbkey).set((byte) params.getParameter("fcb"));
        channel.attr(hisAcqs).set(allDate);
        return sendDate;
    }

    public void sendMsg(Asdu asdu){
        AsduHead asduHead = (AsduHead) params.getParameter("asduHead");
        ControlDomain_C controlDomain_C = new ControlDomain_C((byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 8);
        LinkAddress linkAddress = (LinkAddress) params.getParameter("linkAddress");
        AsduHead asHead = new AsduHead();
        asHead.setTi(Ti.compareType(asduHead.getTi()));
        asHead.setVsq((byte)vsq);
        asHead.setCot((byte)5);
        asHead.setPad(asduHead.getPad());
        asHead.setRad(asduHead.getRad());

        asdu.setAsduHead(asHead);
        VariableLengthFrame response = new VariableLengthFrame(controlDomain_C, linkAddress, asdu);
        channel.attr(responsekey).set(response);
        SlaveProtocol.returnMsg(channel,response);
    }
}