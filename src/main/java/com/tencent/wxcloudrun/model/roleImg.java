package com.tencent.wxcloudrun.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class roleImg {
    private String id;
    private String rotaName;
    private String rotaUrl;
    private boolean rotaShow;
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String rotaSort;

    public String getId() {
        return id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRotaName() {
        return rotaName;
    }

    public void setRotaName(String rotaName) {
        this.rotaName = rotaName;
    }

    public String getRotaUrl() {
        return rotaUrl;
    }

    public void setRotaUrl(String rotaUrl) {
        this.rotaUrl = rotaUrl;
    }

    public boolean isRotaShow() {
        return rotaShow;
    }

    public void setRotaShow(boolean rotaShow) {
        this.rotaShow = rotaShow;
    }


    public String getRotaSort() {
        return rotaSort;
    }

    public void setRotaSort(String rotaSort) {
        this.rotaSort = rotaSort;
    }

    @Override
    public String toString() {
        return "roleImg{" +
                "id='" + id + '\'' +
                ", rotaName='" + rotaName + '\'' +
                ", rotaUrl='" + rotaUrl + '\'' +
                ", rotaShow=" + rotaShow +
                ", createTime=" + createTime +
                ", rotaSort=" + rotaSort +
                '}';
    }
}
