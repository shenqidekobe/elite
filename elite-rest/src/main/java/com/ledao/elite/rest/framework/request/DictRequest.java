package com.ledao.elite.rest.framework.request;

import lombok.Data;

/**
 * 获取数据字典的参数请求
 * */
@Data
public class DictRequest {

	private String dictType;//类型
	private Long parentId;//上级ID

}
