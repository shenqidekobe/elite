package com.ledao.elite.site.shiro.cas;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.WebUtils;

/**
 * 用户过滤
 * */
public class CasUserFilter extends org.apache.shiro.web.filter.authc.UserFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		saveRequest(request);
		String loginUrl = getLoginUrl();
		WebUtils.issueRedirect(request, response, loginUrl);
		return false;
	}

	protected HttpSession getSession(ServletRequest request) {
		HttpServletRequest httpRequest=(HttpServletRequest)(((ShiroHttpServletRequest)request).getRequest());
		HttpSession session=httpRequest.getSession(false);
		return session;
	}
	
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    	HttpServletRequest  req = (HttpServletRequest) request;
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            HttpSession session =getSession(request);
           
            if( subject.getPrincipal() == null){
            	req.getSession().invalidate();//清空session
            	if(req.getCookies()!=null && req.getCookies().length>0){
	            	Cookie cookie = req.getCookies()[0];//获取cookie
	            	cookie.setMaxAge(0);//让cookie过期
            	}
            }
            if(session == null){
            	subject.logout();
            	return false;
            }else{
            	return subject.getPrincipal() != null;
            }
        }
    }

}
