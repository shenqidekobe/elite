package com.ledao.elite.core.domain.member;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员充值记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberCharge extends IdentifiedEntity {

	private static final long serialVersionUID = 7295165404461571654L;
	
	private Long memberId;//会员ID
	private BigDecimal amount;//充值金额
	@Column(length=32)
	private String status;//状态
	@Column(length=128)
	private String reason;//原因
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号

}
