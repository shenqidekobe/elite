package com.ledao.elite.site.advice.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ledao.elite.core.framework.annotation.ProjectNewNotices;
import com.ledao.elite.core.utils.JsonFileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目新通知（小红点）切入类
 * 
 */
@Slf4j
@Aspect
@Component
public class ProjectNewNoticesAspect {


	@Pointcut("@annotation(com.ledao.elite.core.framework.annotation.ProjectNewNotices)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			Map<String, String> map=getControllerMethodDescription(joinPoint);
			String projectId=request.getParameterMap().get("projectId")[0];
			String  controllerType=map.get("controllerType");
			String  noticeType=map.get("noticeType");
			if("add".equals(controllerType)){
			    JsonFileUtils.addNotice(projectId, noticeType);
			}
			if("remove".equals(controllerType)){
				JsonFileUtils.removeNotice(projectId, noticeType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String>  getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		Map<String,String> map=new HashMap<String,String>();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					String controllerType = method.getAnnotation(ProjectNewNotices.class).controllerType();
					String noticeType = method.getAnnotation(ProjectNewNotices.class).noticeType().name();
					map.put("controllerType", controllerType);
					map.put("noticeType", noticeType);
					break;
				}
			}
		}
		return map;
	}
}
