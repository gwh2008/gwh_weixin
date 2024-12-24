package com.tencent.wxcloudrun.util;


import lombok.Data;

/**
 * 存放微信信息的实体类
 * @author songjinyu
 *
 */
@Data
public class WeiXinEntity {
	//订单id
	private String serialId;
	//支付金额
	private String money;

	private String orderId;
	private String payType;
	private String typeId;
	private String tenantId;

	private String access_token;
	private String ticket;
	private String noncestr;
	private String timestamp;
	private String str;
	private String signature;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
