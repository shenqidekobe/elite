package com.ledao.elite.core.service.member.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberEliteJobs;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberEliteJobsRepository;
import com.ledao.elite.core.repository.member.MemberEliteRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberEliteJobsService;

@Service
public class MemberEliteJobsServiceImpl extends BaseSerivceImpl implements MemberEliteJobsService{
	
	@Resource
	private MemberEliteJobsRepository memberEliteJobsRepository;
	@Resource
	private MemberEliteRepository memberEliteRepository;

	@Override
	public MemberEliteJobs createMemberEliteJobs(MemberEliteJobs obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getJobRole(),obj.getEliteId());
		this.memberEliteJobsRepository.save(obj);
		return obj;
	}

	@Override
	public Map<String, String> updateMemberEliteJobsLevel(Long id, MemberEliteJobs obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId(),obj.getLevel());
		MemberEliteJobs pojo=this.findMemberEliteJobsById(id);
		pojo.setLevel(obj.getLevel());
		pojo.setAuditId(obj.getAuditId());
		pojo.setAuditReason(obj.getAuditReason());
		pojo.setAuditTime(new Date());
		this.memberEliteJobsRepository.save(pojo);
		return this.findMemberEliteJobsByEliteId(pojo.getEliteId());
	}

	@Override
	public void removeMemberEliteJobsByEliteId(Long eliteId) throws EliteServiceException {
		this.verifyParams(eliteId);
		this.memberEliteJobsRepository.deleteMemberEliteJobsByEliteId(eliteId);
	}

	@Override
	public MemberEliteJobs findMemberEliteJobsById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberEliteJobsRepository.find(id);
	}

	@Override
	public Map<String, String> findMemberEliteJobsByEliteId(Long eliteId) throws EliteServiceException {
		this.verifyParams(eliteId);
		Map<String, String> map=new HashMap<>();
		Search search=new Search();
		search.addFilterEqual("eliteId", eliteId);
		List<MemberEliteJobs> list=this.memberEliteJobsRepository.search(search);
		if(!list.isEmpty()){
			for(MemberEliteJobs job:list){
				map.put(job.getJobRole(), job.getJobAdept());
			}
		}
		return map;
	}

	@Override
	public List<MemberEliteJobs> findMemberEliteJobsListByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		MemberElite elite=this.memberEliteRepository.searchUnique(new Search().addFilterEqual("memberId", memberId));
		if(elite==null)
			throw new EliteServiceException("未找到此精英信息",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		Long eliteId=elite.getId();
		Search search=new Search();
		search.addFilterEqual("eliteId", eliteId);
		List<MemberEliteJobs> list=this.memberEliteJobsRepository.search(search);
		return list;
	}

	@Override
	public MemberEliteJobs updateMemberEliteJobs(MemberEliteJobs obj) throws EliteServiceException {
		this.verifyParams(obj.getId());
		memberEliteJobsRepository.save(obj);
		return obj;
	}
	
	

}
