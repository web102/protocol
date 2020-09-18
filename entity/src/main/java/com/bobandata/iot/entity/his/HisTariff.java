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
@Table(name = "his_tariff")
public class HisTariff extends IData {
    private Double pap1Value;
    private Long pap1Status;
    private Double pap1RawValue;
    private Long pap1RawStatus;
    private Double rap1Value;
    private Long rap1Status;
    private Double rap1RawValue;
    private Long rap1RawStatus;
    private Double prp1Value;
    private Long prp1Status;
    private Double prp1RawValue;
    private Long prp1RawStatus;
    private Double rrp1Value;
    private Long rrp1Status;
    private Double rrp1RawValue;
    private Long rrp1RawStatus;
    private Double pap2Value;
    private Long pap2Status;
    private Double pap2RawValue;
    private Long pap2RawStatus;
    private Double rap2Value;
    private Long rap2Status;
    private Double rap2RawValue;
    private Long rap2RawStatus;
    private Double prp2Value;
    private Long prp2Status;
    private Double prp2RawValue;
    private Long prp2RawStatus;
    private Double rrp2Value;
    private Long rrp2Status;
    private Double rrp2RawValue;
    private Long rrp2RawStatus;
    private Double pap3Value;
    private Long pap3Status;
    private Double pap3RawValue;
    private Long pap3RawStatus;
    private Double rap3Value;
    private Long rap3Status;
    private Double rap3RawValue;
    private Long rap3RawStatus;
    private Double prp3Value;
    private Long prp3Status;
    private Double prp3RawValue;
    private Long prp3RawStatus;
    private Double rrp3Value;
    private Long rrp3Status;
    private Double rrp3RawValue;
    private Long rrp3RawStatus;
    private Double pap4Value;
    private Long pap4Status;
    private Double pap4RawValue;
    private Long pap4RawStatus;
    private Double rap4Value;
    private Long rap4Status;
    private Double rap4RawValue;
    private Long rap4RawStatus;
    private Double prp4Value;
    private Long prp4Status;
    private Double prp4RawValue;
    private Long prp4RawStatus;
    private Double rrp4Value;
    private Long rrp4Status;
    private Double rrp4RawValue;
    private Long rrp4RawStatus;
    private Double pap5Value;
    private Long pap5Status;
    private Double pap5RawValue;
    private Long pap5RawStatus;
    private Double rap5Value;
    private Long rap5Status;
    private Double rap5RawValue;
    private Long rap5RawStatus;
    private Double prp5Value;
    private Long prp5Status;
    private Double prp5RawValue;
    private Long prp5RawStatus;
    private Double rrp5Value;
    private Long rrp5Status;
    private Double rrp5RawValue;
    private Long rrp5RawStatus;
    private Double pap6Value;
    private Long pap6Status;
    private Double pap6RawValue;
    private Long pap6RawStatus;
    private Double rap6Value;
    private Long rap6Status;
    private Double rap6RawValue;
    private Long rap6RawStatus;
    private Double prp6Value;
    private Long prp6Status;
    private Double prp6RawValue;
    private Long prp6RawStatus;
    private Double rrp6Value;
    private Long rrp6Status;
    private Double rrp6RawValue;
    private Long rrp6RawStatus;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp lastRefreshTime;

    private Long tariffId;


    @Column(
            name = "PAP1_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap1Value() {
        return this.pap1Value;
    }

    public void setPap1Value(Double pap1Value) {
        this.pap1Value = pap1Value;
    }

    @Column(
            name = "PAP1_STATUS"
    )
    public Long getPap1Status() {
        return this.pap1Status;
    }

    public void setPap1Status(Long pap1Status) {
        this.pap1Status = pap1Status;
    }

    @Column(
            name = "PAP1_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap1RawValue() {
        return this.pap1RawValue;
    }

    public void setPap1RawValue(Double pap1RawValue) {
        this.pap1RawValue = pap1RawValue;
    }

    @Column(
            name = "PAP1_RAW_STATUS"
    )
    public Long getPap1RawStatus() {
        return this.pap1RawStatus;
    }

    public void setPap1RawStatus(Long pap1RawStatus) {
        this.pap1RawStatus = pap1RawStatus;
    }

    @Column(
            name = "RAP1_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap1Value() {
        return this.rap1Value;
    }

