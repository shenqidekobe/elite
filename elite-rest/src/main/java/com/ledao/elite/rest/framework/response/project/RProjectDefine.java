package com.ledao.elite.rest.framework.response.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefineStage;

import lombok.Data;

@Data
public class RProjectDefine {
	private Long projectId;
	private String solutionVals;//解决方案类型
	private String projectName;
	private BigDecimal totalAmount;//总费用
	private Date startTime;
	private Date endTime;
	private BigDecimal stockRate;//股份
	private String fileName;//附件原始文件名称
	private String downPath;//下载路径
	private List<RProjectDefineStage> stageList = new ArrayList<RProjectDefineStage>();
	
	public RProjectDefine(){}	
	public RProjectDefine(ProjectDefine define){
		this.projectId=define.getProjectId();
		this.projectName=define.getProject().getName();
		this.solutionVals=define.getProject().getSolutionVals();
		this.totalAmount=define.getTotalAmount();
		this.stockRate = define.getStock();
		this.fileName=define.getAtta()==null?"":define.getAtta().getFileName();
		this.downPath=define.getAtta()==null?"":define.getAtta().getDownPath();
		if(define.getStages().size()>0){
			startTime=define.getStages().get(0).getStartTime();
			for(ProjectDefineStage stage:define.getStages()){
				stageList.add(new RProjectDefineStage(stage));
				endTime=stage.getOriginalStopTime();
			}
		}
		
	}
}
