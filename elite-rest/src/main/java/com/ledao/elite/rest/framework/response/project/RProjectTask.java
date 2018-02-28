package com.ledao.elite.rest.framework.response.project;

import java.math.BigDecimal;
import java.util.List;

import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;

/**
 * 任务信息
 * @author Chenghao
 *
 */
@Data
public class RProjectTask {
	
	private Long id;
	private String name;//任务名字
	private String projectName;//项目名称
	private String taskNum;//任务编号
	private String imgName;//图片名
	private String imgPath;//图片名
	private String intro;//任务简介要求
	private String taskTypeVal;//任务类型
	private String demandTypeVal;//需求人才类型(角色)
	private BigDecimal amount;//报酬
	private String deliveryTime;//交付日期
	private String startTime;//任务开始时间
	private String endTime;//任务截止时间{招募截止时间}
	private String createTime;//任务发布时间
	private Integer deliveryDay;//交付天数
	private String status;//任务状态
	private String recruitStatus;//已认领的状态
	private Long taskoverVal;//剩余时间
	private String stageTitle;//项目所属阶段
	private Boolean applyFlag=false;
	private Integer applyCount;
	private List<String> stageList;
	private Integer stageCount;
	public RProjectTask(){}
	
	public RProjectTask(ProjectStageTask task){
		this.id=task.getId();
		this.imgName=task.getImgName();
		this.projectName=task.getProject().getName();
		this.stageTitle=task.getProject().getForStage().getTitle();
		this.name=task.getName();
		this.taskNum=task.getTaskNum();
		this.taskTypeVal=task.getTaskTypeVal();
		this.demandTypeVal=task.getDemandTypeVal();
		this.amount=task.getAmount();
		this.deliveryTime=DateTimeUtils.formatDate(task.getDeliveryTime(), "yyyy-MM-dd");
		this.startTime=DateTimeUtils.formatDate(task.getStartTime(), "yyyy-MM-dd");
		this.endTime=DateTimeUtils.formatDate(task.getEndTime(), "yyyy-MM-dd");
		this.createTime=DateTimeUtils.formatDate(task.getCreateTime(), "yyyy-MM-dd");
		this.intro=ParaseHtml.ParaseHtmlContent(task.getIntro());
		this.status=task.getStatus().name();
		this.deliveryDay=task.getDeliveryDay();
		this.taskoverVal=task.getTaskoverVal();
	}
	
}
