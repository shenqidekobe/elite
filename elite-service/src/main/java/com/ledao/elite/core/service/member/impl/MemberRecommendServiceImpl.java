package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberRecommend;
import com.ledao.elite.core.domain.member.MemberRecommend.MemberRecommend_Status;
import com.ledao.elite.core.domain.member.MemberRecommend.MemberRecommend_Type;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.repository.member.MemberRecommendRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberRecommendService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectStageTaskService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.core.service.project.ProjectTenderRecordService;
import com.ledao.elite.core.service.project.ProjectTenderService;

@Service
public class MemberRecommendServiceImpl extends BaseSerivceImpl implements MemberRecommendService{
	
	@Resource
	private MemberRecommendRepository  memberRecommendRepository;
	@Resource
	private MemberPassportRepository memberPassportRepository;
	@Resource
	private ProjectTenderRecordService projectTenderRecordService;
	@Resource
	private ProjectTenderService projectTenderService;
	@Resource
	private ProjectStageTaskService projectStageTaskService;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource
	protected EventPublishService eventPublishService;
	@Resource
	protected ProjectService projectService;
	@Resource
	protected LocalCoreConfig localCoreConfig;
	
	@Override
	public MemberRecommend createMemberRecommend(MemberRecommend obj) throws EliteServiceException {
		this.verifyParams(obj);
		memberRecommendRepository.save(obj);	
		return obj;
	}

	@Override
	public SearchResult<MemberRecommend> findRecommendProjectList(Long memberId,Pager pager) throws EliteServiceException {
		this.verifyParams(memberId);
		SearchResult<MemberRecommend> sr=new SearchResult<>();
		List<MemberRecommend> results = memberRecommendRepository.findRecommendProjectList(memberId, pager);
		for(MemberRecommend recommend:results){
			ProjectTender tender = projectTenderService.findProjectTenderByProjectId(recommend.getProjectId());
			recommend.setTender(tender);
			if(!recommend.getType().equals(MemberRecommend_Type.r_project)){
				MemberPassport member = memberPassportRepository.find(recommend.getRecommemberId());
				recommend.setRecommemberName(member.getNickName());
			}
			List<ProjectTenderRecord> list = projectTenderRecordService.findProjectTenderRecordsByProjectId(recommend.getProject().getId());
			recommend.setTenderCount(list.size());
		}
		Integer totalCount= memberRecommendRepository.findRecommendProjectCount(memberId);
		sr.setResult(results);
		sr.setTotalCount(totalCount);
		return sr;
	}
	
	@Override
	public MemberRecommend findRecommendProjectByMemberIdAndProjectId(Long memberId, Long projectId)
			throws EliteServiceException {
		// TODO Auto-generated method stub
		this.verifyParams(memberId);
		Search search=new Search();
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("projectId", projectId);
		return this.memberRecommendRepository.searchUnique(search);
	}

	@Override
	public Integer findRecommendProjectCount(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		return memberRecommendRepository.findRecommendProjectCount(memberId);
	}

	@Override
	public SearchResult<MemberRecommend> findRecommendTaskList(Long memberId, Pager pager)
			throws EliteServiceException {
		
		this.verifyParams(memberId);
		SearchResult<MemberRecommend> sr=new SearchResult<>();
		List<MemberRecommend> results = memberRecommendRepository.findRecommendTaskList(memberId, pager);
		for(MemberRecommend recommend:results){
			Integer count = projectTaskRecruitService.findTaskRecruitCount(recommend.getTaskId());
			recommend.setTaskCount(count);
			MemberPassport member = memberPassportRepository.find(recommend.getRecommemberId());
			recommend.setRecommemberName(member.getNickName());
		}
		Integer totalCount= memberRecommendRepository.findRecommendTaskCount(memberId);
		
		sr.setResult(results);
		sr.setTotalCount(totalCount);
		return sr;
	}

	@Override
	public MemberRecommend findRecommendProject(Long memberId, Long projectId) throws EliteServiceException {
		MemberRecommend commend = memberRecommendRepository.findRecommendProject(memberId, projectId);
		if(commend!=null){
			 List<ProjectTenderRecord> list = projectTenderRecordService.findProjectTenderRecordsByProjectId(projectId);
			 commend.setTenderCount(list.size());
			 if(commend.getRecommemberId()!=null){
				 MemberPassport member = memberPassportRepository.find(commend.getRecommemberId());
				 if(member!=null){
					 commend.setRecommemberName(member.getNickName());
				 }
			 }
			 ProjectTender tender = projectTenderService.findProjectTenderByProjectId(commend.getProjectId());
			 if(tender!=null){
			    commend.setTender(tender);
			 }
		}
		return commend;
	}

	@Override
	public void createMemberRecommends(Long[] memberIds,Long recommemberId,MemberRecommend_Type type,Long projectId)throws EliteServiceException {
		
		this.verifyParams(memberIds,type,projectId);
		ProjectStageTask task =null;
		String url=localCoreConfig.getDomainServer();
		StringKeyValue message=MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PLATFORM_REM_CTO_PROJECT);
		String title=message.getKey();
		for(int i=0;i<memberIds.length;i++){
			MemberRecommend obj=this.findRecommendProjectByMemberIdAndProjectId(memberIds[i], projectId);
			if(obj==null){
			obj = new MemberRecommend();
			obj.setRecommemberId(recommemberId);
			obj.setProjectId(projectId);
			obj.setType(type);
			obj.setStatus(MemberRecommend_Status.normal);
			obj.setMemberId(memberIds[i]);
			obj.setTask(task);
			this.memberRecommendRepository.save(obj);
			 String replace="<a href='"+url+"/project/c/"+projectId+"/index' target='_blank'>查看详情</a>";
			 String content = String.format(message.getValue(), replace);
			eventPublishService.publishMessageEvent(memberIds[i],projectId,recommemberId, title, content, true, MemberMessage_Type.system);
			}
		}

	}

	@Override
	public Integer findRecommendTaskCount(Long memberId) throws EliteServiceException {
		return memberRecommendRepository.findRecommendTaskCount(memberId);
	}

	@Override
	public MemberRecommend findRecommendTask(Long memberId, Long taskId) throws EliteServiceException {
		this.verifyParams(memberId,taskId);
		MemberRecommend recom = memberRecommendRepository.findRecommendTask(memberId, taskId);
		if(recom!=null){
			Integer count = projectTaskRecruitService.findTaskRecruitCount(recom.getTaskId());
			MemberPassport member = memberPassportRepository.find(recom.getRecommemberId());
			recom.setTaskCount(count);
			recom.setRecommemberName(member.getNickName());
		}
		return recom;
	}


	
	
	
}
