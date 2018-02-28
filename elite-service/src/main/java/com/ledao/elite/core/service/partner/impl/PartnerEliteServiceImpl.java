package com.ledao.elite.core.service.partner.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.member.MemberWithdraw.Withdraw_Status;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Status;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberPartnerEliteRepository;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.repository.partner.PartnerEliteRepository;
import com.ledao.elite.core.repository.partner.PartnerProjectRepository;
import com.ledao.elite.core.repository.project.ProjectStageTaskRepository;
import com.ledao.elite.core.repository.project.ProjectTaskRecruitRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.partner.PartnerEliteService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectStageTaskService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.core.utils.DateTimeUtils;

@Service("partnerEliteService")
public class PartnerEliteServiceImpl extends BaseSerivceImpl implements PartnerEliteService {

	@Resource
	private PartnerEliteRepository partnerEliteRepository;
	@Resource
	private PartnerProjectRepository partnerProjectRepository;
	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private ProjectStageTaskService projectStageTaskService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private ProjectService projectService;
	@Resource
	private MemberWithdrawService memberWithdrawService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private MemberPartnerEliteRepository memberPartnerEliteRepository;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource
	private MemberPassportRepository memberPassportRepository;
	@Resource
	private ProjectStageTaskRepository projectStageTaskRepository;
	@Resource
	private ProjectTaskRecruitRepository projectTaskRecruitRepository;

