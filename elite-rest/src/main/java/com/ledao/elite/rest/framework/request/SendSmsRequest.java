package com.ledao.elite.rest.framework.request;

import lombok.Data;

/**
 * 发送短信请求参数
 * */
@Data
public class SendSmsRequest {
	
	private String type;//短信类型
	private String account;//帐号
	private String bankCard;//银行卡号
	private String phone;//银行预留手机号
}
