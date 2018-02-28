package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 支付操作的参数请求
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PayRequest extends RequestBaseRest{

	private static final long serialVersionUID = 4919605192456038851L;
	
	private String type;//类型
	private String channel;//渠道
	private Long projectId;//项目ID
	private String invoiceRise;//发票抬头
	private String invoiceAddress;//发票地址
	private String invoiceName;//收件人姓名
	private String invoicePhone;//收件人联系方式

}
