package com.ledao.elite.site.dto;

import java.util.List;

import com.ledao.elite.core.framework.base.ResponseBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 返回结果定义
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> extends ResponseBase{
	
	private static final long serialVersionUID = -2781417062501297183L;
	
	private List<T> data=null;
	
	private T object=null;
	
	private Long id;

	public ResponseResult(String msg) {
		super(msg);
	}
	public ResponseResult(T object) {
		super();
		this.object=object;
	}
	public ResponseResult(String result, String msg) {
		super(result, msg);
	}

	public ResponseResult(String result, String code, String msg) {
		super(result, code, msg);
	}
	public ResponseResult(List<T> data) {
		super();
		this.data = data;
	}
	public ResponseResult(Long id) {
		super();
		this.id = id;
	}
	

}
