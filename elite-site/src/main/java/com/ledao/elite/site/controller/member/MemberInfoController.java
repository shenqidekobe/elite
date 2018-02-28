package com.ledao.elite.site.controller.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Status;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.domain.member.MemberSkill;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.cache.custom.IdCardCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.MemberUpdatePass;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberEducationService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberMessageService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberProjectsService;
import com.ledao.elite.core.service.member.MemberSkillService;
import com.ledao.elite.core.service.sys.AttasService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 会员信息接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberInfoController")
@RequestMapping("/member")
public class MemberInfoController extends BaseController{
	
	
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberProjectsService memberProjectsService;
	@Resource
	private MemberEducationService memberEducationService;
	@Resource
	private MemberSkillService memberSkillService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private MemberCompanyService memberCompanyService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;
	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private MemberMessageService memberMessageService;
	@Resource
	private AttasService attasService;
	
	
	/**
	 * 更新会员头像
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/update/photo",method=RequestMethod.POST)
	public ResponseResult<String> updateMemberPhoto(Long photoId){
		if(photoId!=null){
			MemberBasic basic=this.memberBasicService.findMemberBasicByMemberId(getMemberId());
			if(basic==null){
				basic = new MemberBasic();
				basic.setPhotoId(photoId);
				basic.setMemberId(getMemberId());
				memberBasicService.createMemberBasic(basic);
			}else{
				basic.setPhotoId(photoId);
				this.memberBasicService.updateBasicInfoNoFixed(basic);
			}
			Attas atta=this.attasService.findById(photoId);
			if(atta!=null){
				this.principal.setHeader(atta.getPath());
			}
		}
		return new ResponseResult<>();
	}
	
	/**
	 * 查看会员基本信息详情
	 * */
	@SuppressWarnings("incomplete-switch")
	@SSOToken
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String memberView(Model model){
		Long memberId=getMemberId();
		MemberIdentity_Type memberType=MemberIdentity_Type.valueOf(MemberIdentity_Type.class,getMemberCurrentType());
		switch (memberType) {
		case company:
			MemberCompany memberCompany=this.memberCompanyService.findMemberCompanyByMemberId(memberId);
			model.addAttribute("obj", memberCompany);
			break;
		case elite:
			MemberElite memberElite=this.memberEliteService.findMemberEliteByMemberId(memberId);
			model.addAttribute("obj", memberElite);
			break;
		case partnerCompany:
			MemberPartnerCompany partnerCompany=this.memberPartnerCompanyService.findMemberPartnerCompanyByMemberId(memberId);
			model.addAttribute("obj", partnerCompany);
			break;
		case partnerElite:
			MemberPartnerElite partnerElite=this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(memberId);
			model.addAttribute("obj", partnerElite);
			break;
		}
		return "/member/basicView";
	}
	
	/**
	 * 会员精英当前状态信息完善入口
	 * */
	@SSOToken
	@RequestMapping(value="/current",method=RequestMethod.GET)
	public String memberCurrentIn(Model model){
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		if(!Member_Status.waitAduit.equals(memberElite.getStatus())){
			//非待审核直接返回500
			return ERROR_VIEW;
		}
		if(memberElite.getEliteJobs()!=null&&memberElite.getEliteJobs().size()>0){
			memberElite.setFirstJobs(memberElite.getEliteJobs().get(0));
		}
		Integer unreadCount=this.memberMessageService.findMemberMessageCount(getMemberId(),null,MemberMessage_Status.unread.name(),null);
		MemberPassport mp=this.memberPassportService.findMemberPassportById(getMemberId());
		memberElite.setInviteCode(mp.getInviteCode());
		model.addAttribute("obj", memberElite);
		model.addAttribute("unreadCount", unreadCount);
		return "/member/elite/current";
	}
	
	/**
	 * 会员精英信息完善
	 * */
	@SuppressWarnings("unchecked")
	@SSOToken
	@MemberHandleLog(description="更新精英的信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/elite/current/save",method=RequestMethod.POST)
	public ResponseResult<String> memberCurrent(MemberElite elite,String eliteJobMaps){
		if(StringUtils.isNotEmpty(eliteJobMaps)){
			Map<String,String> map=JSON.parseObject(eliteJobMaps, Map.class);
			elite.setEliteJobMap(map);
		}
		this.memberEliteService.updateMemberEliteCurrentInfo(getMemberId(), elite);
		
		if(StringUtils.isNotEmpty(elite.getInviteCode())){
			MemberPassport mp=this.memberPassportService.findMemberPassportById(getMemberId());
			mp.setInviteCode(elite.getInviteCode());
			this.memberPassportService.updateMemberPassport(mp);
		}
		return new ResponseResult<>();
	}
	
