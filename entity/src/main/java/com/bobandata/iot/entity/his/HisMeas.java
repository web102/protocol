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
 * @Date: Created in 18:48 2019/1/15.
 */
@Entity
@Table(name = "his_meas")
public class HisMeas extends IData {
    private Double pValue;
    private Long pStatus;
    private Double qValue;
    private Long qStatus;
    private Double iaValue;
    private Long iaStatus;
    private Double uaValue;
    private Long uaStatus;
    private Double paValue;
    private Long paStatus;
    private Double qaValue;
    private Long qaStatus;
    private Double ibValue;
    private Long ibStatus;
    private Double ubValue;
    private Long ubStatus;
    private Double pbValue;
    private Long pbStatus;
    private Double qbValue;
    private Long qbStatus;
    private Double icValue;
    private Long icStatus;
    private Double ucValue;
    private Long ucStatus;
    private Double pcValue;
    private Long pcStatus;
    private Double qcValue;
    private Long qcStatus;
    private Double factorValue;
    private Long factorStatus;
    private Double freqValue;
    private Long freqStatus;
    private Double uQrValue;
    private Long uQrStatus;
    private Double iQrValue;
    private Long iQrStatus;
    private Double pRawValue;
    private Long pRawStatus;
    private Double qRawValue;
    private Long qRawStatus;
    private Double iaRawValue;
    private Long iaRawStatus;
    private Double uaRawValue;
    private Long uaRawStatus;
    private Double paRawValue;
    private Long paRawStatus;
    private Double qaRawValue;
    private Long qaRawStatus;
    private Double ibRawValue;
    private Long ibRawStatus;
    private Double ubRawValue;
    private Long ubRawStatus;
    private Double pbRawValue;
    private Long pbRawStatus;
    private Double qbRawValue;
    private Long qbRawStatus;
    private Double icRawValue;
    private Long icRawStatus;
    private Double ucRawValue;
    private Long ucRawStatus;
    private Double pcRawValue;
    private Long pcRawStatus;
    private Double qcRawValue;
    private Long qcRawStatus;
    private Double factorRawValue;
    private Long factorRawStatus;
    private Double freqRawValue;
    private Long freqRawStatus;
    private Double uQrRawValue;
    private Long uQrRawStatus;
    private Double iQrRawValue;
    private Long iQrRawStatus;
    private Long measId;

    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp lastRefreshTime;


    @Column(name = "P_VALUE")
    public Double getpValue() {
        return pValue;
    }
    public void setpValue(Double pValue) {
        this.pValue = pValue;
    }

    @Column(name = "P_STATUS")
    public Long getpStatus() {
        return pStatus;
    }
    public void setpStatus(Long pStatus) {
        this.pStatus = pStatus;
    }

    @Column(name = "Q_VALUE")
    public Double getqValue() {
        return qValue;
    }
    public void setqValue(Double qValue) {
        this.qValue = qValue;
    }

    @Column(name = "Q_STATUS")
    public Long getqStatus() {
        return qStatus;
    }
    public void setqStatus(Long qStatus) {
        this.qStatus = qStatus;
    }

    @Column(name = "IA_VALUE")
    public Double getIaValue() {
        return iaValue;
    }
    public void setIaValue(Double iaValue) {
        this.iaValue = iaValue;
    }

