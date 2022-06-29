package com.tencent.wxcloudrun.utils;

import java.util.List;

public class PageDto<T> {
    /*
    当前页面码
     */
    private int pageNumber;
    /*
    页面记录数
     */
    private int pageSize;
    /*
    总条数
     */
    private int totalRecord;
    /*
    总页数
     */
    private int totalPage;
    /*
    数据集合
     */
    private List<T> list;

    public PageDto(int pageNumber,int pageSize,int totalRecord){
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        //计算总页数
        if(totalRecord % pageSize ==0){
            totalPage = totalRecord/pageSize;
        }else{
            totalPage = totalRecord/pageSize+1;
        }

    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {

        this.list = list;
    }
}
