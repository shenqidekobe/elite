package com.ledao.elite.manager.shiro;

import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 验证Path权限
 */
@Slf4j
public class AuthPathMatchingFilter extends PathMatchingFilter {

	
	@Override
	protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		Principal pal = (Principal) SecurityUtils.getSubject().getPrincipal();
		log.info("用户 = {}", pal);
		// 未登录重定向到登录
		if (pal == null) {
			return false;
		}
		return true;
	}

	
	@Override
	protected void executeChain(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String path = super.getPathWithinApplication(request);
		String queryUrl = request.getQueryString();
		log.info("请求路径 = {}", path);
		log.info("请求参数 = {}", queryUrl);

		boolean isPermitted = SecurityUtils.getSubject().isPermitted(path);
		boolean isExclude = isExclude(path);
		log.info("是否有访问权限 ？ {}", isPermitted);
		log.info("是否在非验证权限列表 ？ {}", isExclude);
		if (isExclude || isPermitted) {
			super.executeChain(request, response, chain);
		} else {
			if (logoutPaths != null && logoutPaths.contains(path)) {
				WebUtils.issueRedirect(request, response, logoutUrl);
			} else {
				WebUtils.issueRedirect(request, response, unAuthUrl);
			}
		}
	}

	/**
	 * 判断是否排除权限校验
	 * 
	 * @param path
	 * @return
	 */
	private boolean isExclude(String path) {
		if (unAuthPaths != null) {
			for (String excludePath : unAuthPaths) {
				if (pathMatcher.matches(excludePath, path)) {
					return true;
				}
			}
		}
		return false;
	}

	
	
	private String logoutUrl;
	private String unAuthUrl;
	/**
	 * 无权限要退出登录Paths
	 */
	private List<String> logoutPaths;
	/**
	 * 只验证登录，不验证用户拥有的权限Path
	 */
	private List<String> unAuthPaths;

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public void setUnAuthUrl(String unAuthUrl) {
		this.unAuthUrl = unAuthUrl;
	}

	public void setLogoutPaths(List<String> logoutPaths) {
		this.logoutPaths = logoutPaths;
	}

	public void setUnAuthPaths(List<String> unAuthPaths) {
		this.unAuthPaths = unAuthPaths;
	}
}