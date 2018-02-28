package com.ledao.elite.core.domain.sys;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 系统用户角色关系对象
 * @author Administrator
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysUserRole extends IdentifiedEntity {

	private static final long serialVersionUID = -8744306386644240223L;

	private Long userId;//用户ID
	private Long roleId;//角色ID
	
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "userId", insertable=false, updatable=false)
    private SysUser sysUser;//用户
    
    @ManyToOne
 	@JoinColumn(name = "roleId", insertable=false, updatable=false)
    private SysRole sysRole;//角色
}
