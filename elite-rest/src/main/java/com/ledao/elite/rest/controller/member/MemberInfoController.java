package com.ledao.elite.rest.controller.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.domain.member.MemberSkill;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.cache.custom.IdCardCache;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
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
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.core.service.project.ProjectTenderRecordService;
import com.ledao.elite.core.service.sys.AttasService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.RequestBaseRest;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.response.member.RMemberBasic;
import com.ledao.elite.rest.framework.response.member.RMemberCompany;
import com.ledao.elite.rest.framework.response.member.RMemberCredit;
import com.ledao.elite.rest.framework.response.member.RMemberEducation;
import com.ledao.elite.rest.framework.response.member.RMemberElite;
import com.ledao.elite.rest.framework.response.member.RMemberProjects;
import com.ledao.elite.rest.framework.response.member.RMemberSkill;

/**
 * 会员个人资料接口中心
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
	@Resource
	private ProjectTenderRecordService projectTenderRecordService;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	
	
	/**
	 * 更新会员头像
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/update/photo",method=RequestMethod.POST)
	public ModelAndView updateMemberPhoto(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long photoId=request.getDataId();
		if(photoId!=null){
			Long memberId=getMemberId();
			MemberBasic basic=this.memberBasicService.findMemberBasicByMemberId(memberId);
			if(basic==null){
				basic = new MemberBasic();
				basic.setMemberId(memberId);
				basic.setPhotoId(photoId);
				basic.setMemberId(getMemberId());
				memberBasicService.createMemberBasic(basic);
			}else{
				basic.setPhotoId(photoId);
				this.memberBasicService.updateBasicInfoNoFixed(basic);
			}
		}
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 会员精英当前状态信息完善入口
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/get",method=RequestMethod.POST)
	public ModelAndView memberCurrentIn(){
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		if(memberElite.getEliteJobs()!=null&&memberElite.getEliteJobs().size()>0){
			memberElite.setFirstJobs(memberElite.getEliteJobs().get(0));
		}
		
		RMemberElite data=new RMemberElite(memberElite);
		Date applyDate = memberElite.getApplyDate();
		if(applyDate!=null){
			String date = DateTimeUtils.formatDate(DateTimeUtils.addOrSub(applyDate, 30), "yyyy.MM.dd");
			data.setAplayDate(date);
		}
		ResponseResultData<RMemberElite> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}
	
	
	/**
	 * 精英签约CTO申请
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/update",method=RequestMethod.POST)
	public ModelAndView memberEliteUpdate(){
		ResponseResultData<Object> rsp=new ResponseResultData<>();
		MemberElite elite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		elite.setCtoSigned(true);
		elite.setApplyDate(new Date());
		memberEliteService.updateMemberElite(elite);
		//返回下次申请CTO日期
//		Date applyDate = DateTimeUtils.addOrSub(new Date(), 30);
//		String date = DateTimeUtils.formatDate(applyDate,"yyyy/MM/dd");
//		rs.setCode(ErrorCodeEnum.APPERROR.code);
//		rs.setMsg(date);
		return rsp.responseResult();
	}
	
	/**
	 * 申请审核资格
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/applyAudit", method = RequestMethod.POST)
	public ModelAndView applyAudit() {
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		MemberDetailInfo info = memberPassportService .findMemberDetailInfoById(getMemberId());
		ResponseBaseRest rs=new ResponseBaseRest();
		ResponseResultData<Object> rsp=new ResponseResultData<>();
		//如果申请审核时间还没过3天则不能修改
		if(memberElite.getApplyAuditTime()!=null && DateTimeUtils.dateSub(new Date(),memberElite.getApplyAuditTime())<=3){
			Date date = DateTimeUtils.addOrSub(memberElite.getApplyAuditTime(), 3);
			String message="没有申请机会,下次申请时间为"+DateTimeUtils.formatDate(date, "yyyy-MM-dd");
			rs.setCode(ErrorCodeEnum.APPERRORTIP.code);
			rs.setMsg(message);
			return rsp.responseResult(rs);
		}else{
			if(info.getCredit().getId()==null || info.getBasic().getId()==null  || info.getEducations().size()==0 || info.getProjects().size()==0 || memberElite.getEliteJobs().size()==0 || null==memberElite.getIntro()){
				rs.setCode(ErrorCodeEnum.APPERRORTIP.code);
				rs.setMsg("您的资料完善度\n还不满足审核条件,先完善哦!");
				return rsp.responseResult(rs);
			}
			return rsp.responseResult();
		}
	}
	
	/**
	 * 申请审核
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public ModelAndView audit() {
		ResponseResultData<Object> rsp=new ResponseResultData<>();
		MemberElite elite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		elite.setApplyAuditTime(new Date());
		elite.setStatus(Member_Status.aduitIn);
		memberEliteService.updateMemberElite(elite);
		return rsp.responseResult();
	}

	
	/**
	 * 会员精英信息完善
	 * */
	@SuppressWarnings("unchecked")
	@SSOToken
	@MemberHandleLog(description="更新精英的信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/elite/current/save",method=RequestMethod.POST)
	public ModelAndView memberCurrent(){
		ResponseBaseRest rs=new ResponseBaseRest();
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		String reqJson=parseRequest("jobAge","allotDuration","onjobed","eliteJobMaps");
		MemberElite elite=JSON.parseObject(reqJson, MemberElite.class);
		String eliteJobMaps=elite.getEliteJobMaps();
		String jobRole="";
		if(StringUtils.isNotEmpty(eliteJobMaps)){
			Map<String,String> map=JSON.parseObject(eliteJobMaps, Map.class);
			elite.setEliteJobMap(map);
			for (String role :elite.getEliteJobMap().keySet()) {
				jobRole=role;
			}
		}
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		if(!"".equals(jobRole) && memberElite.getEliteJobs().size()>0 && !memberElite.getEliteJobs().get(0).getJobRole().equals(jobRole)){
			if(memberElite.getUpdateRoleFlag()){
				Integer tenderCount = projectTenderRecordService.findCtoProjectTenderInCount(getMemberId(), 1);
				Integer recruitWinCount = projectTaskRecruitService.findProjectTaskRecruitCount(getMemberId(), ProjectTaskRecruit_Status.recruit_win);
				if(tenderCount+recruitWinCount>0){
					rs.setCode(ErrorCodeEnum.APPERROR.code);
					rs.setMsg("您有正在进行的任务不能进行修改！");
					return rsp.responseResult(rs);
				}
			}else{
				rs.setCode(ErrorCodeEnum.APPERROR.code);
				Date modifyDate=DateTimeUtils.addOrSub(memberElite.getEliteJobs().get(0).getUpdateRoleDate(), 30);
				rs.setMsg("您不能修改下次修改角色时间为"+DateTimeUtils.formatDate(modifyDate, "yyyy-MM-dd"));
				return rsp.responseResult(rs);
			}
			
		}
		this.memberEliteService.updateMemberEliteCurrentInfo(getMemberId(), elite);
		return rsp.responseResult();
	}
	
	/**
	 * 会员精英自我描述信息保存
	 * */
	@SSOToken
	@MemberHandleLog(description="更新精英的信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/elite/intro/save",method=RequestMethod.POST)
	public ModelAndView memberIntro(){
		String reqJson=parseRequest("value");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		String intro=request.getValue();
		
		MemberElite elite=this.memberEliteService.findMemberEliteByMemberId(getMemberId());
		if(elite==null){
			throw new EliteServiceException("帐号不存在",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}
		elite.setIntro(intro);
		this.memberEliteService.updateMemberEliteInfo(getMemberId(), elite);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 会员基本信息完善入口
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/basic/get",method=RequestMethod.POST)
	public ModelAndView memberBasicIn(){
		MemberBasic basic=this.memberBasicService.findMemberBasicByMemberId(getMemberId());
		MemberPassport member = this.memberPassportService.findMemberPassportById(getMemberId());
		RMemberBasic data=new RMemberBasic();
		if(basic!=null){
			data=new RMemberBasic(basic);
		}
		data.setNickName(member.getNickName());
		ResponseResultData<RMemberBasic> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}
	
	/**
	 * 会员基本信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员基本信息",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/basic/save",method=RequestMethod.POST)
	public ModelAndView memberBasic(){
		String reqJson=parseRequest("photoId","birthday","areaName","memberSign","nickName","sex");
		MemberBasic basic=JSON.parseObject(reqJson, MemberBasic.class);
		this.memberBasicService.updateMemberBasic(getMemberId(), basic);
		
		if(StringUtils.isNotEmpty(basic.getNickName())){
			MemberPassport mp=this.memberPassportService.findMemberPassportById(getMemberId());
			mp.setNickName(basic.getNickName());
			this.memberPassportService.updateMemberPassport(mp);
			
			//同步更新多重身份
			MemberUpdatePass updatePass=new MemberUpdatePass();
			updatePass.setMemberId(mp.getId());
			updatePass.setAccount(mp.getAccount());
			updatePass.setNickName(basic.getNickName());
			eventPublishService.publishMemberIdentityDate(null, null, null,updatePass);
		}
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 会员征信信息完善入口
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/credit/get",method=RequestMethod.POST)
	public ModelAndView memberCreditIn(Model model){
//		if(currentTypeValidUpdateInfo()){
//			throw new EliteServiceException("帐号非待审核，不能更新",ErrorCodeEnum.ALREADLY_AUDIT.code);
//		}
		MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		ResponseResultData<RMemberCredit> rsp=new ResponseResultData<>(); 
		if(credit==null){
			return rsp.responseResult(); 
		}
		RMemberCredit data=new RMemberCredit(credit);
		return rsp.responseResult(data);
	}
	
	/**
	 * 会员征信信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员征信信息",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/credit/save",method=RequestMethod.POST)
	public ModelAndView memberCredit(){
		String reqJson=parseRequest("realName","idCard","cardJust","cardReverse");
		MemberCredit credit=JSON.parseObject(reqJson, MemberCredit.class);
		credit.setMemberId(getMemberId());
		MemberCredit obj=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		ResponseResultData<Object> rs=new ResponseResultData<>(); 
		ResponseBaseRest rsp=new ResponseBaseRest();
		if(obj==null||!obj.getIsCard()){
			boolean cflag=IdCardCache.valid(getMemberId());
			if(!cflag){
				rsp.setCode(ErrorCodeEnum.APPERROR.code);
				rsp.setMsg("身份证信息验证过于频繁，请明天再试");
				return rs.responseResult(rsp);
			}
			boolean flag=this.memberCreditService.findValidateIdCard(credit.getRealName(), credit.getIdCard());
			if(!flag){
				rsp.setCode(ErrorCodeEnum.APPERROR.code);
				rsp.setMsg("身份证号码和姓名不匹配");
				return rs.responseResult(rsp);
			}
		}
		try {
			if(obj==null){
				memberCreditService.createMemberCredit(credit);
			}else{
				memberCreditService.updateMemberCredit(getMemberId(), credit);
			}
		} catch (EliteServiceException e) {
			rsp.setCode(ErrorCodeEnum.APPERROR.code);
			rsp.setMsg(e.getMsg());
			return rs.responseResult(rsp);
		}
		return rs.responseResult();
	}
	
	/**
	 * 项目方创业信息完善入口
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/company/get",method=RequestMethod.POST)
	public ModelAndView memberCompanyIn(){
		MemberCompany company=this.memberCompanyService.findMemberCompanyByMemberId(getMemberId());
		
		RMemberCompany data=new RMemberCompany(company);
		ResponseResultData<RMemberCompany> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}
	
	/**
	 * 项目方创业信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="更新项目方的创业信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/company/save",method=RequestMethod.POST)
	public ModelAndView memberCompany(){
		String reqJson=parseRequest("companyPosition","companyed");
		MemberCompany company=JSON.parseObject(reqJson, MemberCompany.class);
		this.memberCompanyService.updateMemberCompany(getMemberId(), company);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 会员精英信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="更新精英的信息",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/elite/save",method=RequestMethod.POST)
	public ModelAndView memberElite(){
		String reqJson=parseRequest("intro");
		MemberElite elite=JSON.parseObject(reqJson, MemberElite.class);
		this.memberEliteService.updateMemberEliteInfo(getMemberId(), elite);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 会员项目经验查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/project/get",method=RequestMethod.POST)
	public ModelAndView memberProjectFind(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long id=request.getDataId();
		MemberProjects mp= this.memberProjectsService.findById(id);
		
		RMemberProjects data=new RMemberProjects(mp);
		ResponseResultData<RMemberProjects> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}
	
	/**
	 * 会员项目经验列表查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/project/list",method=RequestMethod.POST)
	public ModelAndView memberProjectList(){
		List<MemberProjects> list=memberProjectsService.findByMemberId(getMemberId());
		
		List<RMemberProjects> dataList=new ArrayList<>();
		for(MemberProjects mp:list){
			RMemberProjects data=new RMemberProjects(mp);
			dataList.add(data);
		}
		ResponseResultData<List<RMemberProjects>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(dataList);
	}
	
	
	/**
	 * 会员项目经验完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员项目经验",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/elite/project/save",method=RequestMethod.POST)
	public ModelAndView memberProject(){
		String reqJson=parseRequest("project","position","startTime","endTime","intro");
		MemberProjects project=JSON.parseObject(reqJson, MemberProjects.class);
		project.setMemberId(getMemberId());
		if(project.getId()==null){
			memberProjectsService.createMemberProject(project);
		}else{
			memberProjectsService.updateMemberProject(project.getId(), project);
		}
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 会员项目经验删除
	 * */
	@SSOToken
	@MemberHandleLog(description="删除会员项目经验",type=LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value="/elite/project/remove",method=RequestMethod.POST)
	public ModelAndView removeMemberProject(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long id=request.getDataId();
		this.memberProjectsService.removePhysicalProjectById(id);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 会员教育经验完善入口
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/education/get",method=RequestMethod.POST)
	public ModelAndView memberEducationFind(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long id=request.getDataId();
		MemberEducation me=this.memberEducationService.findById(id);
		
		RMemberEducation data=new RMemberEducation(me);
		ResponseResultData<RMemberEducation> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}
	
	/**
	 * 会员教育经验列表查询{edu,train}
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/education/list",method=RequestMethod.POST)
	public ModelAndView memberEducationList(){
		String reqJson=parseRequest("value");
		RequestBaseRest obj=JSON.parseObject(reqJson, RequestBaseRest.class);
		String type=obj.getValue();
		List<MemberEducation> list=memberEducationService.findByMemberId(getMemberId(),type);
		
		List<RMemberEducation> dataList=new ArrayList<>();
		for(MemberEducation me:list){
			RMemberEducation data=new RMemberEducation(me);
			dataList.add(data);
		}
		ResponseResultData<List<RMemberEducation>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(dataList);
	}
	
	/**
	 * 会员教育经验完善
	 * */
	@SuppressWarnings("unchecked")
	@SSOToken
	@MemberHandleLog(description="保存会员教育经验",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/elite/education/save",method=RequestMethod.POST)
	public ModelAndView memberEducation(){
		String reqJson=parseRequest("organ","education","graduateTime");
		Map<String,String> educationMap=JSON.parseObject(reqJson, Map.class);
		String date = educationMap.get("graduateTime")+"-01";
		educationMap.put("graduateTime", date);
		MemberEducation education=JSON.parseObject(JSON.toJSONString(educationMap), MemberEducation.class);
		education.setMemberId(getMemberId());
		if(education.getId()==null){
			memberEducationService.createMemberEducation(education);
		}else{
			memberEducationService.updateMemberEducation(education.getId(), education);
		}
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * 会员教育经验删除
	 * */
	@SSOToken
	@MemberHandleLog(description="删除会员教育经验",type=LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value="/elite/education/remove",method=RequestMethod.POST)
	public ModelAndView removeMemberEducation(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long id=request.getDataId();
		this.memberEducationService.removePhysicalById(id);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 会员技能槽信息完善入口
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/skill/get",method=RequestMethod.POST)
	public ModelAndView memberSkillFind(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long id=request.getDataId();
		MemberSkill skill=this.memberSkillService.findById(id);
		
		RMemberSkill data=new RMemberSkill(skill);
		ResponseResultData<RMemberSkill> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(data);
	}
	
	/**
	 * 会员技能列表查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/elite/skill/list",method=RequestMethod.POST)
	public ModelAndView memberSkillList(){
		List<MemberSkill> list=memberSkillService.findMemberSkillByMemberId(getMemberId());
		
		List<RMemberSkill> dataList=new ArrayList<>();
		for(MemberSkill skill:list){
			RMemberSkill data=new RMemberSkill(skill);
			dataList.add(data);
		}
		ResponseResultData<List<RMemberSkill>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(dataList);
	}
	
	/**
	 * 会员技能槽信息完善
	 * */
	@SSOToken
	@MemberHandleLog(description="保存会员技能槽",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/skill/save",method=RequestMethod.POST)
	public ModelAndView memberSkill(){
		String reqJson=parseRequest("level","skill");
		MemberSkill skill=JSON.parseObject(reqJson, MemberSkill.class);
		skill.setMemberId(getMemberId());
		if(skill.getId()==null){
			memberSkillService.createMemberSkill(skill);
		}else{
			memberSkillService.updteMemberSkill(skill.getId(), skill);
		}
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * 会员技能槽删除
	 * */
	@SSOToken
	@MemberHandleLog(description="删除会员技能槽",type=LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value="/skill/remove",method=RequestMethod.POST)
	public ModelAndView removeMemberSkill(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long id=request.getDataId();
		this.memberSkillService.removePhysicalById(id);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	
	/**
	 * 当前状态的信息修改验证其是否符合修改邀请
	 * */
	@SuppressWarnings("unused")
	private boolean currentTypeValidUpdateInfo(){
		boolean flag=false;
		MemberPassport memberDetail=this.memberPassportService.findMemberPassportById(getMemberId());
		String type=memberDetail.getCurrentType();
		Long memberId=getMemberId();
		if(MemberIdentity_Type.company.name().equals(type)){
			MemberCompany member=this.memberCompanyService.findMemberCompanyByMemberId(memberId);
			if(member==null){
				throw new EliteServiceException("项目方不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
			}
			if(!Member_Status.waitAduit.equals(member.getStatus())){
				flag=true;
			}
		}else if(MemberIdentity_Type.elite.name().equals(type)||MemberIdentity_Type.cto.name().equals(type)){
			MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
			if(memberElite==null){
				throw new EliteServiceException("精英不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
			}
			if(!Member_Status.waitAduit.equals(memberElite.getStatus())){
				flag=true;
			}
		}
		return flag;
	}
	
	
}
