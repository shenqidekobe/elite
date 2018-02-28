package com.ledao.elite.site.advice.aop;

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
import com.ledao.elite.core.domain.member.MemberLogs;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.member.MemberLogsService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.site.shiro.PrincipalService;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统操作日志切入类
 * 
 * @author kobe.liu
 */
@Slf4j
@Aspect
@Component
public class MemberHandleLogAspect {

	@Resource
	private MemberLogsService memberLogsService;
	@Resource
	private PrincipalService principalService;
	@Resource
	private MemberPassportService memberPassportService;

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
			MemberLogs logs = getControllerMethodDescription(joinPoint);
			String reqIp=com.ledao.elite.core.utils.WebUtils.getClientIp(request);
			Long memberId=principalService.getPrincipalId();
			if(memberId==null&&(LogsEnum.register.value.equals(logs.getOperType())||LogsEnum.sendSms.value.equals(logs.getOperType()))){
				String account=request.getParameter("account");
				MemberPassport member=memberPassportService.findMemberPassportByAccount(account);
				if(member!=null){
					memberId=member.getId();
				}
			}
			logs.setReqIp(reqIp);
			logs.setMemberId(memberId);
			logs.setReqParam(JSON.toJSONString(request.getParameterMap()));
			this.memberLogsService.saveMemberLogs(logs);
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
	public static MemberLogs getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		MemberLogs obj=new MemberLogs();
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
