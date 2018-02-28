package com.ledao.elite.core.service.member.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAccount;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Suffix;
import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.domain.member.MemberSkill;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Search_Type;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Status;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord.WorkRecord_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.framework.dto.MemberUpdatePass;
import com.ledao.elite.core.framework.thread.newly.MemberPartnerRegisterThread;
import com.ledao.elite.core.framework.thread.newly.PartnerEliteRegisterThread;
import com.ledao.elite.core.repository.member.MemberIncomeRepository;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.repository.partner.PartnerEliteRepository;
import com.ledao.elite.core.repository.partner.PartnerRecordRepository;
import com.ledao.elite.core.repository.project.ProjectStageTaskRepository;
import com.ledao.elite.core.repository.project.ProjectTaskRecruitRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberBankService;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberEducationService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberIdentityService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberProjectsService;
import com.ledao.elite.core.service.member.MemberSkillService;
import com.ledao.elite.core.service.platform.PlatformWorkRecordService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.sys.CommonCodeService;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.core.utils.encry.PasswordUtils;

@Service("memberPassportService")
public class MemberPassportServiceImpl extends BaseSerivceImpl implements MemberPassportService {

	@Resource
	private MemberPassportRepository memberPassportRepository;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private MemberCompanyService memberCompanyService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private MemberBankService memberBankService;
	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;
	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private MemberIdentityService memberIdentityService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private CommonCodeService commonCodeService;
	@Resource
	private PlatformWorkRecordService PlatformWorkRecordService;
	@Resource
	private MemberProjectsService memberProjectsService;
	@Resource
	private MemberEducationService memberEducationService;
	@Resource
	private MemberSkillService memberSkillService;
	@Resource
	private PartnerEliteRepository partnerEliteRepository;
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectStageTaskRepository projectStageTaskRepository;
	@Resource
	private PartnerRecordRepository partnerRecordRepository;
	@Resource
	private ProjectTaskRecruitRepository projectTaskRecruitRepository;
	@Resource
	private MemberIncomeRepository memberIncomeRepository;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private EventPublishService eventPublishService;

