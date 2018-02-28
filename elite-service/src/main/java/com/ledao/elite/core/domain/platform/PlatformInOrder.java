package com.ledao.elite.core.domain.platform;

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
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Channel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台入账订单对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformInOrder extends IdentifiedEntity {

	private static final long serialVersionUID = 257768595438051891L;
	
	//平台入账订单状态
	public enum PlatformInOrder_Status{
		wait_pay("待支付"),
		success("支付成功"),
		failure("支付失败"),
		expire("过期订单");
		public String label;
		PlatformInOrder_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	//平台入账订单类型
	public enum PlatformInOrder_Type{
		intention("意向金"),
		prostage("项目阶段费用");
		public String label;
		PlatformInOrder_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	@Column(length=32)
	private String orderId;//订单号
	private Long projectId;//项目ID
	private Long stageId;//项目阶段ID
	private Long taskId;//阶段任务ID
	private Long memberId;//会员ID
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private PlatformInOrder_Status status;//订单状态
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private PlatformInOrder_Type type;//类型
	
	private BigDecimal orderAmount;//订单金额
	private BigDecimal payAmount;//待付款金额
	private BigDecimal receiptAmount;//实付金额
	
	private BigDecimal pointAmount;//积分金额
	private BigDecimal couponsAmount;//优惠卷金额
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Pay_Channel payType=Pay_Channel.alipay;//支付类型
	@Column(length=32)
	private String payAccount;//支付者帐号
	@Temporal(TemporalType.TIMESTAMP)
	private Date payTime;//支付时间
	
	private Long voucherId;//支付凭证附件ID
	@Column(length=64)
	private String intro;//备注
	@Column(length=32)
	private String thirdSerial;//第三方交易流水号
	private Long auditId;//审核人
	@Column(length=128)
	private String auditReason;//审核原因
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间
	
	@Column(length=64)
	private String invoiceRise;//发票抬头
	@Column(length=128)
	private String invoiceAddress;//发票邮寄地址
	@Column(length=32)
	private String invoiceName;//发票收件人名称
	@Column(length=64)
	private String invoicePhone;//发票收件人电话
	
	
	@Transient
	private String memberName; //客户姓名
	@Transient
	private String companyName; //客户公司
	@Transient
	private String memberPhone; //客户联系手机
	/************************hibernate many one config**************************/

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "projectId", insertable = false, updatable = false)
	private Project project;//项目
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "stageId", insertable = false, updatable = false)
	private ProjectDefineStage stage;//项目阶段
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "taskId", insertable = false, updatable = false)
	private ProjectStageTask task;//任务
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voucherId", insertable = false, updatable = false)
	private Attas voucherAtta;// 支付凭证附件
	


}
