package com.ledao.elite.core.service.member.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberMessage;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Status;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.MemberMessageInfo;
import com.ledao.elite.core.repository.member.MemberMessageRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberMessageService;
import com.ledao.elite.core.utils.DateTimeUtils;

@Service
public class MemberMessageServiceImpl extends BaseSerivceImpl implements MemberMessageService {

	@Resource
	private MemberMessageRepository memberMessageRepository;
	
	@Override
	public MemberMessage createMemberMessage(MemberMessage obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getMemberId(),obj.getTitle());
		obj.setStatus(MemberMessage_Status.unread);
		this.memberMessageRepository.save(obj);
		return obj;
	}

	@Override
	public void updateMemberMessageStatus(Long id, String status) throws EliteServiceException {
		this.verifyParams(status);
		MemberMessage obj=this.findMemberMessageById(id);
		obj.setStatus(MemberMessage_Status.valueOf(MemberMessage_Status.class, status));
		if(status.equals(MemberMessage_Status.read.name())){
			obj.setReadTime(new Date());
		}
		this.memberMessageRepository.save(obj);
	}

	@Override
	public MemberMessage findMemberMessageById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberMessageRepository.find(id);
	}

	@Override
	public Integer findMemberMessageCount(Long memberId,String type,String status,Long projectId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search=new Search(MemberMessage.class);
		search.addFilterEqual("memberId", memberId);
		if(StringUtils.isNotEmpty(status)){
			search.addFilterEqual("status", MemberMessage_Status.valueOf(MemberMessage_Status.class, status));
		}
		if(StringUtils.isNotEmpty(type)){
			search.addFilterEqual("type", MemberMessage_Type.valueOf(MemberMessage_Type.class, type));
		}
		if(projectId!=null){
			search.addFilterEqual("projectId", projectId);
		}
		return this.memberMessageRepository.count(search);
	}
	
	@Override
	public SearchResult<MemberMessage> findMemberMessageList(Long memberId, String status,String type,Long projectId,Pager pager)
			throws EliteServiceException {
		this.verifyParams(memberId);
		if(pager==null)pager=new Pager();
		
		Search search=new Search(MemberMessage.class);
		search.addFilterEqual("memberId", memberId);
		if(StringUtils.isNotEmpty(status)){
			search.addFilterEqual("status", MemberMessage_Status.valueOf(MemberMessage_Status.class, status));
		}
		if(StringUtils.isNotEmpty(type)){
			search.addFilterEqual("type", MemberMessage_Type.valueOf(MemberMessage_Type.class, type));
		}
		if(projectId!=null){
			search.addFilterEqual("projectId", projectId);
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.memberMessageRepository.searchAndCount(search);
	}

	@Override
	public List<MemberMessageInfo> findIndexMessage(Long memberId) {
		
		List<MemberMessageInfo> infos = new ArrayList<MemberMessageInfo>();
		Search search=new Search(MemberMessage.class);
		search.addFilterEqual("memberId", memberId);
		search.addSort("createTime", true);
		
		//系统消息
		MemberMessageInfo systemInfo = new MemberMessageInfo();
		systemInfo.setType(MemberMessage_Type.system.name());
		search.addFilterEqual("type", MemberMessage_Type.system);
		List<MemberMessage> list = this.memberMessageRepository.search(search);
		if(list.size()>0){
			String date= DateTimeUtils.formatDate(list.get(0).getCreateTime(), "yyyy-MM-dd");
			systemInfo.setEndTime(date);
		}
		search.addFilterEqual("status", MemberMessage_Status.unread);
		int count = this.memberMessageRepository.count(search);		
		systemInfo.setCount(count);
		infos.add(systemInfo);
		
		//项目或任务消息
		search.removeFiltersOnProperty("status");
		search.removeFiltersOnProperty("type");
		MemberMessageInfo projectInfo = new MemberMessageInfo();
		projectInfo.setType(MemberMessage_Type.project.name());
		search.addFilterEqual("type", MemberMessage_Type.project);
		List<MemberMessage> projectList = this.memberMessageRepository.search(search);
		if(projectList.size()>0){
			String date= DateTimeUtils.formatDate(projectList.get(0).getCreateTime(), "yyyy-MM-dd");
			projectInfo.setEndTime(date);
		}
		search.addFilterEqual("status", MemberMessage_Status.unread);
		int projectCount = this.memberMessageRepository.count(search);
		projectInfo.setCount(projectCount);
		infos.add(projectInfo);
		
		//活动消息
		search.removeFiltersOnProperty("status");
		search.removeFiltersOnProperty("type");
		MemberMessageInfo activityInfo = new MemberMessageInfo();
		activityInfo.setType(MemberMessage_Type.activity.name());
		search.addFilterEqual("type", MemberMessage_Type.activity);
		List<MemberMessage> activityList = this.memberMessageRepository.search(search);
		if(activityList.size()>0){
			String date= DateTimeUtils.formatDate(activityList.get(0).getCreateTime(), "yyyy-MM-dd");
			activityInfo.setEndTime(date);
		}
		search.addFilterEqual("status", MemberMessage_Status.unread);
		int activityCount = this.memberMessageRepository.count(search);
		activityInfo.setCount(activityCount);
		infos.add(activityInfo);
		return infos;
	}
	
	

}
