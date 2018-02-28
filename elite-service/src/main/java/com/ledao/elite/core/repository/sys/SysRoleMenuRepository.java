package com.ledao.elite.core.repository.sys;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.SysRoleMenu;

/**
 *  系统角色菜单关系接口
 * 
 * @author kobe.liu
 * */
public interface SysRoleMenuRepository extends GenericDAO<SysRoleMenu, Long> {
	
	/**
	 * 按角色ID删除
	 * @param roleId
	 * @return  int
	 * */
	int deleteSysRoleMenu(Long roleId);

}
