package com.ledao.elite.core.service.sys;

import java.util.List;

import com.ledao.elite.core.domain.sys.SysMenu;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 系统菜单接口
 *
 * @author zhiyu cao
 **/

public interface SysMenuService {


	/**
	 * 根据Id查询
	 *
	 **/
	SysMenu findById(Long Id) throws EliteServiceException;
	
	/**
	 * 按菜单类型查询列表
	 * 
	 * @param type
	 * @return List<SysMenu>
	 * */
	List<SysMenu> findSysMenuByType(String type)throws EliteServiceException;
	
	/**
	 * 获取roleId拥有的菜单
	 * 
	 * @param roleId
	 * @return List<SysMenu>
	 * */
	List<SysMenu> findSysMenuByRoleId(Long roleId)throws EliteServiceException;
	
	/**
	 * 获取用户的顶级菜单信息
	 * 
	 * @param userId
	 * @return List<SysMenu>
	 * */
	List<SysMenu> findSysUserRootMenus(Long userId)throws EliteServiceException;
	
	/**
	 * 获取用户的所有菜单权限
	 * 
	 * @param userId
	 * @return List<SysMenu>
	 * */
	List<SysMenu> findSysUserAllMenus(Long userId)throws EliteServiceException;


}
