package com.bobandata.iot.entity.dms;

import javax.persistence.*;

/**
 * @Author: zhanglingzhi
 * @Description:    参数模板中间表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:17 2018/7/18.
 */
@Entity
public class Param_model_set {
//    ID	中间表ID	int	10	是
//    PARAM_ID	信息体ID	int	10	否
//    MODEL_ID	模型ID	int	10	否

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "PARAM_ID")
    private Integer paramId;
    @Column(name = "MODEL_ID")
    private Integer modelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }
}
