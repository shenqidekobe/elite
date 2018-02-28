package com.ledao.elite.manager.controller;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.manager.config.AppConfig;
import com.ledao.elite.manager.exception.NoLoginException;
import com.ledao.elite.manager.shiro.Principal;
import com.ledao.elite.manager.shiro.PrincipalService;

/**
 * Controller - 基类
 * 
 * @author kobe.liu
 * @version 1.0
 */
public class BaseController {

	protected ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
			.getLogger(getClass());

	/** 系统首页 */
	protected static final String INDEX_VIEW = "/index";

	/** 用户登录页 */
	protected static final String LOGIN_VIEW = "/login";

	/** 重定向命令 */
	protected static final String REDIRECT_COMMAND = "redirect:";

	/** 系统错误页 */
	protected static final String ERROR_VIEW = "/common/error";

	/** 会员操作无权限页 */
	protected static final String UNAUTHORIZED_VIEW = "/common/unauthorized";

	/** 默认时间格式化 */
	protected static final String DATE_FORMAT_DEFAULT_PATTEN = "yyyy-MM-dd HH:mm:ss";

	/** "验证结果"参数名称 */
	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	private Validator validator;

	@Resource
	protected PrincipalService principalService;

	@Resource
	protected Principal principal;

	@Resource
	protected EventPublishService eventPublishService;

	@Resource
	protected LocalCoreConfig localCoreConfig;
	
	@Resource
	protected AppConfig appConfig;

	/**
	 * 获取在线会员ID
	 */
	protected Long getUserId() {
		Long userId = this.principalService.getPrincipalId();
		if (userId == null)
			throw new NoLoginException("当前未登录");
		return userId;

	}

	/**
	 * 获取在线会员RoleId
	 */
	protected Long getRoleId() {
		Long roleId = this.principalService.getRoleId();
		if (roleId == null)
			throw new NoLoginException("当前未登录");
		return roleId;

	}

	/**
	 * 数据验证
	 * 
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
					RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 数据验证
	 * 
	 * @param type
	 *            类型
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
		Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
					RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

}