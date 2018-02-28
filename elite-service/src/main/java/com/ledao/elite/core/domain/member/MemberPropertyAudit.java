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
 * 会员的属性审核意见对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberPropertyAudit extends IdentifiedEntity {

	private static final long serialVersionUID = -568305790241159657L;
	
	private Long memberId;//会员ID
	private Long propertyId;//属性ID
	@Column(length=32)
	private String propertyName;//属性名
	@Column(length=128)
	private String auditReason;//审核意见
	private Long auditId;//审核员ID
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间

}
