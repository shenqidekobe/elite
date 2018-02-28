package com.ledao.elite.core.framework.dto.site;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 提现金额换算结果对象
 * */
@Data
public class WithdrawConversion {


	private BigDecimal taxAfter;//税后金额
	private BigDecimal tax;//税费金额
	private BigDecimal factorage;//手续费
}
