package com.bobandata.iot.util;

public class TaskParam {
    private int startMark;
    private int endMark;
    private String startDate;
    private String endDate;
    private byte taskType;
    private String ipAddress;
    private int ipPort;
    private String restPath;
    private Integer channelId;
    private Integer instructId;
    private Integer protocolId;
    private String LinkAddress;

    public int getStartMark() {
        return this.startMark;
    }

    public void setStartMark(int startMark) {
        this.startMark = startMark;
    }

    public int getEndMark() {
        return this.endMark;
    }

    public void setEndMark(int endMark) {
        this.endMark = endMark;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public byte getTaskType() {
        return this.taskType;
    }

    public void setTaskType(byte taskType) {
        this.taskType = taskType;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getInstructId() {
        return instructId;
    }

    public void setInstructId(Integer instructId) {
        this.instructId = instructId;
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getIpPort() {
        return ipPort;
    }

    public void setIpPort(int ipPort) {
        this.ipPort = ipPort;
    }

    public String getRestPath() {
        return restPath;
    }

    public void setRestPath(String restPath) {
        this.restPath = restPath;
    }

    public String getLinkAddress() {
        return LinkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        LinkAddress = linkAddress;
    }
}