package com.ledao.elite.core.domain.sys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统角色对象
 * @author Administrator
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysRole extends IdentifiedEntity {

	private static final long serialVersionUID = -3045463544805444885L;

	private Long deptId;//部门ID
	@Column(length=32)
	private String name;//角色名
	@Column(length=16)
	private String status;//状态
	@Column(length=128)
	private String intro;//简介
	private Long createId;//创建者
	private Long updateId;//修改者
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "deptId", insertable=false, updatable=false)
    private SysDept sysDept;//角色所属部门
    
	@OneToMany(mappedBy = "sysRole")
	private List<SysRoleMenu> menus;//用户的角色列表
	
}
