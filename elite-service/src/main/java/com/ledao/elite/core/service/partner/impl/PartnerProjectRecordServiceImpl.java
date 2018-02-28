package com.ledao.elite.core.service.partner.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.partner.PartnerProjectRecordRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.partner.PartnerProjectRecordService;
import com.ledao.elite.core.service.project.ProjectService;

@Service("partnerProjectRecordService")
public class PartnerProjectRecordServiceImpl extends BaseSerivceImpl implements PartnerProjectRecordService {

	@Resource
	private PartnerProjectRecordRepository partnerProjectRecordRepository;
	@Resource
	private ProjectService projectService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private MemberIncomeService memberIncomeService;

	@Override
	public PartnerProjectRecord createPartnerProjectRecord(Long partnerId, Long partnerProjectId, Long projectId)
			throws EliteServiceException {
		this.verifyParams(projectId);
		PartnerProjectRecord record = findPartnerProjectRecordByProjectId(projectId);
		if (record != null)
			throw new EliteServiceException("项目ID" + projectId + "已经入驻过，不能再次入驻", ErrorCodeEnum.OBJECT_EXIST.code);
		PartnerProjectRecord obj = new PartnerProjectRecord();
		obj.setPartnerId(partnerId);
		if (partnerProjectId != null) {
			PartnerProjectRecord partnerRecord = findPartnerProjectRecordByPartnerProjectId(partnerProjectId);
			if (partnerRecord == null) {
				obj.setPartnerProjectId(partnerProjectId);
			}
		}
		obj.setProjectId(projectId);
		this.partnerProjectRecordRepository.save(obj);
		return obj;
	}

	@Override
	public void updatePartnerProjectRecordAsIncome(Long projectId, BigDecimal income) throws EliteServiceException {
		this.verifyParams(projectId, income);
		PartnerProjectRecord record = this.findPartnerProjectRecordByProjectId(projectId);
		record.setIncome(record.getIncome().add(income));
		this.partnerProjectRecordRepository.save(record);
	}

	public PartnerProjectRecord findPartnerProjectRecordByProjectId(Long projectId) throws EliteServiceException {
		this.verifyParams(projectId);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		List<PartnerProjectRecord> list=this.partnerProjectRecordRepository.search(search);
		return list.isEmpty()?null:list.get(0);
	}

	@Override
	public SearchResult<PartnerProjectRecord> findPartnerProjectRecordByPartnerId(Long partnerId, String keyword,
			Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<PartnerProjectRecord> sr = new SearchResult<PartnerProjectRecord>();
		List<PartnerProjectRecord> partnerlist = this.partnerProjectRecordRepository
				.findPartnerProjectRecordByPartnerId(partnerId, keyword, startTime, endTime, pager);
		for (int i = 0; i < partnerlist.size(); i++) {
			if (partnerlist.get(i).getProjectId() != null) {
				Project project = this.projectService.findProjectById(partnerlist.get(i).getProjectId());
				partnerlist.get(i).setProject(project);
				if (partnerlist.get(i).getPartner().getMemberId() != null) {
					BigDecimal partnerIncome = this.memberIncomeService.findMemberIncomeByMemeberIdAndProjectId(
							partnerlist.get(i).getPartner().getMemberId(), partnerlist.get(i).getProjectId());
					partnerlist.get(i).setIncome(partnerIncome);
				}
			}
		}
		Integer totalCount = this.partnerProjectRecordRepository.findPartnerProjectRecordCountByPartnerId(partnerId,
				keyword, startTime, endTime);
		sr.setTotalCount(totalCount);
		sr.setResult(partnerlist);
		return sr;
	}

	@Override
	public PartnerProjectRecord findPartnerProjectRecordByPartnerProjectId(Long partnerProjectId)
			throws EliteServiceException {
		this.verifyParams(partnerProjectId);
		Search search = new Search();
		search.addFilterEqual("partnerProjectId", partnerProjectId);
		return this.partnerProjectRecordRepository.searchUnique(search);
	}

	@Override
	public Integer findPartnerProjectRecordCountByPartnerId(Long partnerId, String keyword)
			throws EliteServiceException {
		return this.partnerProjectRecordRepository.findPartnerProjectRecordCountByPartnerId(partnerId, keyword, null,
				null);
	}

}
