package com.ledao.elite.manager.controller.project;

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
import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBank.MemberBank_Type;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.annotation.ProjectNewNotices;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.constant.NoticeTypeEnum;
import com.ledao.elite.core.framework.constant.RoleEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.framework.dto.ProjectDetailInfo;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.service.member.MemberBankService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * BM项目管理控制中心
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectBmController")
@RequestMapping("/project")
public class ProjectBmController extends BaseController {

	@Resource
	private ProjectService projectService;
	@Resource
	private SysUserService SysUserService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberCompanyService memberCompanyService;
	@Resource
	private MemberBankService memberBankService;

	/**
	 * BM项目详情
	 */
	@RequestMapping(value = "/{type}/detail", method = RequestMethod.POST)
	public String detail(Model model, Long id) {
		Project project = this.projectService.findProjectDetailByIdAndType(id, "bm");
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
		}
		List<MemberBank> alipays = this.memberBankService.findMemberBankByMemberIdAndType(id, MemberBank_Type.alipay);
		List<MemberBank> banks = this.memberBankService.findMemberBankByMemberIdAndType(id, MemberBank_Type.bank);
		model.addAttribute("alipays", alipays);
		model.addAttribute("banks", banks);
		this.projectService.updateProjectStatusInById(id);
		model.addAttribute("it", pInfo);
		return "/project/bm/detail";
	}

	/**
	 * bm的股权项目分配pm列表
	 */
	@RequestMapping(value = "{type}/allot/view")
	public String alloutPmView(@PathVariable String type, Model model, Long id) {
		Project project = this.projectService.findProjectById(id);
		if (project.getPmId() != null) {
			SysUser sysUser = this.SysUserService.findSysUserById(project.getPmId());
			if (sysUser != null) {
				project.setPmName(sysUser.getUserName());
			}
		}
		model.addAttribute("project", project);
		if (type.equals("bm")) {
			model.addAttribute("userRoleType", "project_mamager");
		}
		return "/project/" + type + "/allot";
	}

	/**
	 * 查询pm用户列表
	 */
	@RequestMapping(value = "{type}/allot/listData")
	public String listdata(@PathVariable String type, String userRoleType, Model model, Long id, String keyword,
			Pager pager) {
		Long roleId = RoleEnum.valueOf(RoleEnum.class, userRoleType).getRoleId();
		SearchResult<SysUser> result = this.SysUserService.findSysUserListByKeyWordAndType(keyword, roleId, pager,
				GlobalDefinedConstant.System_Status.normal.name());
		pager.calPageCount((long) result.getTotalCount());
		model.addAttribute("list", result.getResult());
		model.addAttribute("pager", pager);
		return "/project/" + type + "/allotlist_frag";
	}

	/**
	 * 分配pm
	 */
	@ResponseBody
	@SystemHandleLog(description = "分配PM", type = LogsEnum.update)
	@ProjectNewNotices(controllerType = "add", noticeType = NoticeTypeEnum.PmNewProject)
	@RequestMapping(value = "{type}/allot/save", method = RequestMethod.POST)
	public ResponseBase allot(@PathVariable String type, @RequestParam Long projectId, Long userId) {
		Project project = this.projectService.findProjectById(projectId);
		project.setPmId(userId);
		this.projectService.updateProject(project);
		return new ResponseBase();
	}

	/**
	 * BM显示待审核项目方内容
	 */
	@RequestMapping(value = "/bm/auditview")
	public String auditView(Model model, Long id) {
		MemberDetailInfo member = this.memberPassportService.findMemberDetailInfoById(id);
		model.addAttribute("member", member);
		return "/project/bm/audit";
	}

	/**
	 * BM审核项目显示
	 */
	@RequestMapping(value = "/bm/project/auditview")
	public String auditProjectView(Model model, Long projectId) {
		Project project = this.projectService.findProjectDetailById(projectId);
		this.projectService.updateProjectStatusInById(projectId);
		model.addAttribute("project", project);
		return "/project/bm/audit_project";
	}

	/**
	 * pm审核项目
	 */
	@ResponseBody
	@SystemHandleLog(description = "pm审核项目", type = LogsEnum.update)
	@RequestMapping(value = "/bm/saveAuditProject")
	public ResponseBase save(Model model, Long projectId, String auditReason) {
		Project project = new Project();
		project.setAuditReason(auditReason);
		project.setId(projectId);
		project.setAuditId(getUserId());
		project.setStatus(Project_Status.unpass);
		Project projectBack = this.projectService.updateProjectAudit(project);
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_AUDIT_UNPASS);
		String title = message.getKey();
		String url = localCoreConfig.getDomainServer();
		String replace = "<a href='" + url + "/member/index' target='_blank'>去查看原因并完善~</a>";
		String content = String.format(message.getValue(), projectBack.getName(), replace);
		eventPublishService.publishMessageEvent(projectBack.getCompanyId(), null, null, title, content, false,
				MemberMessage_Type.system);
		return new ResponseBase();
	}

	/**
	 * bm创建项目页面
	 */
	@RequestMapping(value = "/bm/createview")
	public String createProjectView(Model model) {
		Map<String, String> projectTypes = DictCache.getParamMap(Dict_Type.project_type.name());
		Map<String, String> projectBudgets = DictCache.getParamMap(Dict_Type.project_budget.name());
		Map<String, String> projectFuncs = DictCache.getParamMap(Dict_Type.project_func.name());
		Map<String, String> industrys = DictCache.getParamMap(Dict_Type.choice_industry.name());
		model.addAttribute("projectTypes", projectTypes);
		model.addAttribute("projectBudgets", projectBudgets);
		model.addAttribute("projectFuncs", projectFuncs);
		model.addAttribute("industrys", industrys);
		return "/project/bm/create_project";
	}
	/**
	 * bm 创建项目
	 */
	@ResponseBody
	@SystemHandleLog(description = "bm新建项目", type = LogsEnum.create)
	@RequestMapping(value = "/bm/createProject", method = RequestMethod.POST)
	public ResponseBase createProject(Project project) {
		project.setBmId(getUserId());
		this.projectService.createBmProject(project);
		return new ResponseBase();
	}
   
	/**
	 * 超级管理员分配BM页面
	 */
	@RequestMapping(value = "/bm/bmlist/view")
	public String bmlistView( Model model, Long id) {
		Project project = this.projectService.findProjectById(id);
		if (project.getBmId() != null) {
			SysUser sysUser = this.SysUserService.findSysUserById(project.getBmId());
			if (sysUser != null) {
				project.setPmName(sysUser.getUserName());
			}
		}
		model.addAttribute("project", project);
		return "/project/bm/bmlist";
	}
	
	/**
	 * 查询bm用户列表
	 */
	@RequestMapping(value = "bm/bmlist/listData")
	public String bmlistdata( Model model, Long id, String keyword,
			Pager pager) {
		Long roleId = RoleEnum.business_manager.getRoleId();
		SearchResult<SysUser> result = this.SysUserService.findSysUserListByKeyWordAndType(keyword, roleId, pager,
				GlobalDefinedConstant.System_Status.normal.name());
		pager.calPageCount((long) result.getTotalCount());
		model.addAttribute("list", result.getResult());
		model.addAttribute("pager", pager);
		return "/project/bm/bmlist_frag";
	}
	
	/**
	 * 分配pm
	 */
	@ResponseBody
	@SystemHandleLog(description = "分配BM", type = LogsEnum.update)
	@RequestMapping(value = "bm/bmlist/save", method = RequestMethod.POST)
	public ResponseBase allotBm(Long projectId, Long userId) {
		Project project = this.projectService.findProjectById(projectId);
		project.setBmId(userId);
		this.projectService.updateProject(project);
		return new ResponseBase();
	}
	
	/**
	 * 无效项目
	 */
	@ResponseBody
	@SystemHandleLog(description = "无效项目", type = LogsEnum.update)
	@RequestMapping(value = "bm/invalid/save", method = RequestMethod.POST)
	public ResponseBase invalid(Long projectId) {
		Project project = this.projectService.findProjectById(projectId);
		project.setStatus(Project_Status.invalid);
		this.projectService.updateProject(project);
		return new ResponseBase();
	}
}
