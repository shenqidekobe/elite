package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证短信参数
 * */
public class ValidSmsRequest extends RequestBaseRest{

	private static final long serialVersionUID = -3117990507317913643L;
	
	@Setter@Getter
	private String type;//短信类型
	@Setter@Getter
	private String account;//发送手机号
	@Setter@Getter
	private String verifyCode;//验证码
}
