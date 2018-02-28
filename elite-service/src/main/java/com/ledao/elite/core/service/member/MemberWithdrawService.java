package com.ledao.elite.core.service.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.member.MemberWithdraw.Withdraw_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Way;

/**
 * 提现服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface MemberWithdrawService {

	/**
	 * 申请提现
	 * 
	 * @param withdraw
	 * @return memberWithdraw
	 * */
	MemberWithdraw createMemberWithdraw(MemberWithdraw withdraw)throws EliteServiceException;
	
	/**
	 * 更新提现信息
	 * 
	 * @param withdraw
	 * @return memberWithdraw
	 * */
	MemberWithdraw updateMemberWithdraw(MemberWithdraw withdraw)throws EliteServiceException;
	
	/**
	 * 更新提现数据
	 * 
	 * @param withdraw
	 * @return memberWithdraw
	 * */
	MemberWithdraw updateMemberWithdrawNofix(MemberWithdraw withdraw)throws EliteServiceException;
	
	/**
	 * 财务确认提现，开始执行提醒任务
	 * 
	 * @param id
	 * @param invoiceAttaId
	 * @param withdrawAttaId
	 * @param isAffirm
	 * @param reason
	 * @param minute
	 * */
	void updateMemberWithdrawAsFinanceAffirm(Long id,Long invoiceAttaId,Long withdrawAttaId,boolean isAffirm,String reason,Integer minute)throws EliteServiceException;
	
	/**
	 * 处理提现的回调
	 * 
	 * @param orderId
	 * @param receiptAmount
	 * @param payWay
	 * @param processTime
	 * @param isSuccess
	 * */
	void updateMemberWithdrawAsCallback(String orderId,BigDecimal receiptAmount,Pay_Way payWay,Date processTime,boolean isSuccess)throws EliteServiceException;
	
	/**
	 * 按ID查找提现记录
	 * 
	 * @param id
	 * @return memberWithdraw
	 * */
	MemberWithdraw findMemberWithdrawById(Long id)throws EliteServiceException;
	
	/**
	 * 按提现订单号查询
	 * 
	 * @param orderId
	 * @return memberWithdraw
	 * */
	MemberWithdraw findMemberWithdrawByOrderId(String orderId)throws EliteServiceException;
	
	/**
	 * 查找提现列表
	 * 
	 * @param keyword(姓名，编号，财务）
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return SearchResult<MemberWithdraw>
	 * */
	SearchResult<MemberWithdraw> findMemberWithdrawList(String keyword,Date startTime,Date endTime,String status,Pager pager)throws EliteServiceException;

   /**
    * 会员查询最近提现记录
    */
	List<MemberWithdraw> findMemberWithdrawListByMemberId(Date startTime,Long MemberId,Withdraw_Status status)throws EliteServiceException;
	
	/**
	 * 分页查询会员最近提现记录
	 */
	SearchResult<MemberWithdraw> findMemberWithdrawsByMemberId(Date startTime,Long MemberId,Withdraw_Status status,Pager pager)throws EliteServiceException;
	
}
