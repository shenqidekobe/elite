package com.ledao.elite.core.domain.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.cache.custom.DictCache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 项目日志对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class ProjectLog extends IdentifiedEntity {

	private static final long serialVersionUID = -489845477249090840L;
	
	//项目日志类型
	public enum ProjectLog_Type{
		company("项目方的日志"),
		elite("CTO和精英的日志");
		public String label;
		ProjectLog_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return this.label;
		}
	}

	private Long projectId;//项目ID
	private String stageCode;//阶段Code
	private Long taskId;//任务ID
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ProjectLog_Type logType;//日志类型
	@Column(length=32)
	private String taskStatus;//任务当前状态
	@Column(length=32)
	private String title;//标题
	@Column(length=128)
	private String content;//操作内容
	private Long attaId;//操作涉及的附件ID
	private Long createId;//操作者
	
	
	public ProjectLog(ProjectLog_Type logType,Long projectId, String stageCode, Long taskId, String content, Long attaId, Long createId) {
		super();
		this.logType=logType;
		this.projectId = projectId;
		this.stageCode = stageCode;
		this.taskId = taskId;
		this.content = content;
		this.attaId = attaId;
		this.createId = createId;
	}
	public ProjectLog(ProjectLog_Type logType,Long projectId,String content, Long attaId, Long createId) {
		super();
		this.logType=logType;
		this.projectId = projectId;
		this.content = content;
		this.attaId = attaId;
		this.createId = createId;
	}
	public ProjectLog(ProjectLog_Type logType,Long projectId,String content,Long createId) {
		super();
		this.logType=logType;
		this.projectId = projectId;
		this.content = content;
		this.createId = createId;
	}
	public ProjectLog(ProjectLog_Type logType,Long projectId,String stageCode,String content, Long attaId, Long createId) {
		super();
		this.logType=logType;
		this.projectId = projectId;
		this.stageCode = stageCode;
		this.content = content;
		this.attaId = attaId;
		this.createId = createId;
	}
	public ProjectLog(ProjectLog_Type logType,Long projectId,String stageCode,String content,Long createId) {
		super();
		this.logType=logType;
		this.projectId = projectId;
		this.stageCode = stageCode;
		this.content = content;
		this.createId = createId;
	}
    
	@Transient
	public String getStageName(){
		if(StringUtils.isEmpty(title)){
			return DictCache.getParamDesc(Dict.Dict_Type.project_stage.name(), stageCode);
		}
		return title;
	}

	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
   	@JoinColumn(name = "attaId", insertable=false, updatable=false)
    private Attas atta;//附件
}
