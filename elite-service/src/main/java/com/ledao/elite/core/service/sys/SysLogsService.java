package com.ledao.elite.core.service.sys;

import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysLogs;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统操作日志服务接口
 * */
public interface SysLogsService {
	
	/**
	 * 保存系统操作日志
	 * @param obj
	 * @return SysLogs
	 * */
	SysLogs saveSysLogs(SysLogs obj)throws EliteServiceException;
	
	/**
	 * 按条件分页查询系统操作日志
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return SearchResult<SysLogs>
	 * */
	SearchResult<SysLogs> findSysLogsList(String userName,Date startTime,Date endTime,Pager pager)throws EliteServiceException;
	
    /**
     * 根据id查询
     * @param id
     * @return SysLogs
     * @throws EliteServiceException
     */
	SysLogs findSysLogById(Long id)throws EliteServiceException;
}
