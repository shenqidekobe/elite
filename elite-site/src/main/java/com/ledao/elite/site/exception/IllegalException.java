package com.ledao.elite.site.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/**
 * 非法操作异常
 */
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class IllegalException extends RuntimeException {
	private static final long serialVersionUID = 6407893836520838749L;
	
	@Getter
	protected String message;

    public IllegalException(){
    }
    public IllegalException(String message) {
        this.message = message;
    }
}
