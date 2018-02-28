package com.ledao.elite.core.repository.member.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberTeam;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberTeamRepository;

@Repository
public class MemberTeamRepositoryImpl extends GenericRepositoryImpl<MemberTeam, Long> implements MemberTeamRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberTeam> findMemberTeamList(Long memberId, String keyWord, Pager pager) {
		String sql = "select * from t_member_team t left join t_member_passport p on t.team_member_id= p.id where t.member_id=:memberId";
		if (StringUtils.isNotEmpty(keyWord)) {
			sql += " and p.nick_name like '%" + keyWord + "%'";
		}
		sql += " order by t.last_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberTeam.class);
		query.setLong("memberId", memberId);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findMemberTeamCount(Long memberId, String keyWord) {
		String sql = "select count(t.id) from t_member_team t left join t_member_passport p on t.team_member_id= p.id where t.member_id=:memberId";
		if (StringUtils.isNotEmpty(keyWord)) {
			sql += " and p.nick_name like '%" + keyWord + "%'";
		}
		Query query = getSession().createSQLQuery(sql);
		query.setLong("memberId", memberId);
		return Integer.valueOf(query.uniqueResult().toString());
	}
		
	
}
