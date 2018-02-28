package com.ledao.elite.site.controller.project;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberRecommend;
import com.ledao.elite.core.domain.member.MemberRecommend.MemberRecommend_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectMaterial.ProjectMaterial_Status;
import com.ledao.elite.core.domain.project.ProjectSettingStage;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.ProjectTender.ProjectTender_Status;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.domain.project.ProjectWeekly;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Type;
import com.ledao.elite.core.framework.annotation.ESChain;
import com.ledao.elite.core.framework.annotation.ProjectNewNotices;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.NoticeTypeEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.dto.site.WeeklyKey;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberRecommendService;
import com.ledao.elite.core.service.member.MemberTeamService;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectLogService;
import com.ledao.elite.core.service.project.ProjectMaterialService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectSettingStageService;
import com.ledao.elite.core.service.project.ProjectStageTaskService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.core.service.project.ProjectTenderRecordService;
import com.ledao.elite.core.service.project.ProjectTenderService;
import com.ledao.elite.core.service.project.ProjectWeeklyService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.MemberMap;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * CTO接标项目处理器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectCTOController")
@RequestMapping("/project/c")
public class ProjectCTOController extends BaseController {

	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectDefineService projectDefineService;
	@Resource
	private ProjectTenderService projectTenderService;
	@Resource
	private ProjectTenderRecordService projectTenderRecordService;
	@Resource
	private ProjectSettingStageService projectSettingStageService;
	@Resource
	private ProjectStageTaskService projectStageTaskService;
	@Resource
	private MemberRecommendService memberRecommendService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private MemberTeamService memberTeamService;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource
	private ProjectMaterialService projectMaterialService;
	@Resource
	private ProjectWeeklyService projectWeeklyService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private ProjectLogService projectLogService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;

	/**
	 * 查看项目招标书
	 */
	@SSOToken
	@RequestMapping(value = "/{projectId}/tender", method = RequestMethod.GET)
	public String viewProjectTender(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		ProjectTender tender = this.projectTenderService.findProjectTenderByProjectId(projectId);
		if(!tender.getStatus().equals(ProjectTender_Status.tender_in)){
			return UNAUTHORIZED_VIEW;
		}
		examineIsUnreadMessage(model, true);
		model.addAttribute("obj", project);
		model.addAttribute("tender", tender);
		return "/member/cto/global/look_tender";
	}

	/**
	 * CTO项目竞标
	 */
	@SSOToken
	@RequestMapping(value = "{projectId}/bid", method = RequestMethod.GET)
	public String projectTenderBid(@PathVariable Long projectId, Model model) {
		ProjectTender tender = this.projectTenderService.findProjectTenderByProjectId(projectId);
		if(!tender.getStatus().equals(ProjectTender_Status.tender_in)){
			return UNAUTHORIZED_VIEW;
		}
		Integer month = DateTimeUtils.getMonth(tender.getQualityTime(), tender.getEndTime());
		List<ProjectSettingStage> stageList = this.projectSettingStageService.findSettingStagesByProjectId(projectId);
		examineIsUnreadMessage(model, true);
		model.addAttribute("obj", tender);
		model.addAttribute("month", month);
		model.addAttribute("stageList", stageList);
		return "/member/cto/global/bid_tender";
	}

