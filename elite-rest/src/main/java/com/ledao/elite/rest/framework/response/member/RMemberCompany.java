package com.ledao.elite.rest.framework.response.member;

import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;

/**
 * rest返回的项目方对象
 * 
 * @author kobe.liu
 * */
@Data
public class RMemberCompany {
	
	private String companyName;//公司名
	private String companyPosition;//职位
	private String companyScale;//公司规模key
	private String companyScaleVal;//公司规模value
	private String companyIntro;//公司简介
	
	public RMemberCompany(){}
	public RMemberCompany(MemberCompany company){
		this.companyName=company.getCompanyName();
		this.companyPosition=company.getCompanyPosition();
		this.companyScale=company.getCompanyScale();
		this.companyScaleVal=company.getCompanyScaleVal();
		this.companyIntro=ParaseHtml.ParaseHtmlContent(company.getCompanyIntro());
	}

}
