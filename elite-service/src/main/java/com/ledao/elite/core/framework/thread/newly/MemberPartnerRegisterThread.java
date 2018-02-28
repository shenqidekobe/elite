package com.ledao.elite.core.framework.thread.newly;

import org.apache.commons.lang3.StringUtils;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Way;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Status;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Type;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerEliteService;
import com.ledao.elite.core.service.partner.PartnerProjectService;
import com.ledao.elite.core.service.partner.PartnerRecordService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.utils.SpringContextUtil;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 渠道方注册 包含（人才渠道，项目渠道）
 */
@Slf4j
public class MemberPartnerRegisterThread implements Runnable {

	@Setter
	private Long memberId;// 成功入驻的会员ID
	@Setter
	private String account;// 入驻会员帐号
	@Setter
	private String inviteCode;// 邀请码
	@Setter
	private String invitePhone;// 邀请手机号
	@Setter
	private String inviteMemberPartnerType;// 邀请
	@Setter
	private MemberPartnerCompany sonPartnerCompany;// 项目渠道
	@Setter
	private MemberPartnerElite sonPartnerElite;// 人才渠道

	private MemberPartnerEliteService memberPartnerEliteService;
	private MemberPartnerCompanyService memberPartnerCompanyService;
	private PartnerRecordService partnerRecordService;
	private MemberPassportService memberPassportService;
	private PlatformInviteCodeService platformInviteCodeService;
	protected PartnerEliteService partnerEliteService;
	protected PartnerProjectService partnerProjectService;

	public MemberPartnerRegisterThread() {
		memberPartnerEliteService = (MemberPartnerEliteService) SpringContextUtil.getBean("memberPartnerEliteService");
		memberPartnerCompanyService = (MemberPartnerCompanyService) SpringContextUtil
				.getBean("memberPartnerCompanyService");
		memberPassportService = (MemberPassportService) SpringContextUtil.getBean("memberPassportService");
		partnerRecordService = (PartnerRecordService) SpringContextUtil.getBean("partnerRecordService");
		platformInviteCodeService = (PlatformInviteCodeService) SpringContextUtil.getBean("platformInviteCodeService");
		partnerEliteService = (PartnerEliteService) SpringContextUtil.getBean("partnerEliteService");
		partnerProjectService = (PartnerProjectService) SpringContextUtil.getBean("partnerProjectService");
	}

