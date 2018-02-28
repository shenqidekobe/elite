package com.ledao.elite.core.repository.sys.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysRestLogs;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysRestLogsRepository;

@Repository
public class SysResLogsRepositoryImpl extends GenericRepositoryImpl<SysRestLogs, Long>
		implements SysRestLogsRepository {

	@Override
	public SearchResult<SysRestLogs> findSysRestLogsByUserIdAndTime(Long userId, Date startTime, Date endTime,
			Pager pager) {

		if (pager == null)
			pager = new Pager();
		Search search = new Search(SysRestLogs.class);
		search.addFilterEqual("userId", userId);
		search.addFilterGreaterOrEqual("createTime", startTime);
		search.addFilterLessOrEqual("createTime", endTime);
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
