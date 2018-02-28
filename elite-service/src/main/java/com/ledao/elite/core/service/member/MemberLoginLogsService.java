package com.ledao.elite.core.service.member;

import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberLoginLogs;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员登录日志服务接口
 */
public interface MemberLoginLogsService {

	/**
	 * 保存会员登录日志
	 * 
	 * @param obj
	 * @return MemberLoginLogs
	 */

	MemberLoginLogs saveMemberLoginLogs(MemberLoginLogs obj) throws EliteServiceException;

	/**
	 * 按条件分页查询会员登录日志
	 * 
	 * @param memberId
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return SearchResult<MemberLoginLogs>
	 */
	SearchResult<MemberLoginLogs> findMemberLoginLogsList(String account, Date startTime, Date endTime, Pager pager)
			throws EliteServiceException;

	/**
	 * 根据Id查询
	 * 
	 * @param id
	 * @return MemberLoginLogs
	 * @throws EliteServiceException
	 */
	MemberLoginLogs findMemberLoginLogsById(Long id) throws EliteServiceException;

}