    public void setRap1Value(Double rap1Value) {
        this.rap1Value = rap1Value;
    }

    @Column(
            name = "RAP1_STATUS"
    )
    public Long getRap1Status() {
        return this.rap1Status;
    }

    public void setRap1Status(Long rap1Status) {
        this.rap1Status = rap1Status;
    }

    @Column(
            name = "RAP1_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap1RawValue() {
        return this.rap1RawValue;
    }

    public void setRap1RawValue(Double rap1RawValue) {
        this.rap1RawValue = rap1RawValue;
    }

    @Column(
            name = "RAP1_RAW_STATUS"
    )
    public Long getRap1RawStatus() {
        return this.rap1RawStatus;
    }

    public void setRap1RawStatus(Long rap1RawStatus) {
        this.rap1RawStatus = rap1RawStatus;
    }

    @Column(
            name = "PRP1_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp1Value() {
        return this.prp1Value;
    }

    public void setPrp1Value(Double prp1Value) {
        this.prp1Value = prp1Value;
    }

    @Column(
            name = "PRP1_STATUS"
    )
    public Long getPrp1Status() {
        return this.prp1Status;
    }

    public void setPrp1Status(Long prp1Status) {
        this.prp1Status = prp1Status;
    }

    @Column(
            name = "PRP1_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp1RawValue() {
        return this.prp1RawValue;
    }

    public void setPrp1RawValue(Double prp1RawValue) {
        this.prp1RawValue = prp1RawValue;
    }

    @Column(
            name = "PRP1_RAW_STATUS"
    )
    public Long getPrp1RawStatus() {
        return this.prp1RawStatus;
    }

    public void setPrp1RawStatus(Long prp1RawStatus) {
        this.prp1RawStatus = prp1RawStatus;
    }

    @Column(
            name = "RRP1_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp1Value() {
        return this.rrp1Value;
    }

    public void setRrp1Value(Double rrp1Value) {
        this.rrp1Value = rrp1Value;
    }

    @Column(
            name = "RRP1_STATUS"
    )
    public Long getRrp1Status() {
        return this.rrp1Status;
    }

    public void setRrp1Status(Long rrp1Status) {
        this.rrp1Status = rrp1Status;
    }

    @Column(
            name = "RRP1_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp1RawValue() {
        return this.rrp1RawValue;
    }

    public void setRrp1RawValue(Double rrp1RawValue) {
        this.rrp1RawValue = rrp1RawValue;
    }

    @Column(
            name = "RRP1_RAW_STATUS"
    )
    public Long getRrp1RawStatus() {
        return this.rrp1RawStatus;
    }

    public void setRrp1RawStatus(Long rrp1RawStatus) {
        this.rrp1RawStatus = rrp1RawStatus;
    }

    @Column(
            name = "PAP2_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap2Value() {
        return this.pap2Value;
    }

    public void setPap2Value(Double pap2Value) {
        this.pap2Value = pap2Value;
    }

    @Column(
            name = "PAP2_STATUS"
    )
    public Long getPap2Status() {
        return this.pap2Status;
    }

    public void setPap2Status(Long pap2Status) {
        this.pap2Status = pap2Status;
    }

    @Column(
            name = "PAP2_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap2RawValue() {
        return this.pap2RawValue;
    }

    public void setPap2RawValue(Double pap2RawValue) {
        this.pap2RawValue = pap2RawValue;
    }

    @Column(
            name = "PAP2_RAW_STATUS"
    )
    public Long getPap2RawStatus() {
        return this.pap2RawStatus;
    }

    public void setPap2RawStatus(Long pap2RawStatus) {
        this.pap2RawStatus = pap2RawStatus;
    }

    @Column(
            name = "RAP2_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap2Value() {
        return this.rap2Value;
    }

    public void setRap2Value(Double rap2Value) {
        this.rap2Value = rap2Value;
    }

    @Column(
            name = "RAP2_STATUS"
    )
    public Long getRap2Status() {
        return this.rap2Status;
    }

    public void setRap2Status(Long rap2Status) {
        this.rap2Status = rap2Status;
    }

    @Column(
            name = "RAP2_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap2RawValue() {
        return this.rap2RawValue;
    }

    public void setRap2RawValue(Double rap2RawValue) {
        this.rap2RawValue = rap2RawValue;
    }

