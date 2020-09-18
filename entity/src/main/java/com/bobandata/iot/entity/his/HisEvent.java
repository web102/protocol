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
 * @Date: Created in 18:50 2019/1/15.
 */
@Entity
@Table(name = "his_event")
public class HisEvent extends IData {
    private Short eventType;
    private Short eventFlag;
    private String content;
    private Long eventId;
    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp lastRefreshTime;


    @Column(name = "EVENT_TYPE")
    public Short getEventType() {
        return eventType;
    }

    public void setEventType(Short eventType) {
        this.eventType = eventType;
    }

    @Column(name = "EVENT_FLAG")
    public Short getEventFlag() {
        return eventFlag;
    }

    public void setEventFlag(Short eventFlag) {
        this.eventFlag = eventFlag;
    }

    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name="EVENT_ID")
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
