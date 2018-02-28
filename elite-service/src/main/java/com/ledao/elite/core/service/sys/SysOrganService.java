package com.ledao.elite.core.service.sys;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysOrgan;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统单位接口
 *
 * @author zhiyu cao
 **/

public interface SysOrganService {

	/**
	 * 创建
	 *
	 **/
	SysOrgan create(SysOrgan obj) throws EliteServiceException;

	/**
	 * 分页查询单位
	 *
	 **/
	SearchResult<SysOrgan> findSysOrganList(String name, String status,Long  parentId,Long areaId, Pager pager)
			throws EliteServiceException;
	/**
	 * 按ID逻辑删除
	 * 
	 * @param Id
	 * @return SysOrgan
	 */
	SysOrgan removeLogicById(Long id) throws EliteServiceException;

	/**
	 * 根据ID查询
	 * 
	 */
	SysOrgan findSysOrganById(Long organId) throws EliteServiceException;

	/**
	 * 更新
	 * 
	 */
	SysOrgan updateSysOrgan(long id, SysOrgan obj) throws EliteServiceException;

	/**
	 * 根据id name 查询
	 * 
	 */
	
	SysOrgan findSysOrganByIdAndName(Long id,String name) throws EliteServiceException;
	/**
	 * 
	 * */
	List<SysOrgan> findSysOrganAll() throws EliteServiceException;
	

}
