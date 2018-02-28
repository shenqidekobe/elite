package com.ledao.elite.rest.controller.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberRecommend;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.domain.project.ProjectSettingStage;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.ProjectTender.ProjectTender_Status;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.domain.project.ProjectTenderRecord.ProjectTenderRecord_Status;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.AttachmentsConstant;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
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
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.RequestBaseRest;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.ProjectForEliteRequest;
import com.ledao.elite.rest.framework.request.TaskForEliteRequest;
import com.ledao.elite.rest.framework.response.LoginResponse;
import com.ledao.elite.rest.framework.response.project.BidTenderInfo;
import com.ledao.elite.rest.framework.response.project.RCProject;
import com.ledao.elite.rest.framework.response.project.RProjectDefine;
import com.ledao.elite.rest.framework.response.project.RProjectSettingStage;
import com.ledao.elite.rest.framework.response.project.RProjectTask;
import com.ledao.elite.rest.framework.response.project.RTaskMember;
import com.ledao.elite.rest.framework.response.project.RTenderProject;
import com.ledao.elite.rest.framework.utils.ColorImageUtils;

/**
 * 精英的项目接口中心
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("projectCompanyEController")
@RequestMapping("/project/e")
public class ProjectCompanyEController extends BaseController{
	
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
	@Resource
	protected LocalCoreConfig localCoreConfig;
	
	
	/**
	 * 查看项目招标书
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/tender/view",method=RequestMethod.POST)
	public ModelAndView viewProjectTender(){
		String reqJson=parseRequest("projectId");
		ProjectForEliteRequest request = JSON.parseObject(reqJson, ProjectForEliteRequest.class);
		Long projectId=request.getProjectId();
		ProjectTender tender=this.projectTenderService.findProjectTenderByProjectId(projectId);
		ProjectTenderRecord tenderrecord = projectTenderRecordService.findProjectTenderRecordByMemberId(projectId,getMemberId());
		RTenderProject project = new RTenderProject(tender);
		Map<String,String> map = ColorImageUtils.getColorImage(localCoreConfig.getRandomColor());
		String imgPath = AttachmentsConstant.ATTA_SERVER_PATH +"/"+"preview/img/projectPicture/png"+"/"+map.get(tender.getProject().getBackgroundColor());
		project.setImgPath(imgPath);
		List<ProjectSettingStage> settingList = projectSettingStageService.findSettingStagesByProjectId(projectId);
		Integer stageCount =0;
		List<String> stages  = project.getStageList();
		stages.add("竞标");stages.add("立项");
		//各阶段名和到第几阶段
		if(tender.getProject().getStatus().equals(Project_Status.pass_already)){ 
			stageCount=1;
		}
		if(tender.getProject().getDefineId()!=null){
			List<ProjectDefineStage> stageList = projectDefineStageService.findProjectDefineStageByDefinedId(tender.getProject().getDefineId());
			for(ProjectDefineStage stage:stageList){
				stages.add(stage.getTitle());
			}
			stageCount=tender.getProject().getTrustStageCount()+1;
		}else{
			for(ProjectSettingStage stage:settingList){
				stages.add(stage.getTitle());
			}
		}
		stages.add("质保期");stages.add("已结束");
		if(tenderrecord!=null){
			project.setStatus(tender.getStatus().equals(ProjectTender_Status.tender_in)?tenderrecord.getStatus().name():tender.getStatus().name());
			if(tenderrecord.getDefineId()!=null){
				project.setStatus(tenderrecord.getStatus().name());
				project.setStageId(tenderrecord.getTrustStage().getId());
				project.setStageStatus(tenderrecord.getTrustStage().getStatus().name());
				project.setStageTitle(tenderrecord.getTrustStage().getTitle());
				if(tenderrecord.getProject().getStatus().equals(Project_Status.quality)){
					project.setStageStatus(tenderrecord.getProject().getStatus().name());
					project.setStageTitle(tenderrecord.getProject().getStatus().getLabel());
					stageCount=stageCount+1;
				}else if(tenderrecord.getProject().getStatus().equals(Project_Status.finish)){
					project.setStageStatus(tenderrecord.getProject().getStatus().name());
					project.setStageTitle(tenderrecord.getProject().getStatus().getLabel());
					stageCount=stageCount+2;
				}
				project.setSendDefined(true);
			}else{
				if(project.getStatus().equals(ProjectTenderRecord_Status.tender_not.name())){
					stages=new ArrayList<>();
					stages.add("竞标");stages.add(ProjectTenderRecord_Status.tender_not.label);
				}else if(project.getStatus().equals(ProjectTender_Status.tender_stop.name())||project.getStatus().equals(ProjectTender_Status.tender_cancel.name())){
					stages=new ArrayList<>();
					stages.add("竞标");stages.add(ProjectTender_Status.tender_stop.label);
				}
			}
		}else{
			project.setStatus(tender.getStatus().equals(ProjectTender_Status.tender_in)?"wait_tender":tender.getStatus().name());
		}
		
		project.setStageCount(stageCount);
		project.setStageList(stages);
		
		ResponseResultData<RTenderProject> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(project);
	}

	/**
	 * CTO项目竞标
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/tender/bid",method=RequestMethod.POST)
	public ModelAndView projectTenderBid(){
		String reqJson=parseRequest("projectId");
		ProjectForEliteRequest request = JSON.parseObject(reqJson, ProjectForEliteRequest.class);
		Long projectId=request.getProjectId();
		ProjectTender tender=this.projectTenderService.findProjectTenderByProjectId(projectId);
		List<ProjectSettingStage> stageList=this.projectSettingStageService.findSettingStagesByProjectId(projectId);
		List<RProjectSettingStage> stages = new ArrayList<RProjectSettingStage>();
		for(ProjectSettingStage stage:stageList){
			stages.add(new RProjectSettingStage(stage));
		}
		BidTenderInfo info = new BidTenderInfo(tender);
		info.setStageList(stages);
		ResponseResultData<BidTenderInfo> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(info);
	}
	
	/**
	 * 保存CTO竞标记录
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/bid/save",method=RequestMethod.POST)
	public ModelAndView saveProjectTenderBid(){
		ResponseResultData<LoginResponse> rsp=new ResponseResultData<>(); 
		ResponseBaseRest rs=new ResponseBaseRest();
		String req=parseRequest("recordStages");
		ProjectForEliteRequest request = JSON.parseObject(req, ProjectForEliteRequest.class);
		String recordStages=request.getRecordStages();
		
		String reqJson=parseRequest("amount","projectId","tenderId","serviceWay","advantage");
		ProjectTenderRecord obj = JSON.parseObject(reqJson, ProjectTenderRecord.class);
		ProjectTender tender = projectTenderService.findProjectTenderById(obj.getTenderId());
		ProjectTenderRecord record=this.projectTenderRecordService.findProjectTenderRecordByProject(obj.getTenderId(), obj.getProjectId(), getMemberId());
		if(record!=null){
			rs.setMsg("您已竞标过此项目");
			rs.setCode(ErrorCodeEnum.TENDER_EXIST.code);
		}else{
			obj.setMemberId(getMemberId());
			obj=this.projectTenderRecordService.createProjectTenderRecord(obj, recordStages);
			
			// 发布竞标日志
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, obj.getProjectId(), null, null,
					getMemberId(), "立项阶段", "我发送了" + tender.getProject().getName() + "项目竞标书", null);
			// 发布竞标消息
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_TENDER_COMPLETE);
			String replace = "<a href='" + localCoreConfig.getDomainServer() + "/project/c/" + obj.getProjectId() + "/index'>查看详情</a>";
			String content = String.format(message.getValue(), tender.getProject().getName(), replace);
			eventPublishService.publishMessageEvent(getMemberId(), obj.getProjectId(), null, message.getKey(), content, false,
					MemberMessage_Type.system);
			// 发送给项目经理
			StringKeyValue messagepm = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PM_TENDER_APPLY);
			String title = String.format(messagepm.getKey(), tender.getProject().getName());
			String replacepm = "<a href='" + localCoreConfig.getDomainServer() + "/manager/project/pm'>看看吧</a>~";
			String contentpm = String.format(messagepm.getValue(), replacepm);
			eventPublishService.publishSysMessageEvent(tender.getProject().getPmId(), null, title, contentpm, false);
		}
		return rsp.responseResult(rs);
	}
	
	/**
	 * CTO查看项目定标书
	 * */
	@ResponseBody
	@RequestMapping(value="/define/view",method=RequestMethod.POST)
	public ModelAndView viewProjectDefine() {
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		ProjectDefine define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.cto,
				ProjectDefine_Status.wait);
		RProjectDefine projectDefine=new RProjectDefine(define);
		ResponseResultData<RProjectDefine> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(projectDefine);
	}
	
	/**
	 * CTO确认项目定标书
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/define/save", method = RequestMethod.POST)
	public ModelAndView saveProjectDefine() {
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		
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
		String replacebm = "<a href='" + localCoreConfig.getDomainServer() + "/manager/project/pm'>去看看吧</a>~";
		String contentbm = String.format(messagebm.getValue(), project.getName(), replacebm);
		eventPublishService.publishSysMessageEvent(project.getPmId(), null, titlebm, contentbm, false);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * CTO查看项目详情
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/project/view",method=RequestMethod.POST)
	public Map<String,Object> viewProject(){
		String reqJson=parseRequest("projectId");
		ProjectForEliteRequest request = JSON.parseObject(reqJson, ProjectForEliteRequest.class);
		Long projectId=request.getProjectId();
		
		Map<String,Object> map = new HashMap<String,Object>();
		Project project=this.projectService.findProjectById(projectId);
		List<ProjectSettingStage> stageList = projectSettingStageService.findSettingStagesByProjectId(projectId);
		MemberRecommend recommend = memberRecommendService.findRecommendProject(getMemberId(), projectId);
		//竞标中
		ProjectTenderRecord tenderrecord = projectTenderRecordService.findProjectTenderRecordByMemberId(projectId,getMemberId());
		map.put("obj", project);
		map.put("stageList", stageList);
		map.put("tenderRecord", tenderrecord);
		map.put("recommend", recommend);
		return map;
	}
	
   /**
    * CTO已中标项目列表
    */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/tender/listData",method=RequestMethod.POST)		
	public ModelAndView viewTenderWinProject(){
		ResponseResultData<List<RCProject>> rsp=new ResponseResultData<>(); 
		String reqJson=parseRequest("pageth","type");
		ProjectForEliteRequest request = JSON.parseObject(reqJson, ProjectForEliteRequest.class);
		Pager pager = new Pager();
		pager.setPageth(request.getPageth());
		List<RCProject> list = new ArrayList<RCProject>();
		if(request.getType().equals("recommend")){
			SearchResult<MemberRecommend> recom = memberRecommendService.findRecommendProjectList(getMemberId(), pager);
			for(MemberRecommend record:recom.getResult()){
				RCProject project = new RCProject(record.getProject());
				project.setStatus(record.getTender().getStatus().equals(ProjectTender_Status.tender_in)?"wait_tender":record.getTender().getStatus().name());
				list.add(project);
			}
		}else if(request.getType().equals("tender")){
			SearchResult<ProjectTenderRecord> result = projectTenderRecordService.findCtoProjectTender(getMemberId(),null, pager);
			for(ProjectTenderRecord record:result.getResult()){
				RCProject project = new RCProject(record.getProject());
				Map<String,String> map = ColorImageUtils.getColorImage(localCoreConfig.getRandomColor());
				String imgPath = AttachmentsConstant.ATTA_SERVER_PATH +"/"+"preview/img/projectPicture/png"+"/"+map.get(record.getProject().getBackgroundColor());
				project.setImgPath(imgPath);
				project.setStatus(record.getTender().getStatus().equals(ProjectTender_Status.tender_in)?record.getStatus().name():record.getTender().getStatus().name());
				if(record.getDefineId()!=null){
					project.setStatus(record.getStatus().name());
					project.setStageId(record.getTrustStage().getId());
					project.setStageStatus(record.getTrustStage().getStatus().name());
					project.setStageTitle(record.getTrustStage().getTitle());
					if(record.getProject().getStatus().equals(Project_Status.quality)){
						project.setStageStatus(record.getProject().getStatus().name());
						project.setStageTitle(record.getProject().getStatus().getLabel());
					}else if(record.getProject().getStatus().equals(Project_Status.finish)){
						project.setStageStatus(record.getProject().getStatus().name());
						project.setStageTitle(record.getProject().getStatus().getLabel());
					}
					project.setSendDefined(true);
				}
				list.add(project);
			}
		}
		return  rsp.responseResult(list);
	}
	
	/**
	 * CTO查看任务详情
	 * */
	@ResponseBody
	@SSOToken
	@RequestMapping(value="/view/taskList",method=RequestMethod.POST)
	public ModelAndView viewProjectTask(){
		String reqJson=parseRequest("projectId","pageth");
		ProjectForEliteRequest request = JSON.parseObject(reqJson, ProjectForEliteRequest.class);
		Pager pager = new Pager();
		pager.setPageth(request.getPageth());
		Long projectId = request.getProjectId();
		List<RProjectTask> list = new ArrayList<>();
		SearchResult<ProjectStageTask> result = projectStageTaskService.findProjectStageTaskListByProjectId(projectId,pager);
		for(ProjectStageTask pst:result.getResult()){
			RProjectTask task= new RProjectTask(pst);
			String imgName = task.getImgName().substring(0,task.getImgName().indexOf("."));
			String imgPath = AttachmentsConstant.ATTA_SERVER_PATH +"/"+"preview/img/taskPicture/png"+"/"+imgName;
			task.setImgPath(imgPath);
			list.add(task);
		}
		ResponseResultData<List<RProjectTask>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}
	
	/**
	 * CTO指定精英列表
	 */
	@SSOToken
	@RequestMapping(value = "/view/applyEliteList", method = RequestMethod.POST)
	public ModelAndView viewApplyElite() {
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Pager pager=new Pager(0,50);
		List<RTaskMember> memberlist = new ArrayList<>();
		SearchResult<ProjectTaskRecruit> list = projectTaskRecruitService.findProjectTaskRecruitByTaskId(getMemberId(),
				request.getDataId(), pager);
		for(ProjectTaskRecruit recruit:list.getResult()){
			RTaskMember member=new RTaskMember(recruit.getInfo());
			member.setTaskId(recruit.getTaskId());
			memberlist.add(member);
		}
		ResponseResultData<List<RTaskMember>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(memberlist);
	}
	
	
	/**
	 * CTO指定精英
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/appoint",method=RequestMethod.POST)
	public ModelAndView appointElite(){
		String reqJson=parseRequest("eliteId","taskId");
		TaskForEliteRequest request = JSON.parseObject(reqJson, TaskForEliteRequest.class);
		ProjectTaskRecruit recuit =  projectTaskRecruitService.findProjectTaskRecruit(request.getEliteId(),request.getTaskId());
		projectTaskRecruitService.updateProjectTask(recuit);
		//确认人选
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, recuit.getProjectId(),recuit.getProject().getForStage().getStageCode(),
				recuit.getTaskId(), recuit.getMemberId(), recuit.getProject().getForStage().getTitle(), "我通过了" + recuit.getTask().getName() + "任务", null);
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * 验收任务
	 * @param passworld
	 * @return
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/task/acceptance",method=RequestMethod.POST)
	public ModelAndView acceptanceTask(){
		ResponseResultData<Object> rs=new ResponseResultData<>(); 
		ResponseBaseRest rsp = new ResponseBaseRest();
		String reqJson=parseRequest("taskId","password");
		TaskForEliteRequest request = JSON.parseObject(reqJson, TaskForEliteRequest.class);
		Long taskId = request.getTaskId();
		MemberPassport member = memberPassportService.findMemberPassportByIdValidPassword(getMemberId(),request.getPassword());
		if(member!=null){
			ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(taskId);
			Integer guaranteeTime=DateTimeUtils.taskQuaTime(task.getDeliveryTime(), task.getStartTime());//质保时间
			task.setGuaranteeTime(guaranteeTime);
			task.setStatus(ProjectTask_Status.quality);
			task.setAcceptTime(new Date());
			projectStageTaskService.updateProjectStageTask(task);
			//验收任务
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
		}else{
			rsp.setCode(ErrorCodeEnum.ACCOUNT_PASS_FAULT.code);
		}		
		return rs.responseResult(rsp);
	}
	
	/**
	 * 关闭任务
	 * @param passworld
	 * @return
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/task/close",method=RequestMethod.POST)
	public ModelAndView closeTask(){
		ResponseResultData<Object> rs=new ResponseResultData<>(); 
		ResponseBaseRest rsp = new ResponseBaseRest();
		String reqJson=parseRequest("taskId");
		TaskForEliteRequest request = JSON.parseObject(reqJson, TaskForEliteRequest.class);
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(request.getTaskId());
		task.setStatus(ProjectTask_Status.closed);
		try {
			projectStageTaskService.updateProjectStageTask(task);
			//关闭任务
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, task.getProjectId(),
					task.getProject().getForStage().getStageCode(), task.getId(), task.getEliteMemberId(), task.getProject().getForStage().getTitle(),
					"我关闭了" + task.getName() + "任务", null);
		} catch (Exception e) {
			rsp.setCode(ErrorCodeEnum.FAILURE.name());
			rsp.setMsg(e.getMessage());
		}
		return rs.responseResult(rsp);
	}
	
	/**
	 * CTO发布任务
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/pushtask",method=RequestMethod.POST)
	public ModelAndView pushTask(){
		ResponseResultData<Object> rs=new ResponseResultData<>(); 
		ResponseBaseRest rsp=new ResponseBaseRest();
		String reqJson=parseRequest("demandType","projectId","stageId","name","taskType","startTime","deliveryTime","endTime","amount","intro");
		ProjectStageTask obj = JSON.parseObject(reqJson, ProjectStageTask.class);
		//该项目所属阶段任务总共金额
		ProjectDefineStage stage = projectDefineStageService.findProjectDefineStageById(obj.getStageId());
		List<ProjectStageTask> taskList=this.projectStageTaskService.findValidProjectStageTask(obj.getStageId());
		BigDecimal amount = obj.getAmount();
		for(ProjectStageTask task:taskList){
			amount=amount.add(task.getAmount());
		}
		boolean flag =projectStageTaskService.findProjectStageTaskByName(obj.getProjectId(), obj.getName());
		rsp.isSuccess(false);
		if(flag){
			rsp.setMsg("不能存在相同的任务名");
			rsp.setCode(ErrorCodeEnum.ATTRIBUTE_NAME_REPEAT.name());
			return rs.responseResult(rsp);
		}else if(stage.getAmount().compareTo(amount)<0){
			rsp.setMsg("该项目当前阶段的总金额已超出");
			rsp.setCode(ErrorCodeEnum.AMOUNT_OVER.name());
			return rs.responseResult(rsp);
		}else{
			rsp.isSuccess(true);
			Project project = projectService.findProjectById(obj.getProjectId());
			try {
				obj.setCreateId(getMemberId());
				ProjectStageTask task = projectStageTaskService.createProjectStageTask(obj,false,getMemberId());
				//发布任务
				eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, project.getId(),
						project.getForStage().getStageCode(), task.getId(), getMemberId(), project.getForStage().getTitle(),
						"我发布了" + obj.getName() + "任务", null);
			} catch (Exception e) {
				rsp.setMsg(e.getMessage());
				rsp.setCode(ErrorCodeEnum.FAILURE.name());
			}
		}
		return rs.responseResult(rsp);
	}

		
	/**
	 *精英-我的任务列表
	 * */
	@ResponseBody
	@SSOToken
	@RequestMapping(value="/task/listData",method=RequestMethod.POST)
	public ModelAndView myTaskOfUnder_way(){
			ResponseResultData<List<RProjectTask>> rsp=new ResponseResultData<>(); 
			String reqJson=parseRequest("type","pageth");
			TaskForEliteRequest request = JSON.parseObject(reqJson, TaskForEliteRequest.class);
			String type = request.getType();
			Pager pager = new Pager();
			pager.setPageth(request.getPageth());
			List<RProjectTask> list = new ArrayList<RProjectTask>();
			
			if (type.equals("recommend")) {// 待领取
				SearchResult<MemberRecommend> result = memberRecommendService.findRecommendTaskList(getMemberId(), pager);
				for(MemberRecommend recommend:result.getResult()){
					list.add(new RProjectTask(recommend.getTask()));
				}
			}else{
				SearchResult<ProjectTaskRecruit> results = new SearchResult<>();
				if(type.equals("claiming")){//认领中
					results = projectTaskRecruitService.findProjectTaskRecruitList(getMemberId(), null, ProjectTaskRecruit.ProjectTaskRecruit_Status.recruit_in.name(), pager);
				}else if(type.equals("claim")){//已领取
					results = projectTaskRecruitService.findClaimProjectTaskRecruitList(getMemberId(), pager);
				}
				for(ProjectTaskRecruit recruit:results.getResult()){
					RProjectTask  task = new RProjectTask(recruit.getTask());
					String imgName = task.getImgName().substring(0,task.getImgName().indexOf("."));
					String imgPath = AttachmentsConstant.ATTA_SERVER_PATH +"/"+"preview/img/taskPicture/png"+"/"+imgName;
					task.setImgPath(imgPath);
					task.setRecruitStatus(recruit.getStatus().name());
					if(!recruit.getStatus().equals(ProjectTaskRecruit_Status.recruit_cancel)){
						task.setApplyFlag(true);
					}
					list.add(task);
				}
			}
			
			return rsp.responseResult(list);
	}
	
	/**
	 *精英-任务主页
	 * */
	@ResponseBody
	@SSOToken
	@RequestMapping(value="/{taskId}/index",method=RequestMethod.POST)
	public ModelAndView taskDetails(){
		ResponseResultData<ProjectStageTask> rsp=new ResponseResultData<>(); 
		String reqJson=parseRequest("taskId");
		TaskForEliteRequest request = JSON.parseObject(reqJson, TaskForEliteRequest.class);
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(request.getTaskId());
		return rsp.responseResult(projectStageTask);
	}
	
	
	/**
	 * 任务大厅的可招募任务列表
	 * */
	@ResponseBody
	@RequestMapping(value="/hall/task/listData",method=RequestMethod.POST)
	public ModelAndView hallTaskListData(){
		String reqJson=parseRequest("pageth","memberId");
		TaskForEliteRequest request = JSON.parseObject(reqJson, TaskForEliteRequest.class);
		Pager pager = new Pager();
		pager.setPageth(request.getPageth());
		Long memberId=request.getMemberId();
		SearchResult<ProjectStageTask> sr=this.projectStageTaskService.findProjectStageTaskList(null, null,null,null, null, null, null, pager);
		List<RProjectTask> list = new ArrayList<RProjectTask>();
		for(ProjectStageTask stageTask:sr.getResult()){
			RProjectTask  task = new RProjectTask(stageTask);
			String imgName = task.getImgName().substring(0,task.getImgName().indexOf("."));
			String imgPath = AttachmentsConstant.ATTA_SERVER_PATH +"/"+"preview/img/taskPicture/png"+"/"+imgName;
			task.setImgPath(imgPath);
			if(memberId!=null && !memberId.equals("")){
				ProjectTaskRecruit apply=this.projectTaskRecruitService.findProjectTaskRecruit(memberId,stageTask.getId());
				if(apply!=null && !apply.getStatus().equals(ProjectTaskRecruit_Status.recruit_cancel)){
					task.setApplyFlag(true);
				}
			}
			list.add(task);
		}
		ResponseResultData<List<RProjectTask>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}
	
	/**
	 *精英-任务详情
	 * */
	@ResponseBody
	@SSOToken
	@RequestMapping(value="/task/view",method=RequestMethod.POST)
	public ModelAndView viewTask(){
		ResponseResultData<RProjectTask> rsp=new ResponseResultData<>(); 
		String reqJson=parseRequest("taskId");
		TaskForEliteRequest request = JSON.parseObject(reqJson, TaskForEliteRequest.class);
		Long taskId = request.getTaskId();
		MemberPassport member = memberPassportService.findMemberPassportById(getMemberId());
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(taskId);
		Integer applyCount=this.projectTaskRecruitService.findProjectTaskApplyCountByTaskId(taskId);
		RProjectTask task = new RProjectTask(projectStageTask);
		String imgName = projectStageTask.getImgName().substring(0,projectStageTask.getImgName().indexOf("."));
		String imgPath = AttachmentsConstant.ATTA_SERVER_PATH +"/"+"preview/img/taskPicture/png"+"/"+imgName;
		task.setImgPath(imgPath);
		ProjectTaskRecruit apply=this.projectTaskRecruitService.findProjectTaskRecruit(getMemberId(),taskId);
		if(apply!=null && !apply.getStatus().equals(ProjectTaskRecruit_Status.recruit_cancel)){
			task.setApplyFlag(true);
		}
		task.setApplyCount(applyCount);
		long deadlineTime = projectStageTask.getEndTime() == null ? 0l: (projectStageTask.getEndTime().getTime() / 1000 - new Date().getTime() / 1000);
		task.setTaskoverVal(deadlineTime);
		List<String> stageList = new ArrayList<>();
		Integer stageCount=0;
		if(member.getCurrentType().equals("cto")||apply==null){
			stageList.add(ProjectTask_Status.wait_recruit.label);
			if(projectStageTask.getStatus().name().equals(ProjectTask_Status.closed.name())){
				stageList.add(ProjectTask_Status.closed.label);
				stageCount=stageCount+1;
			}else{
				stageList.add(ProjectTask_Status.recruit_in.label);
				stageList.add(ProjectTask_Status.starting.label);
				stageList.add(ProjectTask_Status.wait_accept.label);
				stageList.add(ProjectTask_Status.quality.label);
				stageList.add(ProjectTask_Status.finish.label);
				if(projectStageTask.getStatus().name().equals(ProjectTask_Status.recruit_in.name())){
					stageCount=stageCount+1;
				}else if(projectStageTask.getStatus().name().equals(ProjectTask_Status.starting.name())){
					stageCount=stageCount+2;
				}else if(projectStageTask.getStatus().name().equals(ProjectTask_Status.wait_accept.name())){
					stageCount=stageCount+3;
				}else if(projectStageTask.getStatus().name().equals(ProjectTask_Status.quality.name())){
					stageCount=stageCount+4;
				}else if(projectStageTask.getStatus().name().equals(ProjectTask_Status.finish.name())){
					stageCount=stageCount+5;
				}
			}
			
		}else if(member.getCurrentType().equals("elite") && apply!=null){
			stageList.add(ProjectTask_Status.recruit_in.label);
			if(apply.getStatus().name().equals(ProjectTaskRecruit_Status.recruit_not.name())){
				stageList.add(ProjectTaskRecruit_Status.recruit_not.label);
				stageCount=stageCount+1;
			}else if(apply.getStatus().name().equals(ProjectTaskRecruit_Status.recruit_cancel.name())){
				stageList.add(ProjectTaskRecruit_Status.recruit_cancel.label);
				stageCount=stageCount+1;
			}else if(projectStageTask.getStatus().name().equals(ProjectTask_Status.closed.name())){
				stageList.add(ProjectTask_Status.closed.label);
				stageCount=stageCount+1;
			}else if(apply.getStatus().name().equals(ProjectTaskRecruit_Status.recruit_win.name())){
				stageList.add(ProjectTask_Status.starting.label);
				stageList.add(ProjectTask_Status.wait_accept.label);
				stageList.add(ProjectTask_Status.quality.label);
				stageList.add(ProjectTask_Status.finish.label);
				if(projectStageTask.getStatus().name().equals(ProjectTask_Status.starting.name())){
					stageCount=stageCount+1;
				}else if(projectStageTask.getStatus().name().equals(ProjectTask_Status.wait_accept.name())){
					stageCount=stageCount+2;
				}else if(projectStageTask.getStatus().name().equals(ProjectTask_Status.quality.name())){
					stageCount=stageCount+3;
				}else if(projectStageTask.getStatus().name().equals(ProjectTask_Status.finish.name())){
					stageCount=stageCount+4;
				}
			}
		}
		task.setStageList(stageList);
		task.setStageCount(stageCount);
		return rsp.responseResult(task);
	}

	/**
	 *精英-报名or取消报名
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/apply",method=RequestMethod.POST)
	public ModelAndView apply(){
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest rs = new ResponseBaseRest();
		String reqJson=parseRequest("taskId","oper");
		TaskForEliteRequest request = JSON.parseObject(reqJson, TaskForEliteRequest.class);
		Long taskId = request.getTaskId();
		rs.setCode(ErrorCodeEnum.SUCCESS.code);
		MemberPassport member = memberPassportService.findMemberPassportById(getMemberId());
		if(!member.getCurrentType().equals(MemberIdentity_Type.elite.name())
				&&!member.getCurrentType().equals(MemberIdentity_Type.cto.name())){
			rs.setMsg("请注册成为云英汇精英再来报名");
			rs.setCode(ErrorCodeEnum.NOT_ELITE.code);
			return rsp.responseResult(rs);
		}
		MemberElite elite=this.memberEliteService.findMemberEliteByMemberId(getMemberId());
		if(elite==null||!Member_Status.normal.equals(elite.getStatus())){
			rs.setMsg("您的信息还未审核通过，请耐心等待");
			rs.setCode(ErrorCodeEnum.ACCOUNT_AUDIT_UNPASS.code);
			return rsp.responseResult(rs);
		}
//		else if(!elite.isTaked()){
//			rs.setMsg("您正处于休息中，不能接活");
//			rs.setCode(ErrorCodeEnum.ACCOUNT_AUDIT_UNPASS.code);
//			return rsp.responseResult(rs);
//		}
		ProjectStageTask projectStageTask = projectStageTaskService.findProjectStageTaskById(taskId);
		if(!projectStageTask.getStatus().equals(ProjectTask_Status.wait_recruit)
				&&!projectStageTask.getStatus().equals(ProjectTask_Status.recruit_in)){
			rs.setMsg("任务已经报名结束，请关注其他任务");
			rs.setCode(ErrorCodeEnum.TASK_OVER.code);
			return rsp.responseResult(rs);
		}
		Date currentTime=new Date();
		if(currentTime.after(projectStageTask.getEndTime())){
			rs.setMsg("任务已经报名结束，请关注其他任务");
			rs.setCode(ErrorCodeEnum.TASK_OVER.code);
			return rsp.responseResult(rs);
		}
		ProjectTaskRecruit 	obj =  projectTaskRecruitService.findProjectTaskRecruit(getMemberId(), taskId);
		Integer applyCount=this.projectTaskRecruitService.findProjectTaskApplyCountByTaskId(taskId);
		if(obj!=null){
			if(obj.getStatus().equals(ProjectTaskRecruit_Status.recruit_cancel)){
				projectTaskRecruitService.updateProjectTaskRecruitStatus(obj.getId(), "recruit_in");
				applyCount++;
			}else{
				projectTaskRecruitService.updateProjectTaskRecruitStatus(obj.getId(), "recruit_cancel");
				applyCount--;
			}
		}else{
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
			//报名
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.elite, projectStageTask.getProjectId(), projectStageTask.getProject().getForStage().getStageCode(),
					projectStageTask.getId(), getMemberId(), projectStageTask.getProject().getForStage().getTitle(), "我申请了" + projectStageTask.getName() + "任务", null);
			// 系统消息
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_APPLY_TASK_COMPLETE);
			String title = String.format(message.getKey(), projectStageTask.getName());
			String replace = "<a href=" + localCoreConfig.getDomainServer() + "+\"/hall\">任务大厅~</a>";
			String content = String.format(message.getValue(), projectStageTask.getName(), replace);
			eventPublishService.publishSysMessageEvent(getMemberId(), null, title, content, false);
		}
		if(applyCount<0){
			applyCount=0;
		}
		rs.setMsg(applyCount.toString());
		return rsp.responseResult(rs);
		
	}

}