package com.ledao.elite.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 会员权限对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SitePower extends IdentifiedEntity {

	private static final long serialVersionUID = 1544790724965183830L;
	
	@Column(length=64)
	private String name;//名称
	@Column(length=64)
	private String flag;//shiro标识
	@Column(length=512)
	private String paths;//地址
	private int level;//等级
	private int orders;//排序号
	@Column(length=32)
	private String status;//状态

}
