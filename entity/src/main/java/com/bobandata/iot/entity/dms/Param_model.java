package com.bobandata.iot.entity.dms;

import javax.persistence.*;

/**
 * @Author: zhanglingzhi
 * @Description:    参数模板表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:17 2018/7/18.
 */
@Entity
public class Param_model {

//    MODEL_ID	模板ID	Int	10
//    MODEL_NAME	模板名称	varchar	20
//    MODEL_DESC	模板描述	int	10

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer modelId;
    @Column(name = "MODEL_NAME")
    private String modelName;
    @Column(name = "MODEL_DESC")
    private String modelDesc;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }
}