    @Column(name = "IA_STATUS")
    public Long getIaStatus() {
        return iaStatus;
    }
    public void setIaStatus(Long iaStatus) {
        this.iaStatus = iaStatus;
    }
    @Column(name = "UA_VALUE")
    public Double getUaValue() {
        return uaValue;
    }
    public void setUaValue(Double uaValue) {
        this.uaValue = uaValue;
    }
    @Column(name = "UA_STATUS")
    public Long getUaStatus() {
        return uaStatus;
    }
    public void setUaStatus(Long uaStatus) {
        this.uaStatus = uaStatus;
    }
    @Column(name = "PA_VALUE")
    public Double getPaValue() {
        return paValue;
    }
    public void setPaValue(Double paValue) {
        this.paValue = paValue;
    }
    @Column(name = "PA_STATUS")
    public Long getPaStatus() {
        return paStatus;
    }
    public void setPaStatus(Long paStatus) {
        this.paStatus = paStatus;
    }
    @Column(name = "QA_VALUE")
    public Double getQaValue() {
        return qaValue;
    }
    public void setQaValue(Double qaValue) {
        this.qaValue = qaValue;
    }
    @Column(name = "QA_STATUS")
    public Long getQaStatus() {
        return qaStatus;
    }
    public void setQaStatus(Long qaStatus) {
        this.qaStatus = qaStatus;
    }
    @Column(name = "IB_VALUE")
    public Double getIbValue() {
        return ibValue;
    }
    public void setIbValue(Double ibValue) {
        this.ibValue = ibValue;
    }
    @Column(name = "IB_STATUS")
    public Long getIbStatus() {
        return ibStatus;
    }
    public void setIbStatus(Long ibStatus) {
        this.ibStatus = ibStatus;
    }
    @Column(name = "UB_VALUE")
    public Double getUbValue() {
        return ubValue;
    }
    public void setUbValue(Double ubValue) {
        this.ubValue = ubValue;
    }
    @Column(name = "UB_STATUS")
    public Long getUbStatus() {
        return ubStatus;
    }
    public void setUbStatus(Long ubStatus) {
        this.ubStatus = ubStatus;
    }
    @Column(name = "PB_VALUE")
    public Double getPbValue() {
        return pbValue;
    }
    public void setPbValue(Double pbValue) {
        this.pbValue = pbValue;
    }
    @Column(name = "PB_STATUS")
    public Long getPbStatus() {
        return pbStatus;
    }
    public void setPbStatus(Long pbStatus) {
        this.pbStatus = pbStatus;
    }
    @Column(name = "QB_VALUE")
    public Double getQbValue() {
        return qbValue;
    }
    public void setQbValue(Double qbValue) {
        this.qbValue = qbValue;
    }
    @Column(name = "QB_STATUS")
    public Long getQbStatus() {
        return qbStatus;
    }
    public void setQbStatus(Long qbStatus) {
        this.qbStatus = qbStatus;
    }
    @Column(name = "IC_VALUE")
    public Double getIcValue() {
        return icValue;
    }
    public void setIcValue(Double icValue) {
        this.icValue = icValue;
    }
    @Column(name = "IC_STATUS")
    public Long getIcStatus() {
        return icStatus;
    }
    public void setIcStatus(Long icStatus) {
        this.icStatus = icStatus;
    }
    @Column(name = "UC_VALUE")
    public Double getUcValue() {
        return ucValue;
    }
    public void setUcValue(Double ucValue) {
        this.ucValue = ucValue;
    }
    @Column(name = "UC_STATUS")
    public Long getUcStatus() {
        return ucStatus;
    }
    public void setUcStatus(Long ucStatus) {
        this.ucStatus = ucStatus;
    }
    @Column(name = "PC_VALUE")
    public Double getPcValue() {
        return pcValue;
    }
    public void setPcValue(Double pcValue) {
        this.pcValue = pcValue;
    }
    @Column(name = "PC_STATUS")
    public Long getPcStatus() {
        return pcStatus;
    }
    public void setPcStatus(Long pcStatus) {
        this.pcStatus = pcStatus;
    }
    @Column(name = "QC_VALUE")
    public Double getQcValue() {
        return qcValue;
    }
    public void setQcValue(Double qcValue) {
        this.qcValue = qcValue;
    }
    @Column(name = "QC_STATUS")
    public Long getQcStatus() {
        return qcStatus;
    }
    public void setQcStatus(Long qcStatus) {
        this.qcStatus = qcStatus;
    }
    @Column(name = "FACTOR_VALUE")
    public Double getFactorValue() {
        return factorValue;
    }
    public void setFactorValue(Double factorValue) {
        this.factorValue = factorValue;
    }
    @Column(name = "FACTOR_STATUS")
    public Long getFactorStatus() {
        return factorStatus;
    }
    public void setFactorStatus(Long factorStatus) {
        this.factorStatus = factorStatus;
    }
    @Column(name = "FREQ_VALUE")
    public Double getFreqValue() {
        return freqValue;
    }
    public void setFreqValue(Double freqValue) {
        this.freqValue = freqValue;
    }
    @Column(name = "FREQ_STATUS")
    public Long getFreqStatus() {
        return freqStatus;
    }
    public void setFreqStatus(Long freqStatus) {
        this.freqStatus = freqStatus;
    }
    @Column(name = "U_QR_VALUE")
    public Double getuQrValue() {
        return uQrValue;
    }
    public void setuQrValue(Double uQrValue) {
        this.uQrValue = uQrValue;
    }
    @Column(name = "U_QR_STATUS")
    public Long getuQrStatus() {
        return uQrStatus;
    }
    public void setuQrStatus(Long uQrStatus) {
        this.uQrStatus = uQrStatus;
    }
    @Column(name = "I_QR_VALUE")
    public Double getiQrValue() {
        return iQrValue;
    }
    public void setiQrValue(Double iQrValue) {
        this.iQrValue = iQrValue;
    }
    @Column(name = "I_QR_STATUS")
    public Long getiQrStatus() {
        return iQrStatus;
    }
    public void setiQrStatus(Long iQrStatus) {
        this.iQrStatus = iQrStatus;
    }
    @Column(name = "P_RAW_VALUE")
    public Double getpRawValue() {
        return pRawValue;
    }
    public void setpRawValue(Double pRawValue) {
        this.pRawValue = pRawValue;
    }
    @Column(name = "P_RAW_STATUS")
    public Long getpRawStatus() {
        return pRawStatus;
    }
    public void setpRawStatus(Long pRawStatus) {
        this.pRawStatus = pRawStatus;
    }
    @Column(name = "Q_RAW_VALUE")
    public Double getqRawValue() {
        return qRawValue;
    }
    public void setqRawValue(Double qRawValue) {
        this.qRawValue = qRawValue;
    }
    @Column(name = "Q_RAW_STATUS")
    public Long getqRawStatus() {
        return qRawStatus;
    }
    public void setqRawStatus(Long qRawStatus) {
        this.qRawStatus = qRawStatus;
    }
    @Column(name = "IA_RAW_VALUE")
    public Double getIaRawValue() {
        return iaRawValue;
    }
    public void setIaRawValue(Double iaRawValue) {
        this.iaRawValue = iaRawValue;
    }
    @Column(name = "IA_RAW_STATUS")
    public Long getIaRawStatus() {
        return iaRawStatus;
    }
    public void setIaRawStatus(Long iaRawStatus) {
        this.iaRawStatus = iaRawStatus;
    }
    @Column(name = "UA_RAW_VALUE")
    public Double getUaRawValue() {
        return uaRawValue;
    }
    public void setUaRawValue(Double uaRawValue) {
        this.uaRawValue = uaRawValue;
    }
    @Column(name = "UA_RAW_STATUS")
    public Long getUaRawStatus() {
        return uaRawStatus;
    }
    public void setUaRawStatus(Long uaRawStatus) {
        this.uaRawStatus = uaRawStatus;
    }
    @Column(name = "PA_RAW_VALUE")
    public Double getPaRawValue() {
        return paRawValue;
    }
    public void setPaRawValue(Double paRawValue) {
        this.paRawValue = paRawValue;
    }
    @Column(name = "PA_RAW_STATUS")
    public Long getPaRawStatus() {
        return paRawStatus;
    }
    public void setPaRawStatus(Long paRawStatus) {
        this.paRawStatus = paRawStatus;
    }
    @Column(name = "QA_RAW_VALUE")
    public Double getQaRawValue() {
        return qaRawValue;
    }
    public void setQaRawValue(Double qaRawValue) {
        this.qaRawValue = qaRawValue;
    }
    @Column(name = "QA_RAW_STATUS")
    public Long getQaRawStatus() {
        return qaRawStatus;
    }
    public void setQaRawStatus(Long qaRawStatus) {
        this.qaRawStatus = qaRawStatus;
    }
    @Column(name = "IB_RAW_VALUE")
    public Double getIbRawValue() {
        return ibRawValue;
    }
    public void setIbRawValue(Double ibRawValue) {
        this.ibRawValue = ibRawValue;
    }
    @Column(name = "IB_RAW_STATUS")
    public Long getIbRawStatus() {
        return ibRawStatus;
    }
    public void setIbRawStatus(Long ibRawStatus) {
        this.ibRawStatus = ibRawStatus;
    }
    @Column(name = "UB_RAW_VALUE")
    public Double getUbRawValue() {
        return ubRawValue;
    }
    public void setUbRawValue(Double ubRawValue) {
        this.ubRawValue = ubRawValue;
    }
    @Column(name = "UB_RAW_STATUS")
    public Long getUbRawStatus() {
        return ubRawStatus;
    }
    public void setUbRawStatus(Long ubRawStatus) {
        this.ubRawStatus = ubRawStatus;
    }
    @Column(name = "PB_RAW_VALUE")
    public Double getPbRawValue() {
        return pbRawValue;
    }
    public void setPbRawValue(Double pbRawValue) {
        this.pbRawValue = pbRawValue;
    }
    @Column(name = "PB_RAW_STATUS")
    public Long getPbRawStatus() {
        return pbRawStatus;
    }
    public void setPbRawStatus(Long pbRawStatus) {
        this.pbRawStatus = pbRawStatus;
    }
    @Column(name = "QB_RAW_VALUE")
    public Double getQbRawValue() {
        return qbRawValue;
    }
    public void setQbRawValue(Double qbRawValue) {
        this.qbRawValue = qbRawValue;
    }
    @Column(name = "QB_RAW_STATUS")
    public Long getQbRawStatus() {
        return qbRawStatus;
    }
    public void setQbRawStatus(Long qbRawStatus) {
        this.qbRawStatus = qbRawStatus;
    }
    @Column(name = "IC_RAW_VALUE")
    public Double getIcRawValue() {
        return icRawValue;
    }
    public void setIcRawValue(Double icRawValue) {
        this.icRawValue = icRawValue;
    }
    @Column(name = "IC_RAW_STATUS")
    public Long getIcRawStatus() {
        return icRawStatus;
    }
    public void setIcRawStatus(Long icRawStatus) {
        this.icRawStatus = icRawStatus;
    }
    @Column(name = "UC_RAW_VALUE")
    public Double getUcRawValue() {
        return ucRawValue;
    }
    public void setUcRawValue(Double ucRawValue) {
        this.ucRawValue = ucRawValue;
    }
    @Column(name = "UC_RAW_STATUS")
    public Long getUcRawStatus() {
        return ucRawStatus;
    }
    public void setUcRawStatus(Long ucRawStatus) {
        this.ucRawStatus = ucRawStatus;
    }
    @Column(name = "PC_RAW_VALUE")
    public Double getPcRawValue() {
        return pcRawValue;
    }
    public void setPcRawValue(Double pcRawValue) {
        this.pcRawValue = pcRawValue;
    }
    @Column(name = "PC_RAW_STATUS")
    public Long getPcRawStatus() {
        return pcRawStatus;
    }
    public void setPcRawStatus(Long pcRawStatus) {
        this.pcRawStatus = pcRawStatus;
    }
    @Column(name = "QC_RAW_VALUE")
    public Double getQcRawValue() {
        return qcRawValue;
    }
    public void setQcRawValue(Double qcRawValue) {
        this.qcRawValue = qcRawValue;
    }
    @Column(name = "QC_RAW_STATUS")
    public Long getQcRawStatus() {
        return qcRawStatus;
    }
    public void setQcRawStatus(Long qcRawStatus) {
        this.qcRawStatus = qcRawStatus;
    }
    @Column(name = "FACTOR_RAW_VALUE")
    public Double getFactorRawValue() {
        return factorRawValue;
    }
    public void setFactorRawValue(Double factorRawValue) {
        this.factorRawValue = factorRawValue;
    }
    @Column(name = "FACTOR_RAW_STATUS")
    public Long getFactorRawStatus() {
        return factorRawStatus;
    }
    public void setFactorRawStatus(Long factorRawStatus) {
        this.factorRawStatus = factorRawStatus;
    }
    @Column(name = "FREQ_RAW_VALUE")
    public Double getFreqRawValue() {
        return freqRawValue;
    }
    public void setFreqRawValue(Double freqRawValue) {
        this.freqRawValue = freqRawValue;
    }
    @Column(name = "FREQ_RAW_STATUS")
    public Long getFreqRawStatus() {
        return freqRawStatus;
    }
    public void setFreqRawStatus(Long freqRawStatus) {
        this.freqRawStatus = freqRawStatus;
    }
    @Column(name = "U_QR_RAW_VALUE")
    public Double getuQrRawValue() {
        return uQrRawValue;
    }
    public void setuQrRawValue(Double uQrRawValue) {
        this.uQrRawValue = uQrRawValue;
    }
    @Column(name = "U_QR_RAW_STATUS")
    public Long getuQrRawStatus() {
        return uQrRawStatus;
    }
    public void setuQrRawStatus(Long uQrRawStatus) {
        this.uQrRawStatus = uQrRawStatus;
    }
    @Column(name = "I_QR_RAW_VALUE")
    public Double getiQrRawValue() {
        return iQrRawValue;
    }
    public void setiQrRawValue(Double iQrRawValue) {
        this.iQrRawValue = iQrRawValue;
    }
    @Column(name = "I_QR_RAW_STATUS")
    public Long getiQrRawStatus() {
        return iQrRawStatus;
    }
    public void setiQrRawStatus(Long iQrRawStatus) {
        this.iQrRawStatus = iQrRawStatus;
    }
    @Column(name = "LAST_REFRESH_TIME")
    public Timestamp getLastRefreshTime() {
        return lastRefreshTime;
    }
    public void setLastRefreshTime(Timestamp lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEAS_ID")
    public Long getMeasId() {
        return measId;
    }
    public void setMeasId(Long measId) {
        this.measId = measId;
    }
}
