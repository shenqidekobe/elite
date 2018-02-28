package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberSkill;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberSkillRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberSkillService;


@Service
public class MemberSkillServiceImpl extends BaseSerivceImpl implements MemberSkillService {

	@Resource
	private MemberSkillRepository memberSkillRepository;
	
	@Override
	public MemberSkill createMemberSkill(MemberSkill obj) throws EliteServiceException {
		this.verifyParams(obj);
		this.memberSkillRepository.save(obj);
		return obj;
	}

	@Override
	public MemberSkill updteMemberSkill(Long id,MemberSkill memberSkill) throws EliteServiceException {
		this.verifyParams(memberSkill);
		MemberSkill obj = this.memberSkillRepository.find(memberSkill.getId());
		if (obj == null)
			throw new EliteServiceException("未找到该数据",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		BeanUtils.copyProperties(memberSkill, obj,new String[]{"memberId"});
		return this.memberSkillRepository.save(obj) ? obj : null;
	}

	@Override
	public void removePhysicalById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		MemberSkill obj = memberSkillRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到该数据",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		memberSkillRepository.remove(obj);
	}
	
	@Override
	public MemberSkill findById(Long id)throws EliteServiceException{
		this.verifyParams(id);
		MemberSkill obj = memberSkillRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到该数据",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return obj;
	}

	@Override
	public List<MemberSkill> findMemberSkillByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search(MemberSkill.class);
		search.addFilter(new Filter("memberId", memberId));
		search.addSort("createTime", false);
		return memberSkillRepository.search(search);
	}

}
