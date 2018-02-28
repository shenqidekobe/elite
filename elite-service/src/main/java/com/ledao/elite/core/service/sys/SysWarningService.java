package com.ledao.elite.core.service.sys;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysWarning;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统预警接口
 *
 **/

public interface SysWarningService {

	/**
	 * 创建
	 *
	 **/
	SysWarning createSysWarning(SysWarning obj) throws EliteServiceException;

	/**
	 * 分页查询
	 * 
	 */
	SearchResult<SysWarning> findSysWarning(Long createId, Pager pager);
}
