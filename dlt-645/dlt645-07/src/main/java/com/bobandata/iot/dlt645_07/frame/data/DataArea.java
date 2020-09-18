package com.bobandata.iot.dlt645_07.frame.data;

import com.bobandata.iot.dlt645_07.frame.util.IUtilFrame;

/**
 * @Author: liutuo
 * @Description: 数据域：长度、数据项、数据体
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 12:06 2018/9/2.
 */
public abstract class DataArea implements IUtilFrame{

    //消息长度
    protected int dataLength;

    protected DataValue dataValue;

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public DataValue getDataValue() {
        return dataValue;
    }

    public void setDataValue(DataValue dataValue) {
        this.dataValue = dataValue;
    }
}
