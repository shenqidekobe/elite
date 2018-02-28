package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员擅长技能等级评定对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberGoodRate extends IdentifiedEntity {

	private static final long serialVersionUID = -5876812053623802762L;
	
	private Long memberId;//会员ID
	@Column(length=32)
	private String goodSkill;//擅长的技能
	@Column(length=16)
	private String level;//等级
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditId;//审核人ID
	@Column(length=256)
	private String auditReason;//审核原因

}
