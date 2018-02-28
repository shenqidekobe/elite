package com.ledao.elite.rest.framework.response.project;

import com.ledao.elite.core.domain.member.MemberMessage;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;

@Data
public class RMemberMessage {

	private String title;//标题
	private String content;//内容
	private String status;//状态
	private String createTime;//发送时间
	private String imgPath;
	private String src;
	public RMemberMessage(){}
	
	public RMemberMessage(MemberMessage message){
		this.title=message.getTitle();
		this.content=ParaseHtml.ParaseHtmlContent(message.getContent());
		this.status=message.getStatus().name();
		this.createTime=DateTimeUtils.formatDate(message.getCreateTime(), "yyyy.MM.dd");
	}
	
}
