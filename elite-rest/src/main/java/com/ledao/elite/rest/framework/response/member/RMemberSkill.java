package com.ledao.elite.rest.framework.response.member;

import com.ledao.elite.core.domain.member.MemberSkill;

import lombok.Data;

/**
 * rest返回的技能对象
 * 
 * @author kobe.liu
 * */
@Data
public class RMemberSkill {
	
	private Long id;
	private String skill;//技能名称
	private String level;//技能级别，熟练度
	private String intro;//技能描述
	
	public RMemberSkill(){}
	public RMemberSkill(MemberSkill skill){
	}

}
