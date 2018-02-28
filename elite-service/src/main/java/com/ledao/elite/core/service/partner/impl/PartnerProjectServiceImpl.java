package com.ledao.elite.core.service.partner.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.domain.partner.PartnerProject.PartnerProject_Status;
import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.repository.partner.PartnerEliteRepository;
import com.ledao.elite.core.repository.partner.PartnerProjectRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.partner.PartnerProjectRecordService;
import com.ledao.elite.core.service.partner.PartnerProjectService;
import com.ledao.elite.core.service.project.ProjectService;

@Service("partnerProjectService")
public class PartnerProjectServiceImpl extends BaseSerivceImpl implements PartnerProjectService {

	@Resource
	private PartnerProjectRepository partnerProjectRepository;
	@Resource
	private PartnerEliteRepository partnerEliteRepository;
	@Resource
	private MemberPassportRepository memberPassportRepository;
	@Resource
	private ProjectService projectService;
	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private PartnerProjectRecordService partnerProjectRecordService;

	@Override
	public PartnerProject createPartnerProject(PartnerProject obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getPartnerId());
		boolean  rs=this.findPartnerCompanyPhoneExisted(obj.getPhone());
		//2016-12-21更新为，一个帐号可以在多种角色备案
		//PartnerElite partnerElite = partnerEliteRepository.searchUnique(new Search().addFilterEqual("phone", obj.getPhone()));
		if(rs){
			throw new EliteServiceException("手机号码已备案过",ErrorCodeEnum.ACCOUNT_EXIST.code);
		}else{
			List<MemberPassport> member= this.memberPassportRepository.queryMemberIdentityList(obj.getPhone());
			if(!member.isEmpty()||member.size()>0){
				throw new EliteServiceException("手机号已注册过",ErrorCodeEnum.ACCOUNT_EXIST.code);
			}else{
				this.partnerProjectRepository.save(obj);
				//备案数量+1
				MemberPartnerCompany company = memberPartnerCompanyService.findMemberPartnerCompanybyId(obj.getPartnerId());
				company.setPutCount(company.getPutCount() + 1);
				memberPartnerCompanyService.updateMemberPartnerCompany(company.getMemberId(), company);
			}
		}
		return obj;
	}

	@Override
	public PartnerProject createPartnerProjectByMemberCompany(Project project, Long partnerId, String account)
			throws EliteServiceException {
		PartnerProject obj=this.findPartnerCompanyByPartnerPut(partnerId, account);
		if(obj==null){
		   obj=new PartnerProject();
		   MemberPartnerCompany company = memberPartnerCompanyService.findMemberPartnerCompanybyId(obj.getPartnerId());
			company.setPutCount(company.getPutCount() + 1);
			memberPartnerCompanyService.updateMemberPartnerCompany(company.getMemberId(), company);
		}
		obj.setAreaId(project.getAreaId());
		obj.setAreaName(project.getAreaName());
		obj.setProductIndustry(project.getProductIndustry());
		obj.setProductStage(project.getProductIndustry());
		obj.setProjectSolution(project.getProjectSolution());
		obj.setProjectName(project.getName());
		obj.setPartnerId(partnerId);
		obj.setPhone(account);
		obj.setProjectId(project.getId());
		this.partnerProjectRepository.save(obj);
		return obj;
	}

	@Override
	public PartnerProject updatePartnerProject(PartnerProject obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getId());
		this.partnerProjectRepository.save(obj);
		return obj;
	}

	@Override
	public PartnerProject findPartnerProjectByAccount(String account) throws EliteServiceException {
		this.verifyParams(account);
		Search search = new Search();
		search.addFilterEqual("phone", account);

		List<PartnerProject> list = this.partnerProjectRepository.search(search);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public PartnerProject findPartnerProjectAsEnter(String account, String recommdUser, String contactPhone,Long partnerId)
			throws EliteServiceException {
		Search search = new Search();
		search.addFilterOr(new Filter("phone", account), new Filter("phone", recommdUser),
				new Filter("phone", contactPhone));
		if(partnerId!=null){
			search.addFilterEqual("partnerId", partnerId);
		}
		search.addSort("createTime", false);
		List<PartnerProject> list = partnerProjectRepository.search(search);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public PartnerProject findPartnerProjectById(Long id) throws EliteServiceException {
		return this.partnerProjectRepository.searchUnique(new Search().addFilterEqual("id", id));
	}

	@Override
	public SearchResult<PartnerProject> findPartnerProjectList(Long partnerId, String name, String phone, String email,
			String status, Pager pager) throws EliteServiceException {
		SearchResult<PartnerProject> rs = this.partnerProjectRepository
				.findPartnerProjectListByNameAndPhoneAndEmailAndStatus(partnerId, name, phone, email, status, pager);
		List<PartnerProject> list = rs.getResult();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProjectId() != null) {
				Project obj = this.projectService.findProjectById(list.get(i).getProjectId());
				list.get(i).setProject(obj);
			}
		}
		rs.setResult(list);
		return rs;

	}

	@Override
	public SearchResult<PartnerProject> findPartnerProjectListByKeyWord(Long memberId, Long partnerId, String keyword,
			Pager pager) throws EliteServiceException {
		SearchResult<PartnerProject> sr = new SearchResult<PartnerProject>();
		List<PartnerProject> partnerlist = this.partnerProjectRepository.findPartnerProjectByKeyword(memberId, null,
				keyword, partnerId, pager);
		for (int i = 0; i < partnerlist.size(); i++) {
			Project project = this.projectService.findProjectById(partnerlist.get(i).getProjectId());
			partnerlist.get(i).setProject(project);
		}
		Integer totalCount = this.partnerProjectRepository.findPartnerProjectByKeywordCount(memberId, null, keyword,
				partnerId);
		sr.setTotalCount(totalCount);
		sr.setResult(partnerlist);
		return sr;
	}

	@Override
	public Integer findPartnerProjectCountByPartnerId(Long partnerId, String type) throws EliteServiceException {

		return this.partnerProjectRepository.findPartnerProjectByPartnerIdCount(partnerId, type);
	}

	@Override
	public SearchResult<PartnerProject> findPartnerProjectList(Long partnerId, Pager pager)
			throws EliteServiceException {
		this.verifyParams(partnerId);
		Search search = new Search(PartnerProject.class);
		search.addFilter(new Filter("partnerId", partnerId));
		search.addSort("createTime", true);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return this.partnerProjectRepository.searchAndCount(search);
	}

	@Override
	public SearchResult<PartnerProject> findPartnerProjectsByStatusAndKeyWorld(Long partnerId, String areaName,
			String keyword, String entryed, String status,Date startTime,Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<PartnerProject> sr = new SearchResult<PartnerProject>();
		List<PartnerProject> partnerlist = this.partnerProjectRepository
				.findPartnerProjectsByStatusAndKeyWorld(partnerId, areaName, keyword, entryed, status,startTime,endTime, pager);
		for (int i = 0; i < partnerlist.size(); i++) {
			PartnerProjectRecord projectRecord = this.partnerProjectRecordService
					.findPartnerProjectRecordByPartnerProjectId(partnerlist.get(i).getId());
			if (projectRecord != null) {
				Project project = projectRecord.getProject();
				partnerlist.get(i).setStatus(PartnerProject_Status.audit_pass);
				partnerlist.get(i).setProject(project);
			}
		}
		Integer totalCount = this.partnerProjectRepository.findPartnerProjectsByStatusAndKeyWorldCount(partnerId,
				areaName, keyword, entryed, status,startTime,endTime);
		sr.setTotalCount(totalCount);
		sr.setResult(partnerlist);
		return sr;
	}

	@Override
	public SearchResult<PartnerProject> findPartnerCompanyByPartner(Long memberId, PartnerProject_Status status,
			Pager pager) throws EliteServiceException {
		this.verifyParams(memberId, status);
		MemberPartnerCompany company = this.memberPartnerCompanyService.findMemberPartnerCompanyByMemberId(memberId);
		Long partnerId = company.getId();
		Search search = new Search();
		search.addFilterEqual("status", status);
		search.addFilterEqual("partnerId", partnerId);
		search.addFilterNotEmpty("projectId");
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		SearchResult<PartnerProject> sr = this.partnerProjectRepository.searchAndCount(search);
		List<PartnerProject> partnerProjects = sr.getResult();
		sr.setResult(partnerProjects);
		return sr;
	}
	@Override
	public boolean findPartnerCompanyPhoneExisted(String phone) throws EliteServiceException {
		this.verifyParams(phone);
		List<PartnerProject> list =this.partnerProjectRepository.search(new Search().addFilterEqual("phone", phone));
	    boolean rs=false;
		if(list.size()>0){
	    	rs=true;
	    }
		return  rs;
	}
	
	public PartnerProject findPartnerCompanyListFirstByPhone(String phone)throws EliteServiceException{
		this.verifyParams(phone);
		List<PartnerProject> list =this.partnerProjectRepository.search(new Search().addFilterEqual("phone", phone));
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public PartnerProject findPartnerCompanyByPartnerPut(Long partnerId, String account) throws EliteServiceException {
		Search search=new Search();
		search.addFilterEqual("phone", account);
		search.addFilterEqual("partnerId", partnerId);
		search.addFilterEmpty("projectId");
		return this.partnerProjectRepository.searchUnique(search);
	}

	@Override
	public void updatePartnerCompanyStatuaBysql(String status, long id) throws EliteServiceException {
		this.partnerProjectRepository.updatePartnerProjectStatusBySql(status, id);
	}


}