	@Override
	public PartnerElite createPartnerElite(PartnerElite obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getPartnerId(), obj.getPhone());
		PartnerElite partnerElite = this.findPartnerEliteListByPhone(obj.getPhone());
		PartnerProject partnerProject = partnerProjectRepository.searchUnique(new Search().addFilterEqual("phone", obj.getPhone()));
		if (partnerElite != null ||partnerProject!=null) {
			throw new EliteServiceException("手机号码已备案过", ErrorCodeEnum.ACCOUNT_EXIST.code);
		} else {
			MemberPartnerElite elite = memberPartnerEliteService.findMemberPartnerEliteById(obj.getPartnerId());
			elite.setPutCount(elite.getPutCount() + 1);
			memberPartnerEliteService.updateMemberPartnerElite(elite.getMemberId(), elite);
			this.partnerEliteRepository.save(obj);
		}
		return obj;
	}
	@Override
	public PartnerElite createPartnerEliteByPartner(PartnerElite obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getPartnerId(), obj.getPhone());
		PartnerElite partnerElite = this.findPartnerEliteListByPhone(obj.getPhone());
		//PartnerProject partnerProject = partnerProjectRepository.searchUnique(new Search().addFilterEqual("phone", obj.getPhone()));
		if (partnerElite != null) {
			throw new EliteServiceException("手机号码已备案过", ErrorCodeEnum.ACCOUNT_EXIST.code);
		} else {
			List<MemberPassport> member= this.memberPassportRepository.queryMemberIdentityList(obj.getPhone());
			if(!member.isEmpty()||member.size()>0){
				throw new EliteServiceException("手机号已注册过", ErrorCodeEnum.ACCOUNT_EXIST.code);
			} else {
				MemberPartnerElite elite = memberPartnerEliteService.findMemberPartnerEliteById(obj.getPartnerId());
				elite.setPutCount(elite.getPutCount() + 1);
				memberPartnerEliteService.updateMemberPartnerElite(elite.getMemberId(), elite);
				this.partnerEliteRepository.save(obj);
			}
		}
		return obj;
	}

	@Override
	public PartnerElite createPartnerEliteNoVerifyParams(PartnerElite obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getPartnerId(), obj.getPhone());
		MemberPartnerElite elite = memberPartnerEliteService.findMemberPartnerEliteById(obj.getPartnerId());
		elite.setPutCount(elite.getPutCount() + 1);
		memberPartnerEliteService.updateMemberPartnerElite(elite.getMemberId(), elite);
		this.partnerEliteRepository.save(obj);

		return obj;
	}

	@Override
	public SearchResult<PartnerElite> findPartnerEliteList(Long partnerId, String name, String phone, String email,
			String status, Pager pager) throws EliteServiceException {
		return this.partnerEliteRepository.findPartnerListByNameAndPhoneAndEmailAndStatus(partnerId, name, phone, email,
				status, pager);
	}

	@Override
	public PartnerElite findPartnerEliteBymemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		List<PartnerElite> list=this.partnerEliteRepository.search(new Search().addFilterEqual("memberId", memberId));
		return list.isEmpty()?null:list.get(0);
	}

	@Override
	public PartnerElite findPartnerEliteByAccount(String account) throws EliteServiceException {
		this.verifyParams(account);
		Search search = new Search();
		search.addFilterEqual("phone", account);
		List<PartnerElite> list = this.partnerEliteRepository.search(search);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public PartnerElite updatePartnerElite(Long auditId, Long id) throws EliteServiceException {
		this.verifyParams(auditId, id);
		PartnerElite obj = this.partnerEliteRepository.find(id);
		if (null == obj)
			throw new EliteServiceException("未找到相应的精英", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setAuditId(auditId);
		this.partnerEliteRepository.save(obj);
		return obj;
	}

	@Override
	public PartnerElite updatePartnerElite(PartnerElite obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getId());
		this.partnerEliteRepository.save(obj);
		return obj;
	}

	@Override
	public SearchResult<PartnerElite> findPartnerElitesByKeyWord(Long memberId, String status, String keyword,
			Long partnerId, String searchType, Date startTime, Date endTime, Pager pager) throws EliteServiceException {

		SearchResult<PartnerElite> sr = new SearchResult<PartnerElite>();
		List<PartnerElite> partnerlist = this.partnerEliteRepository.findPartnerEliteByKeyword(memberId, status,
				keyword, partnerId, searchType, startTime, endTime, pager);
		// 查询入驻精英的审核状态
		for (int i = 0; i < partnerlist.size(); i++) {
			PartnerElite partnerElite = partnerlist.get(i);
			if (partnerElite.getMemberId() != null) {
				MemberElite elite = this.memberEliteService.findMemberEliteByMemberId(partnerElite.getMemberId());
				if (elite != null) {
					partnerlist.get(i).setEliteStatus(elite.getStatus());
					partnerlist.get(i).setMemberElite(elite);
				}
				MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(partnerElite.getMemberId());
				if (basic != null) {
					partnerlist.get(i).setMemberBasic(basic);
				}
				MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(partnerElite.getMemberId());
				if (credit != null) {
					partnerlist.get(i).setMemberCredit(credit);
				}
			}
		}

		Integer totalCount = this.partnerEliteRepository.findPartnerEliteByKeywordCount(memberId, status, keyword,
				partnerId, searchType, startTime, endTime, pager);
		sr.setTotalCount(totalCount);
		sr.setResult(partnerlist);
		return sr;
	}

	@Override
	public PartnerElite findPartnerEliteById(Long id) throws EliteServiceException {
		this.verifyParams(id);

		return this.partnerEliteRepository.searchUnique(new Search().addFilterEqual("id", id));
	}

	@Override
	public SearchResult<PartnerElite> findPartnerElitesByPartner(Long memberId, PartnerElite_Status status, Pager pager)
			throws EliteServiceException {
		this.verifyParams(memberId, status);
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(memberId);
		Long partnerId = elite.getId();
		Search search = new Search();
		search.addFilterEqual("partnerId", partnerId);
		search.addFilterNotEmpty("memberId");
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		SearchResult<PartnerElite> sr = this.partnerEliteRepository.searchAndCount(search);
		List<PartnerElite> eliteList = sr.getResult();

		// 任务处理，
		for (int i = 0; i < eliteList.size(); i++) {
			PartnerElite partnerElite = eliteList.get(i);
			List<ProjectStageTask> tasks = this.projectStageTaskService
					.findProjectStageTaskByMemberId(partnerElite.getMemberId());
			int tasksInCount = 0;
			int tasksFinishCount = 0;
			BigDecimal tasksInAmount = new BigDecimal(0);
			BigDecimal tasksFinishAmount = new BigDecimal(0);
			BigDecimal WithdrawAmount = new BigDecimal(0);

			// 精英任务查询
			for (int j = 0; j < tasks.size(); j++) {
				ProjectStageTask task = tasks.get(j);
				if (task.getStatus().equals(ProjectTask_Status.finish)
						|| task.getStatus().equals(ProjectTask_Status.quality)) {
					tasksFinishCount++;
					if (task.getAmount() != null) {
						tasksFinishAmount = tasksFinishAmount.add(task.getAmount());
					}
				} else if (task.getStatus().name().equals(ProjectTask_Status.starting.name())
						|| task.getStatus().equals(ProjectTask_Status.wait_accept)) {
					tasksInCount++;
					if (task.getAmount() != null) {
						tasksInAmount = tasksInAmount.add(task.getAmount());
					}
				}
			}
			MemberElite memberElite = this.memberEliteService.findMemberEliteByMemberId(partnerElite.getMemberId());
			// cto 项目查询
			if (memberElite != null && memberElite.isCtoed()) {
				List<Project> projects = this.projectService.findProjectListByctoId(partnerElite.getMemberId());
				for (int k = 0; k < projects.size(); k++) {
					Project project = projects.get(k);
					if (project.getStatus().equals(Project_Status.stage_course)) {
						tasksInCount++;
						if (project.getTotalAmount() != null) {
							tasksInAmount = tasksInAmount.add(project.getTotalAmount());
						}
					} else if (project.getStatus().equals(Project_Status.finish)
							|| project.getStatus().equals(Project_Status.quality)) {
						tasksFinishCount++;
						if (project.getTotalAmount() != null) {
							tasksFinishAmount = tasksFinishAmount.add(project.getTotalAmount());
						}
					}
				}
			}
			// 近一个月 提现查询
			List<MemberWithdraw> withdraws = this.memberWithdrawService.findMemberWithdrawListByMemberId(
					DateTimeUtils.addMonth(new Date(), -1), partnerElite.getMemberId(), Withdraw_Status.success);
			for (int j = 0; j < withdraws.size(); j++) {
				MemberWithdraw withdraw = withdraws.get(i);
				if (withdraw.getReceiptAmount() != null) {
					WithdrawAmount = WithdrawAmount.add(withdraw.getReceiptAmount());
				}

			}
			partnerElite.setTasksInCount(tasksInCount);
			partnerElite.setTasksFinishCount(tasksFinishCount);
			partnerElite.setTasksInAmount(tasksInAmount);
			partnerElite.setTasksFinishAmount(tasksFinishAmount);
			partnerElite.setWithdrawAmount(WithdrawAmount);
			eliteList.set(i, partnerElite);
		}
		sr.setResult(eliteList);
		return sr;
	}

	@Override
	public List<PartnerElite> findPartnerEliteListByPartner(Long partnerId) throws EliteServiceException {
		this.verifyParams(partnerId);
		return this.partnerEliteRepository.search(new Search().addFilterEqual("partnerId", partnerId));
	}

	@Override
	public PartnerElite findPartnerEliteListByPhone(String phone) throws EliteServiceException {
		this.verifyParams(phone);
		return this.partnerEliteRepository.searchUnique(new Search().addFilterEqual("phone", phone));
	}

	@Override
	public SearchResult<PartnerElite> findPartnerElitesByKeyWordAndInviteRegister(Long partnerId, String keyword,
			String status, Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<PartnerElite> sr = new SearchResult<PartnerElite>();
		List<PartnerElite> list = this.partnerEliteRepository.findPartnerEliteListByKeywordAndStatus(partnerId, status,
				keyword, startTime, endTime, pager);
		for (int i = 0; i < list.size(); i++) {
			PartnerElite partnerElite = list.get(i);
			if (partnerElite.getMemberId() != null) {
				MemberElite elite = this.memberEliteService.findMemberEliteByMemberId(partnerElite.getMemberId());
				if (elite != null) {
					partnerElite.setMemberElite(elite);
				}
				MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(partnerElite.getMemberId());
				if (basic != null) {
					partnerElite.setMemberBasic(basic);
				}
				MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(partnerElite.getMemberId());
				if (credit != null) {
					partnerElite.setMemberCredit(credit);
				}
				Date recruitTime = this.projectTaskRecruitService
						.findProjectTaskRecuritLastRecruitTime(partnerElite.getMemberId());
				partnerElite.setLastRecruitTime(recruitTime);

				Search search = new Search();
				search.addFilterEqual("eliteMemberId", partnerElite.getMemberId());
				List<ProjectStageTask> tasks = this.projectStageTaskRepository.search(search);
				// 精英任务查询
				int tasksInCount = 0;
				for (int j = 0; j < tasks.size(); j++) {
					ProjectStageTask task = tasks.get(j);
					if (task.getStatus().equals(ProjectTask_Status.starting)
							|| task.getStatus().equals(ProjectTask_Status.wait_accept)||task.getStage().equals(ProjectTask_Status.quality)) {
						tasksInCount++;
					}
				}
				Date lastRecuriteTime=this.findLastRecruitTimeByMemberId(partnerElite.getMemberId());
				// cto 项目查询
				if (elite != null && elite.isCtoed()) {
					List<Project> projects = this.projectService.findProjectListByctoId(partnerElite.getMemberId());
					for (int k = 0; k < projects.size(); k++) {
						Project project = projects.get(k);
						if (project.getStatus().equals(Project_Status.stage_course)||project.getStatus().equals(Project_Status.quality)) {
							tasksInCount++;
						}
					}
					//最后接项目时间
					Date lastReProject=this.projectService.findparojectListByCtoId(partnerElite.getMemberId());
					if(lastRecuriteTime==null){
						lastRecuriteTime=lastReProject;
					}else{
						if(lastReProject!=null){
							if(lastReProject.getTime()>lastRecuriteTime.getTime()){
								lastRecuriteTime=lastReProject;
							}
						}
					}
				}
				partnerElite.setTasksInCount(tasksInCount);

				// 查询订单时间，防止循环引用写在这里
			
				partnerElite.setLastRecruitTime(lastRecuriteTime);
				//
				list.set(i, partnerElite);
			}
		}
		Integer totalCount = this.partnerEliteRepository.findPartnerEliteListByKeywordAndStatusCount(partnerId, status,
				keyword, startTime, endTime);
		sr.setResult(list);
		sr.setTotalCount(totalCount);
		return sr;
	}

	@Override
	public SearchResult<PartnerElite> findPartnerEliteInComeList(Long parnterMemberId,Long partnerId, Long memberId, String incomeType,
			String status, Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<PartnerElite> sr = new SearchResult<PartnerElite>();
		List<PartnerElite> list = this.partnerEliteRepository.findPartnerEliteInComeList(partnerId, incomeType, status,
				startTime, endTime, pager);
		for (int i = 0; i < list.size(); i++) {
			PartnerElite partnerElite = list.get(i);
			if (partnerElite.getMemberId() != null) {
				BigDecimal tasksInAmount = this.memberIncomeService
						.findMemberIncomeSumByMemberId(partnerElite.getMemberId(), startTime, endTime, null);
				list.get(i).setTasksInAmount(tasksInAmount);
				BigDecimal partnerAmount = new BigDecimal(0);
				if(incomeType.equals(Income_Type.partner_direct.name())){
					partnerAmount = this.memberIncomeService.findMemberIncomeSumByPartnerId(memberId,
							 partnerElite.getMemberId(), parnterMemberId,startTime, endTime, incomeType);
				}else if(incomeType.equals(Income_Type.partner_own.name())){
					partnerAmount = this.memberIncomeService.findMemberIncomeSumByMemberIdAndSourceMemberId(parnterMemberId,
							 partnerElite.getMemberId(), startTime, endTime, incomeType);
				}
				list.get(i).setPartnerAmount(partnerAmount);
			}
		}
		Integer totalCount = this.partnerEliteRepository.findMemberPartnerEliteListInComeCount(partnerId, incomeType,
				status, startTime, endTime);
		sr.setResult(list);
		sr.setTotalCount(totalCount);
		return sr;
	}

	@Override
	public SearchResult<MemberPartnerElite> findPartnerEliteDirectInComeList(Long partnerId, Long memberId,
			String status, String searchType, Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<MemberPartnerElite> sr = new SearchResult<MemberPartnerElite>();
		Integer totalCount ;
		List<MemberPartnerElite> list;
		if(searchType.equals(Income_Type.partner_direct.name())){
		 list= this.memberPartnerEliteRepository.findMemberPartnerEliteDirectList(partnerId,
				status, searchType, startTime, endTime, pager);
		 totalCount= this.memberPartnerEliteRepository.findMemberPartnerEliteListDirectCount(partnerId, status,
					searchType, startTime, endTime);
		}
		else{
			list=this.memberPartnerEliteRepository.findMemberPartnerEliteInDirectList(memberId, status, searchType, startTime, endTime, pager);
			totalCount=this.memberPartnerEliteRepository.findMemberPartnerEliteListDirectCount(memberId, status, searchType, startTime, endTime);
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMemberId() != null) {
				MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(list.get(i).getMemberId());
				list.get(i).setMemberBasic(basic);
				// 收益
				BigDecimal income = this.memberIncomeService.findMemberIncomeSumByMemberId(list.get(i).getMemberId(),
						startTime, endTime, Income_Type.partner_own.name());
				BigDecimal partnerIncome = this.memberIncomeService.findMemberIncomeSumByMemberIdAndSourceMemberId(memberId,list.get(i).getMemberId(), startTime,
						endTime, searchType);
				list.get(i).setIncome(income);
				list.get(i).setPartnerIncome(partnerIncome);
//				Integer putCount = this.findPartnerEliteCountByIncomeDataAndPartnerId(memberId, list.get(i).getId(),
//						startTime, endTime, searchType);
//				list.get(i).setPutCount(putCount);
			}

		}
		sr.setResult(list);
		sr.setTotalCount(totalCount);
		return sr;
	}

	@Override
	public Integer findPartnerEliteCountByPartnerId(Long partnerId, String type) throws EliteServiceException {
		return this.partnerEliteRepository.findPartnerEliteByKeywordCount(null, type, null, partnerId, null, null, null,
				null);
	}

	@Override
	public Integer findPartnerEliteCountByIncomeDataAndPartnerId(Long memberId, Long partnerId, Date startTime,
			Date endTime, String incomeType) throws EliteServiceException {
		return this.partnerEliteRepository.findPartnerEliteCountByIncomeDataAndPartnerId(partnerId, memberId,
				incomeType, startTime, endTime);
	}

	public Date findLastRecruitTimeByMemberId(long memberId) throws EliteServiceException {
		Search search = new Search();
		search.addFilterEqual("memberId", memberId);
		search.addSort("createTime", true);
		search.addFilterEqual("status", ProjectTaskRecruit_Status.recruit_win);
		List<ProjectTaskRecruit> list = this.projectTaskRecruitRepository.search(search);
 		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0).getRecruitTime();
		}

	}
	@Override
	public void updatePartnerEliteStatusBysql(String status, Long id) throws EliteServiceException {
        this.partnerEliteRepository.updatePartnerEliteIdBysql(status, id);		
	}

}
