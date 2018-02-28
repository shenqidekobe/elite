package com.ledao.elite.core.repository.project.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectRepository;

@Repository
public class ProjectRepositoryImpl extends GenericRepositoryImpl<Project, Long> implements ProjectRepository {

	@Override
	public void mergeProject(Project obj){
		String sql="update t_project set guarantee_time=?,status=?,accept_time=? where id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger(0, obj.getGuaranteeTime());
		query.setString(1, obj.getStatus().name());
		query.setDate(2, obj.getAcceptTime());
		query.setLong(3, obj.getId());
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findProjectListByMemberPartnerCompanyId(Long memberId) {
		String sql = "select p.* from t_project p,t_partner_project_record r"
				+ " where p.id= r.project_id and r.partner_id =" + memberId;
		Query query = getSession().createSQLQuery(sql).addEntity(Project.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findProjectListByMemberPartnerId(Long partnerId, String status, Pager pager) {
		String sql = "select p.* from t_project p,t_partner_project_record r"
				+ " where p.id= r.project_id and partner_id =" + partnerId;
		Query query = getSession().createSQLQuery(sql).addEntity(Project.class);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findProjectListCountByMemberPartnerId(Long partnerId, String status) {
		String sql = "select count(p.id) from t_project p,t_partner_project_record r"
				+ " where p.id= r.project_id and partner_id =" + partnerId;
		Query query = getSession().createSQLQuery(sql);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findProjectListByMemberPartnerCompanyByOwn(Long memberId,String incomeType, Date startTime, Date endTime,
			Pager pager) {
		String sql = "select p.* from t_project p,(select project_id from  t_member_income i where member_id= "
				+ memberId + " and (income_type='" + incomeType + "' or income_type ='parent_order_award')";
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " group by project_id )a where p.id =a.project_id ";
		sql += " order by p.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(Project.class);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findProjectCountByMemberPartnerCompanyByOwn(Long memberId,String incomeType, Date startTime, Date endTime) {
		String sql = "select count(p.id)  from t_project p,(select project_id from  t_member_income i where member_id= "
				+ memberId + " and (income_type='" + incomeType + "' or income_type ='parent_order_award')";
		if (startTime != null) {
			sql += " and i.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and i.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " group by project_id )a where p.id =a.project_id ";
		Query query = getSession().createSQLQuery(sql);
		return Integer.valueOf(query.uniqueResult().toString());
	}
}
