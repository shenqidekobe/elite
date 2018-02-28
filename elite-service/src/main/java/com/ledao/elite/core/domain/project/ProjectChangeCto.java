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

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目更换CTO申请对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectChangeCto extends IdentifiedEntity{

	private static final long serialVersionUID = -6925946992453268157L;
	
	//更换cto的状态
	public enum ChangeCto_Status{
		wait_process("待处理"),
		agree("同意"),
		reject("驳回");
		public String label;
		ChangeCto_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}

	private Long projectId;//项目ID
	
	private Long originalCtoId;//原始CTO的ID
	
	private Long newCtoId;//新CTO的ID
	@Column(length=512)
	private String reason;//更换原因
	
	private Long attaId;//附件ID
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ChangeCto_Status status;//变更状态
	
	@Column(length=32)
	private String processWay;//处理方式
	
	@Column(length=128)
	private String processResult;//处理结果
	
	private Long createId;//创建者ID
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	
	private Long processId;//处理人ID
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
	
}
