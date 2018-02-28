package com.ledao.elite.core.service.member;

import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 项目方服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface MemberCompanyService {

	/**
	 * 创建项目方资料
	 * 
	 * @param obj
	 */
	MemberCompany createMemberCompany(MemberCompany obj) throws EliteServiceException;

	/**
	 * 根据会员ID修改项目方资料
	 * 
	 * @param memberId
	 * @param obj
	 */
	MemberCompany updateMemberCompany(Long memberId, MemberCompany obj) throws EliteServiceException;

	/**
	 * 修改项目方状态
	 * 
	 * @param memberId
	 * @param status
	 */
	MemberCompany updateMemberCompanyStatus(Long memberId, String status) throws EliteServiceException;

	/**
	 * 审核项目方资料
	 * 
	 * @param memberId
	 * @param auditId
	 * @param auditReason
	 * @param status
	 */
	MemberCompany updateMemberCompanyAudit(Long memberId, Long auditId, String auditReason, String status)
			throws EliteServiceException;

	/**
	 * 根据会员ID查询项目方资料
	 * 
	 * @param memberId
	 */
	MemberCompany findMemberCompanyByMemberId(Long memberId) throws EliteServiceException;
    
	
	/**
	 * 
	 */
	MemberCompany updateMemberCompanyInfoNoFixed(MemberCompany company)throws EliteServiceException;
}
