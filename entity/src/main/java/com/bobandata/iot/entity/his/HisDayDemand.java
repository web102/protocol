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
 * @Timestamp: Created in 18:50 2019/1/15.
 */
@Entity
@Table(name = "his_day_demand")
public class HisDayDemand extends IData {
    private Double papValue;
    private Long papStatus;
    private Double papRawValue;
    private Long papRawStatus;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp papTime;
    private Double rapValue;
    private Long rapStatus;
    private Double rapRawValue;
    private Long rapRawStatus;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp rapTime;
    private Double prpValue;
    private Long prpStatus;
    private Double prpRawValue;
    private Long prpRawStatus;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp prpTime;
    private Double rrpValue;
    private Long rrpStatus;
    private Double rrpRawValue;
    private Long rrpRawStatus;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp rrpTime;
    private Long demandId;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DEMAND_ID")
    public Long getDemandId() {return demandId;}
    public void setDemandId(Long demandId) {this.demandId = demandId;}


    @Column(
            name = "PAP_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPapValue() {
        return papValue;
    }
    public void setPapValue(Double papValue) {
        this.papValue = papValue;
    }

    @Column(
            name = "PAP_STATUS"
    )
    public Long getPapStatus() {
        return papStatus;
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
        return papRawValue;
    }
    public void setPapRawValue(Double papRawValue) {
        this.papRawValue = papRawValue;
    }

    @Column(
            name = "PAP_RAW_STATUS"
    )
    public Long getPapRawStatus() {
        return papRawStatus;
    }
    public void setPapRawStatus(Long papRawStatus) {
        this.papRawStatus = papRawStatus;
    }

    @Column(
            name = "PAP_TIME"
    )
    public Timestamp getPapTime() {
        return papTime;
    }
    public void setPapTime(Timestamp papTime) {
        this.papTime = papTime;
    }

    @Column(
            name = "RAP_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRapValue() {
        return rapValue;
    }
    public void setRapValue(Double rapValue) {
        this.rapValue = rapValue;
    }
    @Column(
            name = "RAP_STATUS"
    )
    public Long getRapStatus() {
        return rapStatus;
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
        return rapRawValue;
    }
    public void setRapRawValue(Double rapRawValue) {
        this.rapRawValue = rapRawValue;
    }

    @Column(
            name = "RAP_RAW_STATUS"
    )
    public Long getRapRawStatus() {
        return rapRawStatus;
    }
    public void setRapRawStatus(Long rapRawStatus) {
        this.rapRawStatus = rapRawStatus;
    }

    @Column(
            name = "RAP_TIME"
    )
    public Timestamp getRapTime() {
        return rapTime;
    }
    public void setRapTime(Timestamp rapTime) {
        this.rapTime = rapTime;
    }

    @Column(
            name = "PRP_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrpValue() {
        return prpValue;
    }
    public void setPrpValue(Double prpValue) {
        this.prpValue = prpValue;
    }

    @Column(
            name = "PRP_STATUS"
    )
    public Long getPrpStatus() {
        return prpStatus;
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
        return prpRawValue;
    }
    public void setPrpRawValue(Double prpRawValue) {
        this.prpRawValue = prpRawValue;
    }

    @Column(
            name = "PRP_RAW_STATUS"
    )
    public Long getPrpRawStatus() {
        return prpRawStatus;
    }
    public void setPrpRawStatus(Long prpRawStatus) {
        this.prpRawStatus = prpRawStatus;
    }

    @Column(
            name = "PRP_TIME"
    )
    public Timestamp getPrpTime() {
        return prpTime;
    }
    public void setPrpTime(Timestamp prpTime) {
        this.prpTime = prpTime;
    }

    @Column(
            name = "RRP_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrpValue() {
        return rrpValue;
    }
    public void setRrpValue(Double rrpValue) {
        this.rrpValue = rrpValue;
    }

    @Column(
            name = "RRP_STATUS"
    )
    public Long getRrpStatus() {
        return rrpStatus;
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
        return rrpRawValue;
    }
    public void setRrpRawValue(Double rrpRawValue) {
        this.rrpRawValue = rrpRawValue;
    }

    @Column(
            name = "RRP_RAW_STATUS"
    )
    public Long getRrpRawStatus() {
        return rrpRawStatus;
    }
    public void setRrpRawStatus(Long rrpRawStatus) {
        this.rrpRawStatus = rrpRawStatus;
    }

    @Column(
            name = "RRP_TIME"
    )
    public Timestamp getRrpTime() {
        return rrpTime;
    }
    public void setRrpTime(Timestamp rrpTime) {
        this.rrpTime = rrpTime;
    }
}
