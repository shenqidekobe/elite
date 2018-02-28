package com.ledao.elite.site.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 会员数据级拦截
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class MemberDataInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView view)
			throws Exception {
		//验证当前数据是否属于登录会员的数据
		if(view!=null){
			//log.info("**********************response data*************************");
			//log.info(JSON.toJSONString(map));
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		return true;
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}

}
