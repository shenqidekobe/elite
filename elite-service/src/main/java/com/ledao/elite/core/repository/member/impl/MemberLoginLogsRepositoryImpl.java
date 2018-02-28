package com.ledao.elite.core.repository.member.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberLoginLogs;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberLoginLogsRepository;

@Repository
public class MemberLoginLogsRepositoryImpl extends GenericRepositoryImpl<MemberLoginLogs, Long>
		implements MemberLoginLogsRepository {

	@Override
	public SearchResult<MemberLoginLogs> findMemberLoginLogsList(Long memberId, Date startDate, Date endDate,
			Pager pager) {
		if (pager == null)
			pager = new Pager();
		Search search = new Search(MemberLoginLogs.class);
		search.addFilterEqual("memberId", memberId);
		search.addFilterGreaterOrEqual("createTime", startDate);
		search.addFilterLessOrEqual("createTime", endDate);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.searchAndCount(search);
	}

}
