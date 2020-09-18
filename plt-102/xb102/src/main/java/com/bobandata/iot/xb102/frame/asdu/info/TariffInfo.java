package com.bobandata.iot.xb102.frame.asdu.info;

/**
 * @Author: 李志鹏
 * @Date: 2019/12/1 18:48
 * @Desc:
 **/
public abstract class TariffInfo extends Info{
    private int checksum;

    public int getChecksum() {
        return this.checksum;
    }
    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }
}
