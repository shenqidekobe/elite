package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Getter;
import lombok.Setter;

/**
 * 消息箱列表请求参数
 * */
public class MessageListRequest extends RequestBaseRest{

	private static final long serialVersionUID = -2795640036931729109L;
	
	@Setter@Getter
	private String status;//消息状态
	@Setter@Getter
	private String type;//消息类型
	@Setter@Getter
	private Long projectId;//项目ID

}
