package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目延期记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectDefer extends IdentifiedEntity {

	private static final long serialVersionUID = 2348575693172104680L;
	
	//延期状态
	public enum Defer_Status{
		wait_process("待处理"),
		agree("同意"),
		reject("驳回");
		public String label;
		Defer_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	private Long projectId;//项目ID
	private Long stageId;//阶段ID
	private Long taskId;//任务ID
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Defer_Status status;//延期状态
	
	@Column(length=32)
	private String launchType;//发起方类型(精英orCTO)
	private Long launchMemberId;//发起人ID
	
	@Column(length=128)
	private String reason;//延期原因
	@Column(length=1000)
	private String intro;//延期描述
	@Column(length=32)
	private String attaId;//附件文档ID
	private int applyDay;//申请延期天数
	private int agreeDay;//同意延期天数
	@Column(length=256)
	private String reply;//回复内容
	private BigDecimal amount;//给接收方的金额
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	private Long processId;//处理人ID
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
}
