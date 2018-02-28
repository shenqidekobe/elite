package com.ledao.elite.rest.controller.project;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectLog;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectWeekly;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Type;
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
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.MaterialRequest;
import com.ledao.elite.rest.framework.request.ProjectLogRequest;
import com.ledao.elite.rest.framework.request.WeeklyRequest;
import com.ledao.elite.rest.framework.response.project.RProjectLog;
import com.ledao.elite.rest.framework.response.project.RProjectLogInfo;
import com.ledao.elite.rest.framework.response.project.RProjectMaterial;
import com.ledao.elite.rest.framework.response.project.RProjectWeekly;
import com.ledao.elite.rest.framework.response.project.RProjectWeeklyDetail;
import com.ledao.elite.rest.framework.response.project.RProjectWeeklyInfo;

/**
 * 项目接口中心
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("projectCompanyRController")
@RequestMapping("/project/r")
public class ProjectCompanyRController extends BaseController{
	
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
	 * 项目文件列表数据查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/material/listdata",method=RequestMethod.POST)
	public ModelAndView materialListData(){
		String reqJson=parseRequest("taskId","projectId","stageId","queryType","pageth");
		MaterialRequest request = JSON.parseObject(reqJson, MaterialRequest.class);
		Pager pager = new Pager();
		pager.setPageth(request.getPageth());
		SearchResult<ProjectMaterial>  sr=this.projectMaterialService.findProjectMaterialList(request.getTaskId(),request.getProjectId(), request.getStageId(), getMemberId(), request.getQueryType(), pager);
		List<RProjectMaterial> list = new ArrayList<RProjectMaterial>();
		for(ProjectMaterial material:sr.getResult()){
			if(material.getUploadId()!=null && material.getUploadId().equals(getMemberId())){
				material.setRead(true);
			}
			list.add(new RProjectMaterial(material));
		}
		ResponseResultData<List<RProjectMaterial>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}
	
	/**
	 * 项目周报列表查询
	 * */
	@ResponseBody
	@SSOToken
	@RequestMapping(value="/weekly/listData",method=RequestMethod.POST)
	public ModelAndView weeklyListData(){
		String reqJson=parseRequest("projectId","weeklyType");
		WeeklyRequest request = JSON.parseObject(reqJson, WeeklyRequest.class);
		Calendar cal = Calendar.getInstance();
		int month  = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.MONTH, month-1);
		
		Integer count = 0;
		Integer unCount = 0;
		
		List<RProjectWeeklyInfo> infoList = new ArrayList<>();
		for(int i=month;i>=1;i--){
			RProjectWeeklyInfo info = new RProjectWeeklyInfo();
			String title= i==month?"本月":i+"月";
			Integer monthWeekly=i;
			List<RProjectWeekly> list = new ArrayList<>();
			Map<WeeklyKey,List<ProjectWeekly>> map=new HashMap<>();
			if(request.getWeeklyType().equals("receive")){
				count = count+this.projectWeeklyService.findProjectWeeklyCountAsReceive(request.getTaskId(), request.getProjectId(), getMemberId(),
						ProjectWeekly_Type.company, null, i);
				unCount = unCount + this.projectWeeklyService.findProjectWeeklyCountAsReceive(request.getTaskId(), request.getProjectId(), getMemberId(),
						ProjectWeekly_Type.company, false, i);
				
				map=this.projectWeeklyService.findProjectWeeklyListAsReceive(request.getTaskId(),request.getProjectId(), ProjectWeekly_Type.company.name(), getMemberId(), i);
			}else{
				count = count+this.projectWeeklyService.findProjectWeeklyCountAsSend(request.getTaskId(), request.getProjectId(), getMemberId(),
						ProjectWeekly_Type.cto, null, i);
				unCount = unCount + this.projectWeeklyService.findProjectWeeklyCountAsSend(request.getTaskId(), request.getProjectId(), getMemberId(),
						ProjectWeekly_Type.cto, false, i);
				
				map=this.projectWeeklyService.findProjectWeeklyListAsSend(request.getTaskId(),request.getProjectId(),getMemberId(), i);
			}	
			int weeks=1;
			for(WeeklyKey weekly:map.keySet()){
				RProjectWeekly rweekly = new RProjectWeekly(weekly);
				rweekly.setWeek(weeks);
				weeks=weeks+1;
				if(weekly.exist){
					ProjectWeekly week = map.get(weekly).get(0);
					rweekly.setId(week.getId());
					rweekly.setCreateDate(DateTimeUtils.formatDate(week.getAtta().getCreateTime(), "yyyy.MM.dd"));
					rweekly.setFileName(week.getAtta().getFileName());
					rweekly.setFileSize(week.getAtta().getFileSize());
				}
				list.add(rweekly);
			}
			info.setTitle(title);
			info.setMonthWeekly(monthWeekly);
			info.setWeeklyList(list);
			infoList.add(info);
		}
		RProjectWeeklyDetail detail = new RProjectWeeklyDetail();
		detail.setCount(count);
		detail.setUnReadCount(unCount);
		detail.setList(infoList);
		ResponseResultData<RProjectWeeklyDetail> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(detail);
	}
	
	/**
	 * 精英(cto)任务周报列表查询
	 * */
	@ResponseBody
	@SSOToken
	@RequestMapping(value="/task/weekly/listData",method=RequestMethod.POST)
	public ModelAndView taskWeeklyListData(){
		String reqJson=parseRequest("taskId","weeklyType");
		WeeklyRequest request = JSON.parseObject(reqJson, WeeklyRequest.class);
		Calendar cal = Calendar.getInstance();
		int month  = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.MONTH, month-1);
		
		Integer count = 0;
		Integer unCount = 0;
		
		List<RProjectWeeklyInfo> infoList = new ArrayList<>();
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(request.getTaskId());
		for(int i=month;i>=1;i--){
			RProjectWeeklyInfo info = new RProjectWeeklyInfo();
			String title= i==month?"本月":i+"月";
			Integer monthWeekly=i;
			List<RProjectWeekly> list = new ArrayList<>();
			Map<WeeklyKey,List<ProjectWeekly>> map=new HashMap<>();

			if(request.getWeeklyType().equals("receive")){
				count = count+this.projectWeeklyService.findProjectWeeklyCountAsReceive(request.getTaskId(), request.getProjectId(), getMemberId(),
						ProjectWeekly_Type.cto, null, i);
				unCount = unCount + this.projectWeeklyService.findProjectWeeklyCountAsReceive(request.getTaskId(), request.getProjectId(), getMemberId(),
						ProjectWeekly_Type.cto, false, i);
				map=this.projectWeeklyService.findProjectWeeklyListAsReceive(request.getTaskId(),request.getProjectId(), ProjectWeekly_Type.cto.name(), getMemberId(), i);
			}else{
				count = count+this.projectWeeklyService.findProjectWeeklyCountAsSend(request.getTaskId(), request.getProjectId(), getMemberId(),
						ProjectWeekly_Type.cto, null, i);
				unCount = unCount + this.projectWeeklyService.findProjectWeeklyCountAsSend(request.getTaskId(), request.getProjectId(), getMemberId(),
						ProjectWeekly_Type.cto, false, i);
				map=this.projectWeeklyService.findProjectWeeklyListAsSend(request.getTaskId(),request.getProjectId(), getMemberId(), i);
			}
			int weeks=1;
			for(WeeklyKey weekly:map.keySet()){
				RProjectWeekly rweekly = new RProjectWeekly(weekly);
				rweekly.setWeek(weeks);
				weeks=weeks+1;
				if(task.getClaimTime()!=null && task.getClaimTime().contains(DateTimeUtils.formatDate(weekly.getEndTime(), "yyyy-MM-dd"))){
					rweekly.setClaim(true);
				}
				if(weekly.exist){
					ProjectWeekly week = map.get(weekly).get(0);
					rweekly.setId(week.getId());
					rweekly.setCreateDate(DateTimeUtils.formatDate(week.getAtta().getCreateTime(), "yyyy.MM.dd"));
					rweekly.setFileName(week.getAtta().getFileName());
					rweekly.setFileSize(week.getAtta().getFileSize());
				}
				list.add(rweekly);
			}
			info.setTitle(title);
			info.setMonthWeekly(monthWeekly);
			info.setWeeklyList(list);
			infoList.add(info);
		}
		RProjectWeeklyDetail detail = new RProjectWeeklyDetail();
		detail.setCount(count);
		detail.setUnReadCount(unCount);
		detail.setList(infoList);
		ResponseResultData<RProjectWeeklyDetail> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(detail);
	}
	
	/**
	 * 删除周报
	 * @param id
	 * @return
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/weekly/delete", method = RequestMethod.POST)
	public ModelAndView delWeeklyData(Long id) {
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		projectWeeklyService.delWeekly(request.getDataId());
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}

	/**
	 * 索要周报
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/claim/weekly", method = RequestMethod.POST)
	public ModelAndView claimWeekly() {
		String reqJson=parseRequest("taskId","content");
		WeeklyRequest request = JSON.parseObject(reqJson, WeeklyRequest.class);
		ProjectStageTask task = projectStageTaskService.findProjectStageTaskById(request.getTaskId());
		task.setClaimTime(task.getClaimTime() == null ? request.getContent() : task.getClaimTime() + "," + request.getContent());
		projectStageTaskService.updateProjectStageTask(task);
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_ASK_WEEKLY);
		String title = String.format(message.getKey(), task.getName());
		String contentmessage = String.format(message.getValue(), task.getName());
		eventPublishService.publishMessageEvent(task.getEliteMemberId(), task.getProjectId(), getMemberId(), title,
				contentmessage, false, MemberMessage_Type.project);
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}

	/**
	 * 索要周报
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/project/claim/weekly",method=RequestMethod.POST)
	public ModelAndView claimProjectWeekly(){
		String reqJson=parseRequest("projectId","month","week");
		WeeklyRequest request = JSON.parseObject(reqJson, WeeklyRequest.class);
		Long projectId=request.getProjectId(); int month=request.getMonth(); int week=request.getWeek();
		
		//发送消息给未发周报的cto
		Project project=this.projectService.findProjectById(projectId);
		//向PM索要周报
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_ASK_WEEKLY);
		String title=String.format(message.getKey(), project.getName());
		String content = String.format(message.getValue(),project.getName(),month,week);
		eventPublishService.publishSysMessageEvent(project.getPmId(), null, title, content, false);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}

	
	/**
	 * 项目日志列表数据查询
	 * */
	@ResponseBody
	@SSOToken
	@RequestMapping(value="/log/listdata",method=RequestMethod.POST)
	public ModelAndView projectLogListData(){
		String reqJson=parseRequest("projectId","pageth");
		ProjectLogRequest request = JSON.parseObject(reqJson, ProjectLogRequest.class);
		Date st=null,et=null;
		try {
			if (StringUtils.isNotEmpty(request.getStartTime())) {
			    st=DateUtils.parseDate(request.getStartTime(),GlobalDefinedConstant.System_Date_Patten.DATE.patten);
			}
			if (StringUtils.isNotEmpty(request.getEndTime())) {
				et=DateUtils.parseDate(request.getEndTime(),GlobalDefinedConstant.System_Date_Patten.DATE.patten);
			}
		} catch (ParseException e) {}
		SearchResult<ProjectLog> sr=this.projectLogService.findProjectLogList(request.getProjectId(),getMemberId(), request.getStageCode(), request.getKeyword(),st, et, request.getPager());
		List<RProjectLogInfo> list = new ArrayList<RProjectLogInfo>();
		String key = "";
		List<RProjectLog> listLog = new ArrayList<RProjectLog>();
		for(ProjectLog log:sr.getResult()){
			if(key.equals("")){
				key=log.getStageName();
			}
			if(!log.getStageName().equals(key)){
				list.add(new RProjectLogInfo(key, listLog));
				key=log.getStageName();
				listLog = new ArrayList<RProjectLog>();
				listLog.add(new RProjectLog(log));
			}else{
				listLog.add(new RProjectLog(log));
			}
		}
		if(listLog.size()>0){
			list.add(new RProjectLogInfo(key, listLog));
		}
		ResponseResultData<List<RProjectLogInfo>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}

	/**
	 * 项目任务日志列表数据查询
	 * */
	@SSOToken
	@RequestMapping(value="/log/task/listData",method=RequestMethod.POST)
	public ModelAndView projectLogTaskListData(){
		String reqJson=parseRequest("taskId","pageth");
		ProjectLogRequest request = JSON.parseObject(reqJson, ProjectLogRequest.class);
		Date st=null,et=null;
		try {
			if (StringUtils.isNotEmpty(request.getStartTime())) {
			    st=DateUtils.parseDate(request.getStartTime(),GlobalDefinedConstant.System_Date_Patten.DATE.patten);
			}
			if (StringUtils.isNotEmpty(request.getEndTime())) {
				et=DateUtils.parseDate(request.getEndTime(),GlobalDefinedConstant.System_Date_Patten.DATE.patten);
			}
		} catch (ParseException e) {}
		SearchResult<ProjectLog> sr=this.projectLogService.findTaskLogList(request.getTaskId(),getMemberId(), request.getStageCode(), request.getKeyword(),st, et, request.getPager());
		List<RProjectLogInfo> list = new ArrayList<RProjectLogInfo>();
		String key = "";
		List<RProjectLog> listLog = new ArrayList<RProjectLog>();
		for(ProjectLog log:sr.getResult()){
			if(key.equals("")){
				key=log.getStageName();
			}
			if(!log.getStageName().equals(key)){
				list.add(new RProjectLogInfo(key, listLog));
				key=log.getStageName();
				listLog = new ArrayList<RProjectLog>();
				listLog.add(new RProjectLog(log));
			}else{
				listLog.add(new RProjectLog(log));
			}
		}
		if(listLog.size()>0){
			list.add(new RProjectLogInfo(key, listLog));
		}	
		ResponseResultData<List<RProjectLogInfo>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}

	
}
