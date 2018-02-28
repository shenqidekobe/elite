package com.ledao.elite.core.service.sys;

import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysRestLogs;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统rest接口
 *
 * @author zhiyu cao
 **/

public interface SysRestLogsService {

	/**
	 * 创建系统rest消息
	 *
	 **/
	SysRestLogs createSysRestLogs(SysRestLogs obj) throws EliteServiceException;

	/**
	 * 按条件分页接口操作日志
	 * 
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return SearchResult<SysRestLogs>
	 */
	SearchResult<SysRestLogs> findSysRestLogsList(String userName, Date startTime, Date endTime, Pager pager)
			throws EliteServiceException;

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return SysRestLogs
	 * @throws EliteServiceException
	 */
	SysRestLogs findSysRestLogById(Long id) throws EliteServiceException;

}
