package com.ledao.elite.core.framework.plugin.quartz;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.member.MemberWithdraw.Withdraw_Status;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Status;
import com.pingplusplus.model.Transfer;

import lombok.extern.slf4j.Slf4j;

/**
 * 提现任务
 * @author kobe
 * */
@Slf4j
public class QuartzJobTask extends AbstractQuartzJob{
	
	@Override
	protected void runTimingTask(JobExecutionContext context) throws JobExecutionException {
		MemberWithdraw withdraw=this.memberWithdrawService.findMemberWithdrawById(this.getDataId());
		if(withdraw!=null){
			//执行企业代付接口
			Map<String,Object> transferParams=new HashMap<String, Object>();
			String orderId=withdraw.getOrderId();
			orderId=orderId.substring(1, orderId.length());
			transferParams.put("orderNum",orderId);//银联订单号不能带字符，去除首字母
			transferParams.put("amount", withdraw.getReceiptAmount().multiply(new BigDecimal(100)));//实际提现金额,单位：分
			transferParams.put("recipient", null);
			transferParams.put("desc", "会员提现申请");
			
			MemberBank memberBank=this.memberBankService.findMemberBankById(withdraw.getBankId());
			String cardNum=memberBank.getBankCard();//银行卡号
			String cardName=memberBank.getHolder();//银行卡姓名
			String openBankCode=memberBank.getBankCode();//开户银行编号
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("card_number",cardNum);
			extra.put("user_name",cardName);
			extra.put("open_bank_code",openBankCode);
			transferParams.put("extra", extra);
			Map<String,Object> result=pingPlusPlusService.transfer(transferParams);
			Transfer transfer=(Transfer) result.get("pingpp");
			log.info("提现任务开始处理提现记录ID："+this.getDataId()+" 提交ping++企业付款记录："+transfer);
			if(transfer!=null&&transfer.getStatus().equals("scheduled")){
				//付款状态。目前支持 5 种状态：pending: 处理中; paid: 付款成功; failed: 付款失败;scheduled:待发送，unionpay渠道的转账会在请求成功后延时5分钟发送，5分钟内处于待发送状态;canceled:取消发送，更新（取消）转账后的订单状态。
				String transferId=transfer.getId();
				withdraw.setThirdSerial(transferId);
				withdraw.setStatus(Withdraw_Status.processing);
				this.memberWithdrawService.updateMemberWithdrawNofix(withdraw);
				//任务执行结束
				this.quartzTaskService.updateQuartzTask(this.getDataId(), QuartzTask_Status.run_end);
			}else{
				log.info("提现处理任务录ID："+this.getDataId()+" 提交失败");
			}
		}
	}

}
