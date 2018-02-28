package com.ledao.elite.core.service.project.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberTeam;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.repository.project.ProjectStageTaskRepository;
import com.ledao.elite.core.repository.project.ProjectTaskRecruitRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberTeamService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;

@Service
public class ProjectTaskRecruitServiceImpl extends BaseSerivceImpl implements ProjectTaskRecruitService{
	
	@Resource
	private ProjectTaskRecruitRepository projectTaskRecruitRepository;
	@Resource
	private ProjectStageTaskRepository projectStageTaskRepository;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberTeamService memberTeamService;

	@Override
	public ProjectTaskRecruit createProjectTaskRecruit(ProjectTaskRecruit obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getTaskId(),obj.getMemberId());
		obj.setStatus(ProjectTaskRecruit_Status.recruit_in);
		this.projectTaskRecruitRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectTaskRecruit updateProjectTaskRecruitStatus(Long id, String status) throws EliteServiceException {
		this.verifyParams(status);
		ProjectTaskRecruit obj=this.findProjectTaskRecruitById(id);
		List<ProjectTaskRecruit> tasklist = this.findProjectTaskRecruitByTaskId(obj.getTaskId());
		//如果只有一个人认领且取消后
		ProjectStageTask task = projectStageTaskRepository.find(obj.getTaskId());
		if(tasklist.size()==1 && status.equals(ProjectTaskRecruit_Status.recruit_cancel.name())){
			task.setStatus(ProjectTask_Status.wait_recruit);
		}else{
			task.setStatus(ProjectTask_Status.recruit_in);
		}
		projectStageTaskRepository.save(task);
		obj.setStatus(ProjectTaskRecruit_Status.valueOf(ProjectTaskRecruit_Status.class, status));
		this.projectTaskRecruitRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectTaskRecruit findProjectTaskRecruitById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectTaskRecruitRepository.find(id);
	}

	@Override
	public SearchResult<ProjectTaskRecruit> findProjectTaskRecruitList(Long memberId,Long taskId, String status, Pager pager)
			throws EliteServiceException {
		SearchResult<ProjectTaskRecruit> sr=new SearchResult<>();
		List<ProjectTaskRecruit> results=this.projectTaskRecruitRepository.queryProjectStageTaskHallList(memberId,taskId, status, pager);
		for(ProjectTaskRecruit ptr:results){
			Integer applyCount=this.findProjectTaskApplyCountByTaskId(ptr.getTaskId());
			ptr.setApplyCount(applyCount);
			String applyFlag="N";
			ProjectTaskRecruit apply=this.findProjectTaskRecruit(memberId,ptr.getTaskId());
			if(apply!=null){
				applyFlag="Y";
			}
			ptr.setApplyFlag(applyFlag);
			
		}
		Integer totalCount=this.projectTaskRecruitRepository.queryProjectStageTaskHallCount(memberId,taskId, status);
		sr.setResult(results);
		sr.setTotalCount(totalCount);
		return sr;
	}

	@Override
	public ProjectTaskRecruit findProjectTaskRecruitByTaskIdAndMemberId(Long memberId, Long taskId,String status)
			throws EliteServiceException {
		this.verifyParams(memberId,taskId);
		return this.projectTaskRecruitRepository.findProjectTaskRecruitByTaskIdAndMemberId(memberId, taskId ,status);
	}

	@Override
	public ProjectTaskRecruit findProjectTaskRecruit(Long memberId, Long taskId) throws EliteServiceException {
		this.verifyParams(memberId,taskId);
		Search search = new Search(ProjectTaskRecruit.class);
		search.addFilter(new Filter("memberId", memberId));
		search.addFilter(new Filter("taskId", taskId));
		return projectTaskRecruitRepository.searchUnique(search);
	}

	@Override
	public void updateProjectTask(ProjectTaskRecruit obj) throws EliteServiceException {
		//其它认领的人标注失败
		projectTaskRecruitRepository.updateProjectStageTaskByTaskId(obj.getTaskId(), ProjectTaskRecruit_Status.recruit_not.toString());
		
		obj.setStatus(ProjectTaskRecruit_Status.recruit_win);
		obj.setRecruitTime(new Date());
		this.projectTaskRecruitRepository.save(obj);
		
		ProjectStageTask task = projectStageTaskRepository.find(obj.getTask().getId());
		task.setEliteMemberId(obj.getMemberId());
		task.setStatus(ProjectTask_Status.starting);
		projectStageTaskRepository.save(task);
		
		//加入其团队(cto)
		MemberTeam team = memberTeamService.findMemberTeamByMemberId(obj.getTask().getCreateId(),obj.getMemberId());
		if(team!=null){
			team.setPartnershipNum(team.getPartnershipNum()+1);
			team.setLastTime(new Date());
			memberTeamService.updatetMemberTeam(team);
		}else{
			team = new MemberTeam();
			team.setMemberId(task.getCreateId());
			team.setTeamMemberId(obj.getMemberId());
			team.setLastTime(new Date());
			memberTeamService.createMemberTeam(team);
		}
		//加入其团队(精英)
		MemberTeam memberTeam = memberTeamService.findMemberTeamByMemberId(obj.getMemberId(),obj.getTask().getCreateId());
		if(memberTeam!=null){
			memberTeam.setPartnershipNum(memberTeam.getPartnershipNum()+1);
			memberTeam.setLastTime(new Date());
			memberTeamService.updatetMemberTeam(memberTeam);
		}else{
			memberTeam = new MemberTeam();
			memberTeam.setMemberId(obj.getMemberId());
			memberTeam.setTeamMemberId(obj.getTask().getCreateId());
			memberTeam.setLastTime(new Date());
			memberTeamService.createMemberTeam(memberTeam);
		}
		
	}
	
