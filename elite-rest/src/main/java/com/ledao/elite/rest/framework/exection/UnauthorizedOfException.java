package com.ledao.elite.rest.framework.exection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/**
 * 无权限异常
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedOfException extends RuntimeException {
	private static final long serialVersionUID = 6407893836520838749L;
	
	@Getter
	protected String message;

    public UnauthorizedOfException(){
    }
    public UnauthorizedOfException(String message) {
        this.message = message;
    }
}
