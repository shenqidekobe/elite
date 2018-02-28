package com.ledao.elite.core.domain.member;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 前端站点角色对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SiteRole extends IdentifiedEntity {

	private static final long serialVersionUID = 5509992874342099957L;
	
	@Column(length=64)
	private String name;//名称
	@Column(length=128)
	private String intro;//简介
	
	/************************hibernate many one config**************************/
	@OneToMany(mappedBy = "siteRole")
	private List<SiteRolePower> powers;//用户的角色列表

}
