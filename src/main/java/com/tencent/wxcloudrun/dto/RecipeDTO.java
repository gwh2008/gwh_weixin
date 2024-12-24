package com.tencent.wxcloudrun.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO
 *
 * @author
 * @date 2024-08-19 14:39:14
 */
@Data
public class RecipeDTO  {
    private static final long serialVersionUID = 1L;
    private String pageRedirectionData; //支付宝支付成功后跳转页面html
    //订单id
    private String orderId;
    //支付金额
    private String money;

    private Integer serialId; //处方表id
    /** 费用补缴方式（需补缴金额=病人负担金额-个人账户支付) 1:现金  2:微信  3:支付宝 */
    private String fybjfs;
    private String bjje;//补缴金额
    private String qrCodeUrl;//微信支付返回的二维码连接

    /** 门诊统筹--信用支付相关返回*/
    private  String xyjyzf; //信用就医支付金额
    private  String xyjysfje; //信用就医实付金额
    private  String xyjyyhje; //信用就医优惠金额
    private  String xyjyyhsbyy; //信用就医优惠失败原因
    private  String xyjysm; //信用就医异常说明
    /** id_no	string	*证件号 */
    private String idNo;
    /** nme	string	*姓名 */
    private String nme;
    private String authType; //认证类型，1、刷脸 2、扫码  3、刷卡
    private String feeType; //支付类型：0、门诊统筹，1、慢性病筹， 2、纯个账消费 3、 信用支付
    /**  p_sfxyjy 门诊统筹--是否信用就医	1:是,0:否；默认是“0” */
    private String  pSfxyjy;

    /** pay_fee	number	*支付金额	单位：元 （精确到小数点后两位） */
    private String payFee;

    /** 普通门诊刷卡标志
     * 门诊统、慢性病筹：0，纯个账消费：1
     * */
    private String pPtmzskbz;

    /** 疾病编码，关联疾病表 */
    private String icdCode;

    /** 疾病名称 */
    private String icdName;

    /**
     * 地纬结算返回的 结算号 jshid
     * 本结算号为该次门诊结算在医保系统中的唯一标识
     */
    private String jshid;

    /**
     * 地纬结算返回的 住院流水号  zylsh
     *
     */
    private String zylsh;

    /**
     * 总金额
     */
    private String zje;

    /** 账户余额 */
    private String zhye;

    /** *病人负担金额 */
    private String brfdje;

    /** 个人账户支付 */
    private String grzhzf;

    /** 结算单 */
    private String report;

    /** 累积个人支付 */
    private String ljgrzf;

    /** 本次统筹支付 */
    private String tczf;

    /** 参保人行政机构id
     * 经办机构
     * */
    private String jbjgid;

    /**
     * 参保单位名称
     * */
    private String cbdw;


}
