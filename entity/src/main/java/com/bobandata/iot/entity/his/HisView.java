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
 * @Date: Created in 18:40 2019/1/15.
 */
@Entity
@Table(name = "his_view")
public class HisView extends IData{

    private Long viewId;
    private Double papValue;
    private Long papStatus;
    private Double papRawValue;
    private Long papRawStatus;
    private Double rapValue;
    private Long rapStatus;
    private Double rapRawValue;
    private Long rapRawStatus;
    private Double prpValue;
    private Long prpStatus;
    private Double prpRawValue;
    private Long prpRawStatus;
    private Double rrpValue;
    private Long rrpStatus;
    private Double rrpRawValue;
    private Long rrpRawStatus;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp lastRefreshTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VIEW_ID", unique = true, nullable = false)
    public Long getViewId() {
        return viewId;
    }

    public void setViewId(Long viewId) {
        this.viewId = viewId;
    }

    @Column(
            name = "PAP_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPapValue() {
        return this.papValue;
    }

    public void setPapValue(Double papValue) {
        this.papValue = papValue;
    }

    @Column(
            name = "PAP_STATUS"
    )
    public Long getPapStatus() {
        return this.papStatus;
    }

    public void setPapStatus(Long papStatus) {
        this.papStatus = papStatus;
    }

    @Column(
            name = "PAP_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPapRawValue() {
        return this.papRawValue;
    }

    public void setPapRawValue(Double papRawValue) {
        this.papRawValue = papRawValue;
    }

    @Column(
            name = "PAP_RAW_STATUS"
    )
    public Long getPapRawStatus() {
        return this.papRawStatus;
    }

    public void setPapRawStatus(Long papRawStatus) {
        this.papRawStatus = papRawStatus;
    }

    @Column(
            name = "RAP_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRapValue() {
        return this.rapValue;
    }

    public void setRapValue(Double rapValue) {
        this.rapValue = rapValue;
    }

    @Column(
            name = "RAP_STATUS"
    )
    public Long getRapStatus() {
        return this.rapStatus;
    }

    public void setRapStatus(Long rapStatus) {
        this.rapStatus = rapStatus;
    }

    @Column(
            name = "RAP_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRapRawValue() {
        return this.rapRawValue;
    }

    public void setRapRawValue(Double rapRawValue) {
        this.rapRawValue = rapRawValue;
    }

    @Column(
            name = "RAP_RAW_STATUS"
    )
    public Long getRapRawStatus() {
        return this.rapRawStatus;
    }

    public void setRapRawStatus(Long rapRawStatus) {
        this.rapRawStatus = rapRawStatus;
    }

    @Column(
            name = "PRP_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrpValue() {
        return this.prpValue;
    }

    public void setPrpValue(Double prpValue) {
        this.prpValue = prpValue;
    }

    @Column(
            name = "PRP_STATUS"
    )
    public Long getPrpStatus() {
        return this.prpStatus;
    }

    public void setPrpStatus(Long prpStatus) {
        this.prpStatus = prpStatus;
    }

    @Column(
            name = "PRP_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrpRawValue() {
        return this.prpRawValue;
    }

    public void setPrpRawValue(Double prpRawValue) {
        this.prpRawValue = prpRawValue;
    }

    @Column(
            name = "PRP_RAW_STATUS"
    )
    public Long getPrpRawStatus() {
        return this.prpRawStatus;
    }

    public void setPrpRawStatus(Long prpRawStatus) {
        this.prpRawStatus = prpRawStatus;
    }

    @Column(
            name = "RRP_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrpValue() {
        return this.rrpValue;
    }

    public void setRrpValue(Double rrpValue) {
        this.rrpValue = rrpValue;
    }

    @Column(
            name = "RRP_STATUS"
    )
    public Long getRrpStatus() {
        return this.rrpStatus;
    }

    public void setRrpStatus(Long rrpStatus) {
        this.rrpStatus = rrpStatus;
    }

    @Column(
            name = "RRP_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrpRawValue() {
        return this.rrpRawValue;
    }

    public void setRrpRawValue(Double rrpRawValue) {
        this.rrpRawValue = rrpRawValue;
    }

    @Column(
            name = "RRP_RAW_STATUS"
    )
    public Long getRrpRawStatus() {
        return this.rrpRawStatus;
    }

    public void setRrpRawStatus(Long rrpRawStatus) {
        this.rrpRawStatus = rrpRawStatus;
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

    public String toString() {
        return "HisViewAcq [papValue=" + this.papValue + ", papStatus=" + this.papStatus + ", papRawValue=" + this.papRawValue + ", papRawStatus=" + this.papRawStatus + ", rapValue=" + this.rapValue + ", rapStatus=" + this.rapStatus + ", rapRawValue=" + this.rapRawValue + ", rapRawStatus=" + this.rapRawStatus + ", prpValue=" + this.prpValue + ", prpStatus=" + this.prpStatus + ", prpRawValue=" + this.prpRawValue + ", prpRawStatus=" + this.prpRawStatus + ", rrpValue=" + this.rrpValue + ", rrpStatus=" + this.rrpStatus + ", rrpRawValue=" + this.rrpRawValue + ", rrpRawStatus=" + this.rrpRawStatus + ", lastRefreshTime=" + this.lastRefreshTime + "]";
    }
}
