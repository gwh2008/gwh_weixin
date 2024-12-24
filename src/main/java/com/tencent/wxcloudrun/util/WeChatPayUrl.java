package com.tencent.wxcloudrun.util;

/**
 * 微信支付接口地址
 *
 */
public class WeChatPayUrl {
    //统一下单预下单接口url
    public static final String Uifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //订单状态查询接口URL
    public static final String Orderquery = "https://api.mch.weixin.qq.com/pay/orderquery";
    //订单申请退款
    public static final String Refund = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    //付款码 支付
    public static final String MicroPay = "https://api.mch.weixin.qq.com/pay/micropay";

    //二维码支付沙箱环境
    public static final String qrCodePaySadBox = "https://api.mch.weixin.qq.com/xdc/apiv2sandbox/pay/micropay";

    //微信网页授权 获取“code”请求地址
    public static final String GainCodeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";

}

