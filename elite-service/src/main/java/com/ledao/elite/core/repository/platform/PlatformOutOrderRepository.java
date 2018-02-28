package com.ledao.elite.core.repository.platform;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.platform.PlatformOutOrder;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 平台出账订单接口
 */
public interface PlatformOutOrderRepository extends GenericDAO<PlatformOutOrder, Long> {

	/**
	 * 组合条件查询项目出账订单
	 */
	List<PlatformOutOrder> queryPlatformOutOrderList(Long projectId,String type, String keyword, Date startTime, Date endTime,
			Pager pager,String status);

	/**
	 * 组合条件查询项目出账订单数量
	 */
	Integer queryPlatformOutOrderListCount(Long projectId, String type,String keyword, Date startTime, Date endTime,String status);


	/**
	 *  分页模糊查询
	 * 
	 * @param keyword(项目名称,用户名)
	 * @param pager
	 * @return
	 */
	List<PlatformOutOrder> queryPlatformOutOrdersByKeyword(String keyword,String status,String type, Pager pager);

	/**
	 * 应收管理 分页模糊查询 数量
	 * 
	 * @param keyword(项目名称，用户名)
	 * @param pager
	 * @return
	 */
	Integer queryPlatformOutOrdersCountByKeyword(String keyword,String status,String type);
}
