package com.ledao.elite.core.domain.platform;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台退款申请记录
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformRefund extends IdentifiedEntity {

	private static final long serialVersionUID = 6628042455821711983L;
	
	//退款状态
	public enum Refund_Status{
		wait_process("待处理"),
		processed("处理中"),
		process_success("处理成功"),
		process_fail("处理失败");
		public String label;
		Refund_Status(String label){
			this.label=label;
		}
	}
	
	@Column(length=32)
	private String orderId;//退款订单号
	private Long memberId;//申请者ID
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Refund_Status status;//状态码
	private BigDecimal amount;//退款金额
	@Column(length=64)
	private String thirdSerial;//第三方流水号
	@Column(length=256)
	private String reason;//退款原因
	
	private Long projectId;//项目ID
	private Long stageId;//项目阶段ID
	private Long taskId;//阶段任务ID
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyTime;//申请时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间

}
