package com.ledao.elite.core.service.sys;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysAudit;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 系统审核会员记录接口
 *
 * @author zhiyu cao
 **/

public interface SysAuditService {

	/**
	 * 短信发送对象
	 *
	 **/
	SysAudit createSysAudit(SysAudit obj) throws EliteServiceException;

	/**
	 * 按Id物理删除
	 * 
	 */
	SysAudit removePhysicalById(Long Id) throws EliteServiceException;

	/**
	 * 根据userId或者memberId查询
	 * 
	 */
	SearchResult<SysAudit> findSysAuditByPhone(Long userId, Long memberId) throws EliteServiceException;

}
