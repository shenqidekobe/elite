package com.ledao.elite.core.framework.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据字典管理用的树形结构
 * 
 * @version 1.0
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictMangerTree {
	
	//数据字典类型{顶级，子级}
	public enum DictMangerTree_TYPE{
		first,sub
	}
	
	private Long id;//ID
	private Long dictId;//ID
	private Long parentId;//上级ID
	private String name;//名称
	private Integer level;//级别
	private String type;//类型
	private boolean isParent=false;//是否父节点
	private boolean open=false;//是否展开

	public void setIsParent(boolean isParent) {
        this.isParent = isParent;  
    } 
	public boolean getIsParent(){
		return isParent;
	}
}
