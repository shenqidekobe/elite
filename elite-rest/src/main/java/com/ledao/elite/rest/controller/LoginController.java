package com.ledao.elite.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.MemberLoginLog;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.shiro.sso.ITokenUserManage;
import com.ledao.elite.core.framework.shiro.sso.SSOToken;
import com.ledao.elite.core.framework.shiro.sso.TokenGenerateFactory;
import com.ledao.elite.core.framework.thread.InviteCodeUpdateThread;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.WebUtils;
import com.ledao.elite.core.utils.encry.Digests;
import com.ledao.elite.core.utils.encry.Encodes;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.LoginRequest;
import com.ledao.elite.rest.framework.request.RegisterRequest;
import com.ledao.elite.rest.framework.response.LoginResponse;


/**
 * 用户登录控制器
 * 
 * @author kobe.liu
 * */
@Controller("loginController")
@RequestMapping("")
public class LoginController extends BaseController{
	
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private SmsRecordService smsRecordService;
	@Resource(name="tokenUserRedisManage")
	private ITokenUserManage tokenUserManage;
	
	
	/**
	 * 会员注册接口
	 * */
	@MemberHandleLog(description="会员注册",type=LogsEnum.register)
	@ResponseBody
	@RequestMapping(value="/register/save",method=RequestMethod.POST)
	public ModelAndView registerSave(){
		String reqJson=parseRequest("currentType","account","password","inviteCode");
		RegisterRequest request=JSON.parseObject(reqJson, RegisterRequest.class);
		String currentType=request.getCurrentType(),nickName=request.getNickName(),account=request.getAccount(),password=request.getPassword(),channel=request.getClient();
		LoginResponse data=new LoginResponse();
		ResponseBaseRest result=new ResponseBaseRest();
		try {
			MemberPassport obj=new MemberPassport();
			obj.setAccount(account);
			obj.setNickName(nickName);
			obj.setPassword(password);
			obj.setCurrentType(currentType);
			if(!request.getInviteCode().equals("")){
				obj.setInviteCode(request.getInviteCode());
			}
			MemberPassport mepp = this.memberPassportService.createMemberPassport(obj);
			Long memberId=obj.getId();
			
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
			String context=String.format(message.getValue(), replace);
			eventPublishService.publishMessageEvent(mepp.getId(), null, null, message.getKey(), context, false,MemberMessage_Type.system);
			
			//精英注册邀请码更新
			if(!request.getInviteCode().equals("") && MemberIdentity_Type.elite.name().equals(currentType)){
				InviteCodeUpdateThread icut=new InviteCodeUpdateThread();
				icut.setInviteCode(request.getInviteCode());
				new Thread(icut).start();
			}
			
			//注册成功开始登录
			String accesToken=persistenceUser(memberId, account, channel);
			data.setMemberId(memberId);
			data.setCurrentType(currentType);
			data.setSsoToken(accesToken);
			data.setExpireTime(SSOToken.expireTime);
		} catch (EliteServiceException e) {
			result.setCode(e.getCode());
			result.setMsg(e.getMsg());
		}
		ResponseResultData<LoginResponse> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(result,data);
	}
	
	/**
	 * 会员登录接口
	 * */
	@MemberLoginLog(description="会员登录",type=LogsEnum.login)
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req){
		String reqJson=parseRequest("account","password");
		LoginRequest request=JSON.parseObject(reqJson, LoginRequest.class);
		String account=request.getAccount(),password=request.getPassword(),channel=request.getClient();
		
		ResponseResultData<LoginResponse> rsp=new ResponseResultData<>(); 
		LoginResponse data=new LoginResponse();
		ResponseBaseRest result=new ResponseBaseRest();
		
		MemberPassport user=this.memberPassportService.findMemberPassportByAccount(account);
		String msg="";
		String code=ErrorCodeEnum.SUCCESS.code;
		if (user == null) {
			msg="帐号不存在";
			code=ErrorCodeEnum.OBJECT_NOT_EXIST.code;
			result.setCode(code);
			result.setMsg(msg);
			return rsp.responseResult(result);
		}else{
			if (user.getStatus().equals(Member_Status.disabled.name())) {
				msg="帐号被冻结";
				code=ErrorCodeEnum.OBJECT_DISABLED.code;
			} else if (user.getStatus().equals(GlobalDefinedConstant.System_Status.deleted)) {
				msg="帐号未开通";
				code=ErrorCodeEnum.OBJECT_DELETED.code;
			}else{
				byte[] salt = Encodes.decodeHex(user.getPassSalt());
				String pass = new String(password);
				byte[] psalt = pass.getBytes();
				String inpwd = Encodes.encodeHex(Digests.md5(psalt, salt));
				if (!user.getPassword().equals(inpwd)) {
					msg="帐号密码错误";
					code=ErrorCodeEnum.ACCOUNT_PASS_FAULT.code;
				}
			}
			if(StringUtils.isNotEmpty(msg)){
				result.setCode(code);
				result.setMsg(msg);
				return rsp.responseResult(result);
			}else{
				String accesToken=persistenceUser(user.getId(), account, channel);
				data.setMemberId(user.getId());
				data.setCurrentType(user.getCurrentType());
				data.setSsoToken(accesToken);
				data.setExpireTime(SSOToken.expireTime);
				//更新会员最后登录状态
				this.memberPassportService.updateMemberLastLogin(user.getId(),account,WebUtils.getClientIp(req));
			}
		}
		return rsp.responseResult(result,data);
	}
	
	
	/**
	 * 存储会员登录token
	 * */
	private String persistenceUser(Long memberId,String account,String channel){
		Map<String,String> userMap = new HashMap<String, String>();
    	userMap.put("channel", channel);
    	userMap.put("account", account);
    	String accesToken = TokenGenerateFactory.genToken(userMap);
    	try {
    		SSOToken ssoToken=new SSOToken(memberId, accesToken, new Date());
    		tokenUserManage.persistenceUser(accesToken, ssoToken);
		} catch (Exception e) {
		}
		return accesToken;
	}
	
}
