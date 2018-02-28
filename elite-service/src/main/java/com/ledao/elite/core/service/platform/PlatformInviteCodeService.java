package com.ledao.elite.core.service.platform;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 平台邀请码服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface PlatformInviteCodeService {
	
	
	/**
	 * 创建一条新的邀请码
	 * 
	 * @param platformInviteCode
	 * @return platformInviteCode
	 * */
	PlatformInviteCode createPlatformInviteCode(PlatformInviteCode obj)throws EliteServiceException;
	
	/**
	 * 后台为精英生成多条邀请码
	 * @param ids
	 * @throws EliteServiceException
	 */
	void createPlatformInviteCodesToElite(long[]ids)throws EliteServiceException;
	
	/**
	 * 更新邀请码
	 * 
	 * @param platformInviteCode
	 * */
	void updatePlatformInviteCode(PlatformInviteCode obj)throws EliteServiceException;
	
	/**
	 * 按ID查询邀请码记录
	 * 
	 * @param id
	 * @return platformInviteCode
	 * */
	PlatformInviteCode findPlatformInviteCodeById(Long id)throws EliteServiceException;
	
	/**
	 * 按code查询邀请码记录
	 * 
	 * @param code
	 * @return platformInviteCode
	 * */
	PlatformInviteCode findPlatformInviteCodeByCode(String code)throws EliteServiceException;
	
	/**
	 * 获取用户的邀请码，如果没有则创建一条
	 * 
	 * @param userId
	 * @param type
	 * @return platformInviteCode
	 * */
	PlatformInviteCode findPlatformInviteCodeByUserId(Long userId,InviteCode_Type type)throws EliteServiceException;
	
	/**
	 * 获取用户的最新邀请码
	 * 
	 * @param userId
	 * @return platformInviteCode
	 * */
	PlatformInviteCode findNewPlatformInviteCodeByUserId(Long userId)throws EliteServiceException;

	/**
	 * 组合查询邀请码
	 * @param keyword(邀请码)
	 * @param type
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PlatformInviteCode> findPaltformInviteCodes(String keyword,String type,Pager pager )throws EliteServiceException;
	/**
	 * 查询全部邀请码
	 */
	 List<PlatformInviteCode> findAllPaltformInviteCodes()throws EliteServiceException;
}
