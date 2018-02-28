package com.ledao.elite.site.advice.aop;

import java.lang.reflect.Method;
import java.util.Date;

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
import com.ledao.elite.core.domain.member.MemberLoginLogs;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.framework.annotation.MemberLoginLog;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.member.MemberLoginLogsService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.site.shiro.PrincipalService;

import lombok.extern.slf4j.Slf4j;

/**
 * 会员登录登出日志切入类
 * 
 * @author kobe.liu
 */
@Slf4j
@Aspect
@Component
public class MemberLoginLogAspect {

	@Resource
	private MemberLoginLogsService memberLoginLogsService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private PrincipalService principalService;

	@Pointcut("@annotation(com.ledao.elite.core.framework.annotation.MemberLoginLog)")
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
			MemberLoginLogs memberLoginLogs = getControllerMethodDescription(joinPoint);
			String reqIp=com.ledao.elite.core.utils.WebUtils.getClientIp(request);
			Long memberId=principalService.getPrincipalId();
			if(memberId==null&&LogsEnum.login.value.equals(memberLoginLogs.getOperType())){
				//处理登录的会员ID
				String account=request.getParameter("account");
				MemberPassport member=memberPassportService.findMemberPassportByAccount(account);
				if(member!=null){
					memberId=member.getId();
				}
			}
			memberLoginLogs.setLoginIp(reqIp);
			memberLoginLogs.setMemberId(memberId);
			memberLoginLogs.setLoginTime(new Date());
			memberLoginLogs.setReqParam(JSON.toJSONString(request.getParameterMap()));
			this.memberLoginLogsService.saveMemberLoginLogs(memberLoginLogs);
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
	public static MemberLoginLogs getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		MemberLoginLogs obj=new MemberLoginLogs();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					String type = method.getAnnotation(MemberLoginLog.class).type().value;
					obj.setOperType(type);
					break;
				}
			}
		}
		return obj;
	}
}
