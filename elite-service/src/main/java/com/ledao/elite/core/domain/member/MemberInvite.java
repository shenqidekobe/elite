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
 * 会员的邀请信息对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberInvite extends IdentifiedEntity {

	private static final long serialVersionUID = 3380238066804664138L;
	
	private Long memberId;//会员ID
	private Long inviteMemberId;//邀请的会员ID
	@Column(length=11)
	private String phone;//邀请的电话
	@Column(length=32)
	private String email;//邀请的邮箱
	
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号

}
