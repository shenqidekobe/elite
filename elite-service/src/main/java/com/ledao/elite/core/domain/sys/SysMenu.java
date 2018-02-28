package com.ledao.elite.core.domain.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统菜单对象
 * @author Administrator
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysMenu extends IdentifiedEntity {

	private static final long serialVersionUID = 6705518223491542104L;
	
	public enum SYSMENU_TYPE{
		menu,button
	}

	private Long parentId;//上级ID
	@Column(length=32)
	private String name;//菜单名称
	@Column(length=32)
	private String flag;//菜单标识
	@Column(length=512)
	private String paths;//菜单地址
	@Column(length=16)
	private String type;//菜单类型{菜单类型or按钮类型}
	private int orders;//排序号
	@Column(length=16)
	private String status;//状态
	
	
	@Transient
	private List<SysMenu> haveChilds;//拥有的子菜单
	
	
	/************************hibernate many one config**************************/
	@ManyToOne
	@JoinColumn(name = "parentId", insertable = false, updatable = false)
	private SysMenu parent;
	
	@JsonIgnore
	@OneToMany(mappedBy = "parent")
	@OrderBy("orders ASC")
	private List<SysMenu> childs = new ArrayList<>();


}
