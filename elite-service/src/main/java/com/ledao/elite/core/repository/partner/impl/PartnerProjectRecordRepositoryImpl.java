package com.ledao.elite.core.repository.partner.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.partner.PartnerProjectRecordRepository;

@Repository
public class PartnerProjectRecordRepositoryImpl extends GenericRepositoryImpl<PartnerProjectRecord, Long>
		implements PartnerProjectRecordRepository {

	@Override
	@SuppressWarnings("unchecked")
	public List<PartnerProjectRecord> findPartnerProjectRecordByPartnerId(Long partnerId, String keyword,Date startTime,Date endTime, Pager pager) {
		String sql = "select m.* " + " from t_partner_project_record m ,t_project p ,t_member_passport a"
				+ " where  m.project_id=p.id" + " and m.partner_id ='" + partnerId + "' and p.company_id =a.id";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or p.contact_phone like '%" + keyword
					+ "%' or p.contact_name like '%" + keyword + "%' or a.account like '%" + keyword
					+ "%' or a.nick_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerProjectRecord.class);
         
		if (startTime!=null) {
			query.setDate("startTime",startTime);
		} 
		if (endTime!=null) {
			query.setDate("endTime", endTime);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findPartnerProjectRecordCountByPartnerId(Long partnerId, String keyword,Date startTime,Date endTime) {
		String sql = "select count(m.id) " + " from t_partner_project_record m,t_project p,t_member_passport a"
				+ " where  m.project_id=p.id" + " and m.partner_id ='" + partnerId + "' and p.company_id =a.id";

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or p.contact_phone like '%" + keyword
					+ "%' or p.contact_name like '%" + keyword + "%' or a.account like '%" + keyword
					+ "%' or a.nick_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		if (startTime!=null) {
			query.setDate("startTime",startTime);
		} 
		if (endTime!=null) {
			query.setDate("endTime", endTime);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public Integer findPartnerProjectRecordCountByTime(Long partnerId, Date startTime, Date endTime) {
		String sql = "select count(m.id) " + " from t_partner_project_record m"
				+ " where  m.partner_id ='" + partnerId + "' and partner_project_id is null ";
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		Query query = getSession().createSQLQuery(sql);
		if (startTime!=null) {
			query.setDate("startTime",startTime);
		} 
		if (endTime!=null) {
			query.setDate("endTime", endTime);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

}
