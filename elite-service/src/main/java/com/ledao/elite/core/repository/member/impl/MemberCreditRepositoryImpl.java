package com.ledao.elite.core.repository.member.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberCreditRepository;

@Repository
public class MemberCreditRepositoryImpl extends GenericRepositoryImpl<MemberCredit, Long> implements MemberCreditRepository {


	@Override
	public int updateCredit(MemberCredit obj){
		String sql="update t_member_credit set real_name=?,id_card=?,card_just="+obj.getCardJust()+",card_Reverse="+obj.getCardReverse()+",is_Card=? where id=?";
		Query query=getSession().createSQLQuery(sql);
		query.setString(0, obj.getRealName());
		query.setString(1, obj.getIdCard());
		query.setBoolean(2, obj.getIsCard());
		query.setLong(3, obj.getId());
		return query.executeUpdate();
	}
	
	@Override
	public MemberCredit queryMemberCreditByAlipayCount(String alipayAccount) {
		String hql="from MemberCredit where alipayAccount=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,alipayAccount);
		return (MemberCredit) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public MemberCredit queryMemberCreditByIdCard(String idCard) {
		String hql="from MemberCredit where idCard=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,idCard);
		List<MemberCredit> list=query.list();
		return list.isEmpty()?null:list.get(0);
	}

}
