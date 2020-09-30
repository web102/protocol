package com.bobandata.iot.util;

import lombok.Data;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:50 2018/8/6.
 */
@Data
public class SimpleTree {
    private Integer id;
    private String title;
    private Integer modelId;
    private Integer rootId;
    private List<SimpleTree> children;


    public SimpleTree(){
    }
    public SimpleTree(Integer id, String title){
        this.title = title;
        this.id=id;
    }
    public SimpleTree(Integer id, String title, Integer modelId){
        this.title = title;
        this.id=id;
        this.modelId=modelId;
    }

    public SimpleTree(Integer id,String title, Integer modelId, Integer rootId) {
        this.title = title;
        this.id = id;
        this.modelId = modelId;
        this.rootId = rootId;
    }

    public SimpleTree(Integer id,String title,  Integer modelId, Integer rootId, List<SimpleTree> children) {
        this.title = title;
        this.id = id;
        this.modelId = modelId;
        this.rootId = rootId;
        this.children = children;
    }
}