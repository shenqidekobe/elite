package com.ledao.elite.site.shiro.cas;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

/**
 * cas过滤
 * */
public class CustomCasFilter extends CasFilter {

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		     WebUtils.issueRedirect(request, response, getSuccessUrl());
	        return false;
	}
         
}
