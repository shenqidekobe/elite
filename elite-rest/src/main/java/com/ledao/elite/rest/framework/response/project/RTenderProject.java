package com.ledao.elite.rest.framework.response.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;

@Data
public class RTenderProject {
	
	private Long id;//项目Id
	private String name;//项目名
	private String firstName;//项目首字
	private String imgPath;
	private String projectNum;// 项目编号
	private String projectBudget;//项目预算
	private Date startTime;//开始日期
	private Date expectTime;//期望交付日期  
	private List<String> industryValList;
	private String areaName;// 地区名
	private String contactName;// 联系人
	private String contactPhone;// 联系电话
	private String referProject;// 参考项目
	private String intro;// 项目简介
	private String createTime;
	private String fileName;//附件文件名称
	private String downPath;//文件路径
	private String solutionVals;//解决方案类型
	private String status;//竞标状态
	private Long tenderoverVal;//剩余时长
	private boolean isSendDefined;//是否发送了定标书
	private List<String> stageList=new ArrayList<>();//各阶段性名称
	private Integer stageCount=0;//属于
	private Long stageId;//当前阶段Id
	private String stageTitle;//阶段标题
	private String stageStatus;//阶段标题
	
	public RTenderProject(){}
	public RTenderProject(ProjectTender tender){
		this.id=tender.getProjectId();
		this.name=tender.getProject().getName();
		this.projectNum=tender.getProject().getProjectNum();
		this.firstName=tender.getProject().getFirstName();
		this.startTime=tender.getProject().getStartTime();
		if(tender.getProject().getStatus().equals(Project_Status.audit_in)||tender.getProject().getStatus().equals(Project_Status.wait_audit)||tender.getProject().getStatus().equals(Project_Status.pass_wait)||tender.getProject().getStatus().equals(Project_Status.unpass)){
			this.expectTime=tender.getProject().getExpectTime();
		}else if(tender.getProject().getStatus().equals(Project_Status.pass_already)||tender.getProject().getStatus().equals(Project_Status.stage_course)||tender.getProject().getStatus().equals(Project_Status.quality)||tender.getProject().getStatus().equals(Project_Status.finish)){
			this.expectTime=tender.getProject().getDeliveryTime();
		}
		this.expectTime=tender.getProject().getExpectTime();
		this.projectBudget=tender.getProject().getProjectBudget();
		this.industryValList=tender.getProject().getIndustryValList();
		this.areaName=tender.getProject().getAreaName();
		this.contactName=tender.getProject().getContactName();
		this.contactPhone=tender.getProject().getContactPhone();
		this.referProject=tender.getProject().getReferProject();
		this.fileName=tender.getAtta()==null?"":tender.getAtta().getFileName();
		this.downPath=tender.getAtta()==null?"":tender.getAtta().getDownPath();
		this.intro=ParaseHtml.ParaseHtmlContent(tender.getProject().getIntro());
		this.createTime=DateTimeUtils.formatDate(tender.getProject().getCreateTime(), "yyyy-MM-dd");
		this.solutionVals=tender.getProject().getSolutionVals();
		this.tenderoverVal=tender.getTenderoverVal();
		
	}
	
}
