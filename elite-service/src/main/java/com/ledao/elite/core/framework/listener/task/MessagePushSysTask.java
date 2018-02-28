package com.ledao.elite.core.framework.listener.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.listener.AsyncTaskListener;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.SysMessagePushTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.service.sys.SysMessageService;

/**
 * 系统推送消息任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class MessagePushSysTask extends AsyncTaskListener{
	
	@Resource
	private SysMessageService sysMessageService;

	@Async
	@Override
	protected void execute(BaseTask task) {
		SysMessagePushTask mpTask=(SysMessagePushTask) task;
		sysMessageService.createSysMessage(mpTask.getMessage());
	}

	@Override
	public boolean supportsTaskType(BaseTask_Type taskType) {
		return BaseTask_Type.message_push_sys.equals(taskType);
	}

}