	/**
	 * 精英签约CTO申请
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/update",method=RequestMethod.POST)
	public ResponseResult<String> memberEliteUpdate(){
		ResponseResult<String>  rs = new ResponseResult<>();
		MemberElite elite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		elite.setCtoSigned(true);
		elite.setApplyDate(new Date());
		memberEliteService.updateMemberElite(elite);
		//返回下次申请CTO日期
		Date applyDate = DateTimeUtils.addOrSub(new Date(), 30);
		String date = DateTimeUtils.formatDate(applyDate,"yyyy/MM/dd");
		rs.setMsg(date);
		return rs;
	}

	
	/**
	 * 查看会员基本信息详情
	 * */
	@SSOToken
	@RequestMapping(value="/basic/view",method=RequestMethod.GET)
	public String memberBasicView(Model model){
		MemberBasic basic=this.memberBasicService.findMemberBasicByMemberId(getMemberId());
		model.addAttribute("obj", basic);
		return "/member/basicView";
	}
	
	
	/**
	 * 会员基本信息完善入口
	 * */
	@SSOToken
	@RequestMapping(value="/basic",method=RequestMethod.GET)
	public String memberBasicIn(Model model){
		if(currentTypeValidUpdateInfo()){
			return ERROR_VIEW;
		}
		String type=getMemberCurrentType();
		MemberBasic basic=this.memberBasicService.findMemberBasicByMemberId(getMemberId());
		Integer unreadCount=this.memberMessageService.findMemberMessageCount(getMemberId(),null,MemberMessage_Status.unread.name(),null);
		if(basic!=null){
			model.addAttribute("head",basic.getMemberPhoto());
		}
		model.addAttribute("obj", basic);
		model.addAttribute("unreadCount", unreadCount);
		return "/member/"+type+"/basic";
	}
	
	/**
	 * 会员基本信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员基本信息",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/basic/save",method=RequestMethod.POST)
	public ResponseResult<String> memberBasic(MemberBasic basic,String photoUrl){
		this.memberBasicService.saveOrUpdateBasic(getMemberId(), basic);
		if(StringUtils.isNotEmpty(photoUrl)){
			this.principal.setHeader(photoUrl);
		}
		if(StringUtils.isNotEmpty(basic.getEmail())){
			MemberPassport mp=this.memberPassportService.findMemberPassportById(getMemberId());
			mp.setEmail(basic.getEmail());
			//个人资料页面更新昵称的时候
			if(StringUtils.isNotEmpty(basic.getNickName())){
				mp.setNickName(basic.getNickName());
			}
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
	 * 查看会员项目经验详情
	 * */
	@SSOToken
	@RequestMapping(value="/project/view",method=RequestMethod.GET)
	public String memberProjectView(Model model){
		List<MemberProjects> list=memberProjectsService.findByMemberId(getMemberId());
		model.addAttribute("list", list);
		return "/member/projectView";
	}
	
	/**
	 * 会员项目经验查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/project/{id}",method=RequestMethod.POST)
	public MemberProjects memberProjectFind(@PathVariable Long id){
		return this.memberProjectsService.findById(id);
	}
	
	/**
	 * 会员项目经验列表查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/project/list",method=RequestMethod.POST)
	public ResponseResult<MemberProjects> memberProjectList(){
		List<MemberProjects> list=memberProjectsService.findByMemberId(getMemberId());
		return new ResponseResult<>(list);
	}
	
	
	/**
	 * 会员项目经验完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员项目经验",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/project/save",method=RequestMethod.POST)
	public MemberProjects memberProject(MemberProjects project){
		project.setMemberId(getMemberId());
		MemberProjects pojo=null;
		if(project.getId()==null){
			pojo=memberProjectsService.createMemberProject(project);
		}else{
			pojo=memberProjectsService.updateMemberProject(project.getId(), project);
		}
		return pojo;
	}
	
	
	/**
	 * 会员项目经验删除
	 * */
	@SSOToken
	@MemberHandleLog(description="删除会员项目经验",type=LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value="/project/remove",method=RequestMethod.POST)
	public ResponseResult<String> removeMemberProject(Long id){
		this.memberProjectsService.removePhysicalProjectById(id);
		return new ResponseResult<>();
	}
	
	
	/**
	 * 查看会员教育经验详情
	 * */
	@SSOToken
	@RequestMapping(value="/education/{type}/view",method=RequestMethod.GET)
	public String memberEducationView(@PathVariable String type,Model model){
		List<MemberEducation> list=memberEducationService.findByMemberId(getMemberId(),type);
		model.addAttribute("list", list);
		return "/member/educationView";
	}
	
