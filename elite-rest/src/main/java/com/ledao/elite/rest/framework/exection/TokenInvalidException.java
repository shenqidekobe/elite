package com.ledao.elite.rest.framework.exection;

import lombok.Getter;

/**
 * token无效异常
 */
public class TokenInvalidException extends RuntimeException {
	private static final long serialVersionUID = 6407893836520838749L;
	
	@Getter
	protected String message;
	@Getter
	private String code;
	
    public TokenInvalidException(){
    }
    public TokenInvalidException(String msg,String code) {
		super(msg);
		this.code=code;
		this.message=msg;
	}
}
