package com.ledao.elite.core.framework.plugin.pay;

import java.util.Map;

import com.ledao.elite.core.exception.EliteBusinessException;

/**
 * 支付接口
 * */
public interface PaymentService {
	
	/**
	 * 支付接口
	 * 
	 * @param payParams
	 * 
	 * @return Map
	 * */
	Map<String,Object> pay(Map<String,Object> payParams)throws EliteBusinessException;
   
	/**
	 * 查询接口
	 * 
	 * @param queryParams
	 * 
	 * @return Map
	 * */
	Map<String,Object> query(Map<String,Object> queryParams)throws EliteBusinessException;
	
	/**
	 * 退款接口
	 * 
	 * @param refundParams
	 * 
	 * @return Map
	 * */
	Map<String,Object> refund(Map<String,Object> refundParams)throws EliteBusinessException;
	
	
	/**
	 * 企业代付接口
	 * 
	 * @param transferParams
	 * 
	 * @return Map
	 * */
	Map<String,Object> transfer(Map<String,Object> transferParams)throws EliteBusinessException;
	
	/**
	 * 企业代付查询
	 * 
	 * @param id
	 * 
	 * @return Map
	 * */
	Map<String,Object> queryTransfer(String id)throws EliteBusinessException;
}
