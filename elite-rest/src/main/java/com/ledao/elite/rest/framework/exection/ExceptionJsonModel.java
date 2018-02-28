package com.ledao.elite.rest.framework.exection;

import com.ledao.elite.rest.framework.ResponseResultData;

import lombok.Getter;
import lombok.Setter;

/**
 * 异常错误jsonModel
 * */
public class ExceptionJsonModel {

	@Setter@Getter
	private ResponseResultData<Object> response; 
	
}