	@Override
	public void run() {
		PartnerRecord_Type partnerType=PartnerRecord_Type.valueOf(PartnerRecord_Type.class,inviteMemberPartnerType);
		log.info("**********************************渠道方推荐的【"+partnerType+"的帐号："+account+"】入驻线程开始处理**********************************");
		PartnerRecord record = this.partnerRecordService.findPartnerRecordByAccountAndStatus(account,partnerType);
		// 填写邀请码 邀请手机号情况
		if (!StringUtils.isEmpty(inviteCode) || invitePhone != null) {
			Long partnerMemberId = null;
			if (!StringUtils.isEmpty(inviteCode)) {
				PlatformInviteCode partnerInviteCode = this.platformInviteCodeService
						.findPlatformInviteCodeByCode(inviteCode);
				if (partnerInviteCode == null) {
					return;
				} else if (inviteMemberPartnerType.equals(MemberIdentity_Type.partnerElite.name())
						&& !InviteCode_Type.channel_elite.equals(partnerInviteCode.getType())) {
					// 非渠道功能
					return;
				} else if (inviteMemberPartnerType.equals(MemberIdentity_Type.partnerCompany.name())
						&& !InviteCode_Type.channel_company.equals(partnerInviteCode.getType())) {
					return;
				}
				partnerMemberId = partnerInviteCode.getUserId();
			} else {
				MemberPassport partnerPassport = this.memberPassportService.findMemberPassportByAccount(invitePhone);
				if (partnerPassport == null) {
					return;
				}
				partnerMemberId = partnerPassport.getId();
			}
			Long partnerId;
			if (inviteMemberPartnerType.equals(MemberIdentity_Type.partnerCompany.name())) {
				MemberPartnerCompany partnerCompany = this.memberPartnerCompanyService
						.findMemberPartnerCompanyByMemberId(partnerMemberId);
				partnerId = partnerCompany.getId();
				
				sonPartnerCompany.setParentId(partnerId);
				this.memberPartnerCompanyService.updateMemberPartnerCompanyPartnerId(partnerId,sonPartnerCompany.getId());
			} else {
				MemberPartnerElite partnerElite = this.memberPartnerEliteService
						.findMemberPartnerEliteByMemberId(partnerMemberId);
				partnerId = partnerElite.getId();
				sonPartnerElite.setParentId(partnerId);
				this.memberPartnerEliteService.updateMemberPartnerElitePartnerId(partnerId, sonPartnerElite.getId());
			}

			if (record == null) {
				record = new PartnerRecord();
				record.setParentId(partnerId);
				record.setMemberId(memberId);
				record.setStatus(PartnerRecord_Status.registered);
				record.setEnterWay(PartnerElite_Way.invite);
				record.setMemberPhone(account);
			} else {
				
				if (record.getParentId().equals(partnerId)) {
					record.setMemberId(memberId);
					record.setEnterWay(PartnerElite_Way.put);
					record.setStatus(PartnerRecord_Status.registered);
				} else {
					// 新增情况 备案人和邀请码（邀请手机号）不属于同一个人 以邀请码为主，备案人列表显示状态为备案失败状态
					//备案失败
                    record.setStatus(PartnerRecord_Status.audit_nopass);
                   //插入到新的备案列表中
                   
                   PartnerRecord realRecord = new PartnerRecord();
                   realRecord.setParentId(partnerId);
                   realRecord.setMemberId(memberId);
                   realRecord.setStatus(PartnerRecord_Status.registered);
                   realRecord.setEnterWay(PartnerElite_Way.invite);
                   realRecord.setMemberPhone(account);
                   realRecord.setPartnerType(PartnerRecord_Type.valueOf(PartnerRecord_Type.class, inviteMemberPartnerType));
       			   this.partnerRecordService.updatePartnerRecordInfoNoFixed(realRecord);
				}
			}
			record.setPartnerType(PartnerRecord_Type.valueOf(PartnerRecord_Type.class, inviteMemberPartnerType));
			this.partnerRecordService.updatePartnerRecordInfoNoFixed(record);
		}
		// 注册手机号 在推荐列表中
		else if (record != null) {
			record.setMemberId(memberId);
			record.setStatus(PartnerRecord_Status.registered);
			record.setEnterWay(PartnerElite_Way.put);
			record.setPartnerType(PartnerRecord_Type.valueOf(PartnerRecord_Type.class, inviteMemberPartnerType));
			this.partnerRecordService.updatePartnerRecordInfoNoFixed(record);
			
			if (inviteMemberPartnerType.equals(MemberIdentity_Type.partnerCompany.name())) {
				sonPartnerCompany.setParentId(record.getParentId());
				this.memberPartnerCompanyService.updateMemberPartnerCompanyPartnerId(record.getParentId(),sonPartnerCompany.getId());
			} else {
				sonPartnerElite.setParentId(record.getParentId());
				this.memberPartnerEliteService.updateMemberPartnerElitePartnerId(record.getParentId(), sonPartnerElite.getId());
			}
		}
		log.info("**********************************渠道方推荐入驻线程处理结束**********************************");
		//增加情况  看是否在备案项目或者备案精英中，如果在，显示备案失败
		//2016-12-21更新为：帐号切换功能调整，号码的角色可以多个备案
		/*PartnerElite partnerElite=this.partnerEliteService.findPartnerEliteByAccount(account);
        if(partnerElite!=null){
        	this.partnerEliteService.updatePartnerEliteStatusBysql(PartnerElite_Status.audit_nopass.name(), partnerElite.getId());
        }
       
        PartnerProject partnerCompany=this.partnerProjectService.findPartnerProjectAsEnter(account, null, null, null);
        if(partnerCompany!=null){
        	this.partnerProjectService.updatePartnerCompanyStatuaBysql(PartnerProject_Status.audit_nopass.name(), partnerCompany.getId());
        }*/
	}

}
