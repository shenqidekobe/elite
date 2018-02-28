package com.ledao.elite.core.domain.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.sys.Attas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目周报对象
 * 分为CTO给客户的周报和精英提交给CTO的周报、每周的周报至少一个
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectWeekly extends IdentifiedEntity {

	private static final long serialVersionUID = -663315927863685631L;
	
	//项目周报状态
	public enum ProjectWeekly_Status{
		wait_audit("待审核"),
		normal("正常状态"),
		unaudit("审核未通过");
		public String label;
		ProjectWeekly_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	//项目周报类型
	public enum ProjectWeekly_Type{
		cto("精英提交的周报"),
		company("CTO提交的周报");
		public String label;
		ProjectWeekly_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	private Long projectId;//项目ID
	private Long taskId;//任务ID
	private Long attaId;//附件ID
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ProjectWeekly_Type weeklyType;//周报类型
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ProjectWeekly_Status status;//状态
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkTime;//查看时间
	
	@Temporal(TemporalType.DATE)
	private Date weeklyTime;//周报所属时间{记录每周五的时间}
	
	@Type(type = "yes_no")
	private boolean readed=false;//是否已读
	private Long createId;//创建者ID
	private Long receiveId;//接受者ID{补偿字段}
	private Long auditId;//审核者ID
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间
	@Column(length=512)
	private String auditReason;//审核原因
	
	@Transient
	private Boolean expired=false;//是否超期,默认未超期{未在当前周内提交}
	
	@Transient
    private MemberPassport memberPassport;//会员信息
	
	/************************hibernate many one config**************************/
	@ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
	@JoinColumn(name = "attaId", insertable=false, updatable=false)
    private Attas atta;//周报附件

}
