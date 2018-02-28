package com.ledao.elite.core.service.platform.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformOutOrder;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Status;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Type;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.platform.PlatformOutOrderRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;
import com.ledao.elite.core.service.sys.CommonCodeService;

@Service("platformOutOrderService")
public class PlatformOutOrderServiceImpl extends BaseSerivceImpl implements PlatformOutOrderService {

	@Resource
	private PlatformOutOrderRepository platformOutOrderRepository;
	@Resource
	private CommonCodeService commonCodeService;

	@Override
	public PlatformOutOrder createPlatformOutOrder(PlatformOutOrder order) throws EliteServiceException {
		this.verifyParams(order, order.getOrderAmount(), order.getMemberId(), order.getType());
		String orderId = commonCodeService.disposeOddNumber("outOrderNum", COMMON_PREVAL.DU.name(), "yyyyMMdd", 8,
				null);
		order.setOrderId(orderId);
		order.setStatus(PlatformOutOrder_Status.wait_pay);
		this.platformOutOrderRepository.save(order);
		return order;
	}

	@Override
	public void createPlatformOutOrder(Long memberId, PlatformOutOrder_Type type, Long projectId, Long stageId,
			Long taskId, BigDecimal amount,BigDecimal taxAmount) throws EliteServiceException {
		PlatformOutOrder order = new PlatformOutOrder();
		String orderId = commonCodeService.disposeOddNumber("outOrderNum", COMMON_PREVAL.DU.name(), "yyyyMMdd", 8,
				null);
		order.setOrderId(orderId);
		order.setStatus(PlatformOutOrder_Status.success);
		order.setType(type);
		order.setMemberId(memberId);
		order.setProjectId(projectId);
		order.setStageId(stageId);
		order.setTaskId(taskId);
		order.setOrderAmount(amount.add(taxAmount));
		order.setReceiptAmount(amount);
		order.setTaxAmount(taxAmount);
		this.platformOutOrderRepository.save(order);
	}

	@Override
	public PlatformOutOrder updatePlatformOutOrder(PlatformOutOrder order) throws EliteServiceException {
		this.verifyParams(order, order.getId(), order.getOrderId());
		this.platformOutOrderRepository.save(order);
		return order;
	}

	@Override
	public PlatformOutOrder findPlatformOutOrderByOrderId(String orderId) throws EliteServiceException {
		this.verifyParams(orderId);
		Search search = new Search();
		search.addFilterEqual("orderId", orderId);
		return this.platformOutOrderRepository.searchUnique(search);
	}
	@Override
	public PlatformOutOrder findPlatformOutOrderById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.platformOutOrderRepository.find(id);
	}

	@Override
	public SearchResult<PlatformOutOrder> findPlatformOutOrderList(String keyword,String status,
			String type, Pager pager) throws EliteServiceException {
		SearchResult<PlatformOutOrder> sr = new SearchResult<>();
		List<PlatformOutOrder> memberList = this.platformOutOrderRepository.queryPlatformOutOrdersByKeyword(keyword,
				status,type,pager);
		Integer totalCount = this.platformOutOrderRepository.queryPlatformOutOrdersCountByKeyword(keyword,
				status,type);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public SearchResult<PlatformOutOrder> findPlatformOutOrderListByProjectId(String status,
			Long projectId, Pager pager,String keyword,Date startTime,Date endTime) throws EliteServiceException {
		
		SearchResult<PlatformOutOrder> sr = new SearchResult<>();
		List<PlatformOutOrder> memberList = this.platformOutOrderRepository.queryPlatformOutOrderList(projectId,"task_income", keyword, startTime, endTime, pager, status);
		Integer totalCount = this.platformOutOrderRepository.queryPlatformOutOrderListCount(projectId,"task_income", keyword, startTime, endTime, status);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

}
