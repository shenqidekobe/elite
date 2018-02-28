package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberProjectsRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberProjectsService;

@Service
public class MemberProjectsServiceImpl extends BaseSerivceImpl implements MemberProjectsService {

	@Resource
	private MemberProjectsRepository memberProjectsRepository;

	@Override
	public MemberProjects createMemberProject(MemberProjects memberProjects) throws EliteServiceException {
		this.verifyParams(memberProjects,memberProjects.getProject(),memberProjects.getMemberId());
		memberProjectsRepository.save(memberProjects);
		return memberProjects;
	}

	@Override
	public MemberProjects updateMemberProject(Long id,MemberProjects memberProjects) throws EliteServiceException {
		this.verifyParams(memberProjects, memberProjects.getId());
		MemberProjects obj = memberProjectsRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到该项目信息",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		BeanUtils.copyProperties(memberProjects, obj,new String[]{"memberId"});
		this.memberProjectsRepository.save(obj);
		return obj;
	}

	@Override
	public MemberProjects removePhysicalProjectById(Long ProjectId) throws EliteServiceException {
		MemberProjects obj = memberProjectsRepository.find(ProjectId);
		if (obj == null)
			throw new EliteServiceException("未找到该项目信息",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return memberProjectsRepository.remove(obj) ? obj : null;
	}

	@Override
	public List<MemberProjects> findByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search(MemberProjects.class);
		search.addFilter(new Filter("memberId", memberId));
		search.addSort("startTime", true);//倒序
		return memberProjectsRepository.search(search);
	}

	@Override
	public MemberProjects findById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		MemberProjects obj = memberProjectsRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到该项目信息",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return obj;
	}

}
