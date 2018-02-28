package com.ledao.elite.core.repository.member.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Search_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.utils.CommonUtils;

@Repository
public class MemberPassportRepositoryImpl extends GenericRepositoryImpl<MemberPassport, Long>
		implements MemberPassportRepository {

	// 查询会员列表sql语句
	private static String QUERY_LIST_PREFIX = "select DISTINCT m.* ";
	private static String QUERY_COUNT_PREFIX = "select count(m.id) ";
	private static String QUERY_LIST_SQL = " from t_member_passport m "
			+ "left join t_member_identity f on m.id=f.member_id " + "left join t_member_basic b on m.id=b.member_id "
			+ "left join t_member_credit c on m.id=c.member_id";

	// 查询精英圈列表sql语句
	private static String QUERY_CIRCLE_LIST_PREFIX = "select t.* ";
	private static String QUERY_CIRCLE_COUNT_PREFIX = "select count(t.id) ";
	private static String QUERY_CIRCLE_LIST_SQL = " from t_member_passport t left join t_member_elite e on t.id=e.member_id left join t_member_elite_jobs b on b.elite_id=e.id where e.`status`='normal' ";

	@Override
	public int updateMemberViewCount(Long memberId) {
		String sql = "update t_member_passport set view_count=view_count+1 where id=:memberId";
		Query query = getSession().createSQLQuery(sql);
		query.setLong("memberId", memberId);
		return query.executeUpdate();
	}
	
	@Override
	public int updatePassword(Long memberId,String passSalt,String password){
		String sql = "update t_member_passport set pass_salt=?,password=? where id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setString(0, passSalt);
		query.setString(1, password);
		query.setLong(2, memberId);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Order
	public List<MemberPassport> queryMemberIdentityList(String account){
		String sql="select * from t_member_passport where account like '"+account+"%' order by login_order desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryMemberPassportList(String type, String status, String keyword, String channel,
			boolean isStar, Date startTime, Date endTime, Pager pager) {
		String sql = QUERY_LIST_PREFIX + QUERY_LIST_SQL + " where f.type=:type";
		if (StringUtils.isNotEmpty(status)) {
			sql += " and f.status=:status";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.member_num like '%" + keyword
					+ "%' or b.real_name like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(channel)) {
			sql += " and m.channel=:channel";
		}
		if (startTime != null) {
			sql += " and t.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and t.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		query.setString("type", type);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (StringUtils.isNotEmpty(channel)) {
			query.setString("channel", channel);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryMemberPassportCount(String type, String status, String keyword, String channel, boolean isStar,
			Date startTime, Date endTime) {
		String sql = QUERY_COUNT_PREFIX + QUERY_LIST_SQL + " where f.type=:type";
		if (StringUtils.isNotEmpty(status)) {
			sql += " and f.status=:status";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.member_num like '%" + keyword
					+ "%' or b.real_name like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(channel)) {
			sql += " and m.channel=:channel";
		}
		if (startTime != null) {
			sql += " and t.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and t.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")
					+ "'";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		query.setString("type", type);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (StringUtils.isNotEmpty(channel)) {
			query.setString("channel", channel);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryMemberPassportListByPartnerElite(Long partnerId, Date startTime, Date endTime,
			String keyword, String searchType, Date beforeServenDayTime, Pager pager) {

		String sql = "select * from t_member_passport m"
				 +" left join t_partner_elite p on p.member_id =m.id"
				 +" left join t_member_credit c on c.member_id=m.id";
			// 注册后从未接单
		 if (PartnerElite_Search_Type.no_task_ever.name().equals(searchType)) {
			 sql += "  left join  (select count(id) total,member_id tmember_id from t_project_task_recruit where status='recruit_win' group by member_id) t on t.tmember_id=m.id ";
			 sql += "  left join  (select count(id) total,cto_id cto_id from t_project group by cto_id) k on k.cto_id=m.id where t.total is null and k.total is null";
			 sql+="   and p.partner_id ="+partnerId;
		     }
		 else if(PartnerElite_Search_Type.no_recevieandcurrenttask_in_14days.name().equals(searchType)){
			 sql += " left join  (select count(id) total,member_id tmember_id from t_project_task_recruit where status='recruit_win' and recruit_time>=:overTime group by member_id) t on t.tmember_id=m.id ";
			 sql += " left join  (select count(id) total,cto_id cto_id from t_project group by cto_id) k on k.cto_id=m.id";
			 sql += " left join  (select count(id) total,elite_member_id tmember_id from t_project_stage_task where status='starting'  group by elite_member_id) s on s.tmember_id=m.id ";
			 sql +="  where t.total is null and  k.total is null and s.total is null  and partner_id ="+partnerId;
		 }
		 else{
			  sql+="  where p.partner_id ="+partnerId;
		   }
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.account like '%" + keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}	
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		// 7天未登录
		if (PartnerElite_Search_Type.last_login_over_7days.name().equals(searchType)) {
			sql += " and m.last_login_time <:overTime ";
		}
		//
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		if (PartnerElite_Search_Type.last_login_over_7days.name().equals(searchType)
				|| PartnerElite_Search_Type.no_recevieandcurrenttask_in_14days.name().equals(searchType)) {
			query.setDate("overTime", beforeServenDayTime);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryMemberPassportListByPartnerEliteCount(Long partnerId, Date startTime, Date endTime,
			String keyword, String searchType, Date beforeServenDayTime) {
		String sql = "select count(m.id) from t_member_passport m"
				 +" left join t_partner_elite p on p.member_id =m.id"
				 +" left join t_member_credit c on c.member_id=m.id";
			// 注册后从未接单
		 if (PartnerElite_Search_Type.no_task_ever.name().equals(searchType)) {
				 sql += "  left join  (select count(id) total,member_id tmember_id from t_project_task_recruit where status='recruit_win' group by member_id) t on t.tmember_id=m.id ";
				 sql += "  left join  (select count(id) total,cto_id cto_id from t_project group by cto_id) k on k.cto_id=m.id where t.total is null and k.total is null";
				 sql+="   and p.partner_id ="+partnerId;
		     }
		 else if(PartnerElite_Search_Type.no_recevieandcurrenttask_in_14days.name().equals(searchType)){
			 sql += " left join  (select count(id) total,member_id tmember_id from t_project_task_recruit where status='recruit_win' and recruit_time>=:overTime group by member_id) t on t.tmember_id=m.id ";
			 sql += " left join  (select count(id) total,cto_id cto_id from t_project group by cto_id) k on k.cto_id=m.id";
			 sql += " left join  (select count(id) total,elite_member_id tmember_id from t_project_stage_task where status='starting'  group by elite_member_id) s on s.tmember_id=m.id ";
			 sql +="  where t.total is null and k.total is null and s.total is null and p.partner_id ="+partnerId;
		 }
		 else{
			  sql+="  where  p.partner_id ="+partnerId;
		   }
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.account like '%" + keyword + "%' or c.real_name like '%" + keyword + "%')";
		}
		if (startTime != null) {
			sql += " and m.create_time >=:startTime";
		}	
		if (endTime != null) {
			sql += " and m.create_time<=:endTime";
		}
		// 7天未登录
		if (PartnerElite_Search_Type.last_login_over_7days.name().equals(searchType)) {
			sql += " and m.last_login_time <:overTime ";
		}
		//
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);

		if (startTime != null) {
			query.setDate("startTime", startTime);
		}
		if (endTime != null) {
			query.setDate("endTime", endTime);
		}
		
		if (PartnerElite_Search_Type.last_login_over_7days.name().equals(searchType)
				|| PartnerElite_Search_Type.no_recevieandcurrenttask_in_14days.name().equals(searchType)) {
			query.setDate("overTime", beforeServenDayTime);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryMemberPassportListByMemberPartner(String keyword,String type,String memberPartnerType,Date startTime,Date endTime,Pager pager,String status) {

		String sql = QUERY_LIST_PREFIX + " from t_member_passport m left join t_member_credit c on m.id=c.member_id,";
		if (MemberIdentity_Type.partnerCompany.name().equals(memberPartnerType)) {
			sql = sql + " t_member_partner_company p";
		} else if (MemberIdentity_Type.partnerElite.name().equals(memberPartnerType)) {
			sql = sql + " t_member_partner_elite p";
		}
		sql = sql + "  where m.id=p.member_id and m.current_type='"+memberPartnerType+"'";

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.account like '%" + keyword
					+ "%' or p.channel_num like '%" + keyword + "%' or c.real_name like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(type)) {
			sql += " and p.type=:type";
		}
		if (startTime != null) {
			sql += " and m.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and m.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")+ "'";
		}
		if (StringUtils.isNotEmpty(status)) {
			if(status.equals(Member_Status.disabled.name())){
				sql += " and m.status=:status";
			}else{
				sql += " and m.status!='disabled' and p.status=:status";
			}
			
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		if (StringUtils.isNotEmpty(type)) {
			query.setString("type", type);
		}
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryMemberPassportListByMemberPartnerCount(String keyword, String type, String memberPartnerType,Date startTime,Date endTime,String status) {
		String sql = QUERY_COUNT_PREFIX + " from t_member_passport m left join t_member_credit c on m.id=c.member_id,";
		if (MemberIdentity_Type.partnerCompany.name().equals(memberPartnerType)) {
			sql = sql + " t_member_partner_company p";
		} else if (MemberIdentity_Type.partnerElite.name().equals(memberPartnerType)) {
			sql = sql + " t_member_partner_elite p";
		}
		sql = sql + " where m.id=p.member_id";

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.account like '%" + keyword
					+ "%' or p.channel_num like '%" + keyword + "%' or c.real_name like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(type)) {
			sql += " and p.type=:type";
		}
		if (StringUtils.isNotEmpty(status)) {
			if(status.equals(Member_Status.disabled.name())){
				sql += " and m.status=:status";
			}else{
				sql += " and m.status!='disabled' and p.status=:status";
			}
		}
		if (startTime != null) {
			sql += " and m.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and m.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")+ "'";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(type)) {
			query.setString("type", type);
		}
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryMemberPassportListByMemberElite(String keyword, Boolean ctoed, String status,
			Date startTime,Date endTime,Pager pager,String inviteCode,String role) {

		String sql = QUERY_LIST_PREFIX + " from t_member_passport m left join t_member_elite e on m.id=e.member_id left join t_member_elite_jobs b on b.elite_id=e.id"
			   	+ " where e.ctoed=:ctoed";

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.member_num like '%" + keyword
					+ "%' or m.account like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(inviteCode)) {
			sql += " and m.invite_code=:inviteCode";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and e.status=:status";
		}
		if (StringUtils.isNotEmpty(role)) {
			sql += " and b.job_role=:role";
		}
		if (startTime != null) {
			sql += " and m.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and m.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")+ "'";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		if (StringUtils.isNotEmpty(inviteCode)) {
			query.setString("inviteCode", inviteCode);
		}
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (StringUtils.isNotEmpty(role)) {
			query.setString("role", role);
		}
		String ctoedStr = "N";
		if (ctoed) {
			ctoedStr = "Y";
		}
		query.setString("ctoed", ctoedStr);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryMemberPassportListCountByMemberElite(String keyword, Boolean ctoed, String status,Date startTime,Date endTime,String inviteCode,String role) {
		String sql = QUERY_COUNT_PREFIX + " from t_member_passport m left join t_member_elite e on m.id=e.member_id left join t_member_elite_jobs b on b.elite_id=e.id"
			   	+ " where e.ctoed=:ctoed";

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.member_num like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(inviteCode)) {
			sql += " and m.invite_code=:inviteCode";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and e.status=:status";
		}
		if (startTime != null) {
			sql += " and m.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and m.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")+ "'";
		}
		if (StringUtils.isNotEmpty(role)) {
			sql += " and b.job_role=:role";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(inviteCode)) {
			query.setString("inviteCode", inviteCode);
		}
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		if (StringUtils.isNotEmpty(role)) {
			query.setString("role", role);
		}
		String ctoedStr = "N";
		if (ctoed) {
			ctoedStr = "Y";
		}
		query.setString("ctoed", ctoedStr);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryMemberPassportListByMemberCompany(String keyword, String status,Date startTime,Date endTime,Pager pager) {

		String sql = QUERY_LIST_PREFIX + " from t_member_passport m," + " t_member_company e"
				+ " where m.id=e.member_id and m.current_type = 'company' ";

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.member_num like '%" + keyword
					+ "%' or m.account like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(status)) {
			if(status.equals(Member_Status.disabled.name())){
				sql += " and m.status=:status";
			}else{
				sql += " and m.status!='disabled' and e.status=:status";
			}
		}
		if (startTime != null) {
			sql += " and m.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and m.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")+ "'";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryMemberPassportListCountByMemberCompany(String keyword, String status,Date startTime,Date endTime) {
		String sql = QUERY_COUNT_PREFIX + " from t_member_passport m," + "t_member_company e"
				+ " where  m.id=e.member_id and m.current_type = 'company' ";

		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or m.member_num like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(status)) {
			if(status.equals(Member_Status.disabled.name())){
				sql += " and m.status=:status";
			}else{
				sql += " and m.status!='disabled' and e.status=:status";
			}
		}
		if (startTime != null) {
			sql += " and m.create_time >= '" + DateFormatUtils.format(startTime, "yyyy-MM-dd") + "'";
		}
		if (endTime != null) {
			sql += " and m.create_time <= '" + DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd")+ "'";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> findMemberPassportListForProject(String keyWord, Long projectId) {
		String sql = QUERY_LIST_PREFIX + " from t_member_passport m  where m.id in("
				+ "	select t.elite_member_id from t_project_stage_task t where t.project_id=:id union"
				+ "	select p.company_id from t_project p  where p.id=:projectId ) ";
		if (StringUtils.isNotEmpty(keyWord) && !keyWord.equals("@")) {
			sql += " and (m.nick_name like '%" + keyWord + "%' or m.account like '%" + keyWord + "%') ";
		}
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		query.setLong("id", projectId);
		query.setLong("projectId", projectId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryMemberEliteList(Integer count) {
		String sql = "select m.* from t_member_passport m right join t_member_elite e on m.id=e.member_id where e.status='normal' and m.home_show='Y' order by m.update_time desc limit 0,"
				+ count;
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryCTOList(String keyword) {
		String sql = " select m.* from t_member_passport m " + " left join t_member_credit c on m.id=c.member_id"
				+ " where m.current_type='cto'";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or m.account like '%" + keyword + "%') ";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryMemberEliteCircleList(String keyword, String role, String jobAge, String duration,
			String industry, Pager pager) {
		String sql = QUERY_CIRCLE_LIST_PREFIX + QUERY_CIRCLE_LIST_SQL;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (t.nick_name like '%" + keyword + "%' or t.member_num like '%" + keyword
					+ "%' or t.account like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(role)) {
			sql += " and b.job_role=:role";
		}
		if (StringUtils.isNotEmpty(jobAge)) {
			if (MemberElite.MAX_JOBAGE.toString().equals(jobAge)) {
				sql += " and e.job_age=:jobAge";
			} else {
				sql += " and e.job_age>=:jobAgeMin and e.job_age<=:jobAgeMax";
			}
		}
		if (StringUtils.isNotEmpty(duration)) {
			sql += " and e.allot_duration=:duration";
		}
		if (StringUtils.isNotEmpty(industry)) {
			List<String> list = CommonUtils.separatorStrToList(industry, ",");
			String str = "";
			for (String indus : list) {
				if (str.equals("")) {
					str = "find_in_set('" + indus + "',e.attention_industry)";
				} else {
					str += " or " + "find_in_set('" + indus + "',e.attention_industry)";
				}
			}
			sql += " and (" + str + ")";
		}
		sql += " order by t.create_time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		if (StringUtils.isNotEmpty(role)) {
			query.setString("role", role);
		}
		if (StringUtils.isNotEmpty(jobAge)) {
			if (MemberElite.MAX_JOBAGE.toString().equals(jobAge)) {
				query.setInteger("jobAge", Integer.parseInt(jobAge));
			} else {
				String[] jobAges = StringUtils.split(jobAge, "-");
				query.setInteger("jobAgeMin", Integer.parseInt(jobAges[0]));
				query.setInteger("jobAgeMax", Integer.parseInt(jobAges[1]));
			}
		}
		if (StringUtils.isNotEmpty(duration)) {
			query.setString("duration", duration);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryMemberEliteCircleCount(String keyword, String role, String jobAge, String duration,
			String industry) {
		String sql = QUERY_CIRCLE_COUNT_PREFIX + QUERY_CIRCLE_LIST_SQL;
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (t.nick_name like '%" + keyword + "%' or t.member_num like '%" + keyword
					+ "%' or t.account like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(role)) {
			sql += " and b.job_role=:role";
		}
		if (StringUtils.isNotEmpty(jobAge)) {
			if (MemberElite.MAX_JOBAGE.toString().equals(jobAge)) {
				sql += " and e.job_age=:jobAge";
			} else {
				sql += " and e.job_age>=:jobAgeMin and e.job_age<=:jobAgeMax";
			}
		}
		if (StringUtils.isNotEmpty(duration)) {
			sql += " and e.allot_duration=:duration";
		}
		if (StringUtils.isNotEmpty(industry)) {
			List<String> list = CommonUtils.separatorStrToList(industry, ",");
			String str = "";
			for (String indus : list) {
				if (str.equals("")) {
					str = "find_in_set('" + indus + "',e.attention_industry)";
				} else {
					str += " or " + "find_in_set('" + indus + "',e.attention_industry)";
				}
			}
			sql += " and (" + str + ")";
		}
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(role)) {
			query.setString("role", role);
		}
		if (StringUtils.isNotEmpty(jobAge)) {
			if (MemberElite.MAX_JOBAGE.toString().equals(jobAge)) {
				query.setInteger("jobAge", Integer.parseInt(jobAge));
			} else {
				String[] jobAges = StringUtils.split(jobAge, "-");
				query.setInteger("jobAgeMin", Integer.parseInt(jobAges[0]));
				query.setInteger("jobAgeMax", Integer.parseInt(jobAges[1]));
			}
		}
		if (StringUtils.isNotEmpty(duration)) {
			query.setString("duration", duration);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPassport> queryMemberPassportListByCurrentType(String keyword, String currentType, String status,
			Pager pager, String orderType) {
		String sql = " select m.* from t_member_passport m " + " left join t_member_credit c on m.id=c.member_id"
				+ " left join t_member_elite e on m.id=e.member_id " + " where m.current_type='" + currentType + "'";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or m.account like '%" + keyword + "%') ";
		}
		if (StringUtils.isNotEmpty(orderType)) {
			sql += " order by e.audit_time " + orderType;
		}

		Query query = getSession().createSQLQuery(sql).addEntity(MemberPassport.class);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryMemberPassportListCountByCurrentType(String keyword, String currentType, String status) {
		String sql = " select count(m.id) from t_member_passport m "
				+ " left join t_member_credit c on m.id=c.member_id"
				+ " left join t_member_elite e on m.id=e.member_id " + " where m.current_type='" + currentType + "'";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.nick_name like '%" + keyword + "%' or c.real_name like '%" + keyword
					+ "%' or m.account like '%" + keyword + "%') ";
		}
		sql += " order by m.create_time desc";
		Query query = getSession().createSQLQuery(sql);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public void deleteByMemberId(Long memberId){
		String sql="delete from t_member_projects where member_id=?";
		Query query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_education where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_message where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_identity where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_elite where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_company where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_account where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_basic where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_attention where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_attention where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_partner_elite where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_partner_company where member_id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
		sql="delete from t_member_passport where id=?";
		query = getSession().createSQLQuery(sql);
		query.setLong(0, memberId);
		query.executeUpdate();
	}
}
