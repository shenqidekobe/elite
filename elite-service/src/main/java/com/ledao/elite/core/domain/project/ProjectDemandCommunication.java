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
 * 项目前期需求沟通记录对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectDemandCommunication extends IdentifiedEntity{
	
	private static final long serialVersionUID = 4834379396318414848L;
	
	private Long projectId;//项目ID
	private Long pmId;//平台方PMID
	private Long ceoId;//项目方用户ID
	private Long attaId;//附件
	@Column(length=32)
	private String status;//状态
	@Column(length=256)
	private String intro;//简介
	@Column(length=256)
	private String reason;//原因
	@Temporal(TemporalType.TIMESTAMP)
	private Date confirmTime;//客户确认时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date downloadTime;//客户下载时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date removeTime;//删除时间
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目

}
