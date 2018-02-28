package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目阶段性的任务对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectStageTask extends IdentifiedEntity {

	private static final long serialVersionUID = -4337687607889174867L;
	
	//项目任务状态定义
	public enum ProjectTask_Status{
		wait_recruit("待认领"),
		recruit_in("认领中"),
		starting("进行中"),
		wait_accept("待验收"),
		accept_success("验收成功"),
		quality("质保期"),
		finish("完成"),
		accept_fail("验收失败"),
		closed("已关闭"),
		invalid("无效的任务");
		public String label;
		ProjectTask_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
			
		}
	}
	
	private Long projectId;//项目ID
	private Long stageId;//阶段ID
	
	@Column(length=32)
	private String taskNum;//任务编号
	private Long taskLogo;//任务logo
	@Column(length = 16)
	private String backgroundColor;//背景色
	@Column(length = 30)
	private String imgName;//图片名
	@Column(length=32)
	private String name;//任务名字
	@Column(length=4000)
	private String intro;//任务简介要求
	
	@Column(length=256)
	private String taskType;//任务类型
	@Column(length=64)
	private String demandType;//需求人才类型(角色)
	
	private BigDecimal amount;//报酬
	private BigDecimal guaranteeAmount;//质保金
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryTime;//交付日期
	@Temporal(TemporalType.TIMESTAMP)
	private Date originalDeliveryTime;//原始的确定交付时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;//任务开始时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;//任务截止时间{招募截止时间}
	private Long attaId;//任务附件
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ProjectTask_Status status;//任务状态
	
	@Column(length=256)
	private String acceptReason;//验收原因
	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptTime;//验收时间
	
	@Column(length=512)
	private String closeReason;//关闭原因
	
	@Type(type = "yes_no")
	private boolean isHall=true;//是否发布到任务大厅
	private Long eliteMemberId;//接收任务的精英会员ID
	private Long createId;//创建者
	
	private Integer guaranteeTime;//质保时间{默认为任务时间的一半}，从验收时间开始计算,不足一天算一天
	private Integer viewCount=0;//浏览次数
	@Type(type = "yes_no")
	private boolean isRemind=false;//是否已提醒
	private String claimTime;//索要周报标识
	
	@Transient
	private Integer applyCount=0;//报名人数
	
	@Transient
	private MemberPassport member;
	
	@Transient
	List<ProjectTaskRecruit> recruitList;
	
	//任务类型描述值
	public String getTaskTypeVal(){
		return DictCache.getParamDesc(Dict_Type.task_type.name(), taskType);
	}
	//需求人才类型描述值
	public String getDemandTypeVal(){
		return DictCache.getParamDesc(Dict_Type.job_role.name(), demandType);
	}
	
	/************************hibernate many one config**************************/
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "stageId", insertable=false, updatable=false)
    private ProjectDefineStage stage;//项目阶段
    
    @ManyToOne(fetch=FetchType.LAZY)
 	@JoinColumn(name = "attaId", insertable=false, updatable=false)
    private Attas atta;
    
    @ManyToOne(fetch=FetchType.LAZY)
 	@JoinColumn(name = "taskLogo", insertable=false, updatable=false)
    private Attas logoAtta;
    
    // 交付时间天数
 	public Integer getDeliveryDay() {
 		if (deliveryTime == null) {
 			return DateTimeUtils.dateSub(endTime, startTime);
 		}
 		return DateTimeUtils.dateSub(deliveryTime, startTime);
 	}
 	
 	//交付日期
 	@Transient
	public Long getTaskoverVal(){
		return deliveryTime==null?0l:(deliveryTime.getTime()/1000-new Date().getTime()/1000);
	}
 	//质保天数
 	@Transient
	public Long getGuaranteeTimeVal(){
 		if(this.getStatus().equals(ProjectTask_Status.quality)){
			Date guarantee = DateTimeUtils.addOrSub(this.getAcceptTime(),this.getGuaranteeTime());
			return guarantee.getTime() / 1000 - new Date().getTime() / 1000;
		}
 		return 0l;
	}
}
