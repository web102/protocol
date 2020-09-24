package com.bobandata.iot.entity.dms;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:38 2018/7/17.
 */

@Data
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
}
