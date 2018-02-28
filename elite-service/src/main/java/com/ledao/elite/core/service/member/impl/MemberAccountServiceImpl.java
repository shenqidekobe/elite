package com.ledao.elite.core.service.member.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberAccount;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.repository.member.MemberAccountRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.sys.CommonCodeService;

@Service("memberAccountService")
public class MemberAccountServiceImpl extends BaseSerivceImpl implements MemberAccountService{
	
	@Resource
	private MemberAccountRepository memberAccountRepository;
	
	@Resource
	private CommonCodeService commonCodeService;
	
	@Override
	public MemberAccount createInitMemberAccount(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		MemberAccount ma=findMemberAccountByMemberId(memberId);
		if(ma!=null){
			return ma;
		}
		BigDecimal initial=new BigDecimal(0);
		String accountNum = commonCodeService.disposeOddNumber("accountNum", COMMON_PREVAL.MA.name(), "yyyyMMdd", 8, null);
		MemberAccount account=new MemberAccount();
		account.setMemberId(memberId);
		account.setAccountId(accountNum);
		account.setBalance(initial);
		account.setTotalIncome(initial);
		account.setTrustAmount(initial);
		account.setTrustStock(initial);
		account.setTrustNum(0);
		account.setEliteCoin(initial);
		account.setEnsured(false);
		account.setEnsureAmount(initial);
		account.setGuaranteeAmount(initial);
		this.memberAccountRepository.save(account);
		return account;
	}

	@Override
	public MemberAccount updateMemberAccount(MemberAccount obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId(),obj.getMemberId());
		this.memberAccountRepository.save(obj);
		return obj;
	}

	@Override
	public MemberAccount findMemberAccountByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		//List<MemberAccount> list=this.memberAccountRepository.search(new Search().addFilterEqual("memberId", memberId));
		return this.memberAccountRepository.queryMemberAccountByMemberId(memberId);
	}

	@Override
	public MemberAccount findMemberAccountById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberAccountRepository.find(id);
	}

	@Override
	public MemberAccount findMemberAccountByAccountId(String accountId) throws EliteServiceException {
		this.verifyParams(accountId);
		return this.memberAccountRepository.searchUnique(new Search().addFilterEqual("accountId", accountId));
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public MemberAccount updateMemberAccountTrustAmount(Long memberId, BigDecimal trustAmount, Data_Oper oper)
			throws EliteServiceException {
		this.verifyParams(memberId,trustAmount,oper);
		MemberAccount account=this.findMemberAccountByMemberId(memberId);
		BigDecimal result=account.getTrustAmount();
		switch (oper) {
		case sum:
			result=result.add(trustAmount);
			break;
		case sub:
			result=result.subtract(trustAmount);
			break;
		}
		logger.info("更新会员："+memberId+"的托管费用总额："+account.getTrustAmount()+",本次托管金额："+trustAmount+"*************************");
		account.setTrustAmount(result);
		this.memberAccountRepository.save(account);
		return account;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public MemberAccount updateMemberAccountTrustStock(Long memberId, BigDecimal trustStock, Data_Oper oper)
			throws EliteServiceException {
		this.verifyParams(memberId,trustStock,oper);
		MemberAccount account=this.findMemberAccountByMemberId(memberId);
		BigDecimal result=account.getTrustStock();
		switch (oper) {
		case sum:
			result=result.add(trustStock);
			break;
		case sub:
			result=result.subtract(trustStock);
			break;
		}
		account.setTrustStock(result);
		this.memberAccountRepository.save(account);
		return account;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public MemberAccount updateMemberAccountTotalIncome(Long memberId,BigDecimal totalIncome,BigDecimal balance, Data_Oper oper)throws EliteServiceException{
		this.verifyParams(memberId,totalIncome,balance);
		MemberAccount account=this.findMemberAccountByMemberId(memberId);
		if(account==null)return null;
		BigDecimal result=account.getBalance();
		BigDecimal total=account.getTotalIncome();
		switch (oper) {
		case sum:
			result=result.add(balance);
			total=total.add(totalIncome);
			break;
		case sub:
			result=result.subtract(balance);
			total=total.subtract(totalIncome);
			break;
		}
		logger.info("更新会员账户【"+memberId+"】,更新前的余额："+account.getBalance()+",更新后的余额总额："+result);
		this.memberAccountRepository.updateMemberAccount(account.getId(), result, total);
		return account;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public MemberAccount updateMemberAccountBalance(Long memberId, BigDecimal balance, Data_Oper oper)
			throws EliteServiceException {
		this.verifyParams(memberId,balance,oper);
		MemberAccount account=this.findMemberAccountByMemberId(memberId);
		BigDecimal result=account.getBalance();
		switch (oper) {
		case sum:
			result=result.add(balance);
			break;
		case sub:
			result=result.subtract(balance);
			break;
		}
		account.setBalance(result);
		this.memberAccountRepository.save(account);
		return account;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
    public MemberAccount updateMemberAccountBalance(Long memberId,BigDecimal balance,BigDecimal guaranteeAmount,Data_Oper oper,Data_Oper quaOper)throws EliteServiceException{
		this.verifyParams(memberId,balance,guaranteeAmount,oper);
		MemberAccount account=this.findMemberAccountByMemberId(memberId);
		if(account==null)return null;
		BigDecimal result=account.getBalance();
		BigDecimal totalIncome=account.getTotalIncome();
		switch (oper) {
		case sum:
			result=result.add(balance);
			totalIncome=totalIncome.add(balance);//增加累计收益
			break;
		case sub:
			result=result.subtract(balance);
			break;
		}
		BigDecimal quaAmount=account.getGuaranteeAmount();
		switch (quaOper) {
		case sum:
			quaAmount=quaAmount.add(guaranteeAmount);
			break;
		case sub:
			quaAmount=quaAmount.subtract(guaranteeAmount);
			break;
		}
		account.setTotalIncome(totalIncome);
		account.setBalance(result);
		account.setGuaranteeAmount(quaAmount);
		this.memberAccountRepository.save(account);
		return account;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public MemberAccount updateMemberAccountGuaranteeAmount(Long memberId,BigDecimal guaranteeAmount,Data_Oper oper)throws EliteServiceException{
		this.verifyParams(memberId,guaranteeAmount,oper);
		MemberAccount account=this.findMemberAccountByMemberId(memberId);
		BigDecimal result=account.getGuaranteeAmount();
		switch (oper) {
		case sum:
			result=result.add(guaranteeAmount);
			break;
		case sub:
			result=result.subtract(guaranteeAmount);
			break;
		}
		account.setGuaranteeAmount(result);
		this.memberAccountRepository.save(account);
		return account;
	}
	

}
