package com.ledao.elite.core.exception;

import lombok.Getter;

/**
 * 业务异常
 * */
public class EliteBusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String code;
	@Getter
	private String msg;
	

	public EliteBusinessException(String msg) {
		super(msg);
		this.msg=msg;
	}
	
	public EliteBusinessException(String msg,String code) {
		super(msg);
		this.code=code;
		this.msg=msg;
	}

	public EliteBusinessException(String msg, Throwable t) {
		super(msg, t);
		this.msg=msg;
	}
}