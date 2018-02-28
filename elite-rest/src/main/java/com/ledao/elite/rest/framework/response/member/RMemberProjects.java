package com.ledao.elite.rest.framework.response.member;

import java.util.Date;

import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;

/**
 * rest返回的会员项目经验对象
 * 
 * @author kobe.liu
 * */
@Data
public class RMemberProjects {
	
	private Long id;
	private String project;//项目名称
	private String company;//公司
	private String position;//职位
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String intro;//项目简介
	
	public RMemberProjects(){}
	public RMemberProjects(MemberProjects mp){
		this.id=mp.getId();
		this.project=mp.getProject();
		this.company=mp.getCompany();
		this.position=mp.getPosition();
		this.startTime=mp.getStartTime();
		this.endTime=mp.getEndTime();
		this.intro=ParaseHtml.ParaseHtmlContent(mp.getIntro());
	}

}
