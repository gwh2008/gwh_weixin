package com.tencent.wxcloudrun.util;


import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 数字 client
 *
 * Created by YouHan on 2020-09-09 13:28:40
 * Copyright © 2021 YouHan All rights reserved.
 */
public class YouNumberUtil {
    /**
     * 整数正则表达式
     */
    public static final String INTEGER_REGEX = "^[-\\+]?[\\d]*$";

    /**
     * 数字正则表达式
     */
    public static final String NUMBER_REGEX = "^-?\\d+(\\.\\d+)?$";

    /**
     * 是否是整数
     *
     * @param s
     * @return java.lang.Boolean
     * @date 2020/9/12 8:38
     * @author YouHan
     */
    public static boolean isInteger(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }

        return s.matches(INTEGER_REGEX);
    }

    /**
     * 判断给定字符串是否为十六进制数
     *
     * @param value 值
     * @return 是否为十六进制
     */
    public static boolean isHex(String value) {
        final int index = (value.startsWith("-") ? 1 : 0);
        if (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index)) {
            try {
                Long.decode(value);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }

        return false;
    }

    /**
     * 是否是数字（包括小数）
     *
     * @param s
     * @return java.lang.Boolean
     * @date 2020/9/9 14:01
     * @author YouHan
     */
    public static boolean isNumber(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }

        return s.matches(NUMBER_REGEX);
    }

    /**
     * 十进制转十六进制
     *
     * @param n 十进制数
     * @return java.lang.String
     * @date 2019/4/8 09:22
     * @author YouHan
     */
    public static String intToHex(Integer n) {
        if (null == n) {
            return null;
        }

        return String.format("%X", n);
    }

    /**
     * 十进制转十六进制
     *
     * @param n 十进制数
     * @return java.lang.String
     * @date 2019/4/8 09:22
     * @author YouHan
     */
    public static String longToHex(Long n) {
        if (null == n) {
            return null;
        }

        return String.format("%X", n);
    }

    /**
     * 十进制转十六进制
     *
     * @param n
     * @param length
     * @return java.lang.String
     * @date 2019-08-12 09:56
     * @author YouHan
     */
    public static String intToHexPrefix(Integer n, Integer length) {
        if (null == n) {
            return null;
        }
        if (null == length || length <= 0) {
            return null;
        }

        String result = String.format("%X", n);
        if (result.length() < length) {
            result = YouStringUtil.appendPrefixContent(result, "0", length - result.length());
        }

        return result;
    }

    /**
     * 十进制转十六进制
     *
     * @param n
     * @param length
     * @return java.lang.String
     * @date 2019-08-12 09:56
     * @author YouHan
     */
    public static String longToHexPrefix(Long n, Integer length) {
        if (null == n) {
            return null;
        }
        if (null == length || length <= 0) {
            return null;
        }

        String result = String.format("%X", n);
        if (result.length() < length) {
            result = YouStringUtil.appendPrefixContent(result, "0", length - result.length());
        }

        return result;
    }

    /**
     * 十进制转十六进制
     *
     * @param n 十进制数
     * @return java.lang.String
     * @date 2019/4/8 09:22
     * @author YouHan
     */
    public static String intToHexSuffix(Integer n, Integer length) {
        if (null == n) {
            return null;
        }
        if (null == length || length <= 0) {
            return null;
        }

        String result = String.format("%X", n);
        if (result.length() < length) {
            result = YouStringUtil.appendSuffixContent(result, "0", length - result.length());
        }

        return result;
    }

    /**
     * 十进制转十六进制
     *
     * @param n 十进制数
     * @return java.lang.String
     * @date 2019/4/8 09:22
     * @author YouHan
     */
    public static String longToHexSuffix(Long n, Integer length) {
        if (null == n) {
            return null;
        }
        if (null == length || length <= 0) {
            return null;
        }

        String result = String.format("%X", n);
        if (result.length() < length) {
            result = YouStringUtil.appendSuffixContent(result, "0", length - result.length());
        }

        return result;
    }

    /**
     * 十六进制转十进制
     *
     * @param hex
     * @return java.lang.Integer
     * @date 2019/4/8 09:49
     * @author YouHan
     */
    public static Integer hexToInt(String hex) {
        Long n = hexToLong(hex);
        if (null == n) {
            return null;
        }

        // 超出整数最大值，不予处理
        if (Integer.MAX_VALUE < n) {
            return null;
        }

        return Integer.valueOf(String.valueOf(n));
    }

    /**
     * 十六进制转十进制
     *
     * @param hex
     * @return java.lang.Integer
     * @date 2019/4/8 09:49
     * @author YouHan
     */
    public static Long hexToLong(String hex) {
        if (StringUtils.isBlank(hex)) {
            return null;
        }

        // 去除前缀为 0 的 十六进制
        if (hex.length() > 1 && hex.startsWith("0")) {
            hex = hex.substring(1);
        }

        return Long.valueOf(hex, 16);
    }

    /**
     * 字符串转十六进制
     *
     * @param s
     * @return
     */
    public static String stringToHex(String s) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = s.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制转字符串
     *
     * @param s
     * @return
     */
    public static String hexToString(String s) {
        String str = "0123456789ABCDEF";
        char[] hexs = s.toCharArray();
        byte[] bytes = new byte[s.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }

        return new String(bytes);
    }

    /**
     * 去除末尾多余的 0
     *
     * @param s
     * @return java.lang.String
     * @author YouHan
     * @date 2021/7/2 15:39
     */
    public static String stripTrailingZeros(String s) {
        if (StringUtils.isBlank(s)) {
            return "0";
        }

        if (!isNumber(s)) {
            return "0";
        }

        if (!s.contains(".")) {
            return s;
        }

        while (s.endsWith("0")) {
            s = s.substring(0, s.length() - 1);
        }
        if (s.endsWith(".")) {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }

    /**
     * int 转 String
     * 1024以内高效，超出后，正常转换
     */
    static int cacheSize = 1024;
    static String[] caches = new String[cacheSize];

    static {
        for (int i = 0; i < cacheSize; i++) {
            caches[i] = String.valueOf(i);
        }
    }

    public static String int2String(int data) {
        if (data < cacheSize) {
            return caches[data];
        }
        return String.valueOf(data);
    }

    /**
     * 获取几位的 int 随机数
     *
     * @param length
     * @return int
     * @author YouHan
     * @date 2021/12/19
     */
    public static int getRandomInt(int length) {
        return (int) ((Math.random() * 9 + 1) * 10 * length);
    }

    /**
     * 获取几位的 long 随机数
     *
     * @param length
     * @return long
     * @author YouHan
     * @date 2021/12/19
     */
    public static long getRandomLong(long length) {
        return (long) ((Math.random() * 9 + 1) * 10 * length);
    }

    /**
     * 获取随机数
     *
     * @param
     * @return java.client.concurrent.ThreadLocalRandom
     * @author YouHan
     * @date 2021/6/3 10:29
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获取缓存穿透时间（单位秒），最长不超过 5 分钟
     *
     * @param
     * @return java.lang.Long
     * @date 2021/4/26 9:50
     * @author YouHan
     */
    public static Long getCachePenetrationTime() {
        return Long.valueOf(int2String(getRandom().nextInt(300)));
    }

    /**
     * 获取数据库缓存时间（单位秒），最长不超过 1 小时
     *
     * @param
     * @return java.lang.Long
     * @date 2021/4/26 9:50
     * @author YouHan
     */
    public static Long getDBCacheTime() {
        return Long.valueOf(int2String(getRandom().nextInt(3600)));
    }

    /**
     * 十六进制高低位转换
     *
     * @param hexString
     * @return java.lang.String
     * @author YouHan
     * @date 2021/12/11
     */
    public static String hexHighLowPositionConvert(String hexString) {
        if (StringUtils.isBlank(hexString) || hexString.length() % 2 != 0) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        for (int i = hexString.length() - 2; i >= 0; i = i - 2) {
            result.append(hexString.substring(i, i + 2));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
    }
}

