package com.ledao.elite.core.framework.constant;

/**
 * 系统默认角色定义
 * */
public enum RoleEnum {
	
	super_admin(100L,"超级管理员"),
	business_manager(500L,"商务经理"),
	project_mamager(600L,"项目经理");
	
	public Long roleId;
	public String roleName;
	RoleEnum(Long roleId,String roleName){
		this.roleId=roleId;
		this.roleName=roleName;
	}
	public Long getRoleId(){
		return roleId;
	}
	public String getRoleName(){
		return roleName;
	}

}
