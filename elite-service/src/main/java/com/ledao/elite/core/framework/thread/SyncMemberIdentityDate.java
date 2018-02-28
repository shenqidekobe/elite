package com.ledao.elite.core.framework.thread;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.framework.dto.MemberUpdatePass;
import com.ledao.elite.core.service.member.MemberBankService;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberPassportService;

import lombok.extern.slf4j.Slf4j;

/**
 * 托管费用同步更新CTO的项目阶段
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Slf4j
@Component
public class SyncMemberIdentityDate{
	
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private MemberBankService memberBankService;

	public void SyncMemberIdentity(MemberBank bank,MemberBasic basic,MemberCredit credit,MemberUpdatePass updatePass) {
		log.info("同步更新会员基础数据basic："+basic+",credit:"+credit+",bank:"+bank+",updatePass:"+updatePass);
		if(basic!=null){
			MemberPassport member = memberPassportService.findMemberPassportById(basic.getMemberId());
			List<MemberPassport> list = memberPassportService.findMoreMemberIdentiyList(member.getAccount());
			if(list.size()==0)return;
			for(MemberPassport membert:list){
				if(membert.getId().equals(member.getId())){
                	log.info("会员【"+member.getId()+"】基本信息不参与更新******************");
					continue;
				}
				MemberBasic targetBasic=this.memberBasicService.findMemberBasicByMemberId(membert.getId());
				if(targetBasic==null){
					targetBasic = new MemberBasic();
				}
				BeanUtils.copyProperties(basic, targetBasic,new String[] { "memberId","id"});
				targetBasic.setCopyed(true);
				targetBasic.setMemberId(membert.getId());
				log.info("同步更新MemberBasic【"+targetBasic.getId()+",memberId="+targetBasic.getMemberId()+"】");
				this.memberBasicService.syncUpdateMemberBasic(targetBasic);
			}
		}
		if(credit!=null){
			MemberPassport member = memberPassportService.findMemberPassportById(credit.getMemberId());
			List<MemberPassport> list = memberPassportService.findMoreMemberIdentiyList(member.getAccount());
			if(list.size()==0)return;
			for(MemberPassport membert:list){
				if(membert.getId().equals(member.getId())){
					log.info("会员【"+member.getId()+"】征信信息不参与更新******************");
					continue;
				}
				MemberCredit targetCredit=this.memberCreditService.findMemberCreditByMemberId(membert.getId());
				if(targetCredit==null){
					targetCredit = new MemberCredit();
				}
				BeanUtils.copyProperties(credit, targetCredit,new String[] { "memberId","id"});
				targetCredit.setCopyed(true);
				targetCredit.setMemberId(membert.getId());
				log.info("同步更新MemberCredit【"+targetCredit.getId()+",memberId="+targetCredit.getMemberId()+"】");
				this.memberCreditService.syncUpdateMemberCredit(targetCredit);
			}
		}
		if(bank!=null){
			MemberPassport member = memberPassportService.findMemberPassportById(bank.getMemberId());
			List<MemberPassport> list = memberPassportService.findMoreMemberIdentiyList(member.getAccount());
			if(list.size()==0)return;
			for(MemberPassport membert:list){
				if(membert.getId().equals(member.getId())){
					log.info("会员【"+member.getId()+"】银行卡信息不参与更新******************");
					continue;
				}
				List<MemberBank> targetbankList=this.memberBankService.findMemberBankByMemberId(membert.getId());
				MemberBank targetMemberBank = new MemberBank();
				for(MemberBank targetBank:targetbankList){
					if(targetBank.getBankCard().equals(bank.getBankCard())){
						targetMemberBank = targetBank;
						break;
					}
				}
				BeanUtils.copyProperties(bank, targetMemberBank,new String[] { "memberId","id"});
				targetMemberBank.setCopyed(true);
				targetMemberBank.setMemberId(membert.getId());
				log.info("同步更新MemberBank【"+targetMemberBank.getId()+",memberId="+targetMemberBank.getMemberId()+"】");
				this.memberBankService.syncUpdateMemberBank(targetMemberBank);
			}
		}
		if(updatePass!=null){
			String account=updatePass.getAccount();
			List<MemberPassport> list = memberPassportService.findMoreMemberIdentiyList(account);
			if(list.size()==0)return;
			if(updatePass.getNickName()==null&&updatePass.getEmail()==null){
				//修改密码
				for(MemberPassport member:list){
					if(!member.getAccount().equals(account)){
						this.memberPassportService.updatePassword(member.getId(), updatePass.getPassSalt(), updatePass.getPassword());
					}
				}
			}else{
				//修改昵称
				for(MemberPassport member:list){
					if(!member.getAccount().equals(account)){
						if(updatePass.getEmail()!=null){
							member.setEmail(updatePass.getEmail());
						}
						if(updatePass.getNickName()!=null){
							member.setNickName(updatePass.getNickName());
						}
						this.memberPassportService.updateMemberPassport(member);
					}
				}
			}
		}
	}	
}
