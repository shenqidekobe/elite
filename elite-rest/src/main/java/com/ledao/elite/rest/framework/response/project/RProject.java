package com.ledao.elite.rest.framework.response.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;

@Data
public class RProject {
	
	private Long id;
	private String imgPath;
	private String projectNum;//项目编号
	private String name;//项目名
	private Date startTime;//开始日期
	private Date expectTime;//期望交付日期
	private Date deliveryTime;//确定交付时间
	private String backgroundColor;//项目背景色
	private String projectBudget;//项目预算
	private String totalAmount;//金额
	private String projectSolution;// 解决方案类型字符串
	private String projectSolutionVal;// 解决方案类型key
	private Date createTime;//项目创建时间
	
	private List<String> industryList;//项目所属行业
	private String productIndustry;//项目行业key
	private List<String> projectFunction;//功能类型
	
	private String status;//状态
	private String areaName;//地区
	private String intro;//简介
	
	private String contactName;//联系人
	private String contactPhone;//联系电话
	private String recommendUser;//推荐人
	private String referProject;//参考项目
	private boolean planed;//是否参与商业计划
	private boolean sendDefined;//是否发送了立项书
	
	private String stageTitle;//阶段标题
	private String stageStatus;//阶段状态
	private BigDecimal intentionAmount;//意向金
	private BigDecimal acceptAmount;//本阶段实际费用
	private RTrustStage trustStage;//托管阶段
	private List<String> stageList;
	private Integer stageCount;
	public RProject(){}
	public RProject(Project project){
		this.id=project.getId();
		this.projectNum=project.getProjectNum();
		this.name=project.getName();
		this.startTime=project.getStartTime();
		if(project.getStatus().equals(Project_Status.audit_in)||project.getStatus().equals(Project_Status.wait_audit)||project.getStatus().equals(Project_Status.pass_wait)||project.getStatus().equals(Project_Status.unpass)){
			this.expectTime=project.getExpectTime();
			this.totalAmount=project.getProjectBudget();
		}else if(project.getStatus().equals(Project_Status.pass_already)||project.getStatus().equals(Project_Status.stage_course)||project.getStatus().equals(Project_Status.quality)||project.getStatus().equals(Project_Status.finish)){
			this.expectTime=project.getDeliveryTime();
			this.totalAmount=project.getTotalAmount().toString();
		}
		this.deliveryTime=project.getDeliveryTime();
		this.backgroundColor=project.getBackgroundColor();
		this.projectBudget=project.getProjectBudget();
		this.projectSolution=project.getSolutionVals();
		this.projectSolutionVal=project.getProjectSolution();
		this.createTime=project.getCreateTime();
		this.intro=ParaseHtml.ParaseHtmlContent(project.getIntro());
		this.projectFunction=project.getFunctionValList();
		this.industryList=project.getIndustryValList();
		this.productIndustry=project.getProductIndustry();
		this.status=project.getStatus().name();
		this.areaName=project.getAreaName();
		this.contactName=project.getContactName();
		this.contactPhone=project.getContactPhone();
		this.recommendUser=project.getRecommendUser();
		this.referProject=project.getReferProject();
		this.planed=project.isPlaned();
		this.sendDefined=project.isSendDefined();
		this.acceptAmount=project.getProjectDefine()==null?null:project.getAcceptStage().getAmount();
		this.trustStage=project.getTrustStage()==null?null:new RTrustStage(project.getTrustStage());
		this.stageTitle=project.getForStage()==null?"":project.getForStage().getTitle();
		this.stageStatus=project.getForStage()==null?"":project.getForStage().getStatus().name();
	}
	
}
