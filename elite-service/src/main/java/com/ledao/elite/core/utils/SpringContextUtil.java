package com.ledao.elite.core.utils;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 手动获取spring上下文配置信息
 * */
@Component("springContextUtil")
public class SpringContextUtil implements ServletContextAware, ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	private static ServletContext servletContext;

	
	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}

	public static String getWebPath(){
		return servletContext.getRealPath("/");
	}

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
