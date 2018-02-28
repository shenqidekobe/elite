package com.ledao.elite.core.service.sys;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysRole;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统角色接口
 *
 * @author zhiyu cao
 **/

public interface SysRoleService {

	/**
	 * 创建 系统角色
	 *
	 **/
	SysRole createSysRole(SysRole obj) throws EliteServiceException;

	/**
	 * 保存角色的菜单信息
	 */
	void createSysRoleMenu(Long roleId, String menuIds) throws EliteServiceException;

	/**
	 * 根据Id查询
	 * 
	 */
	SysRole findSysRoleById(Long id) throws EliteServiceException;

	/**
	 * 按ID物理删除
	 * 
	 */
	SysRole removePhysicalById(Long Id) throws EliteServiceException;

	/**
	 * 根据关联判断后是否删除
	 * 
	 */
	SysRole removeSysRoleById(Long Id) throws EliteServiceException;

	/**
	 * 更新
	 * 
	 */
	SysRole updateSysRole(Long id, SysRole role) throws EliteServiceException;

	/**
	 * 分页查询
	 * 
	 */
	SearchResult<SysRole> findSysRoleList(String status, Pager pager) throws EliteServiceException;

	/**
	 * 查询全部
	 * 
	 */
	List<SysRole> findAll() throws EliteServiceException;

	/**
	 * 根据id name 查询
	 *
	 * 
	 */
	SysRole findSysRoleByIdAndName(Long id, String name) throws EliteServiceException;
}
