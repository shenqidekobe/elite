package com.ledao.elite.rest.framework.response.project;

import java.util.Date;
import java.util.List;

import com.ledao.elite.core.domain.project.ProjectTender;

import lombok.Data;

/**
 * 竞标信息
 * @author Chenghao
 *
 */
@Data
public class BidTenderInfo {
	
	private Long projectId;
	private Long tenderId;
	private String solutionVals;//解决方案类型
	private String projectName;
	private Date startTime;
	private Date endTime;
	private String stockRate;//股份
	private List<RProjectSettingStage> stageList;
	
	public BidTenderInfo(){}	
	public BidTenderInfo(ProjectTender tender){
		this.projectId=tender.getProjectId();
		this.startTime=tender.getStartTime();
		this.endTime=tender.getEndTime();
		this.tenderId =tender.getId();
		this.projectName=tender.getProject().getName();
		this.solutionVals=tender.getProject().getSolutionVals();
		this.stockRate = tender.getStockRate();
	}
}
