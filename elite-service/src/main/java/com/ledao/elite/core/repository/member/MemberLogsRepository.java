package com.ledao.elite.core.repository.member;

import java.util.Date;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberLogs;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员操作日志接口
 */
public interface MemberLogsRepository extends GenericDAO<MemberLogs, Long> {

	/**
	 * 根据memberId，起始时间分页查询
	 * 
	 * @param memberId
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return
	 */
	SearchResult<MemberLogs> findMemberLogsList(Long memberId, Date startTime, Date endTime, Pager pager);
}