    @Column(
            name = "RAP2_RAW_STATUS"
    )
    public Long getRap2RawStatus() {
        return this.rap2RawStatus;
    }

    public void setRap2RawStatus(Long rap2RawStatus) {
        this.rap2RawStatus = rap2RawStatus;
    }

    @Column(
            name = "PRP2_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp2Value() {
        return this.prp2Value;
    }

    public void setPrp2Value(Double prp2Value) {
        this.prp2Value = prp2Value;
    }

    @Column(
            name = "PRP2_STATUS"
    )
    public Long getPrp2Status() {
        return this.prp2Status;
    }

    public void setPrp2Status(Long prp2Status) {
        this.prp2Status = prp2Status;
    }

    @Column(
            name = "PRP2_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp2RawValue() {
        return this.prp2RawValue;
    }

    public void setPrp2RawValue(Double prp2RawValue) {
        this.prp2RawValue = prp2RawValue;
    }

    @Column(
            name = "PRP2_RAW_STATUS"
    )
    public Long getPrp2RawStatus() {
        return this.prp2RawStatus;
    }

    public void setPrp2RawStatus(Long prp2RawStatus) {
        this.prp2RawStatus = prp2RawStatus;
    }

    @Column(
            name = "RRP2_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp2Value() {
        return this.rrp2Value;
    }

    public void setRrp2Value(Double rrp2Value) {
        this.rrp2Value = rrp2Value;
    }

    @Column(
            name = "RRP2_STATUS"
    )
    public Long getRrp2Status() {
        return this.rrp2Status;
    }

    public void setRrp2Status(Long rrp2Status) {
        this.rrp2Status = rrp2Status;
    }

    @Column(
            name = "RRP2_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp2RawValue() {
        return this.rrp2RawValue;
    }

    public void setRrp2RawValue(Double rrp2RawValue) {
        this.rrp2RawValue = rrp2RawValue;
    }

    @Column(
            name = "RRP2_RAW_STATUS"
    )
    public Long getRrp2RawStatus() {
        return this.rrp2RawStatus;
    }

    public void setRrp2RawStatus(Long rrp2RawStatus) {
        this.rrp2RawStatus = rrp2RawStatus;
    }

    @Column(
            name = "PAP3_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap3Value() {
        return this.pap3Value;
    }

    public void setPap3Value(Double pap3Value) {
        this.pap3Value = pap3Value;
    }

    @Column(
            name = "PAP3_STATUS"
    )
    public Long getPap3Status() {
        return this.pap3Status;
    }

    public void setPap3Status(Long pap3Status) {
        this.pap3Status = pap3Status;
    }

    @Column(
            name = "PAP3_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap3RawValue() {
        return this.pap3RawValue;
    }

    public void setPap3RawValue(Double pap3RawValue) {
        this.pap3RawValue = pap3RawValue;
    }

    @Column(
            name = "PAP3_RAW_STATUS"
    )
    public Long getPap3RawStatus() {
        return this.pap3RawStatus;
    }

    public void setPap3RawStatus(Long pap3RawStatus) {
        this.pap3RawStatus = pap3RawStatus;
    }

    @Column(
            name = "RAP3_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap3Value() {
        return this.rap3Value;
    }

    public void setRap3Value(Double rap3Value) {
        this.rap3Value = rap3Value;
    }

    @Column(
            name = "RAP3_STATUS"
    )
    public Long getRap3Status() {
        return this.rap3Status;
    }

    public void setRap3Status(Long rap3Status) {
        this.rap3Status = rap3Status;
    }

    @Column(
            name = "RAP3_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap3RawValue() {
        return this.rap3RawValue;
    }

    public void setRap3RawValue(Double rap3RawValue) {
        this.rap3RawValue = rap3RawValue;
    }

    @Column(
            name = "RAP3_RAW_STATUS"
    )
    public Long getRap3RawStatus() {
        return this.rap3RawStatus;
    }

    public void setRap3RawStatus(Long rap3RawStatus) {
        this.rap3RawStatus = rap3RawStatus;
    }

    @Column(
            name = "PRP3_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp3Value() {
        return this.prp3Value;
    }

    public void setPrp3Value(Double prp3Value) {
        this.prp3Value = prp3Value;
    }

    @Column(
            name = "PRP3_STATUS"
    )
    public Long getPrp3Status() {
        return this.prp3Status;
    }

