package com.ledao.elite.site.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Status;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberTeam;
import com.ledao.elite.core.domain.platform.PlatformFeedback;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.framework.annotation.CountStatistics;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.StatisticsEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberTeamService;
import com.ledao.elite.core.service.platform.PlatformFeedbackService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectStageTaskService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 系统首页
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("indexController")
@RequestMapping("")
public class IndexController extends BaseController{
	
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectStageTaskService projectStageTaskService;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private MemberTeamService memberTeamService;
	@Resource
	private PlatformFeedbackService platformFeedbackService;
	@Resource
	private PlatformInviteCodeService platformInviteCodeService;
	
	/**
	 * 系统首页
	 * */
	@RequestMapping(value={"","/index"},method=RequestMethod.GET)
	public String index(Model model){
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		return "index";
	}
	
	/**
	 * 任务大厅
	 * */
	@RequestMapping(value="/hall",method=RequestMethod.GET)
	public String hall(Model model){
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		//检查会员当前身份是否属于精英，否则不通过查看
		String currentType=getMemberCurrentType();
		if(MemberIdentity_Type.elite.name().equals(currentType)||MemberIdentity_Type.cto.name().equals(currentType)){
			return "/index/hall/task_hall";
		}else{
			return REDIRECT_COMMAND+"/member/index";
		}
	}
	
