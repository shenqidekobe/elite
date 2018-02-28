package com.ledao.elite.core.service.member.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberLogs;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.member.MemberLogsRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberLogsService;
import com.ledao.elite.core.service.member.MemberPassportService;

@Service
public class MemberLogsServiceImpl extends BaseSerivceImpl implements MemberLogsService {

	@Resource
	private MemberLogsRepository memberLogsRepository;

	@Resource
	private MemberPassportService memberPassportService;

	@Override
	public MemberLogs saveMemberLogs(MemberLogs obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getContent());
		this.memberLogsRepository.save(obj);
		return obj;
	}

	@Override
	public SearchResult<MemberLogs> findMemberLogsList(String account, Date startTime, Date endTime, Pager pager)
			throws EliteServiceException {
		Long memberId = null;
		if (!(null == account || "".equals(account))) {
			MemberPassport passport = this.memberPassportService.findMemberPassportByAccount(account);
			if (null != passport) {
				memberId = passport.getId();
			} else {
				SearchResult<MemberLogs> result = new SearchResult<MemberLogs>();
				return result;
			}
		}

		return this.memberLogsRepository.findMemberLogsList(memberId, startTime, endTime, pager);
	}

	@Override
	public MemberLogs findMemberLogs(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberLogsRepository.find(id);
	}

}
