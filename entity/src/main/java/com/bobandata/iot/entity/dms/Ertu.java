package com.bobandata.iot.entity.dms;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: zhanglingzhi
 * @Description:    终端表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 14:55 2018/7/17.
 */
@Data
@Entity
public class Ertu {

//    ERTU_ID		终端ID		Int		10	是
//    ERTU_NAME		场站名称	varchar	20	否
//    ERTU_TYPE		场站类型	int		6	否
//    STATUS 		是否使用中	int		10	否
//    PROTOCOL_ID	规约ID		int		10	否
//    ACQUIRED_ID	任务ID		int		10	否
//    ADDRESS		地址		varchar	100	否
//    MANUFACTURER  制造商      varchar
//    MODEL         型号        varchar
//    STORE_CAP		存储容量	int		20	否


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ertuId;
    @Column(name = "ERTU_NAME")
    private String ertuName;
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "ERTU_TYPE")
    private Integer ertuType;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "PROTOCOL_ID")
    private Integer protocolId;
    @Column(name = "ACQUIRED_ID")
    private Integer acquiredId;
    @Column(name = "ADDRESS",unique = true)
    private String address;
    @Column(name = "STORE_CAP")
    private Integer storeCap;
}
