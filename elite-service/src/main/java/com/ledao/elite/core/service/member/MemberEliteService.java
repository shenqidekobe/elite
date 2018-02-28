package com.ledao.elite.core.service.member;

import java.util.Date;
import java.util.Map;

import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 精英服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface MemberEliteService {
	
	/**
	 * 创建精英资料
	 * @param obj
	 * */
	MemberElite createMemberElite(MemberElite obj)throws EliteServiceException;
	
	/**
	 * 根据会员ID修改精英当前状态的资料
	 * @param memberId
	 * @param obj
	 * */
	MemberElite updateMemberEliteCurrentInfo(Long memberId,MemberElite obj)throws EliteServiceException;
	
	/**
	 * 根据会员ID修改精英资料
	 * @param memberId
	 * @param obj
	 * */
	MemberElite updateMemberEliteInfo(Long memberId,MemberElite obj)throws EliteServiceException;
	
	/**
	 * 修改精英状态
	 * @param memberId
	 * @param status
	 * */
	MemberElite updateMemberEliteStatus(Long memberId,String status)throws EliteServiceException;
	
	/**
	 * 修改精英
	 * @param memberId
	 * @param status
	 * */
	MemberElite updateMemberElite(MemberElite obj)throws EliteServiceException;

	
	/**
	 * 审核精英资料
	 * @param memberId
	 * @param auditId
	 * @param auditReason
	 * @param status
	 * */
	MemberElite updateMemberEliteAudit(Long memberId,Long auditId,String auditReason,String status,Boolean ctoSigned)throws EliteServiceException;
	
	/**
	 * 审核精英升级为CTO资料
	 * @param memberId
	 * @param auditId
	 * @param auditReason
	 * @param isCto
	 * */
	MemberElite updateMemberEliteAuditCto(Long memberId,Long auditId,String auditReason,boolean isCto,String status)throws EliteServiceException;
	
	/**
	 * 根据会员ID查询精英资料
	 * @param memberId
	 * */
	MemberElite findMemberEliteByMemberId(Long memberId)throws EliteServiceException;
	/**
	 * 根据Id查询精英资料
	 * @param id
	 * */
	MemberElite findMemberEliteById(Long id)throws EliteServiceException;
	/**
	 * 根据审核通过的会员
	 * @param memberId
	 * */
	Map<String,Integer> findMemberEliteStatusCount(boolean ctoed,String status,Date startTime,Date endTime)throws EliteServiceException;
	
	/**
	 * 查询elite必填项未填选项
	 * @param memberId
	 * @return
	 * @throws EliteServiceException
	 */
	Map<String,String>findMemberEliteNecessaryNull(Long memberId)throws EliteServiceException;

}
