package com.ledao.elite.rest.framework.response.project;


import com.ledao.elite.core.domain.project.ProjectLog;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;

@Data
public class RProjectLog {
	
	private String stageName;//阶段名
	private String content;//操作内容
	private String createDate;//创建日期
	private String createTime;//创建时分
	
	public RProjectLog(){} 
	public RProjectLog(ProjectLog projectLog){
		this.stageName=projectLog.getStageName();
		this.content=ParaseHtml.ParaseHtmlContent(projectLog.getContent());
		this.createDate=DateTimeUtils.formatDate(projectLog.getCreateTime(), "MM.dd");
		this.createTime=DateTimeUtils.formatDate(projectLog.getCreateTime(), "HH:mm");
	}
}
