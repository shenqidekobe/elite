package com.ledao.elite.site.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * 登录用户服务
 * */
@Component
public class PrincipalService {

	@Resource
	protected Principal principal;
	
	/**
	 *  获得会员ID
	 */
	public Long getPrincipalId() {
		try {
			if(principal.getMemberId()!=null){
				return principal.getMemberId();
			}
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal.getMemberId();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
