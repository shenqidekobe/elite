package com.ledao.elite.core.framework.plugin.pay.pingpp;

/**
 * Ping++支付渠道
 * */
public enum PingPayChannel {
	
	alipay("支付宝APP支付"),
	alipay_wap("支付宝手机网页支付"),
	alipay_pc_direct("支付宝 PC 网页支付"),
	alipay_qr("支付宝当面付"),
	cnp_u("应用内快捷支付（银联）"),
	cnp_f("应用内快捷支付（外卡）"),
	cp_b2b("银联企业网银支付"),
	upacp("银联全渠道支付"),
	upacp_wap("银联全渠道手机网页支付"),
	upacp_pc("银联 PC 网页支付"),
	wx("微信支付，即微信 APP 支付"),
	wx_pub("微信公众号支付"),
	wx_pub_qr("微信公众号扫码支付"),
	wx_wap("微信 WAP 支付");
	
	public String desc;
	PingPayChannel(String desc){
		this.desc=desc;
	}
	public String getDesc(){
		return desc;
	}

}
