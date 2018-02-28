package com.ledao.elite.rest.framework.response.project;

import java.math.BigDecimal;

import com.ledao.elite.core.domain.project.ProjectDefineStage;

import lombok.Data;

@Data
public class RTrustStage {
	
	private Boolean finished=false;//所有阶段结束标识
	private BigDecimal firstAmount;//第一阶段费用{金额-已付意向金}
	private BigDecimal amount;
	
	public RTrustStage(){}
	public RTrustStage(ProjectDefineStage stage){
		this.finished=stage.getFinished();
		this.firstAmount=stage.getFirstAmount();
		this.amount=stage.getAmount();
	}
}
