package com.ledao.elite.rest.framework.response.project;

import com.ledao.elite.core.domain.member.MemberPassport;

import lombok.Data;

/**
 * 认领任务精英信息
 * @author Chenghao
 */
@Data
public class RTaskMember {
	private Long id;
	private Long taskId;
	private String nickName;//昵称
	private String photoPath;//头像
	private String allotDurationVal;//每周可分配时长
	private String jobAgeVal;//工作年限
	private String memberSign;//个性签名
	
	public RTaskMember(){}
	
	public RTaskMember(MemberPassport member){
		this.id=member.getId();
		this.nickName=member.getNickName();
		this.memberSign=member.getBasic().getMemberSign();
		this.allotDurationVal=member.getElite().getAllotDurationVal();
		this.jobAgeVal=member.getElite().getJobAgeVal();
	}
}
