package com.ledao.elite.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.utils.WebUtils;
import com.ledao.elite.manager.shiro.CaptchaUsernamePasswordToken;
import com.ledao.elite.manager.shiro.IncorrectCaptchaException;


/**
 * 后台用户登录控制器
 * 
 * @author kobe.liu
 * */
@Controller("loginController")
@RequestMapping("")
public class LoginController extends BaseController{
	
	
	/**
	 * 登录页面
	 * */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(HttpServletResponse rsp){
		rsp.setHeader("flag", "login");
		return "login";
	}
	
	
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * */
	@SystemHandleLog(description="登录",type=LogsEnum.login)
	@ResponseBody
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(String account,String password,String captcha,
			@RequestParam(value = "rememberMe",required = false)boolean rememberMe,HttpServletRequest request){
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(account, password,rememberMe,WebUtils.getClientIp(request),captcha,null);  
	    token.setRememberMe(true);  
	    Subject subject = SecurityUtils.getSubject();
	    String message="";
	    try {  
	        subject.login(token);  
	        message=GlobalDefinedConstant.JS_DEFINED.JS_RESULT.SUCCESS;
	    } catch (IncorrectCredentialsException e) {
	    	message = "帐号登录密码错误";
	    } catch (ExcessiveAttemptsException e) {  
	    	message = "登录失败次数过多："+token.getPrincipal();  
	    } catch (LockedAccountException e) {  
	    	message = "帐号已被锁定: " + token.getPrincipal();
	    } catch (DisabledAccountException e) {  
	    	message = "帐号已被禁用: " + token.getPrincipal();
	    } catch (ExpiredCredentialsException e) {  
	    	message = "帐号已过期: " + token.getPrincipal();  
	    } catch (UnknownAccountException e) {  
	    	message = "帐号不存在: "+token.getPrincipal();  
	    } catch (UnauthorizedException e) {  
	    	message = "您没有得到相应的授权！";  
	    }catch (IncorrectCaptchaException e) {  
	    	message = e.getMessage();  
	    }
	    return message;
	}
	
	/**
	 * 用户登出
	 * */
	@SystemHandleLog(description="登出",type=LogsEnum.logout)
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		//request.getSession().invalidate();
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/";
	}

}
