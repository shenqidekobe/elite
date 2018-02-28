package com.ledao.elite.core.service.member.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.member.MemberPartnerCompanyRepository;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.repository.partner.PartnerProjectRecordRepository;
import com.ledao.elite.core.repository.partner.PartnerProjectRepository;
import com.ledao.elite.core.repository.partner.PartnerRecordRepository;
import com.ledao.elite.core.repository.project.ProjectRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;

@Service("memberPartnerCompanyService")
public class MemberPartnerCompanyServiceImpl extends BaseSerivceImpl implements MemberPartnerCompanyService {

	@Resource
	private MemberPartnerCompanyRepository memberPartnerCompanyRepository;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private MemberPassportRepository memberPassportRepository;
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private PartnerProjectRepository partnerProjectRepository;
	@Resource
	private ProjectRepository projectRepository;
	@Resource
	private PartnerRecordRepository partnerRecordRepository;
	@Resource
	private PartnerProjectRecordRepository partnerProjectRecordRepository;

	@Override
	public MemberPartnerCompany createMemberPartnerCompany(MemberPartnerCompany obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getMemberId());
		obj.setStatus(MemberPassport.Member_Status.waitAduit);
		this.memberPartnerCompanyRepository.save(obj);
		memberPartnerCompanyRepository.flush();
		return obj;
	}

	@Override
	public void updateMemberPartnerCompanyInfo(Long memberId, String sex, String areaName, String companyName,
			String email) throws EliteServiceException {
		this.verifyParams(memberId, sex, areaName, email);
		MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(memberId);
		if (basic == null) {
			basic = new MemberBasic();
			basic.setMemberId(memberId);
		}
		basic.setSex(sex);
		basic.setAreaName(areaName);
		basic.setEmail(email);
		if (basic.getId() == null) {
			this.memberBasicService.createMemberBasic(basic);
		} else {
			this.memberBasicService.updateBasicInfoNoFixed(basic);
		}
		if (companyName != null) {
			MemberPartnerCompany company = this.findMemberPartnerCompanyByMemberId(memberId);
			company.setCompanyName(companyName);
			this.memberPartnerCompanyRepository.save(company);
		}

	}

	@Override
	public MemberPartnerCompany updateMemberPartnerCompany(Long memberId, MemberPartnerCompany obj)
			throws EliteServiceException {
		MemberPartnerCompany pojo = this.findMemberPartnerCompanyByMemberId(memberId);
		if (pojo == null)
			return null;
		BeanUtils.copyProperties(obj, pojo);
		this.memberPartnerCompanyRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberPartnerCompany updateMemberPartnerCompanyStatus(Long memberId, String status)
			throws EliteServiceException {
		MemberPartnerCompany pojo = this.findMemberPartnerCompanyByMemberId(memberId);
		if (pojo == null)
			return null;
		pojo.setStatus(Member_Status.valueOf(Member_Status.class, status));
		this.memberPartnerCompanyRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberPartnerCompany updateMemberPartnerCompanyAudit(MemberPartnerCompany company)
			throws EliteServiceException {
		MemberPartnerCompany pojo = this.findMemberPartnerCompanyByMemberId(company.getMemberId());
		if (pojo == null)
			return null;
		pojo.setAuditId(company.getAuditId());
		pojo.setAuditReason(company.getAuditReason());
		pojo.setAuditTime(new Date());
		pojo.setStatus(company.getStatus());
		if (company.getParentId() != null) {
			pojo.setParentId(company.getParentId());
		}
		this.memberPartnerCompanyRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberPartnerCompany updateMemberPartnerCompanyNoFixed(MemberPartnerCompany company)
			throws EliteServiceException {
		this.verifyParams(company, company.getId());
		this.memberPartnerCompanyRepository.save(company);
		return company;
	}
	
	@Override
	public void updateMemberPartnerEliteAsCalculate(Long id,Integer orderCount,BigDecimal totalAmount,Integer calculateOrderCount,
			BigDecimal calculateTotalAmount,Date startOrderTime,Date lastOrderTime)throws EliteServiceException{
		this.memberPartnerCompanyRepository.updateMemberPartnerEliteAsCalculate(id, orderCount, totalAmount, calculateOrderCount, calculateTotalAmount, startOrderTime, lastOrderTime);
	}
	
	@Override
	public void updateMemberPartnerEliteClearCalculate(Long id,Date clearCalculateTime)throws EliteServiceException{
		this.memberPartnerCompanyRepository.updateMemberPartnerEliteClearCalculate(id, clearCalculateTime);
	}

	@Override
	public MemberPartnerCompany findMemberPartnerCompanyByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		return this.memberPartnerCompanyRepository.searchUnique(new Search().addFilterEqual("memberId", memberId));
	}

	@Override
	public MemberPartnerCompany findMemberPartnerCompanyByPhone(String phone) throws EliteServiceException {
		this.verifyParams(phone);
		MemberPassport member = this.memberPassportRepository
				.searchUnique(new Search().addFilterEqual("account", phone));
		if (member == null)
			return null;
		return this.findMemberPartnerCompanyByMemberId(member.getId());
	}

	@Override
	public void updateMemberPartnerCompanyBasic(Long memberId, MemberPassport member, MemberBasic basic,
			MemberCredit credit, MemberPartnerCompany company) throws EliteServiceException {

		// 更改用户名昵称
		MemberPassport memberObj = this.memberPassportRepository.find(memberId);
		memberObj.setNickName(member.getNickName());
		this.memberPassportRepository.save(memberObj);

		// 更新基本信息
		MemberBasic basicObj = this.memberBasicService.findMemberBasicByMemberId(memberId);
		if (basicObj == null) {
			basicObj = new MemberBasic();
			basicObj.setMemberId(memberId);
		}
		basicObj.setAreaName(basic.getAreaName());
		basicObj.setEmail(basic.getEmail());
		basicObj.setSex(basic.getSex());
		if (basicObj.getId() != null) {
			this.memberBasicService.updateBasicInfoNoFixed(basicObj);
		} else {
			this.memberBasicService.createMemberBasic(basicObj);
		}

		// 更新公司名称
		MemberPartnerCompany companyObj = this.findMemberPartnerCompanyByMemberId(memberId);
		companyObj.setCompanyName(company.getCompanyName());
		companyObj.setType(company.getType());
		this.updateMemberPartnerCompanyNoFixed(companyObj);
	}

	@Override
	public MemberPartnerCompany findMemberPartnerCompanybyId(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberPartnerCompanyRepository.find(id);
	}
	
	@Override
	public List<MemberPartnerCompany> findMemberPartnerEliteCompanyByStatus(Member_Status status) throws EliteServiceException{
		return this.memberPartnerCompanyRepository.search(new Search().addFilterEqual("status", status));
	}

	@Override
	public SearchResult<MemberPartnerCompany> findMemberPartnerEliteCompanyByParentId(Long partnerId, Date startTime,
			Date endTime, String status, Pager pager) throws EliteServiceException {
		SearchResult<MemberPartnerCompany> sr = new SearchResult<MemberPartnerCompany>();
		pager.setStartIndex(pager.getStartIndex() + 3);
		List<MemberPartnerCompany> list = this.memberPartnerCompanyRepository
				.findMemberPartnerCompanyChampionsList(partnerId, status, startTime, endTime, pager);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMemberId() != null) {
				MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(list.get(i).getMemberId());
				list.get(i).setMemberBasic(basic);

				// 收益
				BigDecimal income = this.memberIncomeService.findMemberIncomeSumByMemberId(list.get(i).getMemberId(),
						startTime, endTime, null);
				list.get(i).setPartnerIncome(income);

				// 推荐的渠道
				Integer putParnterCount = this.partnerRecordRepository.findPartnerRecordsCountByPartnerIdAndType(
						list.get(i).getId(), PartnerRecord_Type.partnerCompany.name(), startTime, endTime);
				list.get(i).setPutParnterCount(putParnterCount);

				// 推荐的项目

			}
		}

		Integer totalCount = this.memberPartnerCompanyRepository.findMemberPartnerCompanyChampionsListCount(partnerId,
				status);
		if (totalCount > 2) {
			totalCount = totalCount - 3;
		} else {
			totalCount = 0;
		}
		sr.setResult(list);
		sr.setTotalCount(totalCount);
		return sr;
	}

	@Override
	public List<MemberPartnerCompany> findMemberPartnerCompanyListTopThree(Long parentId, Date startTime, String status,
			Date endTime) throws EliteServiceException {
		this.verifyParams(parentId);
		Pager pager = new Pager();
		pager.setPageSize(3);
		List<MemberPartnerCompany> list = this.memberPartnerCompanyRepository
				.findMemberPartnerCompanyChampionsList(parentId, status, startTime, endTime, pager);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMemberId() != null) {
				MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(list.get(i).getMemberId());
				list.get(i).setMemberBasic(basic);

				// 收益
				BigDecimal income = this.memberIncomeService.findMemberIncomeSumByMemberId(list.get(i).getMemberId(),
						startTime, endTime, null);
				list.get(i).setPartnerIncome(income);

				// 推荐的渠道
				Integer putParnterCount = this.partnerRecordRepository
						.findPartnerRecordsCountByPartnerIdAndType(list.get(i).getId(), PartnerRecord_Type.partnerCompany.name(),startTime, endTime);
				list.get(i).setPutParnterCount(putParnterCount);

				// 推荐的项目
				Integer putCount = this.findMemberPartnerProjectCountByTimes(list.get(i).getId(), status, startTime, endTime);
			    list.get(i).setPutCount(putCount);
			}

		}
		return list;
	}

	@Override
	public Integer findMemberPartnerCompanyIndex(Long partnerId, Long memberId, Date startTime, Date endTime,
			String status) {
		List<MemberPartnerCompany> list = this.memberPartnerCompanyRepository.findMemberPartnerCompanyAllList(partnerId,
				status, startTime, endTime);
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			if (memberId.equals(list.get(i).getMemberId())) {
				result = i + 1;
				break;
			}
		}
		return result;
	}

	@Override
	public SearchResult<MemberPartnerCompany> findMemberPartnerComapnyIncomeByParent(Long memberId, String searchType,
			Date startTime, Date endTime, Pager pager) throws EliteServiceException {

		SearchResult<MemberPartnerCompany> sr = new SearchResult<MemberPartnerCompany>();
		List<MemberPartnerCompany> list = this.memberPartnerCompanyRepository
				.findMemberPartnerCompanyListByIncome(memberId, searchType, startTime, endTime, pager);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMemberId() != null) {
				MemberPartnerCompany partnerCompany = list.get(i);
				// 项目总额
				BigDecimal projectTotalAmount = new BigDecimal(0);
				// 项目托管总额
				BigDecimal projectTrustedAmount = new BigDecimal(0);
				List<Project> projects = this.projectRepository
						.findProjectListByMemberPartnerCompanyId(partnerCompany.getId());
				for (int j = 0; j < projects.size(); j++) {
					if (projects.get(j).getTotalAmount() != null) {
						projectTotalAmount = projectTotalAmount.add(projects.get(j).getTotalAmount());
						projectTrustedAmount = projectTrustedAmount.add(projects.get(j).getTrustedAmount());
					}
				}
				partnerCompany.setProjectTotalAmount(projectTotalAmount);
				partnerCompany.setProjectTrustedAmount(projectTrustedAmount);

				BigDecimal myincome = this.memberIncomeService.findMemberIncomeSumByMemberIdAndParnterId(memberId, partnerCompany.getId(), startTime, endTime);
				partnerCompany.setPartnerIncome(myincome);
				list.set(i, partnerCompany);
			}
		}

		Integer totalCount = this.memberPartnerCompanyRepository.findMemberPartnerCompanyListByIncomeCount(memberId,
				searchType, startTime, endTime);
		sr.setResult(list);
		sr.setTotalCount(totalCount);
		return sr;
	}
	
	@Override
	public Map<String,Object> findPartnerCompanyDirectCount(Long id,Date startTime,Date endTime)throws EliteServiceException{
		Map<String,Object> resultMap=new HashMap<String, Object>();
		MemberPartnerCompany partner=this.memberPartnerCompanyRepository.find(id);
		Long partnerId=partner.getParentId();
		if(partnerId==null)return resultMap;
		MemberPartnerCompany parentPartner=this.memberPartnerCompanyRepository.find(partnerId);
		List<MemberPartnerCompany> resultList=getChildrenPartner(parentPartner.getId());
		Integer orderCount=0;
		BigDecimal orderAmount=new BigDecimal(0);
		//直接=本人的上级推荐的渠道的人才总数
		for(MemberPartnerCompany pce:resultList){
			Integer bb=pce.getCalculateOrderCount()==null?0:pce.getCalculateOrderCount();
			BigDecimal cc=pce.getCalculateTotalAmount()==null?new BigDecimal(0):pce.getCalculateTotalAmount();
			orderCount+=bb;
			orderAmount=orderAmount.add(cc);
		}
		resultMap.put("orderCount", orderCount);
		resultMap.put("orderAmount", orderAmount);
		return resultMap;
	}
	
	@Override
	public Map<String,Object> findPartnerCompanyInDirectCount(Long id,Date startTime,Date endTime)throws EliteServiceException{
		Map<String,Object> resultMap=new HashMap<String, Object>();
		MemberPartnerCompany partner=this.memberPartnerCompanyRepository.find(id);
        Long partnerId=partner.getParentId();
		if(partnerId==null)return resultMap;
		MemberPartnerCompany parentPartner=this.memberPartnerCompanyRepository.find(partnerId);
	    Long parentPartnerId=parentPartner.getParentId();
		if(parentPartnerId==null)return resultMap;
		MemberPartnerCompany parentParentPartner=this.memberPartnerCompanyRepository.find(parentPartnerId);
		List<MemberPartnerCompany> resultList=new ArrayList<>();
		List<MemberPartnerCompany> list=getChildrenPartner(parentParentPartner.getId());
		for(MemberPartnerCompany mpe:list){
			resultList.addAll(getChildrenPartner(mpe.getId()));
		}
		//间接=本人的上级的上级下面的渠道推荐的渠道的人才数量
		Integer orderCount=0;
		BigDecimal orderAmount=new BigDecimal(0);
		for(MemberPartnerCompany pce:resultList){
			Integer bb=pce.getCalculateOrderCount()==null?0:pce.getCalculateOrderCount();
			BigDecimal cc=pce.getCalculateTotalAmount()==null?new BigDecimal(0):pce.getCalculateTotalAmount();
			orderCount+=bb;
			orderAmount=orderAmount.add(cc);
		}
		resultMap.put("orderCount", orderCount);
		resultMap.put("orderAmount", orderAmount);
		return resultMap;
	}
	
	private List<MemberPartnerCompany> getChildrenPartner(Long parentId){
		Search search=new Search();
		search.addFilterEqual("parentId",parentId);
		return memberPartnerCompanyRepository.search(search);
	}

	@Override
	public void updateMemberPartnerCompanyPartnerId(Long partnerId, Long id) throws EliteServiceException {
		this.verifyParams(id);
		this.memberPartnerCompanyRepository.updateMemberPartnerCompanyPartnerIdBysql(partnerId, id);

	}

	@Override
	public Integer findMemberPartnerProjectCountByTimes(Long partnerId, String status, Date startTime, Date endTime)
			throws EliteServiceException {
		Integer allCount = 0;
		// 备案表中数量
		allCount += this.partnerProjectRepository.findPartnerProjectByKeywordAndTimeCount(partnerId, null, startTime,
				endTime);
		// 备案记录中的数量
		allCount += this.partnerProjectRecordRepository.findPartnerProjectRecordCountByTime(partnerId, startTime,
				endTime);

		return allCount;
	}
}
