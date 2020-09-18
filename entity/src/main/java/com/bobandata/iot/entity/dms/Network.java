package com.bobandata.iot.entity.dms;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zhanglingzhi
 * @Description:    通道表（网络通信参数）
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:23 2018/7/17.
 */
@Entity
public class Network {

//    CHANNEL_ID			通道ID				Int			20	是
//    CHANNEL_NAME			通道名称			varchar		100	否
//    STATUS				状态				int			10	否
//    IP_ADDRESS			IP地址				varchar		20	否
//    IP_PORT				访问端口			Int			10	否
//    LAST_SUCCESS_TIME_TAG	最后一次链接时间	timestamp		否
//    ERTU_ID				终端ID				Int			20	否


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer channelId;
    @Column(name = "CHANNEL_NAME")
    private String channelName;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    @Column(name = "IP_PORT")
    private Integer ipPort;
    @Column(name = "LAST_SUCCESS_TIME_TAG")
    private Date lastSuccessTimeTag;
    @Column(name = "ERTU_ID")
    private Integer ertuId;


    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getIpPort() {
        return ipPort;
    }

    public void setIpPort(Integer ipPort) {
        this.ipPort = ipPort;
    }

    public Date getLastSuccessTimeTag() {
        return lastSuccessTimeTag;
    }

    public void setLastSuccessTimeTag(Date lastSuccessTimeTag) {
        this.lastSuccessTimeTag = lastSuccessTimeTag;
    }

    public Integer getErtuId() {
        return ertuId;
    }

    public void setErtuId(Integer ertuId) {
        this.ertuId = ertuId;
    }


}
