package com.bobandata.iot.entity.dms;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: zhanglingzhi
 * @Description:    信息体表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:14 2018/7/17.
 */
@Entity
public class Pulse {
//    PULSE_ID	信息体ID	Int	20	是
//    PULSE_NAME	信息体名称	varchar	100	否
//    PULSE_TYPE	信息体类型	int	6	否
//    PULSE_ADDR	信息体地址	int 	11	否
//    METER_ID	电表ID	int	20	否
//    IS_LIMIT_VALID	是否限制有效值	int	11	否
//    LIMIT_VALID_INTERVAL	限制有效间隔	int	11	否
//    LOW_LIMIT	下限	float		否
//    UP_LIMIT	上限	float		否
//    LIMIT_VALID_TIME_TAG	限制有效值的标签	timestamp		否

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pulseId;
    @Column(name = "PULSE_NAME")
    private String pulseName;
    @Column(name = "PULSE_TYPE")
    private Integer pulseType;
    @Column(name = "PULSE_ADDR")
    private Integer pulseAddr;
    @Column(name = "METER_ID")
    private Integer meterId;
    @Column(name = "IS_LIMIT_VALID")
    private Integer isLimitValid;
    @Column(name = "LIMIT_VALID_INTERVAL")
    private Integer limitValidInterval;
    @Column(name = "LOW_LIMIT")
    private Float lowLimit;
    @Column(name = "UP_LIMIT")
    private Float upLimit;
    @Column(name = "LIMIT_VALID_TIME_TAG")
    private Date limitValidTimeTag;

    public Integer getPulseId() {
        return pulseId;
    }

    public void setPulseId(Integer pulseId) {
        this.pulseId = pulseId;
    }

    public String getPulseName() {
        return pulseName;
    }

    public void setPulseName(String pulseName) {
        this.pulseName = pulseName;
    }

    public Integer getPulseType() {
        return pulseType;
    }

    public void setPulseType(Integer pulseType) {
        this.pulseType = pulseType;
    }

    public Integer getPulseAddr() {
        return pulseAddr;
    }

    public void setPulseAddr(Integer pulseAddr) {
        this.pulseAddr = pulseAddr;
    }

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public Integer getIsLimitValid() {
        return isLimitValid;
    }

    public void setIsLimitValid(Integer isLimitValid) {
        this.isLimitValid = isLimitValid;
    }

    public Integer getLimitValidInterval() {
        return limitValidInterval;
    }

    public void setLimitValidInterval(Integer limitValidInterval) {
        this.limitValidInterval = limitValidInterval;
    }

    public Float getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(Float lowLimit) {
        this.lowLimit = lowLimit;
    }

    public Float getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(Float upLimit) {
        this.upLimit = upLimit;
    }

    public Date getLimitValidTimeTag() {
        return limitValidTimeTag;
    }

    public void setLimitValidTimeTag(Date limitValidTimeTag) {
        this.limitValidTimeTag = limitValidTimeTag;
    }
}
