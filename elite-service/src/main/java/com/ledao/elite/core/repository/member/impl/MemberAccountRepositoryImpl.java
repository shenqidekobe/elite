package com.ledao.elite.core.repository.member.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberAccount;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberAccountRepository;

@Repository
public class MemberAccountRepositoryImpl extends GenericRepositoryImpl<MemberAccount, Long> implements MemberAccountRepository {
	
	@Override
	public void updateMemberAccount(Long id, BigDecimal balance,BigDecimal totalIncome){
		String sql = "update t_member_account set balance=?,total_income=? where id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setBigDecimal(0, balance);
		query.setBigDecimal(1, totalIncome);
		query.setLong(2, id);
		query.executeUpdate();
		getSession().flush();
		getSession().clear();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public MemberAccount queryMemberAccountByMemberId(Long memberId){
		String hql="from MemberAccount where memberId=?";
		Query query=getSession().createQuery(hql);
		query.setLong(0,memberId);
		List<MemberAccount> list=query.list();
		return list.isEmpty()?null:list.get(0);
	}
	
}
