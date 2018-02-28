package com.ledao.elite.atta.advice.aop;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.sys.SysLogs;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.service.sys.SysLogsService;

import lombok.extern.slf4j.Slf4j;

/**
 * 后台操作记录日志{系统操作日志切入类}
 * 
 * @author kobe.liu
 */
@Slf4j
@Aspect
@Component
public class MemberHandleLogAspect {

	@Resource
	private SysLogsService sysLogsService;

	@Pointcut("@annotation(com.ledao.elite.core.framework.annotation.MemberHandleLog)")
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
			SysLogs sysLogs = getControllerMethodDescription(joinPoint);
			String reqIp=com.ledao.elite.core.utils.WebUtils.getClientIp(request);
			Long userId=null;
			sysLogs.setReqIp(reqIp);
			sysLogs.setUserId(userId);
			sysLogs.setReqParam(JSON.toJSONString(request.getParameterMap()));
			this.sysLogsService.saveSysLogs(sysLogs);
		} catch (Exception e) {
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
	public static SysLogs getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		SysLogs obj=new SysLogs();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					String content = method.getAnnotation(MemberHandleLog.class).description();
					String type = method.getAnnotation(MemberHandleLog.class).type().value;
					obj.setContent(content);
					obj.setOperType(type);
					obj.setClassImpl(targetName);
					obj.setReqMethod(methodName);
					break;
				}
			}
		}
		return obj;
	}
}
