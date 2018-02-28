package com.ledao.elite.core.service.sys;

import java.util.List;

import com.ledao.elite.core.domain.sys.SysRoleMenu;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 系统角色菜单关系接口
 *
 * @author zhiyu cao
 **/

public interface SysRoleMenuService {

	/**
	 * 创建
	 *
	 **/
	SysRoleMenu create(Long roleId,Long menuId) throws EliteServiceException;

	/**
	 * 按角色ID删除
	 * 
	 */
	void removeById(Long Id) throws EliteServiceException;
	
	/**
	 * 查询全部
	 * 
	 */
	List<SysRoleMenu> findAll() throws EliteServiceException;

}
