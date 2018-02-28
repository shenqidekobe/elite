package com.ledao.elite.manager.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

@Component
public class PrincipalService {

	/**
	 *  获得在线用户ID
	 */
	public Long getPrincipalId() {
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();
		if (principal != null) {
			return principal.getUserId();
		}
		return null;
	}
	/**
	 *  获得在线用户RoleID
	 */
	public Long getRoleId() {
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();
		if (principal != null) {
			return principal.getRoleId();
		}
		return null;
	}
}
