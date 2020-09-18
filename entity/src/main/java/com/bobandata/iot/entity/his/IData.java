package com.bobandata.iot.entity.his;

import com.bobandata.iot.util.TimestampDeserializer;
import com.bobandata.iot.util.TimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 18:40 2019/1/15.
 */
@MappedSuperclass
public class IData {
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp occurTime;
    private Long meterId;

    @Column(name = "OCCUR_TIME", nullable = false, length = 19)
    public Timestamp getOccurTime() {
        return occurTime;
    }
    public void setOccurTime(Timestamp occurTime) {
        this.occurTime = occurTime;
    }
    @Column(name = "METER_ID", nullable = false)
    public Long getMeterId() {
        return meterId;
    }
    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

}
