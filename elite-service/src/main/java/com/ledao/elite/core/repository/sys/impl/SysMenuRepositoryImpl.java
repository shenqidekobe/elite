package com.ledao.elite.core.repository.sys.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.SysMenu;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysMenuRepository;

@SuppressWarnings("unchecked")
@Repository
public class SysMenuRepositoryImpl extends GenericRepositoryImpl<SysMenu, Long> implements SysMenuRepository {
	
	private static String QUERY_MENU_HQL="select DISTINCT m.* from t_sys_menu m "
			+ "left join t_sys_role_menu rm on m.id=rm.menu_id "
			+ "left join t_sys_role r on r.id=rm.role_id "
			+ "left join t_sys_user_role ur on ur.role_id=r.id "
			+ "left join t_sys_user u on u.id=ur.user_id where m.`status`='normal' and u.id=?";

	
	@Override
	public List<SysMenu> queryUserRootMenu(Long userId) {
		String hql=QUERY_MENU_HQL+" and (m.parent_id is null or m.parent_id=null) order by m.orders asc";
		Query query=getSession().createSQLQuery(hql).addEntity(SysMenu.class);
		query.setLong(0, userId);
		return query.list();
	}
	
	@Override
	public List<SysMenu> queryUserChildMenu(Long userId,Long parentId){
		String hql=QUERY_MENU_HQL+" and m.parent_id=? order by m.orders asc";
		Query query=getSession().createSQLQuery(hql).addEntity(SysMenu.class);
		query.setLong(0, userId);
		query.setLong(1, parentId);
		return query.list();
	}


	@Override
	public List<SysMenu> queryUserAllMenu(Long userId) {
		String hql=QUERY_MENU_HQL+" order by m.orders asc";
		Query query=getSession().createSQLQuery(hql).addEntity(SysMenu.class);
		query.setLong(0, userId);
		return query.list();
	}

}
