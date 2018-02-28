package com.ledao.elite.site.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ledao.elite.site.controller.PlatformVisitCodeController;

/**
 * 安全码拦截器
 */
@Component
public class SafetyCodeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String contextPath = request.getContextPath();
		String requestURL = request.getRequestURL().toString();

		if (request.getSession().getAttribute(PlatformVisitCodeController.VISIT_SESSION) != null) {
			return true;
		}
		if (request.getRequestURI().startsWith(contextPath + "/res/")) {
			return true;
		}
		if (requestURL.endsWith("/visit") || requestURL.endsWith("/visit/start")
				||requestURL.endsWith("/asyn/notify")) {
			return true;
		}
		if (request.getSession().getAttribute(PlatformVisitCodeController.VISIT_SESSION) == null) {
			//log.info("******************************未登录邀请码，请输入您的邀请码再来访问******************************");
			response.sendRedirect(contextPath+"/visit");
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
