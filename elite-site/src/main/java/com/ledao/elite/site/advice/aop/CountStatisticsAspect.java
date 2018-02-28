package com.ledao.elite.site.advice.aop;

import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.ledao.elite.core.framework.annotation.CountStatistics;
import com.ledao.elite.core.framework.constant.StatisticsEnum;
import com.ledao.elite.core.service.member.MemberPassportService;

/**
 * 数量统计切面
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Aspect
@Component
public class CountStatisticsAspect {
	
	@Resource
	private MemberPassportService memberPassportService;
	
	@Pointcut("@annotation(com.ledao.elite.core.framework.annotation.CountStatistics)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截用户请求
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@AfterReturning("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		statistics(joinPoint);
	}
	
	@SuppressWarnings("rawtypes")
	private void statistics(JoinPoint joinPoint){
		String[] params=null;
		StatisticsEnum type=null;
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						params = method.getAnnotation(CountStatistics.class).param();
						type = method.getAnnotation(CountStatistics.class).type();
						break;
					}
				}
			}
			if(type==null||params==null)return;
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			switch (type) {
			case memberViewCount:
				String id=params[0];
				Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				Long memberId=Long.parseLong(pathVariables.get(id).toString());
				this.memberPassportService.updateMemberViewCount(memberId);
				break;
			}
		} catch (Exception e) {e.printStackTrace();}
	}
}
