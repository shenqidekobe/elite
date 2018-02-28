package com.ledao.elite.core.repository.member.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPartnerElite.MemberPartnerElite_Search_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberPartnerCompanyRepository;

@Repository
public class MemberPartnerCompanyRepositoryImpl extends GenericRepositoryImpl<MemberPartnerCompany, Long>
		implements MemberPartnerCompanyRepository {
	
	@Override
	public void updateMemberPartnerEliteAsCalculate(Long id,Integer orderCount,BigDecimal totalAmount,Integer calculateOrderCount,
			BigDecimal calculateTotalAmount,Date startOrderTime,Date lastOrderTime){
		String sql = "update t_member_partner_company set order_count=?,total_amount=?,calculate_order_count=?,"
				+ "calculate_total_amount=?,start_order_time=?,last_order_time=? where id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger(0, orderCount);
		query.setBigDecimal(1, totalAmount);
		query.setInteger(2, calculateOrderCount);
		query.setBigDecimal(3, calculateTotalAmount);
		query.setDate(4, startOrderTime);
		query.setDate(5, lastOrderTime);
		query.setLong(6, id);
		query.executeUpdate();
	}
	
	@Override
	public void updateMemberPartnerEliteClearCalculate(Long id,Date clearCalculateTime){
		String sql = "update t_member_partner_company set calculate_order_count=0,calculate_total_amount=0,clear_calculate_time=? where id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setDate(0, clearCalculateTime);
		query.setLong(1, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPartnerCompany> findMemberPartnerCompanyChampionsList(Long partnerId, String status,
			Date startTime, Date endTime, Pager pager) {
		String sql = "select m.* from t_member_partner_company m  left join "
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
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerCompany.class);

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

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPartnerCompany> findMemberPartnerCompanyAllList(Long partnerId, String status, Date startTime,
			Date endTime) {
		String sql = "select m.* from t_member_partner_company m  left join "
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
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerCompany.class);

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

	@Override
	public Integer findMemberPartnerCompanyChampionsListCount(Long partnerId, String status) {
		String sql = "select count(m.id) from t_member_partner_company m " + "where m.parent_id =" + partnerId;
		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
		}
		Query query = getSession().createSQLQuery(sql);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
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
	public List<MemberPartnerCompany> findMemberPartnerCompanyListBySearchType(Long parentId, String keyword,
			String searchType, Date startTime, Date endTime, Date beforDays, Pager pager) {
		String sql = " select m.* from t_member_partner_company m "
				+ " left join t_member_passport p on m.member_id=p.id"
				+ " left join t_member_credit c on m.member_id=c.member_id";
		if (MemberPartnerElite_Search_Type.no_task_ever.name().equals(searchType)) {
			sql += " left join  (select count(id) total,parent_id eparent_id from t_partner_record  group by parent_id ) e on e.eparent_id=m.id where e.total is null"
					+ " and m.put_count=0";
		} else if (MemberPartnerElite_Search_Type.no_task_over_14days.name().equals(searchType)) {
			sql += "  left join  (select count(id) total,parent_id eparent_id from t_partner_record where create_time >:overTime  group by parent_id ) e on e.eparent_id=m.id ";
			sql += "  left join  (select count(id) total,partner_id epartner_id from t_partner_project_record  where create_time >:overTime group by partner_id) pe on pe.epartner_id=m.id where pe.total is null and  e.total is null";
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
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerCompany.class);
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
	public Integer findMemberPartnerCompanyListBySearchTypeCount(Long parentId, String keyword, String searchType,
			Date startTime, Date endTime, Date beforDays) {
		String sql = " select count(m.id) from t_member_partner_company m "
				+ " left join t_member_passport p on m.member_id=p.id"
				+ " left join t_member_credit c on m.member_id=c.member_id";
		if (MemberPartnerElite_Search_Type.no_task_ever.name().equals(searchType)) {
			sql += " left join  (select count(id) total,parent_id eparent_id from t_partner_record  group by parent_id ) e on e.eparent_id=m.id where e.total is null"
					+ " and m.put_count=0";
		} else if (MemberPartnerElite_Search_Type.no_task_over_14days.name().equals(searchType)) {
			sql += "  left join  (select count(id) total,parent_id eparent_id from t_partner_record where create_time >:overTime  group by parent_id ) e on e.eparent_id=m.id ";
			sql += "  left join  (select count(id) total,partner_id epartner_id from t_partner_project_record  where create_time >:overTime group by partner_id) pe on pe.epartner_id=m.id where pe.total is null and  e.total is null";
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
	public List<MemberPartnerCompany> findMemberPartnerCompanyListByIncome(Long memberId,String searchType, Date startTime, Date endTime,
			Pager pager) {
		String sql = "select * from t_member_partner_company c,(select source_member_id memberId ,count(project_id) projectCount from t_member_income"
                   +" where member_id="+memberId+" and income_type ='"+searchType+"'";
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}

		sql += " group by  source_member_id ) e where e.memberId =c.member_id";
		sql += " order by e.projectCount desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPartnerCompany.class);
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		return query.list();
	}

	@Override
	public Integer findMemberPartnerCompanyListByIncomeCount(Long memberId,String searchType, Date startTime, Date endTime) {
		String sql = "select count(c.id) from t_member_partner_company c,(select source_member_id memberId ,count(project_id) projectCount from t_member_income"
                +" where member_id="+memberId+" and income_type = '"+searchType+"'";
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}

		sql += " group by  source_member_id ) e where e.memberId =c.member_id";
		sql += " order by e.projectCount desc";
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
	public void updateMemberPartnerCompanyPartnerIdBysql(Long partnerId, Long id) {
		// TODO Auto-generated method stub
		if(id!=null){
		String sql="update t_t_member_partner_company set partner_id ="+partnerId+" where id=" +id;
		getSession().createSQLQuery(sql);
		}
		
	}
	
}
