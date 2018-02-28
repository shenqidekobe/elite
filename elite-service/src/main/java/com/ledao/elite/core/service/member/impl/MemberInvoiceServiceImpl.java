package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberInvoice;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.member.MemberInvoiceRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberInvoiceService;

/**
 *
 *
 * @author zhiyu cao
 **/
@Service
public class MemberInvoiceServiceImpl extends BaseSerivceImpl implements MemberInvoiceService {

	@Resource
	private MemberInvoiceRepository MemberInvoiceRepository;

	@Override
	public void createMemberInvoice(MemberInvoice obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getMemberId(), obj.getProjectId(), obj.getStageId(), obj.getAmount(),
				obj.getInvoiceRise());
		this.MemberInvoiceRepository.save(obj);
	}

	@Override
	public List<MemberInvoice> findMemberInvoiceListByProjectId(Long projectId) throws EliteServiceException {
		this.verifyParams(projectId);
		return this.MemberInvoiceRepository.search(new Search().addFilter(new Filter("projectId", projectId)));
	}

	@Override
	public MemberInvoice findMemberInvoiceByStageId(Long stageId) throws EliteServiceException {
		this.verifyParams(stageId);
		return this.MemberInvoiceRepository.searchUnique(new Search().addFilter(new Filter("stageId", stageId)));
	}

}