	/**
	 * 会员教育经验完善入口
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/education/{id}",method=RequestMethod.POST)
	public MemberEducation memberEducationFind(@PathVariable Long id){
		return this.memberEducationService.findById(id);
	}
	
	/**
	 * 会员教育经验列表查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/education/list",method=RequestMethod.POST)
	public ResponseResult<MemberEducation> memberEducationList(String type){
		List<MemberEducation> list=memberEducationService.findByMemberId(getMemberId(),type);
		return new ResponseResult<>(list);
	}
	
	/**
	 * 会员教育经验完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员教育经验",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/education/save",method=RequestMethod.POST)
	public MemberEducation memberEducation(MemberEducation education){
		education.setMemberId(getMemberId());
		MemberEducation pojo=null;
		if(education.getId()==null){
			pojo=memberEducationService.createMemberEducation(education);
		}else{
			pojo=memberEducationService.updateMemberEducation(education.getId(), education);
		}
		return pojo;
	}
	
	/**
	 * 会员教育经验删除
	 * */
	@SSOToken
	@MemberHandleLog(description="删除会员教育经验",type=LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value="/education/remove",method=RequestMethod.POST)
	public ResponseResult<String> removeMemberEducation(Long id){
		this.memberEducationService.removePhysicalById(id);
		return new ResponseResult<>();
	}
	
	/**
	 * 查看会员教育经验详情
	 * */
	@SSOToken
	@RequestMapping(value="/skill/view",method=RequestMethod.GET)
	public String memberSkillView(@PathVariable String type,Model model){
		List<MemberSkill> list=memberSkillService.findMemberSkillByMemberId(getMemberId());
		model.addAttribute("list", list);
		return "/member/skillView";
	}
	
	/**
	 * 会员技能槽信息完善入口
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/skill/{id}",method=RequestMethod.POST)
	public MemberSkill memberSkillFind(@PathVariable Long id){
		return this.memberSkillService.findById(id);
	}
	
	/**
	 * 会员技能列表查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/skill/list",method=RequestMethod.POST)
	public ResponseResult<MemberSkill> memberSkillList(String type){
		List<MemberSkill> list=memberSkillService.findMemberSkillByMemberId(getMemberId());
		return new ResponseResult<>(list);
	}
	
	/**
	 * 会员技能槽信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员技能槽",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/skill/save",method=RequestMethod.POST)
	public MemberSkill memberSkill(MemberSkill skill){
		skill.setMemberId(getMemberId());
		MemberSkill pojo=null;
		if(skill.getId()==null){
			pojo=memberSkillService.createMemberSkill(skill);
		}else{
			pojo=memberSkillService.updteMemberSkill(skill.getId(), skill);
		}
		return pojo;
	}
	
	/**
	 * 会员技能槽删除
	 * */
	@SSOToken
	@MemberHandleLog(description="删除会员技能槽",type=LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value="/skill/remove",method=RequestMethod.POST)
	public ResponseResult<String> removeMemberSkill(Long id){
		this.memberSkillService.removePhysicalById(id);
		return new ResponseResult<>();
	}
	
	/**
	 * 查看会员征信详情
	 * */
	@SSOToken
	@RequestMapping(value="/credit/view",method=RequestMethod.GET)
	public String memberCreditView(Model model){
		MemberCredit basic=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		model.addAttribute("obj", basic);
		return "/member/creditView";
	}
	
	/**
	 * 会员征信信息完善入口
	 * */
	@SSOToken
	@RequestMapping(value="/credit",method=RequestMethod.GET)
	public String memberCreditIn(Model model){
		if(currentTypeValidUpdateInfo()){
			return ERROR_VIEW;
		}
		String type=getMemberCurrentType();
		MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		model.addAttribute("obj", credit);
		
		return "/member/"+type+"/credit";
	}
	
	/**
	 * 会员征信信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员征信信息",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/credit/save",method=RequestMethod.POST)
	public ResponseResult<String> memberCredit(MemberCredit credit){
		credit.setMemberId(getMemberId());
		MemberCredit obj=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		//检查身份证姓名是否匹配
		if(obj==null||!obj.getIsCard()){
			boolean cflag=IdCardCache.valid(getMemberId());
			if(!cflag){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"频繁认证，请明天再试");
			}
			boolean flag=this.memberCreditService.findValidateIdCard(credit.getRealName(), credit.getIdCard());
			if(!flag){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "身份证号码和姓名不匹配");
			}
		}
		try {
			if(obj==null){
				memberCreditService.createMemberCredit(credit);
			}else{
				memberCreditService.updateMemberCredit(getMemberId(), credit);
			}
		} catch (EliteServiceException e) {
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE, e.getMsg());
		}
		
		return new ResponseResult<>();
	}
	
	/**
	 * 项目方创业信息完善入口
	 * */
	@SSOToken
	@RequestMapping(value="/company",method=RequestMethod.GET)
	public String memberCompanyIn(Model model){
		MemberCompany company=this.memberCompanyService.findMemberCompanyByMemberId(getMemberId());
		if(!Member_Status.waitAduit.equals(company.getStatus())){
			return ERROR_VIEW;
		}
		model.addAttribute("obj",company);
		return "/member/company/info";
	}
	
