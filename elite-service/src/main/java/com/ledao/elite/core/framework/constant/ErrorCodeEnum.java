package com.ledao.elite.core.framework.constant;

/**
 * 错误码枚举定义
 * */
public enum ErrorCodeEnum {
	
	SUCCESS("0000","操作成功"),
	FAILURE("1111","操作失败"),
	EMAIL_ILLEGALITY("1501","邮箱格式错误"),
	PHONE_ILLEGALITY("1502","手机号码格式错误"),
	FILE_TYPE_ILLEGALITY("1601","不支持的文件类型"),
	FILE_LENGTH_LIMIT("1602","文件大小超过限制"),
	NOLOGIN("2001","未登录"),
	ACCOUNT_AUDIT_UNPASS("3000","帐号还未审核通过"),
	ACCOUNT_EXIST("3001","帐号已存在"),
	EMAIL_EXIST("3002","邮箱已存在"),
	IDCARD_EXIST("3003","身份证已存在"),
	TENDER_EXIST("3004","已竞标过此项目"),
	ALREADLY_AUDIT("3005","已审核过，不能直接更新"),
	AMOUNT_OVER("3006","金额已超出"),
	OBJECT_AUDIT_UNUPDATE("3007","对象已审核,不能被修改"),
	CITY_NOT_EXIST("3010","城市不存在"),
	INVITE_NOT_EXIST("3011","邀请码不存在"),
	BANK_NOT_MATCH("3012","银行卡信息不匹配"),
	BANK_FREQUENT_VALID("3013","银行卡信息验证过于频繁，请明天再试"),
	BANK_NOT_SUPPORT("3014","此卡系统不支持，绑定失败"),
	BANK_NOT_EXIT("3015","该银行卡号不存在"),
	PAY_EXIST("3020","已支付过"),
	TASK_OVER("3021","任务结束,请关注其他任务"),
	SURPASS_NUM_LIMIT("3022","超过次数限制"),
	OBJECT_EXIST("4001","对象已存在"),
	OBJECT_NOT_EXIST("4002","对象不存在"),
	OBJECT_DISABLED("4003","对象被禁用"),
	OBJECT_DELETED("4004","对象被删除"),
	ACCOUNT_PASS_FAULT("4005","帐号密码错误"),
	TRADE_PASS_FAULT("4006","交易密码错误"),
	OBJECT_USE("4007","对象被使用"),
	OBJECT_EXPIRE("4008","对象过期"),
	ATTRIBUTE_NAME_REPEAT("4009","属性名重复"),
	NOT_ELITE("6001","非精英"),
	NOT_DEFINE("6002","未立项"),
	VERIFYCODEFAULT("8001","验证码错误"),
	PARAM_ISNULL("8002","参数为空"),
	AUTHFAILED("8888","验证失败"),
	TOKENFAILED("8890","ssoToken无效"),
	UNAUTHORIZED("9000","无权限操作"),
	ERROR("9998","出现异常"),
	OTHER("9999","未知的其他异常"),
	APPERROR("0001",""),
	APPERRORTIP("0002","");
	
	public String code;
	public String msg;
	ErrorCodeEnum(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	public String getCode(){
		return code;
	}
	public String getMsg(){
		return msg;
	}

}
