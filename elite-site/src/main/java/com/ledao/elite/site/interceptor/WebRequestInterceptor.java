package com.ledao.elite.site.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.AttachmentsConstant;
import com.ledao.elite.core.utils.LogHelper;
import com.ledao.elite.site.shiro.Principal;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求拦截
 * */
@Slf4j
@Component
public class WebRequestInterceptor extends HandlerInterceptorAdapter{
	
	@Resource
	private Principal principal;
	
	@Resource
	private LocalCoreConfig localCoreConfig;
	
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
		request.setAttribute("_UPLOADPATH",AttachmentsConstant.ATTA_SERVER_PATH);
		if(request.getRequestURI().startsWith(contextPath+"/res/")){
			return true;
		}
		Enumeration<String> keys = request.getParameterNames();
		Map<String,String> paramMap=new HashMap<>();
		while(keys.hasMoreElements()) {
		    String key = keys.nextElement();
		    String value=request.getParameter(key);
		    paramMap.put(key, value);
		}
		String host = request.getRemoteHost();
		String requestURL = request.getRequestURL().toString();
		String parameter = JSON.toJSONString(((HttpServletRequestWrapper) request).getParameterMap());
		String method = request.getMethod();

		if(!requestURL.contains("/common/unfound")){
			List<String> logs = Lists.newArrayList(LogHelper.titleOutput("REQUEST LOGGING"),
					String.format("RequestURL: %s", requestURL),
					String.format("Method: %s", method),
					String.format("Host: %s", host), String.format("RequestParameters: %s", parameter));
			log.info("\n" + StringUtils.join(logs, "\n") + "\n");
		}
		return true;
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}

}
