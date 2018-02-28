package com.ledao.elite.core.framework.constant;

/**
 * 新通知（小红点）枚举类
 * */
public enum NoticeTypeEnum {

	Weekly("周报"),
	Material("文件"),
	PmNewProject("PM新项目"),
	CtoTender("CTO竞标");
	public String value;
	NoticeTypeEnum(String value) {
    	this.value = value;
	}
	public String getValue(){
		return value;
	}
}
