package com.ledao.elite.rest.framework.response.project;

import com.ledao.elite.core.domain.project.ProjectSettingStage;

import lombok.Data;

/**
 * 项目已经设置的阶段信息
 * @author Chenghao
 *
 */
@Data
public class RProjectSettingStage {
	
	private String stageCode;//阶段CODE
	private String title;//标题
	
	public RProjectSettingStage(){}
	
	public RProjectSettingStage(ProjectSettingStage stage){
		this.stageCode=stage.getStageCode();
		this.title=stage.getTitle();		
	}
}