	/**
	 * 保存CTO竞标记录
	 */
	@SSOToken
	@ResponseBody
	@ProjectNewNotices(controllerType = "add", noticeType = NoticeTypeEnum.CtoTender)
	@RequestMapping(value = "/bid/save", method = RequestMethod.POST)
	public ResponseResult<String> saveProjectTenderBid(ProjectTenderRecord obj, String recordStages, Long projectId) {
		ProjectTender tender = projectTenderService.findProjectTenderById(obj.getTenderId());
		ProjectTenderRecord record = this.projectTenderRecordService.findProjectTenderRecordByProject(obj.getTenderId(),
				obj.getProjectId(), getMemberId());
		ResponseResult<String> rsp = new ResponseResult<>();
		if (record != null) {
			rsp.isSuccess(false);
		} else {
			obj.setMemberId(getMemberId());
			obj = this.projectTenderRecordService.createProjectTenderRecord(obj, recordStages);
			// 发布竞标日志
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, obj.getProjectId(), null, null,
					getMemberId(), "立项阶段", "我发送了" + tender.getProject().getName() + "项目竞标书", null);
			// 发布竞标消息
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_TENDER_COMPLETE);
			String replace = "<a href='" + getServerUrl() + "/project/c/" + projectId + "/index'>查看详情</a>";
			String content = String.format(message.getValue(), tender.getProject().getName(), replace);
			eventPublishService.publishMessageEvent(getMemberId(), projectId, null, message.getKey(), content, false,
					MemberMessage_Type.system);
			// 发送给项目经理
			StringKeyValue messagepm = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PM_TENDER_APPLY);
			String title = String.format(messagepm.getKey(), tender.getProject().getName());
			String replacepm = "<a href='" + getServerUrl() + "/manager/project/pm'>看看吧</a>~";
			String contentpm = String.format(messagepm.getValue(), replacepm);
			eventPublishService.publishSysMessageEvent(tender.getProject().getPmId(), null, title, contentpm, false);
		}
		return rsp;
	}

	/**
	 * CTO查看项目定标书
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "{projectId}/define", method = RequestMethod.GET)
	public String viewProjectDefine(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		ProjectDefine define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.cto,
				ProjectDefine_Status.wait);
		ProjectTender tender = this.projectTenderService.findProjectTenderByProjectId(projectId);
		ProjectTenderRecord tenderRecord = this.projectTenderRecordService
				.findProjectTenderRecordByProject(tender.getId(), projectId, getMemberId());
		if(!tenderRecord.getDefineId().equals(define.getId())){
			return UNAUTHORIZED_VIEW;
		}
		BigDecimal percent = localCoreConfig.getQualityAmountPercent();
		examineIsUnreadMessage(model, true);
		model.addAttribute("obj", project);
		model.addAttribute("percent", percent);
		model.addAttribute("define", define);
		model.addAttribute("tenderRecord", tenderRecord);
		return "/member/cto/global/affirm_define";
	}

	/**
	 * 项目的主页
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/{projectId}/index", method = RequestMethod.GET)
	public String projectIndex(@PathVariable Long projectId, Model model) {
		ProjectTenderRecord record = projectTenderRecordService.findProjectTenderRecordByMemberId(projectId,
				getMemberId());
		Project project = projectService.findProjectById(projectId);
		examineIsUnreadMessage(model, true);
		model.addAttribute("project", project);
		model.addAttribute("record", record);
		return "/member/cto/project/index";
	}

	/**
	 * CTO查看项目详情
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/viewProject", method = RequestMethod.POST)
	public String viewProject(Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		ProjectDefine define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.cto, null);
		List<ProjectDefineStage> stageList = new ArrayList<ProjectDefineStage>();

		if (define != null) {
			define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.company, null);
			if (define != null) {
				stageList = projectDefineStageService.findProjectDefineStageByDefinedId(define.getId());
			}
		}

		List<ProjectSettingStage> settingList = projectSettingStageService.findSettingStagesByProjectId(projectId);
		MemberRecommend recommend = memberRecommendService.findRecommendProject(getMemberId(), projectId);
		// 竞标中
		ProjectTenderRecord tenderrecord = projectTenderRecordService.findProjectTenderRecordByMemberId(projectId,
				getMemberId());
		MemberElite elite = this.memberEliteService.findMemberEliteByMemberId(getMemberId());
		BigDecimal percent = localCoreConfig.getQualityAmountPercent();

		model.addAttribute("obj", project);
		model.addAttribute("define", define);
		model.addAttribute("settingList", settingList);
		model.addAttribute("stageList", stageList);
		model.addAttribute("tenderRecord", tenderrecord);
		model.addAttribute("recommend", recommend);
		model.addAttribute("elite", elite);
		model.addAttribute("percent", percent);
		return "/member/cto/project/project_view";
	}

	/**
	 * CTO查看任务详情
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/viewProjectTask", method = RequestMethod.POST)
	public String viewProjectTask(Long projectId, Pager pager, Model model) {
		Project project = projectService.findProjectById(projectId);
		ProjectTenderRecord record = projectTenderRecordService.findProjectTenderRecordByMemberId(projectId,
				getMemberId());
		SearchResult<ProjectStageTask> result = projectStageTaskService.findProjectStageTaskListByProjectId(projectId,
				pager);
		model.addAttribute("list", result.getResult());
		model.addAttribute("record", record);
		model.addAttribute("obj", project);
		pager.calPageCount((long) result.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/cto/project/task_assign";
	}

	/**
	 * CTO项目文件列表
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/material", method = RequestMethod.POST)
	public String materialList(Model model, Long projectId) {
		Project project = projectService.findProjectById(projectId);
		Integer receiveCount = this.projectMaterialService.findProjectMaterialCount(null, projectId, null,
				getMemberId(), ProjectMaterial.QUERYTYPE_RECEIVE);
		Integer sendCount = this.projectMaterialService.findProjectMaterialCount(null, projectId, null, getMemberId(),
				ProjectMaterial.QUERYTYPE_SEND);
		Integer unreadCount = this.projectMaterialService.findProjectMaterialCount(null, projectId, null, getMemberId(),
				ProjectMaterial.QUERYTYPE_UNREAD);
		model.addAttribute("receiveCount", receiveCount);
		model.addAttribute("sendCount", sendCount);
		model.addAttribute("unreadCount", unreadCount);
		model.addAttribute("project", project);
		return "/member/cto/project/material_list";
	}

	/**
	 * cto项目文件列表数据查询
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/material/listData", method = RequestMethod.POST)
	public String materialListData(Long projectId, Long stageId, String queryType, Pager pager, Model model) {
		pager = new Pager(0, 50);
		SearchResult<ProjectMaterial> sr = this.projectMaterialService.findProjectMaterialList(null, projectId, stageId,
				getMemberId(), queryType, pager);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		model.addAttribute("queryType", queryType);
		return "/member/cto/project/material_list_frag";
	}

	/**
	 * 保存文件信息
	 */
	@SSOToken
	@ResponseBody
	@ProjectNewNotices(controllerType = "add", noticeType = NoticeTypeEnum.Material)
	@RequestMapping(value = "/material/save", method = RequestMethod.POST)
	public ResponseResult<String> saveProjectMaterial(Long receiveId, Long projectId, Long stageId, Long attaId,
			String status, @RequestParam(value = "isDelivery", required = false) boolean isDelivery) {
		Project project = projectService.findProjectById(projectId);
		MemberPassport member = memberPassportService.findMemberPassportById(receiveId);
		ProjectMaterial obj = new ProjectMaterial();
		obj.setProjectId(projectId);
		obj.setReceiveId(receiveId);
		obj.setStageId(stageId);
		obj.setAttaId(attaId);
		obj.setUploadId(getMemberId());
		obj.setDeliveryed(isDelivery);
		obj.setStatus(ProjectMaterial_Status.valueOf(ProjectMaterial_Status.class, status));
		this.projectMaterialService.createProjectMaterial(obj);
		if(member!=null && !member.getCurrentType().equals("elite")){
			// 发送pm文件
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, projectId,
					project.getForStage()==null?"立项阶段":project.getForStage().getStageCode(), null, getMemberId(), project.getForStage().getTitle(),
					"我提交了" + project.getName()+ "项目文件", null);
			// 发送给pm
			StringKeyValue messagepm = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PM_ACCEPT_MATERIAL);
			String replacepm = "<a href='" + getServerUrl() + "/manager/project/pm'>查看</a>";
			String contentpm = String.format(messagepm.getValue(), project.getName(), replacepm);
			eventPublishService.publishSysMessageEvent(project.getPmId(), null, messagepm.getKey(),
					contentpm, false);
		}else{
			//发送给精英文件
		}
		
		return new ResponseResult<>();
	}

	/**
	 * 下载文件
	 */
	@SSOToken
	@RequestMapping(value = "/read/material/{id}", method = RequestMethod.GET)
	public String downMaterial(@PathVariable Long id) {
		ProjectMaterial material = projectMaterialService.findProjectMaterialById(id);
		if (material == null || material.getAttaId() == null) {
			return ERROR_VIEW;
		}
		material.setRead(true);
		this.projectMaterialService.updateProjectMaterialNoFixed(material);
		return REDIRECT_COMMAND + material.getAtta().getDownPath();
	}

	/**
	 * 项目周报列表
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/weekly", method = RequestMethod.POST)
	public String weeklyList(Long taskId, Long projectId, Integer month, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		MemberPassport member = memberPassportService.findMemberPassportById(project.getCompanyId());
		model.addAttribute("obj", project);
		//默认获取当前月份
		Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        model.addAttribute("currentMonth", currentMonth);
        //周报可选时间范围
        Date start=project.getStartTime();
        start=start==null?new Date():start;
        Date end=project.getDeliveryTime();
        end=end==null?new Date():end;
        model.addAttribute("member", member);
        model.addAttribute("startTime",DateTimeUtils.monthFormat.format(start));
        model.addAttribute("endTime",DateTimeUtils.monthFormat.format(end));
        return "/member/cto/project/weekly_list";
	}

	/**
	 * 项目周报列表查询
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/weekly/listData", method = RequestMethod.POST)
	public String weeklyListData(Long taskId, Long projectId, Integer month, String type, Model model) {
		Calendar cal = Calendar.getInstance();
//		ProjectTenderRecord record = projectTenderRecordService.findProjectTenderRecordByMemberId(projectId,getMemberId());
		// 要发周报的人
		List<ProjectStageTask> list = projectStageTaskService.findProjectStageTask(null,projectId);
		cal.set(Calendar.MONTH, month - 1);
		//
		Map<WeeklyKey, List<ProjectWeekly>> map = new HashMap<>();
		//cto发送的周报
		if(type.equals(ProjectWeekly_Type.company.name())){
			map = this.projectWeeklyService.findProjectWeeklyListAsSend(taskId,projectId, getMemberId(), month);
		}else{
			map = this.projectWeeklyService.findProjectWeeklyListAsReceive(taskId,projectId, type, getMemberId(), month);
		}
		Integer count = this.projectWeeklyService.findProjectWeeklyCountAsReceive(taskId, projectId, getMemberId(),
				ProjectWeekly_Type.cto, null, month);
		Integer unCount = this.projectWeeklyService.findProjectWeeklyCountAsReceive(taskId, projectId, getMemberId(),
				ProjectWeekly_Type.cto, false, month);
		model.addAttribute("count", count);
		model.addAttribute("unCount", unCount);
		
		model.addAttribute("projectId", projectId);
		model.addAttribute("list", list);
		model.addAttribute("type", type);
		model.addAttribute("weeklyMap", map);
		model.addAttribute("month", cal.getTime());
		model.addAttribute("monthNum", month);
		return "/member/cto/project/weekly_list_frag";
	}

	/**
	 * 索要周报
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/claim/weekly", method = RequestMethod.POST)
	public ResponseResult<String> claimWeekly(Long taskId, Long memberId, String content) {
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
		task.setClaimTime(task.getClaimTime() == null ? content : task.getClaimTime() + "," + content);
		projectStageTaskService.updateProjectStageTask(task);
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_ASK_WEEKLY);
		String title = String.format(message.getKey(), task.getName());
		String contentmessage = String.format(message.getValue(), task.getName());
		eventPublishService.publishMessageEvent(task.getEliteMemberId(), task.getProjectId(), getMemberId(), title,
				contentmessage, false, MemberMessage_Type.project);
		return new ResponseResult<>();
	}

	/**
	 * CTO发送客户项目周报
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/weekly/save", method = RequestMethod.POST)
	@ProjectNewNotices(controllerType = "add", noticeType = NoticeTypeEnum.Weekly)
	public ResponseResult<String> saveWeeklyData(Long taskId, Long projectId, Long attaId, Date weeklyTime,
			Model model) {
		SimpleDateFormat sif = new SimpleDateFormat("yyyy-MM-dd");
		Project project = projectService.findProjectById(projectId);
		ProjectWeekly weekly = new ProjectWeekly();
		weekly.setTaskId(taskId);
		weekly.setWeeklyTime(weeklyTime);
		weekly.setAttaId(attaId);
		weekly.setProjectId(projectId);
		weekly.setReceiveId(project.getCompanyId());
		weekly.setCreateId(getMemberId());
		weekly.setWeeklyType(ProjectWeekly_Type.company);
		projectWeeklyService.createProjectWeekly(weekly);
		// 发送周报
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, project.getId(), project.getForStage().getStageCode(), null, getMemberId(),
				project.getForStage()==null?"立项阶段":project.getForStage().getTitle(), "我提交了" + sif.format(weeklyTime) + "这周周报", null);
		// 发送给pm
		StringKeyValue messagepm = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PM_RECEIVE_WEEKLY);
		String title = String.format(messagepm.getKey(), project.getName());
		String replacepm = "<a href='" + getServerUrl() + "/manager/project/pm'>查看</a>";
		String contentpm = String.format(messagepm.getValue(), project.getName(), sif.format(weeklyTime), replacepm);
		eventPublishService.publishSysMessageEvent(project.getPmId(), null, title, contentpm, false);
		return new ResponseResult<>();
	}

	/**
	 * 删除周报
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/weekly/delete", method = RequestMethod.POST)
	public ResponseResult<String> delWeeklyData(Long id) {
		projectWeeklyService.delWeekly(id);
		return new ResponseResult<>();
	}

	/**
	 * 项目日志列表
	 */
	@SSOToken
	@RequestMapping(value = "/log", method = RequestMethod.POST)
	public String projectLogList(Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		Integer count = this.projectLogService.findProjectLogCount(projectId, ProjectLog_Type.elite);
		model.addAttribute("count", count);
		model.addAttribute("obj", project);
		return "/member/cto/project/project_log_list";
	}

	/**
	 * CTO发布任务
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/pushTask", method = RequestMethod.POST)
	public ResponseResult<String> pushTask(ProjectStageTask obj) {
		ResponseResult<String> rsp = new ResponseResult<>();
		// 该项目所属阶段任务总共金额
		ProjectDefineStage stage = projectDefineStageService.findProjectDefineStageById(obj.getStageId());
		List<ProjectStageTask> taskList = this.projectStageTaskService.findValidProjectStageTask(obj.getStageId());
		BigDecimal amount = obj.getAmount();
		for (ProjectStageTask task : taskList) {
			amount=amount.add(task.getAmount());
		}
		boolean flag = projectStageTaskService.findProjectStageTaskByName(obj.getProjectId(), obj.getName());
		rsp.isSuccess(false);
		if (flag) {
			rsp.setMsg("不能存在相同的任务名");
			return rsp;
		} else if (stage.getAmount().compareTo(amount) < 0) {
			rsp.setMsg("该项目当前阶段的总金额已超出");
			return rsp;
		} else {
			rsp.isSuccess(true);
			Project project = projectService.findProjectById(obj.getProjectId());
			ProjectStageTask task = projectStageTaskService.createProjectStageTask(obj, false, null);
			// 发布任务
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, project.getId(),
					project.getForStage().getStageCode(), task.getId(), getMemberId(), project.getForStage().getTitle(),
					"我发布了" + obj.getName() + "任务", null);
		}
		return rsp;
	}

	/**
	 * 联想参与该项目的项目方和精英
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/sendFileMember", method = RequestMethod.POST)
	public ResponseResult<MemberMap> sendFileMember(String keyWord, Long projectId) {
		ResponseResult<MemberMap> rsp = new ResponseResult<MemberMap>();
		List<MemberPassport> list = memberPassportService.findMemberPassportListForProject(keyWord, projectId);
		List<MemberMap> mapList = new ArrayList<>();
		for (MemberPassport member : list) {
			MemberMap map = new MemberMap();
			if (member.getAccount().contains(keyWord)) {
				map.setLabel(member.getAccount());
			}
			if (member.getNickName().contains(keyWord)) {
				map.setLabel(member.getNickName());
			}
			map.setAccount(member.getAccount());
			map.setName(member.getNickName());
			map.setDesc(member.getCurrentType());
			map.setValue(member.getId());
			mapList.add(map);
		}
		rsp.setData(mapList);
		return rsp;
	}

	/**
	 * CTO确定项目定标书
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/define/save", method = RequestMethod.POST)
	public ResponseResult<String> saveProjectDefine(Long projectId) {
		Project project = projectService.findProjectById(projectId);
		this.projectService.updateProjectAffirmDefinedAsCTO(projectId, getMemberId(), true);
		
		// 确定项目立项书日志
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, project.getId(),
				project.getForStage().getStageCode(), null, getMemberId(), project.getForStage().getTitle(), "我确认" + project.getName() + "定标书",
				null);
		// 确定项目立项书消息
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_AFFIRM_DEFINE_SUCCESS);
		String title = String.format(message.getKey(), project.getName());
		String content = String.format(message.getValue(), project.getName());
		eventPublishService.publishSysMessageEvent(getMemberId(), null, title, content, false);

		StringKeyValue messagebm = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.BM_RECEIVE_CTO_AFFIRM_DEFINE);
		String titlebm = String.format(messagebm.getKey(), project.getName());
		String replacebm = "<a href='" + getServerUrl() + "/manager/project/pm'>去看看吧</a>~";
		String contentbm = String.format(messagebm.getValue(), project.getName(), replacebm);
		eventPublishService.publishSysMessageEvent(project.getPmId(), null, titlebm, contentbm, false);
		return new ResponseResult<>();
	}

	/**
	 * CTO推荐主页面推荐和竞标统计
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public String projectCount(Model model) {
		Long memberId = getMemberId();
		Integer tenderInCount = projectTenderRecordService.findCtoProjectTenderInCount(memberId, 0);
		Integer tenderCount = projectTenderRecordService.findCtoProjectTenderInCount(memberId, 1);
		Integer recommendcount = memberRecommendService.findRecommendProjectCount(memberId);
		model.addAttribute("tenderInCount", tenderInCount);
		model.addAttribute("recommendcount", recommendcount);
		model.addAttribute("tenderCount", tenderCount);
		return "/member/cto/main/project_list";
	}

	/**
	 * CTO我的项目
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/listData", method = RequestMethod.POST)
	public String listData(String type, Pager pager, Model model) {
		Long memberId = getMemberId();
		String url="";
		//推荐的项目
		if(type.equals("recommend")){
			url="member/cto/main/project_recommend_list";
			MemberElite elite=this.memberEliteService.findMemberEliteByMemberId(getMemberId());
			SearchResult<MemberRecommend> result = memberRecommendService.findRecommendProjectList( memberId, pager);
			model.addAttribute("result",result.getResult());
			pager.calPageCount((long) result.getTotalCount());
			model.addAttribute("elite",elite);
		}else if(type.equals("tenderIn")){//竞标中的项目
			url="/member/cto/main/tender_in_list_frag";
			SearchResult<ProjectTenderRecord> result = projectTenderRecordService.findCtoProjectTender(memberId,
					0, pager);
			model.addAttribute("result", result.getResult());
			pager.calPageCount((long) result.getTotalCount());
		}else if(type.equals("tender")){//竞标成功的项目
			url="/member/cto/main/tender_win_list_frag";
			SearchResult<ProjectTenderRecord> result = projectTenderRecordService.findCtoProjectTender(memberId,
					1, pager);
			model.addAttribute("result", result.getResult());
			pager.calPageCount((long) result.getTotalCount());
			
		}
		
		Integer tenderInCount = projectTenderRecordService.findCtoProjectTenderInCount(memberId, 0);
		Integer tenderCount = projectTenderRecordService.findCtoProjectTenderInCount(memberId, 1);
		Integer recommendcount = memberRecommendService.findRecommendProjectCount(memberId);
		model.addAttribute("tenderInCount", tenderInCount);
		model.addAttribute("recommendcount", recommendcount);
		model.addAttribute("tenderCount", tenderCount);
		
		BigDecimal percent = localCoreConfig.getQualityAmountPercent();
		model.addAttribute("pagination", pager);
		model.addAttribute("percent", percent);
		return url;
	}

	

	/**
	 * 分配任务页面信息
	 * 
	 * @param memberId
	 * @param model
	 * @return
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/viewTaskMember", method = RequestMethod.GET)
	public String viewTaskMember(Long memberId, Model model) {
		List<MemberAttention> attendList = memberAttentionService.findAttenTionedUsers(memberId);
		model.addAttribute("attendList", attendList);
		List<MemberAttention> list = memberAttentionService.findAttenTionUsers(memberId);
		model.addAttribute("list", list);
		// List<MemberTeam> teamlist =
		// memberTeamService.findMyMemberTeam(memberId);
		// model.addAttribute("teamlist",teamlist);
		return "";
	}

	/**
	 * CTO分配任务(推荐和至任务大厅)
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/assignTask", method = RequestMethod.POST)
	public ResponseResult<String> assignTask(@RequestParam(value = "memberIds[]") Long[] memberIds, Long projectId) {
		memberRecommendService.createMemberRecommends(memberIds, getMemberId(), MemberRecommend_Type.r_task, projectId);
		return new ResponseResult<>();
	}

	/**
	 * CTO指定精英详情
	 */

	@SSOToken
	@RequestMapping(value = "/viewApplyElite", method = RequestMethod.POST)
	public String viewApplyElite(Model model, Pager pager, Long taskId) {
		SearchResult<ProjectTaskRecruit> list = projectTaskRecruitService.findProjectTaskRecruitByTaskId(getMemberId(),
				taskId, pager);
		model.addAttribute("list", list.getResult());
		model.addAttribute("count", list.getTotalCount());
		return "/member/cto/project/applyelite_view";
	}

	/**
	 * CTO指定精英
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/appoint", method = RequestMethod.POST)
	public ResponseResult<String> appointElite(Long memberId, Long taskId) {
		ProjectTaskRecruit recuit = projectTaskRecruitService.findProjectTaskRecruit(memberId, taskId);
		projectTaskRecruitService.updateProjectTask(recuit);
		// 确认人选
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, recuit.getProjectId(),recuit.getProject().getForStage().getStageCode(),
				recuit.getTaskId(), recuit.getMemberId(), recuit.getProject().getForStage().getTitle(), "我通过了" + recuit.getTask().getName() + "任务", null);
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_AFFIEM_TASK_ELITE);
		String title = String.format(message.getKey(), recuit.getTask().getName());
		String replace = "<a href='" + getServerUrl() + "/project/c/task/" + taskId + "/index'>查看</a>";
		String content = String.format(message.getValue(), recuit.getTask().getName(), replace);
		eventPublishService.publishSysMessageEvent(getMemberId(), null, title, content, false);
		return new ResponseResult<>();
	}

	/**
	 * cto--任务管理--任务首页
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/task/{taskId}/index", method = RequestMethod.GET)
	public String taskDetails(@PathVariable Long taskId, Model model, Pager pager) {
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(taskId);
		if(!projectStageTask.getCreateId().equals(getMemberId())){
			return UNAUTHORIZED_VIEW;
		}
		examineIsUnreadMessage(model, true);
		model.addAttribute("obj", projectStageTask);
		model.addAttribute("taskId", taskId);
		return "/member/cto/task/index";
	}

	/**
	 * cto-任务详情
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "/viewTask", method = RequestMethod.POST)
	public String viewTask(Long taskId, Model model) {
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(taskId);
		MemberRecommend recom = memberRecommendService.findRecommendTask(getMemberId(), taskId);
		Integer applyCount = this.projectTaskRecruitService.findProjectTaskApplyCountByTaskId(taskId);
		String applyFlag = "N";
		ProjectTaskRecruit apply = this.projectTaskRecruitService.findProjectTaskRecruit(getMemberId(), taskId);
		if (apply != null) {
			applyFlag = "Y";
		}
		List<ProjectTaskRecruit> recruitlist = projectTaskRecruitService.findProjectTaskRecruitByTaskId(taskId);
//		if(projectStageTask.getStatus().equals(ProjectTask_Status.quality)){
//			Date guarantee = DateTimeUtils.addOrSub(projectStageTask.getAcceptTime(),projectStageTask.getGuaranteeTime());
//			model.addAttribute("guaranteeTime",guarantee.getTime() / 1000 - new Date().getTime() / 1000);
//		}
		model.addAttribute("recruitList", recruitlist);
		Date time=projectStageTask.getStatus().equals(ProjectTask_Status.quality)?projectStageTask.getAcceptTime():projectStageTask.getEndTime();
		model.addAttribute("deadlineTime",(time.getTime() / 1000 - new Date().getTime() / 1000));
		model.addAttribute("applyFlag", applyFlag);
		model.addAttribute("applyCount", applyCount);
		model.addAttribute("recom", recom);
		model.addAttribute("obj", projectStageTask);
		return "/member/cto/task/task_view";
	}

	/**
	 * 精英-是否接活
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/changeWork", method = RequestMethod.POST)
	public ResponseResult<String> toWork(boolean workFlag) {
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		memberElite.setTaked(workFlag);
		memberEliteService.updateMemberElite(memberElite);
		return new ResponseResult<>();
	}
	
	/**
	 * 申请审核
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/applyAudit", method = RequestMethod.POST)
	public ResponseResult<String> applyAudit() {
		MemberElite memberElite = memberEliteService.findMemberEliteByMemberId(getMemberId());
		MemberDetailInfo info = memberPassportService .findMemberDetailInfoById(getMemberId());
		ResponseResult<String> rsp = new ResponseResult<>();
		rsp.isSuccess(false);
		//如果申请审核时间还没过3天则不能修改
		if(memberElite.getApplyAuditTime()!=null && DateTimeUtils.dateSub(new Date(),memberElite.getApplyAuditTime())<=3){
			Date date = DateTimeUtils.addOrSub(memberElite.getApplyAuditTime(), 3);
			String message="没有申请机会,下次申请时间为"+DateTimeUtils.formatDate(date, "yyyy-MM-dd");
			rsp.setMsg(message);
			return rsp;
		}else{
			if(info.getCredit().getId()==null || info.getBasic().getId()==null || info.getEducations().size()==0 || info.getProjects().size()==0 || memberElite.getEliteJobs().size()==0 || null==memberElite.getIntro()){
				String message="您的资料完善度还不满足审核条件,先完善哦!";
				rsp.setMsg(message);
				return rsp;
			}
			rsp.isSuccess(true);
			return rsp;
		}
		
	}
	
	/**
	 * 申请审核
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public ResponseResult<String> audit() {
		ResponseResult<String> rsp = new ResponseResult<>();
		rsp.isSuccess(false);
		try {
			MemberElite elite = memberEliteService.findMemberEliteByMemberId(getMemberId());
			elite.setApplyAuditTime(new Date());
			elite.setStatus(Member_Status.aduitIn);
			memberEliteService.updateMemberElite(elite);
			rsp.isSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp;
	}
	
	/**
	 * 进行验收任务
	 * 
	 * @param passworld
	 * @return
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@RequestMapping(value = "{taskId}/acceptance", method = RequestMethod.GET)
	public String acceptance(@PathVariable Long taskId, Model model) {
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
		if(!task.getCreateId().equals(getMemberId())){
			return UNAUTHORIZED_VIEW;
		}
		examineIsUnreadMessage(model, true);
		model.addAttribute("obj", task);
		return "/member/cto/project/task_acceptance";
	}

	/**
	 * 验收任务
	 * 
	 * @param passworld
	 * @return
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/acceptanceTask", method = RequestMethod.POST)
	public ResponseResult<String> acceptanceTask(String password, Long taskId) {
		ResponseResult<String> rsp = new ResponseResult<>();
		MemberPassport member = memberPassportService.findMemberPassportByIdValidPassword(getMemberId(), password);
		if (member != null) {
			ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
//			Integer guaranteeTime = DateTimeUtils.taskQuaTime(task.getDeliveryTime(), task.getStartTime());// 质保时间
//			task.setGuaranteeTime(guaranteeTime);
			task.setStatus(ProjectTask_Status.accept_success);
			task.setAcceptTime(new Date());
			projectStageTaskService.updateProjectStageTask(task);
			// 验收任务
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, task.getProjectId(), task.getProject().getForStage().getStageCode(), task.getId(),
					task.getEliteMemberId(), task.getProject().getForStage().getTitle(), "CTO验收通过了" + task.getName() + "任务", null);
			// 系统消息
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_ACCEPT_TASK);
			String content = String.format(message.getValue(), task.getName(), task.getGuaranteeTime());
			eventPublishService.publishSysMessageEvent(getMemberId(), null, message.getKey(), content, false);
			StringKeyValue messageElite = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_ACCEPT_TASK);
			String title = String.format(messageElite.getKey(), task.getName());
			String contentElite = String.format(messageElite.getValue(), task.getName(), task.getGuaranteeTime());
			eventPublishService.publishSysMessageEvent(task.getEliteMemberId(), null, title, contentElite, false);
		} else {
			rsp.isSuccess(false);
		}
		return rsp;
	}

	/**
	 * 提醒任务
	 * 
	 * @param passworld
	 * @return
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/taskRemind", method = RequestMethod.POST)
	public ResponseResult<String> taskRemind(Long taskId) {
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
		task.setRemind(true);
		projectStageTaskService.updateProjectStageTaskForRemind(task);
		// 任务消息提醒
		eventPublishService.publishMessageEvent(task.getEliteMemberId(), task.getProjectId(), getMemberId(), "任务提醒",
				task.getName() + "任务文件记得提交", false, MemberMessage_Type.project);
		return new ResponseResult<>();
	}

	/**
	 * 关闭任务
	 * 
	 * @param passworld
	 * @return
	 */
	@ESChain(ref = "validCto")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public ResponseResult<String> closeTask(Long taskId, String reason) {
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
		task.setCloseReason(reason);
		task.setStatus(ProjectTask_Status.closed);
		projectStageTaskService.updateProjectStageTask(task);
		// 验收任务
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, task.getProjectId(),
				task.getProject().getForStage().getStageCode(), task.getId(), task.getEliteMemberId(), task.getProject().getForStage().getTitle(),
				"我关闭了" + task.getName() + "任务", null);
		return new ResponseResult<>();
	}

}
