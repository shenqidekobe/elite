package com.ledao.elite.core.service.member.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.member.MemberWithdraw.Withdraw_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Type;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Status;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Type;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Way;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Way;
import com.ledao.elite.core.repository.member.MemberWithdrawRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;
import com.ledao.elite.core.service.sys.CommonCodeService;
import com.ledao.elite.core.service.sys.QuartzTaskService;

@Service("memberWithdrawService")
public class MemberWithdrawServiceImpl extends BaseSerivceImpl implements MemberWithdrawService {

	@Resource
	private MemberWithdrawRepository memberWithdrawRepository;
	@Resource
	private CommonCodeService commonCodeService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private PlatformFundService platformFundService;
	@Resource
	private PlatformOutOrderService platformOutOrderService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private QuartzTaskService quartzTaskService;

	@Override
	public MemberWithdraw createMemberWithdraw(MemberWithdraw withdraw) throws EliteServiceException {
		this.verifyParams(withdraw, withdraw.getBankId(),withdraw.getMemberId(), withdraw.getAmount());
		String orderId = commonCodeService.disposeOddNumber("withdrawNum", COMMON_PREVAL.W.name(), "yyyyMM", 8, null);
		withdraw.setOrderId(orderId);
		withdraw.setStatus(Withdraw_Status.wait_process);
		this.memberWithdrawRepository.save(withdraw);
		// 扣减余额
		this.memberAccountService.updateMemberAccountBalance(withdraw.getMemberId(), withdraw.getAmount(),
				Data_Oper.sub);
		// 新建流水
		this.platformFundService.createPlatformFund("提现", orderId, null, null, withdraw.getMemberId(),
				withdraw.getAmount(), PlatformFund_Type.withdraw,PlatformFund_Status.wait_pay);
		return withdraw;
	}

	@Override
	public MemberWithdraw updateMemberWithdraw(MemberWithdraw withdraw) throws EliteServiceException {
		this.verifyParams(withdraw, withdraw.getId(), withdraw.getMemberId(), withdraw.getAmount());
		this.memberWithdrawRepository.save(withdraw);
		
		//更新流水账号状态
		this.platformFundService.updatePlatformFund(withdraw.getOrderId(), PlatformFund_Status.success);
		return withdraw;
	}
	
	@Override
	public MemberWithdraw updateMemberWithdrawNofix(MemberWithdraw withdraw)throws EliteServiceException{
		this.verifyParams(withdraw, withdraw.getId());
		this.memberWithdrawRepository.save(withdraw);
		return withdraw;
	}
	
	@Override
	public void updateMemberWithdrawAsFinanceAffirm(Long id,Long invoiceAttaId,Long withdrawAttaId,boolean isAffirm,String reason,Integer minute)throws EliteServiceException{
		this.verifyParams(id);
		MemberWithdraw obj=this.memberWithdrawRepository.find(id);
		if(obj==null){
			throw new EliteServiceException("提现记录不存在",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}else if(!MemberWithdraw.Withdraw_Status.wait_process.equals(obj.getStatus())){
			throw new EliteServiceException("提现已经处理过",ErrorCodeEnum.OBJECT_USE.code);
		}
		if(isAffirm){
			//添加到定时任务表中
			this.quartzTaskService.createQuartzTask(id, QuartzTask_Type.withdraw, QuartzTask_Way.one, null, minute, null);
			
			//更新提现记录为财务已确认
			obj.setStatus(Withdraw_Status.confirmed);
		}else{
			obj.setStatus(Withdraw_Status.refuse);
			obj.setReason(reason);
		}
		obj.setAffirmTime(new Date());
		obj.setInvoiceAttaId(invoiceAttaId);
		obj.setWithdrawAttaId(withdrawAttaId);
		this.memberWithdrawRepository.save(obj);
	}
	
	@Override
	public void updateMemberWithdrawAsCallback(String orderId,BigDecimal receiptAmount,Pay_Way payWay,Date processTime,boolean isSuccess)throws EliteServiceException{
		MemberWithdraw withdraw=findMemberWithdrawByOrderId(orderId);
		if(withdraw==null){
			return;
		}
		if(!isSuccess){
			withdraw.setStatus(Withdraw_Status.failure);
			this.memberWithdrawRepository.save(withdraw);
			//更新平台流水
			this.platformFundService.updatePlatformFund(orderId, PlatformFund_Status.failure);
		}
		//处理提现成功
		withdraw.setStatus(Withdraw_Status.success);
		withdraw.setReceiptAmount(receiptAmount);
		withdraw.setReceiptWay(payWay);
		withdraw.setProcessTime(processTime);
		this.memberWithdrawRepository.save(withdraw);
		
		//更新平台流水
		this.platformFundService.updatePlatformFund(orderId, PlatformFund_Status.success);
		
		//新增平台出账订单
		platformOutOrderService.createPlatformOutOrder(withdraw.getMemberId(),PlatformOutOrder_Type.withdraw,null,null,null,receiptAmount,new BigDecimal(0));
		
		//任务执行结束
		quartzTaskService.updateQuartzTask(withdraw.getId(), QuartzTask_Status.run_end);
	}

	@Override
	public MemberWithdraw findMemberWithdrawById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.memberWithdrawRepository.find(id);
	}
	
	@Override
	public MemberWithdraw findMemberWithdrawByOrderId(String orderId)throws EliteServiceException{
		this.verifyParams(orderId);
		return this.memberWithdrawRepository.searchUnique(new Search().addFilterEqual("orderId", orderId));
	}

	@Override
	public SearchResult<MemberWithdraw> findMemberWithdrawList(String keyword, Date startTime, Date endTime,
			String status, Pager pager) throws EliteServiceException {
		SearchResult<MemberWithdraw> sr = new SearchResult<>();
		List<MemberWithdraw> withdrawList = this.memberWithdrawRepository.queryMemberWithdrawList(keyword, status,
				startTime, endTime, pager);
		for (int i = 0; i < withdrawList.size(); i++) {
			MemberPassport member = withdrawList.get(i).getMemberPassport();
			if (member.getId() != null) {
				MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(member.getId());
				member.setCredit(credit);
			}
			withdrawList.get(i).setMemberPassport(member);
		}
		Integer totalCount = this.memberWithdrawRepository.queryMemberWithdrawListCount(keyword, startTime, endTime,
				status);
		sr.setTotalCount(totalCount);
		sr.setResult(withdrawList);
		return sr;
	}

	@Override
	public List<MemberWithdraw> findMemberWithdrawListByMemberId(Date startTime, Long memberId,Withdraw_Status status)
			throws EliteServiceException {
		Search search=new Search();
		search.addFilterEqual("status", status);
		if(startTime!=null){
			search.addFilterGreaterThan("createTime", startTime);
		}
		if(memberId!=null){
			search.addFilterEqual("memberId", memberId);
		}
		return this.memberWithdrawRepository.search(search);
	}

	@Override
	public SearchResult<MemberWithdraw> findMemberWithdrawsByMemberId(Date startTime, Long memberId,
			Withdraw_Status status,Pager pager) throws EliteServiceException {
		// TODO Auto-generated method stub
		Search search=new Search();
		if(status!=null){
			search.addFilterEqual("status", status);
		}
		search.addFilterEqual("memberId", memberId);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		SearchResult<MemberWithdraw> sr = this.memberWithdrawRepository.searchAndCount(search);
		return sr;
	}

}
