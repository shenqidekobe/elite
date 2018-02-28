package com.ledao.elite.site.controller.project;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberRecommend;
import com.ledao.elite.core.domain.project.ProjectLog;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectMaterial.ProjectMaterial_Status;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.domain.project.ProjectWeekly;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Type;
import com.ledao.elite.core.framework.annotation.ESChain;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.dto.site.WeeklyKey;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberRecommendService;
import com.ledao.elite.core.service.project.ProjectLogService;
import com.ledao.elite.core.service.project.ProjectMaterialService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectStageTaskService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.core.service.project.ProjectWeeklyService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.ResponseResult;

@Controller("taskEliteController")
@RequestMapping("/task")
public class TaskEliteController extends BaseController {

	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource
	private ProjectStageTaskService projectStageTaskService;
	@Resource
	private MemberRecommendService memberRecommendService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private ProjectMaterialService projectMaterialService;
	@Resource
	private ProjectWeeklyService projectWeeklyService;
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectLogService projectLogService;

	/**
	 * 精英-我的任务列表
	 */
	@ESChain(ref = "validElite")
	@SSOToken
	@RequestMapping(value = "/task_list", method = RequestMethod.POST)
	public String myTaskOfUnder_way(Model model, Pager pager, String type) {
		String url = "";
		SearchResult<ProjectTaskRecruit> results = new SearchResult<>();
		if (type.equals("recomd_me")) {// 待领取
			SearchResult<MemberRecommend> result = memberRecommendService.findRecommendTaskList(getMemberId(), pager);
			pager.calPageCount((long) result.getTotalCount());
			model.addAttribute("list", result.getResult());
			url = "recommend";
		} else {
			if (type.equals("under_way")) {// 认领中
				url = "frag";
				results = projectTaskRecruitService.findProjectTaskRecruitList(getMemberId(), null, ProjectTaskRecruit.ProjectTaskRecruit_Status.recruit_in.name(), pager);
			} else if (type.equals("under_finish")) {// 已领取
				url = "claim";
				results = projectTaskRecruitService.findClaimProjectTaskRecruitList(getMemberId(), pager);
			}
			pager.calPageCount((long) results.getTotalCount());
			model.addAttribute("list", results.getResult());

		}
		
		Integer recomCount = memberRecommendService.findRecommendTaskCount(getMemberId());
		Integer recruitInCount = projectTaskRecruitService.findProjectTaskRecruitCount(getMemberId(),
				ProjectTaskRecruit_Status.recruit_in);
		Integer recruitWinCount = projectTaskRecruitService.findClaimProjectTaskRecruitCount(getMemberId());
		model.addAttribute("recomCount", recomCount);
		model.addAttribute("recruitWinCount", recruitWinCount);
		model.addAttribute("recruitInCount", recruitInCount);
		
		model.addAttribute("pagination", pager);
		getSize(model, pager);
		return "/member/elite/main/task_list_" + url;
	}

	/**
	 * 统计各个栏目的数量
	 * 
	 * @param model
	 * @return
	 */
	@ESChain(ref = "validElite")
	@SSOToken
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public String taskListCount(Model model) {
		Integer recomCount = memberRecommendService.findRecommendTaskCount(getMemberId());
		Integer recruitWinCount = projectTaskRecruitService.findProjectTaskRecruitCount(getMemberId(),
				ProjectTaskRecruit_Status.recruit_win);
		Integer recruitInCount = projectTaskRecruitService.findProjectTaskRecruitCount(getMemberId(),
				ProjectTaskRecruit_Status.recruit_in);
		model.addAttribute("recomCount", recomCount);
		model.addAttribute("recruitWinCount", recruitWinCount);
		model.addAttribute("recruitInCount", recruitInCount);
		return "/member/elite/main/task_list";
	}

	/**
	 * 精英-任务主页
	 */
	@ESChain(ref = "validElite")
	@SSOToken
	@RequestMapping(value = "/{taskId}/index", method = RequestMethod.GET)
	public String taskDetails(@PathVariable Long taskId, Model model, Pager pager) {
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(taskId);
		ProjectTaskRecruit recruit = projectTaskRecruitService.findProjectTaskRecruit(getMemberId(), taskId);
		if(recruit==null){
			return UNAUTHORIZED_VIEW;
		}
		examineIsUnreadMessage(model, true);
		model.addAttribute("obj", projectStageTask);
		model.addAttribute("taskId", taskId);
		model.addAttribute("recruit", recruit);
		return "/member/elite/task/index";
	}

