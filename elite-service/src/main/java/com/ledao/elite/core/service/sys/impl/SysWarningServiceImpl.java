package com.ledao.elite.core.service.sys.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysWarning;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.sys.SysWarningRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysWarningService;

@Service
public class SysWarningServiceImpl extends BaseSerivceImpl implements SysWarningService {

	@Resource
	private SysWarningRepository sysWarningRepository;

	@Override
	public SysWarning createSysWarning(SysWarning obj) throws EliteServiceException {
		this.verifyParams(obj);
		this.sysWarningRepository.save(obj);
		return obj;
	}


	@Override
	public SearchResult<SysWarning> findSysWarning(Long createId, Pager pager) {
		if (pager == null)
			pager = new Pager();
		Search search = new Search(SysWarning.class);
		search.addFilter(new Filter("createId", createId));
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.sysWarningRepository.searchAndCount(search);
	}

}
