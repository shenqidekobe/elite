package com.ledao.elite.core.framework.listener.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.listener.AsyncTaskListener;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.SmsPushTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.service.sys.SmsRecordService;

/**
 * 短信推送任务服务
 * */
@Component
public class SmsPushWriteTask extends AsyncTaskListener{

	@Resource
	private SmsRecordService smsRecordService;
	
	@Async
	@Override
	protected void execute(BaseTask task) {
		SmsPushTask smsTask=(SmsPushTask) task;
		smsRecordService.createSmsRecord(smsTask.getSms());
	}

	@Override
	public boolean supportsTaskType(BaseTask_Type taskType) {
		return BaseTask_Type.push_sms.equals(taskType);
	}

}
