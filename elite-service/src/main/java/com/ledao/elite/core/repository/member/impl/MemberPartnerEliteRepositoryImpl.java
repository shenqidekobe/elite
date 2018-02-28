package com.ledao.elite.core.repository.member.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPartnerElite.MemberPartnerElite_Search_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberPartnerEliteRepository;

@Repository
public class MemberPartnerEliteRepositoryImpl extends GenericRepositoryImpl<MemberPartnerElite, Long>
		implements MemberPartnerEliteRepository {

	@Override
	public void updateEliteInfoCount(Long id, Integer putCount, Integer enterCount,Integer calculateEliteCount) {
		String sql = "update t_member_partner_elite set put_count=?,enter_count=?,calculate_elite_count=? where id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger(0, putCount);
		query.setInteger(1, enterCount);
		query.setInteger(2, calculateEliteCount);
		query.setLong(3, id);
		query.executeUpdate();
	}
	
	@Override
	public void updateMemberPartnerEliteAsCalculate(Long id,Integer taskCount,BigDecimal taskTotalAmount,Integer calculateEliteCount,
			Integer calculateTaskCount,BigDecimal calculateTaskTotalAmount,Date startOrderTime,Date lastOrderTime){
		String sql = "update t_member_partner_elite set task_count=?,total_amount=?,calculate_elite_count=?,"
				+ "calculate_task_count=?,calculate_total_amount=?,start_order_time=?,last_order_time=? where id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger(0, taskCount);
		query.setBigDecimal(1, taskTotalAmount);
		query.setInteger(2, calculateEliteCount);
		query.setInteger(3, calculateTaskCount);
		query.setBigDecimal(4, calculateTaskTotalAmount);
		query.setDate(5, startOrderTime);
		query.setDate(6, lastOrderTime);
		query.setLong(7, id);
		query.executeUpdate();
	}
	
	@Override
	public void updateMemberPartnerEliteClearCalculate(Long id,Date clearCalculateTime){
		String sql = "update t_member_partner_elite set calculate_elite_count=0,calculate_task_count=0,calculate_total_amount=0,clear_calculate_time=? where id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setDate(0, clearCalculateTime);
		query.setLong(1, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPartnerElite> findMemberPartnerEliteList(Long partnerId, String status, Date startTime,
			Date endTime, Pager pager) {
		String sql = "select m.* from t_member_partner_elite m  left join "
				+ " (select sum(amount) allamount ,member_id from t_member_income ";
		if (startTime != null || endTime != null) {
			sql += " where";
		}
		if (startTime != null) {
			sql += "  create_time >=:startTime";
		}
		if (endTime != null) {
			if (startTime != null) {
				sql += " and";
			}
			sql += "  create_time<=:endTime";
		}

		sql += " group by member_id) a  on m.member_id=a.member_id where m.parent_id =" + partnerId;

		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
		}
		sql += " order by a.allamount desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerElite.class);

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
	public Integer findMemberPartnerEliteListCount(Long partnerId, String status, Date startTime, Date endTime) {
		String sql = "select count(m.id) from t_member_partner_elite m  left join "
				+ " (select sum(amount) allamount ,member_id from t_member_income ";
		if (startTime != null || endTime != null) {
			sql += " where";
		}
		if (startTime != null) {
			sql += "  create_time >=:startTime";
		}
		if (endTime != null) {
			if (startTime != null) {
				sql += " and";
			}
			sql += "  create_time<=:endTime";
		}

		sql += " group by member_id) a  on m.member_id=a.member_id where m.parent_id =" + partnerId;
		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
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

	@Override
	public Integer findMemberPartnerPutCount(Long partnerId, Date startTime, Date endTime) {
		String sql = "select count(m.id) from t_member_partner_elite m where m.parent_id =" + partnerId;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPartnerElite> findMemberPartnerEliteList(Long partnerId, String status, Date startTime,
			Date endTime) {
		String sql = "select m.* from t_member_partner_elite m  left join "
				+ " (select sum(amount) allamount ,member_id from t_member_income ";
		if (startTime != null || endTime != null) {
			sql += " where";
		}
		if (startTime != null) {
			sql += "  create_time >=:startTime";
		}
		if (endTime != null) {
			if (startTime != null) {
				sql += " and";
			}
			sql += "  create_time<=:endTime";
		}

		sql += " group by member_id) a  on m.member_id=a.member_id where m.parent_id =" + partnerId;

		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
		}
		sql += " order by a.allamount desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerElite.class);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPartnerElite> findMemberPartnerEliteListBySearchType(Long parentId, String keyword,
			String searchType, Date startTime, Date endTime, Date beforDays, Pager pager) {
		String sql = " select m.* from t_member_partner_elite m " + " left join t_member_passport p on m.member_id=p.id"
				+ " left join t_member_credit c on m.member_id=c.member_id";
		if (MemberPartnerElite_Search_Type.no_task_ever.name().equals(searchType)) {
			sql += " left join  (select count(id) total,parent_id eparent_id from t_partner_record  group by parent_id ) e on e.eparent_id=m.id where e.total is null"
					+ " and m.put_count=0";
		} else if (MemberPartnerElite_Search_Type.no_task_over_14days.name().equals(searchType)) {
			sql += "  left join  (select count(id) total,parent_id eparent_id from t_partner_record where create_time >:overTime  group by parent_id ) e on e.eparent_id=m.id ";
			sql += "  left join  (select count(id) total,partner_id epartner_id from t_partner_elite  where create_time >:overTime group by partner_id) pe on pe.epartner_id=m.id where pe.total is null and  e.total is null";
		} else if (MemberPartnerElite_Search_Type.last_login_over_7days.name().equals(searchType)) {
			sql += " where p.last_login_time <:overTime ";
		}
		sql += " and  parent_id = " + parentId;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or p.account like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerElite.class);
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		if (MemberPartnerElite_Search_Type.last_login_over_7days.name().equals(searchType)
				|| MemberPartnerElite_Search_Type.no_task_over_14days.name().equals(searchType)) {
			query.setDate("overTime", beforDays);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findMemberPartnerEliteListBySearchTypeCount(Long parentId, String keyword, String searchType,
			Date startTime, Date endTime, Date beforDays) {
		String sql = " select count(m.id) from t_member_partner_elite m "
				+ " left join t_member_passport p on m.member_id=p.id"
				+ " left join t_member_credit c on m.member_id=c.member_id";
		if (MemberPartnerElite_Search_Type.no_task_ever.name().equals(searchType)) {
			sql += " left join  (select count(id) total,parent_id eparent_id from t_partner_record  group by parent_id ) e on e.eparent_id=m.id where e.total is null"
					+ " and m.put_count=0";
		} else if (MemberPartnerElite_Search_Type.no_task_over_14days.name().equals(searchType)) {
			sql += "  left join  (select count(id) total,parent_id eparent_id from t_partner_record where create_time >:overTime  group by parent_id ) e on e.eparent_id=m.id ";
			sql += "  left join  (select count(id) total,partner_id epartner_id from t_partner_elite  where create_time >:overTime group by partner_id) pe on pe.epartner_id=m.id where pe.total is null and  e.total is null";
		} else if (MemberPartnerElite_Search_Type.last_login_over_7days.name().equals(searchType)) {
			sql += " where p.last_login_time <:overTime ";
		}
		sql += "  and parent_id = " + parentId;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or p.account like '%" + keyword + "%')";
		}
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
		if (MemberPartnerElite_Search_Type.last_login_over_7days.name().equals(searchType)
				|| MemberPartnerElite_Search_Type.no_task_over_14days.name().equals(searchType)) {
			query.setDate("overTime", beforDays);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPartnerElite> findMemberPartnerEliteDirectList(Long partnerId, String status,String incomeType, Date startTime,
			Date endTime, Pager pager) {
		String sql = "select distinct p.* from t_member_partner_elite p,"
		        + " (select sum(amount) allamount ,source_member_id from t_member_income "
				+"  where income_type ='"+incomeType+"'";
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}
		sql += " group by source_member_id) a ";
		sql +=" where a.allamount is not null and a.source_member_id =p.member_id and p.parent_id ="+partnerId;
		sql += " order by a.allamount desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerElite.class);

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
	public Integer findMemberPartnerEliteListDirectCount(Long partnerId, String status,String incomeType, Date startTime, Date endTime) {
		String sql = "select count(distinct p.id) from t_member_partner_elite p,"
		        + " (select sum(amount) allamount ,source_member_id from t_member_income "
				+"  where income_type ='"+incomeType+"'";
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}
		sql += " group by source_member_id) a ";
		sql +=" where a.allamount is not null  and a.source_member_id =p.member_id and p.parent_id ="+partnerId;
		sql += " order by a.allamount desc";
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
	public List<MemberPartnerElite> findMemberPartnerEliteInDirectList(Long memberId, String status,String incomeType, Date startTime,
			Date endTime, Pager pager) {
		String sql = "select distinct p.* from t_member_partner_elite p,"
				+ " (select sum(amount) allamount ,source_member_id from t_member_income "
				+"  where income_type ='"+incomeType+"' and member_id="+memberId;
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}
		sql += " group by source_member_id) a ";
		sql +=" where a.allamount is not null and a.source_member_id =p.member_id";
		sql += " order by a.allamount desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerElite.class);
		
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
	public Integer findMemberPartnerEliteListInDirectCount(Long memberId, String status,String incomeType, Date startTime, Date endTime) {
		String sql = "select count(distinct p.id) from t_member_partner_elite p,"
				+ " (select sum(amount) allamount ,source_member_id from t_member_income "
				+"  where income_type ='"+incomeType+"' and member_id"+memberId;
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}
		sql += " group by source_member_id) a ";
		sql +=" where a.allamount is not null  and a.source_member_id =p.member_id";
		sql += " order by a.allamount desc";
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
	@Override
	public void updateMemberPartnerElitePartnerIdBysql(Long partnerId, Long id) {
		if(id!=null){
		String sql="update t_member_partner_elite set parent_id ="+partnerId+" where id=" +id;
		Query query=getSession().createSQLQuery(sql);
		query.executeUpdate();
		}
	}
}
