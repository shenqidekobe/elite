package com.ledao.elite.core.domain.sys;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统单位对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysOrgan extends IdentifiedEntity{
	
	private static final long serialVersionUID = -3490775719900790641L;
	
	private Long parentId;//上级ID
	private Long areaId;//区域ID
	@Column(length=32)
	private String areaName;//地区名称
	@Column(length=64)
	private String name;//单位名称
	private Long logo;//单位logoID
	@Column(length=256)
	private String intro;//单位简介
	@Column(length=32)
	private String status;//状态
	private Integer orders;//排序号
	private Long createId;//创建者ID
	private Long updateId;//修改者ID
	
	/************************hibernate many one config**************************/
	@OneToMany(mappedBy = "sysOrgan")
	private Set<SysDept> depts;//单位哦的部门列表

}
