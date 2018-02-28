package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.member.MemberIdentityRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberIdentityService;

@Service
public class MemberIdentityServiceImpl extends BaseSerivceImpl implements MemberIdentityService{
	
	@Resource
	private MemberIdentityRepository memberIdentityRepository;

	@Override
	public void createMemberIdentity(MemberIdentity obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getMemberId(),obj.getForeignId());
		this.memberIdentityRepository.save(obj);
	}
	
	@Override
	public void updateMemberIdentity(Long memberId,MemberIdentity_Type oldType,MemberIdentity_Type newType)throws EliteServiceException{
		this.verifyParams(memberId,oldType,newType);
		Search search=new Search();
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("type", oldType.name());
		MemberIdentity identity=this.memberIdentityRepository.searchUnique(search);
		if(identity==null)
			return;
		identity.setType(newType.name());
		identity.setName(newType.getLabel());
		this.memberIdentityRepository.save(identity);
	}
	
	@Override
	public List<MemberIdentity> findMemberIdentityListByMemberId(Long memberId)throws EliteServiceException{
		this.verifyParams(memberId);
		return this.memberIdentityRepository.search(new Search().addFilterEqual("memberId", memberId));
	}

}
