package com.ledao.elite.core.domain.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目相关确认单(如需求确认单)对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectConfirmCert extends IdentifiedEntity{

	private static final long serialVersionUID = 4903755278267319547L;
	
	//确认单类型
	public enum ConfirmCert_Type{
		demand_change("需求变更确认单"),
		defer("延期申请确认单"),
		bug_update("bug修改确认单"),
		project_pause("项目暂停确认单");
		public String label;
		ConfirmCert_Type(String label){
			this.label=label;
		}
	}
	
	//确认单状态
	public enum ConfirmCert_Status{
		wait("待确认"),
		confirmed("已确认"),
		unconfirmed("未确认");
		public String label;
		ConfirmCert_Status(String label){
			this.label=label;
		}
	}
	
	private Long projectId;//项目ID
	
	@Column(length=32)
	private String type;//确认单类型
	
	private Long certId;//确认单关联的ID
	
	private Long targetId;//需要确认的目标用户ID
	
	private Long sourceId;//发确认单用户ID
	
	@Column(length=32)
	private String status;//状态
	
	private Long attaId;//附件ID
	
	@Column(length=256)
	private String intro;//简介
	
	@Column(length=256)
	private String reason;//原因
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date confirmTime;//确认时间
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
}