    public void setPrp3Status(Long prp3Status) {
        this.prp3Status = prp3Status;
    }

    @Column(
            name = "PRP3_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp3RawValue() {
        return this.prp3RawValue;
    }

    public void setPrp3RawValue(Double prp3RawValue) {
        this.prp3RawValue = prp3RawValue;
    }

    @Column(
            name = "PRP3_RAW_STATUS"
    )
    public Long getPrp3RawStatus() {
        return this.prp3RawStatus;
    }

    public void setPrp3RawStatus(Long prp3RawStatus) {
        this.prp3RawStatus = prp3RawStatus;
    }

    @Column(
            name = "RRP3_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp3Value() {
        return this.rrp3Value;
    }

    public void setRrp3Value(Double rrp3Value) {
        this.rrp3Value = rrp3Value;
    }

    @Column(
            name = "RRP3_STATUS"
    )
    public Long getRrp3Status() {
        return this.rrp3Status;
    }

    public void setRrp3Status(Long rrp3Status) {
        this.rrp3Status = rrp3Status;
    }

    @Column(
            name = "RRP3_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp3RawValue() {
        return this.rrp3RawValue;
    }

    public void setRrp3RawValue(Double rrp3RawValue) {
        this.rrp3RawValue = rrp3RawValue;
    }

    @Column(
            name = "RRP3_RAW_STATUS"
    )
    public Long getRrp3RawStatus() {
        return this.rrp3RawStatus;
    }

    public void setRrp3RawStatus(Long rrp3RawStatus) {
        this.rrp3RawStatus = rrp3RawStatus;
    }

    @Column(
            name = "PAP4_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap4Value() {
        return this.pap4Value;
    }

    public void setPap4Value(Double pap4Value) {
        this.pap4Value = pap4Value;
    }

    @Column(
            name = "PAP4_STATUS"
    )
    public Long getPap4Status() {
        return this.pap4Status;
    }

    public void setPap4Status(Long pap4Status) {
        this.pap4Status = pap4Status;
    }

    @Column(
            name = "PAP4_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap4RawValue() {
        return this.pap4RawValue;
    }

    public void setPap4RawValue(Double pap4RawValue) {
        this.pap4RawValue = pap4RawValue;
    }

    @Column(
            name = "PAP4_RAW_STATUS"
    )
    public Long getPap4RawStatus() {
        return this.pap4RawStatus;
    }

    public void setPap4RawStatus(Long pap4RawStatus) {
        this.pap4RawStatus = pap4RawStatus;
    }

    @Column(
            name = "RAP4_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap4Value() {
        return this.rap4Value;
    }

    public void setRap4Value(Double rap4Value) {
        this.rap4Value = rap4Value;
    }

    @Column(
            name = "RAP4_STATUS"
    )
    public Long getRap4Status() {
        return this.rap4Status;
    }

    public void setRap4Status(Long rap4Status) {
        this.rap4Status = rap4Status;
    }

    @Column(
            name = "RAP4_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap4RawValue() {
        return this.rap4RawValue;
    }

    public void setRap4RawValue(Double rap4RawValue) {
        this.rap4RawValue = rap4RawValue;
    }

    @Column(
            name = "RAP4_RAW_STATUS"
    )
    public Long getRap4RawStatus() {
        return this.rap4RawStatus;
    }

    public void setRap4RawStatus(Long rap4RawStatus) {
        this.rap4RawStatus = rap4RawStatus;
    }

    @Column(
            name = "PRP4_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp4Value() {
        return this.prp4Value;
    }

    public void setPrp4Value(Double prp4Value) {
        this.prp4Value = prp4Value;
    }

    @Column(
            name = "PRP4_STATUS"
    )
    public Long getPrp4Status() {
        return this.prp4Status;
    }

    public void setPrp4Status(Long prp4Status) {
        this.prp4Status = prp4Status;
    }

    @Column(
            name = "PRP4_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp4RawValue() {
        return this.prp4RawValue;
    }

    public void setPrp4RawValue(Double prp4RawValue) {
        this.prp4RawValue = prp4RawValue;
    }

    @Column(
            name = "PRP4_RAW_STATUS"
    )
    public Long getPrp4RawStatus() {
        return this.prp4RawStatus;
    }

    public void setPrp4RawStatus(Long prp4RawStatus) {
        this.prp4RawStatus = prp4RawStatus;
    }

