package com.bobandata.iot.entity.dms;

import com.bobandata.iot.util.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: zhanglingzhi
 * @Description:    参数表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:17 2018/7/18.
 */
@Entity
public class Param {

//    PARAM_ID	信息体ID	Int	20	是
//    PARAM_NAME	信息体名称	varchar	100	否
//    PARAM_TYPE	信息体类型	int	6	否
//    IS_LIMIT_VALID	是否限制有效值	int	11	否
//    LIMIT_VALID_INTERVAL	限制有效间隔	int	11	否
//    LOW_LIMIT	下限	float		否
//    UP_LIMIT	上限	float		否
//    LIMIT_VALID_TIME_TAG	限制有效值的标签	timestamp		否

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paramId;
    @Column(name = "PARAM_NAME")
    private String paramName;
    @Column(name = "PARAM_TYPE")
    private Integer paramType;
    @Column(name = "IS_LIMIT_VALID")
    private Integer isLimitValid;
    @Column(name = "LIMIT_VALID_INTERVAL")
    private Integer limitValidInterval;
    @Column(name = "LOW_LIMIT")
    private Float lowLimit;
    @Column(name = "UP_LIMIT")
    private Float upLimit;
    @Column(name = "LIMIT_VALID_TIME_TAG")
    @JsonSerialize(using = DateTimeSerializer.class)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date limitValidTimeTag;

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
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
