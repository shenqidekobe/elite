package com.ledao.elite.core.service.project.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.repository.project.ProjectStageTaskRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.project.ProjectStageTaskService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.core.service.sys.CommonCodeService;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.core.utils.DateTimeUtils;

@Service("projectStageTaskService")
public class ProjectStageTaskServiceImpl extends BaseSerivceImpl implements ProjectStageTaskService {

	@Resource
	private ProjectStageTaskRepository projectStageTaskRepository;

	@Resource
	private CommonCodeService commonCodeService;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource
	private MemberPassportService memberPassportService;
	
	@Resource
	protected EventPublishService eventPublishService;
	
	@Resource
	protected LocalCoreConfig localCoreConfig;

	@Override
	public ProjectStageTask createProjectStageTask(ProjectStageTask obj, boolean isSend, Long memberId)
			throws EliteServiceException {
		this.verifyParams(obj, obj.getProjectId(), obj.getStageId(), obj.getName(), obj.getAmount());
		String taskNum = commonCodeService.disposeOddNumber("taskNum", COMMON_PREVAL.T.name(), "yyyyMMdd", 8, null);
		String backgroundColor=CommonUtils.getRandColorCode();
		List<String> imgList=this.localCoreConfig.getTaskRandomImg();
		int size=imgList.size();
		int index=size<=1?0:new Random().nextInt(size);
		String imgName=imgList.get(index);
		obj.setImgName(imgName);
		obj.setBackgroundColor(backgroundColor);
		obj.setTaskNum(taskNum);
		obj.setStatus(ProjectTask_Status.wait_recruit);
		this.projectStageTaskRepository.save(obj);
		if (isSend) {
			// TODO:发送给memberId
		}
		return obj;
	}

