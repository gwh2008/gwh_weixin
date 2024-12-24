package com.tencent.wxcloudrun.util;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WeChatPayConfig {

    @Value("${wechatpay.appid}")
    private String appId;

    @Value("${wechatpay.api_key}")
    private String apiKey;

    @Value("${wechatpay.mch_id}")
    private String mchId;

    @Value("${wechatpay.mch_key}")
    private String mchKey;

    @Value("${wechatpay.noticeUrl}")
    private String noticeUrl;

}
