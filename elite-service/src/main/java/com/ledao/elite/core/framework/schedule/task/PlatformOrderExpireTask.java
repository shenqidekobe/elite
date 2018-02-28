package com.ledao.elite.core.framework.schedule.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Status;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.platform.PlatformInOrderService;

/**
 * 平台订单过期时间处理任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class PlatformOrderExpireTask extends ExpireAbstractTask{
	
	public static Integer expireMinute=30;//30分钟过期
	
	@Resource
	private PlatformInOrderService platformInOrderService;
	@Resource
	private PlatformFundService platformFundService;

	@Override
	public void execute() throws EliteBusinessException {
		log.info("*****平台入账订单到期定时任务处理开始执行*****");
		List<PlatformInOrder> orderList=this.platformInOrderService.findPlatformInOrderExpire(expireMinute);
		if(orderList.isEmpty())return;
		for(PlatformInOrder order:orderList){
			log.info("订单号："+order.getOrderId()+"已经过期*****************");
			//首先：更新订单状态
			this.platformInOrderService.updatePlatformInOrderStatus(order.getId(), PlatformInOrder_Status.expire);
			//然后：更新流水状态
			this.platformFundService.updatePlatformFund(order.getOrderId(), PlatformFund_Status.close);
		}
	}
	
}
