package com.ledao.elite.rest.framework.response.member;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberEliteJobs;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;

/**
 * rest返回的精英对象
 * 
 * @author kobe.liu
 * */
@Data
public class RMemberElite {
	
	private Integer jobAge;
	private String jobAgeVal;//工作年限
	private String allotDuration;
	private String allotDurationVal;//每周可分配时长
	private List<String> industryValList;//关注行业
	private boolean onjobed;//是否在职
	private String intro;//个人简介说明{自我描述}
	private boolean aplayFlag;//申请资格
	private String aplayDate;//下次申请资格时间
	private RMemberEliteJobs memberEliteJobs;//精英职位map
	
	public RMemberElite(){}
	public RMemberElite(MemberElite elite){
		this.jobAge=elite.getJobAge();
		this.jobAgeVal=elite.getJobAgeVal();
		this.allotDuration=elite.getAllotDuration();
		this.allotDurationVal=elite.getAllotDurationVal();
		this.industryValList=elite.getAttentionIndustryListVal();
		this.onjobed=elite.isOnjobed();
		this.intro=ParaseHtml.ParaseHtmlContent(elite.getIntro());
		this.aplayFlag=elite.getApplyFlag();
		//计算职位map
		List<MemberEliteJobs> jobs=elite.getEliteJobs();
		if(!jobs.isEmpty()){
			memberEliteJobs=new RMemberEliteJobs(jobs.get(0));
		}
	}

}
