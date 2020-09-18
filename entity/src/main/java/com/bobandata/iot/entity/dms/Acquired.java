package com.bobandata.iot.entity.dms;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:38 2018/7/17.
 */

@Entity
@Table(name = "Acquired")
public class Acquired {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer acquiredId;

    @Column(name = "acquired_name")
    private String acquiredName;
    @Column(name = "acquired_interval")
    private Integer acquiredInterval;
    @Column(name = "initial_delay")
    private Integer initialDelay;
    @Column(name = "cur_status")
    private Integer curStatus;
    @Column(name = "address")
    private Integer address;
    @Column(name = "pri")
    private Integer pri;
    @Column(name = "last_acquire_time")
    private Date lastAcquireTime;
    @Column(name = "comm_mode")
    private Integer commMode;

    public Integer getAcquiredId() {
        return acquiredId;
    }

    public void setAcquiredId(Integer acquiredId) {
        this.acquiredId = acquiredId;
    }

    public String getAcquiredName() {
        return acquiredName;
    }

    public void setAcquiredName(String acquiredName) {
        this.acquiredName = acquiredName;
    }

    public Integer getAcquiredInterval() {
        return acquiredInterval;
    }

    public void setAcquiredInterval(Integer acquiredInterval) {
        this.acquiredInterval = acquiredInterval;
    }

    public Integer getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(Integer initialDelay) {
        this.initialDelay = initialDelay;
    }

    public Integer getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(Integer curStatus) {
        this.curStatus = curStatus;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getPri() {
        return pri;
    }

    public void setPri(Integer pri) {
        this.pri = pri;
    }

    public Date getLastAcquireTime() {
        return lastAcquireTime;
    }

    public void setLastAcquireTime(Date lastAcquireTime) {
        this.lastAcquireTime = lastAcquireTime;
    }

    public Integer getCommMode() {
        return commMode;
    }

    public void setCommMode(Integer commMode) {
        this.commMode = commMode;
    }
}