	/**
	 * 精英-任务详情
	 */
	@ESChain(ref = "validElite")
	@SSOToken
	@RequestMapping(value = "/viewTask", method = RequestMethod.POST)
	public String viewTask(Long taskId, Model model, Pager pager) {
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(taskId);
		MemberRecommend recom = memberRecommendService.findRecommendTask(getMemberId(), taskId);
		ProjectTaskRecruit recruit = projectTaskRecruitService.findProjectTaskRecruit(getMemberId(), taskId);
		Integer applyCount = this.projectTaskRecruitService.findProjectTaskApplyCountByTaskId(taskId);
		String applyFlag = "N";
		ProjectTaskRecruit apply = this.projectTaskRecruitService.findProjectTaskRecruit(getMemberId(), taskId);
		if (apply != null) {
			applyFlag = "Y";
		}
		Date time=projectStageTask.getStatus().equals(ProjectTask_Status.quality)?projectStageTask.getAcceptTime():projectStageTask.getEndTime();
		model.addAttribute("deadlineTime",(time.getTime() / 1000 - new Date().getTime() / 1000));
		model.addAttribute("applyFlag", applyFlag);
		model.addAttribute("applyCount", applyCount);
		model.addAttribute("recom", recom);
		model.addAttribute("recruit", recruit);
		model.addAttribute("obj", projectStageTask);
		return "/member/elite/task/task_view";
	}

	public void getSize(Model model, Pager pager) {
		model.addAttribute("recommendSize",
				memberRecommendService.findRecommendTaskList(getMemberId(), pager).getResult().size());
		model.addAttribute("fragSize", projectTaskRecruitService
				.findProjectTaskRecruitList(getMemberId(), null, "recruit_in", pager).getResult().size());
		model.addAttribute("claimSize", projectTaskRecruitService
				.findProjectTaskRecruitList(getMemberId(), null, "recruit_win", pager).getResult().size());
	}

	/**
	 * 精英-任务计划
	 * 
	 */
	@ESChain(ref = "validElite")
	@SSOToken
	@RequestMapping(value = "/task_plan", method = RequestMethod.POST)
	public String taskPlan(Model model, Long taskId) {
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(taskId);

		model.addAttribute("obj", projectStageTask.getStage());
		return "/member/elite/main/task_plan";
	}

	/**
	 * 精英-报名or取消报名
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public ResponseResult<String> apply(Long taskId, String oper, Model model, Pager pager) {
		if (!getMemberCurrentType().equals(MemberIdentity_Type.elite.name())
				&& !getMemberCurrentType().equals(MemberIdentity_Type.cto.name())) {
			return new ResponseResult<>("请注册成为云英汇精英再来报名");
		}
		MemberElite elite = this.memberEliteService.findMemberEliteByMemberId(getMemberId());
		if (elite == null || !Member_Status.normal.equals(elite.getStatus())) {
			return new ResponseResult<>("您的信息还未审核通过，请耐心等待");
		}
//		else if(!elite.isTaked()){
//			return new ResponseResult<>("您正处于休息中，不能接活");
//		}
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(taskId);
		if (!projectStageTask.getStatus().equals(ProjectTask_Status.wait_recruit)
				&& !projectStageTask.getStatus().equals(ProjectTask_Status.recruit_in)) {
			return new ResponseResult<>("任务已经报名结束，请关注其他任务");
		}
		Date currentTime = new Date();
		if (projectStageTask.getEndTime()==null||currentTime.after(projectStageTask.getEndTime())) {
			return new ResponseResult<>("任务已经报名结束，请关注其他任务");
		}
		ProjectTaskRecruit obj = projectTaskRecruitService.findProjectTaskRecruit(getMemberId(), taskId);
		Integer applyCount = this.projectTaskRecruitService.findProjectTaskApplyCountByTaskId(taskId);
		if (obj != null) {
			if (obj.getStatus().equals(ProjectTaskRecruit_Status.recruit_cancel)) {
				projectTaskRecruitService.updateProjectTaskRecruitStatus(obj.getId(), "recruit_in");
				applyCount++;
			} else {
				projectTaskRecruitService.updateProjectTaskRecruitStatus(obj.getId(), "recruit_cancel");
				applyCount--;
				// 系统消息
				StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_APPLY_TASK_CANCEL);
				String title = String.format(message.getKey(), projectStageTask.getName());
				String content = String.format(message.getValue(), projectStageTask.getName());
				eventPublishService.publishSysMessageEvent(getMemberId(), null, title, content, false);
			}
		} else {
			ProjectTaskRecruit projectTaskRecruit = new ProjectTaskRecruit();
			projectTaskRecruit.setMemberId(getMemberId());
			projectTaskRecruit.setTaskId(taskId);
			projectTaskRecruit.setStatus(ProjectTaskRecruit.ProjectTaskRecruit_Status.recruit_in);
			projectTaskRecruit.setStageId(projectStageTask.getStageId());
			projectTaskRecruit.setAmount(projectStageTask.getAmount());
			projectTaskRecruit.setRemd(true);
			projectTaskRecruit.setRemdUser(projectTaskRecruit.getRemdUser());
			projectTaskRecruit.setProjectId(projectStageTask.getProjectId());
			projectTaskRecruitService.createProjectTaskRecruit(projectTaskRecruit);
			applyCount++;
			projectStageTask.setStatus(ProjectTask_Status.recruit_in);
			this.projectStageTaskService.updateProjectStageTask(projectStageTask);
			// 报名
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, projectStageTask.getProjectId(), projectStageTask.getProject().getForStage().getStageCode(),
					projectStageTask.getId(), getMemberId(), projectStageTask.getProject().getForStage().getTitle(), "我申请了" + projectStageTask.getName() + "任务", null);
			// 系统消息
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_APPLY_TASK_COMPLETE);
			String title = String.format(message.getKey(), projectStageTask.getName());
			String replace = "<a href=" + getServerUrl() + "+\"/hall\">任务大厅~</a>";
			String content = String.format(message.getValue(), projectStageTask.getName(), replace);
			eventPublishService.publishSysMessageEvent(getMemberId(), null, title, content, false);
		}
		if (applyCount < 0) {
			applyCount = 0;
		}
		return new ResponseResult<>(Long.valueOf(applyCount));
	}

	/**
	 * 精英文件列表
	 */
	@ESChain(ref = "validElite")
	@SSOToken
	@RequestMapping(value = "/material", method = RequestMethod.POST)
	public String materialList(Model model, Long projectId, Long taskId) {
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
		Integer receiveCount = this.projectMaterialService.findProjectMaterialCount(taskId, projectId, null,
				getMemberId(), ProjectMaterial.QUERYTYPE_RECEIVE);
		Integer sendCount = this.projectMaterialService.findProjectMaterialCount(taskId, projectId, null, getMemberId(),
				ProjectMaterial.QUERYTYPE_SEND);
		Integer unreadCount = this.projectMaterialService.findProjectMaterialCount(taskId, projectId, null,
				getMemberId(), ProjectMaterial.QUERYTYPE_UNREAD);
		model.addAttribute("receiveCount", receiveCount);
		model.addAttribute("sendCount", sendCount);
		model.addAttribute("unreadCount", unreadCount);
		model.addAttribute("project", task.getProject());
		model.addAttribute("taskId", taskId);
		return "/member/elite/task/material_list";
	}

