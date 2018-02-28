package com.ledao.elite.core.service.member;

import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.exception.EliteServiceException;;

/**
 * 会员征信服务接口
 */
public interface MemberCreditService {

	/**
	 * 更新征信信息
	 * @param memberCredit
	 * @return
	 * @throws EliteServiceException
	 */
	MemberCredit syncUpdateMemberCredit(MemberCredit memberCredit) throws EliteServiceException;
	/**
	 * 添加会员征信信息
	 * 
	 * @param memberCredit
	 * @return MemberCredit
	 */
	MemberCredit createMemberCredit(MemberCredit memberCredit) throws EliteServiceException;

	/**
	 * 添加会员征信信息
	 * 
	 * @param memberId
	 * @param memberCredit
	 * @return MemberCredit
	 */
	MemberCredit updateMemberCredit(Long memberId, MemberCredit memberCredit) throws EliteServiceException;

	/**
	 * 人才渠道方 修改征信信息
	 */
	MemberCredit updateMemberCreditByPartnerElite(Long memberId, MemberCredit credit) throws EliteServiceException;

	/**
	 * 项目渠道方 修改征信信息
	 * 
	 */
	MemberCredit updateMemberCreditByPartnerCompany(Long memberId, MemberCredit credit) throws EliteServiceException;

	/**
	 * 修改基本信息，修改属性不固定
	 * 
	 * @param basic
	 * @throws EliteServiceException
	 */
	void updateCreditInfoNoFixed(MemberCredit obj) throws EliteServiceException;

	/**
	 * 查询会员征信信息
	 * 
	 * @param memberId
	 * @return MemberCredit
	 */
	MemberCredit findMemberCreditByMemberId(Long memberId) throws EliteServiceException;

	/**
	 * 根据支付宝帐号查询
	 */
	boolean findMemberCreditByAlipayCount(String alipayAccount) throws EliteServiceException;

	/**
	 * 根据身份证号查询
	 */
	boolean findMemberCreditByIdCard(String idCard) throws EliteServiceException;

	/**
	 * 验证身份证姓名是否匹配
	 */
	boolean findValidateIdCard(String name, String idCard) throws EliteServiceException;
}
