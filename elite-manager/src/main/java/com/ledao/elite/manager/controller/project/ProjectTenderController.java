package com.ledao.elite.manager.controller.project;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectSettingStage;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.ProjectTender.ProjectTender_Status;
import com.ledao.elite.core.domain.project.ProjectTenderRecord.ProjectTenderRecord_Status;
import com.ledao.elite.core.framework.annotation.ProjectNewNotices;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.constant.NoticeTypeEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.platform.PlatformWorkRecordService;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectSettingStageService;
import com.ledao.elite.core.service.project.ProjectTenderRecordService;
import com.ledao.elite.core.service.project.ProjectTenderService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 项目招标书控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectTenderController")
@RequestMapping("/project")
public class ProjectTenderController extends BaseController {

	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectTenderService projectTenderService;
	@Resource
	private ProjectTenderRecordService projectTenderRecordService;
	@Resource
	private ProjectDefineService projectDefineService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private PlatformWorkRecordService platformWorkRecordService;
	@Resource
	private ProjectSettingStageService projectSettingStageService;

	/**
	 * 发招标书首页
	 */
	@RequestMapping(value = "/{type}/tender/view", method = RequestMethod.POST)
	public String view(@PathVariable String type, Model model, Long id) {

		Project obj = this.projectService.findProjectById(id);
		List<ProjectSettingStage> stages = this.projectSettingStageService.findSettingStagesByProjectId(id);
		model.addAttribute("it", obj);
		model.addAttribute("list", stages);
		return "/project/tender/tender";
	}

	/**
	 * 发招标招标书
	 */
	@ResponseBody
	@RequestMapping(value = "/{type}/tender/save", method = RequestMethod.POST)
	@SystemHandleLog(description = "发招标书", type = LogsEnum.create)
	public ResponseBase save(@PathVariable String type, Project project, ProjectTender tender, int qualitTyMonth) {
		tender.setQualityTime(DateTimeUtils.addMonth(tender.getEndTime(), qualitTyMonth));
		this.projectService.createResetProjectTender(project, tender);
		return new ResponseBase();
	}

	/**
	 * 查看招标书
	 */
	@RequestMapping(value = "/tender/findeview", method = RequestMethod.POST)
	public String viewTender(Model model, Long id) {
		ProjectTender tender = this.projectTenderService.findProjectTenderByProjectId(id);
		List<ProjectSettingStage> stages = this.projectSettingStageService.findSettingStagesByProjectId(id);
		model.addAttribute("it", tender);
		model.addAttribute("stages", stages);
		return "/project/tender/find_tender";
	}

	/**
	 * 查看招标书 根据tenderId 查询
	 */
	@RequestMapping(value = "/tender/view/detail", method = RequestMethod.POST)
	public String viewTenderByTenderId(Model model, Long id) {
		ProjectTender tender = this.projectTenderService.findProjectTenderById(id);
		List<ProjectSettingStage> stages = this.projectSettingStageService
				.findSettingStagesByProjectId(tender.getProjectId());
		model.addAttribute("it", tender);
		model.addAttribute("stages", stages);
		return "/project/tender/find_tender";
	}

	/**
	 * 取消招标书首页
	 */
	@RequestMapping(value = "/tender/view/remove", method = RequestMethod.POST)
	public String viewRemoveTender(Model model, Long id) {
		ProjectTender tender = this.projectTenderService.findProjectTenderById(id);
		model.addAttribute("tender", tender);
		return "/project/tender/remove_tender";
	}

	/**
	 * 取消招标书
	 */
	@ResponseBody
	@RequestMapping(value = "/tender/remove", method = RequestMethod.POST)
	@SystemHandleLog(description = "取消招标书", type = LogsEnum.remove)
	public ResponseBase removeTender(Long id, String reason) {
		ProjectTender tender = this.projectTenderService.findProjectTenderById(id);
		tender.setReason(reason);
		tender.setCloseTime(new Date());
		tender.setStatus(ProjectTender_Status.tender_cancel);
		this.projectTenderService.updateRemoveProjectTender(tender);
		return new ResponseBase();
	}

