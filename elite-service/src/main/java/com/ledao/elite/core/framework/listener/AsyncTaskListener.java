package com.ledao.elite.core.framework.listener;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.framework.listener.event.EliteEvent;

/**
 * 异步任务监听事件处理基类
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public abstract class AsyncTaskListener implements SmartApplicationListener{
	
	protected ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		EliteEvent targetEvent=(EliteEvent) event;
		BaseTask task=targetEvent.getTask();
		Assert.notNull(task);
		if(supportsTaskType(task.getTaskType())){
			log.info("****************AsyncTaskListener事件异步处理器开始执行："+task.getTaskType().getDesc()+"*****************");
			execute(task);
			log.info("****************"+task.getTaskType().getDesc()+"的异步事件处理结束*****************");
		}
	}

	protected abstract void execute(BaseTask task);
	
	@Override
	public boolean supportsEventType(
			Class<? extends ApplicationEvent> eventType) {
		return eventType==EliteEvent.class;
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return true;
	}
	
	public abstract boolean supportsTaskType(BaseTask_Type taskType);
	
	@Override
	public int getOrder(){ return 0; };
}