	/**
	 * 精英项目文件列表数据查询
	 */
	@SSOToken
	@RequestMapping(value = "/material/listData", method = RequestMethod.POST)
	public String materialListData(Long taskId, Long stageId, String queryType, Pager pager, Model model) {

		SearchResult<ProjectMaterial> sr = this.projectMaterialService.findProjectMaterialList(taskId, null, stageId,
				getMemberId(), queryType, pager);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		model.addAttribute("queryType", queryType);
		return "/member/elite/task/material_list_frag";
	}

	/**
	 * 保存文件信息
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/material/save", method = RequestMethod.POST)
	public ResponseResult<String> saveProjectMaterial(Long receiveId, Long projectId, Long stageId, Long attaId,
			String status, Long taskId, @RequestParam(value = "isDelivery", required = false) boolean isDelivery) {
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
		MemberPassport member = memberPassportService.findMemberPassportById(getMemberId());
		ProjectMaterial obj = new ProjectMaterial();
		obj.setTaskId(taskId);
		obj.setProjectId(task.getProjectId());
		obj.setReceiveId(receiveId);
		obj.setStageId(stageId);
		obj.setAttaId(attaId);
		obj.setUploadId(getMemberId());
		obj.setDeliveryed(isDelivery);
		obj.setStatus(ProjectMaterial_Status.valueOf(ProjectMaterial_Status.class, status));
		//cto在任务详情中发送文件时(不包含此任务还没指定的和)
		if(member.getCurrentType().equals("cto") && receiveId.equals(getMemberId())){
			obj.setReceiveId(task.getEliteMemberId());
			
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, projectId,
					task.getProject().getForStage().getStageCode(), task.getId(),  obj.getUploadId(), task.getProject().getForStage().getTitle(),
					"我上传了给精英" +task.getName() + "任务的文件", obj.getAttaId());
			
			// 发送给精英
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_ACCEPT_MATERIAL);
			String replace = "<a href='" + localCoreConfig.getDomainServer() + "/project/c/" + projectId + "/index?rp=material' target='_blank'>去看看~</a>";
			String content = String.format(message.getValue(), task.getProject().getName(), replace);
			eventPublishService.publishMessageEvent(task.getEliteMemberId(), task.getProjectId(),getMemberId(), message.getKey(),
					content, false,MemberMessage_Type.project);
		}else{
			//精英发送文件时任务状态变为待验收
			if (task.getStatus().equals(ProjectTask_Status.starting)) {
				task.setStatus(ProjectTask_Status.wait_accept);
			}
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, obj.getProjectId(),
					task.getProject().getForStage().getStageCode(), task.getId(), obj.getUploadId(), task.getProject().getForStage().getTitle(),
					"我上传了" + task.getName() + "任务的文件", obj.getAttaId());
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_ACCEPT_MATERIAL);
			String url = localCoreConfig.getDomainServer();
			String replace = "<a href='" + url + "/project/c/" + projectId + "/index?rp=material' target='_blank'>去看看~</a>";
			String content = String.format(message.getValue(), task.getProject().getName(), replace);
			eventPublishService.publishMessageEvent(task.getProject().getCtoId(), task.getProjectId(), getMemberId(),
					message.getKey(), content, false, MemberMessage_Type.project);
		}
		this.projectMaterialService.createProjectMaterialForElite(obj, task);
		return new ResponseResult<>();
	}

	/**
	 * 项目周报列表
	 */
	@ESChain(ref = "validElite")
	@SSOToken
	@RequestMapping(value = "/weekly", method = RequestMethod.POST)
	public String weeklyList(Long taskId, Integer month, Model model) {
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
		//默认获取当前月份
		Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        model.addAttribute("currentMonth", currentMonth);
        //周报可选时间范围
        Date start=task.getProject().getStartTime();
        start=start==null?new Date():start;
        Date end=task.getProject().getDeliveryTime();
        end=end==null?new Date():end;
        model.addAttribute("startTime",DateTimeUtils.monthFormat.format(start));
        model.addAttribute("endTime",DateTimeUtils.monthFormat.format(end));
        model.addAttribute("taskId", taskId);
        return "/member/elite/task/weekly_list";
        
	}

