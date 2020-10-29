package com.bobandata.iot.entity.dms;

import javax.persistence.*;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:29 2018/7/17.
 */

@Entity
public class Instruct {

//INSTRUCT_ID	 指令ID		int		10
//INSTRUCT_NAME	 指令名称	varchar	20
//INSTRUCT_TYPE	 指令类型	varchar	20
//INSTRUCT_PATH  指令路径	int		10
//INSTRUCT_FRAME 指令结构	int 	10

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer instructId;

    @Column(name="INSTRUCT_NAME")
    private String instructName;
    @Column(name="INSTRUCT_TYPE")
    private String instructType;
    @Column(name="INSTRUCT_PATH")
    private Integer instructPath;
    @Column(name="INSTRUCT_FRAME")
    private Integer instructFrame;

    public Integer getInstructId() {
        return instructId;
    }

    public void setInstructId(Integer instructId) {
        this.instructId = instructId;
    }

    public String getInstructName() {
        return instructName;
    }

    public void setInstructName(String instructName) {
        this.instructName = instructName;
    }

    public String getInstructType() {
        return instructType;
    }

    public void setInstructType(String instructType) {
        this.instructType = instructType;
    }

    public Integer getInstructPath() {
        return instructPath;
    }

    public void setInstructPath(Integer instructPath) {
        this.instructPath = instructPath;
    }

    public Integer getInstructFrame() {
        return instructFrame;
    }

    public void setInstructFrame(Integer instructFrame) {
        this.instructFrame = instructFrame;
    }
}
