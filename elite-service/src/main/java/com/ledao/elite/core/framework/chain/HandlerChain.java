package com.ledao.elite.core.framework.chain;

import com.ledao.elite.core.exception.EliteBusinessException;

import lombok.Setter;

/**
 * 责任链处理基类
 * */
public abstract class HandlerChain {
	
	@Setter
	protected HandlerChain handler;
	
	public abstract void handle(Object args)throws EliteBusinessException;

}
