package com.ledao.elite.rest.framework.response;

import java.math.BigDecimal;

import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberBasic.Sex_Enum;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;

import lombok.Data;

/**
 * 会员个人主页的信息返回对象
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
public class MemberHomeResponse{
	
	private String memberNum;//编号
	private String account;//帐号
	private String email;//邮箱
	private String nickName;//昵称
	private String areaName;//地区
	private String sex=Sex_Enum.M.name();//性别，默认男的
	private String status;//状态{enum:Member_Status}
	private String headImg;//头像地址
	private double integrity=5.0;//诚信度{默认5分}
	private String memberSign;//头衔/职位
	private String currentType;//当前身份
	private boolean stared=false;//是否明星成员/金牌雇主
	private boolean taked=true;//是否接活
	private boolean ctoed=false;//是否cto
	private BigDecimal totalIncome;//收益总额
	private BigDecimal trustAmount;//托管费用
	private Integer count;//进行中数量或者发布的项目数量
	private Integer fansCount;//粉丝数量
	
	
	
	public MemberHomeResponse(){}
	public MemberHomeResponse(MemberDetailInfo member){
		this.memberNum=member.getMemberNum();
		this.account=member.getAccount();
		this.email=member.getEmail();
		this.nickName=member.getNickName();
		this.currentType=member.getCurrentType();
		this.totalIncome=member.getTotalIncome();
		this.trustAmount=member.getTrustAmount();
		this.fansCount=member.getFansCount();
		MemberBasic basic=member.getBasic();
		if(basic!=null){
			this.integrity=basic.getIntegrity();
			this.sex=basic.getSex();
			this.headImg=basic.getMemberPhoto()==null?null:basic.getMemberPhoto().getPath();
			this.memberSign=basic.getMemberSign();
			this.areaName=basic.getAreaName();
		}
		if(MemberIdentity_Type.company.name().equals(member.getCurrentType())){
			MemberCompany company=member.getCompany();
			this.stared=company.getStared();
			this.status=company.getStatus().name();
			this.count=member.getPublishProjectCount();
		}else if(MemberIdentity_Type.cto.name().equals(member.getCurrentType())||MemberIdentity_Type.elite.name().equals(member.getCurrentType())){
			MemberElite elite=member.getElite();
			this.taked=elite.isTaked();
			this.stared=elite.isStared();
			this.ctoed=elite.isCtoed();
			this.status=elite.getStatus().name();
			this.count=member.getTaskCount()+member.getCarryProjectCount();//承接项目数量+任务数量
		}
		
	}

}
