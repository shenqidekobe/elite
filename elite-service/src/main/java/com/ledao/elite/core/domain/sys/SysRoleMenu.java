package com.ledao.elite.core.domain.sys;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统角色菜单关系对象
 * @author Administrator
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysRoleMenu extends IdentifiedEntity {

	private static final long serialVersionUID = -4575852106218726543L;

	private Long roleId;//角色ID
	private Long menuId;//菜单ID
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "roleId", insertable=false, updatable=false)
    private SysRole sysRole;//角色
    
    @ManyToOne
   	@JoinColumn(name = "menuId", insertable=false, updatable=false)
    private SysMenu sysMenu;//菜单
	
}
