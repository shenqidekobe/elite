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
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectLog;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectMaterial.ProjectMaterial_Status;
import com.ledao.elite.core.domain.project.ProjectWeekly;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.dto.site.WeeklyKey;
import com.ledao.elite.core.service.project.ProjectLogService;
import com.ledao.elite.core.service.project.ProjectMaterialService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectWeeklyService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 项目相关信息控制器
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
	private ProjectLogService projectLogService;
	@Resource
	private ProjectMaterialService projectMaterialService;
	@Resource
	private ProjectWeeklyService projectWeeklyService;
	
	/**
	 * 项目文件列表
	 * */
	@SSOToken
	@RequestMapping(value="/material",method=RequestMethod.POST)
	public String materialList(Model model,Long projectId){
		Project project = projectService.findProjectById(projectId);
		Integer receiveCount=this.projectMaterialService.findProjectMaterialCount(null,projectId, null, getMemberId(), ProjectMaterial.QUERYTYPE_RECEIVE);
		Integer sendCount=this.projectMaterialService.findProjectMaterialCount(null,projectId, null, getMemberId(), ProjectMaterial.QUERYTYPE_SEND);
		Integer unreadCount=this.projectMaterialService.findProjectMaterialCount(null,projectId, null, getMemberId(), ProjectMaterial.QUERYTYPE_UNREAD);
		model.addAttribute("receiveCount",receiveCount);
		model.addAttribute("sendCount",sendCount);
		model.addAttribute("unreadCount",unreadCount);
		model.addAttribute("project", project);
		return "project/record/material_list";
	}
	
	/**
	 * 项目文件列表数据查询
	 * */
	@SSOToken
	@RequestMapping(value="/material/listData",method=RequestMethod.POST)
	public String materialListData(Long projectId,Long stageId,String queryType,Pager pager,Model model){
		SearchResult<ProjectMaterial>  sr=this.projectMaterialService.findProjectMaterialList(null,projectId, stageId, getMemberId(), queryType, pager);
		pager.calPageCount((long)sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		model.addAttribute("queryType", queryType);
		return "project/record/material_list_frag";
	}
	
	/**
	 * 保存文件信息
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/material/save",method=RequestMethod.POST)
	public ResponseResult<String> saveProjectMaterial(Long receiveId,Long projectId,Long stageId,Long attaId,
			String status,@RequestParam(value = "isDelivery", required = false) boolean isDelivery){
		Project project=this.projectService.findProjectById(projectId);
		if(project==null){
			throw new EliteServiceException("项目不存在", ErrorCodeEnum.FAILURE.code);
		}
		ProjectMaterial obj=new ProjectMaterial();
		obj.setProjectId(projectId);
		obj.setReceiveId(receiveId);
		ProjectDefineStage stage=project.getForStage();
		obj.setStageId(stage==null?null:stage.getId());
		obj.setAttaId(attaId);
		obj.setUploadId(getMemberId());
		obj.setDeliveryed(isDelivery);
		obj.setStatus(ProjectMaterial_Status.valueOf(ProjectMaterial_Status.class, status));
		this.projectMaterialService.createProjectMaterial(obj);
		return new ResponseResult<>();
	}
	
	/**
	 * 删除文件记录
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/material/remove",method=RequestMethod.POST)
	public ResponseResult<String> removeProjectMaterial(Long id){
		this.projectMaterialService.removeProjectMaterialById(id, getMemberId());
		return new ResponseResult<>();
	}
	
	/**
	 * 下载文件
	 * */
	@SSOToken
	@RequestMapping(value="/material/read/{id}",method=RequestMethod.GET)
	public String downMaterial(@PathVariable Long id){
		ProjectMaterial material=this.projectMaterialService.updateProjectMaterialStatus(id,true);
		if(material==null||material.getAttaId()==null){
			return ERROR_VIEW;
		}
		return REDIRECT_COMMAND+material.getAtta().getDownPath();
	}
	
	/**
	 * 项目日志列表
	 * */
	@SSOToken
	@RequestMapping(value="/log",method=RequestMethod.POST)
	public String projectLogList(Long projectId,Model model){
		Project project=this.projectService.findProjectById(projectId);
		Integer count=this.projectLogService.findProjectLogCount(projectId,ProjectLog_Type.company);
		model.addAttribute("count",count);
		model.addAttribute("obj", project);
		return "/project/record/project_log_list";
	}
	
	/**
	 * 项目日志列表数据查询
	 * */
	@SSOToken
	@RequestMapping(value="/log/listData",method=RequestMethod.POST)
	public String projectLogListData(Long projectId,String stageCode,String startTime,String endTime,String keyword,Pager pager,Model model){
		pager = new Pager(0,50);
		Date st=null,et=null;
		try {
			if (StringUtils.isNotEmpty(startTime)) {
			    st=DateUtils.parseDate(startTime,GlobalDefinedConstant.System_Date_Patten.DATE.patten);
			}
			if (StringUtils.isNotEmpty(endTime)) {
				et=DateUtils.parseDate(endTime,GlobalDefinedConstant.System_Date_Patten.DATE.patten);
			}
		} catch (ParseException e) {}
		SearchResult<ProjectLog> sr=this.projectLogService.findProjectLogList(projectId,getMemberId(), stageCode, keyword,st, et, pager);
		pager.calPageCount((long)sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		return "/project/record/project_log_list_frag";
	}
	
	/**
	 * 项目周报列表
	 * */
	@SSOToken
	@RequestMapping(value="/weekly",method=RequestMethod.POST)
	public String weeklyList(Long projectId,Integer month,Model model){
		Project project=this.projectService.findProjectById(projectId);
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
        model.addAttribute("startTime",DateTimeUtils.monthFormat.format(start));
        model.addAttribute("endTime",DateTimeUtils.monthFormat.format(end));
		return "project/record/weekly_list";
	}

	/**
	 * 项目周报列表查询
	 * */
	@SSOToken
	@RequestMapping(value="/weekly/listData",method=RequestMethod.POST)
	public String weeklyListData(Long projectId,int month,Model model){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.MONTH, month-1);
		Map<WeeklyKey,List<ProjectWeekly>> map=this.projectWeeklyService.findProjectWeeklyListAsReceive(null,projectId, ProjectWeekly_Type.company.name(), getMemberId(), month);
		Integer count=this.projectWeeklyService.findProjectWeeklyCountAsReceive(null,projectId, getMemberId(), ProjectWeekly_Type.company, null,month);
		Integer unCount=this.projectWeeklyService.findProjectWeeklyCountAsReceive(null,projectId, getMemberId(), ProjectWeekly_Type.company, false,month);
		model.addAttribute("count", count);
		model.addAttribute("unCount", unCount);
		model.addAttribute("weeklyMap", map);
		model.addAttribute("month", cal.getTime());
		model.addAttribute("monthNum", month);
		model.addAttribute("projectId", projectId);
		return "project/record/weekly_list_frag";
	}
	
	/**
	 * 下载周报
	 * */
	@SSOToken
	@RequestMapping(value="/read/weekly/{id}",method=RequestMethod.GET)
	public String downWeekly(@PathVariable Long id){
		ProjectWeekly weekly=this.projectWeeklyService.findProjectWeeklyById(id);
		if(weekly==null||weekly.getAttaId()==null){
			return ERROR_VIEW;
		}
		weekly.setReaded(true);
		weekly.setCheckTime(new Date());
		this.projectWeeklyService.updateProjectWeeklyStatus(weekly);
		return REDIRECT_COMMAND+weekly.getAtta().getDownPath();
	}
	
	/**
	 * 索要周报
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/claim/weekly",method=RequestMethod.POST)
	public ResponseResult<String> claimWeekly(Long projectId,int month,int week){
		//发送消息给未发周报的cto
		Project project=this.projectService.findProjectById(projectId);
		//向PM索要周报
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_ASK_WEEKLY);
		String title=String.format(message.getKey(), project.getName());
		String content = String.format(message.getValue(),project.getName(),month,week);
		eventPublishService.publishSysMessageEvent(project.getPmId(), null, title, content, false);
		return new ResponseResult<>();
	}
}
