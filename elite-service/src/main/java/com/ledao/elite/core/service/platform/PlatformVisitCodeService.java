package com.ledao.elite.core.service.platform;

import com.ledao.elite.core.domain.platform.PlatformVisitCode;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 平台访问码服务接口
 * */
public interface PlatformVisitCodeService {
	
	
	/**
	 * 根据邀请姓名、电话、邀请码查询是否存在记录
	 * 
	 * @param visitName
	 * @param visitPhone
	 * @param visitCode
	 * @return platformVisitCode
	 * */
	PlatformVisitCode findPlatformVisitCode(String visitName,String visitPhone,String visitCode)throws EliteServiceException;

	/**
	 * 更新邀请记录
	 * 
	 * @param obj
	 * */
	PlatformVisitCode updatePlatformVisitCode(PlatformVisitCode obj)throws EliteServiceException;
	
	/**
	 * 更新邀请记录对应的会员
	 * 
	 * @param visitCode
	 * @param memberId
	 * 
	 * */
	void updatePlatformVisitMember(String visitCode,Long memberId)throws EliteServiceException;
}
