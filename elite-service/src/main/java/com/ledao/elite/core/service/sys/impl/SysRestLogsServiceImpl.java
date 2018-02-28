package com.ledao.elite.core.service.sys.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysRestLogs;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.sys.SysRestLogsRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysRestLogsService;
import com.ledao.elite.core.service.sys.SysUserService;

@Service
public class SysRestLogsServiceImpl extends BaseSerivceImpl implements SysRestLogsService {

	@Resource
	private SysRestLogsRepository sysRestLogsRepository;
	@Resource
	private SysUserService sysUserService;

	@Override
	public SysRestLogs createSysRestLogs(SysRestLogs obj) throws EliteServiceException {

		this.sysRestLogsRepository.save(obj);
		return obj;
	}

	@Override
	public SearchResult<SysRestLogs> findSysRestLogsList(String userName, Date startTime, Date endTime, Pager pager)
			throws EliteServiceException {
		Long userId = null;
		if (!(null == userName || "".equals(userName))) {
			SysUser user = this.sysUserService.findSysUserByLoginNameAndStatus(userName, null);
			if (null != user) {
				userId = user.getId();
			} else {
				return new SearchResult<SysRestLogs>();
			}
		}
		return this.sysRestLogsRepository.findSysRestLogsByUserIdAndTime(userId, startTime, endTime, pager);
	}

	@Override
	public SysRestLogs findSysRestLogById(Long id) throws EliteServiceException {

		return this.sysRestLogsRepository.find(id);
	}

}
