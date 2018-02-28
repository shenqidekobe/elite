package com.ledao.elite.core.framework.constant;

/**
 * 日志枚举类
 * */
public enum LogsEnum {

	login("登录"),
	logout("登出"),
	register("注册"),
	create("创建"),
	update("修改"),
	saveOrUpdate("保存或修改"),
	remove("删除"),
	query("查询"),
	disable("禁用"),
	enable("启用"),
	upload("上传"),
	download("下载"),
	sendSms("发送短信"),
	sendEmail("发送邮件"),
	sendMsg("发送系统消息"),
	validSms("验证手机短信"),
	validCard("验证身份证"),
	validBank("验证银行卡"),
	pay("发起支付"),
	other("其他");
	
	public String value;
	LogsEnum(String value) {
    	this.value = value;
	}
	public String getValue(){
		return value;
	}
}