	/**
	 * 精英项目周报列表
	 */
	@ESChain(ref = "validElite")
	@SSOToken
	@RequestMapping(value = "/weekly/listData", method = RequestMethod.POST)
	public String weeklyListData(Long taskId, Long projectId, Integer month, Model model) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		Map<WeeklyKey, List<ProjectWeekly>> map = this.projectWeeklyService.findProjectWeeklyListAsSend(taskId,
				projectId, getMemberId(), month);
		
		Integer count = this.projectWeeklyService.findProjectWeeklyCountAsSend(taskId, null, getMemberId(),
				ProjectWeekly_Type.cto, null, month);
		model.addAttribute("count", count);
		
		model.addAttribute("projectId", projectId);
		model.addAttribute("weeklyMap", map);
		model.addAttribute("month", cal.getTime());
		return "/member/elite/task/weekly_list_frag";
	}

	/**
	 * 精英发送CTO项目周报
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/weekly/save", method = RequestMethod.POST)
	public ResponseResult<String> saveWeeklyData(Long taskId, Long projectId, Long attaId, Date weeklyTime,
			Model model) {
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);

		ProjectWeekly weekly = new ProjectWeekly();
		weekly.setTaskId(taskId);
		weekly.setWeeklyTime(weeklyTime);
		weekly.setAttaId(attaId);
		weekly.setProjectId(task.getProjectId());
		weekly.setReceiveId(task.getProject().getCtoId());
		weekly.setCreateId(getMemberId());
		weekly.setWeeklyType(ProjectWeekly_Type.cto);
		projectWeeklyService.createProjectWeekly(weekly);
		return new ResponseResult<>();
	}

	/**
	 * 任务日志列表
	 */
	@SSOToken
	@RequestMapping(value = "/log", method = RequestMethod.POST)
	public String projectLogList(Long taskId, Model model) {
		ProjectStageTask obj = projectStageTaskService.findProjectStageTaskById(taskId);
		Integer count = this.projectLogService.findTaskLogCount(taskId, getMemberId());
		model.addAttribute("count", count);
		model.addAttribute("obj", obj);
		return "/member/elite/task/project_log_list";
	}

	/**
	 * 项目任务日志列表数据查询
	 */
	@SSOToken
	@RequestMapping(value = "/log/listData", method = RequestMethod.POST)
	public String projectLogListData(Long taskId, String stageCode, String startTime, String endTime, String keyword,
			Pager pager, Model model) {
		pager = new Pager(0,50);
		Date st = null, et = null;
		try {
			if (StringUtils.isNotEmpty(startTime)) {
				st = DateUtils.parseDate(startTime, GlobalDefinedConstant.System_Date_Patten.DATE.patten);
			}
			if (StringUtils.isNotEmpty(endTime)) {
				et = DateUtils.parseDate(endTime, GlobalDefinedConstant.System_Date_Patten.DATE.patten);
			}
		} catch (ParseException e) {
		}
		SearchResult<ProjectLog> sr = this.projectLogService.findTaskLogList(taskId, getMemberId(), stageCode, keyword,
				st, et, pager);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		return "/member/elite/task/project_log_list_frag";
	}

}
