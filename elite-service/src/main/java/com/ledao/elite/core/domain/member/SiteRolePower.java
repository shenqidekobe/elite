package com.ledao.elite.core.domain.member;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 前端角色权限关系对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SiteRolePower extends IdentifiedEntity {

	private static final long serialVersionUID = -2081959705053526191L;
	
	private Long roleId;//角色ID
	private Long powerId;//权限ID
	
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "roleId", insertable=false, updatable=false)
    private SiteRole siteRole;//角色
    
    @ManyToOne
   	@JoinColumn(name = "powerId", insertable=false, updatable=false)
    private SitePower sitePower;//权限

}
