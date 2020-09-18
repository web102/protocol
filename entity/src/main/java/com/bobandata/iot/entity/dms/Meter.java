package com.bobandata.iot.entity.dms;

import javax.persistence.*;

/**
 * @Author: zhanglingzhi
 * @Description:    电表表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:09 2018/7/17.
 */
@Entity
public class Meter {
//    METER_ID			电表ID		Int		10
//    METER_NAME		名称		varchar	20
//    METER_ADDR		电表SN		varchar	20
//    STATUS				通讯状态	int		10
//    ERTU_ID			终端ID		int		10
//    PROTOCOL_ID		规约ID		int		10
//    ACQUIRED_ID		任务ID		int		10
//    SERIALPORT        串口端号    varchar 20
//    BAUDRATE          波特率      int     10
//    DATA_BIT          数据位      int     10
//    PARITY_BIT        校验位      int     10
//    STOP_BIT          停止位      int     10

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer meterId;
    @Column(name = "METER_NAME")
    private String meterName;
    @Column(name = "METER_ADDR")
    private String meterAddr;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "ERTU_ID")
    private Integer ertuId;
    @Column(name = "PROTOCOL_ID")
    private Integer protocolId;
    @Column(name = "ACQUIRED_ID")
    private Integer acquiredId;
    @Column(name="SERIALPORT")
    private String serialport;
    @Column(name = "BAUDRATE")
    private int baudrate;
    @Column(name = "DATA_BIT")
    private short dataBit;
    @Column(name = "STOP_BIT")
    private short stopBit;
    @Column(name = "PARITY_BIT")
    private short parityBit;

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getMeterAddr() {
        return meterAddr;
    }

    public void setMeterAddr(String meterAddr) {
        this.meterAddr = meterAddr;
    }


    public Integer getErtuId() {
        return ertuId;
    }

    public void setErtuId(Integer ertuId) {
        this.ertuId = ertuId;
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public Integer getAcquiredId() {
        return acquiredId;
    }

    public void setAcquiredId(Integer acquiredId) {
        this.acquiredId = acquiredId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSerialport() {
        return serialport;
    }

    public void setSerialport(String serialport) {
        this.serialport = serialport;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(int baudrate) {
        this.baudrate = baudrate;
    }

    public short getDataBit() {
        return dataBit;
    }

    public void setDataBit(short dataBit) {
        this.dataBit = dataBit;
    }

    public short getStopBit() {
        return stopBit;
    }

    public void setStopBit(short stopBit) {
        this.stopBit = stopBit;
    }

    public short getParityBit() {
        return parityBit;
    }

    public void setParityBit(short parityBit) {
        this.parityBit = parityBit;
    }
}
