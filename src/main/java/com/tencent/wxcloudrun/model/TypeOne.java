package com.tencent.wxcloudrun.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TypeOne {

    private String id;

    private String typeName;

    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String remark;

    private Boolean show;

    @Override
    public String toString() {
        return "TypeOne{" +
                "id='" + id + '\'' +
                ", typeName='" + typeName + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", show=" + show +
                '}';
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
