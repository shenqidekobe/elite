package com.ledao.elite.core.repository.member.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberWithdrawRepository;

@Repository
public class MemberWithdrawRepositoryImpl extends GenericRepositoryImpl<MemberWithdraw, Long>
		implements MemberWithdrawRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberWithdraw> queryMemberWithdrawList(String keyword, String status,Date startTime,Date endTime, Pager pager) {
		String sql = "select * from t_member_withdraw d,t_member_credit c" + " where d.member_id=c.member_id";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (d.order_id like '%" + keyword + "%' or c.real_name like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and d.status=:status";
		}
		if (startTime != null) {
			sql += " and d.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd HH:ss:") + "'";
		}
		if (endTime != null) {
			sql += " and d.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " order by d.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberWithdraw.class);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryMemberWithdrawListCount(String keyword,Date startTime,Date endTime, String status) {
		String sql = "select count(d.id) from t_member_withdraw d,t_member_credit c" + " where d.member_id=c.member_id";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (d.order_id like '%" + keyword + "%' or c.real_name like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and d.status=:status";
		}
		if (startTime != null) {
			sql += " and d.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and d.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
}
