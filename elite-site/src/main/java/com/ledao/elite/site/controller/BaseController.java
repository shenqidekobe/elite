package com.ledao.elite.site.controller;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Status;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.service.member.MemberMessageService;
import com.ledao.elite.core.utils.WebUtils;
import com.ledao.elite.site.config.AppConfig;
import com.ledao.elite.site.exception.NoLoginException;
import com.ledao.elite.site.shiro.Principal;
import com.ledao.elite.site.shiro.PrincipalService;

/**
 * Controller - 基类
 * 
 * @author kobe.liu
 * @version 1.0
 */
public class BaseController {
	
	protected ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(getClass());
	
	/** 系统首页 */
	protected static final String INDEX_VIEW = "/index";

	/** 会员登录页 */
	protected static final String MEMBER_LOGIN_VIEW = "/login";
	
	/** 重定向命令 */
	protected static final String REDIRECT_COMMAND = "redirect:";
	
	/** 系统404页 */
	protected static final String NOT_FOUND_VIEW = "/common/unfound";
	
	/** 系统500页 */
	protected static final String ERROR_VIEW = "/common/error";
	
	/** 会员操作无权限页 */
	protected static final String UNAUTHORIZED_VIEW = "/common/unauthorized";
	
	/** 支付失败页 */
	protected static final String PAY_FAIL_VIEW = "/common/payfail";
	
	/** 支付中页 */
	protected static final String PAY_ING_VIEW = "/common/paying";
	
	/** 默认时间格式化*/
	protected static final String DATE_FORMAT_DEFAULT_PATTEN="yyyy-MM-dd HH:mm:ss";

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
	
	@Resource
	protected MemberMessageService memberMessageService;
	
	/**
	 * 获取在线会员ID
	 * */
	protected Long getMemberId() {
		Long memberId=this.principalService.getPrincipalId();
		if(memberId==null)
			throw new NoLoginException("当前未登录");
		return memberId;
	}
	
	/**
	 * 获取在线会员的当前身份类型
	 * */
	protected String getMemberCurrentType() {
		Long memberId=this.principalService.getPrincipalId();
		if(memberId==null)
			throw new NoLoginException("当前未登录");
		return principal.getCurrentType();
	}
	
	/**
	 * 验证会员身份是否是某个角色
	 * */
	protected boolean identityIsCompany(MemberIdentity_Type type){
		Long memberId=this.principalService.getPrincipalId();
		if(memberId==null)
			throw new NoLoginException("当前未登录");
		Map<String,String> identityMap=this.principal.getIdentityMap();
		for(String key:identityMap.keySet()){
			if(type.name().equals(key)){
				return true;
			}
		}
		return false;
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
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
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
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}
	
	/**
	 * subject登录方法
	 * 
	 * @param account:帐号
	 * @param password:密码
	 * @param request:请求
	 * */
	protected void login(String account,String password,HttpServletRequest request){
		UsernamePasswordToken token = new UsernamePasswordToken(account,password,true,WebUtils.getClientIp(request));  
	    Subject subject = SecurityUtils.getSubject();
	    subject.login(token);
	}
	
	/**
	 * 查看会员是否有未读消息
	 * 是否已经登录，未登录则不查询。已登录则验证登录ID是否有效
	 * */
	protected void examineIsUnreadMessage(Model model,boolean isLogin){
		Long memberId=principalService.getPrincipalId();
		if(isLogin){
			memberId=getMemberId();
		}
		if(memberId!=null){
			Integer unreadCount=this.memberMessageService.findMemberMessageCount(memberId,null,MemberMessage_Status.unread.name(),null);
			model.addAttribute("unreadCount", unreadCount);
		}
	}
	
	protected String getServerUrl(){
		return localCoreConfig.getDomainServer();
	}
}