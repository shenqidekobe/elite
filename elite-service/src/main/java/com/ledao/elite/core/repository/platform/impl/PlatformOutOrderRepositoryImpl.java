package com.ledao.elite.core.repository.platform.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformOutOrder;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformOutOrderRepository;

@Repository
public class PlatformOutOrderRepositoryImpl extends GenericRepositoryImpl<PlatformOutOrder, Long>
		implements PlatformOutOrderRepository {

	@Override
	@SuppressWarnings("unchecked")
	public List<PlatformOutOrder> queryPlatformOutOrderList(Long projectId, String type,String keyword, Date startTime,
			Date endTime, Pager pager, String status) {
		String sql = " select * from  t_platform_out_order o"
				+ " left join t_member_passport m on o.member_id =m.id"
				+ " left join t_member_credit c on  o.member_id=c.member_id "
				 +" where o.project_id =:projectId and m.current_type in('elite','cto')";
		if (StringUtils.isNotEmpty(status)) {
			sql += " and o.status=:status";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and o.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and o.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " order by o.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PlatformOutOrder.class);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		query.setLong("projectId", projectId);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryPlatformOutOrderListCount(Long projectId,String type, String keyword, Date startTime, Date endTime,
			String status) {
				String sql = " select  count(o.id) from  t_platform_out_order o"
						+ " left join t_member_passport m on o.member_id =m.id"
						+ " left join t_member_credit c on  o.member_id=c.member_id "
						 +" where o.project_id =:projectId and m.current_type in('elite','cto')";
		if (StringUtils.isNotEmpty(status)) {
			sql += " and o.status=:status";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and o.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and o.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " order by o.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		query.setLong("projectId", projectId);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformOutOrder> queryPlatformOutOrdersByKeyword(String keyword, String status, String type,
		 Pager pager) {
		String sql = "select o.* from t_platform_out_order o, t_project p "
				+ "where o.project_id = p.id ";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or o.member_name like '%" + keyword + "%' or o.order_id like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and o.status=:status";
		}
		if (StringUtils.isNotEmpty(type)) {
			sql += " and o.type=:type";
		}
		sql += " order by o.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PlatformOutOrder.class);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (StringUtils.isNotEmpty(type)) {
			query.setString("type", type);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryPlatformOutOrdersCountByKeyword(String keyword, String status, String type) {
		String sql = "select count(o.id) from t_platform_out_order o, t_project p "
				+ "where o.project_id = p.id  ";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or o.member_name like '%" + keyword + "%' or o.order_id like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and o.status=:status";
		}
		if (StringUtils.isNotEmpty(type)) {
			sql += " and o.type=:type";
		}
		sql += " order by o.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (StringUtils.isNotEmpty(type)) {
			query.setString("type", type);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
}
