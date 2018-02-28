package com.ledao.elite.core.repository.member;

import java.math.BigDecimal;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberAccount;

/**
 * 会员账户信息接口
 * */
public interface MemberAccountRepository extends GenericDAO<MemberAccount, Long>{
	
	void updateMemberAccount(Long id, BigDecimal balance,BigDecimal totalIncome);

	MemberAccount queryMemberAccountByMemberId(Long memberId);
}
