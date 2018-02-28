package com.ledao.elite.core.domain.member;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员资金流水对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberFund extends IdentifiedEntity {

	private static final long serialVersionUID = 8214263987974143411L;

	@Column(length=32)
	private String fundNum;//流水号
	private Long memberId;//会员ID
	private Long projectId;//项目ID
	@Column(length=32)
	private String fundType;//流水类型
	private BigDecimal amount;//金额
	@Column(length=16)
	private String inOut;//收入或支出
	@Column(length=32)
	private String title;//标题
	@Column(length=32)
	private String fundStatus;//流水涉及的当前状态
}
