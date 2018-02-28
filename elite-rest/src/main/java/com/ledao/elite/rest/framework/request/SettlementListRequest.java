package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Getter;
import lombok.Setter;

/**
 * 结算列表请求参数
 * */
public class SettlementListRequest extends RequestBaseRest{

	private static final long serialVersionUID = -2795640036931729109L;
	
	@Setter@Getter
	private String type;//类型
	@Setter@Getter
	private String keyword;//关键词
	@Setter@Getter
	private Integer pageth;//关键词

}
