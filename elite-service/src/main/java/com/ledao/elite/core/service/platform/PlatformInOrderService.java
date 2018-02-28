package com.ledao.elite.core.service.platform;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Status;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 平台入账订单服务接口
 */
public interface PlatformInOrderService {

	/**
	 * 创建平台订单
	 * 
	 * @param platformInOrder
	 * @return orderId
	 */
	String createPlatformInOrder(PlatformInOrder obj) throws EliteServiceException;
	
	
	/**
	 * 创建线下支付订单
	 * 
	 * @param platformInOrder
	 * @return orderId
	 */
	String createPlatformInOrderOffline(PlatformInOrder order,ProjectDefineStage stage) throws EliteServiceException;
	

	/**
	 * 更新平台订单信息，处理回调的更新
	 * 
	 * @param orderNum:订单号
	 * @param totalFee:交易金额
	 * @param tradeNum:交易订单号
	 * @param buyerEmail:买家帐号
	 * @param payTime:交易付款时间
	 * @param isSuccess:是否成功
	 */
	void updatePlatformInOrderAsCallback(String orderNum, String totalFee, String tradeNum, String buyerEmail,
			Date payTime, boolean isSuccess) throws EliteServiceException;
	
	/**
	 * 更新订单状态
	 * 
	 * @param id
	 * @param status
	 * */
	void updatePlatformInOrderStatus(Long id,PlatformInOrder_Status status)throws EliteServiceException;
	
	/**
	 * 按ID查询订单
	 * 
	 * @param id
	 * @return platformInOrder
	 */
	PlatformInOrder findPlatformInOrderById(Long id) throws EliteServiceException;

	/**
	 * 按订单号查询订单
	 * 
	 * @param orderId
	 * @return platformInOrder
	 */
	PlatformInOrder findPlatformInOrderByOrderId(String orderId) throws EliteServiceException;

	/**
	 * 按项目阶段ID查询
	 * 
	 * @param orderId
	 * @return List<PlatformInOrder>
	 */
	List<PlatformInOrder> findPlatformInOrderByStageId(Long stageId,PlatformInOrder_Status status) throws EliteServiceException;
	
	/**
	 * 查询过期的未支付订单
	 * 
	 * @param expireMinute
	 * @return List<PlatformInOrder>
	 * */
	List<PlatformInOrder> findPlatformInOrderExpire(Integer expireMinute)throws EliteServiceException;

	/**
	 * 模糊查询
	 * 
	 * @param keyword
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PlatformInOrder> findPlatformInOrdersByKey(String keyword,String status,String type,String payType, Pager pager) throws EliteServiceException;

}
