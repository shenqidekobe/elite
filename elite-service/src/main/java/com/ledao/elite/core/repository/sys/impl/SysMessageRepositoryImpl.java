package com.ledao.elite.core.repository.sys.impl;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysMessage;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysMessageRepository;

@Repository
public class SysMessageRepositoryImpl extends GenericRepositoryImpl<SysMessage, Long> implements SysMessageRepository {

	@Override
	public SearchResult<SysMessage> querySysMessagesByUserId(Long userId, String unread, Pager pager) {

		Search search = new Search(SysMessage.class);
		search.addFilterEqual("userId", userId);
		if (unread != null) {
			if (unread.equals("true")) {
				search.addFilterEqual("isRead", false);
			} else {
				search.addFilterEqual("isRead", true);
			}
		}
		search.addFilterOr(Filter.equal("status", GlobalDefinedConstant.System_Status.normal.name()),
				Filter.isNull("status"));
		if (pager != null) {
			search.setFirstResult(pager.getStartIndex());
			search.setMaxResults(pager.getPageSize());
		}
		search.addSort("createTime", true);

		return this.searchAndCount(search);
	}

}
