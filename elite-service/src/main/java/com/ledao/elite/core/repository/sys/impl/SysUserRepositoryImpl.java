package com.ledao.elite.core.repository.sys.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysUserRepository;

@Repository
public class SysUserRepositoryImpl extends GenericRepositoryImpl<SysUser, Long> implements SysUserRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<SysUser> querSearchSysUsersList(String keyword, String status, Long roleId, Pager pager) {
		if(pager==null){
			pager=new Pager();
		}
		String sql = "select m.* from t_sys_user m" + " where m.status=:status ";
		if (roleId != null) {
			sql += " and  m.id in (select user_id from t_sys_user_role where role_id=" + roleId + ")";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.login_name like '%" + keyword + "%' or m.user_name like '%" + keyword
					+ "%'or m.phone like '%" + keyword + "%')";
		}
		sql += " order by m.create_time desc ";
		Query query = getSession().createSQLQuery(sql).addEntity(SysUser.class);
		query.setString("status", status);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer querSearchSysUsersListCount(String keyword, String status, Long roleId) {
		String sql = "select count(m.id) from t_sys_user m" + " where m.status=:status ";
		if (roleId != null) {
			sql += " and  m.id in (select user_id from t_sys_user_role where role_id=" + roleId + ")";
		}
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.login_name like '%" + keyword + "%' or m.user_name like '%" + keyword
					+ "%'or m.phone like '%" + keyword + "%')";
		}
		sql += " order by m.create_time desc ";
		Query query = getSession().createSQLQuery(sql);
		query.setString("status", status);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysUser> querSearchSysUsersByRoleId(String keyword, String status, Long roleId, Pager pager) {
		String sql = "select m.* from t_sys_user m where m.id in (select user_id from t_sys_user_role where role_id="
				+ roleId + ")";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.login_name like '%" + keyword + "%' or m.user_name like '%" + keyword + "%' or m.phone like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status";
		}
		sql += " order by m.user_name ";
		Query query = getSession().createSQLQuery(sql).addEntity(SysUser.class);

		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer querSearchSysUsersByRoleIdCount(String keyword, String status, Long roleId) {
		String sql = "select count(m.id) from t_sys_user m where m.id in (select user_id from t_sys_user_role where role_id="
				+ roleId + ")";
		if (StringUtils.isNotEmpty(keyword)) {
			sql += " and (m.login_name like '%" + keyword + "%' or m.user_name like '%" + keyword + "%')";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and m.status=:status";
		}
		sql += " order by m.user_name ";
		Query query = getSession().createSQLQuery(sql);
		if (StringUtils.isNotEmpty(status)) {
			query.setString("status", status);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

}
