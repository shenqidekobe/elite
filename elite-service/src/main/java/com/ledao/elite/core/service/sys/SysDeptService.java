package com.ledao.elite.core.service.sys;

import java.util.List;

import com.ledao.elite.core.domain.sys.SysDept;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.dto.manager.OrganDeptTree;

/**
 * 系统部门接口
 *
 * @author zhiyu cao
 **/

public interface SysDeptService {

	/**
	 * 创建
	 *
	 **/
	SysDept create(SysDept obj) throws EliteServiceException;

	/**
	 * 根据Id查询
	 *
	 **/
	SysDept findById(Long Id) throws EliteServiceException;

	/**
	 * 根据单位Id查询
	 *
	 **/
	SysDept findByOrganId(Long organId) throws EliteServiceException;

	/**
	 * 根据Id物理删除
	 *
	 **/
	SysDept removePhysicalById(Long Id) throws EliteServiceException;

	/**
	 * 根据Id逻辑删除
	 *
	 **/
	SysDept removeLogicById(Long Id) throws EliteServiceException;

	/**
	 * 查询全部
	 *
	 **/
	List<SysDept> findAll() throws EliteServiceException;

	/**
	 * 更新
	 *
	 **/
	SysDept updateSysDept(Long deptId, SysDept obj) throws EliteServiceException;

	/**
	 * 根据单位ID和部门名称查询部门信息
	 * 
	 * @param organId
	 * @param name
	 * @return SysDept
	 */
	SysDept findSysDeptByOrganIdAndName(Long organId, String name) throws EliteServiceException;

	/**
	 * 查询部门树
	 * 
	 * @param isAllOrgan
	 * @param organId
	 * @param parentId
	 * 
	 * @return List<OrganDeptTree>
	 */
	List<OrganDeptTree> findDeptTreeChildren(boolean isAllOrgan, Long organId, Long parentId)
			throws EliteServiceException;

}
