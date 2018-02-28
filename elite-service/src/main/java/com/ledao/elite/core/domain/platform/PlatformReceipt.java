package com.ledao.elite.core.domain.platform;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台收款记录对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformReceipt extends IdentifiedEntity{

	private static final long serialVersionUID = 7747381659829727120L;
	
	private Long projectId;//项目ID
	
	private Long stageId;//阶段ID
	
	private Long memberId;//会员ID
	
	@Column(length=32)
	private String remitWay;//汇款方式
	
	@Column(length=32)
	private String remitAccount;//汇款帐号
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date remitTime;//汇款时间
	
	@Column(length=32)
	private String remitSerial;//汇款流水
	
	private BigDecimal remitAmount;//汇款金额
	
	private BigDecimal actualAmount;//实收金额
	
	private Long certAttaId;//凭证附件ID
	
	private Long createId;//创建者ID
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date confirmTime;//确认时间
	
	private Long confirmId;//确认者ID
	

}