	/**
	 * 任务大厅的可竞标项目列表
	 * */
	@RequestMapping(value="/hall/project/listData",method=RequestMethod.POST)
	public String hallProjectListData(String projectSolution,String status,Pager pager,Model model){
		SearchResult<Project> sr=this.projectService.findProjectListAsHall(projectSolution, status, pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination",pager);
		return "/index/hall/project_list_frag";
	}
	
	/**
	 * 任务大厅的可招募任务列表
	 * */
	@RequestMapping(value="/hall/task/listData",method=RequestMethod.POST)
	public String hallTaskListData(String taskType,String demandType,String status,Pager pager,Model model){
		Long projectId=null;BigDecimal minAmount=null;BigDecimal maxAmount=null;Long stageId=null;
		pager.setPageSize(12);
		SearchResult<ProjectStageTask> sr=this.projectStageTaskService.findProjectStageTaskList(projectId, taskType,demandType,minAmount, maxAmount, stageId, status, pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination",pager);
		return "/index/hall/task_list_frag";
	}
	
	/**
	 * 查看任务详情
	 * */
	@RequestMapping(value="/hall/task/{id}",method=RequestMethod.GET)
	public String hallTaskDetail(@PathVariable Long id,Model model){
		ProjectStageTask task=this.projectStageTaskService.findProjectStageTaskById(id);
		if(task==null){
			return NOT_FOUND_VIEW;
		}
		Integer applyCount=this.projectTaskRecruitService.findProjectTaskApplyCountByTaskId(id);
		Long memberId=principalService.getPrincipalId();
		String applyFlag="N";
		if(memberId!=null){
			ProjectTaskRecruit apply=this.projectTaskRecruitService.findProjectTaskRecruit(memberId,id);
			if(apply!=null && !apply.getStatus().equals(ProjectTaskRecruit_Status.recruit_cancel)){
				applyFlag="Y";
			}
		}
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		model.addAttribute("applyFlag", applyFlag);
		model.addAttribute("applyCount",applyCount);
		model.addAttribute("obj", task);
		model.addAttribute("project", task.getProject());
		model.addAttribute("deadlineTime", task.getEndTime()==null?0l:(task.getEndTime().getTime()/1000-new Date().getTime()/1000));
		return "/index/hall/view_task";
	}
	
	
	/**
	 * 精英圈
	 * */
	@RequestMapping(value="/circle",method=RequestMethod.GET)
	public String eliteCircle(Model model){
		Long memberId=principalService.getPrincipalId();
		if(memberId!=null){
			Integer unreadCount=this.memberMessageService.findMemberMessageCount(memberId,null,MemberMessage_Status.unread.name(),null);
			model.addAttribute("unreadCount", unreadCount);
		}
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		Integer count=this.memberPassportService.findMemberEliteCount();
		model.addAttribute("count", count);
		return "/index/circle/list";
	}
	
	/**
	 * 精英圈列表查询
	 * */
	@RequestMapping(value="/circle/listData",method=RequestMethod.POST)
	public String eliteCircleListData(String keyword, String role,String jobAge,String duration,String industry,Pager pager,Model model){
		SearchResult<MemberPassport>  sr=this.memberPassportService.findMemberPassportListByEliteCircle(principalService.getPrincipalId(),keyword, role, jobAge, duration, industry, pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination",pager);
		return "/index/circle/list_frag";
	}
	
	/**
	 * 精英详情
	 * */
	@CountStatistics(type=StatisticsEnum.memberViewCount,param={"id"})
	@RequestMapping(value="/circle/view/{id}",method=RequestMethod.GET)
	public String eliteDetail(@PathVariable Long id,Model model){
		MemberDetailInfo member=this.memberPassportService.findMemberDetailInfoById(id);
		if(!Member_Status.normal.equals(member.getElite().getStatus())
				||(MemberIdentity_Type.elite.name().equals(member.getCurrentType())&&MemberIdentity_Type.cto.name().equals(member.getCurrentType()))){
			return ERROR_VIEW;
		}
		//如果是cto则查询其发布的任务数量
		if(MemberIdentity_Type.cto.equals(member.getCurrentType())){
			Integer publishTaskCount=this.projectStageTaskService.findProjectStageTaskCount(member.getId());
			member.setPublishTaskCount(publishTaskCount);
		}
		Long memberId=this.principalService.getPrincipalId();
		if(memberId!=null){
			MemberAttention maObj=this.memberAttentionService.checkAttentioned(memberId, member.getId());
			member.setAttentioned(maObj!=null);
		}
		List<MemberAttention> al=memberAttentionService.findAttenTionUsers(id);
		
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		model.addAttribute("obj", member);
		model.addAttribute("asize", al==null?0:al.size());//他的关注数量
		return "/index/circle/view";
	}
	
	/**
	 * 精英详情，他的关注列表
	 * */
	@RequestMapping(value="/circle/view/attention",method=RequestMethod.POST)
	public String invitePage(Long memberId,Pager pager,Model model){
		SearchResult<MemberAttention> sr = memberAttentionService.findAttenTionUser("attentioned", memberId, null, pager);
		Long cmId=this.principalService.getPrincipalId();//当前用户id
		for (MemberAttention m : sr.getResult()) {
			MemberTeam team = memberTeamService.findMemberTeamByMemberId(m.getMemberId(),m.getAttentionMemberId());
			Long Id =m.getAttentionMemberId();
			Integer count = memberAttentionService.findAttenTionedUserCount(Id);
			MemberDetailInfo memberDetailInfo =  memberPassportService.findMemberDetailInfoById(Id);
			if (cmId != null) {
				MemberAttention maObj = this.memberAttentionService.checkAttentioned(cmId, m.getAttentionMemberId());
				memberDetailInfo.setAttentioned(maObj != null);
			}
			m.setDetailInfo(memberDetailInfo);
			m.setAttenionCount(count);
			m.setPartnershipNum(team==null?0:team.getPartnershipNum());
		}
		model.addAttribute("list",sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/index/circle/view_frag";
	}
	
	/**
	 * 我的邀请码界面
	 * */
	@RequestMapping(value="/invite",method=RequestMethod.GET)
	public String invitePage(Model model){
		Long memberId=this.principalService.getPrincipalId();
		if(memberId!=null){
			PlatformInviteCode obj=this.platformInviteCodeService.findNewPlatformInviteCodeByUserId(memberId);
			model.addAttribute("obj", obj);
		}
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		return "/index/inviteCode";
	}
	
	/**
	 * 保存反馈记录
	 * */
	@RequestMapping(value="/feedback/save",method=RequestMethod.POST)
	public ResponseResult<String> saveFeedback(PlatformFeedback obj){
		this.platformFeedbackService.createPlatformFeedback(obj);
		return new ResponseResult<>();
	}
	
	/**
	 * 各个身份的介绍页面
	 * */
	@RequestMapping(value="/introduce/{type}",method=RequestMethod.GET)
	public String introduce(@PathVariable String type,Model model){
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		return "/index/introduce/"+type;
	}
	
	
	/**
	 * 底部友情链接
	 * */
	@RequestMapping(value="/index/{type}",method=RequestMethod.GET)
	public String footerLink(@PathVariable String type,Model model){
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		return "/index/blogroll/"+type;
	}
	
	/**
	 * 类型协议
	 * */
	@RequestMapping(value="/protocol/{type}",method=RequestMethod.GET)
	public String index(@PathVariable String type){
		return "/index/protocol/"+type;
	}

}
