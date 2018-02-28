package com.ledao.elite.core.framework.listener.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.listener.AsyncTaskListener;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.MessagePushTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.service.member.MemberMessageService;

/**
 * 推送消息给会员任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class MessagePushMemberTask extends AsyncTaskListener{
	
	@Resource
	private MemberMessageService memberMessageService;

	@Async
	@Override
	protected void execute(BaseTask task) {
		MessagePushTask mpTask=(MessagePushTask) task;
		memberMessageService.createMemberMessage(mpTask.getMessage());
	}

	@Override
	public boolean supportsTaskType(BaseTask_Type taskType) {
		return BaseTask_Type.message_push_member.equals(taskType);
	}

}
