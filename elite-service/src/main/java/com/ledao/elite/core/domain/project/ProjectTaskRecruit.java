package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.util.Date;

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
import com.ledao.elite.core.framework.dto.MemberDetailInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目的阶段性的任务招募关系对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectTaskRecruit extends IdentifiedEntity {

	private static final long serialVersionUID = 4864135726069504177L;
	
	//招募状态
	public enum ProjectTaskRecruit_Status{
		recruit_in("招募中"),
		recruit_win("已招募"),
		recruit_cancel("取消报名"),
		recruit_not("未招募"),
		task_closed("已关闭");
		public String label;
		ProjectTaskRecruit_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	private Long projectId;//项目ID
	private Long stageId;//项目阶段ID
	private Long taskId;//项目阶段任务ID
	private Long memberId;//精英ID
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ProjectTaskRecruit_Status status;//状态
	@Column(length=32)
	private String source;//任务的来源
	
	@Column(length=256)
	private String reason;//原因
	private BigDecimal amount;//金额
	@Type(type = "yes_no")
	private boolean isRemd;//是否推荐
	private Long remdUser;//推荐人
	@Temporal(TemporalType.TIMESTAMP)
	private Date recruitTime;//招募时间
	
	
	@Transient
	private Integer applyCount;//报名数量
	@Transient
	private String applyFlag;//我的报名标识
	@Transient
	private Integer boardCount=0;//和该精英搭伙次数
	@Transient
	private Integer taskCount;//和该精英的任务数量
	@Transient
	private MemberDetailInfo info;//和该精英的任务数量
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
	@JoinColumn(name = "taskId", insertable=false, updatable=false)
    private ProjectStageTask task;//对应的任务
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "stageId", insertable=false, updatable=false)
    private ProjectDefineStage stage;//项目阶段

}