	@Override
	public Integer findProjectTaskApplyCountByTaskId(Long taskId)throws EliteServiceException{
		this.verifyParams(taskId);
		//TODO:需加入缓存查询
		return this.projectTaskRecruitRepository.count(new Search().addFilterEqual("taskId", taskId).
				addFilterNotIn("status", ProjectTaskRecruit.ProjectTaskRecruit_Status.recruit_cancel));
	}

	@Override
	public List<ProjectTaskRecruit> findProjectTaskRecruitByTaskId(Long taskId) throws EliteServiceException {
		this.verifyParams(taskId);
		Search search = new Search(ProjectTaskRecruit.class);
		search.addFilter(new Filter("taskId", taskId));
		search.addFilterNotEqual("status",ProjectTaskRecruit_Status.recruit_cancel);
		List<ProjectTaskRecruit> list = projectTaskRecruitRepository.search(search);
		for(ProjectTaskRecruit recruit:list){
			MemberDetailInfo memberDetail=memberPassportService.findMemberDetailInfoById(recruit.getMemberId());
			recruit.setInfo(memberDetail);
		}
		return list;
	}

	@Override
	public Integer findProjectTaskRecruitCount(Long memberId,ProjectTaskRecruit_Status status) throws EliteServiceException {
		this.verifyParams(memberId,status);
		Search search = new Search(ProjectTaskRecruit.class);
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("status", status);
		return projectTaskRecruitRepository.count(search);
	}

	@Override
	public Integer findTaskRecruitCount(Long taskId) throws EliteServiceException {
		this.verifyParams(taskId);
		Search search = new Search(ProjectTaskRecruit.class);
		search.addFilterEqual("taskId", taskId);
		search.addFilterEqual("status", ProjectTaskRecruit_Status.recruit_in);
		return projectTaskRecruitRepository.count(search);
	}

	@Override
	public SearchResult<ProjectTaskRecruit> findProjectTaskRecruitByTaskId(Long memberId, Long taskId, Pager pager) throws EliteServiceException  {
		Search search = new Search(ProjectTaskRecruit.class);
		search.addFilterEqual("taskId", taskId);
		search.addFilterNotEqual("status", ProjectTaskRecruit_Status.recruit_cancel);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		SearchResult<ProjectTaskRecruit> list = projectTaskRecruitRepository.searchAndCount(search);
		
		for(ProjectTaskRecruit recruit: list.getResult()){
			MemberDetailInfo memberDetail=memberPassportService.findMemberDetailInfoById(recruit.getMemberId());
			//和精英搭伙次数
			MemberTeam team = memberTeamService.findMemberTeamByMemberId(memberId,recruit.getMemberId());
			if(team!=null){
				recruit.setBoardCount(team.getPartnershipNum());
			}
			//进行中的任务 
			Integer startTaskCount = projectTaskRecruitRepository.memberProjectTaskCount(recruit.getMemberId());
			recruit.setTaskCount(startTaskCount);
			
			recruit.setInfo(memberDetail);
		}
		return list;
	}


	


	@Override
	public void updateProjectTaskRecruitStatus(Long memberId, Long taskId) throws EliteServiceException {
		//没招募到的精英更新 
		List<ProjectTaskRecruit> list = this.findProjectTaskRecruitByTaskId(taskId);
		for(ProjectTaskRecruit recruit:list){
			recruit.setStatus(ProjectTaskRecruit_Status.recruit_not);
			projectTaskRecruitRepository.save(recruit);
		}
		
		ProjectTaskRecruit obj =  this.findProjectTaskRecruit(memberId,taskId);
		obj.setStatus(ProjectTaskRecruit_Status.recruit_win);
		obj.setRecruitTime(new Date());
		this.projectTaskRecruitRepository.save(obj);
		
		ProjectStageTask task = projectStageTaskRepository.find(obj.getTask().getId());
		task.setStatus(ProjectTask_Status.starting);
		projectStageTaskRepository.save(task);
		
	}

	@Override
	public Date findProjectTaskRecuritLastRecruitTime(long memberId) throws EliteServiceException {
		Search search=new Search();
		search.addFilterEqual("memberId", memberId);
		search.addSort("createTime", true);
		search.addFilterEqual("status", ProjectTaskRecruit_Status.recruit_win);
		List<ProjectTaskRecruit>list=this.projectTaskRecruitRepository.search(search);
		if(list.size()==0){
			return null;
		}
		else{
			return list.get(0).getRecruitTime();
		}
	   
	}

	@Override
	public SearchResult<ProjectTaskRecruit> findClaimProjectTaskRecruitList(Long memberId, Pager pager)
			throws EliteServiceException {
		Search search=new Search(ProjectTaskRecruit.class);
		search.addFilterEqual("memberId", memberId);
		search.addSort("createTime", true);
		search.addFilterIn("status", ProjectTaskRecruit_Status.recruit_win,ProjectTaskRecruit_Status.recruit_not,ProjectTaskRecruit_Status.task_closed);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return projectTaskRecruitRepository.searchAndCount(search);
	}

	@Override
	public Integer findClaimProjectTaskRecruitCount(Long memberId) throws EliteServiceException {
		Search search=new Search(ProjectTaskRecruit.class);
		search.addFilterEqual("memberId", memberId);
		search.addFilterIn("status", ProjectTaskRecruit_Status.recruit_win,ProjectTaskRecruit_Status.recruit_not,ProjectTaskRecruit_Status.task_closed);
		return projectTaskRecruitRepository.count(search);
	}

	@Override
	public void updateExpireTask() throws EliteServiceException {
		projectStageTaskRepository.updateExpireTask();
		this.projectTaskRecruitRepository.updateExpireRecruitTask();
	}
	
}
