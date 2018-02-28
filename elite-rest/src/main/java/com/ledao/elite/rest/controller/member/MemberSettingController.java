package com.ledao.elite.rest.controller.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.sys.SmsRecord.Sms_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.MemberUpdatePass;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.RequestBaseRest;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.ForgetPassRequest;
import com.ledao.elite.rest.framework.request.ValidSmsRequest;

/**
 * 会员设置中心的接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberSettingController")
@RequestMapping("/member/setting")
public class MemberSettingController  extends BaseController{
	
	
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private SmsRecordService smsRecordService;
	
	/**
	 * 邮箱绑定
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/update/email",method=RequestMethod.POST)
	public ModelAndView updateEmail(){
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest rs = new ResponseBaseRest();
		rs.setCode(ErrorCodeEnum.SUCCESS.code);
		String reqJson=parseRequest("value");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long memberId=request.getMemberId();
		String email=request.getValue();
		if(!CommonUtils.checkEmail(email)){
			rs.setCode(ErrorCodeEnum.EMAIL_ILLEGALITY.code);
			rs.setMsg("邮箱格式错误");
			return rsp.responseResult(rs);
		}
		MemberBasic basic=memberBasicService.findMemberBasicByMemberId(memberId);
		if(basic==null){
			basic = new MemberBasic();
			basic.setEmail(email);
			basic.setMemberId(memberId);
			memberBasicService.createMemberBasic(basic);
			
			MemberPassport mp=this.memberPassportService.findMemberPassportById(getMemberId());
			mp.setEmail(basic.getEmail());
			this.memberPassportService.updateMemberPassport(mp);
			
			//同步更新多重身份
			MemberUpdatePass updatePass=new MemberUpdatePass();
			updatePass.setMemberId(mp.getId());
			updatePass.setAccount(mp.getAccount());
			updatePass.setNickName(basic.getNickName());
			updatePass.setEmail(basic.getEmail());
			eventPublishService.publishMemberIdentityDate(null, null, null,updatePass);
			
			return rsp.responseResult(rs);
		}
		basic.setEmail(email);
		this.memberBasicService.saveOrUpdateBasic(getMemberId(), basic);
		//同步更新会员帐号信息表的邮箱
		MemberPassport mp=this.memberPassportService.findMemberPassportById(getMemberId());
		mp.setEmail(basic.getEmail());
		this.memberPassportService.updateMemberPassport(mp);
		
		return rsp.responseResult(rs);
	}
	
	
	/**
	 * 更换手机号
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/update/phone",method=RequestMethod.POST)
	public ModelAndView updatePhone(){
		String reqJson=parseRequest("account","verifyCode","type");
		ValidSmsRequest request=JSON.parseObject(reqJson, ValidSmsRequest.class);
		Long memberId=request.getMemberId();
		String account=request.getAccount();
		String type=request.getType();
		String verifyCode=request.getVerifyCode();
		ResponseBaseRest result=new ResponseBaseRest();
		try {
			boolean flag=this.smsRecordService.findSmsRecordIsExists(account,type, verifyCode, null);
			if(flag){
				result.setCode(ErrorCodeEnum.VERIFYCODEFAULT.code);
				result.setMsg("手机验证码错误");
			}else{
				this.memberPassportService.updateMemberPhone(memberId, account);
			}
		} catch (EliteServiceException e) {
			result.setCode(ErrorCodeEnum.ERROR.code);
			result.setMsg(e.getMsg());
		}
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(result);
	}
	
	/**
	 * 验证帐号的登录密码
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/valid/loginPass",method=RequestMethod.POST)
	public ModelAndView validLoginPass(){
		String reqJson=parseRequest("value");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long memberId=request.getMemberId();
		String password=request.getValue();
		memberPassportService.findMemberPassportByIdValidPassword(memberId, password);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * 重置密码第二步，输入新密码更新
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/update/loginPass",method=RequestMethod.POST)
	public ModelAndView updateLoginPass(){
		String reqJson=parseRequest("value");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long memberId=request.getMemberId();
		String password=request.getValue();
		this.memberPassportService.updateMemberPassword(memberId, null, password);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * 找回密码
	 * */
	@ResponseBody
	@RequestMapping(value="/forget/loginPass",method=RequestMethod.POST)
	public ModelAndView forgetLoginPass(){
		String reqJson=parseRequest("account","verifyCode","password");
		ForgetPassRequest request=JSON.parseObject(reqJson, ForgetPassRequest.class);
		String password=request.getPassword(),account=request.getAccount(),verifyCode=request.getVerifyCode();
		boolean flag=this.smsRecordService.findSmsRecordIsExists(account, Sms_Type.forget.name(), verifyCode, null);
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest result=new ResponseBaseRest();
		if(flag){
			result.setCode(ErrorCodeEnum.VERIFYCODEFAULT.code);
			result.setMsg("手机验证码错误");
			return rsp.responseResult(result);
		}else{
			MemberPassport member=this.memberPassportService.findMemberPassportByAccount(account);
			if(member==null){
				result.setCode(ErrorCodeEnum.FAILURE.code);
				result.setMsg("未找到该用户");
				return rsp.responseResult(result);
			}
			this.memberPassportService.updateMemberPassword(member.getId(), null, password);
		}
		return rsp.responseResult(result);
	}
	
}
