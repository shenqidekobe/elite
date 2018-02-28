package com.ledao.elite.core.service.member.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberEliteJobs;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.repository.member.MemberEliteRepository;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberEliteJobsService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberIdentityService;
import com.ledao.elite.core.service.member.MemberProjectsService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;

@Service
public class MemberEliteServiceImpl extends BaseSerivceImpl implements MemberEliteService {

	@Resource
	private MemberEliteRepository memberEliteRepository;
	@Resource
	private MemberEliteJobsService memberEliteJobsService;
	@Resource
	private MemberPassportRepository memberPassportRepository;
	@Resource
	private MemberIdentityService memberIdentityService;
	@Resource
	private PlatformInviteCodeService platformInviteCodeService;
	@Resource
	private EventPublishService eventPublishService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private MemberProjectsService memberProjectsService;

	@Override
	public MemberElite createMemberElite(MemberElite obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getMemberId());
		// 初始创建会员为待审核状态
		obj.setStatus(MemberPassport.Member_Status.waitAduit);
		this.memberEliteRepository.save(obj);
		return obj;
	}

	@Override
	public MemberElite updateMemberEliteCurrentInfo(Long memberId, MemberElite obj) throws EliteServiceException {
		this.verifyParams(memberId, obj);
		MemberElite pojo = this.findMemberEliteByMemberId(memberId);
		if (pojo == null)
			return null;
		pojo.setJobAge(obj.getJobAge());
		if (obj.getAttentionIndustry() != null) {
			pojo.setAttentionIndustry(obj.getAttentionIndustry());
		}
		pojo.setAllotDuration(obj.getAllotDuration());
		pojo.setOnjobed(obj.isOnjobed());
		if (obj.isCtoSigned()) {
			pojo.setCtoSigned(obj.isCtoSigned());
		}
		MemberEliteJobs eliteJob = null;
		if (pojo.getEliteJobs().size() > 0) {
			eliteJob = pojo.getEliteJobs().get(0);
		}
		Map<String, String> eliteJobMap = obj.getEliteJobMap();
		if (eliteJobMap != null && !eliteJobMap.isEmpty()) {
			Long eliteId = pojo.getId();

			this.memberEliteJobsService.removeMemberEliteJobsByEliteId(eliteId);
			for (String jobRole : eliteJobMap.keySet()) {
				String jobAdept = eliteJobMap.get(jobRole);
				if (jobAdept.endsWith(",")) {
					jobAdept = StringUtils.chop(jobAdept);
				}
				if (",".equals(jobAdept)) {
					jobAdept = "";
				}
				MemberEliteJobs job = new MemberEliteJobs();
				job.setEliteId(eliteId);
				job.setJobRole(jobRole);
				job.setJobAdept(jobAdept);
				if (eliteJob != null) {
					job.setUpdateRoleDate(eliteJob.getUpdateRoleDate());
					// 如果角色改变时
					if (!eliteJob.getJobRole().equals(jobRole)) {
						pojo.setStatus(Member_Status.aduitIn);
						job.setUpdateRoleDate(new Date());
					}
				}
				this.memberEliteJobsService.createMemberEliteJobs(job);
			}
		}
		this.memberEliteRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberElite updateMemberEliteInfo(Long memberId, MemberElite obj) throws EliteServiceException {
		this.verifyParams(memberId, obj);
		MemberElite pojo = this.findMemberEliteByMemberId(memberId);
		if (pojo == null)
			return null;
		if (StringUtils.isNotEmpty(obj.getAttentionIndustry())) {
			pojo.setAttentionIndustry(obj.getAttentionIndustry());
		}
		if (obj.getResumeAttaId() != null) {
			pojo.setResumeAttaId(obj.getResumeAttaId());
		}
		pojo.setIntro(obj.getIntro());
		this.memberEliteRepository.save(pojo);
		if (StringUtils.isNotEmpty(obj.getInviteCode())) {
			MemberPassport mp = this.memberPassportRepository.find(memberId);
			mp.setInviteCode(obj.getInviteCode());
			this.memberPassportRepository.save(mp);
		}
		return pojo;
	}

	@Override
	public MemberElite updateMemberEliteStatus(Long memberId, String status) throws EliteServiceException {
		MemberElite pojo = this.findMemberEliteByMemberId(memberId);
		if (pojo == null)
			return null;
		pojo.setStatus(Member_Status.valueOf(Member_Status.class, status));
		this.memberEliteRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberElite updateMemberEliteAudit(Long memberId, Long auditId, String auditReason, String status,
			Boolean ctoSigned) throws EliteServiceException {
		MemberElite pojo = this.findMemberEliteByMemberId(memberId);
		if (pojo == null)
			return null;
		String formerStatus=pojo.getStatus().name();
     	pojo.setAuditId(auditId);
		pojo.setAuditReason(auditReason);
		pojo.setAuditTime(new Date());
		pojo.setStatus(Member_Status.valueOf(Member_Status.class, status));
		pojo.setCtoSigned(ctoSigned);
		if (ctoSigned&&status.equals(Member_Status.normal.name())) {
			pojo.setCtoed(true);
		}
		this.memberEliteRepository.save(pojo);
		if (status.equals(Member_Status.auditNotPass.name())) {
			List<MemberEliteJobs> jobs = pojo.getEliteJobs();
			if (jobs.size() > 0) {
				MemberEliteJobs job = jobs.get(0);
				if (job.getUpdateRoleDate() != null) {
					job.setUpdateRoleDate(null);
					memberEliteJobsService.updateMemberEliteJobs(job);
				}
			}
		}
		if (status.equals(Member_Status.normal.name()) && ctoSigned) {
			MemberPassport member = this.memberPassportRepository.find(memberId);
			member.setCurrentType(MemberIdentity_Type.cto.name());
			member.setStatus(Member_Status.normal);
			this.memberPassportRepository.save(member);
			memberIdentityService.updateMemberIdentity(memberId, MemberIdentity_Type.elite, MemberIdentity_Type.cto);
		}
		// 发邀请码
		if (status.equals(Member_Status.normal.name())&&!Member_Status.normal.name().equals(formerStatus)) {
			PlatformInviteCode code = new PlatformInviteCode();
			code.setUserId(memberId);
			code.setType(InviteCode_Type.platform_one);
			code.setMaxCount(1);
			PlatformInviteCode obj = this.platformInviteCodeService.createPlatformInviteCode(code);
			logger.debug(code.getInviteCode()+"==="+obj.getInviteCode());

			// 发送通知邀请码
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_RECEIVE_INVITECODE);
			String content = String.format(message.getValue(), obj.getInviteCode());
			eventPublishService.publishMessageEvent(memberId, null, null, message.getKey(), content, false,
					MemberMessage_Type.system);
		}
		return pojo;
	}

	@Override
	public MemberElite updateMemberEliteAuditCto(Long memberId, Long auditId, String auditReason, boolean isCto,
			String status) throws EliteServiceException {
		MemberElite pojo = this.findMemberEliteByMemberId(memberId);
		if (pojo == null)
			return null;
		pojo.setAuditCtoId(auditId);
		pojo.setAuditCtoReason(auditReason);
		pojo.setStatus(Member_Status.valueOf(Member_Status.class, status));
		pojo.setAuditCtoTime(new Date());
		pojo.setCtoed(isCto);
		this.memberEliteRepository.save(pojo);
		// TODO:更新会员身份信息
		memberIdentityService.updateMemberIdentity(memberId, MemberIdentity_Type.elite, MemberIdentity_Type.cto);
		return pojo;
	}

	@Override
	public MemberElite findMemberEliteByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		return this.memberEliteRepository.searchUnique(new Search().addFilterEqual("memberId", memberId));
	}
	@Override
	public MemberElite findMemberEliteById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberEliteRepository.find(id);
	}

	@Override
	public MemberElite updateMemberElite(MemberElite obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getMemberId());
		this.memberEliteRepository.save(obj);
		return obj;
	}

	@Override
	public Map<String, Integer> findMemberEliteStatusCount(boolean ctoed, String status,Date startTime,Date endTime) throws EliteServiceException {
		Search search=new Search();
		search.addFilterEqual("ctoed", ctoed);
		if (startTime != null) {
			search.addFilterGreaterOrEqual("createTime", startTime);
		}
		if (endTime != null) {
			search.addFilterLessOrEqual("createTime",endTime);
		}
		List<MemberElite> elites = this.memberEliteRepository.search(search);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("allCount", elites.size());
		int normalCount = 0;
		for (int i = 0; i < elites.size(); i++) {
			if (Member_Status.normal.equals(elites.get(i).getStatus())) {
				normalCount++;
			}
		}
		map.put(Member_Status.normal.name(), normalCount);
		return map;

	}

	@Override
	public Map<String,String> findMemberEliteNecessaryNull(Long memberId) throws EliteServiceException {
		// TODO Auto-generated method stub
		this.verifyParams(memberId);
		Map<String,String> map = new HashMap<String,String>();
		MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(memberId);
		if (basic != null) {
			if (basic.getBirthday() == null) {
				map.put("birthday", "出生年月");
			}
			if (basic.getAreaName() == null) {
				map.put("areaName", "城市");
			}
			if (basic.getEmail() == null) {
				map.put("email", "绑定邮箱");
			}
			if (basic.getBirthday() == null) {
				map.put("memberSign", "职业头衔");
			}
		} else {
			map.put("birthday", "出生年月");
			map.put("areaName", "城市");
			map.put("email", "绑定邮箱");
			map.put("memberSign", "职业头衔");
		}

		MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(memberId);
		if (credit != null) {
			if (credit.getRealName() == null) {
				map.put("realName", "姓名");
			}
			if (credit.getIdCard() == null) {
				map.put("idCard", "身份证号");
			}
			if (credit.getCardReverse() == null) {
				map.put("cardReverse", "身份证反面");
			}
			if (credit.getCardJust() == null) {
				map.put("cardJust", "身份证正面");
			}
		} else {
			map.put("realName", "姓名");
			map.put("idCard", "身份证号");
			map.put("cardReverse", "身份证反面");
			map.put("cardJust", "身份证正面");
		}
    
		MemberElite elite=this.findMemberEliteByMemberId(memberId);
		if(elite!=null){
			if (elite.getAllotDuration() == null) {
				map.put("allotDuration", "每周可分配时长");
			}
			if (elite.getJobAge() == null) {
				map.put("jobAge", "相关工作年限");
			}
			if (elite.getAttentionIndustry() == null) {
				map.put("attentionIndustry", "关注行业");
			}
			if (elite.getEliteJobs() == null||elite.getEliteJobs().size()<=0) {
				map.put("eliteJobs", "职位角色");
			}
		}
		List<MemberProjects> memberProject=this.memberProjectsService.findByMemberId(memberId);
		if(memberProject==null||memberProject.size()<=0){
			map.put("memberProjects", "项目经历");
		}
		return map;
	}

}
