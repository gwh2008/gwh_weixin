package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.RecipeDTO;
import com.tencent.wxcloudrun.util.WeiXinEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 微信支付接口
 *
 * @author
 * @date 2024-11-08 14:39:14
 */
public interface WeChatService  {


    /**
     * 微信支付沙箱环境
     * @param request
     * @param orderInfoVO
     * @return
     */
    RecipeDTO qrCodePaySadBox(HttpServletRequest request, WeiXinEntity orderInfoVO);

    /**
     * 调用统一下单接口
     * @param orderInfoVO
     * @return
     */
    RecipeDTO unifiedOrder(HttpServletRequest request, WeiXinEntity orderInfoVO);

    String getCode(HttpServletRequest request, HttpServletResponse response);

    String payNotifyPhone(HttpServletRequest request, HttpServletResponse response);

}
