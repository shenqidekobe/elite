package com.ledao.elite.core.domain.platform;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台投诉信息表、申诉对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformComplain extends IdentifiedEntity {

	private static final long serialVersionUID = -7457336364873546108L;
	
	private Long projectId;//涉及项目ID
	private Long statgeId;//涉及阶段ID
	private Long taskId;//涉及任务ID
	private Long targetMemberId;//投诉目标会员ID
	private Long targetIsOutput;//投诉目标会员是否提供产出无
	private Long complainIsOutput;//投诉人是否提供产出物
	@Column(length=128)
	private String title;//标题
	private Long complainMemberId;//投诉人ID
	@Column(length=1000)
	private String content;//内容
	@Column(length=1000)
	private String reply;//回复
	private Long attaId;//附件ID
	private int level;//严重级别
	@Column(length=32)
	private String status;//状态
	private BigDecimal payAmoun;//肯支付金额
	private BigDecimal claimAmount;//索要金额
	private BigDecimal arbitrationAmount;//仲裁金额
	private Long procrssId;//处理人ID
	@Type(type = "yes_no")
	private boolean isOutput;//最终确定是否需要产出物
	@Column(length=256)
	private String processReason;//处理原因
	@Column(length=128)
	private String processResult;//处理结果
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间

}
