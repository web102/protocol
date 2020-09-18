package com.bobandata.iot.xb102.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ti {
    private static final Logger logger = LoggerFactory.getLogger(Ti.class);
    //从站发给主站
    //基本（1-13）
    public static final int returnSingleInfo = 1;                    //带时标的单点信息
    public static final int returnData = 2;                      //记账(计费)电能累计量，每个量为四个八位位组
    public static final int returnTerminalTime = 72;                 //电能累计量数据终端设备的当前系统时间
    //新加
    public static final int returnTariff = 17;                       //费率电能量, 顺序为尖、峰、平、谷，每个量为四个八位位组

    public static final int returnTimeAsyn = 128;

    //主站发给从站
    //基本（100-123）
    public static final int singleInfo = 101;           //读带最新单点信息的记录
    public static final int timeLimitSingleInfo = 102;  //读选定时间范围内单点信息的记录
    public static final int getTerminalTime = 103;      //读电能累计量数据终端设备的当前系统时间
    public static final int data = 120;             //读选定时间、地址范围内记账(计费)电能累计量
    //新加
    public static final int timeAsyn = 128;             //时间同步
    public static final int tariff = 161;               //选定时间、地址范围内的费率

    public static int compareType(int type){
        int returnType = 0;
        switch (type&0xff) {

            case Ti.singleInfo:returnType=returnSingleInfo;break;
            case Ti.timeLimitSingleInfo:returnType=returnSingleInfo;break;
            case Ti.getTerminalTime:returnType=returnTerminalTime;break;
            case Ti.data:returnType= returnData;break;
            case Ti.timeAsyn:returnType=returnTimeAsyn;break;
            case Ti.tariff:returnType=returnTariff;break;

            default:
                logger.error("---------------Not Found Type----------------");
                break;
        }
        return returnType;
    }

}