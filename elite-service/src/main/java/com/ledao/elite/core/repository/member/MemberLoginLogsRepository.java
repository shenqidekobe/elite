package com.ledao.elite.core.repository.member;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberLoginLogs;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员登录日志接口
 */

@Repository
public interface MemberLoginLogsRepository extends GenericDAO<MemberLoginLogs, Long> {

	/**
	 * 根据memberId，起始时间，分页查询会员登录日志
	 * 
	 * @param memberId
	 * @param startDate
	 * @param endDate
	 * @param pager
	 * @return
	 */
	SearchResult<MemberLoginLogs> findMemberLoginLogsList(Long memberId, Date startDate, Date endDate, Pager pager);
}
