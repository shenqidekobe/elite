package com.ledao.elite.core.service.platform;

import java.math.BigDecimal;
import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformOutOrder;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 平台出账订单服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface PlatformOutOrderService {

	/**
	 * 创建出账订单
	 * 
	 * @param platformOutOrder
	 * @return platformOutOrder
	 */
	PlatformOutOrder createPlatformOutOrder(PlatformOutOrder order) throws EliteServiceException;
	
	/**
	 * 系统系统结算时快速创建出账订单
	 * 
	 */
	void createPlatformOutOrder(Long memberId,PlatformOutOrder_Type type,Long projectId,Long stageId,Long taskId,BigDecimal amount,BigDecimal tax) throws EliteServiceException;

	/**
	 * 更新出账订单
	 * 
	 * @param platformOutOrder
	 * @return platformOutOrder
	 */
	PlatformOutOrder updatePlatformOutOrder(PlatformOutOrder order) throws EliteServiceException;

	/**
	 * 按订单号查询
	 * 
	 * @param orderId
	 * @return platformOutOrder
	 */
	PlatformOutOrder findPlatformOutOrderByOrderId(String orderId) throws EliteServiceException;
	/**
	 * 按订单号查询
	 * 
	 * @param orderId
	 * @return platformOutOrder
	 */
	PlatformOutOrder findPlatformOutOrderById(Long id) throws EliteServiceException;

	/**
	 * 查询平台出账订单列表
	 * 
	 * @param pager
	 * @return SearchResult<PlatformOutOrder>
	 */
	SearchResult<PlatformOutOrder> findPlatformOutOrderList(String keyword,String status,
			String type, Pager pager) throws EliteServiceException;

	/**
	 * 根据projectId 平台出账订单
	 */
	SearchResult<PlatformOutOrder> findPlatformOutOrderListByProjectId(String status,
			Long projectId, Pager pager,String keyword,Date startTime,Date endTime) throws EliteServiceException;

}
