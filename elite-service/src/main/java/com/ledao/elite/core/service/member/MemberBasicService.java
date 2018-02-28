
package com.ledao.elite.core.service.member;

import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 会员基本信息服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface MemberBasicService {
	
	
	/**
	 * 同步更新会员基本信息
	 * 
	 * @param memberBasic
	 * @return memberBasic
	 * */
	MemberBasic syncUpdateMemberBasic(MemberBasic obj)throws EliteServiceException;

	/**
	 * 创建会员基本信息
	 * 
	 * @param memberBasic
	 * @return memberBasic
	 * */
	MemberBasic createMemberBasic(MemberBasic obj)throws EliteServiceException;
	
	/**
	 * 修改会员基本信息
	 * 
	 * @param memberId
	 * @param MemberBasic
	 * @return MemberBasic
	 * */
	MemberBasic updateMemberBasic(Long memberId,MemberBasic basic)throws EliteServiceException;
	
	/**
	 * 根据会员ID查询会员基本信息
	 * 
	 * @param memberId
	 * */
	MemberBasic findMemberBasicByMemberId(Long memberId)throws EliteServiceException;
     
	/**
	 * 创建修改基本信息
	 * @param memberId
	 * @param basic
	 * @return
	 * @throws EliteServiceException
	 */
    MemberBasic saveOrUpdateBasic(Long memberId,MemberBasic basic)throws EliteServiceException;

    /**
     * 修改合作伙伴基本信息
     */
    MemberBasic updatePartnerBasic(Long memberId,MemberBasic basic,String type)throws EliteServiceException;

    /**
     * 修改基本信息，修改属性不固定
     * @param basic
     * @throws EliteServiceException
     */
    void updateBasicInfoNoFixed(MemberBasic basic)throws EliteServiceException;
    
    /**
     * 检查邮箱是否存在重复
     * 
     * @param email
     * @return true/false
     * */
    boolean findMemberBasicByEmail(String email)throws EliteServiceException;
}
