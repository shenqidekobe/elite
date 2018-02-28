package com.ledao.elite.core.framework.listener.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.listener.AsyncTaskListener;
import com.ledao.elite.core.framework.listener.event.AcceptSettleTask;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.framework.thread.settle.AcceptStageSettlement;

/**
 * 渠道方结算任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class AcceptStageTask extends AsyncTaskListener{
	
	
	@Resource
	private AcceptStageSettlement acceptStageSettlement;
	
	@Async
	@Override
	protected void execute(BaseTask task) {
		AcceptSettleTask pTask=(AcceptSettleTask) task;
		Long stageId=pTask.getStageId();
		boolean acceptFlag=pTask.isAcceptFlag();
		String acceptReason=pTask.getAcceptReason();
		  
		acceptStageSettlement.settle(stageId, acceptFlag, acceptReason);
	}

	@Override
	public boolean supportsTaskType(BaseTask_Type taskType) {
		return BaseTask_Type.accept_settle.equals(taskType);
	}
	
	@Override
	public int getOrder(){ return 99990; };

}
