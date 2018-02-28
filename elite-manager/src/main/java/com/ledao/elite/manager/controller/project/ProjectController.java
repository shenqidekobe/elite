package com.ledao.elite.manager.controller.project;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.constant.NoticeTypeEnum;
import com.ledao.elite.core.framework.constant.RoleEnum;
import com.ledao.elite.core.framework.dto.NoticeJsonBean;
import com.ledao.elite.core.framework.dto.ProjectDetailInfo;
import com.ledao.elite.core.service.member.MemberBankService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.utils.JsonFileUtils;
import com.ledao.elite.manager.controller.BaseController;

/**
 * BM项目管理控制中心
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectController")
@RequestMapping("/project")
public class ProjectController extends BaseController {

	private static final String bm = "bm";// 商务经理项目列表
	private static final String pm = "pm";// 项目经理项目列表

	@Resource
	private ProjectService projectService;
	@Resource
	private MemberCompanyService memberCompanyService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberBankService memberBankService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;

	/**
	 * 股权项目
	 */
	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
	public String list(@PathVariable String type, Model model) {
		if (!bm.equals(type) && !pm.equals(type))
			return ERROR_VIEW;
		model.addAttribute("list", Project_Status.values());
		model.addAttribute("type", type);
		return "/project/" + type + "/list";
	}

	/**
	 * 股权项目列表查询
	 */
	@RequestMapping(value = "/{type}/listData", method = RequestMethod.POST)
	public String listData(@PathVariable String type, String keyword, String status, Date startTime, Date endTime,
			Long holdBmId, Long holdPmId, String productIndustry, String productFunction, String projectSolution,
			String contactName, String contactPhone, Pager pager, Model model) {
		RoleEnum role = RoleEnum.business_manager;
		if (pm.equals(type)) {
			role = RoleEnum.project_mamager;
		}
		if (getRoleId().equals(RoleEnum.super_admin.roleId)) {
			role = RoleEnum.super_admin;
		}
		SearchResult<ProjectDetailInfo> result = this.projectService.findManagerProjectList(getUserId(), role, keyword,
				status, startTime, endTime, holdBmId, holdPmId, productIndustry, productFunction, projectSolution,
				contactName, contactPhone, pager);
		if (pm.equals(type)) {
			// 新分配项目查询
			List<ProjectDetailInfo> projectList = result.getResult();
			for (int i = 0; i < projectList.size(); i++) {
				ProjectDetailInfo project = projectList.get(i);
				List<NoticeJsonBean> notices = JsonFileUtils.selectNoticeByProject(project.getId() + "");
				for (int j = 0; j < notices.size(); j++) {
					NoticeJsonBean notice = notices.get(j);
					if (NoticeTypeEnum.Weekly.name().equals(notice.getNoticeType())) {
						project.setNewWeeklyNotice(true);
					} else if (NoticeTypeEnum.Material.name().equals(notice.getNoticeType())) {
						project.setNewMaterialNotice(true);
					} else if (NoticeTypeEnum.PmNewProject.name().equals(notice.getNoticeType())) {
						project.setPmNewProjectNotice(true);
					} else if (NoticeTypeEnum.CtoTender.name().equals(notice.getNoticeType())) {
						project.setNewTenderNotice(true);
					}
				}
				projectList.set(i, project);
			}

		}
		pager.calPageCount((long) result.getTotalCount());
		model.addAttribute("role", role);
		model.addAttribute("list", result.getResult());
		model.addAttribute("pager", pager);
		return "/project/" + type + "/list_frag";
	}

	/**
	 * 协助完善信息查询
	 * 
	 * @param type
	 */
	@RequestMapping(value = "/{type}/prefect/view", method = RequestMethod.POST)
	public String prefectView(@PathVariable String type, Model model, Long projectId) {
		Project project = this.projectService.findProjectDetailById(projectId);
		Map<String, String> projectTypes = DictCache.getParamMap(Dict_Type.project_type.name());
		Map<String, String> projectBudgets = DictCache.getParamMap(Dict_Type.project_budget.name());
		Map<String, String> projectFuncs = DictCache.getParamMap(Dict_Type.project_func.name());
		Map<String, String> industrys = DictCache.getParamMap(Dict_Type.choice_industry.name());
		this.projectService.updateProjectStatusInById(projectId);
		model.addAttribute("project", project);
		model.addAttribute("projectTypes", projectTypes);
		model.addAttribute("projectBudgets", projectBudgets);
		model.addAttribute("projectFuncs", projectFuncs);
		model.addAttribute("industrys", industrys);
		return "/project/" + type + "/prefect";
	}

	/**
	 * 完善项目信息
	 */
	@SystemHandleLog(description = "协助完善项目方信息", type = LogsEnum.update)
	@RequestMapping(value = "{type}/prefect/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBase prefect(@PathVariable String type, Project project) {
		this.projectService.updateProjectPrefect(project);
		return new ResponseBase();
	}

}
