package com.tencent.wxcloudrun.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String client
 *
 * Created by YouHan on 2019-09-11 08:57:56
 * Copyright © 2019 YouHan All rights reserved.
 */
public class YouStringUtil {

    /**
     * 下划线
     */
    public static final Pattern LINE = Pattern.compile("_(\\w)");

    /**
     * 驼峰
     */
    public static final Pattern HUMP = Pattern.compile("[A-Z]");

    /**
     * 添加内容
     *
     * @param content
     * @param length
     * @return java.lang.String
     * @author YouHan
     * @date 2021/6/17 9:59
     */
    public static String appendContent(String content, int length) {
        if (length <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i ++) {
            sb.append(content);
        }

        return sb.toString();
    }

    /**
     * 添加前缀内容
     *
     * @param s
     * @param content
     * @param length
     * @return java.lang.String
     * @date 2019-08-12 09:53
     * @author YouHan
     */
    public static String appendPrefixContent(String s, String content, int length) {

        if (length <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < length; i ++) {
            sb.append(content, 0, content.length());
        }

        return sb.toString();
    }

    /**
     * 添加后缀内容
     *
     * @param s
     * @param content
     * @param length
     * @return java.lang.String
     * @date 2019-08-12 09:56
     * @author YouHan
     */
    public static String appendSuffixContent(String s, String content, int length) {
        if (length <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < length; i ++) {
            sb.append(content);
        }

        return sb.toString();
    }

    /**
     * Set 转 String
     *
     * @param stringSet
     * @return java.lang.String
     * @author YouHan
     * @date 2021/6/3 9:26
     */
    public static String setToString(Set<String> stringSet) {
        return setToString(stringSet, null);
    }

    /**
     * Set 转 String
     *
     * @param stringSet
     * @param regex
     * @return java.lang.String
     * @date 2021/6/3 9:21
     * @author YouHan
     */
    public static String setToString(Set<String> stringSet, String regex) {
        // 参数校验
        if (CollectionUtils.isEmpty(stringSet)) {
            return null;
        }
        if (StringUtils.isBlank(regex)) {
            regex = ",";
        }

        // List to String
        StringBuilder sb = new StringBuilder(stringSet.size());
        for (String s : stringSet) {
            sb.append(s).append(regex);
        }

        // 返回结果
        return sb.substring(0, sb.length() - 1);
    }

    /**
     *  字符串列表转字符串
     *
     * @author YouHan
     * @generatedDate: 2018/10/9 17:25
     * @param stringList 要转换的字符串列表
     * @return
     */
    public static String listToString(List<String> stringList) {
        return listToString(stringList, null);
    }

