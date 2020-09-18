package com.bobandata.iot.xb102.frame.asdu.info;

import com.bobandata.iot.xb102.frame.util.IUtilFrame;

public abstract class DataInfo extends Info implements IUtilFrame {

    private int status;
    private int checksum;

    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public int getChecksum() {
        return this.checksum;
    }
    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }


}
