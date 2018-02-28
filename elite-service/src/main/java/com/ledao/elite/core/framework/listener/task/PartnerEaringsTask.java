package com.ledao.elite.core.framework.listener.task;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.framework.listener.AsyncTaskListener;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.framework.thread.settle.PartnerEaringsSettlement;
import com.ledao.elite.core.framework.listener.event.PartnerSettleTask;

/**
 * 渠道方结算任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class PartnerEaringsTask extends AsyncTaskListener{
	
	
	@Resource
	private PartnerEaringsSettlement partnerEaringsSettlement;
	
	@Override
	protected void execute(BaseTask task) {
		PartnerSettleTask pTask=(PartnerSettleTask) task;
		Long memberId=pTask.getMemberId();//推荐的会员ID
		MemberIdentity_Type memberType=pTask.getMemberType();//推荐的会员类型
		BigDecimal totalAmount=pTask.getTotalAmount();//总金额
		Long projectId=pTask.getProjectId();//项目ID
		Long stageId=pTask.getStageId();//阶段ID
		boolean firstStage=pTask.isFirstStage();//是否第一阶段
		boolean lastStage=pTask.isLastStage();//是否最后一个结算
		Long taskId=pTask.getTaskId();//任务ID
		MemberIdentity_Type partnerType=pTask.getPartnerType();//渠道方类型{默认人才推荐方}
		  
		partnerEaringsSettlement.settle(memberId, memberType, totalAmount, projectId, stageId, taskId, firstStage, lastStage, partnerType);
	}

	@Override
	public boolean supportsTaskType(BaseTask_Type taskType) {
		return BaseTask_Type.partner_settle.equals(taskType);
	}
	
	@Override
	public int getOrder(){ return 99999; };

}
