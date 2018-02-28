package com.ledao.elite.core.framework.schedule.task;

import org.slf4j.LoggerFactory;

import com.ledao.elite.core.exception.EliteBusinessException;


public abstract class ExpireAbstractTask {
	
	protected ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(getClass());
	
	public abstract void execute()throws EliteBusinessException;

}
