package com.ledao.elite.site.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.member.MemberAccount;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.domain.member.MemberEducation.MemberEducation_Type;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Status;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.framework.dto.MemberUpdatePass;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberEducationService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberProjectsService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.core.service.project.ProjectTenderRecordService;
import com.ledao.elite.core.service.sys.AttasService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 会员个人主页接口
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
	private MemberCompanyService memberCompanyService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private ProjectService projectService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private AttasService attasService;
	@Resource
	private MemberProjectsService memberProjectsService;
	@Resource
	private MemberEducationService	memberEducationService;
	@Resource
	private ProjectTenderRecordService projectTenderRecordService;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	
	
	/**
	 * 会员切换身份
	 * */
	@SSOToken
	@RequestMapping(value="/{identityType}/switch",method=RequestMethod.GET)
	public String memberSwitch(@PathVariable String identityType){
		String currentType=getMemberCurrentType();
		if(identityType.equals(currentType)){
			return "/member/"+currentType+"/main/index";
		}
		Long memberId=getMemberId();
		MemberPassport newMember=this.memberPassportService.updateMemberIdentity(memberId, identityType);
		String account=MemberPassport.accountOffSuffix(newMember.getAccount());
		//更新会员最后登录时间
		this.memberPassportService.updateMemberLastLogin(newMember.getId(),account,null);
		if(identityType.equals(MemberIdentity_Type.cto.name()) || identityType.equals(MemberIdentity_Type.elite.name())){
			MemberElite elite = memberEliteService.findMemberEliteByMemberId(memberId);
			principal.setStatus(elite==null?Member_Status.waitAduit:elite.getStatus());
		}
		principal.setMemberId(newMember.getId());
		principal.setMemberNum(newMember.getMemberNum());
		principal.setCurrentType(newMember.getCurrentType());
		Map<String,String> identityMap=new HashMap<>();
		identityMap.put(newMember.getCurrentType(), newMember.getCurrentType());
		principal.setIdentityMap(identityMap);
		logger.info("会员帐号【"+account+"】从"+currentType+"身份切换成了"+newMember.getCurrentType()+",新账号为："+newMember.getAccount());
		return REDIRECT_COMMAND+"/member/index";
	}
	
	
	/**
	 * 会员的个人信息主页
	 * @param type:会员类型{company、cto、elite、partnerCompany、partnerElite}
	 * */
	@SSOToken
	@RequestMapping(value={"","/index"},method=RequestMethod.GET)
	public String memberIndex(Model model){
		MemberDetailInfo memberDetail=this.memberPassportService.findMemberDetailInfoById(getMemberId());
		if(memberDetail==null){
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			return REDIRECT_COMMAND+"/";
		}
		Integer recruitWinCount = projectTaskRecruitService.findProjectTaskRecruitCount(getMemberId(), ProjectTaskRecruit_Status.recruit_win);
		memberDetail.setTaskCount(recruitWinCount);
		model.addAttribute("obj", memberDetail);
		String currentType=memberDetail.getCurrentType();
		Map<String,String> identityMap=principal.getIdentityMap();
		MemberIdentity_Type mit=MemberIdentity_Type.valueOf(MemberIdentity_Type.class, currentType);
		identityMap.put(mit.name(), mit.label);
		this.principal.setIdentityMap(identityMap);
		this.principal.setCurrentType(memberDetail.getCurrentType());
		//检查是否有未读消息
		MemberAccount account = memberAccountService.findMemberAccountByMemberId(getMemberId());
		Integer attentionCount = memberAttentionService.findAttenTionedUserCount(getMemberId());
		Integer unreadCount=this.memberMessageService.findMemberMessageCount(getMemberId(),null,MemberMessage_Status.unread.name(),null);
		model.addAttribute("currentType", currentType);
		model.addAttribute("unreadCount", unreadCount);
		model.addAttribute("account", account);
		model.addAttribute("attentionCount", attentionCount);
		return "/member/"+currentType+"/main/index";
	}
	
	/**
	 * 会员个人信息主页
	 * */
	@SSOToken
	@RequestMapping(value="/personage",method=RequestMethod.GET)
	public String personageInfo(Model model){
		MemberDetailInfo memberDetail=this.memberPassportService.findMemberDetailInfoById(getMemberId());
		Integer unreadCount=this.memberMessageService.findMemberMessageCount(getMemberId(),null,MemberMessage_Status.unread.name(),null);
		model.addAttribute("unreadCount", unreadCount);
		model.addAttribute("obj", memberDetail);
		String currentType=getMemberCurrentType();
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/personage/index";
	}
	
	
	/**
	 * 会员个人信息
	 * */
	@SSOToken
	@RequestMapping(value="/personage/frag",method=RequestMethod.POST)
	public String memberPersonageInfo(Model model){
		MemberDetailInfo memberDetail=this.memberPassportService.findMemberDetailInfoById(getMemberId());
		model.addAttribute("obj", memberDetail);
		String currentType=getMemberCurrentType();
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/personage/personage_frag";
	}
	
	/**
	 * 会员个人资料的基本信息查看
	 * */
	@SSOToken
	@RequestMapping(value="/personage/basic",method=RequestMethod.POST)
	public String personageBasic(Model model){
		String currentType=getMemberCurrentType();
		MemberBasic basic=this.memberBasicService.findMemberBasicByMemberId(getMemberId());
		MemberPassport member = memberPassportService.findMemberPassportById(getMemberId());
		model.addAttribute("obj", basic);
		model.addAttribute("member", member);
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/personage/personage_basic";
	}
	
	/**
	 * 精英当前资料
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/personage/current",method=RequestMethod.POST)
	public String personageCurrent(Model model){
		String currentType=getMemberCurrentType();
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		if(memberElite.getEliteJobs()!=null&&memberElite.getEliteJobs().size()>0){
			memberElite.setFirstJobs(memberElite.getEliteJobs().get(0));
		}
		MemberPassport mp=this.memberPassportService.findMemberPassportById(getMemberId());
		memberElite.setInviteCode(mp.getInviteCode());
		model.addAttribute("obj", memberElite);
		Boolean flag = memberElite.getUpdateRoleFlag();
		Date applyDate = memberElite.getApplyDate();
		Date modifyDate = null;
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
			if(applyDate!=null){
				applyDate = DateTimeUtils.addOrSub(applyDate, 30);
			}
			if(memberElite.getEliteJobs().size()>0 && memberElite.getEliteJobs().get(0).getUpdateRoleDate()!=null){
				 modifyDate=DateTimeUtils.addOrSub(memberElite.getEliteJobs().get(0).getUpdateRoleDate(), 30);
			}
			//是否有进行的任务或项目
			if(flag){
				Integer tenderCount = projectTenderRecordService.findCtoProjectTenderInCount(getMemberId(), 1);
				Integer recruitWinCount = projectTaskRecruitService.findProjectTaskRecruitCount(getMemberId(), ProjectTaskRecruit_Status.recruit_win);
				if(tenderCount+recruitWinCount>0){
					flag=false;
				}
			}
		}
		model.addAttribute("applyDate", applyDate);
		model.addAttribute("modifyDate", modifyDate);
		model.addAttribute("flag", flag);
		return "/member/"+currentType+"/personage/personage_current";
	}
	
	/**
	 * 精英项目经验
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/personage/experience",method=RequestMethod.POST)
	public String personageExperience(Long id,Model model){
		String currentType=getMemberCurrentType();
		List<MemberProjects> list =this.memberProjectsService.findByMemberId(getMemberId());
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		model.addAttribute("obj", memberElite);
		model.addAttribute("list", list);
		if(id!=null){
			MemberProjects project = memberProjectsService.findById(id);
			model.addAttribute("project", project);
		}
		if(memberElite.getResumeAttaId()!=null){
			model.addAttribute("atta",attasService.findById(memberElite.getResumeAttaId()));
		}
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/personage/personage_experience";
	}
	
	
	/**
	 * 精英教育经验
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/personage/education",method=RequestMethod.POST)
	public String personageEducation(Long id,Model model){
		String currentType=getMemberCurrentType();
		List<MemberEducation> eduList = memberEducationService.findByMemberId(getMemberId(),MemberEducation_Type.edu.toString());
		List<MemberEducation> trainList = memberEducationService.findByMemberId(getMemberId(),MemberEducation_Type.train.toString());
		model.addAttribute("eduList", eduList);
		model.addAttribute("trainList", trainList);
		if(id!=null){
			MemberEducation education = memberEducationService.findById(id);
			model.addAttribute("education",education);
		}
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/personage/personage_education";
	}

	
	/**
	 * 会员个人资料的基本信息更新
	 * */
	@SSOToken
	@ResponseBody
	@MemberHandleLog(description="更新会员基本信息",type=LogsEnum.saveOrUpdate)
	@RequestMapping(value="/personage/basic/save",method=RequestMethod.POST)
	public ResponseResult<String> savePersonageBasic(Model model,MemberBasic basic){
		this.memberBasicService.saveOrUpdateBasic(getMemberId(),basic);
		
		if(StringUtils.isNotEmpty(basic.getNickName())){
			MemberPassport mp=this.memberPassportService.findMemberPassportById(getMemberId());
			mp.setEmail(basic.getEmail());
			mp.setNickName(basic.getNickName());
			this.memberPassportService.updateMemberPassport(mp);
			
			//同步更新多重身份
			MemberUpdatePass updatePass=new MemberUpdatePass();
			updatePass.setMemberId(mp.getId());
			updatePass.setAccount(mp.getAccount());
			updatePass.setNickName(basic.getNickName());
			updatePass.setEmail(basic.getEmail());
			eventPublishService.publishMemberIdentityDate(null, null, null,updatePass);
		}
		return new ResponseResult<>();
	}
	
	/**
	 * 会员创业属性查看
	 * */
	@SSOToken
	@RequestMapping(value="/personage/company",method=RequestMethod.POST)
	public String personageCompany(Model model){
		MemberCompany company=this.memberCompanyService.findMemberCompanyByMemberId(getMemberId());
		model.addAttribute("obj", company);
		return "/member/company/personage/personage_company";
	}
	
	/**
	 * 会员创业属性更新
	 * */
	@SSOToken
	@ResponseBody
	@MemberHandleLog(description="更新项目方创业信息",type=LogsEnum.update)
	@RequestMapping(value="/personage/company/save",method=RequestMethod.POST)
	public ResponseResult<String> savePersonageCompany(Model model,MemberCompany company){
		this.memberCompanyService.updateMemberCompany(getMemberId(), company);
		return new ResponseResult<>();
	}
	
	/**
	 * 会员征信信息查看
	 * */
	@SSOToken
	@RequestMapping(value="/personage/credit",method=RequestMethod.POST)
	public String personageCredit(Model model){
		String currentType=getMemberCurrentType();
		MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		model.addAttribute("obj", credit);
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/personage/personage_credit";
	}
	
	/**
	 * 会员帐号信息
	 * */
	@SSOToken
	@RequestMapping(value="/personage/setting",method=RequestMethod.POST)
	public String personageSetting(Model model){
		String currentType=getMemberCurrentType();
		MemberPassport port=this.memberPassportService.findMemberPassportById(getMemberId());
		model.addAttribute("obj", port);
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/personage/personage_setting";
	}
	
	/**
	 * 会员帐号更新
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/personage/setting/save",method=RequestMethod.POST)
	public ResponseResult<String> savePersonageSetting(Model model,String oldPass,String newPass){
		String message="";
	    String result=GlobalDefinedConstant.FAILURE;
		try {
			this.memberPassportService.updateMemberPassword(getMemberId(), oldPass, newPass);
			result=GlobalDefinedConstant.SUCCESS;
		} catch (EliteServiceException e) {
			message=e.getMessage();
		}
		return new ResponseResult<>(result,message);
	}
    
	
	/**
	 * 修改密码
	 * */
	@SSOToken
	@RequestMapping(value="/update/pass",method=RequestMethod.POST)
	public ResponseResult<String> updatePassword(String oldPass,String newPass,String confirmPass){
		if(!confirmPass.equals(newPass)){
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"确认密码不一致");
		}
		this.memberPassportService.updateMemberPassword(getMemberId(), oldPass, newPass);
		return new ResponseResult<>();
	}
	
}
