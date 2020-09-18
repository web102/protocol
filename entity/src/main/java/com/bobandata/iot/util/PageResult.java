package com.bobandata.iot.util;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2019/1/10 17:18
 * @Description:
 */

public class PageResult {
    private Integer code;
    private String msg;
    private Object data;         //数据
    private Long pageCount;      //总页数
    private Long pageSize;       //每页数据条数
    private Long pageIndex;      //单前页数
    private Long pojiTotalCount; //数据总条数
    private Long startIndex;     //本页起始数

    public PageResult(){
    }
    
    public PageResult(Integer code, String msg, Object data, Long pageCount, Long pageSize, Long pageIndex, Long pojiTotalCount, Long startIndex ){
        this.code=code;
        this.msg=msg;
        this.data=data;
        this.pageCount=pageCount;
        this.pageSize=pageSize;
        this.pageIndex=pageIndex;
        this.pojiTotalCount=pojiTotalCount;
        this.startIndex=startIndex;
    }

    public PageResult(Object data){
        this.code=0;
        this.msg = "success";
        if(!(data instanceof Boolean)){
            this.data=data;
        }
    }

    public PageResult(Integer code , String msg){
        this.code=code;
        this.msg =msg;
    }

    public PageResult(Object data, Long pageCount, Long pageSize, Long pageIndex, Long pojiTotalCount, Long startIndex){
        this.code = 1;
        this.msg = "success";
        this.data=data;
        this.pageCount=pageCount;
        this.pageSize=pageSize;
        this.pageIndex = pageIndex;
        this.pojiTotalCount=pojiTotalCount;
        this.startIndex = startIndex;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPojiTotalCount() {
        return pojiTotalCount;
    }

    public void setPojiTotalCount(Long pojiTotalCount) {
        this.pojiTotalCount = pojiTotalCount;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }
}