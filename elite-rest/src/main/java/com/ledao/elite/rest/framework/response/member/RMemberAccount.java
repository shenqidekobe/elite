package com.ledao.elite.rest.framework.response.member;

import java.math.BigDecimal;
import com.ledao.elite.core.domain.member.MemberAccount;
import lombok.Data;

/**
 * 用户帐户信息
 * @author Chenghao
 *
 */
@Data
public class RMemberAccount {
	
	private Long memberId;//会员ID
	private String accountId;//帐号编号
	private BigDecimal balance;//账户余额
	private BigDecimal totalIncome;//累计收益
	private BigDecimal trustAmount;//托管总费用
	private BigDecimal trustStock;//托管股权总额
	private BigDecimal guaranteeAmount;//冻结的质保金
	private BigDecimal amountTax;//税费
	private boolean isCard=true;//身份是否验证通过
	
	public RMemberAccount(){}
	public RMemberAccount(MemberAccount account){
		this.memberId=account.getMemberId();
		this.accountId=account.getAccountId();
		this.totalIncome=account.getTotalIncome();
		this.trustAmount=account.getTrustAmount();
		this.trustStock=account.getTrustStock();
		this.balance=account.getBalance();
		this.guaranteeAmount=account.getGuaranteeAmount();
		this.amountTax=account.getAmountTax();		
	}
	
	
}
