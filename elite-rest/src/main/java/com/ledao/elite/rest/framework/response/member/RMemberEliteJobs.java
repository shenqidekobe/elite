package com.ledao.elite.rest.framework.response.member;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberEliteJobs;

import lombok.Data;

/**
 * rest返回的精英对象
 * 
 * @author kobe.liu
 * */
@Data
public class RMemberEliteJobs {
	
	private String jobRole;//角色key
	private String jobRoleVal;//角色名称
	private String jobAdept;//职位下所擅长的技能，多个按逗号分割存储
	private Integer level;//级别
	private List<String> jobAdeptList;//精英职位map
	
	public RMemberEliteJobs(){}
	public RMemberEliteJobs(MemberEliteJobs jobs){
		this.jobRole=jobs.getJobRole();
		this.jobRoleVal=jobs.getJobRoleVal();
		this.jobAdept=jobs.getJobAdept();
		this.level=jobs.getLevel();
		this.jobAdeptList=jobs.getJobAdeptValStrList();
	}

}
