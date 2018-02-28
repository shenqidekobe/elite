package com.ledao.elite.rest.framework.request;

import lombok.Data;

@Data
public class MemberBankRequest {
	private String idCard;//身份证
	private String phone;//电话号码
	private String bankCard;//银行卡号
	private String holder;//持有人姓名
	private String verifyCode;//验证码
}
