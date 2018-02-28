package com.ledao.elite.manager.controller.project;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberRecommend.MemberRecommend_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectMaterial.ProjectMaterial_Status;
import com.ledao.elite.core.domain.project.ProjectSettingStage;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.ProjectWeekly;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Status;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Type;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.annotation.ProjectNewNotices;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.constant.NoticeTypeEnum;
import com.ledao.elite.core.framework.dto.ProjectDetailInfo;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.dto.site.WeeklyKey;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberRecommendService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectMaterialService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectSettingStageService;
import com.ledao.elite.core.service.project.ProjectTenderService;
import com.ledao.elite.core.service.project.ProjectWeeklyService;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * PM项目管理控制中心
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectpmController")
@RequestMapping("/project")
public class ProjectPmController extends BaseController {

	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberRecommendService memberRecommmendService;
	@Resource
	private ProjectMaterialService projectMaterialService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private ProjectWeeklyService projectWeeklyService;
	@Resource
	private EventPublishService eventPublishService;
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectSettingStageService projectSettingStageService;
	@Resource
	private ProjectTenderService projectTenderService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private MemberCompanyService memberCompanyService;

	/**
	 * PM项目详情
	 */
	@ProjectNewNotices(controllerType = "remove", noticeType = NoticeTypeEnum.PmNewProject)
	@RequestMapping(value = "/pm/detail", method = RequestMethod.POST)
	public String detail(Model model, Long projectId) {
		Project project = this.projectService.findProjectDetailByIdAndType(projectId, "pm");
		ProjectDetailInfo pInfo = new ProjectDetailInfo(project);
		if (project != null) {
			if (project.getCompanyId() != null) {
				MemberPassport member = this.memberPassportService.findMemberDetailInfoById(project.getCompanyId());
				model.addAttribute("member", member);
				if (project.getContactPhone() == null) {
					pInfo.setContactName(member.getCredit().getRealName());
					pInfo.setContactPhone(member.getAccount());
				}
				MemberCompany company = this.memberCompanyService.findMemberCompanyByMemberId(project.getCompanyId());
				if (company != null) {
					pInfo.setAuditedMemberCompany(company.getStatus().name());
				}
			}
			// pm股权项目获取 cto信息
			if (project.getCtoId() != null) {
				MemberPassport pass = this.memberPassportService.findMemberPassportById(project.getCtoId());
				if (pass != null) {
					pInfo.setCTOName(pass.getNickName());
					pInfo.setCTOPhone(pass.getAccount());
				}
			}
		}
		this.projectService.updateProjectStatusInById(projectId);
		model.addAttribute("it", pInfo);
		return "/project/pm/detail";
	}

	/**
	 * 推荐给CTO 页面显示
	 */
	@RequestMapping(value = "/pm/recommend/view", method = RequestMethod.POST)
	public String recommendview(Model model, String keyword) {
		List<MemberPassport> list = this.memberPassportService.findMemberpassPortCTOList(keyword);
		model.addAttribute("list", list);
		return "/project/tender/recommend";
	}
	/**
	 * 推荐给CTO列表
	 */
	@RequestMapping(value = "/pm/recommend/listData", method = RequestMethod.POST)
	public String recommendListData(Model model, String keyword) {
		List<MemberPassport> list = this.memberPassportService.findMemberpassPortCTOList(keyword);
		model.addAttribute("list", list);
		return "/project/tender/recommend_list_frag";
	}

	/**
	 * 推荐给CTO
	 */
	@ResponseBody
	@SystemHandleLog(description = "项目推荐给CTO", type = LogsEnum.create)
	@RequestMapping(value = "/pm/recomemd/save", method = RequestMethod.POST)
	public ResponseBase saveRecomend(Long projectId, String id) {
		String ids[] = id.split(",");
		Long memberIds[] = new Long[ids.length];
		for (int i = 0; i < ids.length; i++) {
			memberIds[i] = Long.parseLong(ids[i]);
		}
		this.memberRecommmendService.createMemberRecommends(memberIds, getUserId(), MemberRecommend_Type.r_project,
				projectId);
		return new ResponseBase();
	}

	/**
	 * 查询文件
	 */
	@ProjectNewNotices(controllerType = "remove", noticeType = NoticeTypeEnum.Material)
	@RequestMapping(value = "/pm/material/listData", method = RequestMethod.POST)
	public String materialListData(Model model, Long projectId, Long stageId, String queryType, Pager pager) {

		SearchResult<ProjectMaterial> sr = this.projectMaterialService.findProjectMaterialListByPm(projectId, stageId,
				getUserId(), queryType, pager);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		return "/project/pm/material_list_frag";
	}

