package com.ledao.elite.core.service.partner.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPartnerElite.MemberPartnerElite_Search_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_SearchType;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Status;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberPartnerCompanyRepository;
import com.ledao.elite.core.repository.member.MemberPartnerEliteRepository;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.repository.partner.PartnerRecordRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerRecordService;

@Service("partnerRecordService")
public class PartnerRecordServiceImpl extends BaseSerivceImpl implements PartnerRecordService {

	@Resource
	private PartnerRecordRepository PartnerRecordRepository;
	@Resource
	private MemberPassportService memberPartnerPassportService;
	@Resource
	private MemberPartnerEliteRepository MemberPartnerEliteRepository;
	@Resource
	private MemberPartnerCompanyRepository MemberPartnerCompanyRepository;
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private MemberPassportRepository memberPassportRepository;

	@Override
	public PartnerRecord createPartnerRecord(PartnerRecord obj) throws EliteServiceException {
		Search search=new Search();
		search.addFilterEqual("memberPhone", obj.getMemberPhone());
		search.addFilterEqual("partnerType", obj.getPartnerType());
		List<PartnerRecord> list=this.PartnerRecordRepository.search(search);
		//2016-12-21更新为，一个帐号可以在多种角色备案
		//PartnerRecord partnerRecord = this.findPartnerRecordByPhone(obj.getMemberPhone());
		if (!list.isEmpty()||list.size()>0) {
			throw new EliteServiceException("手机号码已备案过", ErrorCodeEnum.ACCOUNT_EXIST.code);
		} else {
			List<MemberPassport> member= this.memberPassportRepository.queryMemberIdentityList(obj.getMemberPhone());
			if(!member.isEmpty()||member.size()>0){
				throw new EliteServiceException("手机号已注册过", ErrorCodeEnum.ACCOUNT_EXIST.code);
			} else {
				this.PartnerRecordRepository.save(obj);
			}
		}
		return obj;

	}

	@Override
	public PartnerRecord findPartnerRecordByAccountAndStatus(String memberPhone,PartnerRecord_Type partnerType) throws EliteServiceException {
		this.verifyParams(memberPhone,partnerType);
		Search search = new Search();
		search.addFilterEqual("memberPhone", memberPhone);
		search.addFilterNotEqual("status", PartnerRecord_Status.audit_nopass);
		search.addFilterEqual("partnerType", partnerType);
		return this.PartnerRecordRepository.searchUnique(search);
	}

	@Override
	public PartnerRecord findPartnerRecordByPhoneAndPartnerId(Long parentId, String memberPhone)
			throws EliteServiceException {
		this.verifyParams(memberPhone);
		Search search = new Search();
		search.addFilterEqual("memberPhone", memberPhone);
		search.addFilterNotEqual("parentId", parentId);
		return this.PartnerRecordRepository.searchUnique(search);
	}

	@Override
	public PartnerRecord findPartnerRecordByMemberIdAndStatus(Long memberId, PartnerRecord_Status status)
			throws EliteServiceException {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("status", status);
		return this.PartnerRecordRepository.searchUnique(search);
	}

	@Override
	public PartnerRecord findPartnerRecordByMemberId(Long memberId) throws EliteServiceException {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterEqual("memberId", memberId);
		return this.PartnerRecordRepository.searchUnique(search);
	}

	@Override
	public PartnerRecord updatePartnerRecordInfoNoFixed(PartnerRecord record) throws EliteServiceException {
		this.verifyParams(record);
		this.PartnerRecordRepository.save(record);
		return record;
	}

