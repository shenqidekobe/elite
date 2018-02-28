package com.ledao.elite.core.domain.sys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统部门对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysDept extends IdentifiedEntity{
	
	private static final long serialVersionUID = 8026639375959855647L;
	
	private Long organId;//单位ID
	private Long parentId;//上级ID
	@Column(length=32)
	private String name;//名称
	@Column(length=16)
	private String status;//状态
	@Column(length=16)
	private String leadName;//负责人
	@Column(length=64)
	private String leadPhone;//负责人电话
	private Integer orders;//排序号
	private Long createId;//创建者
	private Long updateId;//创建者
	
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "organId", insertable=false, updatable=false)
    private SysOrgan sysOrgan;//部门所属单位
    
	@OneToMany(mappedBy = "sysDept")
	private Set<SysUser> users;//部门的用户列表
	
	@OneToMany(mappedBy = "sysDept")
	private Set<SysRole> roles;//部门的角色列表
	
	@ManyToOne
	@JoinColumn(name = "parentId", insertable = false, updatable = false)
	private SysDept parent;
	@JsonIgnore
	@OneToMany(mappedBy = "parent")
	@Where(clause = "status !='deleted'")
	@OrderBy("orders ASC")
	private Set<SysDept> children = new HashSet<SysDept>();

}
