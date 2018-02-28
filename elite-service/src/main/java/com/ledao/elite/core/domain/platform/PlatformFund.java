package com.ledao.elite.core.domain.platform;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台资金流水记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformFund extends IdentifiedEntity {

	private static final long serialVersionUID = -6374452556928876760L;
	
	//平台流水号类型
	public enum PlatformFund_Type{
		intention("意向金"),
		prostage("阶段性费用"),
		income("收益"),
		guarantee("质保金收益"),
		withdraw("提现"),
		charge("充值"),
		refund("退款");
		public String label;
		PlatformFund_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	//平台流水号状态
	public enum PlatformFund_Status{
		wait_pay("待付款"),
		success("成功"),
		failure("失败"),
		close("关闭");
		public String label;
		PlatformFund_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	@Column(length=32)
	private String fundNum;//流水号
	@Column(length=32)
	private String title;//标题
	@Column(length=32)
	private String orderId;//订单号
	private Long projectId;//项目ID
	private Long stageId;//阶段ID
	private Long memberId;//会员ID
	private BigDecimal amount;//金额
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private PlatformFund_Type type;//类型
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private PlatformFund_Status status;//状态
	
	@Transient
	private PlatformInOrder order;//平台入账订单
	
	/************************hibernate many one config**************************/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId", insertable = false, updatable = false)
	private Project project;//项目对象
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stageId", insertable = false, updatable = false)
	private ProjectDefineStage stage;//项目阶段对象
	
	
}
