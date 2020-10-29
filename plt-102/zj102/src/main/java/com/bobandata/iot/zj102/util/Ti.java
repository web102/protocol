package com.bobandata.iot.zj102.util;

public enum  Ti {

    UNKNOWN             (0, 0,"未定义"),

    //基本（100-123）
    FACTORY             (100, 71,"读制造厂和产品规范"),
    SINGLEINFO          (101, 1,"读带最新单点信息的记录"),
    TIMELIMITSINGLEINFO (102, 1,"读选定时间范围内单点信息的记录"),
    GETTERMINALTIME     (103, 72,"读电能累计量数据终端设备的当前系统时间"),

    FIRST_BILLVIEW      (104,2,"读最早累计时段的记账(计费)电能累计量"),
    FIRST_ADDR_BILLVIEW (105,2,"读最早累计时段的记账(计费)电能累计量"),
    LIMIT_BILLVIEW      (106,2,"读一个指定的过去累计时段的记账(计费)电能累计量"),
    LIMIT_ADDR_BILLVIEW (107,2,"读一个指定的过去累计时段和一个选定的地址范围的记账(计费)电能累计量"),

    FIRST_CYCLEBILLVIEW      (108,5,"读周期地复位的最早累计时段的记账(计费)电能累计量 "),
    FIRST_ADDR_CYCLEBILLVIEW (109,5,"读周期地复位的最早累计时段和一个选定的的地址范围的记账(计费)电能累计量"),
    LIMIT_CYCLEBILLVIEW      (110,5,"读一个指定的过去累计时段的周期地复位的记账(计费)电能累计量"),
    LIMIT_ADDR_CYCLEBILLVIEW (111,5,"读一个指定的过去累计时段和一个选定的地址范围的周期地复位的记账(计费)电能累计量"),

    FIRST_VIEW      (112,8,"读最早累计时段的运行电能累计量"),
    FIRST_ADDR_VIEW (113,8,"读最早累计时段的和一个选定的地址范围运行电能累计量"),
    LIMIT_VIEW      (114,8,"读一个指定的过去累计时段的运行电能累计量"),
    LIMIT_ADDR_VIEW (115,8,"读一个指定的过去累计时段和一个选定的地址范围的运行电能累计量"),

    FIRST_CYCLEVIEW      (116,11,"读周期地复位的最早累计时段的运行电能累计量"),
    FIRST_ADDR_CYCLEVIEW (117,11,"读周期地复位的最早累计时段和一个选定的地址范围的运行电能累计量"),
    LIMIT_CYCLEVIEW      (118,11,"读一个指定的累计时段的周期地复位的运行电能累计量"),
    LIMIT_ADDR_CYCLEVIEW (119,11,"读一个指定的过去累计时段和一个选定的地址范围的周期地复位的运行电能累计量"),


    BILLVIEW            (120, 2,"读选定时间、地址范围内记账(计费)电能累计量"),
    CYCLEBILLVIEW       (121, 5,"读选定时间、地址范围内周期复位记账(计费)电能累计量"),
    VIEW                (122, 8,"读选定时间、地址范围内电能累计量"),
    CYCLEVIEW          (123, 11,"读选定时间、地址范围内周期复位电能累计量"),

    TIMEASYN            (128, 72,"时间同步"),

    GET_DEVIATION        (150, 73,"获取时间阀"),
    SET_DEVIATION        (151, 73,"设置时间阀"),
    RPVIEW              (160, 16,"选定时间、地址范围内的电能累积量"),
    TARIFF              (161, 17,"选定时间、地址范围内的费率"),
    DAY_VIEW             (162, 18,"选定时间、地址范围内的日电能累积量"),
    MONTH_VIEW           (163, 19,"选定时间、地址范围内的月电能累积量"),
    DAY_RPVIEW           (164, 20,"选定时间、地址范围内的日四象限无功电能累积量"),
    MONTH_RPVIEW         (165, 21,"选定时间、地址范围内的月四象限无功电能累积量"),
    DAY_TARIFF           (166, 22,"选定时间、地址范围内的日费率"),
    MONTH_TARIFF         (167, 23,"选定时间、地址范围内的月费率"),
    DAY_DEMAND           (168, 24,"选定时间、地址范围内的月需量"),
    MONTH_DEMAND         (169, 25,"选定时间、地址范围内的月需量"),
    INIT_END             (70 , 70,""),

    MEAS                (170,26,"选定时间、地址范围内的遥测"),
    TERMINAL_FILE        (171,27,"终端档案"),
    METER_FILE           (172,28,"电表档案");




    private byte request;
    private byte response;
    private String name;


    Ti(int request,int response, String name) {
        this.request = (byte) request;
        this.response = (byte) response;
        this.name = name;
    }

    public int getRequest() {
        return request&0xff;
    }

    public int getResponse() {
        return response&0xff;
    }

    public String getName() {
        return name;
    }

    public static Ti findByRequest(int value){
        for (Ti dataType:Ti.values()){
            if(dataType.getRequest()==value){
                return dataType;
            }
        }
        return Ti.UNKNOWN;
    }

    public static Ti findByResponse(int value){
        for (Ti dataType:Ti.values()){
            if(dataType.getResponse()==value){
                return dataType;
            }
        }
        return Ti.UNKNOWN;
    }

    public static Ti find(byte value){
        for (Ti dataType:Ti.values()){
            if(dataType.getRequest()==value||dataType.getResponse()==value){
                return dataType;
            }
        }
        return Ti.UNKNOWN;
    }

}