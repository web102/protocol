package com.bobandata.iot.entity.dms;

import javax.persistence.*;
import lombok.Data;

/**
 * @Author: zhanglingzhi
 * @Description:    电表表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:09 2018/7/17.
 */

@Data
@Entity
public class Meter {
//    METER_ID			电表ID		Int		10
//    METER_NAME		名称		varchar	20
//    METER_NO          电表序号     Int    10
//    PARAM_MODEL_ID       电表开始点号     Int    10
//    METER_ADDR		电表地址	varchar	20
//    STATUS			通讯状态	int		10
//    ERTU_ID			终端ID		int		10
//    MODEL             电表型号    varchar  20
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
    @Column(name = "METER_NO")
    private Integer meterNo;
    @Column(name = "PARAM_MODEL_ID")
    private Integer paramModelId;
    @Column(name = "METER_ADDR")
    private String meterAddr;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "ERTU_ID")
    private Integer ertuId;
    @Column(name = "MODEL")
    private String  model;
    @Column(name = "PROTOCOL_ID")
    private Integer protocolId;
    @Column(name = "ACQUIRED_ID")
    private Integer acquiredId;
    @Column(name="SERIALPORT")
    private String serialport;
    @Column(name = "BAUDRATE")
    private Integer baudrate;
    @Column(name = "DATA_BIT")
    private Short dataBit;
    @Column(name = "STOP_BIT")
    private Short stopBit;
    @Column(name = "PARITY_BIT")
    private Short parityBit;
}