    @Column(
            name = "RRP4_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp4Value() {
        return this.rrp4Value;
    }

    public void setRrp4Value(Double rrp4Value) {
        this.rrp4Value = rrp4Value;
    }

    @Column(
            name = "RRP4_STATUS"
    )
    public Long getRrp4Status() {
        return this.rrp4Status;
    }

    public void setRrp4Status(Long rrp4Status) {
        this.rrp4Status = rrp4Status;
    }

    @Column(
            name = "RRP4_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp4RawValue() {
        return this.rrp4RawValue;
    }

    public void setRrp4RawValue(Double rrp4RawValue) {
        this.rrp4RawValue = rrp4RawValue;
    }

    @Column(
            name = "RRP4_RAW_STATUS"
    )
    public Long getRrp4RawStatus() {
        return this.rrp4RawStatus;
    }

    public void setRrp4RawStatus(Long rrp4RawStatus) {
        this.rrp4RawStatus = rrp4RawStatus;
    }

    @Column(
            name = "PAP5_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap5Value() {
        return this.pap5Value;
    }

    public void setPap5Value(Double pap5Value) {
        this.pap5Value = pap5Value;
    }

    @Column(
            name = "PAP5_STATUS"
    )
    public Long getPap5Status() {
        return this.pap5Status;
    }

    public void setPap5Status(Long pap5Status) {
        this.pap5Status = pap5Status;
    }

    @Column(
            name = "PAP5_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap5RawValue() {
        return this.pap5RawValue;
    }

    public void setPap5RawValue(Double pap5RawValue) {
        this.pap5RawValue = pap5RawValue;
    }

    @Column(
            name = "PAP5_RAW_STATUS"
    )
    public Long getPap5RawStatus() {
        return this.pap5RawStatus;
    }

    public void setPap5RawStatus(Long pap5RawStatus) {
        this.pap5RawStatus = pap5RawStatus;
    }

    @Column(
            name = "RAP5_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap5Value() {
        return this.rap5Value;
    }

    public void setRap5Value(Double rap5Value) {
        this.rap5Value = rap5Value;
    }

    @Column(
            name = "RAP5_STATUS"
    )
    public Long getRap5Status() {
        return this.rap5Status;
    }

    public void setRap5Status(Long rap5Status) {
        this.rap5Status = rap5Status;
    }

    @Column(
            name = "RAP5_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap5RawValue() {
        return this.rap5RawValue;
    }

    public void setRap5RawValue(Double rap5RawValue) {
        this.rap5RawValue = rap5RawValue;
    }

    @Column(
            name = "RAP5_RAW_STATUS"
    )
    public Long getRap5RawStatus() {
        return this.rap5RawStatus;
    }

    public void setRap5RawStatus(Long rap5RawStatus) {
        this.rap5RawStatus = rap5RawStatus;
    }

    @Column(
            name = "PRP5_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp5Value() {
        return this.prp5Value;
    }

    public void setPrp5Value(Double prp5Value) {
        this.prp5Value = prp5Value;
    }

    @Column(
            name = "PRP5_STATUS"
    )
    public Long getPrp5Status() {
        return this.prp5Status;
    }

    public void setPrp5Status(Long prp5Status) {
        this.prp5Status = prp5Status;
    }

    @Column(
            name = "PRP5_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp5RawValue() {
        return this.prp5RawValue;
    }

    public void setPrp5RawValue(Double prp5RawValue) {
        this.prp5RawValue = prp5RawValue;
    }

    @Column(
            name = "PRP5_RAW_STATUS"
    )
    public Long getPrp5RawStatus() {
        return this.prp5RawStatus;
    }

    public void setPrp5RawStatus(Long prp5RawStatus) {
        this.prp5RawStatus = prp5RawStatus;
    }

    @Column(
            name = "RRP5_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp5Value() {
        return this.rrp5Value;
    }

    public void setRrp5Value(Double rrp5Value) {
        this.rrp5Value = rrp5Value;
    }

    @Column(
            name = "RRP5_STATUS"
    )
    public Long getRrp5Status() {
        return this.rrp5Status;
    }

    public void setRrp5Status(Long rrp5Status) {
        this.rrp5Status = rrp5Status;
    }

    @Column(
            name = "RRP5_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp5RawValue() {
        return this.rrp5RawValue;
    }

