package com.ledao.elite.core.repository.sys;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.SysMenu;

/**
 * 系统菜单接口
 * 
 * @author kobe.liu
 * */
public interface SysMenuRepository extends GenericDAO<SysMenu, Long> {

	/**
	 * 查询用户分配的顶级菜单
	 * @param userId
	 * List<SysMenu>
	 * */
	List<SysMenu> queryUserRootMenu(Long userId);
	
	/**
	 * 查询用户分配的子级菜单
	 * @param userId
	 * @param parentId
	 * List<SysMenu>
	 * */
	List<SysMenu> queryUserChildMenu(Long userId,Long parentId);
	
	/**
	 * 查询用户拥有的所有菜单权限
	 * @param userId
	 * @return List<SysMenu>
	 * */
	List<SysMenu> queryUserAllMenu(Long userId);
}
