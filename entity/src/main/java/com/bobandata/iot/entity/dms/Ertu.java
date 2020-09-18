package com.bobandata.iot.entity.dms;

import javax.persistence.*;

/**
 * @Author: zhanglingzhi
 * @Description:    终端表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 14:55 2018/7/17.
 */
@Entity
public class Ertu {

//    ERTU_ID		终端ID		Int		10	是
//    ERTU_NAME		场站名称	varchar	20	否
//    ERTU_TYPE		场站类型	int		6	否
//    STATUS 		是否使用中	int		10	否
//    PROTOCOL_ID	规约ID		int		10	否
//    ACQUIRED_ID	任务ID		int		10	否
//    ADDRESS		地址		varchar	100	否
//    STORE_CAP		存储容量	int		20	否


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ertuId;
    @Column(name = "ERTU_NAME")
    private String ertuName;
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

    public Integer getErtuId() {
        return ertuId;
    }

    public void setErtuId(Integer ertuId) {
        this.ertuId = ertuId;
    }

    public String getErtuName() {
        return ertuName;
    }

    public void setErtuName(String ertuName) {
        this.ertuName = ertuName;
    }

    public Integer getErtuType() {
        return ertuType;
    }

    public void setErtuType(Integer ertuType) {
        this.ertuType = ertuType;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStoreCap() {
        return storeCap;
    }

    public void setStoreCap(Integer storeCap) {
        this.storeCap = storeCap;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
