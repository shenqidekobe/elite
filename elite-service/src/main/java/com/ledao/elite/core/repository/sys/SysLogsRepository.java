package com.ledao.elite.core.repository.sys;

import java.util.Date;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysLogs;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统日志接口
 * 
 * @author kobe.liu
 * */
public interface SysLogsRepository extends GenericDAO<SysLogs, Long> {

	/**
	 * 根据操作人 起始时间 分页查询
	 */
	SearchResult<SysLogs> findSysLogsByUserIdAndTime(Long userId,Date startTime,Date endTime,Pager pager);
}
