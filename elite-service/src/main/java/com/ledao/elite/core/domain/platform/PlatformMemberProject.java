package com.ledao.elite.core.domain.platform;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员在平台成功完成的项目和任务记录对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformMemberProject extends IdentifiedEntity{

	private static final long serialVersionUID = 3734112685076428239L;
	
	private Long memberId;//会员ID
	
	private Long projectId;//项目ID
	
	private Long stageId;//阶段ID
	
	private Long taskId;//任务ID
	
	private BigDecimal amount;//赚取金额

}
