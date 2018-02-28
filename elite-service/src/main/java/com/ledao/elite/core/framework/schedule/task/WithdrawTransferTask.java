package com.ledao.elite.core.framework.schedule.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.member.MemberWithdraw.Withdraw_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Way;
import com.ledao.elite.core.framework.plugin.pay.PaymentService;
import com.ledao.elite.core.framework.plugin.pay.pingpp.PingPlusPlusServiceImpl;
import com.ledao.elite.core.framework.plugin.quartz.QuartzJobScheduler;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.sys.QuartzTaskService;
import com.pingplusplus.model.Transfer;

/**
 * 提现的企业代付轮询任务
 * */
@Component
public class WithdrawTransferTask extends ExpireAbstractTask{
	
	@Resource
	private MemberWithdrawService memberWithdrawService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private PlatformFundService platformFundService;
	@Resource(name="pingPlusPlusService")
	private PaymentService pingPlusPlusService;
	@Resource
	private QuartzTaskService quartzTaskService;
	@Resource
	private QuartzJobScheduler quartzJobScheduler;

	@Override
	public void execute() throws EliteBusinessException {
		List<MemberWithdraw> withdrawList=this.memberWithdrawService.findMemberWithdrawListByMemberId(null, null, Withdraw_Status.processing);
		if(withdrawList.isEmpty())return;
		for(MemberWithdraw mw:withdrawList){
			String transferId=mw.getThirdSerial();
			try {
				//查询企业付款的记录
				Map<String,Object> result=pingPlusPlusService.queryTransfer(transferId);
				Transfer transfer=(Transfer) result.get(PingPlusPlusServiceImpl.PING_RESULT_KEY);
				if(transfer!=null&&transfer.getStatus().equals("paid")
						&&!mw.getStatus().equals(Withdraw_Status.success)){
					BigDecimal receiptAmount=new BigDecimal(transfer.getAmount()).divide(new BigDecimal(100));
					this.memberWithdrawService.updateMemberWithdrawAsCallback(mw.getOrderId(),receiptAmount, Pay_Way.online, new Date(), true);
				}else if(transfer!=null&&transfer.getStatus().equals("failed")
						&&!mw.getStatus().equals(Withdraw_Status.success)){
					log.info("提现订单："+mw.getOrderId()+"处理失败.可能原因："+transfer.getFailureMsg());
					mw.setStatus(Withdraw_Status.failure);
					memberWithdrawService.updateMemberWithdrawNofix(mw);
					//退回金额到会员账户
					this.memberAccountService.updateMemberAccountBalance(mw.getMemberId(), mw.getAmount(),Data_Oper.sum);
					//更新平台流水
					this.platformFundService.updatePlatformFund(mw.getOrderId(), PlatformFund_Status.success);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
