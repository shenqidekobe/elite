package com.ledao.elite.core.repository.sys.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.SysRoleMenu;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysRoleMenuRepository;

@Repository
public class SysRoleMenuRepositoryImpl extends GenericRepositoryImpl<SysRoleMenu, Long> implements SysRoleMenuRepository {
	
	
	@Override
	public int deleteSysRoleMenu(Long roleId){
		String hql="delete from SysRoleMenu where roleId=:roleId";
		Query query=getSession().createQuery(hql);
		query.setLong("roleId", roleId);
		return query.executeUpdate();
	}

}
