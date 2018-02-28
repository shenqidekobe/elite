package com.ledao.elite.rest.advice.aop;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.shiro.sso.ITokenUserManage;
import com.ledao.elite.rest.framework.exection.NoLoginException;
import com.ledao.elite.rest.framework.exection.TokenExpireException;
import com.ledao.elite.rest.framework.exection.TokenInvalidException;
import com.ledao.elite.rest.framework.utils.RestUtils;

/**
 * 请求token验证AOP切面处理
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Aspect
@Component
public class SSOTokenAspect {
	
	private static final String SSOTOKEN_KEY="ssoToken";
	
	
	@Resource(name="tokenUserRedisManage")
	private ITokenUserManage tokenUserManage;
	
	@Pointcut("@annotation(com.ledao.elite.core.framework.annotation.SSOToken)")
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
		Object ssoTokenObj=RestUtils.getRequestParam(SSOTOKEN_KEY);
		if(ssoTokenObj==null){
			throw new NoLoginException("ssoToken为空");
		}
		String ssoToken=ssoTokenObj.toString();
		if(this.tokenUserManage.getUser(ssoToken)==null){
			throw new TokenExpireException("token无效",ErrorCodeEnum.TOKENFAILED.code);
		}
		if(this.tokenUserManage.isExpire(ssoToken)){
			throw new TokenInvalidException("token已失效",ErrorCodeEnum.TOKENFAILED.code);
		}
		this.tokenUserManage.refreshLastRequest(ssoToken);
	}
	
	
}
