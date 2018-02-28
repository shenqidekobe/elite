package com.ledao.elite.core.service.sys.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysPending;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.sys.SysPendingRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysPendingService;

/**
 *
 *
 * @author zhiyu cao
 **/

@Service
public class SysPendingServiceImple extends BaseSerivceImpl implements SysPendingService {

	@Resource
	private SysPendingRepository sysPendingRepository;

	@Override
	public SysPending createSysPending(SysPending obj) throws EliteServiceException {
		// this.verifyParams(obj);
		this.sysPendingRepository.save(obj);
		return obj;
	}

	@Override
	public SysPending removePhysicalById(Long Id) throws EliteServiceException {

		this.verifyParams(Id);
		SysPending obj = this.sysPendingRepository.find(Id);
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return this.sysPendingRepository.remove(obj) ? obj : null;
	}

	@Override
	public SearchResult<SysPending> findSysPending(Long userId, Pager pager) {

		if (pager == null)
			pager = new Pager();
		Search search = new Search(SysPending.class);
		search.addFilter(new Filter("userId", userId));
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.sysPendingRepository.searchAndCount(search);
	}

}
