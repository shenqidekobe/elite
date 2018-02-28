package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberEducationRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberEducationService;

@Service
public class MemberEducationServiceImpl extends BaseSerivceImpl implements MemberEducationService {


	@Resource
	private MemberEducationRepository memberEducationRepository;


	@Override
	public MemberEducation createMemberEducation(MemberEducation memberEducation) throws EliteServiceException {
       this.verifyParams(memberEducation,memberEducation.getOrgan(),memberEducation.getType(),memberEducation.getMajor());
		memberEducationRepository.save(memberEducation);
		return memberEducation;
	}
	
	@Override
	public MemberEducation updateMemberEducation(Long id,MemberEducation memberEducation) throws EliteServiceException {
		this.verifyParams(memberEducation, memberEducation.getId());
		MemberEducation obj = memberEducationRepository.find(memberEducation.getId());
		if (obj == null)
			throw new EliteServiceException("未找到该数据",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		BeanUtils.copyProperties(memberEducation, obj,new String[]{"memberId"});
		this.memberEducationRepository.save(obj);
		return obj;
	}

	@Override
	public MemberEducation removePhysicalById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		MemberEducation obj = memberEducationRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到该数据",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return memberEducationRepository.remove(obj) ? obj : null;
	}
	
	@Override
	public MemberEducation findById(Long id)throws EliteServiceException{
		this.verifyParams(id);
		MemberEducation obj = memberEducationRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到该数据",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return obj;
	}

	@Override
	public List<MemberEducation> findByMemberId(Long memberId,String type) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search(MemberEducation.class);
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("type", type);
		search.addSort("startTime", false);
		return memberEducationRepository.search(search);
	}


	

}
