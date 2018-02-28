package com.ledao.elite.rest.framework.response.member;

import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBank.MemberBank_Type;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.System_Status;

import lombok.Data;

@Data
public class RMemberBank {
	private Long id;
	private Long memberId;//会员ID
	private MemberBank_Type type;//账户类型
	private System_Status status;//状态
	private String bankName;//银行名称
	private String bankCode;//银行编码
	private String bankCard;//银行卡号
	private String holder;//持有人姓名
	private String phone;//银行绑定手机号
	private boolean verifyed=false;//验证是否通过
	
	public RMemberBank(){}
	public RMemberBank(MemberBank bank){
		this.id=bank.getId();
		this.memberId=bank.getMemberId();
		this.type=bank.getType();
		this.status=bank.getStatus();
		this.bankName=bank.getBankName();
		this.bankCode=bank.getBankCode();
		this.bankCard=bank.getBankCard();
		this.holder=bank.getHolder();
		this.phone=bank.getPhone();
		this.verifyed=bank.isVerifyed();
	}
}
