package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Getter;
import lombok.Setter;

/**
 * 找回密码请求参数
 * */
public class ForgetPassRequest extends RequestBaseRest{

	private static final long serialVersionUID = -3117990507317913643L;
	
	@Setter@Getter
	private String account;//发送手机号
	@Setter@Getter
	private String verifyCode;//验证码
	@Setter@Getter
	private String password;//新密码
}
