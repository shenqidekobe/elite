package com.ledao.elite.core.service.member;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 会员身份信息服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface MemberIdentityService {

	
	/**
	 * 给会员增加一种身份
	 * @param obj
	 * */
	void createMemberIdentity(MemberIdentity obj)throws EliteServiceException;
	
	/**
	 * 更新会员身份
	 * 
	 * @param memberId
	 * @param oldType
	 * @param newType
	 * */
	void updateMemberIdentity(Long memberId,MemberIdentity_Type oldType,MemberIdentity_Type newType)throws EliteServiceException;
	
	/**
	 * 根据会员ID查询会员的身份列表
	 * 
	 * @param memberId
	 * @return List<MemberIdentity>
	 * */
	List<MemberIdentity> findMemberIdentityListByMemberId(Long memberId)throws EliteServiceException;
}