	/**
	 * 项目方创业信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="更新项目方的创业信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/company/save",method=RequestMethod.POST)
	public ResponseResult<String> memberCompany(MemberCompany company){
		this.memberCompanyService.updateMemberCompany(getMemberId(), company);
		return new ResponseResult<>();
	}
	
	/**
	 * 会员精英信息完善入口{项目经历}
	 * */
	@SSOToken
	@RequestMapping(value="/elite",method=RequestMethod.GET)
	public String memberEliteIn(Model model){
		List<MemberProjects> list =this.memberProjectsService.findByMemberId(getMemberId());
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		if(!Member_Status.waitAduit.equals(memberElite.getStatus())){
			//非待审核直接返回500
			return ERROR_VIEW;
		}
		model.addAttribute("obj", memberElite);
		model.addAttribute("list", list);
		if(memberElite.getResumeAttaId()!=null){
			model.addAttribute("atta",attasService.findById(memberElite.getResumeAttaId()));
		}
		return "/member/elite/experience";
	}
	
	
	/**
	 * 会员精英信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="更新精英的信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/elite/save",method=RequestMethod.POST)
	public ResponseResult<String> memberElite(MemberElite elite){
		
		this.memberEliteService.updateMemberEliteInfo(getMemberId(), elite);
		return new ResponseResult<>();
	}
	
	/**
	 * 会员精英信息完善
	 * */
	@SSOToken
	@RequestMapping(value="/elite/intro",method=RequestMethod.POST)
	public String memberElite(Model model){
		MemberElite elite = this.memberEliteService.findMemberEliteByMemberId(getMemberId());
		model.addAttribute("obj",elite);
		return "/member/elite/personage/personage_introduce";
	}

	
	/**
	 * 合作伙伴项目方信息完善入口
	 * */
	@SSOToken
	@RequestMapping(value="/partnerCompany",method=RequestMethod.GET)
	public String memberPartnerCompanyIn(Model model){
		return "/member/info/partnerCompany";
	}
	
	/**
	 * 合作伙伴项目方信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="更新合作伙伴项目方信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/partnerCompany/save",method=RequestMethod.POST)
	public ResponseResult<String> memberPartnerCompany(MemberPartnerCompany partnerCompany){
		this.memberPartnerCompanyService.updateMemberPartnerCompany(getMemberId(), partnerCompany);
		return new ResponseResult<>();
	}
	
	/**
	 * 合作伙伴猎头方信息完善入口
	 * */
	@SSOToken
	@RequestMapping(value="/partnerElite",method=RequestMethod.GET)
	public String memberPartnerEliteIn(Model model){
		return "/member/info/partnerElite";
	}
	
	/**
	 * 合作伙伴猎头方信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="更新合作伙伴猎头的信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/partnerElite/save",method=RequestMethod.POST)
	public ResponseResult<String> memberPartnerElite(MemberPartnerElite partnerElite){
		this.memberPartnerEliteService.updateMemberPartnerElite(getMemberId(), partnerElite);
		return new ResponseResult<>();
	}
	
	/**
	 * 设置头像界面
	 * */
	@SSOToken
	@RequestMapping(value="/custom/head",method=RequestMethod.POST)
	public String customHead(Model model){
		return "/member/customHead";
	}
	
	/**
	 * 切换身份界面
	 * */
	@SSOToken
	@RequestMapping(value="/switch/identity",method=RequestMethod.POST)
	public String switchIdentity(Model model){
		model.addAttribute("currentType",getMemberCurrentType());
		return "/member/switchIdentity";
	}
	
	
	/**
	 * 当前状态的信息修改验证其是否符合修改邀请
	 * */
	private boolean currentTypeValidUpdateInfo(){
		boolean flag=false;
		String type=getMemberCurrentType();
		Long memberId=getMemberId();
		if(MemberIdentity_Type.company.name().equals(type)){
			MemberCompany member=this.memberCompanyService.findMemberCompanyByMemberId(memberId);
			if(!Member_Status.waitAduit.equals(member.getStatus())){
				flag=true;
			}
		}else if(MemberIdentity_Type.elite.name().equals(type)||MemberIdentity_Type.cto.name().equals(type)){
			MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
			if(!Member_Status.waitAduit.equals(memberElite.getStatus())){
				flag=true;
			}
		}
		return flag;
	}
	
	
}
