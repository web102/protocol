package com.bobandata.iot.entity.dms;

import javax.persistence.*;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 14:22 2018/7/17.
 */

@Entity
public class InstructProtocolSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer protocolId;
    @Column
    private Integer instructId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public Integer getInstructId() {
        return instructId;
    }

    public void setInstructId(Integer instructId) {
        this.instructId = instructId;
    }
}
