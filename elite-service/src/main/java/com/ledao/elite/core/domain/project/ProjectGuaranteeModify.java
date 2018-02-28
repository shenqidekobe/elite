package com.ledao.elite.core.domain.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目质保期的修改对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectGuaranteeModify extends IdentifiedEntity{

	private static final long serialVersionUID = -8041626765180701135L;
	
	private Long projectId;//项目ID
	
	private Long stageId;//所属阶段ID
	
	@Column(length=512)
	private String intro;//需修改的描述
	
	private Long attaId;//需修改的附件
	
	@Type(type = "yes_no")
	private boolean isSendCto;//是否发送给cto
	
	@Column(length=32)
	private String status;//状态
	
	private int evaluateScore;//评分
	
	@Column(length=128)
	private String evaluateComment;//评语
	
	private Long createId;//创建者ID
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	
	@Column(length=32)
	private String processResult;//处理结果
	
	private Long processId;//处理人ID
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
	@JoinColumn(name = "stageId", insertable=false, updatable=false)
    private ProjectDefineStage stage;//项目的阶段
	

}
