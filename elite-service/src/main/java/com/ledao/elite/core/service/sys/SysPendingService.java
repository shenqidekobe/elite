package com.ledao.elite.core.service.sys;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysPending;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统人员的待处理任务接口
 *
 * @author zhiyu cao
 **/

public interface SysPendingService {

	/**
	 * 创建
	 *
	 **/
	SysPending createSysPending(SysPending obj) throws EliteServiceException;

	/**
	 * 物理删除
	 *
	 **/

	SysPending removePhysicalById(Long Id) throws EliteServiceException;

	/**
	 * 分页查询
	 * 
	 */
	SearchResult<SysPending> findSysPending(Long userId, Pager pager);
}
