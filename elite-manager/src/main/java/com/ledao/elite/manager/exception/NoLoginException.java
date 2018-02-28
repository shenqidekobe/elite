package com.ledao.elite.manager.exception;

import lombok.Getter;

/**
 * 未登录异常
 */
public class NoLoginException extends RuntimeException {
	private static final long serialVersionUID = 6407893836520838749L;
	
	@Getter
	protected String message;

    public NoLoginException(){
    }
    public NoLoginException(String message) {
        this.message = message;
    }
}