    public void setRrp5RawValue(Double rrp5RawValue) {
        this.rrp5RawValue = rrp5RawValue;
    }

    @Column(
            name = "RRP5_RAW_STATUS"
    )
    public Long getRrp5RawStatus() {
        return this.rrp5RawStatus;
    }

    public void setRrp5RawStatus(Long rrp5RawStatus) {
        this.rrp5RawStatus = rrp5RawStatus;
    }

    @Column(
            name = "PAP6_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap6Value() {
        return this.pap6Value;
    }

    public void setPap6Value(Double pap6Value) {
        this.pap6Value = pap6Value;
    }

    @Column(
            name = "PAP6_STATUS"
    )
    public Long getPap6Status() {
        return this.pap6Status;
    }

    public void setPap6Status(Long pap6Status) {
        this.pap6Status = pap6Status;
    }

    @Column(
            name = "PAP6_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPap6RawValue() {
        return this.pap6RawValue;
    }

    public void setPap6RawValue(Double pap6RawValue) {
        this.pap6RawValue = pap6RawValue;
    }

    @Column(
            name = "PAP6_RAW_STATUS"
    )
    public Long getPap6RawStatus() {
        return this.pap6RawStatus;
    }

    public void setPap6RawStatus(Long pap6RawStatus) {
        this.pap6RawStatus = pap6RawStatus;
    }

    @Column(
            name = "RAP6_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap6Value() {
        return this.rap6Value;
    }

    public void setRap6Value(Double rap6Value) {
        this.rap6Value = rap6Value;
    }

    @Column(
            name = "RAP6_STATUS"
    )
    public Long getRap6Status() {
        return this.rap6Status;
    }

    public void setRap6Status(Long rap6Status) {
        this.rap6Status = rap6Status;
    }

    @Column(
            name = "RAP6_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRap6RawValue() {
        return this.rap6RawValue;
    }

    public void setRap6RawValue(Double rap6RawValue) {
        this.rap6RawValue = rap6RawValue;
    }

    @Column(
            name = "RAP6_RAW_STATUS"
    )
    public Long getRap6RawStatus() {
        return this.rap6RawStatus;
    }

    public void setRap6RawStatus(Long rap6RawStatus) {
        this.rap6RawStatus = rap6RawStatus;
    }

    @Column(
            name = "PRP6_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp6Value() {
        return this.prp6Value;
    }

    public void setPrp6Value(Double prp6Value) {
        this.prp6Value = prp6Value;
    }

    @Column(
            name = "PRP6_STATUS"
    )
    public Long getPrp6Status() {
        return this.prp6Status;
    }

    public void setPrp6Status(Long prp6Status) {
        this.prp6Status = prp6Status;
    }

    @Column(
            name = "PRP6_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getPrp6RawValue() {
        return this.prp6RawValue;
    }

    public void setPrp6RawValue(Double prp6RawValue) {
        this.prp6RawValue = prp6RawValue;
    }

    @Column(
            name = "PRP6_RAW_STATUS"
    )
    public Long getPrp6RawStatus() {
        return this.prp6RawStatus;
    }

    public void setPrp6RawStatus(Long prp6RawStatus) {
        this.prp6RawStatus = prp6RawStatus;
    }

    @Column(
            name = "RRP6_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp6Value() {
        return this.rrp6Value;
    }

    public void setRrp6Value(Double rrp6Value) {
        this.rrp6Value = rrp6Value;
    }

    @Column(
            name = "RRP6_STATUS"
    )
    public Long getRrp6Status() {
        return this.rrp6Status;
    }

    public void setRrp6Status(Long rrp6Status) {
        this.rrp6Status = rrp6Status;
    }

    @Column(
            name = "RRP6_RAW_VALUE",
            precision = 20,
            scale = 5
    )
    public Double getRrp6RawValue() {
        return this.rrp6RawValue;
    }

    public void setRrp6RawValue(Double rrp6RawValue) {
        this.rrp6RawValue = rrp6RawValue;
    }

    @Column(
            name = "RRP6_RAW_STATUS"
    )
    public Long getRrp6RawStatus() {
        return this.rrp6RawStatus;
    }

    public void setRrp6RawStatus(Long rrp6RawStatus) {
        this.rrp6RawStatus = rrp6RawStatus;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VIEW_ID", unique = true, nullable = false)
    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }
}

