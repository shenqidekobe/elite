package com.ledao.elite.core.repository.platform;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Status;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Type;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 平台入账订单接口
 */
public interface PlatformInOrderRepository extends GenericDAO<PlatformInOrder, Long> {

	/**
	 * 查询订单
	 * 
	 * @param projectId
	 * @param memberId
	 * @param type
	 * @param status
	 * @return PlatformInOrder
	 */
	PlatformInOrder queryPlatformInOrder(Long projectId, Long memberId, PlatformInOrder_Type type,
			PlatformInOrder_Status status);

	/**
	 * 应收管理 分页模糊查询
	 * 
	 * @param keyword(项目名称，编号)
	 * @param pager
	 * @return
	 */
	List<PlatformInOrder> queryPlatformInOrdersByKeyword(String keyword,String status,String type,String payType, Pager pager);

	/**
	 * 应收管理 分页模糊查询 数量
	 * 
	 * @param keyword(项目名称，编号)
	 * @param pager
	 * @return
	 */
	Integer queryPlatformInOrdersCountByKeyword(String keyword,String status,String type,String payType);
}
