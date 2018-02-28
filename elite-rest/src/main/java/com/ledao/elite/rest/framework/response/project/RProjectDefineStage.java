package com.ledao.elite.rest.framework.response.project;

import java.math.BigDecimal;
import java.util.Date;

import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;

/**
 * 项目的阶段记录
 * @author kobe.liu
 *
 */
@Data
public class RProjectDefineStage {
	
	private Long id;//阶段ID
	private String stageCode;//阶段CODE
	private String title;//标题
	private String status;//阶段状态
	private Date startTime;//开始时间
	private Date stopTime;//截止时间、交付日期{需求变更可变}
	private Date originalStopTime;//原始截止时间
	private BigDecimal amount;//阶段金额{待付金额}
	private BigDecimal intentionAmount;//意向金
	private BigDecimal firstAmount;//第一阶段金额
	private Boolean firstStage=false;//第一阶段
	
	public RProjectDefineStage(){}
	
	public RProjectDefineStage(ProjectDefineStage stage){
		this.id=stage.getId();
		this.stageCode=stage.getStageCode();
		this.title=stage.getTitle();
		this.status=stage.getStatus().name();
		this.startTime=stage.getStartTime();
		this.stopTime=stage.getStopTime();
		this.originalStopTime=stage.getOriginalStopTime();
		this.amount=stage.getAmount();
		this.firstAmount=stage.getFirstAmount();
		if(stage.getFirstAmount()!=null){
			firstStage=true;
		}
	}
	
	public Integer getDeliveryDay() {
		if (stopTime == null) {
			return DateTimeUtils.dateSub(originalStopTime, startTime);
		}
		return DateTimeUtils.dateSub(stopTime, startTime);
	}
}
