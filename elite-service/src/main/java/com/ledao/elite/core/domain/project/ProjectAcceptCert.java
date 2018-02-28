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
 * 项目相关验收单(如项目阶段验收单、整个项目验收单)对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectAcceptCert extends IdentifiedEntity{

	private static final long serialVersionUID = 5711346693883204512L;
	
	//验收单类型
	public enum AcceptCert_Type{
		statge_cert("阶段验收单"),
		project_cert("项目验收单");
		public String label;
		AcceptCert_Type(String label){
			this.label=label;
		}
	}
	
	//验收单状态
	public enum AcceptCert_Status{
		wait("待验收"),
		qualified("合格"),
		unqualified("不合格");
		public String label;
		AcceptCert_Status(String label){
			this.label=label;
		}
	}
	
	private Long projectId;//项目ID
	
	@Column(length=32)
	private String type;//验收单类型
	
	private Long certId;//验收单关联的ID
	
	private Long targetId;//需要验收的目标用户ID
	
	private Long sourceId;//发验收单用户ID
	
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
