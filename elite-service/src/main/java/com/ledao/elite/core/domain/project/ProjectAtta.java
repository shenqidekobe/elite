package com.ledao.elite.core.domain.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 项目附件对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAtta extends IdentifiedEntity{

	private static final long serialVersionUID = -1568645378929472022L;
	
	//项目相关的附件类型定义
	public enum ProjectAtta_Type{
		project_atta("发布项目的附件"),
		stage_atta("项目阶段的附件"),
		project_define_atta("立项书附件");
		public String label;
		ProjectAtta_Type(String label){
			this.label=label;
		}
	}
	
	private Long projectId;//项目ID
	
	private Long stageId;//项目阶段ID
	
	private Long taskId;//任务ID
	
	private Long attaId;//附件ID
	
	@Column(length=32)
	private String type;//类型
	
	@Column(length=32)
	private String status;//状态
	
	private Long createId;//创建者ID
	
	public ProjectAtta(Long projectId, Long attaId, String status, Long createId) {
		super();
		this.projectId = projectId;
		this.attaId = attaId;
		this.type = ProjectAtta_Type.project_atta.name();
		this.status = status;
		this.createId = createId;
	}



	/************************hibernate many one config**************************/
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne(fetch=FetchType.LAZY)
 	@JoinColumn(name = "attaId", insertable=false, updatable=false)
    private Attas atta;
    
}
