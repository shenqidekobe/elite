package com.ledao.elite.core.repository.sys;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysOrgan;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统单位接口
 * 
 * @author kobe.liu
 */
public interface SysOrganRepository extends GenericDAO<SysOrgan, Long> {

	/**
	 * 
	 * 根据name模糊 分页查询
	 * 
	 * @param name
	 * @param status
	 * @param deptId
	 * @param pager
	 * @return SearchResult<SysOrgan>
	 */
	SearchResult<SysOrgan> fuzzySearchSysOrgans(String name,String status, Long parentId, Long areaId, Pager pager);

	/**
	 * 查询当前最大orders
	 * @return
	 */
	Integer querySysOrganMaxOrders();
}
