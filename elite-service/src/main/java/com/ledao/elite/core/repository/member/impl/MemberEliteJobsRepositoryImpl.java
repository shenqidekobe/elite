package com.ledao.elite.core.repository.member.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberEliteJobs;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberEliteJobsRepository;

@Repository
public class MemberEliteJobsRepositoryImpl extends GenericRepositoryImpl<MemberEliteJobs, Long> implements MemberEliteJobsRepository{

	@Override
	public int deleteMemberEliteJobsByEliteId(Long eliteId) {
		String hql="delete from MemberEliteJobs where eliteId=:eliteId";
		Query query=getSession().createQuery(hql);
		query.setLong("eliteId", eliteId);
		return query.executeUpdate();
	}

}
