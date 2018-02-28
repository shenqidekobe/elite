package com.ledao.elite.core.domain.member;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 会员的精英币购买订单对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberCoinOrder extends IdentifiedEntity {

	private static final long serialVersionUID = 396004993992336965L;
	
	@Column(length=32)
	private String orderId;//订单号
	private Long memberId;//会员ID
	@Column(length=32)
	private String source;//精英币来源
	private BigDecimal buyAmount;//直接购买的金额
	private BigDecimal coinCount;//精英币数量
	@Column(length=32)
	private String status;//状态
	
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号

}
