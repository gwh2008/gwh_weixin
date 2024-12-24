package com.tencent.wxcloudrun.util;


import lombok.Data;

@Data

public class OrderInfo {

    private String id;
    private String paymentType;
    private String tradeType;
    private String paymentPrice;
    private String name;
    private String orderNo;
}
