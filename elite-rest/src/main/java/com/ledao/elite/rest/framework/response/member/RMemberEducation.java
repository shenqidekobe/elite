package com.ledao.elite.rest.framework.response.member;

import java.util.Date;

import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;

/**
 * rest返回的会员教育对象
 * 
 * @author kobe.liu
 * */
@Data
public class RMemberEducation {
	
	private Long id;
	private String organ;//机构
	private String major;//专业
	private String education;//学历
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String graduateTime;//毕业时间

	public RMemberEducation(){}
	public RMemberEducation(MemberEducation me){
		this.id=me.getId();
		this.organ=me.getOrgan();
		this.major=me.getMajor();
		this.education=me.getEducation();
		this.startTime=me.getStartTime();
		this.endTime=me.getEndTime();
		this.graduateTime=DateTimeUtils.formatDate(me.getGraduateTime(), "yyyy-MM");
	}
}
