package com.ledao.elite.rest.framework.response.project;

import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;


/**
 * 文件相关信息
 * @author Chenghao
 *
 */
@Data
public class RProjectMaterial {
	
	private String createTime;//上传时间
	private String stageTitle;//阶段名称
	private String fileName;//附件原始文件名称
	private String fileSize;//附件原始文件大小
	private boolean isRead=false;//是否已读
	
	public RProjectMaterial(){}
	public RProjectMaterial(ProjectMaterial material){
		 this.createTime=DateTimeUtils.formatDate(material.getCreateTime(), "yyyy-MM-dd");
		 this.stageTitle=material.getStage()==null?"立项阶段":material.getStage().getTitle();
		 this.fileName = material.getAtta().getFileName();
		 this.fileSize=material.getAtta().getFileSize();
		 this.isRead=material.isRead();
	}
}
