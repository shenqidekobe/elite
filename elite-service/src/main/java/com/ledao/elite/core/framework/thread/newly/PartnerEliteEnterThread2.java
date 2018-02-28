package com.ledao.elite.core.framework.thread.newly;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Status;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerEliteService;
import com.ledao.elite.core.utils.SpringContextUtil;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目猎头方入驻2.0
 * */
@Slf4j
public class PartnerEliteEnterThread2 implements Runnable{
	
	@Setter
	private Long memberId;//成功入驻的会员ID
	@Setter
	private String account;//入驻会员帐号
	
	private MemberPartnerEliteService memberPartnerEliteService;
	private MemberPassportService memberPassportService;
	private PartnerEliteService partnerEliteService;
	
	public PartnerEliteEnterThread2(){
		memberPartnerEliteService=(MemberPartnerEliteService)SpringContextUtil.getBean("memberPartnerEliteService");
		memberPassportService=(MemberPassportService)SpringContextUtil.getBean("memberPassportService");
		partnerEliteService=(PartnerEliteService)SpringContextUtil.getBean("partnerEliteService");
	}
	
	@Override
	public void run() {
		log.info("**********************************渠道猎头方精英入驻线程开始处理**********************************");
		if(StringUtils.isEmpty(account)||memberId==null)
			return;
		//查询入驻会员的信息
		PartnerElite elite=this.partnerEliteService.findPartnerEliteBymemberId(memberId);
		if(elite==null)
			return;
		if(!elite.getStatus().equals(PartnerElite_Status.registered)){
			return;
		}
		elite.setStatus(PartnerElite_Status.audit_pass);
		elite.setAuditTime(new Date());
		if(elite.getName()==null){
			MemberPassport member=this.memberPassportService.findMemberDetailInfoById(memberId);
			elite.setName(member.getNickName());
			elite.setAreaName(member.getBasic().getAreaName());
			elite.setJobAge(member.getElite().getJobAge());
			elite.setEmail(member.getBasic().getEmail());
		}
		this.partnerEliteService.updatePartnerElite(elite);
		MemberPartnerElite mpe=this.memberPartnerEliteService.findMemberPartnerEliteById(elite.getPartnerId());
		try {
			Integer enterCount=mpe.getEnterCount()+1;
			Integer putCount=mpe.getPutCount();
			Integer calculateEliteCount=mpe.getCalculateEliteCount()==null?1:mpe.getCalculateEliteCount()+1;
			this.memberPartnerEliteService.updateEliteInfoCount(mpe.getId(),putCount,enterCount,calculateEliteCount);
			log.info("******************人才渠道方："+mpe.getId()+"其手机号："+account+",已成功入驻人才数量："+enterCount+"个");
		} catch (Exception e) {}
	}
	
}
