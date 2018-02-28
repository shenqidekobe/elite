package com.ledao.elite.core.domain.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目初期设置阶段
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectSettingStage extends IdentifiedEntity {

	private static final long serialVersionUID = -3354762907323465815L;
	
	private Long projectId;//项目ID
	@Column(length=32)
	private String stageCode;//阶段CODE
	@Column(length=32)
	private String title;//标题
	private Integer orders;//排序号
	private Long createId;//创建者ID
	private Long updateId;//修改者ID
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目

}
