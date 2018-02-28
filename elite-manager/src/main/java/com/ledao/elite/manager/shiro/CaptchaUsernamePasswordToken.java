package com.ledao.elite.manager.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 重写用户名密码、加入验证码
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
	private static final long serialVersionUID = -3178260335127476542L;

	private String captcha;
    private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken() {
		super();
	}
	
	public CaptchaUsernamePasswordToken(String username, String password,
			boolean rememberMe, String host, String captcha,String type) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.type = type;
	}

}
