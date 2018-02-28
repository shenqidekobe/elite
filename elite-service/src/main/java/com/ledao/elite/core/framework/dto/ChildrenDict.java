package com.ledao.elite.core.framework.dto;


import com.ledao.elite.core.domain.sys.Dict;

import lombok.Data;

@Data
public class ChildrenDict {

	private Long parentId;//上级ID
	private String dictType;//类型
	private String dictKey;//key
	private String dictVal;//value
	private String dictDesc;//描述信息
	private Integer orders;//排序号
	
	public ChildrenDict(){}
	public ChildrenDict(Dict dict){
		this.parentId=dict.getParentId();
		this.dictType=dict.getDictType();
		this.dictKey=dict.getDictKey();
		this.dictVal=dict.getDictVal();
		this.dictDesc=dict.getDictDesc();
		this.orders=dict.getOrders();
	}
}
