package com.ledao.elite.site.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.sys.SmsRecord.Sms_Type;
import com.ledao.elite.core.framework.annotation.ESChain;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.MemberLoginLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.thread.InviteCodeUpdateThread;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.site.dto.LoginResponse;
import com.ledao.elite.site.dto.ResponseResult;


/**
 * 后台用户登录控制器
 * 
 * @author kobe.liu
 * */
@Controller("loginController")
@RequestMapping("")
public class LoginController extends BaseController{
	
	private static final String ACCOUNT_SESSION_KEY="accountSession";//帐号session
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private SmsRecordService smsRecordService;
	
	/**
	 * 登录页面
	 * */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String index(Model model,@RequestParam(value = "rps",required = false)String rps,HttpServletRequest request,HttpServletResponse rsp){
		//已登录跳转到会员首页
		Long memberId=this.principalService.getPrincipalId();
		if(memberId!=null){
			return REDIRECT_COMMAND+"/member/index";
		}
		rsp.setHeader("flag", "login");
		model.addAttribute("rps", rps);
		return "login";
	}
	
	/**
	 * 忘记密码页面
	 * */
	@RequestMapping(value="/forget/{type}",method=RequestMethod.GET)
	public String forgetPass(@PathVariable String type,HttpServletRequest request, Model model){
		String view="/index/forget/forget";
		if("code".equals(type)){
			view="/index/forget/forget2";
		}else if("reset".equals(type)){
			view="/index/forget/forget3";
		}
		String phone=(String) request.getSession().getAttribute(ACCOUNT_SESSION_KEY);
		if(StringUtils.isEmpty(phone)){
			view="/index/forget/forget";
		}
		return view;
	}
	
	/**
	 * 忘记密码验证手机号
	 * */
	@MemberHandleLog(description="忘记密码,验证手机号",type=LogsEnum.validSms)
	@ResponseBody
	@RequestMapping(value="/forget/verify",method=RequestMethod.POST)
	public ResponseResult<String> forgetVerify(String account,String verifyCode,HttpServletRequest request){
		boolean flag=this.smsRecordService.findSmsRecordIsExists(account, Sms_Type.forget.name(), verifyCode, null);
		if(!flag){
			request.getSession().setAttribute(ACCOUNT_SESSION_KEY, account);
			return new ResponseResult<>();
		}
		return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"验证码错误");
	}
	
	/**
	 * 设置新密码
	 * */
	@MemberHandleLog(description="设置新密码",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/forget/reset/pass",method=RequestMethod.POST)
	public ResponseResult<String> resetPass(String newPass,HttpServletRequest request){
		String phone=(String) request.getSession().getAttribute(ACCOUNT_SESSION_KEY);
		if(StringUtils.isEmpty(phone)){
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"设置密码失败,请先验证帐号");
		}
		MemberPassport member=this.memberPassportService.findMemberPassportByAccount(phone);
		if(member==null){
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"未找到该用户");
		}
		this.memberPassportService.updateMemberPassword(member.getId(), null, newPass);
		return new ResponseResult<>();
	}
	
	
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * */
	@MemberLoginLog(description="登录",type=LogsEnum.login)
	@ResponseBody
	@RequestMapping(value="/startLogin",method=RequestMethod.POST)
	public ResponseResult<LoginResponse> login(String account,String password,String captcha,
			@RequestParam(value = "rememberMe",required = false)boolean rememberMe,
			@RequestParam(value = "rps",required = false)String rps,
			HttpServletRequest request){
	    String message="";
	    String result=GlobalDefinedConstant.FAILURE;
	    LoginResponse object=new LoginResponse();
	    try {
	    	login(account, password, request);
	        result=GlobalDefinedConstant.SUCCESS;
	        object.setRedirect(rps);
	        object.setToken(principal.getToken());
	        object.setCurrentType(principal.getCurrentType());
	    } catch (IncorrectCredentialsException e) {
	    	message = "登录密码错误";
	    } catch (ExcessiveAttemptsException e) {  
	    	message = "登录失败次数过多";
	    } catch (LockedAccountException e) {  
	    	message = "帐号已被锁定";
	    } catch (DisabledAccountException e) {  
	    	message = "帐号已被禁用";
	    } catch (ExpiredCredentialsException e) {  
	    	message = "帐号已过期";
	    } catch (UnknownAccountException e) {  
	    	message = "帐号不存在";
	    } catch (UnauthorizedException e) {  
	    	message = "您没有得到相应的授权";
	    }
	    ResponseResult<LoginResponse> rsp=new ResponseResult<LoginResponse>(result,message);
	    rsp.setObject(object);
	    return rsp;
	}
	
	
	/**
	 * 用户登出
	 * */
	@SSOToken
	@MemberLoginLog(description="登出",type=LogsEnum.logout)
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/";
	}
	
	/**
	 * 注册入口
	 * */
	@SSOToken
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registerIn(Model model){
		return "register";
	}
	
	/**
	 * 会员注册接口
	 * 
	 * @param account:注册帐号
	 * @param password:帐号密码
	 * @param verifyCode:验证码
	 * 
	 * @return 
	 * */
	@ESChain(ref="validEliteInviteCodeChain")
	@MemberHandleLog(description="会员注册",type=LogsEnum.register)
	@ResponseBody
	@RequestMapping(value="/register/save",method=RequestMethod.POST)
	public ResponseResult<String> register(String currentType,String nickName,String account,String password,String verifyCode,String inviteCode,
			HttpServletRequest request){
		boolean flag=this.smsRecordService.findSmsRecordIsExists(account, Sms_Type.register.name(), verifyCode, null);
		if(!flag){
			MemberPassport member=new MemberPassport();
			//publish event
			String url=localCoreConfig.getDomainServer();
			StringKeyValue message=null;
			String replace=null;
			if(currentType.equals("company")){
				message=MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_REGISTER_SUCCESS);
				replace="<a href='"+url+"/project/publish' target='_blank'>发布项目</a>";
			}else if(currentType.equals("elite")){
				message=MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_REGISTER_SUCCESS);
				replace="<a href='"+url+"/member/personage' target='_blank'>完善</a>";
			}
			member.setAccount(account);
			member.setPassword(password);
			member.setCurrentType(currentType);
			member.setNickName(nickName);
			member.setInviteCode(inviteCode);
			MemberPassport mepp=this.memberPassportService.createMemberPassport(member);
			
			String context=String.format(message.getValue(), replace);
			eventPublishService.publishMessageEvent(mepp.getId(), null, null, message.getKey(), context, false,MemberMessage_Type.system);
			
			//精英注册邀请码更新
			if(MemberIdentity_Type.elite.name().equals(currentType)){
				InviteCodeUpdateThread icut=new InviteCodeUpdateThread();
				icut.setInviteCode(inviteCode);
				new Thread(icut).start();
			}
			//start login
			login(account, password, request);
			return new ResponseResult<>();
		}
		return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"验证码错误");
	}
	
	/**
	 * 合作伙伴注册入口
	 */
	@RequestMapping(value="/register/{type}")
	public String partnerRegister(@PathVariable String type,Model model){
		//渠道方首页
		if(type.equals("partner")){
			return "/member/partner/index";
		}else if(type.equals("p")){
			type="partner";
		}
	
		return "/member/"+type+"/register";
	}
}
