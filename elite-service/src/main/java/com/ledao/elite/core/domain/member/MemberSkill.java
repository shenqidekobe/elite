package com.ledao.elite.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员技能对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberSkill extends IdentifiedEntity {

	private static final long serialVersionUID = 710083868565628610L;
	
	private Long memberId;//会员ID
	@Column(length=32)
	private String skill;//技能名称
	@Column(length=32)
	private String level;//技能级别，熟练度
	@Column(length=128)
	private String intro;//技能描述
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号

}
