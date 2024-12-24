package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;

import com.tencent.wxcloudrun.dto.RecipeDTO;
import com.tencent.wxcloudrun.service.WeChatService;
import com.tencent.wxcloudrun.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 微信支付实现
 * @author
 * @date 2024-11-08 14:39:14
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class WeChatServiceImpl  implements WeChatService {

    @Autowired
    private WeChatPayConfig weChatPayConfig;
    private AtomicInteger count = new AtomicInteger();

    /**
     * 微信支付沙箱环境
     * @param request
     * @param orderInfoVO
     * @return
     */
    @Override
    public RecipeDTO qrCodePaySadBox(HttpServletRequest request, WeiXinEntity orderInfoVO) {
        RecipeDTO resultDTO = new RecipeDTO();
        return resultDTO;
    }

    @Override
    public RecipeDTO unifiedOrder(HttpServletRequest request, WeiXinEntity orderInfoVO) {
        RecipeDTO resultDTO = new RecipeDTO();
        //根据自己的业务逻辑自行处理 OrderInfo为我自己业务中的实体类
        String appId = weChatPayConfig.getAppId();
        String mchId = weChatPayConfig.getMchId();
        String mchKey = weChatPayConfig.getMchKey();
        String notifyUrl = weChatPayConfig.getNoticeUrl();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(orderInfoVO.getSerialId());
        //支付类型
        orderInfo.setPaymentType("3");
        //交易类型
        orderInfo.setTradeType("NATIVE");
        //支付金额（BigDecimal 例子：10.00）
        orderInfo.setPaymentPrice(orderInfoVO.getMoney());
        orderInfo.setName("订单信息");
        String body = orderInfo.getName();
        body = body.length() > 40 ? body.substring(0,39) : body;
        //更新编号防止不同终端微信报重复订单号
        orderInfo.setOrderNo(IdUtil.getSnowflake(0,0).nextIdStr());
        Map<String, String> req = new HashMap<>();
        //公众号
        req.put("appid", appId);
        // 商户号
        req.put("mch_id", mchId);
        // 32位随机字符串
        req.put("nonce_str", WXPayUtil.generateNonceStr());
        // 商品描述
        req.put("body", body);
        // 商户订单号
        req.put("out_trade_no", orderInfo.getOrderNo());
        // 标价金额(分)
//        req.put("total_fee", String.valueOf(MoneyUtils.yuanToFen(String.valueOf(orderInfo.getPaymentPrice()))));
        req.put("total_fee", orderInfo.getPaymentPrice());
        // 终端IP
        req.put("spbill_create_ip", request.getRemoteAddr());
        // 回调地址+携带的返回参数  domain 为配置的域名[不可为ip地址]
        req.put("notify_url", notifyUrl);
        // 交易类型
        req.put("trade_type", "NATIVE");
        // 签名
        req.put("attach", String.valueOf(mchId)); //orderInfoVO.getTenantId()
        try {
            // 签名
            req.put("sign", WXPayUtil.generateSignature(req, mchKey, WXPayConstants.SignType.MD5));
            String xmlBody = WXPayUtil.generateSignedXml(req, mchKey);
            System.err.println(String.format("微信支付预下单请求 xml 格式:\n%s", xmlBody));
            String result = WxChatPayCommonUtil.httpsRequest(WeChatPayUrl.Uifiedorder, "POST", xmlBody);
            System.err.println(String.format("%s", result));
            Map<String, String> wxResultMap = WXPayUtil.xmlToMap(result);
            wxResultMap.put("orderNo", orderInfo.getOrderNo());
            if (ObjectUtil.isNotEmpty(wxResultMap.get("return_code")) && wxResultMap.get("return_code").equals("SUCCESS")) {
                if (wxResultMap.get("result_code").equals("SUCCESS")) {
                    System.out.println("预下单成功");
                    System.out.println("QrCode："+ wxResultMap.get("code_url"));
                    String qrCodeUrl = wxResultMap.get("code_url");
                    //生成二维码，返回给前端
                    BufferedImage generate = QrCodeUtil.generate(qrCodeUrl, 300, 300);
                    //转换流信息写出
                    FastByteArrayOutputStream os = new FastByteArrayOutputStream();
                    try {
                        ImageIO.write(generate, "jpg", os);
                    } catch (IOException e) {
                        e.getMessage();
                    }
                    //如果二维码要在前端显示需要转成Base64
                    //前端展示 <img id="qrCodeUrl" src='data:img/jpg;base64,${base64Img }' style="display: none"/>
                    resultDTO.setQrCodeUrl(Base64.getEncoder().encodeToString(os.toByteArray()));
                    //返回回调成功信息
                    resultDTO.setBjje(orderInfo.getPaymentPrice());
                    return  resultDTO;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String getCode(HttpServletRequest request, HttpServletResponse response) {
        String appid = weChatPayConfig.getAppId();
        String access_Url = "wechatpay.authorize";
        String redirect_uri = request.getParameter("redirect_uri");
        System.out.println("前端传进来的redirect_uri>>>>>>>:" + redirect_uri);
        try {
            redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("URLEncode 转码后redirect_uri>>>>>>>:" + redirect_uri);
        //首先获取code值
        String backUri = access_Url + "?appid=" + appid + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
        System.out.println("获取code值 对应的链接地址：" + backUri);
        try {
            response.sendRedirect(backUri);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  backUri;
    }

    /**
     * 支付回调
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String payNotifyPhone(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("-----进入支付回调-------------------============----");
        Map<String, String> notifyMap = baseNotify(request);
        if (notifyMap == null || !"0000".equals(notifyMap.get("errCode"))) {
            return null;
        }
        String bOrderId = notifyMap.get("bOrderId");//订单号
        String total_fee = notifyMap.get("total_fee");//订单金额
        String wexinOrderId = notifyMap.get("wexinOrderId");//微信订单号
        String timeEnd = notifyMap.get("timeEnd");//支付完成时间
        String openId = notifyMap.get("openId");

//    	String bOrderId = "12345678";//订单号
//		String total_fee = "23";//订单金额
//		String wexinOrderId = "1234";//微信订单号
//		String timeEnd = "2018-4-12";//支付完成时间
//		String openId = "111111111111111";
        System.out.println("支付成功 获取openid>>>>>>>>>>>>>>>>>>>" + openId);
//		applicationContent.publishEvent(new WeiXinNotifyEvent(this, bOrderId, total_fee, wexinOrderId, timeEnd));
//		returnTOWX(response);
        String succXML = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg></return_msg></xml>";
        /*******************************************************/
        /**
         * 业务开始与微信回调无关
         */
        /*******************************************************/
        //支付成功 记录支付状态
//		ToyotoPayinfo toyotoPayInfos = toyotoPayInfoService.findByOpenid(openId);
//		if(toyotoPayInfos == null){
//			ToyotoPayinfo payInfo = new ToyotoPayinfo();
//			payInfo.setPaynum(total_fee);
//			payInfo.setOpenid(openId);
//			payInfo.setPayOrderNo(bOrderId);//订单号
//			payInfo.setWeixinOrderNo(wexinOrderId);//微信订单号
        //先查询当前计数器count值
        System.out.println("开始获取当前计数器的count值>>>>>>>>>>>>>>>>>>>>>>");
        int atomic = 0;
        System.out.println("开始获取当前计数器的count值>>>>>>>>>>>>>>>>>>>>>>" + atomic);
        //调用线程安全给当前计数器count值加1
        count = new AtomicInteger(atomic);
        this.safeCountPlus(count);
        int currnum = count.get();
        System.out.println("调用线程安全给当前计数器count值加1之后>>>>>>>>>>>>>>>>>>>>" + currnum);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, +1);
        //支付完成 根据openid查询是否存在分享记录 如果不存在就新增 存在就不新增
        /*******************************************************/
        /**
         * 业务结束
         */
        /*******************************************************/
        //System.out.println(DateUtils.formatDate(toyotoRule.getStarttime(), "yyyy-MM-dd HH:mm:ss"));
        succXML = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg></return_msg></xml>";
        System.out.println("支付完成返回的xml数据>>>>>>>>>>" + succXML);
        System.out.println("-------发送给微信---");
//		}
        return succXML;
    }

    private Map<String, String> baseNotify(HttpServletRequest request) {
        try {
            Map<String, String> resultMap = new HashMap<String, String>();
            System.out.println("支付成功返回的request>>>>>>>>>>>>>>" + request);
            Map<String, String> respMap = parseXml(request);
            System.out.println("支付成功返回的respMap>>>>>>>>>>>>>>>" + respMap);
            String return_code = respMap.get("return_code");
            System.out.println("支付成功 *********************开始回调$$$$$$$$$$$$$$$$$$$$$$-----");
            System.out.println("return_code=======================" + return_code);
            String errMessage = "";
            if (!"SUCCESS".equals(return_code)) {
                errMessage = "return_code返回[失败]";
                resultMap.put("errCode", "4001");
                return resultMap;
            }
            //商户唯一标识
            String appidw = respMap.get("appid");
//			String appidv = PathConfigUtil.getBasePath("wechatpay.appid");
            String appidv = "wechatpay.appid";
            System.out.println("appidw[" + appidw + "]----appidv[" + appidv + "]");
            if (!appidw.equals(appidv)) {
                errMessage = "appid不一致";
                resultMap.put("errCode", "4002");
                return resultMap;
            }
            resultMap.put("errCode", "0000");
            //业务订单号
            String bOrderId = respMap.get("out_trade_no");
            resultMap.put("bOrderId", bOrderId);
            //订单金额
            int total_fee = Integer.parseInt(respMap.get("total_fee"));
            resultMap.put("total_fee", total_fee + "");
            //微信支付订单号
            String wexinOrderId = respMap.get("transaction_id");
            resultMap.put("wexinOrderId", wexinOrderId);
            //订单支付完成时间
            String timeEnd = respMap.get("time_end");
            resultMap.put("timeEnd", timeEnd);
            //支付订单的openId
            String openId = respMap.get("openid");
            System.out.println("获取微信支付的openid>>>>>>>>>>>>>>>>" + openId);
            resultMap.put("openId", openId);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> parseXml(HttpServletRequest request) {
        InputStream inputStream = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            StringBuilder sbd = new StringBuilder();
            // 解析结果存储在HashMap
            inputStream = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            if (elementList.size() > 0) {
                for (Element e : elementList) {
                    map.put(e.getName(), e.getText());
                    sbd.append("[").append(e.getName()).append(":").append(e.getText()).append("]").append(",");
                }
                map.put("wxMessage", sbd.deleteCharAt(sbd.length() - 1).toString());
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    public void safeCountPlus(AtomicInteger count) {
        for (; ; ) {
            int i = count.get();
            // 如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值
            boolean suc = count.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

}
