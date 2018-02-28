package com.ledao.elite.site.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

/**
 * 表单验证码验证
 * */
@Component
public class RFormAuthenticationFilter extends FormAuthenticationFilter {


	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		WebUtils.issueRedirect(request, response, "/");
		return false;
	}

}
