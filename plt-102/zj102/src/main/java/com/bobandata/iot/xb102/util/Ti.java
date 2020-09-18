package com.bobandata.iot.xb102.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ti {
    private static final Logger logger = LoggerFactory.getLogger(Ti.class);
    //从站发给主站
    //基本（1-13）
    public static final int returnSingleInfo = 1;                    //带时标的单点信息
    public static final int returnBillView = 2;                      //记账(计费)电能累计量，每个量为四个八位位组
    public static final int returnCycleBillView = 5;                 //周期复位记账(计费)电能累计量，每个量为四个八位位组
    public static final int returnView = 8;                          //运行电能累计量，每个量为四个八位位组
    public static final int returnCycleView = 11;                     //周期复位运行电能累计量，每个量为四个八位位组
    //基本（70-72）
    public static final int returnInitEnd = 70;                      //初始化结束
    public static final int returnFactory = 71;                      //电能累计量数据终端设备的制造厂和产品规范
    public static final int returnTerminalTime = 72;                 //电能累计量数据终端设备的当前系统时间
    //新加
    public static final int returnRpView = 16;                       //四象限无功电能量，每个量为四个八位位组
    public static final int returnTariff = 17;                       //费率电能量, 顺序为尖、峰、平、谷，每个量为四个八位位组
    public static final int returnDayView = 18;                      //
    public static final int returnMonthView = 19;                    //
    public static final int returnDayRpView = 20;                    //
    public static final int returnMonthRpView = 21;                  //
    public static final int returnDayTariff = 22;                    //
    public static final int returnMonthTariff = 23;                  //
    public static final int returnDayDemand = 24;                    //
    public static final int returnMonthDemand = 26;                  //
    public static final int returnDeviation = 73;                    //
    public static final int returnTimeAsyn = 128;
    public static final int returnSetDeviation = 151;

    //主站发给从站
    //基本（100-123）
    public static final int factory = 100;              //读制造厂和产品规范
    public static final int singleInfo = 101;           //读带最新单点信息的记录
    public static final int timeLimitSingleInfo = 102;  //读选定时间范围内单点信息的记录
    public static final int getTerminalTime = 103;      //读电能累计量数据终端设备的当前系统时间
    public static final int billView = 120;             //读选定时间、地址范围内记账(计费)电能累计量
    public static final int cycleBillView = 121;        //读选定时间、地址范围内周期复位记账(计费)电能累计量
    public static final int view = 122;                 //读选定时间、地址范围内电能累计量
    public static final int cycleView = 123;            //读选定时间、地址范围内周期复位电能累计量
    //新加
    public static final int timeAsyn = 128;             //时间同步
    public static final int getDeviation = 150;         //获取时间阀
    public static final int setDeviation = 151;         //设置时间阀
    public static final int rpView = 160;               //选定时间、地址范围内的电能累积量
    public static final int tariff = 161;               //选定时间、地址范围内的费率
    public static final int dayView = 162;              //选定时间、地址范围内的日电能累积量
    public static final int monthView = 163;            //选定时间、地址范围内的月电能累积量
    public static final int dayRpView = 164;            //选定时间、地址范围内的日四象限无功电能累积量
    public static final int monthRpView = 165;          //选定时间、地址范围内的月四象限无功电能累积量
    public static final int dayTariff = 166;            //选定时间、地址范围内的日费率
    public static final int monthTariff = 167;          //选定时间、地址范围内的月费率
    public static final int dayDemand = 168;            //选定时间、地址范围内的月需量
    public static final int monthDemand = 169;          //选定时间、地址范围内的月需量
    public static final int initEnd = 70;               //

    public static int compareType(int type){
        int returnType = 0;
        switch (type&0xff) {
            case Ti.factory:returnType=returnFactory;break;
            case Ti.singleInfo:returnType=returnSingleInfo;break;
            case Ti.timeLimitSingleInfo:returnType=returnSingleInfo;break;
            case Ti.getTerminalTime:returnType=returnTerminalTime;break;
            case Ti.billView:returnType=returnBillView;break;
            case Ti.cycleBillView:returnType=returnCycleBillView;break;
            case Ti.view:returnType=returnView;break;
            case Ti.cycleView:returnType=returnCycleView;break;
            case Ti.timeAsyn:returnType=timeAsyn;break;
            case Ti.getDeviation:returnType=returnDeviation;break;
            case Ti.setDeviation:returnType=setDeviation;break;
            case Ti.rpView:returnType=returnRpView;break;
            case Ti.tariff:returnType=returnTariff;break;
            case Ti.dayView:returnType=returnDayView;break;
            case Ti.monthView:returnType=returnMonthView;break;
            case Ti.dayRpView:returnType=returnDayRpView;break;
            case Ti.monthRpView:returnType=returnMonthRpView;break;
            case Ti.dayTariff:returnType=returnDayTariff;break;
            case Ti.monthTariff:returnType=returnMonthTariff;break;
            case Ti.dayDemand:returnType= returnDayDemand;break;
            case Ti.monthDemand:returnType= returnMonthDemand;break;
            case Ti.initEnd:returnType=returnInitEnd;break;
            default:
                logger.error("---------------Not Found Type----------------");
                break;
        }
        return returnType;
    }

}