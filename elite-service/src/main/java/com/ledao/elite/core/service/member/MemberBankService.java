package com.ledao.elite.core.service.member;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBank.MemberBank_Type;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 会员银行帐号服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface MemberBankService {
	
	/**
	 * 更新银行卡
	 * @param bank
	 * @return
	 * @throws EliteServiceException
	 */
	MemberBank syncUpdateMemberBank(MemberBank bank)throws EliteServiceException;
	/**
	 * 添加银行卡
	 * */
	MemberBank createMemberBank(MemberBank bank)throws EliteServiceException;
	
	/**
	 * 修改银行卡
	 * */
	MemberBank updateMemberBank(MemberBank bank)throws EliteServiceException;
	
	/**
	 * 删除银行卡
	 * */
	void removeMemberBank(Long id,Long memberId)throws EliteServiceException;
	
	/**
	 * 按ID查询
	 * */
	MemberBank findMemberBankById(Long id)throws EliteServiceException;
	
	/**
	 * 根据会员ID查询有效的银行卡
	 * 
	 * @param memberId
	 * */
	List<MemberBank> findMemberBankByMemberId(Long memberId)throws EliteServiceException;
	/**
	 * 根据会员ID查询有效的银行卡
	 * 
	 * @param memberId
	 * */
	List<MemberBank> findMemberBankByMemberIdAndType(Long memberId,MemberBank_Type type)throws EliteServiceException;

}
