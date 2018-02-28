package com.ledao.elite.core.domain.member;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 会员提现记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberWithdraw extends IdentifiedEntity {

	private static final long serialVersionUID = 1125600003989895700L;
	
	//会员提现状态
	public enum Withdraw_Status{
		wait_process("待处理"),
		confirmed("已确认"),
		processing("处理中"),
		success("处理成功"),
		refuse("拒绝提现"),
		failure("提现失败");
		public String label;
		Withdraw_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return this.label;
		}
	}
	public enum Withdraw_invoiceWay{
		platform("平台代开"),
		customer("客户自开发票");
		public String label;
		Withdraw_invoiceWay(String label){
			this.label=label;
		}
	}
	
	private String orderId;//提现订单号
	private Long memberId;//提现会员ID
	private BigDecimal amount;//提现金额
	private BigDecimal receiptAmount;//实际提现金额
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Withdraw_Status status;//状态
	
	private Long bankId;//提现到账的银行卡号
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private GlobalDefinedConstant.Pay_Way expectWay;//期望提现的方式
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private GlobalDefinedConstant.Pay_Way receiptWay;//实际提现的方式

	@Column(length=128)
	private String thirdSerial;//第三方流水号
	@Column(length=128)
	private String reason;//原因
	@Temporal(TemporalType.TIMESTAMP)
	private Date affirmTime;//财务确认时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	@Column(length=32)
	private String invoiceWay;//发票索要方式
	
	private Long withdrawAttaId;//提现凭证附件ID
	@Type(type = "yes_no")
	private boolean isReceiveInvoice;//是否收到发票
	private BigDecimal invoiceAmount;//发票额
	private Long invoiceAttaId;//发票附件ID
	private BigDecimal taxAmount;//税额
	
	private Long financeId;//做处理的财务ID
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号
    
    @ManyToOne
   	@JoinColumn(name = "bankId", insertable=false, updatable=false)
    private MemberBank bank;//会员银行卡
    
    @ManyToOne
 	@JoinColumn(name = "financeId", insertable=false, updatable=false)
    private SysUser sysUser;//财务账号
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoiceAttaId", insertable = false, updatable = false)
	private Attas atta;// 发票附件
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "withdrawAttaId", insertable = false, updatable = false)
	private Attas withdrawAtta;// 提现凭证

}
