package com.ledao.elite.rest.advice.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.annotation.ESChain;
import com.ledao.elite.core.framework.chain.HandlerChain;
import com.ledao.elite.core.utils.SpringContextUtil;

/**
 * elite site责任链AOP切面处理
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Aspect
@Component
public class ESChainAspect {
	
	
	@Pointcut("@annotation(com.ledao.elite.core.framework.annotation.ESChain)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截用户请求
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		String ref = refName(joinPoint);
		HandlerChain chain=(HandlerChain) SpringContextUtil.getBean(ref);
		chain.handle(null);
	}
	
	@SuppressWarnings("rawtypes")
	private String refName(JoinPoint joinPoint){
		String refName="";
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
						refName = method.getAnnotation(ESChain.class).ref();
						break;
					}
				}
			}
		} catch (Exception e) {}
		return refName;
	}
	
}
