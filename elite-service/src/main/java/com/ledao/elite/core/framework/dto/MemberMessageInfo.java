package com.ledao.elite.core.framework.dto;


import lombok.Data;

@Data
public class MemberMessageInfo {
	
	private String endTime;//最后条消息时间
	private String type;//消息类型
	private Integer count;//未读消息条数
}