	/**
	 * 添加文件
	 */
	@ResponseBody
	@SystemHandleLog(description = "pm上传文件", type = LogsEnum.create)
	@RequestMapping(value = "/pm/material/addMaterial", method = RequestMethod.POST)
	public ResponseBase auditMaterial(ProjectMaterial obj) {
		obj.setStatus(ProjectMaterial_Status.pass);
		ProjectMaterial returnObj = this.projectMaterialService.createProjectMaterial(obj);
		String url = localCoreConfig.getDomainServer();
		Project project = this.projectService.findProjectById(returnObj.getProjectId());
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_RECEIVE_MATERIAL);
		if (obj.getReceiveId() != null) {
			MemberPassport member = this.memberPassportService.findMemberPassportById(obj.getReceiveId());
			if (member != null) {
				String replace = "";
				if (member.getCurrentType().equals(MemberIdentity_Type.company.name())) {
					replace = "<a href='" + url + "/project/" + returnObj.getProjectId()
							+ "/index?rp=material' target='_blank'>去查看</a>";
				  //发送个项目方物料，所属阶段变成待验收状态
				  if(obj.getStageId()!=null){
					  ProjectDefineStage stage=this.projectDefineStageService.findProjectDefineStageById(obj.getStageId());
					  if(stage!=null){
						  stage.setStatus(Stage_Status.wait_accept);
						  this.projectDefineStageService.updateProjectDefineStage(stage);
					  }
				  }
				} else {
					replace = "<a href='" + url + "/project/c/" + returnObj.getProjectId()
							+ "/index?rp=material' target='_blank'>去查看</a>";
				}
				String content = String.format(message.getValue(), replace);
				eventPublishService.publishMessageEvent(project.getCompanyId(), null, null, message.getKey(), content,
						false, MemberMessage_Type.system);
			}
		}
		return new ResponseBase();
	}

	/**
	 * 审核文件页面
	 */
	@RequestMapping(value = "/pm/material/audit/view", method = RequestMethod.POST)
	public String aduitMaterialView(Model model, Long id) {
		model.addAttribute("materialId", id);
		return "/project/pm/audit_material";
	}

	/**
	 * 审核文件
	 */
	@ResponseBody
	@SystemHandleLog(description = "审核文件", type = LogsEnum.update)
	@RequestMapping(value = "/pm/material/audit", method = RequestMethod.POST)
	public ResponseBase auditMaterial(Long id, String type, String auditReason) {
		ProjectMaterial obj = this.projectMaterialService.findProjectMaterialById(id);
		obj.setAuditReason(auditReason);
		obj.setStatus(ProjectMaterial_Status.valueOf(ProjectMaterial_Status.class, type));
		if(obj.getReceiveId()==null&&obj.getProject().getCtoId()!=null){
			obj.setReceiveId(obj.getProject().getCtoId());
		}
		this.projectMaterialService.updateAuditProjectMaterialStatus(obj);
		String fileName = obj.getAtta().getFileName();
		String content = "";
		if (type.equals(ProjectMaterial_Status.pass.name())) {
			content = fileName + "文件审核通过，快去看看吧";
		} else if (type.equals(ProjectMaterial_Status.unpass.name())) {
			content = fileName + "文件审核未通过，原因：" + auditReason;
		}
		String stageCode = null;
		String title = null;
		if (obj.getStage() != null) {
			stageCode = obj.getStage().getStageCode();
			title = obj.getStage().getTitle();
		} else {
			stageCode = obj.getProject().getName();
			title = obj.getProject().getName();
		}
		if (obj.getProjectId() != null && type.equals(ProjectMaterial_Status.unpass.name())) {
			if (obj.getProject().getCtoId() != null) {
				String ctocontent = "你好，你于" + obj.getCreateTime() + "就" + obj.getProject().getName() + "项目提交的"
						+ fileName + "，经项目经理审核，由于原因：" + auditReason + " 未能审核通过，请依照修改后重新上传。";
				eventPublishService.publishMessageEvent(obj.getProject().getCtoId(), null, null, "你提交的文件未能审核通过。",
						ctocontent, false, MemberMessage_Type.system);
			}
		}
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, obj.getProjectId(), stageCode, null,
				obj.getUploadId(), title, content, null);
		return new ResponseBase();
	}

	/**
	 * 查询周报
	 */
	@ProjectNewNotices(controllerType = "remove", noticeType = NoticeTypeEnum.Weekly)
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/pm/weekly/listData", method = RequestMethod.POST)
	public String weeklyListData(Model model, Integer month, Long projectId, Long stageId, String searchType,
			Pager pager, Date startTime, Date endTime, String status) {
		if (month == 0) {
			month = new Date().getMonth() + 1;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		Map<WeeklyKey, List<ProjectWeekly>> map = this.projectWeeklyService.findProjectWeeklyListByPm(null, projectId,
				status, getUserId(), month);
		model.addAttribute("projectId", projectId);
		model.addAttribute("type", searchType);
		model.addAttribute("weeklyMap", map);
		model.addAttribute("month", cal.getTime());
		return "/project/pm/weekly_list_frag";
	}

	/**
	 * PM发送周报给项目方
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/pm/weekly/save", method = RequestMethod.POST)
	public ResponseBase saveWeeklyData(Long projectId, Long attaId, Date weeklyTime, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		ProjectWeekly weekly = new ProjectWeekly();
		weekly.setWeeklyTime(weeklyTime);
		weekly.setAttaId(attaId);
		weekly.setProjectId(projectId);
		weekly.setCreateId(-1L);
		weekly.setReceiveId(project.getCompanyId());
		weekly.setWeeklyType(ProjectWeekly_Type.company);
		projectWeeklyService.createProjectWeekly(weekly);
		// 发送周报
		String url = localCoreConfig.getDomainServer();
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_RECEIVE_WEEKLY);
		String title = String.format(message.getKey(), project.getName());
		String replace = "<a href='" + url + "/project/" + project.getId()
				+ "/index?rp=weekly' target='_blank'>去查看</a>";
		String content = String.format(message.getValue(), project.getName(), weeklyTime, replace);
		eventPublishService.publishMessageEvent(project.getCompanyId(), null, null, title, content, false,
				MemberMessage_Type.system);
		return new ResponseBase();
	}

	/**
	 * 审核周报
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/pm/weekly/audit", method = RequestMethod.POST)
	public ResponseBase aduitWeeklyData(Long id, String status, String time) {
		ProjectWeekly weekly = this.projectWeeklyService.findProjectWeeklyById(id);
		weekly.setStatus(ProjectWeekly_Status.valueOf(ProjectWeekly_Status.class, status));
		weekly.setAuditId(getUserId());
		this.projectWeeklyService.updateProjectWeeklyStatus(weekly);
		if (ProjectWeekly_Status.unaudit.name().equals(status)) {
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PM_UNAUDIT_WEEKLY);
			String title = String.format(message.getKey(), weekly.getProject().getName());
			String content = String.format(message.getValue(), weekly.getProject().getName(), time);
			eventPublishService.publishMessageEvent(weekly.getProject().getCtoId(), null, null, title, content, false,
					MemberMessage_Type.system);
		}
		return new ResponseBase();
	}

	/**
	 * 索要周报
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/pm/weekly/ask", method = RequestMethod.POST)
	public ResponseBase askWeeklyData(Long projectId, String time) {
		// 发送周报
		Project project = this.projectService.findProjectById(projectId);
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PM_ASK_WEEKLY);
		String title = String.format(message.getKey(), project.getName());
		String content = String.format(message.getValue(), project.getName(), time);
		eventPublishService.publishMessageEvent(project.getCtoId(), null, null, title, content, false,
				MemberMessage_Type.system);
		return new ResponseBase();
	}

	/**
	 * 查询招标等记录
	 */
	@SSOToken
	@RequestMapping(value = "/pm/detailLogs/listData", method = RequestMethod.POST)
	public String viewLogs(Model model, Long id, Pager pager) {
		// 设置服务阶段列表
		List<ProjectSettingStage> settingStages = this.projectSettingStageService.findSettingStagesByProjectId(id);
		Date date = null;
		Long updateId = null;
		for (int i = 0; i < settingStages.size(); i++) {
			ProjectSettingStage stage = settingStages.get(i);
			if (i == 0) {
				if (stage.getUpdateTime() != null) {
					date = stage.getUpdateTime();
					updateId = stage.getUpdateId();
				} else {
					date = stage.getCreateTime();
					updateId = stage.getCreateId();
				}
			} else {
				if (stage.getUpdateTime() != null) {
					if (date.getTime() < stage.getUpdateTime().getTime()) {
						date = stage.getUpdateTime();
						updateId = stage.getUpdateId();
					}
				} else {
					if (date.getTime() < stage.getCreateTime().getTime()) {
						date = stage.getCreateTime();
						updateId = stage.getCreateId();
					}
				}
			}
		}
		String controlName = null;
		if (updateId != null) {
			SysUser sysUser = this.sysUserService.findSysUserById(updateId);
			controlName = sysUser.getLoginName();
		}
		model.addAttribute("settingName", controlName);
		model.addAttribute("settingTime", date);
		model.addAttribute("settingStages", settingStages);
		SearchResult<ProjectTender> sr = this.projectTenderService.findProjectTendersDetail(id, pager);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);

		Project project = this.projectService.findProjectById(id);
		if (project != null) {
			if (project.getPmId() != null) {
				SysUser pmUser = this.sysUserService.findSysUserById(project.getPmId());
				project.setPmName(pmUser.getLoginName());
			}
		}
		model.addAttribute("project", project);
		return "/project/pm/logs_list_frag";

	}
}
