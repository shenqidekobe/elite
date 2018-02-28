package com.ledao.elite.rest.framework.response.project;

import java.util.Date;

import com.ledao.elite.core.domain.project.Project;

import lombok.Data;

/**
 * CTO项目信息
 * @author Chenghao
 *
 */
@Data
public class RCProject {
	
	private Long id;
	private String imgPath;
	private String name;//项目名
	private Date startTime;//开始日期
	private Date expectTime;//期望交付日期
	private Date deliveryTime;//确定交付时间
	private String projectBudget;//项目预算
	private String projectSolution;// 解决方案类型字符串
	private boolean isSendDefined=false;//是否发送了定标书
	private String status;//状态
	private Long stageId;//阶段Id
	private String stageTitle;//阶段标题
	private String stageStatus;//阶段标题
	
	public RCProject(){}
	public RCProject(Project project){
		this.id=project.getId();
		this.name=project.getName();
		this.startTime=project.getStartTime();
		this.expectTime=project.getExpectTime();
		this.deliveryTime=project.getDeliveryTime();
		this.projectBudget=project.getProjectBudget();
		this.projectSolution=project.getSolutionVals();
		this.status=project.getStatus().name();
	}
}
