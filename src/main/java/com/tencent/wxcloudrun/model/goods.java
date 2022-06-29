package com.tencent.wxcloudrun.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class goods {
    private String id;

    private String goodName;

    private Double price;

    private String describe;

    private String supplier;

    private String goodsImg;

    private String message;

    private String firstImg;

    private Boolean recommend;

    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Boolean goodsShow;

    private String typeId;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }
    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getGoodsShow() {
        return goodsShow;
    }

    public void setGoodsShow(Boolean goodsShow) {
        this.goodsShow = goodsShow;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "goods{" +
                "id='" + id + '\'' +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                ", describe='" + describe + '\'' +
                ", supplier='" + supplier + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", message='" + message + '\'' +
                ", firstImg='" + firstImg + '\'' +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", goodsShow=" + goodsShow +
                ", typeId='" + typeId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
