package com.ledao.elite.core.repository.sys;

import java.util.Date;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysRestLogs;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统rest接口日志接口
 * 
 * @author kobe.liu
 */
public interface SysRestLogsRepository extends GenericDAO<SysRestLogs, Long> {

	/**
	 * 根据操作人Id 起始时间 分页查询
	 */
	SearchResult<SysRestLogs> findSysRestLogsByUserIdAndTime(Long userId, Date startTime, Date endTime, Pager pager);
}
