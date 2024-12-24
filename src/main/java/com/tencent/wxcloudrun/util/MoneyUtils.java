package com.tencent.wxcloudrun.util;


import java.math.BigDecimal;

/**
 * 钱 工具类
 *
 * Created by YouHan on 2019-06-28 09:12:00
 * Copyright © 2019 YouHan All rights reserved.
 */
public class MoneyUtils {
    public static final String YUAN = "元";
    public static final String FEN = "分";

    /**
     * 元转分
     *
     * @param s
     * @return java.lang.Integer
     * @date 2020/9/10 9:03
     * @author YouHan
     */
    public static Integer yuanToFen(String s) {
        if (!YouNumberUtil.isNumber(s)) {
            return 0;
        }

        return new BigDecimal(s).multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 处理分
     *
     * @param s
     * @return java.lang.Integer
     * @author YouHan
     * @date 2022/7/23
     */
    public static Integer handleFen(String s) {
        if (!YouNumberUtil.isNumber(s)) {
            return 0;
        }

        return new BigDecimal(s).intValue();
    }

    /**
     * 分转元
     *      可以为正负小数（这里保留2位小数）
     *
     * @param s
     * @return java.lang.String
     * @date 2020/9/10 9:01
     * @author YouHan
     */
    public static String fenToYuan(String s) {
        if (!YouNumberUtil.isNumber(s) || "0".equals(s) || "-0".equals(s)) {
            return "0.00";
        }

        return new BigDecimal(s)
                .divide(new BigDecimal(100))
                .setScale(2, BigDecimal.ROUND_DOWN)
                .toString();
    }

    /**
     * 处理元
     *      可以为正负小数（这里保留2位小数）
     *
     * @param s
     * @return java.lang.String
     * @author YouHan
     * @date 2022/7/23
     */
    public static String handleYuan(String s) {
        if (!YouNumberUtil.isNumber(s) || "0".equals(s) || "-0".equals(s)) {
            return "0.00";
        }

        return new BigDecimal(s)
                .setScale(2, BigDecimal.ROUND_DOWN)
                .toString();
    }

    public static void main(String[] args) {
        System.out.println(yuanToFen("10.00"));
    }
}

