package com.ledao.elite.core.repository.sys;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.SysDept;

/**
 * 系统部门接口
 * 
 * @author kobe.liu
 * */
public interface SysDeptRepository extends GenericDAO<SysDept, Long> {

	/**
	 * 按单位ID和上级部门ID查询部门，按orders倒序
	 * 
	 * @param organId
	 * @return List<SysDept>
	 * */
	List<SysDept> querySysDeptByOrganIdAndParentId(Long organId,Long parentId);
}
