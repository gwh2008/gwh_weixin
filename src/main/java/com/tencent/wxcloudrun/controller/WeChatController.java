package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.service.WeChatService;
import com.tencent.wxcloudrun.util.R;
import com.tencent.wxcloudrun.util.WeiXinEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 微信支付控制类
 */
@Slf4j
@RequestMapping("/wechat")
@RestController
public class WeChatController {
    @Resource
    private WeChatService weChatService;


    /**
     * 调用统一下单接口，并组装生成支付所需参数对象.
     *
     * @param orderInfoVO 统一下单请求参数
     */
    @PostMapping("/unifiedOrder")
    public R unifiedOrder(HttpServletRequest request, @RequestBody WeiXinEntity orderInfoVO) {
        return R.ok(weChatService.unifiedOrder(request, orderInfoVO));
    }

    /**
     * 支付回调
     * @param request
     * @param response
     * @return
     */
//    @ResponseBody
    @RequestMapping(value = "/payNotifyPhone", method = RequestMethod.POST, produces = "application/xhtml+xml")
    public R  payNotifyPhone(HttpServletRequest request, HttpServletResponse response) {
        return R.ok(weChatService.payNotifyPhone(request, response));
    }





}
