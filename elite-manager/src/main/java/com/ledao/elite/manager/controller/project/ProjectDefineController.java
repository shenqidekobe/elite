package com.ledao.elite.manager.controller.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectSettingStage;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.repository.project.ProjectTenderRecordRepository;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectSettingStageService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.manager.controller.BaseController;
import com.ledao.elite.manager.model.ProjectDefineStageMapModel;

/**
 * 项目立项书,定标书控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectDefineController")
@RequestMapping("/project")
public class ProjectDefineController extends BaseController {

	@Resource
	private ProjectDefineService projectDefineService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private ProjectService projectService;
	@Resource
	private DictService DictService;
	@Resource
	private ProjectSettingStageService projectSettingStageService;
	@Resource
	protected EventPublishService eventPublishService;
	@Resource
	protected MemberCompanyService memberCompanyService;
	@Resource
	private ProjectTenderRecordRepository projectTenderRecordRepository;

	/**
	 * 添加立项书首页
	 * 
	 * @param type
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{type}/adddefine/view", method = RequestMethod.POST)
	public String view(@PathVariable String type, Model model, Long id, Long projectTenderRecordId) {
		ProjectDefine define = new ProjectDefine();
		Project project = this.projectService.findProjectDetailById(id);
		List<ProjectSettingStage> stages = this.projectSettingStageService.findSettingStagesByProjectId(id);
		MemberCompany company=this.memberCompanyService.findMemberCompanyByMemberId(project.getCompanyId());
		this.projectService.updateProjectStatusInById(id);
		model.addAttribute("projectTenderRecordId", projectTenderRecordId);
		model.addAttribute("it", project);
		model.addAttribute("define", define);
		model.addAttribute("list", stages);
		model.addAttribute("company", company);
		return "/project/" + type + "/define";
	}

	/**
	 * 添加立项书/定标书
	 * 
	 * @param type
	 * @param defineStages
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@SystemHandleLog(description = "发立项书", type = LogsEnum.create)
	@RequestMapping(value = "/{type}/define/save", method = RequestMethod.POST)
	public ResponseBase save(@PathVariable String type, ProjectDefineStageMapModel stageModel, ProjectDefine define,
			Long projectTenderRecordId) throws ParseException {

		List<ProjectDefineStage> stages = new ArrayList<ProjectDefineStage>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		for (Map.Entry<String, ProjectDefineStage> entry : stageModel.getStage().entrySet()) {
			if (null != entry.getValue().getPeriod()) {
				String times[] = entry.getValue().getPeriod().split("-");
				entry.getValue().setStartTime(sdf.parse(times[0]));
				entry.getValue().setOriginalStopTime(sdf.parse(times[1]));
			}

			stages.add(entry.getValue());
		}
		Project project = new Project();
		project.setId(define.getProjectId());
		this.projectService.createProjectDefinesAndDefinesStages(define, stages, project, projectTenderRecordId);

		return new ResponseBase();
	}

	/**
	 * 立项书首页
	 */
	@RequestMapping(value = "/{type}/define/view", method = RequestMethod.POST)
	public String list(@PathVariable String type, Model model, Long id) {
		ProjectDefine_Type defineType = null;
		if (("bm").equals(type)) {
			defineType = ProjectDefine_Type.company;
		} else {
			defineType = ProjectDefine_Type.cto;
		}
		List<ProjectDefine> defineList = this.projectDefineService.findProjectDefinesByProjectId(id, defineType, null);
		ProjectDefine define;
		if (defineList.size() > 0) {
			define = defineList.get(0);
		} else {
			define = new ProjectDefine();
		}
		Project project = this.projectService.findProjectDetailById(id);
		this.projectService.updateProjectStatusInById(id);
		model.addAttribute("it", project);
		model.addAttribute("define", define);
		return "/project/" + type + "/define";
	}

	/**
	 * 查看立项书
	 * 
	 * @param type
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{type}/finddefine/view", method = RequestMethod.POST)
	public String findview(@PathVariable String type, Model model, Long id) {

		Project project = this.projectService.findProjectDetailById(id);
		ProjectDefine_Type defineType = null;
		if (("bm").equals(type)) {
			defineType = ProjectDefine_Type.company;
		} else {
			defineType = ProjectDefine_Type.cto;
		}
		List<ProjectDefine> defines = this.projectDefineService.findProjectDefinesByProjectId(id, defineType, null);
		if (defines.size() > 0) {
			ProjectDefine define = defines.get(0);
			model.addAttribute("define", define);
		}
		model.addAttribute("it", project);
		return "/project/" + type + "/find_define";
	}

	/**
	 * 撤销立项书
	 */
	@ResponseBody
	@SystemHandleLog(description = "撤回立项书", type = LogsEnum.disable)
	@RequestMapping(value = "/bm/define/remove", method = RequestMethod.POST)
	public ResponseBase removeBmTender(Long id) {
		ProjectDefine define=this.projectDefineService.updateProjectDefineCancelByProjectId(id, ProjectDefine_Type.company);
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.BM_CANCEL_TENDER_BOOK);
		String url = localCoreConfig.getDomainServer();
		String replace = "<a href='" + url +"/project/"+define.getProjectId()+"/index' target='_blank'>去看看</a>";
		String title = String.format(message.getKey(), define.getProject().getName());
		String content = String.format(message.getValue(), define.getProject().getName(),replace);
		eventPublishService.publishMessageEvent(define.getProject().getCompanyId(), define.getProjectId(), null, title, content,
				true, MemberMessage_Type.project);
		return new ResponseBase();
	}

	/**
	 * 取消定标书
	 */
	@ResponseBody
	@SystemHandleLog(description = "撤回定标书", type = LogsEnum.disable)
	@RequestMapping(value = "/pm/define/remove", method = RequestMethod.POST)
	public ResponseBase removePmTender(Long projectId,Long tenderRecordId) {
		this.projectDefineService.updateProjectDefineCancelByProjectId(projectId, ProjectDefine_Type.cto);
		ProjectTenderRecord tenderRecord=this.projectTenderRecordRepository.find(tenderRecordId);
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PM_CANCEL_DEFINE_BOOK);
		String url = localCoreConfig.getDomainServer();
		String title = String.format(message.getKey(), tenderRecord.getProject().getName());
		String replace = "<a href='" + url +"/project/c/"+projectId+"/define' target='_blank'>去查看</a>";
		String content = String.format(message.getValue(), tenderRecord.getProject().getName(),replace);
		eventPublishService.publishMessageEvent(tenderRecord.getMemberId(), tenderRecord.getProjectId(), null, title, content,
				true, MemberMessage_Type.project);
		return new ResponseBase();
	}
}