	@SuppressWarnings("incomplete-switch")
	@Override
	public MemberPassport createMemberPassport(MemberPassport obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getAccount(), obj.getPassword());
		String account=obj.getAccount();
		obj.setAccount(account+MemberPassport.typeToSuffix(obj.getCurrentType()).name());
		if (this.findMemberAccountIsExists(obj.getAccount())){
			logger.error("帐号【"+obj.getAccount()+"】已经存在，不能创建***********************");
			throw new EliteServiceException("此帐号已存在", ErrorCodeEnum.OBJECT_EXIST.code);
		}
		// 第一步：保存会员注册信息
		String memberNum = commonCodeService.disposeOddNumber("memberNum", COMMON_PREVAL.M.name(), "yyyyMMdd", 8, null);
		obj.setMemberNum(memberNum);
		if(obj.isDisposePass()){
			String passSalt = PasswordUtils.getAccountSalt();
			obj.setPassSalt(passSalt);
			obj.setPassword(PasswordUtils.getMD5PassWord(obj.getPassword(), passSalt));
		}
		obj.setStatus(MemberPassport.Member_Status.waitAduit);
		boolean flag = this.memberPassportRepository.save(obj);
		Long memberId = obj.getId();
		if (flag) {
			// 第二步：保存会员身份信息
			Long foreignId = null;
			MemberIdentity_Type type = MemberIdentity_Type.valueOf(MemberIdentity_Type.class, obj.getCurrentType());
			switch (type) {
			case company:
				MemberCompany memberCompany = this.memberCompanyService.createMemberCompany(new MemberCompany(memberId));
				foreignId = memberCompany.getId();
				break;
			case elite:
				MemberElite memberElite = this.memberEliteService.createMemberElite(new MemberElite(memberId));
				foreignId = memberElite.getId();
				// 新增渠道方备案功能
				PartnerEliteRegisterThread eliteThread = new PartnerEliteRegisterThread();
				eliteThread.setMemberId(memberId);
				eliteThread.setInviteCode(!obj.isDisposePass()?null:obj.getInviteCode());
				eliteThread.setAccount(account);
				eliteThread.setEmail(obj.getEmail());
				new Thread(eliteThread).start();
				break;
			case partnerCompany:
				MemberPartnerCompany company = new MemberPartnerCompany();
				company.setMemberId(obj.getId());
				company.setChannelNum(obj.getMemberNum());
				MemberPartnerCompany memberPartnerCompany = this.memberPartnerCompanyService
						.createMemberPartnerCompany(company);
				foreignId = memberPartnerCompany.getId();

				// 新增备案功能
				MemberPartnerRegisterThread memberCompnayThread = new MemberPartnerRegisterThread();
				memberCompnayThread.setMemberId(memberId);
				memberCompnayThread.setInviteCode(!obj.isDisposePass()?null:obj.getInviteCode());
				memberCompnayThread.setAccount(account);
				memberCompnayThread.setInvitePhone(obj.getInvitePhone());
				memberCompnayThread.setInviteMemberPartnerType(MemberIdentity_Type.partnerCompany.name());
				memberCompnayThread.setSonPartnerCompany(memberPartnerCompany);
				new Thread(memberCompnayThread).start();
				break;
			case partnerElite:
				MemberPartnerElite elite = new MemberPartnerElite();
				elite.setMemberId(obj.getId());
				elite.setChannelNum(obj.getMemberNum());
				MemberPartnerElite memberPartnerElite = this.memberPartnerEliteService.createMemberPartnerElite(elite);
				foreignId = memberPartnerElite.getId();

				// 新增备案功能
				MemberPartnerRegisterThread memberEliteThread = new MemberPartnerRegisterThread();
				memberEliteThread.setMemberId(memberId);
				memberEliteThread.setInviteCode(!obj.isDisposePass()?null:obj.getInviteCode());
				memberEliteThread.setAccount(account);
				memberEliteThread.setInvitePhone(obj.getInvitePhone());
				memberEliteThread.setInviteMemberPartnerType(MemberIdentity_Type.partnerElite.name());
				memberEliteThread.setSonPartnerElite(memberPartnerElite);
				new Thread(memberEliteThread).start();
				break;
			}
			// 第三步：保存会员身份关系
			MemberIdentity memberIdentity = new MemberIdentity();
			memberIdentity.setMemberId(memberId);
			memberIdentity.setForeignId(foreignId);
			memberIdentity.setType(type.name());
			memberIdentity.setName(type.label);
			this.memberIdentityService.createMemberIdentity(memberIdentity);
		}
		this.memberAccountService.createInitMemberAccount(memberId);
		return obj;
	}
	
	@Override
	public MemberPassport updateMemberIdentity(Long id,String identityType)throws EliteServiceException{
		this.verifyParams(id,identityType);
		Member_Suffix target=MemberPassport.typeToSuffix(identityType);
		MemberPassport sourceMember=this.memberPassportRepository.find(id);
		if(sourceMember==null)return null;
		String account=sourceMember.getAccountOffSuffix();//当前会员帐号的手机号
		String targetAccount=account+target.name();
		MemberPassport targetMember=this.memberPassportRepository.searchUnique(new Search().addFilterEqual("account", targetAccount)); 
		if (targetMember!=null){
			//同步银行卡信息
			List<MemberBank> bankList=this.memberBankService.findMemberBankByMemberId(id);
			this.eventPublishService.publishMemberIdentityDate(bankList.isEmpty()?null:bankList.get(0), null, null, null);
			return targetMember;
		}
		
		targetMember=new MemberPassport();
		targetMember.setNickName(sourceMember.getNickName());
		targetMember.setEmail(sourceMember.getEmail());
		targetMember.setChannel(sourceMember.getChannel());
		targetMember.setRecommendNum(sourceMember.getRecommendNum());
		targetMember.setInviteCode(sourceMember.getInviteCode());
		targetMember.setAccount(account);
		targetMember.setDisposePass(false);
		targetMember.setPassSalt(sourceMember.getPassSalt());
		targetMember.setPassword(sourceMember.getPassword());
		targetMember.setCurrentType(identityType);
		targetMember= this.createMemberPassport(targetMember);
		Long targetId=targetMember.getId();
		//1、同步MemberBasic资料
		MemberBasic basic=this.memberBasicService.findMemberBasicByMemberId(id);
		if(basic!=null){
			MemberBasic targetBasic=new MemberBasic();
			BeanUtils.copyProperties(basic, targetBasic,new String[]{"id","createTime","updateTime","version"});
			targetBasic.setMemberId(targetId);
			targetBasic.setCopyed(true);
			targetBasic.setAsynced(false);
			this.memberBasicService.createMemberBasic(targetBasic);
		}
		//2、同步MemberCredit资料
		MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(id);
		if(credit!=null&&credit.getIdCard()!=null){
			MemberCredit targetCredit=new MemberCredit();
			BeanUtils.copyProperties(credit, targetCredit,new String[]{"id","createTime","updateTime","version"});
			targetCredit.setMemberId(targetId);
			targetCredit.setCopyed(true);
			targetCredit.setAsynced(false);
			this.memberCreditService.createMemberCredit(targetCredit);
		}
		//3、同步MemberBank资料
		List<MemberBank> bankList=this.memberBankService.findMemberBankByMemberId(id);
		if(!bankList.isEmpty()){
			for(MemberBank bank:bankList){
				MemberBank targetBank=new MemberBank();
				BeanUtils.copyProperties(bank, targetBank,new String[]{"id","createTime","updateTime","version"});
				targetBank.setMemberId(targetId);
				targetBank.setCopyed(true);
				targetBank.setAsynced(false);
				this.memberBankService.createMemberBank(targetBank);
			}
		}
		return targetMember;
	}

	@Override
	public MemberPassport updateMemberPassport(MemberPassport obj) throws EliteServiceException {
		this.verifyParams(obj);
		this.memberPassportRepository.save(obj);
		return obj;
	}
	
	@Override
	public void updatePassword(Long memberId,String passSalt,String password)throws EliteServiceException{
		this.verifyParams(memberId,passSalt,password);
		this.memberPassportRepository.updatePassword(memberId,passSalt,password);
	}

	@Override
	public void updateMemberLastLogin(Long memberId,String account,String lastLoginIp) throws EliteServiceException {
		this.verifyParams(memberId);
		//更新身份的登录次序
		List<MemberPassport> list=this.memberPassportRepository.queryMemberIdentityList(account);
		for(MemberPassport mp:list){
			Integer loginOrder=0;
			if(mp.getId().equals(memberId)){
				loginOrder=1;
				if(lastLoginIp!=null){
					mp.setLastLoginIp(lastLoginIp);
					mp.setLastLoginTime(new Date());
				}
			}
			logger.info("更新用户登录次序："+mp.getCurrentType()+":"+loginOrder);
			mp.setLoginOrder(loginOrder);
			this.memberPassportRepository.save(mp);
		}
	}

	@Override
	public void updateMemberCurrentType(Long memberId, String currentType) throws EliteServiceException {
		this.verifyParams(memberId);
		MemberPassport member = this.memberPassportRepository.find(memberId);
		if (member == null)
			throw new EliteServiceException("帐号不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		member.setCurrentType(currentType);
		this.memberPassportRepository.save(member);
	}

	@Override
	public void updateMemberPassword(Long memberId, String oldPass, String newPass) throws EliteServiceException {
		this.verifyParams(memberId, newPass);
		MemberPassport obj = this.memberPassportRepository.find(memberId);
		if (obj == null) {
			throw new EliteServiceException("帐号不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}
		if (StringUtils.isNotEmpty(oldPass)) {
			oldPass = PasswordUtils.getMD5PassWord(oldPass, obj.getPassSalt());
			if (!oldPass.equals(obj.getPassword())) {
				throw new EliteServiceException("原始密码错误", ErrorCodeEnum.ACCOUNT_PASS_FAULT.code);
			}
		}
		String passSalt = PasswordUtils.getAccountSalt();
		String password=PasswordUtils.getMD5PassWord(newPass, passSalt);
		obj.setPassSalt(passSalt);
		obj.setPassword(password);
		this.memberPassportRepository.save(obj);
		
		//同步更新多重身份
		MemberUpdatePass updatePass=new MemberUpdatePass();
		updatePass.setMemberId(obj.getId());
		updatePass.setAccount(obj.getAccount());
		updatePass.setPassSalt(passSalt);
		updatePass.setPassword(password);
		eventPublishService.publishMemberIdentityDate(null, null, null,updatePass);
	}

	@Override
	public void updateMemberPhone(Long memberId, String phone) throws EliteServiceException {
		this.verifyParams(memberId, phone);
		List<MemberPassport> list= this.memberPassportRepository.queryMemberIdentityList(phone);//检查新手机号
		if (!list.isEmpty()) {
			throw new EliteServiceException("此帐号已经存在", ErrorCodeEnum.OBJECT_EXIST.code);
		}
		MemberPassport pojo = this.memberPassportRepository.find(memberId);
		//检索需修改帐号的会员列表
		List<MemberPassport> updateList= this.memberPassportRepository.queryMemberIdentityList(pojo.getAccountOffSuffix());//检查新手机号
		for (MemberPassport mp:updateList) {
			String newPhone=phone+MemberPassport.typeToSuffix(mp.getCurrentType()).name();
			mp.setAccount(newPhone);
			this.memberPassportRepository.save(mp);
		}
	}

	@Override
	public void updateMemberViewCount(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		this.memberPassportRepository.updateMemberViewCount(memberId);
	}

	@Override
	public void updateMemberHomeShow(Long memberId, Boolean homeShow) throws EliteServiceException {
		this.verifyParams(memberId);
		MemberPassport obj = this.memberPassportRepository.find(memberId);
		if (obj == null) {
			throw new EliteServiceException("帐号不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}
		obj.setHomeShow(homeShow);
		this.memberPassportRepository.save(obj);
	}

	@Override
	public MemberPassport findMemberPassportByIdValidPassword(Long memberId, String password)
			throws EliteServiceException {
		this.verifyParams(memberId, password);
		MemberPassport obj = this.memberPassportRepository.find(memberId);
		if (obj == null) {
			throw new EliteServiceException("帐号不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}
		password = PasswordUtils.getMD5PassWord(password, obj.getPassSalt());
		if (!password.equals(obj.getPassword())) {
			throw new EliteServiceException("密码错误", ErrorCodeEnum.ACCOUNT_PASS_FAULT.code);
		}
		return obj;
	}

	@Override
	public boolean findMemberAccountIsExists(String account) throws EliteServiceException {
		this.verifyParams(account);
		if(account.indexOf("_")!=-1){
			return this.memberPassportRepository.searchUnique(new Search().addFilterEqual("account", account)) == null? false : true;
		}else{
			List<MemberPassport> list=this.memberPassportRepository.queryMemberIdentityList(account);
			return !list.isEmpty();
		}
	}

	@Override
	public MemberPassport findMemberPassportByAccount(String account) throws EliteServiceException {
		this.verifyParams(account);
		if (account.indexOf("@") != -1) {
			Search search=new Search();
			search.addFilterEqual("email", account);
			search.addSort("loginOrder", true);
			List<MemberPassport> list=this.memberPassportRepository.search(search);
			return list.isEmpty()?null:list.get(0);
		} else {
			//多账户模式加上下划线查询列表取出第一条(按最近登陆排序)
			List<MemberPassport> list=this.memberPassportRepository.queryMemberIdentityList(account);
			return list.isEmpty()?null:list.get(0);
			//return this.memberPassportRepository.searchUnique(new Search().addFilterEqual("account", account));
		}
	}

	@Override
	public MemberPassport findMemberPassportById(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		return this.memberPassportRepository.find(memberId);
	}

	@Override
	public MemberDetailInfo findMemberDetailInfoById(Long memberId) throws EliteServiceException {
		MemberPassport member = this.findMemberPassportById(memberId);
		if (member == null)
			return null;
		detailReplenish(member);
		MemberDetailInfo detail = new MemberDetailInfo(member);
		MemberAccount account = this.memberAccountService.findMemberAccountByMemberId(memberId);
		if (account == null)
			account = new MemberAccount();
		Integer handProjectCount = this.projectService.findProjectDoingCountBy(memberId);
		List<Project> plist = this.projectService.findProjectListByCompanyId(memberId);
		Integer publishProjectCount = plist.isEmpty() ? 0 : plist.size();
		detail.setHandProjectCount(handProjectCount);
		detail.setPublishProjectCount(publishProjectCount);
		detail.setTrustAmount(account.getTrustAmount());
		detail.setTotalIncome(account.getTotalIncome());
		return detail;
	}

	@Override
	public SearchResult<MemberPassport> findMemberPassportList(String type, String status, String keyword,
			String channel, boolean isStar, Date startTime, Date endTime, Pager pager) throws EliteServiceException {
		this.verifyParams(type, pager);
		SearchResult<MemberPassport> sr = new SearchResult<>();
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberPassportList(type, status, keyword,
				channel, isStar, startTime, endTime, pager);
		for (MemberPassport member : memberList) {
			detailReplenish(member);
		}
		Integer totalCount = this.memberPassportRepository.queryMemberPassportCount(type, status, keyword, channel,
				isStar, startTime, endTime);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@SuppressWarnings("incomplete-switch")
	private void detailReplenish(MemberPassport member) {
		Long memberId = member.getId();
		MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(memberId);
		MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(memberId);
		// 账户信息
		MemberAccount memberAccount = this.memberAccountService.findMemberAccountByMemberId(memberId);
		if (basic == null)
			basic = new MemberBasic();
		if (credit == null)
			credit = new MemberCredit();
		if (memberAccount == null)
			memberAccount = new MemberAccount();
		member.setBasic(basic);
		member.setCredit(credit);
		member.setMemberAccount(memberAccount);
		MemberIdentity_Type memberType = MemberIdentity_Type.valueOf(MemberIdentity_Type.class,
				member.getCurrentType());
		if (memberType.equals(MemberIdentity_Type.cto)) {
			memberType = MemberIdentity_Type.elite;
		}
		switch (memberType) {
		case company:
			MemberCompany memberCompany = this.memberCompanyService.findMemberCompanyByMemberId(memberId);
			member.setCompany(memberCompany);
			break;
		case elite:
			MemberElite memberElite = this.memberEliteService.findMemberEliteByMemberId(memberId);
			member.setElite(memberElite);
			List<MemberProjects> projects = this.memberProjectsService.findByMemberId(memberId);
			member.setProjects(projects);
			List<MemberEducation> educations = this.memberEducationService.findByMemberId(memberId, null);
			member.setEducations(educations);
			List<MemberSkill> skills = this.memberSkillService.findMemberSkillByMemberId(memberId);
			member.setSkills(skills);
			MemberCompany eliteCompany = this.memberCompanyService.findMemberCompanyByMemberId(memberId);
			member.setCompany(eliteCompany);
			List<MemberAttention> maList = this.memberAttentionService.findAttenTionedUsers(memberId);
			member.setFansCount(maList == null ? 0 : maList.size());
			break;
		case partnerCompany:
			MemberPartnerCompany partnerCompany = this.memberPartnerCompanyService
					.findMemberPartnerCompanyByMemberId(memberId);
			member.setPartnerCompany(partnerCompany);
			break;
		case partnerElite:
			MemberPartnerElite partnerElite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(memberId);
			member.setPartnerElite(partnerElite);
			break;
		}
	}

	@Override
	public void updateCloseMemberPassport(Long id, String status, double closeTime, String controlType,
			String closeReason, Long userId) throws EliteServiceException {
		this.verifyParams(id);
		MemberPassport member = this.memberPassportRepository.find(id);
		if (member == null)
			return;
		member.setStatus(Member_Status.valueOf(Member_Status.class, status));
		member.setCloseReason(closeReason);
		member.setCloseTime(closeTime);
		this.memberPassportRepository.save(member);

		PlatformWorkRecord record = new PlatformWorkRecord();
		record.setContent(closeReason);
		record.setForeignId(id);
		record.setType(WorkRecord_Type.closeMember.name());
		record.setUserId(userId);
		record.setStatus(status);
		record.setExt1(controlType);
		record.setExt2(closeTime + "");
		this.PlatformWorkRecordService.createPlatformWorkRecord(record);
	}

	@Override
	public SearchResult<MemberPassport> findMemberPassportListByMemberParnterElite(Long memberId, Date startTime,
			Date endTime, String realName, String searchType, Pager pager) throws EliteServiceException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		if (PartnerElite_Search_Type.no_recevieandcurrenttask_in_14days.name().equals(searchType)) {
			cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - 14);
		} else {
			cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - 7);
		}
		Date beforeServenDayTime = cal.getTime();
		SearchResult<MemberPassport> sr = new SearchResult<MemberPassport>();
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(memberId);
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberPassportListByPartnerElite(
				elite.getId(), startTime, endTime, realName, searchType, beforeServenDayTime, pager);
		for (int i = 0; i < memberList.size(); i++) {
			PartnerElite parElite = this.partnerEliteRepository
					.searchUnique(new Search().addFilterEqual("memberId", memberList.get(i).getId()));
			MemberElite memberElite = this.memberEliteService.findMemberEliteByMemberId(memberList.get(i).getId());
			memberList.get(i).setElite(memberElite);
			MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(memberList.get(i).getId());
			memberList.get(i).setCredit(credit);
			
			Search search = new Search();
			search.addFilterEqual("eliteMemberId", parElite.getMemberId());
			List<ProjectStageTask> tasks = this.projectStageTaskRepository.search(search);
			
			// 精英任务查询
			int tasksInCount = 0;
			for (int j = 0; j < tasks.size(); j++) {
				ProjectStageTask task = tasks.get(j);
				if (task.getStatus().equals(ProjectTask_Status.starting)
						|| task.getStatus().equals(ProjectTask_Status.wait_accept)||task.getStatus().equals(ProjectTask_Status.quality)) {
					tasksInCount++;
				}
			}
			// cto 项目查询
			if (memberElite != null && memberElite.isCtoed()) {
				List<Project> projects = this.projectService.findProjectListByctoId(parElite.getMemberId());
				for (int k = 0; k < projects.size(); k++) {
					Project project = projects.get(k);
					if (project.getStatus().equals(Project_Status.stage_course)||project.getStatus().equals(Project_Status.quality)) {
						tasksInCount++;
					}
				}
			}
			parElite.setTasksInCount(tasksInCount);

			// 查询订单时间，防止循环引用写在这里
			parElite.setLastRecruitTime(this.findLastRecruitTimeByMemberId(memberList.get(i).getId()));
			//
			memberList.get(i).setParElite(parElite);
		}

		Integer totalCount = this.memberPassportRepository.queryMemberPassportListByPartnerEliteCount(elite.getId(),
				startTime, endTime, realName, searchType, beforeServenDayTime);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public SearchResult<MemberPassport> findMemberPassportListByMemberParnter(String keyword, String type,
			String memberPartnerType,Date startTime,Date endTime, Pager pager, String status) throws EliteServiceException {
		this.verifyParams(memberPartnerType, pager);
		SearchResult<MemberPassport> sr = new SearchResult<>();
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberPassportListByMemberPartner(keyword,
				type, memberPartnerType, startTime,endTime, pager, status);
		for (MemberPassport member : memberList) {
			detailReplenish(member);
			MemberPartnerCompany company = member.getPartnerCompany();
			// 推荐人姓名
			if (company.getParentId() != null) {
				MemberPartnerCompany mem = this.memberPartnerCompanyService
						.findMemberPartnerCompanybyId(company.getParentId());
				company.setParentName(mem.getMemberPassport().getNickName());
			}
			if (company.getChargeId() != null) {
				SysUser user = this.sysUserService.findSysUserById(company.getChargeId());
				company.setChargeName(user.getUserName());
			}
			// 收益
			BigDecimal partnerIncome = memberIncomeRepository.findMemberIncomeSumByMemberId(member.getId(), null, null,null);
			// 入驻渠道数
			Integer enterParnterCount = partnerRecordRepository.findPartnerRecordListByKeywordCount(company.getId(),MemberIdentity_Type.partnerCompany.name(), PartnerRecord_Status.audit_pass.name(), null, null,null);
			// 备案渠道数
			Integer putParnterCount = partnerRecordRepository.findPartnerRecordListByKeywordCount(company.getId(),MemberIdentity_Type.partnerCompany.name(), null, null, null, null);
			company.setPartnerIncome(partnerIncome);
			company.setEnterParnterCount(enterParnterCount);
			company.setPutParnterCount(putParnterCount);
			member.setPartnerCompany(company);
		}
		Integer totalCount = this.memberPassportRepository.queryMemberPassportListByMemberPartnerCount(keyword, type,memberPartnerType, startTime,endTime, status);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}
	
	@Override
	public SearchResult<MemberPassport> findMemberParnterList(String keyword, String type,
			String memberPartnerType,Date startTime,Date endTime, Pager pager, String status) throws EliteServiceException {
		this.verifyParams(memberPartnerType, pager);
		SearchResult<MemberPassport> sr = new SearchResult<>();
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberPassportListByMemberPartner(keyword,
				type, memberPartnerType, startTime,endTime,pager, status);
		for (MemberPassport member : memberList) {
			detailReplenish(member);
			MemberPartnerElite elite = member.getPartnerElite();
			// 推荐人姓名
			if (elite.getParentId() != null) {
				MemberPartnerElite mem = this.memberPartnerEliteService.findMemberPartnerEliteById(elite.getParentId());
				elite.setParentName(mem==null?"":mem.getMemberPassport().getNickName());
			}
			if (elite.getChargeId() != null) {
				SysUser user = this.sysUserService.findSysUserById(elite.getChargeId());
				elite.setChargeName(user.getUserName());
			}
			// 收益
			BigDecimal partnerIncome = memberIncomeRepository.findMemberIncomeSumByMemberId(member.getId(), null, null,
					null);
			// 入驻渠道数
			Integer enterParnterCount = partnerRecordRepository.findPartnerRecordListByKeywordCount(elite.getId(),
					MemberIdentity_Type.partnerElite.name(), PartnerRecord_Status.audit_pass.name(), null, null, null);
			// 备案渠道数
			Integer putParnterCount =partnerRecordRepository.findPartnerRecordListByKeywordCount(elite.getId(),
					MemberIdentity_Type.partnerElite.name(), null, null, null, null);
			elite.setPartnerIncome(partnerIncome);
			elite.setEnterParnterCount(enterParnterCount);
			elite.setPutParnterCount(putParnterCount);
			member.setPartnerElite(elite);
		}
		Integer totalCount = this.memberPassportRepository.queryMemberPassportListByMemberPartnerCount(keyword, type,
				memberPartnerType,startTime,endTime,status);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}
	
	@Override
	public SearchResult<MemberPassport> findMemberPassportListByMemberElite(String keyword, Boolean ctoed,
			String status,Date startTime,Date endTime, Pager pager, String inviteCode,String role) throws EliteServiceException {
		this.verifyParams(ctoed);
		SearchResult<MemberPassport> sr = new SearchResult<>();
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberPassportListByMemberElite(keyword,
				ctoed, status, startTime,endTime, pager, inviteCode,role);
		for (MemberPassport member : memberList) {
			detailReplenish(member);
		}
		Integer totalCount = this.memberPassportRepository.queryMemberPassportListCountByMemberElite(keyword, ctoed,
				status,  startTime,endTime,inviteCode,role);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public SearchResult<MemberPassport> findMemberPassportListByEliteCircle(Long memberId, String keyword, String role,
			String jobAge, String duration, String industry, Pager pager) throws EliteServiceException {
		SearchResult<MemberPassport> sr = new SearchResult<>();
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberEliteCircleList(keyword, role,
				jobAge, duration, industry, pager);
		for (MemberPassport member : memberList) {
			detailReplenish(member);
			if (memberId != null) {
				MemberAttention maObj = this.memberAttentionService.checkAttentioned(memberId, member.getId());
				member.setAttentioned(maObj != null);
			}
		}
		Integer totalCount = this.memberPassportRepository.queryMemberEliteCircleCount(keyword, role, jobAge, duration,
				industry);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public SearchResult<MemberPassport> findMemberPassportListByMemberCompany(String keyword, String status,
			String currentType,Date startTime,Date endTime, Pager pager) throws EliteServiceException {
		SearchResult<MemberPassport> sr = new SearchResult<>();
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberPassportListByMemberCompany(keyword,
				status, startTime,endTime,pager);
		for (MemberPassport member : memberList) {
			detailReplenish(member);
		}
		Integer totalCount = this.memberPassportRepository.queryMemberPassportListCountByMemberCompany(keyword, status,startTime,endTime);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public SearchResult<MemberPassport> findMemberPassportListByCurrentType(String keyword, String status,
			String currentType, Pager pager, String orderType) throws EliteServiceException {
		this.verifyParams(currentType, pager);
		SearchResult<MemberPassport> sr = new SearchResult<>();
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberPassportListByCurrentType(keyword,
				currentType, status, pager, orderType);
		for (int i = 0; i < memberList.size(); i++) {
			MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(memberList.get(i).getId());
			MemberElite elite = this.memberEliteService.findMemberEliteByMemberId(memberList.get(i).getId());
			memberList.get(i).setCredit(credit);
			if (elite != null) {
				memberList.get(i).setElite(elite);
			}
		}
		Integer totalCount = this.memberPassportRepository.queryMemberPassportListCountByCurrentType(keyword,
				currentType, status);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public List<MemberPassport> findMemberPassportListForProject(String keyWord, Long projectId)
			throws EliteServiceException {
		this.verifyParams(projectId);
		return memberPassportRepository.findMemberPassportListForProject(keyWord, projectId);
	}

	@Override
	public List<MemberDetailInfo> findMemberEliteListAuditPass(Integer count) throws EliteServiceException {
		count = count == null ? 8 : count;
		List<MemberDetailInfo> resultList = new ArrayList<>();
		List<MemberPassport> memberList = this.memberPassportRepository.queryMemberEliteList(count);
		MemberDetailInfo detail = null;
		for (MemberPassport member : memberList) {
			detailReplenish(member);
			detail = new MemberDetailInfo(member);
			resultList.add(detail);
		}
		return resultList;
	}

	@Override
	public List<MemberPassport> findMemberpassPortCTOList(String keyword) throws EliteServiceException {
		List<MemberPassport> memberList = this.memberPassportRepository.queryCTOList(keyword);
		List<MemberPassport> resultList = new ArrayList<>();
		for (MemberPassport member : memberList) {
			MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(member.getId());
			if (credit != null) {
				member.setCredit(credit);
			}
			resultList.add(member);
		}
		return resultList;
	}

	@Override
	public List<MemberPassport> findMemberpassPortCompanyList(String keyword) throws EliteServiceException {
		Search search = new Search();
		if (StringUtils.isNotEmpty(keyword)) {
			search.addFilterOr(new Filter("memberNum", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("account", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("nickName", "%" + keyword + "%", Filter.OP_LIKE));
		}
		search.addFilterNotEqual("status", Member_Status.disabled);
		search.addFilterEqual("currentType", "company");
		List<MemberPassport> list = this.memberPassportRepository.search(search);
		return list;
	}

	public Integer findMemberEliteCount() throws EliteServiceException {
		return this.memberPassportRepository.queryMemberEliteCircleCount(null, null, null, null, null);
	}

	@Override
	public Integer findMemberCompanyCount(String status,Date startTime,Date endTime) throws EliteServiceException {
		// TODO Auto-generated method stub
		return this.memberPassportRepository.queryMemberPassportListCountByMemberCompany(null,status,startTime,endTime);
	}

	@Override
	public Integer findMemberPartnerCount(String memberPartnerType, String status,Date startTime,Date endTime) throws EliteServiceException {
		return this.memberPassportRepository.queryMemberPassportListByMemberPartnerCount(null, null, memberPartnerType,
				startTime,endTime, status);
	}

	@Override
	public MemberPassport updateDisableMemberPassport(MemberPassport obj) throws EliteServiceException {
		this.memberPassportRepository.save(obj);
		String type = obj.getCurrentType();
		if (type.equals(MemberIdentity_Type.cto.name()) || type.equals(MemberIdentity_Type.elite.name())) {
			MemberElite elite = this.memberEliteService.findMemberEliteByMemberId(obj.getId());
			if (elite != null) {
				elite.setStatus(obj.getStatus());
				this.memberEliteService.updateMemberElite(elite);
			}
		}
		return obj;

	}
	
	@Override
	public void deleteMember(Long id)throws EliteServiceException{
		this.verifyParams(id);
		MemberPassport member=this.memberPassportRepository.find(id);
		if(member==null)return;
		if(!Member_Status.waitAduit.equals(member.getStatus())){
			throw new EliteServiceException("只有待审核的用户可以删除",ErrorCodeEnum.ERROR.code);
		}else if(MemberIdentity_Type.company.name().equals(member.getCurrentType())){
			List<Project> list=this.projectService.findProjectListByCompanyId(id);
			if(!list.isEmpty()||list.size()>0){
				throw new EliteServiceException("该项目方已经发布了项目，不能删除",ErrorCodeEnum.ERROR.code);
			}
		}
		this.memberPassportRepository.deleteByMemberId(id);
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
	public List<MemberPassport> findMoreMemberIdentiyList(String account) {
		List<MemberPassport> list=this.memberPassportRepository.queryMemberIdentityList(MemberPassport.accountOffSuffix(account));
		Iterator<MemberPassport> it=list.iterator();
		while(it.hasNext()){
			MemberPassport member=it.next();
			if(member.getAccount().equals(account)){
				it.remove();
			}
		}
		return list;
	}
	
	
}
