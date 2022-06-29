package com.tencent.wxcloudrun.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Journal {
    private String id;

    private String title;

    private String content;

    private String journalImg;

    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Boolean show;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJournalImg() {
        return journalImg;
    }

    public void setJournalImg(String journalImg) {
        this.journalImg = journalImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", journalImg='" + journalImg + '\'' +
                ", createTime=" + createTime +
                ", show=" + show +
                '}';
    }
}
