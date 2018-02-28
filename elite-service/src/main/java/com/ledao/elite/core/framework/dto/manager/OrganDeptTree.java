package com.ledao.elite.core.framework.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单位部门树形数据
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganDeptTree {
	
	public enum ORGAN_DEPT_TYPE{
		organ,dept
	}
	
	private Long id;//ID
	private Long organId;//单位ID
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
