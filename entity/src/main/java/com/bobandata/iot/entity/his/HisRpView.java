package com.bobandata.iot.entity.his;

import com.bobandata.iot.util.TimestampDeserializer;
import com.bobandata.iot.util.TimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 18:49 2019/1/15.
 */
@Entity
@Table(name = "his_rp_view")
public class HisRpView extends IData {
    private Double rp1Value;
    private Long rp1Status;
    private Double rp1RawValue;
    private Long rp1RawStatus;
    private Double rp2Value;
    private Long rp2Status;
    private Double rp2RawValue;
    private Long rp2RawStatus;
    private Double rp3Value;
    private Long rp3Status;
    private Double rp3RawValue;
    private Long rp3RawStatus;
    private Double rp4Value;
    private Long rp4Status;
    private Double rp4RawValue;
    private Long rp4RawStatus;
    private Long rpViewId;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp lastRefreshTime;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RP_VIEW_ID")
    public Long getRpViewId() {return rpViewId;}
    public void setRpViewId(Long rpViewId){this.rpViewId = rpViewId;}

    @Column(
            name = "RP1_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRp1Value() {
        return this.rp1Value;
    }

    public void setRp1Value(Double rp1Value) {
        this.rp1Value = rp1Value;
    }

    @Column(
            name = "RP1_STATUS"
    )
    public Long getRp1Status() {
        return this.rp1Status;
    }

    public void setRp1Status(Long rp1Status) {
        this.rp1Status = rp1Status;
    }

    @Column(
            name = "RP1_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRp1RawValue() {
        return this.rp1RawValue;
    }

    public void setRp1RawValue(Double rp1RawValue) {
        this.rp1RawValue = rp1RawValue;
    }

    @Column(
            name = "RP1_RAW_STATUS"
    )
    public Long getRp1RawStatus() {
        return this.rp1RawStatus;
    }

    public void setRp1RawStatus(Long rp1RawStatus) {
        this.rp1RawStatus = rp1RawStatus;
    }

    @Column(
            name = "RP2_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRp2Value() {
        return this.rp2Value;
    }

    public void setRp2Value(Double rp2Value) {
        this.rp2Value = rp2Value;
    }

    @Column(
            name = "RP2_STATUS"
    )
    public Long getRp2Status() {
        return this.rp2Status;
    }

    public void setRp2Status(Long rp2Status) {
        this.rp2Status = rp2Status;
    }

    @Column(
            name = "RP2_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRp2RawValue() {
        return this.rp2RawValue;
    }

    public void setRp2RawValue(Double rp2RawValue) {
        this.rp2RawValue = rp2RawValue;
    }

    @Column(
            name = "RP2_RAW_STATUS"
    )
    public Long getRp2RawStatus() {
        return this.rp2RawStatus;
    }

    public void setRp2RawStatus(Long rp2RawStatus) {
        this.rp2RawStatus = rp2RawStatus;
    }

    @Column(
            name = "RP3_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRp3Value() {
        return this.rp3Value;
    }

    public void setRp3Value(Double rp3Value) {
        this.rp3Value = rp3Value;
    }

    @Column(
            name = "RP3_STATUS"
    )
    public Long getRp3Status() {
        return this.rp3Status;
    }

    public void setRp3Status(Long rp3Status) {
        this.rp3Status = rp3Status;
    }

    @Column(
            name = "RP3_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRp3RawValue() {
        return this.rp3RawValue;
    }

    public void setRp3RawValue(Double rp3RawValue) {
        this.rp3RawValue = rp3RawValue;
    }

    @Column(
            name = "RP3_RAW_STATUS"
    )
    public Long getRp3RawStatus() {
        return this.rp3RawStatus;
    }

    public void setRp3RawStatus(Long rp3RawStatus) {
        this.rp3RawStatus = rp3RawStatus;
    }

    @Column(
            name = "RP4_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRp4Value() {
        return this.rp4Value;
    }

    public void setRp4Value(Double rp4Value) {
        this.rp4Value = rp4Value;
    }

    @Column(
            name = "RP4_STATUS"
    )
    public Long getRp4Status() {
        return this.rp4Status;
    }

    public void setRp4Status(Long rp4Status) {
        this.rp4Status = rp4Status;
    }

    @Column(
            name = "RP4_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRp4RawValue() {
        return this.rp4RawValue;
    }

    public void setRp4RawValue(Double rp4RawValue) {
        this.rp4RawValue = rp4RawValue;
    }

    @Column(
            name = "RP4_RAW_STATUS"
    )
    public Long getRp4RawStatus() {
        return this.rp4RawStatus;
    }

    public void setRp4RawStatus(Long rp4RawStatus) {
        this.rp4RawStatus = rp4RawStatus;
    }

    @Column(
            name = "LAST_REFRESH_TIME",
            nullable = false,
            length = 19
    )
    public Timestamp getLastRefreshTime() {
        return this.lastRefreshTime;
    }

    public void setLastRefreshTime(Timestamp lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

}