	@Override
	public void updateProjectStageTask(ProjectStageTask obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getId(), obj.getStatus());
		this.projectStageTaskRepository.save(obj);
	}
	
	@Override
	public void updateProjectStageTaskAsExpire()throws EliteServiceException{
//		Search search=new Search();
//		search.addFilterLessOrEqual("endTime",Calendar.getInstance().getTime());
//		search.addFilterIn("status", ProjectTask_Status.wait_recruit,ProjectTask_Status.recruit_in);
//		List<ProjectStageTask> list=this.projectStageTaskRepository.search(search);
//		
//		for(ProjectStageTask pst:list){
//			pst.setStatus(ProjectTask_Status.closed);
//			this.projectStageTaskRepository.save(pst);
//		}
		projectTaskRecruitService.updateExpireTask();
	}
	
	@Override
	public void updateApplyTaskAsExpire()throws EliteServiceException{
		Search search=new Search();
		
		search.addFilterLessOrEqual("deliveryTime",DateTimeUtils.addOrSub(new Date(), -7));
		search.addFilterIn("status", ProjectTask_Status.starting);
		List<ProjectStageTask> list=this.projectStageTaskRepository.search(search);
		
		for(ProjectStageTask pst:list){
			pst.setStatus(ProjectTask_Status.closed);
			this.projectStageTaskRepository.save(pst);
		}
	}

	@Override
	public ProjectStageTask findProjectStageTaskById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		ProjectStageTask task = this.projectStageTaskRepository.find(id);
		if(task.getEliteMemberId()!=null){
			MemberDetailInfo memberDetail=memberPassportService.findMemberDetailInfoById(task.getEliteMemberId());
			task.setMember(memberDetail);
		}
		return task;
	}

	@Override
	public SearchResult<ProjectStageTask> findProjectStageTaskList(Long projectId, String taskType,String demandType,
			BigDecimal minAmount, BigDecimal maxAmount, Long stageId, String status, Pager pager)
			throws EliteServiceException {
		if (pager == null) {
			pager = new Pager(0, 12);
		}
		SearchResult<ProjectStageTask> sr = new SearchResult<>();
		List<ProjectStageTask> results = this.projectStageTaskRepository.queryProjectStageTaskHallList(projectId,
				taskType,demandType,minAmount, maxAmount, stageId, status, pager);
		for(ProjectStageTask pst:results){
			Integer applyCount=this.projectTaskRecruitService.findProjectTaskApplyCountByTaskId(pst.getId());
			pst.setApplyCount(applyCount);
		}
		Integer totalCount = this.projectStageTaskRepository.queryProjectStageTaskHallCount(projectId, taskType,demandType,
				minAmount, maxAmount, stageId, status);
		sr.setResult(results);
		sr.setTotalCount(totalCount);
		return sr;
	}

	@Override
	public ProjectStageTask updateProjectStageTaskStatus(Long id, String status) throws EliteServiceException {
		this.verifyParams(status);
		ProjectStageTask obj = this.findProjectStageTaskById(id);
		obj.setStatus(ProjectTask_Status.valueOf(ProjectTask_Status.class, status));
		this.projectStageTaskRepository.save(obj);
		return obj;
	}

	@Override
	public List<ProjectStageTask> findProjectStageTask(Long stageId,Long projectId) throws EliteServiceException {
		Search search = new Search();
		if(stageId!=null){
			search.addFilterEqual("stageId", stageId);
		}
		if(projectId!=null){
			search.addFilterEqual("projectId", projectId);
		}
		search.addFilterIn("status", ProjectTask_Status.starting,ProjectTask_Status.wait_accept,ProjectTask_Status.accept_success,ProjectTask_Status.quality,ProjectTask_Status.finish);
		search.addFilterNotEmpty("eliteMemberId");
		List<ProjectStageTask> list =  this.projectStageTaskRepository.search(search);
		for(ProjectStageTask task:list){
			MemberPassport member = memberPassportService.findMemberPassportById(task.getEliteMemberId());
			task.setMember(member);
		}
		return this.projectStageTaskRepository.search(search);
	}
	
	@Override
	public List<ProjectStageTask> findValidProjectStageTask(Long stageId) throws EliteServiceException {
		this.verifyParams(stageId);
		Search search = new Search();
		search.addFilterEqual("stageId", stageId);
		search.addFilterNotEqual("status",ProjectTask_Status.closed);
		return this.projectStageTaskRepository.search(search);
	}
	
	@Override
	public SearchResult<ProjectStageTask> findProjectStageTaskListByProjectId(Long projectId,Pager pager) {
		this.verifyParams(projectId);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		SearchResult<ProjectStageTask> result = projectStageTaskRepository.searchAndCount(search);
		for (ProjectStageTask task : result.getResult()) {
			if(task.getEliteMemberId()!=null){
				MemberPassport member = memberPassportService.findMemberPassportById(task.getEliteMemberId());
				task.setMember(member);
			}
			List<ProjectTaskRecruit> recruitList = projectTaskRecruitService.findProjectTaskRecruitByTaskId(task.getId());
			task.setRecruitList(recruitList);
		}
		return result;
	}
	
	@Override
	public List<ProjectStageTask> findProjectStageTaskByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search();
		search.addFilterEqual("eliteMemberId", memberId);
		return this.projectStageTaskRepository.search(search);
	}
	
	@Override
	public List<ProjectStageTask> findProjectStageTaskByStatus(Long stageId,ProjectTask_Status status)throws EliteServiceException{
		this.verifyParams(status);
		Search search = new Search();
		if(stageId!=null){
			search.addFilterEqual("stageId",stageId);
		}
		search.addFilterEqual("status",status);
		return this.projectStageTaskRepository.search(search);
	}

	@Override
	public void updateProjectStageTaskForRemind(ProjectStageTask task) throws EliteServiceException {
		this.verifyParams(task);
		this.projectStageTaskRepository.save(task);
		eventPublishService.publishMessageEvent(task.getEliteMemberId(), task.getProjectId(), task.getEliteMemberId(), "亲,你的任务时间快到请速度提交产物", null, false,MemberMessage_Type.project);
	}

	@Override
	public boolean findProjectStageTaskByName(Long projectId, String name)throws EliteServiceException {
		this.verifyParams(projectId,name);
		Search search = new Search();
		search.addFilterEqual("projectId",projectId);
		search.addFilterEqual("name",name);
		ProjectStageTask task = this.projectStageTaskRepository.searchUnique(search);
		if(task!=null){
			return true;
		}
		return false;
	}
	
	@Override
	public Integer findProjectStageTaskCount(Long memberId)throws EliteServiceException{
		this.verifyParams(memberId);
		Search search=new Search();
		search.addFilterEqual("createId", memberId);
		search.addFilterNotIn("status", ProjectTask_Status.closed);
		return this.projectStageTaskRepository.count(search);
	}
	
}
