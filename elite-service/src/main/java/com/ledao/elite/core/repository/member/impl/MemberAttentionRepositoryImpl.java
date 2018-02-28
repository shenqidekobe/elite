package com.ledao.elite.core.repository.member.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberAttentionRepository;

@Repository
public class MemberAttentionRepositoryImpl extends GenericRepositoryImpl<MemberAttention, Long> implements MemberAttentionRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberAttention> findAttenTionUsers(String type,Long memberId, String keyWord, Pager pager) {
		String sql = "select m.* "
        		+ "from t_member_attention m left join t_member_passport p on m.member_id = p.id"
        		+ " where 1=1";
		
		if (StringUtils.isNotEmpty(keyWord)) {
			sql += " and p.name like '%" + keyWord + "%'";
		}
		if ("attention".equals(type)) {
			sql += " and m.attention_member_id=:memberId";
		}if ("attentioned".equals(type)) {
			sql += " and m.member_id=:memberId";
		}
		sql+=" order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberAttention.class);
		if (StringUtils.isNotEmpty(keyWord)) {
			query.setString("keyWord", keyWord);
		}
		if ("attentioned".equals(type)) {
			query.setLong("memberId", memberId);
		}if ("attention".equals(type)) {
			query.setLong("memberId", memberId);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
	 	return query.list();
	}

	@Override
	public Integer findAttenTionUserCount(String type,Long memberId, String keyWord) {
		String sql = "select count(m.id) "
        		+ "from t_member_attention m left join t_member_passport p on m.member_id = p.id"
        		+ " where 1=1";
		
		if (StringUtils.isNotEmpty(keyWord)) {
			sql += " and p.name like '%" + keyWord + "%'";
		}
		if ("attention".equals(type)) {
			sql += " and m.attention_member_id=:memberId";
		}if ("attentioned".equals(type)) {
			sql += " and m.member_id=:memberId";
		}
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(keyWord)) {
			query.setString("keyWord", keyWord);
		}
		if ("attentioned".equals(type)) {
			query.setLong("memberId", memberId);
		}if ("attention".equals(type)) {
			query.setLong("memberId", memberId);
		}
		
		return Integer.valueOf(query.uniqueResult().toString());
	}

	
		
}
