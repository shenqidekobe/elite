package com.ledao.elite.manager.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import com.ledao.elite.manager.controller.CaptchaController;

/**
 * 表单验证码验证
 * */
@Component
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

	private String captchaParam = CaptchaController.CAPTCHA_KEY;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}
	
	protected String getType(ServletRequest request) {
		return WebUtils.getCleanParam(request, "type");
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		String type = getType(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new CaptchaUsernamePasswordToken(username, password, rememberMe,
				host, captcha,type);
	}
	
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		WebUtils.issueRedirect(request, response, "/");
		return false;
	}

}
