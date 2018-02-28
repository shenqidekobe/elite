package com.ledao.elite.core.exception;

import lombok.Getter;

/**
 * service层异常
 * */
public class EliteServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String code;
	@Getter
	private String msg;

	public EliteServiceException() {
		super();
	}
	
	public EliteServiceException(String code) {
		super();
		this.code=code;
	}
	
	public EliteServiceException(String msg,String code) {
		super(msg);
		this.code=code;
		this.msg=msg;
	}

	public EliteServiceException(String msg, Throwable t) {
		super(msg, t);
		this.msg=msg;
	}
	
}