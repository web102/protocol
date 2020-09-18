package com.bobandata.iot.util;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:50 2018/8/6.
 */
public class SimpleTree {
    private String title;
    private boolean selected=false;
    private Integer id;
    private Integer modelId;
    private List<SimpleTree> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public List<SimpleTree> getChildren() {
        return children;
    }

    public void setChildren(List<SimpleTree> children) {
        this.children = children;
    }

    public SimpleTree(){
    }
    public SimpleTree(Integer id, String title,boolean selected){
        this.title = title;
        this.selected=selected;
        this.id=id;
    }
    public SimpleTree(Integer id, String title, Integer modelId,boolean selected){
        this.title = title;
        this.selected=selected;
        this.id=id;
        this.modelId=modelId;
    }
}