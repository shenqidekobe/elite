package com.ledao.elite.manager.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ledao.elite.core.framework.constant.AttachmentsConstant;
import com.ledao.elite.manager.shiro.Principal;

/**
 * 请求拦截
 * */
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter{
	
	@Resource
	private Principal principal;
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		String contextPath=request.getContextPath();
		request.setAttribute("_PATH", contextPath);
		request.setAttribute("_URL", WebUtils.getRequestUri(request));
		request.setAttribute("_UPLOADPATH",AttachmentsConstant.ATTA_SERVER_PATH);
		return true;
	}



}
