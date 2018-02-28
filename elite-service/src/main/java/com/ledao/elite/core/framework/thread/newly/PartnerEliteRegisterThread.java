package com.ledao.elite.core.framework.thread.newly;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Status;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Way;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.partner.PartnerEliteService;
import com.ledao.elite.core.service.partner.PartnerRecordService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.utils.SpringContextUtil;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 渠道猎头方精英入驻清算 注意：以用户填写的邀请码为主
 */
@Slf4j
public class PartnerEliteRegisterThread implements Runnable {

	@Setter
	private Long memberId;// 成功入驻的会员ID
	@Setter
	private String account;// 入驻会员帐号
	@Setter
	private String inviteCode;// 邀请码
	@Setter
	private String email;// 邮箱

	private MemberPartnerEliteService memberPartnerEliteService;
	private PartnerEliteService partnerEliteService;
	private PlatformInviteCodeService platformInviteCodeService;
	protected PartnerRecordService partnerRecordService;

	public PartnerEliteRegisterThread() {
		memberPartnerEliteService = (MemberPartnerEliteService) SpringContextUtil.getBean("memberPartnerEliteService");
		partnerEliteService = (PartnerEliteService) SpringContextUtil.getBean("partnerEliteService");
		platformInviteCodeService = (PlatformInviteCodeService) SpringContextUtil.getBean("platformInviteCodeService");
		partnerRecordService = (PartnerRecordService) SpringContextUtil.getBean("partnerRecordService");
	}

	@Override
	public void run() {
		log.info("**********************************人才渠道方入驻线程开始处理**********************************");
		if (StringUtils.isEmpty(account) || memberId == null)
			return;
		// 查询入驻会员的信息
		PartnerElite partnerElite = null;
		MemberPartnerElite mpe = null;
		boolean updatePut = false;
		boolean accountInviteType = false;
		if (StringUtils.isNotEmpty(inviteCode)) {
			PlatformInviteCode partnerInviteCode = this.platformInviteCodeService
					.findPlatformInviteCodeByCode(inviteCode);
			if (partnerInviteCode == null) {
				return;
			} else if (InviteCode_Type.channel_elite.equals(partnerInviteCode.getType())) {
				// 非渠道精英不功能
				mpe = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(partnerInviteCode.getUserId());
				if (mpe == null)
					return;
				partnerElite = this.partnerEliteService.findPartnerEliteByAccount(account);
				if (partnerElite == null) {
					// 邀请码入驻如果未备案，需要插入一条备案信息
					partnerElite = new PartnerElite();
					partnerElite.setPartnerId(mpe.getId());
					partnerElite.setStatus(PartnerElite_Status.registered);
					partnerElite.setEnterWay(PartnerElite_Way.invite);
					partnerElite.setPhone(account);
					partnerElite.setEmail(email);
					partnerElite.setMemberId(memberId);
					partnerElite.setAuditTime(new Date());
					this.partnerEliteService.createPartnerElite(partnerElite);
					updatePut = true;
				} else {
					
					//输入邀请码和备案渠道方的邀请码不一样的时候
					if (partnerElite.getPartnerId().equals(mpe.getId())) {
						partnerElite.setMemberId(memberId);
						partnerElite.setEnterWay(PartnerElite_Way.put);
						partnerElite.setStatus(PartnerElite_Status.registered);
					}else{
						//备案失败
						partnerElite.setStatus(PartnerElite_Status.audit_nopass);
						
					    PartnerElite realpartnerElite = new PartnerElite();
						realpartnerElite.setPartnerId(mpe.getId());
						realpartnerElite.setStatus(PartnerElite_Status.registered);
						realpartnerElite.setEnterWay(PartnerElite_Way.invite);
						realpartnerElite.setPhone(account);
						realpartnerElite.setEmail(email);
						realpartnerElite.setMemberId(memberId);
						realpartnerElite.setAuditTime(new Date());
						this.partnerEliteService.createPartnerEliteNoVerifyParams(realpartnerElite);
						updatePut = true;
					}
					
					this.partnerEliteService.updatePartnerElite(partnerElite);
				}
			}
			// 邀请码非渠道方的情况
			else {
				accountInviteType = true;
			}

		}
		// 邀请码为空，或者邀请码为非渠道方邀请码的时候
		if (StringUtils.isEmpty(inviteCode) || accountInviteType) {
			partnerElite = this.partnerEliteService.findPartnerEliteByAccount(account);
			if (partnerElite == null)
				return;
			mpe = this.memberPartnerEliteService.findMemberPartnerEliteById(partnerElite.getPartnerId());
			if (mpe == null)
				return;
			partnerElite.setMemberId(memberId);
			partnerElite.setEnterWay(PartnerElite_Way.put);
			partnerElite.setStatus(PartnerElite_Status.registered);
			this.partnerEliteService.updatePartnerElite(partnerElite);
		}
		try {
			Integer enterCount = mpe.getEnterCount();
			Integer putCount = mpe.getPutCount();
			if (updatePut) {
				putCount = putCount + 1;
			}
			this.memberPartnerEliteService.updateEliteInfoCount(mpe.getId(), putCount, enterCount,mpe.getCalculateEliteCount());
			log.info("******************人才渠道方：" + mpe.getId() + "入驻了邀请码：" + inviteCode + "，其手机号：" + account
					+ ",已成功入驻人才数量：" + enterCount + "个");
		} catch (Exception e) {
		}
		
		//如果在渠道方，渠道方现实备案失败
		/*PartnerRecord partnerRecord=this.partnerRecordService.findPartnerRecordByPhone(account);
		if(partnerRecord!=null){
			this.partnerRecordService.updatePartnerRecordStatusBySql(PartnerRecord_Status.audit_nopass.name(), partnerRecord.getId());
		}*/
		
	}

}
