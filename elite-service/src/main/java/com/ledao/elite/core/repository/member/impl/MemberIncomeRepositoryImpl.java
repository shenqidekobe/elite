package com.ledao.elite.core.repository.member.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberIncomeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberIncomeRepositoryImpl extends GenericRepositoryImpl<MemberIncome, Long>
		implements MemberIncomeRepository {

	@Override
	public BigDecimal findMemberIncomeSumByMemberId(Long memberId, String incomeType, Date startTime, Date endTime) {
		String sql = "select sum(m.amount)  from t_member_income m" + " where  member_id=" + memberId;
		if (StringUtils.isNotEmpty(incomeType)) {
			if(incomeType.equals(Income_Type.partner_own.name())){
				 sql+=" and(m.income_type=:incomeType or m.income_type ='"+Income_Type.parent_order_award.name()+"')";
			}else{
				sql += " and m.income_type=:incomeType";
			}
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " group by m.member_id ";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(incomeType)) {
			query.setString("incomeType", incomeType);
		}
		if (startTime != null) {
			query.setString("startTime", DateFormatUtils.format(startTime, "yyyy-MM-dd"));
		}
		if (endTime != null) {
			query.setString("endTime", DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd"));
		}
		if (query.uniqueResult() == null) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(query.uniqueResult().toString());
		}
	}

	@Override
	public BigDecimal findMemberIncomeSumByMemberIdAndSourceMemberId(Long memberId, Long sourceMemberId,
			String incomeType, Date startTime, Date endTime) {
		String sql = "select sum(m.amount)  from t_member_income m" + " where  member_id=" + memberId
				+ " and source_member_id =" + sourceMemberId;
		if (StringUtils.isNotEmpty(incomeType)) {
			if(incomeType.equals(Income_Type.partner_own.name())){
				 sql+=" and(m.income_type=:incomeType or m.income_type ='"+Income_Type.parent_order_award.name()+"')";
			}else{
				sql += " and m.income_type=:incomeType";
			}
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " group by m.member_id ";
		System.out.println(sql);
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(incomeType)) {
			query.setString("incomeType", incomeType);
		}
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		if (query.uniqueResult() == null) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(query.uniqueResult().toString());
		}
	}
	
	@Override
	public BigDecimal findMemberIncomeSumByPartnerId(Long sourceMemberId, Long memberId,Long partnerMemberId,
			String incomeType, Date startTime, Date endTime) {
		String sql = "select sum(t.amount) from (select a.amount from t_member_income a left join  t_member_income m on a.source_member_id="+sourceMemberId+" and (a.task_id=m.task_id or (m.task_id is null and a.task_id is null)) and a.stage_id=m.stage_id"
				+ " and m.income_type!='parent_order_award' where  m.member_id="+sourceMemberId+" and m.source_member_id="+memberId+" and  a.member_id="+partnerMemberId+"";
		if (StringUtils.isNotEmpty(incomeType)) {
			sql += " and a.income_type=:incomeType";
		}
		if (startTime != null) {
			sql += " and a.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and a.create_time<=:endTime";
		}
		sql += "  group by a.task_id,a.stage_id)t";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(incomeType)) {
			query.setString("incomeType", incomeType);
		}
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		if (query.uniqueResult() == null) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(query.uniqueResult().toString());
		}
	}

	
	@Override
	public BigDecimal findMemberIncomeSumByMemberIdAndPartnerId(Long memberId, Long partnerId, Date startTime,
			Date endTime) {
		String sql = "select SUM(i.amount) from t_member_income i,t_partner_project_record r" + " where i.member_id ="
				+ memberId + " and  r.partner_id=" + partnerId + " and r.project_id=i.project_id";
		if (startTime != null) {
			sql += " and i.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and i.create_time<=:endTime";
		}
		Query query = getSession().createSQLQuery(sql);
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		if (query.uniqueResult() == null) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(query.uniqueResult().toString());
		}
	}

	@Override
	public BigDecimal findMemberIncomeSumByMemberIdAndProjectId(Long memberId,Long projectId, String incomeType,
			Date startTime, Date endTime) {
		String sql = "select sum(m.amount)  from t_member_income m" + " where  member_id=" + memberId
				+ " and m.project_id =" + projectId;
		if (StringUtils.isNotEmpty(incomeType)) {
			if(incomeType.equals(Income_Type.partner_own.name())){
				 sql+=" and(m.income_type=:incomeType or m.income_type ='"+Income_Type.parent_order_award.name()+"')";
			}else{
				sql += " and m.income_type=:incomeType";
			}
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " group by m.member_id ,m.project_id";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(incomeType)) {
			query.setString("incomeType", incomeType);
		}
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		if (query.uniqueResult() == null) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(query.uniqueResult().toString());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberIncome> findMemberIncomeByPartnerElite(String keyword, Long memberId, String searchType,
			Date startTime, Date endTime, Pager pager) {
		String sql = "select i.* from t_member_income i "
				+ "left join t_member_passport m on m.id=i.source_member_id"
				+ " left join t_member_credit c on c.member_id = i.source_member_id "
				+" left join t_project p on p.id=i.project_id"
				+" left join t_project_stage_task t on t.id=i.task_id"
				+" where i.member_id= " + memberId;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or t.name like '%" + keyword + "%')";
		}
		if ("cto".equals(searchType) || "elite".equals(searchType)) {
			sql += " and m.current_type='" + searchType + "' and (i.income_type ='partner_own' or i.income_type='parent_order_award')";
		} else {
			sql += " and i.income_type ='" + searchType + "'";
		}
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " order by i.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberIncome.class);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findMemberIncomeCountByPartnerElite(String keyword, Long memberId, String searchType, Date startTime,
			Date endTime) {
		String sql = "select count(i.id) from t_member_income i "
				+ "left join t_member_passport m on m.id=i.source_member_id"
				+ " left join t_member_credit c on c.member_id = i.source_member_id "
				+" left join t_project p on p.id=i.project_id"
				+" left join t_project_stage_task t on t.id=i.task_id"
				+" where i.member_id= " + memberId;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or t.name like '%" + keyword + "%')";
		}
		if ("cto".equals(searchType) || "elite".equals(searchType)) {
			sql += " and m.current_type='" + searchType + "' and (i.income_type ='partner_own' or i.income_type='parent_order_award')";
		} else {
			sql += " and i.income_type ='" + searchType + "'";
		}
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		Query query = getSession().createSQLQuery(sql);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberIncome> findMemberIncomesByPartner(String keyword, String searchType, Long partnerId,
			Date startTime, Date endTime, Pager pager) {
		String sql = " select i.* from t_member_income i left join  t_member_passport m on m.id=i.member_id left join  t_member_credit c on c.member_id =i.member_id";
		if ("cto".equals(searchType) || "elite".equals(searchType)) {
			sql += " left join t_partner_elite e on e.member_id =i.member_id where e.member_id is not null and e.partner_id="
					+ partnerId + " and m.current_type ='" + searchType + "'";
		} else if ("direct".equals(searchType)) {
			sql += " left join (select e.* from t_partner_elite e,t_member_partner_elite p "
					+ " where e.partner_id =p.id and p.parent_id =" + partnerId + ") fe on fe.member_id =i.member_id"
					+ " where fe.member_id is not null ";
		} else if ("indirect".equals(searchType)) {
			sql += " left join (select e.* from t_partner_elite e,t_member_partner_elite p,t_member_partner_elite fp"
					+ " where e.partner_id =p.id and p.parent_id=fp.id and fp.parent_id =" + partnerId
					+ ") fe on fe.member_id =i.member_id" + " where fe.member_id is not null ";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.account like '%" + keyword
					+ "%' or c.real_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		Query query = getSession().createSQLQuery(sql).addEntity(MemberIncome.class);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();

	}

	@Override
	public Integer findMemberIncomeCountByPartner(String keyword, String searchType, Long partnerId, Date startTime,
			Date endTime, Pager pager) {
		String sql = " select count(i.id) from t_member_income i left join  t_member_passport m on m.id=i.member_id left join  t_member_credit c on c.member_id =i.member_id";
		if ("cto".equals(searchType) || "elite".equals(searchType)) {
			sql += " left join t_partner_elite e on e.member_id =i.member_id where e.member_id is not null and e.partner_id="
					+ partnerId + " and m.current_type ='" + searchType + "'";
		} else if ("direct".equals(searchType)) {
			sql += " left join (select e.* from t_partner_elite e,t_member_partner_elite p "
					+ " where e.partner_id =p.id and p.parent_id =" + partnerId + ") fe on fe.member_id =i.member_id"
					+ " where fe.member_id is not null ";
		} else if ("indirect".equals(searchType)) {
			sql += " left join (select e.* from t_partner_elite e,t_member_partner_elite p,t_member_partner_elite fp"
					+ " where e.partner_id =p.id and p.parent_id=fp.id and fp.parent_id =" + partnerId
					+ ") fe on fe.member_id =i.member_id" + " where fe.member_id is not null ";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.account like '%" + keyword
					+ "%' or c.real_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		Query query = getSession().createSQLQuery(sql);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberIncome> findMemberIncomeByPartnerCompanyOwn(Long memberId, Date startTime, Date endTime,
			String type, Pager pager) {
		String sql = "select * from t_member_income i where member_id= " + memberId + " and income_type='" + type + "'";
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " group by project_id";
		sql += " order by i.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberIncome.class);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findMemberIncomesCountByPartnerCompanyOwn(Long memberId, String type, Date startTime, Date endTime) {
		String sql = "select count(project_id)  from t_member_income i where member_id= " + memberId
				+ " and income_type='" + type + "'";
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " group by project_id";
		sql += " order by i.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberIncome> findMemberIncomeByPartnerCompany(Long memberId, String keyword,Date startTime, Date endTime, String type,
			Pager pager) {
		String sql = "select * from t_member_income i "
				  +" left join t_project p on p.id=i.project_id"
				  +" left join t_member_credit c on c.member_id=i.source_member_id"
				  +" left join t_project_stage_task t on t.id=i.task_id"
				  +" where i.member_id= " + memberId;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or t.name like '%" + keyword + "%')";
		}
		if (type.indexOf("partner") >= 0) {
			sql += " and income_type in (";
			String[] types = type.split(",");
			for (int i = 0; i < types.length; i++) {
				if (types[i].length() > 0) {
					sql += "'" + types[i] + "',";
				}
			}
			sql=sql.substring(0, sql.length()-1);
			sql += " )";
		}
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " order by i.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberIncome.class);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findMemberIncomesCountByPartnerCompany(Long memberId, String keyword,String type, Date startTime, Date endTime) {
		String sql = "select count(i.id)  from t_member_income i"
				  +" left join t_project p on p.id=i.project_id"
				  +" left join t_member_credit c on c.member_id=i.source_member_id"
				  +" left join t_project_stage_task t on t.id=i.task_id"
				  +" where i.member_id= " + memberId;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or t.name like '%" + keyword + "%')";
		}
		if (type.indexOf("partner") >= 0) {
			sql += " and income_type in (";
			String[] types = type.split(",");
			for (int i = 0; i < types.length; i++) {
				if (types[i].length() > 0) {
					sql += "'" + types[i] + "',";
				}
			}
			sql=sql.substring(0, sql.length()-1);
			sql += " )";
		}
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		Query query = getSession().createSQLQuery(sql);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer findMemberIncomeProjectCount(Long memberId, Income_Type type) {
		String sql = "select * from t_member_income where member_id=? and income_type=? group by project_id";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberIncome.class);
		query.setLong(0, memberId);
		query.setString(1, type.name());
		List<MemberIncome> list = query.list();
		log.info("检索会员收益情况SQL【"+sql+"】【参数：memberId="+memberId+",type="+type+"】的检索结果list size="+list.size());
		return list.isEmpty() ? 0 : list.size();
	}

}
