package com.ledao.elite.core.framework.dto;

/**
 * 通知json文件操作类
 * 
 * @author Administrator
 *
 */
public class NoticeJsonBean {

	// 项目ID
	private String projectId;

	// 通知类型
	private String noticeType;

	//
	private String foreignId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

}
