package com.ledao.elite.rest.framework.response.member;


import com.ledao.elite.core.domain.member.MemberPassport;

import lombok.Data;

@Data
public class RMemberCircle{
	
	private Long memberId;
	private String nickName;
	private String memberPhotoPath;//用户头像
	private String memberSign;
	private Integer attentionCount;//粉丝数量
	private boolean isAttentioned;//是否被关注
	
	public RMemberCircle(){}
	public RMemberCircle(MemberPassport member){
		this.memberId=member.getId();
		this.nickName=member.getNickName();
		this.memberSign=member.getBasic().getMemberSign();
		this.memberPhotoPath=member.getBasic().getPhotoId()==null?"":member.getBasic().getMemberPhoto().getPath();
		this.attentionCount=member.getFansCount();
		this.isAttentioned=member.getAttentioned();
	}

}
