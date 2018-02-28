package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目列表请求参数
 * */
public class ProjectListRequest extends RequestBaseRest{

	private static final long serialVersionUID = -2795640036931729109L;
	
	@Setter@Getter
	private String status;//项目状态

}
