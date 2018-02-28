package com.ledao.elite.core.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysAudit;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.sys.SysAuditRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysAuditService;

@Service
public class SysAuditServiceImpl extends BaseSerivceImpl implements SysAuditService {

	@Resource
	private SysAuditRepository sysAuditRepository;

	@Override
	public SysAudit createSysAudit(SysAudit obj) throws EliteServiceException {
		this.sysAuditRepository.save(obj);
		return obj;
	}

	@Override
	public SysAudit removePhysicalById(Long Id) throws EliteServiceException {

		this.verifyParams(Id);
		SysAudit obj = this.sysAuditRepository.find(Id);
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return this.sysAuditRepository.remove(obj) ? obj : null;
	}

	@Override
	public SearchResult<SysAudit> findSysAuditByPhone(Long userId, Long memberId) throws EliteServiceException {

		Search search = new Search();
		search.addFilter(new Filter("userId", userId));
		search.addFilter(new Filter("memberId", memberId));
		return this.sysAuditRepository.searchAndCount(search);
	}

}
