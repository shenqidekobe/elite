package com.ledao.elite.rest.framework;

import com.ledao.elite.core.framework.base.RequestBase;

import lombok.Getter;
import lombok.Setter;

/**
 * rest的请求基类
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public class RequestBaseRest extends RequestBase{

	private static final long serialVersionUID = -3796716453696766969L;
	
	@Getter
	@Setter
	private Long memberId;//会员唯一标识ID
	@Getter
	@Setter
	private Long dataId;//数据ID，ID参数
	@Getter
	@Setter
	private String value;//字符参数
}