	@Override
	public SearchResult<PartnerRecord> findPartnerRecords(Long partnerId, String partnerType, String keyword,
			String status, Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<PartnerRecord> sr = new SearchResult<PartnerRecord>();
		List<PartnerRecord> list = this.PartnerRecordRepository.findPartnerRecordListByKeyword(partnerId, partnerType,
				status, keyword, startTime, endTime, pager);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMemberId() != null) {
				MemberPassport member = this.memberPartnerPassportService
						.findMemberDetailInfoById(list.get(i).getMemberId());
				list.get(i).setMemberPassport(member);
			}
		}
		Integer total = this.PartnerRecordRepository.findPartnerRecordListByKeywordCount(partnerId, partnerType, status,
				keyword, startTime, endTime);
		sr.setResult(list);
		sr.setTotalCount(total);
		return sr;
	}

	/**
	 * @param partnerId
	 * @param partnerType
	 * @param keyword
	 * @param searchType
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	@Override
	public SearchResult<PartnerRecord> findPartnerRecordsBySearchType(Long partnerId, Long memberId, String partnerType,
			String keyword, String searchType, Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		// TODO Auto-generated method stub
		SearchResult<PartnerRecord> sr = new SearchResult<PartnerRecord>();
		// 全部 未注册情况
		List<PartnerRecord> list;
		Integer total;
//		if (endTime != null) {
//			endTime = DateTimeUtils.addOrSub(endTime, 1);
//		}
		if (searchType == null || searchType.equals(PartnerRecord_SearchType.all.name())
				|| searchType.equals(PartnerRecord_SearchType.no_register.name())) {
			if (PartnerRecord_SearchType.all.name().equals(searchType)) {
				searchType = null;
			}
			list = this.PartnerRecordRepository.findPartnerRecordListByKeyword(partnerId, partnerType, searchType,
					keyword, startTime, endTime, pager);
			total = this.PartnerRecordRepository.findPartnerRecordListByKeywordCount(partnerId, partnerType, searchType,
					keyword, startTime, endTime);

		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			if (MemberPartnerElite_Search_Type.no_task_over_14days.name().equals(searchType)) {
				cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - 14);
			} else {
				cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - 7);
			}
			Date beforeServenDayTime = cal.getTime();
			// 项目渠道方
			if (MemberIdentity_Type.partnerCompany.name().equals(partnerType)) {
				List<MemberPartnerCompany> partnerCompanylist = this.MemberPartnerCompanyRepository
						.findMemberPartnerCompanyListBySearchType(partnerId, keyword, searchType, startTime, endTime,
								beforeServenDayTime, pager);
				list = new ArrayList<PartnerRecord>();
				for (int i = 0; i < partnerCompanylist.size(); i++) {
					MemberPartnerCompany Company = partnerCompanylist.get(i);
					PartnerRecord record = this.findPartnerRecordByMemberId(Company.getMemberId());
					if (record != null) {
						list.add(record);
					}
				}
				total = this.MemberPartnerCompanyRepository.findMemberPartnerCompanyListBySearchTypeCount(partnerId,
						keyword, searchType, startTime, endTime, beforeServenDayTime);

			}
			// 人才渠道方
			else {
				List<MemberPartnerElite> partnerElitelist = this.MemberPartnerEliteRepository
						.findMemberPartnerEliteListBySearchType(partnerId, keyword, searchType, startTime, endTime,
								beforeServenDayTime, pager);
				list = new ArrayList<PartnerRecord>();
				for (int i = 0; i < partnerElitelist.size(); i++) {
					MemberPartnerElite elite = partnerElitelist.get(i);
					PartnerRecord record = this.findPartnerRecordByMemberId(elite.getMemberId());
					if (record != null) {
						list.add(record);
					}
				}
				total = this.MemberPartnerEliteRepository.findMemberPartnerEliteListBySearchTypeCount(partnerId,
						keyword, searchType, startTime, endTime, beforeServenDayTime);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			Integer partnerPutCount = 0;
			Integer partnerEnterCount = 0;
			PartnerRecord record = list.get(i);
			if (record != null && record.getMemberId() != null) {
				MemberPassport member = this.memberPartnerPassportService
						.findMemberDetailInfoById(record.getMemberId());
				record.setMemberPassport(member);
				if (MemberIdentity_Type.partnerElite.name().equals(partnerType) && member.getPartnerElite() != null) {
					partnerEnterCount = this.findPartnerRecordByEnterCount(member.getPartnerElite().getId(),
							partnerType, PartnerRecord_Status.audit_pass.name());
					partnerPutCount = this.findPartnerRecordByEnterCount(member.getPartnerElite().getId(), partnerType,
							null);
					record.setPutCount(member.getPartnerElite().getPutCount());
					record.setEnterCount(member.getPartnerElite().getEnterCount());
				} else if (MemberIdentity_Type.partnerCompany.name().equals(partnerType)
						&& member.getPartnerCompany() != null) {
					partnerEnterCount = this.findPartnerRecordByEnterCount(member.getPartnerCompany().getId(),
							partnerType, PartnerRecord_Status.audit_pass.name());
					partnerPutCount = this.findPartnerRecordByEnterCount(member.getPartnerCompany().getId(),
							partnerType, null);
					record.setPutCount(member.getPartnerCompany().getPutCount());
					record.setEnterCount(member.getPartnerCompany().getEnterCount());
				}
				// 渠道收益
				BigDecimal income = this.memberIncomeService.findMemberIncomeSumByMemberId(member.getId(), null, null,
						null);
				record.setChannelIncome(income);

				// 合作伙伴收益
				BigDecimal parentIncome = this.memberIncomeService
						.findMemberIncomeSumByMemberIdAndSourceMemberId(memberId, member.getId(), null, null, null);
				record.setParentIncome(parentIncome);
				record.setPartnerEnterCount(partnerEnterCount);
				record.setPartnerPutCount(partnerPutCount);

				if (record.getEnterCount() == null) {
					record.setEnterCount(0);
				}
				if (record.getPutCount() == null) {
					record.setPutCount(0);
				}
				list.set(i, record);
			}

		}
		sr.setResult(list);
		sr.setTotalCount(total);
		return sr;
	}

	@Override
	public PartnerRecord findPartnerRecordByPhone(String phone) throws EliteServiceException {
		this.verifyParams(phone);
		return this.PartnerRecordRepository.searchUnique(new Search().addFilterEqual("memberPhone", phone));
	}

	@Override
	public Integer findPartnerRecordByEnterCount(Long partnerId, String partnerType, String status)
			throws EliteServiceException {
		return this.PartnerRecordRepository.findPartnerRecordListByKeywordCount(partnerId, partnerType, status, null,
				null, null);

	}

	@Override
	public void updatePartnerRecordStatusBySql(String status, Long id) throws EliteServiceException {
		this.PartnerRecordRepository.updateParnterRecordStatusBySql(status, id);
	}

}
