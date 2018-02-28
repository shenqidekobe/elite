package com.ledao.elite.rest.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.framework.shiro.sso.ITokenUserManage;
import com.ledao.elite.core.framework.shiro.sso.TokenGenerateFactory;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.RequestBaseRest;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.response.LoginResponse;
import com.ledao.elite.rest.framework.response.MemberEliteInfoResponse;
import com.ledao.elite.rest.framework.response.MemberHomeResponse;
import com.ledao.elite.rest.framework.response.MemberInfoResponse;
import com.ledao.elite.rest.framework.utils.RestUtils;

/**
 * 会员首页的接口中心
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberHomeController")
@RequestMapping("/member")
public class MemberHomeController extends BaseController{

	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource(name="tokenUserRedisManage")
	private ITokenUserManage tokenUserManage;
	
	/**
	 * 会员切换身份
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/identity/switch",method=RequestMethod.POST)
	public ModelAndView memberSwitch(){
		String reqJson=parseRequest("value");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		String identityType=request.getValue();
		Long memberId=getMemberId();
		
		ResponseResultData<LoginResponse> rsp=new ResponseResultData<>(); 
		LoginResponse data=new LoginResponse();
		ResponseBaseRest result=new ResponseBaseRest();
		
		MemberPassport member=this.memberPassportService.findMemberPassportById(memberId);
		if(member==null){
			result.setCode(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
			result.setMsg("未找到当前用户");
			return rsp.responseResult(result,data);
		}
		String currentType=member.getCurrentType();
		if(identityType.equals(currentType)){
			result.setCode(ErrorCodeEnum.ACCOUNT_EXIST.code);
			result.setMsg("当前身份无需切换");
			return rsp.responseResult(result,data);
		}
		MemberPassport newMember=this.memberPassportService.updateMemberIdentity(memberId, identityType);
		Long newMemberId=newMember.getId();
		
		Map<String,String> userMap = new HashMap<String, String>();
    	userMap.put("channel", newMember.getChannel().name());
    	userMap.put("account", newMember.getAccount());
    	String accesToken = TokenGenerateFactory.genToken(userMap);
    	try {
    		com.ledao.elite.core.framework.shiro.sso.SSOToken ssoToken=new com.ledao.elite.core.framework.shiro.sso.SSOToken(newMemberId, accesToken, new Date());
    		tokenUserManage.persistenceUser(accesToken, ssoToken);
    		data.setMemberId(newMemberId);
			data.setCurrentType(newMember.getCurrentType());
			data.setSsoToken(accesToken);
			data.setExpireTime(com.ledao.elite.core.framework.shiro.sso.SSOToken.expireTime);
			//更新会员最后登录时间
			String account=MemberPassport.accountOffSuffix(newMember.getAccount());
			this.memberPassportService.updateMemberLastLogin(newMemberId,account,null);
			logger.info("会员帐号【"+account+"】从"+currentType+"身份切换成了"+identityType+",新账号为："+newMember.getAccount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp.responseResult(result,data);
	}
	
	
	/**
	 * 会员的个人信息主页
	 * @param type:会员类型{company、cto、elite、partnerCompany、partnerElite}
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public ModelAndView memberIndex(){
		Long memberId=getMemberId();
		MemberDetailInfo memberDetail=this.memberPassportService.findMemberDetailInfoById(memberId);
		Integer recruitWinCount = projectTaskRecruitService.findProjectTaskRecruitCount(getMemberId(), ProjectTaskRecruit_Status.recruit_win);
		memberDetail.setTaskCount(recruitWinCount);
		MemberHomeResponse data=new MemberHomeResponse(memberDetail);
		
		ResponseResultData<MemberHomeResponse> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}
	
	/**
	 * 会员的个人资料主页
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/info",method=RequestMethod.POST)
	public ModelAndView memberInfo(){
		Long memberId=getMemberId();
		MemberDetailInfo memberDetail=this.memberPassportService.findMemberDetailInfoById(memberId);
		MemberInfoResponse data=new MemberInfoResponse(memberDetail);
		ResponseResultData<MemberInfoResponse> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}
	
	/**
	 * 精英-是否接活
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/changeWork", method = RequestMethod.POST)
	public ModelAndView toWork() {
		String reqJson=parseRequest("value");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		boolean workFlag = Boolean.valueOf(request.getValue());
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		memberElite.setTaked(workFlag);
		memberEliteService.updateMemberElite(memberElite);
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 *　精英眼中的精英
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/elite/info",method=RequestMethod.POST)
	public ModelAndView eliteInfo(){
		Object id=RestUtils.getRequestParam("memberId");
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long memberId=request.getDataId();
		MemberDetailInfo memberDetail=this.memberPassportService.findMemberDetailInfoById(memberId);
		MemberEliteInfoResponse data=new MemberEliteInfoResponse(memberDetail);
		if(id!=null){
			MemberAttention maObj=memberAttentionService.checkAttentioned(Long.parseLong(id.toString()), memberId);
			data.setAttentioned(maObj!=null);
		}
		ResponseResultData<MemberEliteInfoResponse> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}

	
	/**
	 * 会员的个人详细信息查询，所有数据
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	public ModelAndView memberDetail(){
		Long memberId=getMemberId();
		MemberDetailInfo memberDetail=this.memberPassportService.findMemberDetailInfoById(memberId);
		
		ResponseResultData<MemberDetailInfo> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(memberDetail);
	}
}
