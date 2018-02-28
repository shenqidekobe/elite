package com.ledao.elite.core.repository.partner.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_SearchType;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.partner.PartnerRecordRepository;

@Repository
public class PartnerRecordRepositoryImpl extends GenericRepositoryImpl<PartnerRecord, Long>
		implements PartnerRecordRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<PartnerRecord> findPartnerRecordListByKeyword(Long partnerId, String partnerType, String status,
			String keyword, Date startTime, Date endTime, Pager pager) {
		String sql = "select m.* " + " from t_partner_record m left join t_member_passport p on p.id =m.member_id"
				+ "  left join t_member_credit c on c.member_id =m.member_id" + "  where m.parent_id=" + partnerId
				+ " and m.partner_type='" + partnerType + "'";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.member_phone like '%" + keyword
					+ "%' or p.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {

			if (status.equals("no_register")) {
				sql += " and (m.status=:status or m.status='audit_nopass')";
			} else {
				sql += " and m.status=:status ";
			}
		}

		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerRecord.class);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findPartnerRecordListByKeywordCount(Long partnerId, String partnerType, String status,
			String keyword, Date startTime, Date endTime) {
		String sql = " select count(m.id) "
				+ " from t_partner_record m left join t_member_passport p on p.id =m.member_id"
				+ "  left join t_member_credit c on c.member_id =m.member_id" + " where m.parent_id=" + partnerId
				+ " and m.partner_type='" + partnerType + "'";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or member_phone like '%" + keyword
					+ "%' or p.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		if (StringUtils.isNotEmpty(status)) {
			if (status.equals("no_register")) {
				sql += " and (m.status=:status or m.status='audit_nopass')";
			} else {
				sql += " and m.status=:status ";
			}
		}
		Query query = getSession().createSQLQuery(sql);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartnerRecord> findPartnerRecordListBySearchType(Long partnerId, String partnerType, String status,
			String keyword, Date startTime, Date endTime, String searchType, Date beforDays, Pager pager) {
		String sql = "select m.* " + " from t_partner_record m left join t_member_passport p on p.id =m.member_id"
				+ "  left join t_member_credit c on c.member_id =m.member_id";
		if (PartnerRecord_SearchType.no_task.name().equals(searchType)) {
			sql += "  left join  (select count(id) acount,parent_id from t_member_partner_elite  group by parent_id ) e on e.member_id=m.id where e.total is null";
			sql += "  left join  t_member_partner_elite pe on pe.member_id =m.member_id";
			sql += "  where pe.put_count =0";
		} else if (PartnerRecord_SearchType.no_task_over_14days.name().equals(searchType)) {
			sql += "  left join  (select count(id) acount,parent_id from t_member_partner_elite where create_time <:overTime  group by parent_id ) e on e.member_id=m.id where e.total is null";
			sql += "  left join  (select count(id) acount,partner_id from t_partner_elite  where create_time <:overTime group by partner_id) pe on pe.partner_id=m.id where pe.total is null";
		} else {
			sql += " where p.last_login_time <:overTime ";
		}

		sql += "  and m.parent_id=" + partnerId + " and m.partner_type='" + partnerType + "'";
		;

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.phone like '%" + keyword + "%' or p.nick_name like '%"
					+ keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		sql += " and m.status!='no_register' ";

		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerRecord.class);
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		if (PartnerRecord_SearchType.last_login_over_7days.name().equals(searchType)
				|| PartnerRecord_SearchType.no_task_over_14days.name().equals(searchType)) {
			query.setDate("overTime", beforDays);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findPartnerEliteListRecordBySearchTypeCount(Long partnerId, String partnerType, String status,
			String keyword, Date startTime, Date endTime, String searchType, Date beforDays) {
		String sql = "select count(m.id) "
				+ " from t_partner_record m left join t_member_passport p on p.id =m.member_id"
				+ "  left join t_member_credit c on c.member_id =m.member_id";
		if (PartnerRecord_SearchType.no_task.name().equals(searchType)) {
			sql += "  left join  (select count(id) acount,parent_id from t_member_partner_elite   group by parent_id ) e on e.member_id=m.id where e.total is null";
			sql += "  left join  t_member_partner_elite pe on pe.member_id =m.member_id";
			sql += "  where pe.put_count =0";
		} else if (PartnerRecord_SearchType.no_task_over_14days.name().equals(searchType)) {
			sql += "  left join  (select count(id) acount,parent_id from t_member_partner_elite where create_time <:overTime  group by parent_id ) e on e.member_id=m.id where e.total is null";
			sql += "  left join  (select count(id) acount,partner_id from t_partner_elite  where create_time <:overTime group by partner_id) pe on pe.partner_id=m.id where pe.total is null";
		} else {
			sql += " where p.last_login_time <:overTime ";
		}

		sql += "  and m.parent_id=" + partnerId + " and m.partner_type='" + partnerType + "'";

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.phone like '%" + keyword + "%' or p.nick_name like '%"
					+ keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		sql += " and m.status!='no_register' ";

		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);

		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		if (PartnerRecord_SearchType.last_login_over_7days.name().equals(searchType)
				|| PartnerRecord_SearchType.no_task_over_14days.name().equals(searchType)) {
			query.setDate("overTime", beforDays);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public Integer findPartnerRecordsCountByPartnerIdAndType(Long partnerId, String partnerType, Date startTime,
			Date endTime) {
		String sql = "select count(m.id) from t_partner_record m where m.parent_id =" + partnerId
				+ " and partner_type= '" + partnerType + "'";
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		Query query = getSession().createSQLQuery(sql);
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public void updateParnterRecordStatusBySql(String status, Long id) {
		if (id != null) {
			String sql = "update t_partner_record set status ='" + status + "' where id=" + id;
			Query query = getSession().createSQLQuery(sql);
			query.executeUpdate();
		}

	}

}