    /**
     *  字符串列表转字符串
     *
     * @author YouHan
     * @generatedDate: 2018/10/9 17:25
     * @param stringList 要转换的字符串列表
     * @return
     */
    public static String listToString(List<String> stringList, String regex) {
        // 参数校验
        if (CollectionUtils.isEmpty(stringList)) {
            return null;
        }
        if (StringUtils.isBlank(regex)) {
            regex = ",";
        }

        // List to String
        StringBuilder sb = new StringBuilder(stringList.size());
        for (String s : stringList) {
            sb.append(s).append(regex);
        }

        // 返回结果
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 字符串转列表
     *
     * @param s
     * @return java.client.List<java.lang.String>
     * @date 2019-09-11 09:11
     * @author YouHan
     */
    public static List<String> stringToList(String s) {
        /**
         * 参数校验
         */
        if (StringUtils.isBlank(s)) {
            return null;
        }

        return stringToList(s, null);
    }

    /**
     * 字符串转列表
     *
     * @param s
     * @param regex 分割规则，默认为逗号
     * @return java.client.List<java.lang.String>
     * @date 2019-09-11 09:11
     * @author YouHan
     */
    public static List<String> stringToList(String s, String regex) {
        /**
         * 参数校验
         */
        if (StringUtils.isBlank(s)) {
            return null;
        }

        /**
         * 默认逗号隔开
         */
        if (StringUtils.isBlank(regex)) {
            regex = ",";
        }

        /**
         * 去除首尾空格
         */
        String blankString = " ";
        while (s.startsWith(blankString)) {
            s = s.substring(1);
        }
        while (s.endsWith(blankString)) {
            s = s.substring(0, s.length() -1);
        }

        /**
         * 返回结果列表
         */
        List<String> resultList = new ArrayList<>();

        /**
         * 只有单个元素
         */
        if (!s.contains(regex)) {
            resultList.add(s);
            return resultList;
        }

        String[] strings = s.split(regex);
        for (String e : strings) {
            resultList.add(e);
        }

        return resultList;
    }

    /**
     * 过滤逗号
     * @param s
     * @return
     */
    public static String filterCommaString(String s) {
        // 数据为空校验
        if (StringUtils.isEmpty(s)) {
            return null;
        }

        // 去除 并列逗号
        s = s.replace(",,", ",");

        // 去除 首逗号
        if (s.startsWith(",")) {
            s = s.substring(1, s.length() - 1);
        }

        // 去除 尾逗号
        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }

    /**
     * 是否包含中文（包括中文标点符号和空格）
     *
     * @param s
     * @return boolean
     * @date 2020/9/9 13:30
     * @author YouHan
     */
    public static Boolean isContainChinese(String s) {
        /**
         * 参数校验
         */
        if (StringUtils.isBlank(s)) {
            return false;
        }

        if (s.contains(" ")) {
            return true;
        }

        /**
         * 中文正则表达式
         */
        String regex = "[\u4e00-\u9fa5]";

        if (s.matches(regex)) {
            return Boolean.TRUE;
        }

        /**
         * 中文标点符号处理
         */
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (isChinesePunctuation(c)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 过滤中文（包括标点符号和空格）
     *
     * @param s
     * @return java.lang.String
     * @date 2020/9/9 14:08
     * @author YouHan
     */
    public static String filterChinese(String s) {
        /**
         * 参数校验
         */
        if (StringUtils.isBlank(s)) {
            return "";
        }

        s = s.replace(" ", "");

        if (!isContainChinese(s)) {
            return s;
        }

        /**
         * 过滤中文字符
         */
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder(chars.length);
        for (char c : chars) {
            if (isContainChinese(String.valueOf(c))) {
                continue;
            }
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * 判断是否为中文标点符号
     *
     * @param c
     * @return java.lang.Boolean
     * @date 2020/9/9 13:43
     * @author YouHan
     */
    public static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
            return true;
        }

        return false;
    }

    /**
     * 获取 UUID
     *
     * @param
     * @return java.lang.String
     * @date 2021/4/9 14:08
     * @author YouHan
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 安全比较（可防止时序攻击 timing attack）
     */
    public static boolean safeEqual(String a, String b) {
        if (StringUtils.isBlank(a) || StringUtils.isBlank(b)) {
            return false;
        }
        if (a.length() != b.length()) {
            return false;
        }
        int equal = 0;
        for (int i = 0; i < a.length(); i++) {
            equal |= a.charAt(i) ^ b.charAt(i);
        }
        return equal == 0;
    }

    /**
     * 驼峰转下划线
     *
     * @param s
     * @return java.lang.String
     * @date 2021/5/6 22:20
     * @author YouHan
     */
    public static String humpToLine(String s) {
        Matcher matcher = HUMP.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        if (sb.toString().startsWith("_")) {
            sb.deleteCharAt(0);
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param s
     * @return java.lang.String
     * @date 2021/5/6 22:21
     * @author YouHan
     */
    public static String lineToHump(String s) {
        s = s.toLowerCase();
        Matcher matcher = LINE.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 生成加密的内容
     *
     * @param s
     * @return java.lang.String
     * @author YouHan
     * @date 2021/6/17 10:10
     */
    public static String hide(String s) {
        /**
         * 1
         * 1*
         * 1**
         * 1***
         * 1***5
         * 12***6
         * 12***67
         * 123***78
         * 123***789
         * 123****890
         * 123*****8901
         */
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }

        if (s.length() == 2) {
            return s.substring(0, 1) + "*";
        }

        if (s.length() == 3 || s.length() == 4) {
            return s.substring(0, 1) + appendContent("*", s.length() - 1);
        }

        if (s.length() == 5) {
            return s.substring(0, 1) + "***" + s.substring(4);
        }

        if (s.length() == 6 || s.length() == 7) {
            return s.substring(0, 2) + appendContent("*", 3) + s.substring(5);
        }

        if (s.length() == 8) {
            return s.substring(0, 3) + "***" + s.substring(6);
        }

        return s.substring(0, 3) + appendContent("*", s.length() - 6) + s.substring(s.length() - 3);
    }
}

