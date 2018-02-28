package com.ledao.elite.core.framework.listener.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * 事件定义
 * */
public class EliteEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = -9096921872871973091L;
	@Getter
	private BaseTask task;

	public EliteEvent(Object source,BaseTask task) {
		super(source);
		this.task=task;
	}

}
