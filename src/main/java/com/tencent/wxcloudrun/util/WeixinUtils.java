package com.tencent.wxcloudrun.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import com.thinkgem.jeesite.common.utils.PathConfigUtil;

public class WeixinUtils {
    public static JSONObject getWeiXinEntity(HttpServletRequest request,
                                             String url) {
        //WeiXinEntity wx = new WeiXinEntity();
        JSONObject json = new JSONObject();
        ServletContext context = request.getSession().getServletContext();
        String accessToken = (String) context.getAttribute("accessToken");
        long accessTokenExpireIn = 0;
        if (context.getAttribute("accessTokenExpireIn") != null && !"".equals(context.getAttribute("accessTokenExpireIn"))) {
            long cacheTime = (Long) context.getAttribute("accessTokenExpireIn");
            //long cacheTime = Long.parseLong(currtime);
            accessTokenExpireIn = cacheTime;
        }
        // String access_token = getAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            // accessToken = getAccessToken();
            Map<String, String> map = getAccessToken();
            accessToken = map.get("access_token");
            context.setAttribute("accessToken", accessToken);
            System.out.println("当前时间的毫秒秒数>>>>>>>>>>"
                    + System.currentTimeMillis());
//			context.setAttribute(
//					"accessTokenExpireIn",
//					System.currentTimeMillis()
//							+ (Long.valueOf(map.get("expires_in")) * 1000));
            long currMills = System.currentTimeMillis();
            long limitMills = 6000 * 1000;
            accessTokenExpireIn = currMills + limitMills;
            context.setAttribute(
                    "accessTokenExpireIn", accessTokenExpireIn);//100分钟过期
            System.out.println("获取超时时间的毫秒秒数>>>>>>>>>" + accessTokenExpireIn);
//			System.out.println(DateUtils.formatDate(new Date(),
//					"yyyy-MM-dd HH:mm:ss")
//					+ "-------accessToken 超时，重新获取--------" + accessToken);
        } else if (System.currentTimeMillis() >= accessTokenExpireIn && accessTokenExpireIn != 0) {
            Map<String, String> map = getAccessToken();
            context.removeAttribute("accessToken");
            context.removeAttribute("accessTokenExpireIn");
            accessToken = map.get("access_token");
            context.setAttribute("accessToken", accessToken);
            System.out.println("当前时间的毫秒秒数>>>>>>>>>>"
                    + System.currentTimeMillis());
//			context.setAttribute(
//					"accessTokenExpireIn",
//					System.currentTimeMillis()
//							+ (Long.valueOf(map.get("expires_in")) * 1000));
            long currMills = System.currentTimeMillis();
            long limitMills = 6000 * 1000;
            accessTokenExpireIn = currMills + limitMills;
            context.setAttribute(
                    "accessTokenExpireIn",
                    accessTokenExpireIn);//100分钟过期
//			context.setAttribute(
//					"accessTokenExpireIn",
//					System.currentTimeMillis()
//							+ 600 * 1000);//10分钟过期
            System.out.println("获取超时时间的毫秒秒数>>>>>>>>>" + accessTokenExpireIn);
            System.out.println(new Date()
                    + "-------accessToken 超时，重新获取--------" + accessToken);
        }
        System.out.println("accessToken>>>>>>>>>>>>>>>>>>>>" + accessToken);
        System.out.println("accessTokenExpireIn>>>>>>>>>>>>>>>>>>>>" + accessTokenExpireIn);
        // String ticket = getTicket(access_token);
        String jsapiTicket = (String) context.getAttribute("jsapiTicket");
        long jsapiTicketExpireIn = 0;
        if (context.getAttribute("jsapiTicketExpireIn") != null && !"".equals(context.getAttribute("jsapiTicketExpireIn"))) {
            long cacheTime = (Long) context.getAttribute("jsapiTicketExpireIn");
            //long cacheTime = Long.parseLong(currtime);
            jsapiTicketExpireIn = cacheTime;
        }
        if (StringUtils.isEmpty(jsapiTicket)) {
            //jsapiTicket = getTicket(accessToken);
            Map<String, String> map = getTicket(accessToken);
            jsapiTicket = map.get("ticket");
            context.setAttribute("jsapiTicket", jsapiTicket);
            System.out.println("获取Ticket当前的毫秒数>>>>>>>>>>>>>" + System.currentTimeMillis());
            long currMills = System.currentTimeMillis();
            long limitMills = 6000 * 1000;
            jsapiTicketExpireIn = currMills + limitMills;
//			context.setAttribute("jsapiTicketExpireIn",System.currentTimeMillis()
//							+ (Long.valueOf(map.get("expire_in")) * 1000));
            context.setAttribute("jsapiTicketExpireIn", jsapiTicketExpireIn);//设置100分钟过期
            System.out.println("获取Ticket超时时间的毫秒秒数>>>>>>>>>" + jsapiTicketExpireIn);
//			System.out.println(DateUtils.formatDate(new Date(),
//					"yyyy-MM-dd HH:mm:ss")
//					+ "-------jsapiTicket 超时，重新获取--------" + jsapiTicket);
            // System.out.println(DateUtils.format(new Date(),
            // DateUtils.DATETIME_FORMAT)
            // + "-------jsapiTicket 超时，重新获取--------" + jsapiTicket);
        } else if (System.currentTimeMillis() > jsapiTicketExpireIn && jsapiTicketExpireIn != 0) {
            Map<String, String> map = getTicket(accessToken);
            jsapiTicket = map.get("ticket");
            context.setAttribute("jsapiTicket", jsapiTicket);
            System.out.println("获取Ticket当前的毫秒数>>>>>>>>>>>>>" + System.currentTimeMillis());
            long currMills = System.currentTimeMillis();
            long limitMills = 6000 * 1000;
            jsapiTicketExpireIn = currMills + limitMills;
//			context.setAttribute("jsapiTicketExpireIn",System.currentTimeMillis()
//							+ (Long.valueOf(map.get("expire_in")) * 1000));
            context.setAttribute("jsapiTicketExpireIn", jsapiTicketExpireIn);//设置100分钟过期
            System.out.println("获取Ticket超时时间的毫秒秒数>>>>>>>>>" + jsapiTicketExpireIn);
            System.out.println(new Date()
                    + "-------jsapiTicket 超时，重新获取--------" + jsapiTicket);
        }
        System.out.println("jsapiTicket>>>>>>>>>>>" + jsapiTicket);
        System.out.println("jsapiTicketExpireIn>>>>>>>>>>>" + jsapiTicketExpireIn);
        Map<String, String> ret = SignUtil.sign(jsapiTicket, url);
        // System.out.println(ret.toString());
        //json.put("",(ret.get("jsapi_ticket"));
        json.put("signature", ret.get("signature"));
        json.put("nonceStr", ret.get("nonceStr"));
        json.put("timestamp", ret.get("timestamp"));
//		json.put("appId", PathConfigUtil.getBasePath("pay.appid"));
        json.put("appId", "pay.appid");
        System.out.println("\n\n" + ret.toString() + "\n\n");
        return json;
    }

    // public static Map<String, String> setShareConfig(
    // HttpServletRequest request, String url) throws Exception {
    // ServletContext context = request.getSession().getServletContext();
    // String accessToken = (String) context.getAttribute("accessToken");
    // if (StringUtils.isEmpty(accessToken)) {
    // accessToken = getAccessToken();
    // context.setAttribute("accessToken", accessToken);
    // System.out.println(DateUtils.format(new Date(),
    // DateUtils.DATETIME_FORMAT)
    // + "-------accessToken 超时，重新获取--------" + accessToken);
    // }
    // String jsapiTicket = (String) context.getAttribute("jsapiTicket");
    // if (StringUtils.isEmpty(jsapiTicket)) {
    // jsapiTicket = getTicket(accessToken);
    // context.setAttribute("jsapiTicket", jsapiTicket);
    // System.out.println(DateUtils.format(new Date(),
    // DateUtils.DATETIME_FORMAT)
    // + "-------jsapiTicket 超时，重新获取--------" + jsapiTicket);
    // }
    // Map<String, String> ret = sign(jsapiTicket, url);
    // request.setAttribute("url", ret.get("url"));
    // request.setAttribute("jsapi_ticket", ret.get("jsapi_ticket"));
    // request.setAttribute("nonceStr", ret.get("nonceStr"));
    // request.setAttribute("timestamp", ret.get("timestamp"));
    // request.setAttribute("signature", ret.get("signature"));
    // request.setAttribute("appId", appId);
    // return ret;
    // };
    // 获取token
    private static Map<String, String> getAccessToken() {
        Map<String, String> map = new HashMap<String, String>();
        String expires_in = "";
        String access_token = "";
        String grant_type = "client_credential";// 获取access_token填写client_credential
//		String AppId = PathConfigUtil.getBasePath("pay.appid");// 第三方用户唯一凭证
//		String secret = PathConfigUtil.getBasePath("pay.appsecret");// 第三方用户唯一凭证密钥，即appsecret
        String AppId = "pay.appid";// 第三方用户唯一凭证
        String secret = "pay.appsecret";// 第三方用户唯一凭证密钥，即appsecret
        // 这个url链接地址和参数皆不能变
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="
                + grant_type + "&appid=" + AppId + "&secret=" + secret; // 访问链接
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet
                    .openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            /*
             * System.setProperty("sun.net.client.defaultConnectTimeout",
             * "30000");// 连接超时30秒
             * System.setProperty("sun.net.client.defaultReadTimeout", "30000");
             * // 读取超时30秒
             */
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            System.out.println("获取accesstoken串>>>>>>>>>>" + message);
            // JSONObject demoJson = JSONObject.fromObject(message);
            JSONObject demoJson = JSONObject.parseObject(message);
            access_token = demoJson.getString("access_token");
            expires_in = demoJson.getString("expires_in");
            map.put("access_token", access_token);
            map.put("expires_in", expires_in);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 获取ticket
    private static Map<String, String> getTicket(String access_token) {
        String expire_in = null;
        String ticket = null;
        Map<String, String> map = new HashMap<String, String>();
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                + access_token + "&type=jsapi";// 这个url链接和参数不能变
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet
                    .openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            //System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            //System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            System.out.println("获取ticket串>>>>>>>>>>>" + ticket);
            // JSONObject demoJson = JSONObject.fromObject(message);
            JSONObject demoJson = JSONObject.parseObject(message);
            ticket = demoJson.getString("ticket");
            expire_in = demoJson.getString("expires_in");
            map.put("ticket", ticket);
            map.put("expire_in", expire_in);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String args[]) {
        long a = 121234234;
        long b = System.currentTimeMillis();
        System.out.println(a + b);
    }
}
