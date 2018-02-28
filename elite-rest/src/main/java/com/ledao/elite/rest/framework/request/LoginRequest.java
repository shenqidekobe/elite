package com.ledao.elite.rest.framework.request;

import lombok.Data;

/**
 * 登录请求参数
 * */
@Data
public class LoginRequest {

	private String account;//帐号
	private String password;//密码
	private String verifyCode;//验证码
	private String client;//客户端
}
