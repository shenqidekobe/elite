package com.ledao.elite.core.repository.partner.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.partner.PartnerProjectRepository;

@Repository
public class PartnerProjectRepositoryImpl extends GenericRepositoryImpl<PartnerProject, Long>
		implements PartnerProjectRepository {

	@Override
	public SearchResult<PartnerProject> findPartnerProjectListByNameAndPhoneAndEmailAndStatus(Long partnerId,
			String name, String phone, String email, String status, Pager pager) {

		if (pager == null)
			pager = new Pager();
		Search search = new Search(PartnerProject.class);
		search.addFilterEqual("status", status);
		search.addFilterEqual("partnerId", partnerId);
		if (null != name) {
			search.addFilterLike("name", "%" + name + "%");
		}
		if (null != phone) {
			search.addFilterLike("phone", "%" + phone + "%");
		}
		if (null != email) {
			search.addFilterLike("email", "%" + email + "%");
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.searchAndCount(search);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartnerProject> findPartnerProjectByKeyword(Long memberId, String status, String keyword,
			Long partnerId, Pager pager) {
		//TODO:需根据最新的推荐项目记录查询
		String sql = "select m.* " + "from t_partner_project m ,t_project p" + " where m.project_id =p.id"
				+ "  and m.partner_id in(select mp.id from t_member_partner_company mp where mp.member_id =" + memberId
				+ ") ";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or p.contact_name like '%" + keyword
					+ "%' or p.contact_phone like '%" + keyword + "%')";
		}

		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status";
		}
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerProject.class);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer findPartnerProjectByKeywordCount(Long memberId, String status, String keyword, Long partnerId) {
		//TODO:需根据最新的推荐项目记录查询
		String sql = "select count(m.id)" + "from t_partner_project m ,t_project p" + " where m.project_id =p.id"
				+ "  and m.partner_id in(select mp.id from t_member_partner_company mp where mp.member_id =" + memberId
				+ ") ";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (p.name like '%" + keyword + "%' or p.contact_name like '%" + keyword
					+ "%' or p.contact_phone like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status";
		}
		Query query = getSession().createSQLQuery(sql);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public Integer findPartnerProjectByPartnerIdCount(Long partnerId, String type) {

		String sql = "select count(m.id)" + "from t_partner_project m" + " where m.partner_id = " + partnerId;
		if ("entered".equals(type)) {
			sql = sql + " and  m.project_id !=''";
		}
		Query query = getSession().createSQLQuery(sql);

		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartnerProject> findPartnerProjectsByStatusAndKeyWorld(long partnerId, String areaName, String keyword,
			String entryed, String status,Date startTime,Date endTime,Pager pager) {
		String sql = "select m.* " + "from t_partner_project m "
				+ " where m.partner_id ='" + partnerId + "'";

		if ("true".equals(entryed)) {
			sql += " and m.project_id !=''";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.project_name like '%" + keyword
					+ "%' or m.phone like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
		}
		if(startTime!=null){
			sql+=" and m.create_time >=:startTime";
		}
		if(endTime!=null){
			sql+=" and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerProject.class);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
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
	public Integer findPartnerProjectsByStatusAndKeyWorldCount(long partnerId, String areaName, String keyword,
			String entryed, String status,Date startTime,Date endTime) {
		String sql = "select count(m.id) " + "from t_partner_project m " + " where m.partner_id ='" + partnerId + "'";

		if ("true".equals(entryed)) {
			sql += " and m.project_id !=''";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.project_name like '%" + keyword
					+ "%' or m.phone like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
		}
		if(startTime!=null){
			sql+=" and m.create_time >=:startTime";
		}
		if(endTime!=null){
			sql+=" and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (startTime!=null) {
			query.setDate("startTime",startTime);
		} 
		if (endTime!=null) {
			query.setDate("endTime", endTime);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
	@Override
	public Integer findPartnerProjectByKeywordAndTimeCount(Long partnerId, String status,Date startTime,Date endTime) {
		 String sql = "select count(m.id)"
					+ "from t_partner_project m "
		             +" where m.partner_id ='"+partnerId+"'";
			if(startTime!=null){
				sql+=" and m.create_time >=:startTime";
			}
			if(endTime!=null){
				sql+=" and m.create_time<=:endTime";
			}
			if (StringUtils.isNotEmpty(status)) {
				sql += " and m.status=:status";
			}
			Query query = getSession().createSQLQuery(sql);

			if (StringUtils.isNotEmpty(status)) {
				query.setString("status", status);
			}
			if (startTime!=null) {
				query.setDate("startTime",startTime);
			} 
			if (endTime!=null) {
				query.setDate("endTime", endTime);
			} 
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public void updatePartnerProjectStatusBySql(String status, Long id) {
		if(id!=null){
			String sql="update t_partner_project set status ='"+status+"' where id=" +id;
			Query query=getSession().createSQLQuery(sql);
			query.executeUpdate();
			}		
	}

}
