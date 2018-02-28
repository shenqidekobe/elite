package com.ledao.elite.core.repository.platform.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Status;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformInOrderRepository;

@Repository
public class PlatformInOrderRepositoryImpl extends GenericRepositoryImpl<PlatformInOrder, Long>
		implements PlatformInOrderRepository {

	@Override
	public PlatformInOrder queryPlatformInOrder(Long projectId, Long memberId, PlatformInOrder_Type type,
			PlatformInOrder_Status status) {
		String hql = "from PlatformInOrder where projectId=? and memberId=? and type=? and status=?";
		Query query = getSession().createQuery(hql);
		query.setLong(0, projectId);
		query.setLong(1, memberId);
		query.setString(2, type.name());
		query.setString(3, status.name());
		return (PlatformInOrder) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformInOrder> queryPlatformInOrdersByKeyword(String keyword, String status, String type,
			String payType, Pager pager) {
		String sql = "select o.* from t_platform_in_order o, t_project p, t_member_credit c "
				+ "where o.project_id = p.id and o.member_id = c.member_id ";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or c.real_name like '%" + keyword + "%' or o.third_serial like '%"
					+ keyword + "%' or o.order_id like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and o.status=:status";
		}
		if (StringUtils.isNotEmpty(type)) {
			sql += " and o.type=:type";
		}
		if (StringUtils.isNotEmpty(payType)) {
			sql += " and o.pay_type=:payType";
		}
		sql += " order by o.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PlatformInOrder.class);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (StringUtils.isNotEmpty(type)) {
			query.setString("type", type);
		}
		if (StringUtils.isNotEmpty(payType)) {
			query.setString("payType", payType);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryPlatformInOrdersCountByKeyword(String keyword, String status, String type, String payType) {
		String sql = "select count(o.id) from t_platform_in_order o, t_project p, t_member_credit c "
				+ "where o.project_id = p.id and o.member_id = c.member_id ";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or c.real_name like '%" + keyword + "%' or o.third_serial like '%"
					+ keyword + "%' or o.order_id like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and o.status=:status";
		}
		if (StringUtils.isNotEmpty(type)) {
			sql += " and o.type=:type";
		}
		if (StringUtils.isNotEmpty(payType)) {
			sql += " and o.pay_type=:payType";
		}
		sql += " order by o.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (StringUtils.isNotEmpty(type)) {
			query.setString("type", type);
		}
		if (StringUtils.isNotEmpty(payType)) {
			query.setString("payType", payType);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

}
