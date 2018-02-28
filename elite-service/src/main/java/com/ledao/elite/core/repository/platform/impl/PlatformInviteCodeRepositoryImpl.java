package com.ledao.elite.core.repository.platform.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformInviteCodeRepository;

@Repository
public class PlatformInviteCodeRepositoryImpl extends GenericRepositoryImpl<PlatformInviteCode, Long> implements PlatformInviteCodeRepository {
    
	//查询会员列表sql语句
	private static String QUERY_LIST_PREFIX = "select p.* ";
	private static String QUERY_COUNT_PREFIX = "select count(p.id) ";
	private static String QUERY_LIST_SQL = " from t_platform_invite_code p "
			+ "left join t_member_passport m on m.id=p.user_id ";
	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformInviteCode> queryPlatformInviteCodetList(String keyword, String type, Pager pager) {
		String sql=QUERY_LIST_PREFIX+QUERY_LIST_SQL;
		if(StringUtils.isNotEmpty(keyword)||StringUtils.isNotEmpty(type))
		{
			sql+= "where";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql+=" (m.nick_name like '%" + keyword + "%' or p.invite_code like '%" + keyword+ "%' )";
		}
		if (StringUtils.isNotEmpty(type)) {
			if(StringUtils.isNotEmpty(keyword)){
				sql+=" and";
			}
			sql+=  " p.type=:type";
		}
		sql += " order by p.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PlatformInviteCode.class);
		if (StringUtils.isNotEmpty(type)) {
			query.setString("type", type);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryPlatformInviteCodetCount(String keyword, String type) {
		String sql=QUERY_COUNT_PREFIX+QUERY_LIST_SQL;
		if(StringUtils.isNotEmpty(keyword)||StringUtils.isNotEmpty(type))
		{
			sql+= "where";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql+=" (m.nick_name like '%" + keyword + "%' or p.invite_code like '%" + keyword+ "%' )";
		}
		if (StringUtils.isNotEmpty(type)) {
			if(StringUtils.isNotEmpty(keyword)){
				sql+=" and";
			}
			sql+=  " p.type=:type";
		}
		sql += " order by p.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(type)) {
			query.setString("type", type);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

}