	/**
	 * 投标列表首页
	 */
	@ProjectNewNotices(controllerType = "remove", noticeType = NoticeTypeEnum.CtoTender)
	@RequestMapping(value = "/{type}/tender/list", method = RequestMethod.POST)
	public String list(@PathVariable String type, Model model, Long projectId) {
		ProjectTender tender = this.projectTenderService.findProjectTenderByProjectId(projectId);
		model.addAttribute("id", projectId);
		model.addAttribute("lastTender", tender);
		return "/project/tender/list";
	}

	/**
	 * 查看竞标书列表
	 */
	@RequestMapping(value = "/{type}/tender/listDate", method = RequestMethod.POST)
	public String listDate(@PathVariable String type, Model model, Long id, Long tenderId, String status, Pager pager) {
		SearchResult<ProjectTenderRecord> tenderResult = this.projectTenderRecordService
				.findProjectTenderRecordsByProjectId(id, tenderId, status, pager);
		Project project = this.projectService.findProjectDetailByIdAndType(id, "pm");
		List<ProjectTenderRecord> records = tenderResult.getResult();
		Boolean tenderWined=this.projectTenderRecordService.findProjectTenderWin(tenderId,ProjectTenderRecord_Status.tender_win);
	   // cto是否确认定标书
		Boolean tenderNormal=this.projectTenderRecordService.findProjectTenderWin(tenderId,ProjectTenderRecord_Status.tender_normal);
		pager.calPageCount((long) tenderResult.getTotalCount());
		model.addAttribute("list", records);
		model.addAttribute("pager", pager);
		model.addAttribute("project", project);
		model.addAttribute("tenderWined",tenderWined);
		model.addAttribute("tenderNormal",tenderNormal);
		return "/project/tender/list_frag";
	}

	/**
	 * 查看竞标书详情
	 */
	@RequestMapping(value = "/tender/list/detail")
	public String viewDetail(Model model, Long id) {
		ProjectTenderRecord tenderRecord = this.projectTenderRecordService.findProjectTenderRecordById(id);
		model.addAttribute("it", tenderRecord);
		MemberPassport member = this.memberPassportService.findMemberDetailInfoById(tenderRecord.getMemberId());
		model.addAttribute("member", member);
		return "/project/tender/detail";
	}

	/**
	 * 定标首页
	 */
	@RequestMapping(value = "/tender/calibration/view")
	public String viewCalibration(Model model, Long id) {
		ProjectTenderRecord record = this.projectTenderRecordService.findProjectTenderRecordById(id);
		model.addAttribute("it", record);
		return "/project/tender/calibration";
	}

	/**
	 * 定标
	 */
	@ResponseBody
	@RequestMapping(value = "/tender/settender", method = RequestMethod.POST)
	@SystemHandleLog(description = "定标", type = LogsEnum.saveOrUpdate)
	public ResponseBase saveCalibration(Long recordId) {
		// TODO:验证项目是否确认立项书
		ProjectTenderRecord tenderRecord = this.projectTenderRecordService.findProjectTenderRecordById(recordId);
		ProjectDefine companyDefine = projectDefineService.findProjectDefine(tenderRecord.getProjectId(),
				ProjectDefine_Type.company, ProjectDefine_Status.normal);
		if (companyDefine == null) {
			return new ResponseBase("项目未确认立项书，不能定标");
		}
		// 定标
		this.projectTenderRecordService.updateProjectTenderRecordById(recordId);
		return new ResponseBase();
	}

	/**
	 * 取消定标
	 */
	@ResponseBody
	@RequestMapping(value = "/tender/removetender", method = RequestMethod.POST)
	@SystemHandleLog(description = "取消定标", type = LogsEnum.update)
	public ResponseBase savereCalibration(Long recordId) {
		ProjectTenderRecord tenderRecord = this.projectTenderRecordService.findProjectTenderRecordById(recordId);
		tenderRecord.setStatus(ProjectTenderRecord_Status.tender_in);
		this.projectTenderRecordService.updateProjectTenderRecord(tenderRecord);
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_TENDER_CANCEL);
		String url = localCoreConfig.getDomainServer();
		String title = String.format(message.getKey(), tenderRecord.getProject().getName());
		String replace = "<a href='" + url +"/member/index' target='_blank'>去查看</a>";
		String content = String.format(message.getValue(), tenderRecord.getProject().getName(),replace);
		eventPublishService.publishMessageEvent(tenderRecord.getMemberId(), tenderRecord.getProjectId(), null, title, content,
				true, MemberMessage_Type.project);
     	return new ResponseBase();
	}

}
