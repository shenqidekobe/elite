package com.ledao.elite.core.repository.member;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberCredit;

/**
 * 会员征信信息接口
 * */
public interface MemberCreditRepository extends GenericDAO<MemberCredit, Long>{
	
	int updateCredit(MemberCredit obj);
	
	/**
	 * 根据支付宝帐号查询
	 * */
	MemberCredit queryMemberCreditByAlipayCount(String alipayAccount);
	
	/**
	 * 根据身份证号查询
	 * */
	MemberCredit queryMemberCreditByIdCard(String idCard);

}
