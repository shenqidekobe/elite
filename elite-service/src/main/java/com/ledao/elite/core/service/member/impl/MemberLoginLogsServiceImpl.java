package com.ledao.elite.core.service.member.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberLoginLogs;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.member.MemberLoginLogsRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberLoginLogsService;
import com.ledao.elite.core.service.member.MemberPassportService;

@Service
public class MemberLoginLogsServiceImpl extends BaseSerivceImpl implements MemberLoginLogsService {

	@Resource
	private MemberLoginLogsRepository memberLoginLogsRepository;
	@Resource
	private MemberPassportService memberPassportService;

	@Override
	public MemberLoginLogs saveMemberLoginLogs(MemberLoginLogs obj) throws EliteServiceException {
		this.verifyParams(obj);
		this.memberLoginLogsRepository.save(obj);
		return obj;
	}

	@Override
	public SearchResult<MemberLoginLogs> findMemberLoginLogsList(String account, Date startTime, Date endTime,
			Pager pager) throws EliteServiceException {
		Long memberId = null;
		if (!(null == account || "".equals(account))) {
			MemberPassport passport = this.memberPassportService.findMemberPassportByAccount(account);
			if (null != passport) {
				memberId = passport.getId();
			} else {
				SearchResult<MemberLoginLogs> result = new SearchResult<MemberLoginLogs>();
				return result;
			}
		}

		return this.memberLoginLogsRepository.findMemberLoginLogsList(memberId, startTime, endTime, pager);
	}

	@Override
	public MemberLoginLogs findMemberLoginLogsById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberLoginLogsRepository.find(id);
	}

}
