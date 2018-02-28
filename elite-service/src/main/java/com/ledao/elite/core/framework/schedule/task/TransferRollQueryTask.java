package com.ledao.elite.core.framework.schedule.task;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.member.MemberWithdraw.Withdraw_Status;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.plugin.pay.PaymentService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.pingplusplus.model.Transfer;

/**
 * 企业付款轮询查询任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class TransferRollQueryTask extends ExpireAbstractTask{
	
	@Resource
	private MemberWithdrawService memberWithdrawService;
	@Resource(name="pingPlusPlusService")
	private PaymentService pingPlusPlusService;

	@Override
	public void execute() throws EliteBusinessException {
		log.info("*****企业付款轮询查询任务处理开始执行*****");
		List<MemberWithdraw> list=memberWithdrawService.findMemberWithdrawListByMemberId(null, null, Withdraw_Status.processing);
		if(list.isEmpty())return;
		for(MemberWithdraw withdraw:list){
			String transferId=withdraw.getThirdSerial();
			if(StringUtils.isEmpty(transferId))continue;
			Map<String,Object> result=pingPlusPlusService.queryTransfer(transferId);
			Transfer transfer=(Transfer) result.get("pingpp");
			if(transfer==null)continue;
			String status=transfer.getStatus();
			if("failed".equals(status)){
				//提现处理失败更新提现状态
				withdraw.setStatus(Withdraw_Status.failure);
				withdraw.setReason(transfer.getFailureMsg());
				memberWithdrawService.updateMemberWithdrawNofix(withdraw);
			}
		}
	}
}
