package com.bobandata.iot.entity.dms;

import javax.persistence.*;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:03 2018/7/16.
 */
@Entity
public class Protocol{
//PROTOCOL_ID		规约id			int		10
//PROTOCOL_NAME		规约名称		varchar	20
//PROTOCOL_ALIAS	规约描述		varchar	20
//REST_PATH			规约路径		varchar	20
//VERSION_NAME		版本			verchar	20

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer protocolId;
    @Column(name="PROTOCOL_NAME")
    private String protocolName;
    @Column(name="PROTOCOL_ALIAS")
    private String protocolAlias;
    @Column(name="REST_PATH")
    private String restPath;
    @Column(name="VERSION_NAME")
    private String versionName;

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolAlias() {
        return protocolAlias;
    }

    public void setProtocolAlias(String protocolAlias) {
        this.protocolAlias = protocolAlias;
    }

    public String getRestPath() {
        return restPath;
    }

    public void setRestPath(String restPath) {
        this.restPath = restPath;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
