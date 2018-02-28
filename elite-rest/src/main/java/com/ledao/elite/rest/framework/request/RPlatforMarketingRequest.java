package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Getter;
import lombok.Setter;

/**
 * 首页中banner或活动请求
 * @author Chenghao
 *
 */
public class RPlatforMarketingRequest extends RequestBaseRest{

	private static final long serialVersionUID = -2795640036931729109L;
	
	@Setter@Getter
	private String type;//类型
	@Setter@Getter
	private String channel;//当前角色
}
