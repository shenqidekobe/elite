package com.ledao.elite.core.service.platform;

import java.math.BigDecimal;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformFund;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 平台资金流水服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface PlatformFundService {
	
	/**
	 * 新建流水号
	 * 
	 * @return platformFund
	 * */
	PlatformFund createPlatformFund(String title,String orderId,Long projectId,Long stageId,Long memberId,BigDecimal amount,PlatformFund_Type type,PlatformFund_Status status)throws EliteServiceException;
	
	/**
	 * 更新流水状态
	 * 
	 * @param orderId
	 * @param status
	 * */
	void updatePlatformFund(String orderId,PlatformFund_Status status)throws EliteServiceException;
	
	/**
	 * 查看流水详情
	 * 
	 * @param id
	 * @return platformFund
	 * */
	PlatformFund findPlatformFundById(Long id)throws EliteServiceException;
	
	/**
	 * 流水列表查询
	 * 
	 * @param memberId
	 * @param projectId
	 * @param type
	 * @param pager
	 * @return SearchResult<PlatformFund>
	 * */
	SearchResult<PlatformFund> findPlatformFundList(Long memberId,Long projectId,String type,String keyword,Pager pager)throws EliteServiceException;
	
}
