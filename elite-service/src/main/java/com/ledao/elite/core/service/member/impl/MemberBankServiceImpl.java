package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBank.MemberBank_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.System_Status;
import com.ledao.elite.core.repository.member.MemberBankRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberBankService;

@Service("memberBankService")
public class MemberBankServiceImpl extends BaseSerivceImpl implements MemberBankService{
	
	@Resource
	private MemberBankRepository memberBankRepository;
	@Resource
	private EventPublishService eventPublishService;
	
	
	@Override
	public MemberBank syncUpdateMemberBank(MemberBank bank) throws EliteServiceException {
		this.verifyParams(bank,bank.getMemberId());
		if(bank.isCopyed()){
			this.memberBankRepository.save(bank);
		}
		return bank;
	}

	@Override
	public MemberBank createMemberBank(MemberBank bank) throws EliteServiceException {
		this.verifyParams(bank,bank.getMemberId(),bank.getBankCard(),bank.getType());
		if(!bank.isCopyed()){
			Search search1=new Search();
			search1.addFilterEqual("bankCard", bank.getBankCard());
			Integer cardCount=this.memberBankRepository.count(search1);
			if(cardCount>0){
				throw new EliteServiceException("此卡号已被绑定", ErrorCodeEnum.OBJECT_EXIST.code);
			}
			Search search=new Search();
			search.addFilterEqual("memberId", bank.getMemberId());
			Integer count=this.memberBankRepository.count(search);
			if(count>=MemberBank.DEFAULT_MAX_COUNT){
				throw new EliteServiceException("最多绑定5张银行卡", ErrorCodeEnum.SURPASS_NUM_LIMIT.code);
			}
		}
		bank.setStatus(System_Status.normal);
		bank.setVerifyed(true);
		this.memberBankRepository.save(bank);
		if(bank.isAsynced()){
			//同步更新多重身份
			eventPublishService.publishMemberIdentityDate(bank, null, null,null);
		}
		return bank;
	}

	@Override
	public MemberBank updateMemberBank(MemberBank bank) throws EliteServiceException {
		this.verifyParams(bank,bank.getId());
		this.memberBankRepository.save(bank);
		if(bank.isAsynced()){
			//同步更新多重身份
			eventPublishService.publishMemberIdentityDate(bank, null, null,null);
		}
		return bank;
	}
	
	@Override
	public void removeMemberBank(Long id,Long memberId)throws EliteServiceException{
		this.verifyParams(id,memberId);	
		MemberBank bank=this.memberBankRepository.find(id);
		if(bank==null){
			throw new EliteServiceException("此卡号不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}else if(!memberId.equals(bank.getMemberId())){
			throw new EliteServiceException("非本人银行卡，不能删除", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}
		this.memberBankRepository.remove(bank);
	}

	@Override
	public MemberBank findMemberBankById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberBankRepository.find(id);
	}

	@Override
	public List<MemberBank> findMemberBankByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search=new Search(MemberBank.class);
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("status", System_Status.normal);
		search.addFilterEqual("verifyed",true);
		search.addSort("createTime", true);//倒序 desc
		return this.memberBankRepository.search(search);
	}

	@Override
	public List<MemberBank> findMemberBankByMemberIdAndType(Long memberId,MemberBank_Type type) throws EliteServiceException {
		
		this.verifyParams(memberId);
		Search search=new Search(MemberBank.class);
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("status", System_Status.normal);
		search.addFilterEqual("type", type);
		search.addFilterEqual("verifyed",true);
		return this.memberBankRepository.search(search);
	}

}
