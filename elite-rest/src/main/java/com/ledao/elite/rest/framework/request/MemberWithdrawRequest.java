package com.ledao.elite.rest.framework.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MemberWithdrawRequest {
	
	private Long bankId;
	private BigDecimal withdrawAmount;
	private BigDecimal receiptAmount;
	private String invoiceWay;
	private String password;
	private BigDecimal amount;
}
