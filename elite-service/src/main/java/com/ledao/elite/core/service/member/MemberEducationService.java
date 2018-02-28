package com.ledao.elite.core.service.member;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.exception.EliteServiceException;;

/**
 * 会员教育接口
 */
public interface MemberEducationService {


	/**
	 * 添加教育经历
	 * 
	 * @param memberEducation
	 * @return memberEducation
	 */
	MemberEducation createMemberEducation(MemberEducation memberEducation) throws EliteServiceException;

	/**
	 * 更新教育经历
	 * 
	 * @param memberEducation
	 * @return memberEducation
	 */
	MemberEducation updateMemberEducation(Long id,MemberEducation memberEducation) throws EliteServiceException;
	
	/**
	 * 删除教育经历
	 * 
	 * @param memberEducation
	 * @return memberEducation
	 */
	 MemberEducation removePhysicalById(Long id) throws EliteServiceException;
	 
	 /**
	  * 按ID查询
	  * 
	  * @param id
	  * @return memberEducation
	  * */
	 MemberEducation findById(Long id)throws EliteServiceException;


	/**
	 * 根据会员Id查询教育培训经历
	 * 
	 * @param memberId
	 * @param type
	 * @return memberEducation
	 */
	List<MemberEducation> findByMemberId(Long memberId,String type) throws EliteServiceException;
}
