package com.ledao.elite.core.domain.member;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员账户信息对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberAccount extends IdentifiedEntity {

	private static final long serialVersionUID = 1305464760645102118L;
	
	private Long memberId;//会员ID
	@Column(length=32)
	private String accountId;//帐号编号
	private BigDecimal balance;//账户余额
	private BigDecimal totalIncome;//累计收益
	
	private BigDecimal trustAmount;//托管总费用
	private BigDecimal trustStock;//托管股权总额
	private Integer trustNum;//托管项目数量
	private BigDecimal eliteCoin;//会员的精英币
	private BigDecimal guaranteeAmount;//冻结的质保金
	
	@Type(type = "yes_no")
	private boolean ensured;//是否缴纳了保证金
	private BigDecimal ensureAmount;//保证金金额
	
	
	@Transient
	private BigDecimal amountTax;//税费
	
	//股权百分比,两位小数
	public String getStockPercent(){
	    NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        return percent.format(trustStock.doubleValue());
	}
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号

}
