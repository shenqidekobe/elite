package com.ledao.elite.site.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.shiro.sso.ITokenUserManage;
import com.ledao.elite.site.shiro.PrincipalService;

/**
 * 会员token处理拦截器{登录会员更新最后回话时间}
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class MemberTokenHandlerInterceptor extends HandlerInterceptorAdapter{

	@Resource
	private PrincipalService PrincipalService;
	
	@Resource(name="tokenUserRAMManage")
	private ITokenUserManage tokenUserManage;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(PrincipalService.getPrincipalId()!=null){
			String token = request.getHeader(SSOToken.TOKEN_HEADER_NAME);
			if(StringUtils.isNotEmpty(token)
					&&!this.tokenUserManage.isExpire(token)){
				this.tokenUserManage.refreshLastRequest(token);
			}
		}
		return true;
	}
	
}
