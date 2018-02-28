package com.ledao.elite.core.service.member.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.member.MemberIncomeRepository;
import com.ledao.elite.core.repository.project.ProjectRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberIncomeService;

@Service("memberIncomeService")
public class MemberIncomeServiceImpl extends BaseSerivceImpl implements MemberIncomeService {

	@Resource
	private MemberIncomeRepository memberIncomeRepository;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private ProjectRepository projectRepository;

	@Override
	public void createMemberIncome(MemberIncome income) throws EliteServiceException {
		this.verifyParams(income, income.getMemberId(), income.getAmount(), income.getIncomeType());
		this.memberIncomeRepository.save(income);
	}

	@Override
	public BigDecimal findMemberIncomeSumByMemberId(Long memberId, Date startTime, Date endTime, String incomeType)
			throws EliteServiceException {
		return this.memberIncomeRepository.findMemberIncomeSumByMemberId(memberId, incomeType, startTime, endTime);

	}


	@Override
	public BigDecimal findMemberIncomeSumByMemberIdAndParnterId(Long memberId, Long partnerId, Date startTime,
			Date endTime) throws EliteServiceException {
		return this.memberIncomeRepository.findMemberIncomeSumByMemberIdAndPartnerId(memberId, partnerId, startTime, endTime);
	}
	

	@Override
	public SearchResult<MemberIncome> findMemberIncomesByPartnerElite(String keyword, String searchType, Long memberId,
			Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<MemberIncome> sr = new SearchResult<>();
		List<MemberIncome> memberList = this.memberIncomeRepository.findMemberIncomeByPartnerElite(keyword, memberId, searchType, startTime, endTime, pager);
		for (int i = 0; i < memberList.size(); i++) {
			MemberIncome memberIncome = memberList.get(i);
			if (memberIncome.getSourceMemberId() != null) {
				MemberCredit credit = this.memberCreditService
						.findMemberCreditByMemberId(memberIncome.getSourceMemberId());
				if (credit != null) {
					memberIncome.setSourceMemberRealName(credit.getRealName());
				}
			}
			memberList.set(i, memberIncome);

		}
		Integer totalCount = this.memberIncomeRepository.findMemberIncomeCountByPartnerElite(keyword, memberId, searchType, startTime, endTime);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public BigDecimal findMemberIncomeSumByMemberIdAndSourceMemberId(Long memberId, Long sourceMemberId, Date startTime,
			Date endTime, String incomeType) throws EliteServiceException {
		return this.memberIncomeRepository.findMemberIncomeSumByMemberIdAndSourceMemberId(memberId, sourceMemberId,
				incomeType, startTime, endTime);

	}
	
	@Override
	public BigDecimal findMemberIncomeSumByPartnerId(Long sourceMemberId, Long memberId,Long partnerMemberId, Date startTime,
			Date endTime, String incomeType) throws EliteServiceException {
		// TODO Auto-generated method stub
		return this.memberIncomeRepository.findMemberIncomeSumByPartnerId(sourceMemberId, memberId,partnerMemberId,
				incomeType, startTime, endTime);

	}

	@Override
	public SearchResult<MemberIncome> findMemberIncomesByPartner(String keyword, String searchType, Long partnerId,
			Long memberId, Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<MemberIncome> sr = new SearchResult<>();
		List<MemberIncome> memberList = this.memberIncomeRepository.findMemberIncomesByPartner(keyword, searchType,
				partnerId, startTime, endTime, pager);
		for (int i = 0; i < memberList.size(); i++) {
			BigDecimal allincome = new BigDecimal(0);
			MemberIncome memberIncome = memberList.get(i);
			if (memberIncome.getMemberId() != null) {
				allincome = this.memberIncomeRepository.findMemberIncomeSumByMemberIdAndSourceMemberId(memberId,
						memberIncome.getMemberId(), null, startTime, endTime);
			}
			memberIncome.setAllamount(allincome);
			if (memberIncome.getSourceMemberId() != null) {
				MemberCredit credit = this.memberCreditService
						.findMemberCreditByMemberId(memberIncome.getSourceMemberId());
				if (credit != null) {
					memberIncome.setSourceMemberRealName(credit.getRealName());
				}
			}
			memberList.set(i, memberIncome);

		}
		Integer totalCount = this.memberIncomeRepository.findMemberIncomeCountByPartner(keyword, searchType, partnerId,
				startTime, endTime, pager);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public SearchResult<Project> findMemberIncomesByPartnerCompanyOwn(Long memberId, String searchType,
			Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<Project> sr = new SearchResult<>();
		List<Project> memberList = this.projectRepository.findProjectListByMemberPartnerCompanyByOwn(memberId,searchType,
				startTime, endTime, pager);
		for(int i=0;i<memberList.size();i++){
			BigDecimal allincome=this.findMemberIncomeByMemeberIdAndProjectId(memberId, memberList.get(i).getId());
			memberList.get(i).setPartnerIncome(allincome);
		}
		Integer totalCount = this.projectRepository.findProjectCountByMemberPartnerCompanyByOwn(memberId, searchType, startTime, endTime);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}
	
	@Override
	public SearchResult<MemberIncome> findMemberIncomesByPartnerCompanyType(Long memberId,String keyword, String searchType,
			Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<MemberIncome> sr = new SearchResult<>();
		List<MemberIncome> memberList = this.memberIncomeRepository.findMemberIncomeByPartnerCompany(memberId,keyword,
				startTime, endTime, searchType, pager);
		for (int i = 0; i < memberList.size(); i++) {
			Long projectId = memberList.get(i).getProjectId();
			BigDecimal allincome = this.memberIncomeRepository.findMemberIncomeSumByMemberIdAndProjectId(memberId,
					projectId, searchType, startTime, endTime);
			memberList.get(i).setAllamount(allincome);
			
			if(memberList.get(i).getSourceMemberId()!=null){
				MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(memberList.get(i).getSourceMemberId());
				if(credit!=null){
				memberList.get(i).setSourceMemberRealName(credit.getRealName());
				}
			}
		}
		
		Integer totalCount = this.memberIncomeRepository.findMemberIncomesCountByPartnerCompany(memberId, keyword,searchType,
				startTime, endTime);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}
	
	@Override
	public Integer findMemberIncomeProjectCount(Long memberId,Income_Type type)throws EliteServiceException{
		return memberIncomeRepository.findMemberIncomeProjectCount(memberId, type);
	}

	@Override
	public BigDecimal findMemberIncomeByMemeberIdAndProjectId(Long memberId, Long projectId)
			throws EliteServiceException {
		return this.memberIncomeRepository.findMemberIncomeSumByMemberIdAndProjectId(memberId, projectId, null, null, null);
	}

}
