package com.ledao.elite.core.framework.plugin.pay.pingpp;

/**
 * 认证返回结果代码
 * */
public enum AuthResultCode {

	SUCCESS("0","成功"),
	THIRD_FAIL("2001","调用第三方接口失败"),
	DEDUC_FAIL("2003","扣费失败"),
	INVALID_BUSINESS("3200","无效的商户号"),
	INVALID_SESSIONID("3204","无效的sessionId"),
	NOTFOUND_BUSINESS("3300","商户号未找到"),
	ACCOUNT_BUSINESS("3303","商户号与服务访问账号不匹配"),
	PARAM_ILLEGAL("3420","请求参数不合法"),
	IDCARD_MATE("3431","身份证信息不匹配"),
	IDCARD_FAIL("3432","身份证信息匹配失败"),
	IDCARD_PHOTO("3433","身份验证活体照片非法"),
	IDCARD_UPLOAD_PHOTO("3434","身份验证上传登记照失败"),
	IDCARD_COMP_PHOTO("3435","身份验证照片比对失败"),
	BANK_FAIL("3441","银行卡信息认证失败"),
	BANK_EXCEP("3442","银行卡信息认证请求异常"),
	BANK_TIMEOUT("3443","银行卡信息认证请求超时"),
	BANK_VERIFY_FAIL("3451","银行卡信息认证校验失败"),
	BANK_AUTH_FAIL("3452","银行卡信息认证校验异常");
	
	public String code;
	public String desc;
	AuthResultCode(String code,String desc){
		this.code=code;
		this.desc=desc;
	}
	public String getCode(){
		return code;
	}
	public String getDesc(){
		return desc;
	}
}
