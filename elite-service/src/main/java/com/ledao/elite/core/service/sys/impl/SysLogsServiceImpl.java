package com.ledao.elite.core.service.sys.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysLogs;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.sys.SysLogsRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysLogsService;
import com.ledao.elite.core.service.sys.SysUserService;

@Service
public class SysLogsServiceImpl extends BaseSerivceImpl implements SysLogsService {

	@Resource
	private SysLogsRepository sysLogsRepository;
	@Resource
	private SysUserService sysUserService;

	@Override
	public SysLogs saveSysLogs(SysLogs obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getContent());
		this.sysLogsRepository.save(obj);
		return obj;

	}

	@Override
	public SearchResult<SysLogs> findSysLogsList(String userName, Date startTime, Date endTime, Pager pager)
			throws EliteServiceException {
		Long userId = null;
		if (StringUtils.isNotEmpty(userName)) {
			SysUser user = this.sysUserService.findSysUserByLoginNameAndStatus(userName, null);
			userId =user==null?null: user.getId();
		}
		return this.sysLogsRepository.findSysLogsByUserIdAndTime(userId, startTime, endTime, pager);
	}

	@Override
	public SysLogs findSysLogById(Long id) throws EliteServiceException {
		return this.sysLogsRepository.find(id);
	}

}
