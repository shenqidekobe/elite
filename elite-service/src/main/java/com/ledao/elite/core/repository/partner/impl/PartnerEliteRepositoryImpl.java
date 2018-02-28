package com.ledao.elite.core.repository.partner.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Status;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.partner.PartnerEliteRepository;

@Repository
public class PartnerEliteRepositoryImpl extends GenericRepositoryImpl<PartnerElite, Long>
		implements PartnerEliteRepository {

	@Override
	public SearchResult<PartnerElite> findPartnerListByNameAndPhoneAndEmailAndStatus(Long partnerId, String name,
			String phone, String email, String status, Pager pager) {

		if (pager == null)
			pager = new Pager();
		Search search = new Search(PartnerElite.class);
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
	public List<PartnerElite> findPartnerEliteByKeyword(Long memberId, String status, String keyword, Long partnerId,
			String recorded, Date startTime, Date endTime, Pager pager) {
		String sql = "select m.* " + "from t_partner_elite m left join t_attas ta on m.atta_id=ta.id"
				+ " where m.partner_id ='" + partnerId + "'";

		// 成功入驻
		if ("inter".equals(recorded)) {
			sql += " and m.member_id !=''";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.phone like '%" + keyword + "%' or m.define_role like '%"
					+ keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			if (status.equals(PartnerElite_Status.noRegister.name())) {
				sql += "and (m.status=:status or m.status='audit_nopass')";
			} else {
				sql += " and m.status=:status";
			}
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerElite.class);

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
	public Integer findPartnerEliteByKeywordCount(Long memberId, String status, String keyword, Long partnerId,
			String recorded, Date startTime, Date endTime, Pager pager) {
		String sql = "select count(m.id)" + "from t_partner_elite m " + " where m.partner_id ='" + partnerId + "'";
		if ("true".equals(recorded)) {
			sql += " and m.member_id !=''";
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.phone like '%" + keyword + "%' or m.define_role like '%"
					+ keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			if (status.equals(PartnerElite_Status.noRegister.name())) {
				sql += "and (m.status=:status or m.status='audit_nopass')";
			} else {
				sql += " and m.status=:status";
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
	public List<PartnerElite> findPartnerEliteListByKeywordAndStatus(Long partnerId, String status, String keyword,
			Date startTime, Date endTime, Pager pager) {
		String sql = "select m.* " + " from t_partner_elite m left join t_member_passport p on p.id =m.member_id"
				+ "  left join t_member_credit c on c.member_id =m.member_id" + "  where m.partner_id=" + partnerId;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.phone like '%" + keyword + "%' or p.nick_name like '%"
					+ keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			if (PartnerElite_Status.noRegister.name().equals(status)) {
				sql += " and (m.status=:status or m.status ='audit_nopass')";
			} else {
				sql += " and m.status=:status ";
			}
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerElite.class);

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
	public Integer findPartnerEliteListByKeywordAndStatusCount(Long partnerId, String status, String keyword,
			Date startTime, Date endTime) {
		String sql = " select count(m.id) "
				+ " from t_partner_elite m left join t_member_passport p on p.id =m.member_id"
				+ "  left join t_member_credit c on c.member_id =m.member_id" + " where m.partner_id=" + partnerId;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.name like '%" + keyword + "%' or m.phone like '%" + keyword + "%' or p.nick_name like '%"
					+ keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		if (StringUtils.isNotEmpty(status)) {
			if (PartnerElite_Status.noRegister.name().equals(status)) {
				sql += " and (m.status=:status or m.status ='audit_nopass')";
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
	public List<PartnerElite> findPartnerEliteInComeList(Long partnerId, String incomeType, String status,
			Date startTime, Date endTime, Pager pager) {
		String sql = "select m.* from t_partner_elite m  left join "
				+ " (select sum(amount) allamount ,source_member_id from t_member_income where 1=1";
		
		if (incomeType != null) {
			sql += " and income_type =:incomeType";
		}
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}

		sql += " group by source_member_id) a  on m.member_id=a.source_member_id where m.partner_id ="
				+ partnerId;

		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
		}
		sql += " order by a.allamount desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerElite.class);
		
		if (StringUtils.isNotEmpty(incomeType)) {
			query.setString("incomeType", incomeType);
		}
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
	public Integer findMemberPartnerEliteListInComeCount(Long partnerId, String incomeType, String status,
			Date startTime, Date endTime) {
		String sql = "select count(m.id) from t_partner_elite m  left join "
				+ " (select sum(amount) allamount ,source_member_id from t_member_income ";
		sql += " where income_type ='" + incomeType + "'";
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}
		sql += " group by source_member_id) a  on m.member_id=a.source_member_id where a.allamount is not null and m.partner_id ="
				+ partnerId;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<PartnerElite> findPartnerEliteDirectInComeList(Long partnerId, String status, Date startTime,
			Date endTime, Pager pager) {
		String sql = "select m.* from t_partner_elite m   "
				+ " left join t_member_partner_elite p on p.id= m.partner_id"
				+ " left join (select sum(amount) allamount ,member_id from t_member_income ";
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

		sql += " group by member_id) a  on m.member_id=a.member_id where p.parent_id =" + partnerId;

		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
		}
		sql += " order by a.allamount desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerElite.class);

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
	public Integer findMemberPartnerEliteListInDirectInComeCount(Long partnerId, String status, Date startTime,
			Date endTime) {
		String sql = "select count(m.id) from t_partner_elite m  "
				+ " left join t_member_partner_elite p on p.id= m.partner_id"
				+ " left join (select sum(amount) allamount ,member_id from t_member_income ";
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

		sql += " group by member_id) a  on m.member_id=a.member_id where p.parent_id =" + partnerId;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PartnerElite> findPartnerEliteInDirectInComeList(Long partnerId, String status, Date startTime,
			Date endTime, Pager pager) {
		String sql = "select m.* from t_partner_elite m   "
				+ " left join (select eson.* from t_member_partner_elite eson,t_member_partner_elite efather where efather.id=eson.parent_id and efather.parent_id ="
				+ partnerId + ")  p on p.id= m.partner_id"
				+ " left join (select sum(amount) allamount ,member_id from t_member_income ";
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

		sql += " group by member_id) a  on m.member_id=a.member_id where p.id is not null";

		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status ";
		}
		sql += " order by a.allamount desc";
		Query query = getSession().createSQLQuery(sql).addEntity(PartnerElite.class);

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
	public Integer findMemberPartnerEliteListDirectInComeCount(Long partnerId, String status, Date startTime,
			Date endTime) {
		String sql = "select count(m.id) from t_partner_elite m  "
				+ " left join (select eson.* from t_member_partner_elite eson,t_member_partner_elite efather where efather.id=eson.parent_id and efather.parent_id ="
				+ partnerId + ")  p on p.id= m.partner_id"
				+ " left join (select sum(amount) allamount ,member_id from t_member_income ";
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
	public Integer findPartnerEliteCountByIncomeDataAndPartnerId(Long partnerId, Long memberId, String incomeType,
			Date startTime, Date endTime) {
		String sql = "select  count( distinct e.id) from t_partner_elite e "
				+ " left join  (select  sum(amount) allamount,source_member_id from t_member_income"
				+ " where income_type ='" + incomeType + "' and member_id =" + memberId;
		if (startTime != null) {
			sql += " and create_time >=:startTime";
		}
		if (endTime != null) {
			sql += " and create_time<=:endTime";
		}
		sql += " group by source_member_id)p on p.source_member_id =e.member_id where p.allamount is not null and p.source_member_id is not null and e.partner_id ="
				+ partnerId;
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
	public void updatePartnerEliteIdBysql(String status, Long id) {
		if(id!=null){
		String sql="update t_partner_elite set status ='"+status+"' where id=" +id;
		Query query=getSession().createSQLQuery(sql);
		query.executeUpdate();
		}
	}
}
