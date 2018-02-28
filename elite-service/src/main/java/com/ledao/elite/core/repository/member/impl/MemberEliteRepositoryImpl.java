package com.ledao.elite.core.repository.member.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberEliteRepository;

@Repository
public class MemberEliteRepositoryImpl extends GenericRepositoryImpl<MemberElite, Long>
		implements MemberEliteRepository {

	@Override
	public Integer queryMemberElitePassportCount(boolean ctoed, String status) {
		String sql = " select count(e.id) from t_member_elite e ";
		if (ctoed) {
			sql += " where ctoed ='Y'";
		} else {
			sql += " where ctoed ='N'";
		}
		if (status != null) {
			sql += " and e.status=:status ";
		}
		Query query = getSession().createSQLQuery(sql);
		if (status != null) {
			query.setString("status", status);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

}
