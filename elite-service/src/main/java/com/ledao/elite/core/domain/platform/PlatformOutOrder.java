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
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectStageTask;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台出账订单对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformOutOrder extends IdentifiedEntity {

	private static final long serialVersionUID = -8794446660867826179L;
	
	//平台出账订单状态
	public enum PlatformOutOrder_Status{
		wait_pay("待支付"),
		success("支付成功"),
		failure("支付失败");
		public String label;
		PlatformOutOrder_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	//平台出账订单类型
	public enum PlatformOutOrder_Type{
		task_income("任务收益"),
		guarantee_income("质保金收益"),
		commission_income("提成收益"),
		withdraw("提现");
		public String label;
		PlatformOutOrder_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	@Column(length=32)
	private String orderId;//出账订单号
	private Long projectId;//项目ID
	private Long stageId;//项目阶段ID
	private Long taskId;//阶段任务ID
	private Long memberId;//收款的会员ID
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private PlatformOutOrder_Type type;//类型
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private PlatformOutOrder_Status status;//状态
	
	private BigDecimal orderAmount;//订单金额
	private BigDecimal receiptAmount;//实付金额
	private BigDecimal taxAmount;//含税金额
	@Column(length=32)
	private String memberName;//会员名称
	@Column(length=32)
	private String memberAccount;//会员的收款帐号
	@Column(length=256)
	private String intro;//备注
	private Long voucherId;//凭证附件ID
	private Long auditId;//审核人
	@Column(length=32)
	private String auditReason;//审核理由
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date payqualityTime;//质保金支付时间
	
	@Transient
	private MemberCredit credit;//会员信息

	/************************hibernate many one config**************************/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taskId", insertable = false, updatable = false)
	private ProjectStageTask task;// 阶段任务
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "stageId", insertable = false, updatable = false)
	private ProjectDefineStage stage;//项目阶段
	
	 @ManyToOne
     @JoinColumn(name = "projectId", insertable=false, updatable=false)
	 private Project project;//项目
	    
	 @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name = "memberId", insertable=false, updatable=false)
	 private MemberPassport memberPassport;//精英
	    

}
