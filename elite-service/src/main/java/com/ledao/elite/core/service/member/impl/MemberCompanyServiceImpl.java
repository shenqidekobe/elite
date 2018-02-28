package com.ledao.elite.core.service.member.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberCompanyRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberCompanyService;

@Service
public class MemberCompanyServiceImpl extends BaseSerivceImpl implements MemberCompanyService{
	
	@Resource
	private MemberCompanyRepository memberCompanyRepository;

	@Override
	public MemberCompany createMemberCompany(MemberCompany obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getMemberId());
		//初始创建会员为待审核状态
		obj.setStatus(MemberPassport.Member_Status.waitAduit);
		this.memberCompanyRepository.save(obj);
		return obj;
	}

	@Override
	public MemberCompany updateMemberCompany(Long memberId, MemberCompany obj) throws EliteServiceException {
		MemberCompany pojo=this.findMemberCompanyByMemberId(memberId);
		if(pojo==null)return null;
		if(StringUtils.isNotEmpty(obj.getCompanyIntro())&&obj.getCompanyIntro().length()>4000){
			throw new EliteServiceException(ErrorCodeEnum.ERROR.code,"公司简介不能超过2000个字符");
		}else if(StringUtils.isNotEmpty(obj.getTeamIntro())&&obj.getTeamIntro().length()>4000){
			throw new EliteServiceException(ErrorCodeEnum.ERROR.code,"团队简介不能超过2000个字符");
		}
		BeanUtils.copyProperties(obj, pojo,new String[]{"id","memberId","status","stared","createTime"});
		this.memberCompanyRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberCompany updateMemberCompanyStatus(Long memberId, String status) throws EliteServiceException {
		MemberCompany pojo=this.findMemberCompanyByMemberId(memberId);
		if(pojo==null)return null;
		pojo.setStatus(Member_Status.valueOf(Member_Status.class, status));
		this.memberCompanyRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberCompany updateMemberCompanyAudit(Long memberId, Long auditId, String auditReason, String status)
			throws EliteServiceException {
		MemberCompany pojo=this.findMemberCompanyByMemberId(memberId);
		if(pojo==null)return null;
		pojo.setAuditId(auditId);
		pojo.setAuditReason(auditReason);
		pojo.setAuditTime(new Date());
		pojo.setStatus(Member_Status.valueOf(Member_Status.class, status));
		this.memberCompanyRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberCompany findMemberCompanyByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		return this.memberCompanyRepository.searchUnique(new Search().addFilterEqual("memberId", memberId));
	}

	@Override
	public MemberCompany updateMemberCompanyInfoNoFixed(MemberCompany company) throws EliteServiceException {
		
		this.memberCompanyRepository.save(company);
		return company;
	}

}
