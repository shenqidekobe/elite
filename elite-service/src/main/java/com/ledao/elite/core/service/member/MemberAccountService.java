package com.ledao.elite.core.service.member;

import java.math.BigDecimal;

import com.ledao.elite.core.domain.member.MemberAccount;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;

/**
 * 会员账户中心服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface MemberAccountService {
	
	
	/**
	 * 会员账户初始化
	 * 
	 * @param memberId
	 * @return memberAccount
	 * */
	MemberAccount createInitMemberAccount(Long memberId)throws EliteServiceException;
	
	/**
	 * 更新账户信息
	 * 
	 * @param memberAccount
	 * */
	MemberAccount updateMemberAccount(MemberAccount obj)throws EliteServiceException;
	
	/**
	 * 更新账户托管费用
	 * 
	 * @param memberId
	 * @param trustAmount
	 * @param oper
	 * */
	MemberAccount updateMemberAccountTrustAmount(Long memberId,BigDecimal trustAmount,Data_Oper oper)throws EliteServiceException;
	
	/**
	 * 更新账户托管费股权
	 * 
	 * @param memberId
	 * @param trustStock
	 * @param oper
	 * */
	MemberAccount updateMemberAccountTrustStock(Long memberId,BigDecimal trustStock,Data_Oper oper)throws EliteServiceException;
	
	/**
	 * 更新账户收益和余额
	 * 
	 * @param memberId
	 * @param totalIncome
	 * @param oper
	 * */
	MemberAccount updateMemberAccountTotalIncome(Long memberId,BigDecimal totalIncome,BigDecimal balance, Data_Oper oper)throws EliteServiceException;
	
	/**
	 * 更新账户余额
	 * 
	 * @param memberId
	 * @param balance
	 * @param oper
	 * */
	MemberAccount updateMemberAccountBalance(Long memberId,BigDecimal balance,Data_Oper oper)throws EliteServiceException;
	
	/**
	 * 更新账户余额以及质保金，以及更新会员的累计收益
	 * 
	 * @param memberId
	 * @param balance
	 * @param oper
	 * @param quaOper
	 * */
	MemberAccount updateMemberAccountBalance(Long memberId,BigDecimal balance,BigDecimal guaranteeAmount,Data_Oper oper,Data_Oper quaOper)throws EliteServiceException;
	
	/**
	 * 更新账户质保金
	 * 
	 * @param memberId
	 * @param guaranteeAmount
	 * @param oper
	 * */
	MemberAccount updateMemberAccountGuaranteeAmount(Long memberId,BigDecimal guaranteeAmount,Data_Oper oper)throws EliteServiceException;
	
	/**
	 * 根据会员ID回去会员账户信息
	 * 
	 * @param memberId
	 * @return memberAaccount
	 * */
	MemberAccount findMemberAccountByMemberId(Long memberId)throws EliteServiceException;
	
	/**
	 * 根据ID回去会员账户信息
	 * 
	 * @param id
	 * @return memberAaccount
	 * */
	MemberAccount findMemberAccountById(Long id)throws EliteServiceException;
	
	/**
	 * 根据会员账户ID回去会员账户信息
	 * 
	 * @param accountId
	 * @return memberAaccount
	 * */
	MemberAccount findMemberAccountByAccountId(String accountId)throws EliteServiceException;
	
	

}
