package com.ledao.elite.core.framework.base;

import java.io.Serializable;

import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回基类
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase implements Serializable{

	private static final long serialVersionUID = 6823433247286791535L;
	
	private String result=GlobalDefinedConstant.SUCCESS;
	private String code=ErrorCodeEnum.SUCCESS.code;
	private String msg=null;
	
	public ResponseBase(String result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}

	public ResponseBase(String msg) {
		super();
		this.result=GlobalDefinedConstant.FAILURE;
		this.msg = msg;
	}
	
	public void setCode(String code){
		this.code=code;
		if(ErrorCodeEnum.SUCCESS.code.equals(code)){
			this.result=GlobalDefinedConstant.SUCCESS;
		}else{
			this.result=GlobalDefinedConstant.FAILURE;
		}
	}
	
	public void isSuccess(boolean result){
		if(result){
			this.setResult(GlobalDefinedConstant.SUCCESS);
		}else{
			this.setResult(GlobalDefinedConstant.FAILURE);
		}
	}

}
