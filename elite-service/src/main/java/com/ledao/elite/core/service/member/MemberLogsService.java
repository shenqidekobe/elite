package com.ledao.elite.core.service.member;

import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberLogs;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员操作日志服务接口
 */
public interface MemberLogsService {

	/**
	 * 保存会员操作日志
	 * 
	 * @param obj
	 * @return MemberLogs
	 */
	MemberLogs saveMemberLogs(MemberLogs obj) throws EliteServiceException;

	/**
	 * 按条件分页查询会员操作日志
	 * 
	 * @param account
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return SearchResult<MemberLogs>
	 */
	SearchResult<MemberLogs> findMemberLogsList(String account, Date startTime, Date endTime, Pager pager)
			throws EliteServiceException;

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 * @throws EliteServiceException
	 */
	MemberLogs findMemberLogs(Long id) throws EliteServiceException;
}
