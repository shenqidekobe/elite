package com.ledao.elite.rest.framework.response;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 支付的返回对象
 * */
@Data
public class PayResponse{


	private String orderNum;//订单号
	private BigDecimal orderAmount;//支付金额
	private String subject;//支付标题
	private String body;//描述信息
}
