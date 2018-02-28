package com.ledao.elite.core.repository.sys.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysLogs;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysLogsRepository;

@Repository
public class SysLogsRepositoryImpl extends GenericRepositoryImpl<SysLogs, Long> implements SysLogsRepository {

	@Override
	public SearchResult<SysLogs> findSysLogsByUserIdAndTime(Long userId, Date startTime, Date endTime, Pager pager) {
		if (pager == null)
			pager = new Pager();
		Search search = new Search(SysLogs.class);
		search.addFilterEqual("userId", userId);
		search.addFilterGreaterOrEqual("createTime", startTime);
		search.addFilterLessOrEqual("createTime", endTime);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime",true);
		}
		return this.searchAndCount(search);
	}

}
