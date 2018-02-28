package com.ledao.elite.core.domain.member;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 成员角色对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberRole extends IdentifiedEntity {

	private static final long serialVersionUID = -2506637786118554515L;
	
	private Long memberId;//会员ID
	private Long roleId;//会员角色

	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员
    
    @ManyToOne
 	@JoinColumn(name = "roleId", insertable=false, updatable=false)
    private SiteRole siteRole;//角色
}
