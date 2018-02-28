package com.ledao.elite.rest.framework.response;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberBasic.Sex_Enum;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.rest.framework.response.member.RMemberCompany;
import com.ledao.elite.rest.framework.response.member.RMemberCredit;
import com.ledao.elite.rest.framework.response.member.RMemberEducation;
import com.ledao.elite.rest.framework.response.member.RMemberElite;
import com.ledao.elite.rest.framework.response.member.RMemberProjects;

import lombok.Data;

/**
 * 会员个人资料的信息返回对象
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
public class MemberInfoResponse{
	
	private String memberNum;//编号
	private String nickName;//昵称
	private String areaName;//地区
	private String sex=Sex_Enum.M.name();//性别，默认男的
	private String status;//状态{enum:Member_Status}
	private String headImg;//头像地址
	private String memberSign;//头衔/职位
	private String currentType;//当前身份
	private boolean stared=false;//是否明星成员/金牌雇主
	private boolean ctoed=false;//是否cto
	private Integer taskCount=0;//任务数
	private Integer handProjectCount=0;//承接项目数(CTO)
	
	private RMemberElite elite;//精英当前状态信息
	private RMemberCompany company;//项目方信息
	private RMemberProjects firstProject;//精英项目经验第一条数据
	private RMemberEducation firstEducation;//精英教育经历第一条数据
	private RMemberCredit credit;//会员征信信息
	
	
	
	public MemberInfoResponse(){}
	public MemberInfoResponse(MemberDetailInfo member){
		this.memberNum=member.getMemberNum();
		this.nickName=member.getNickName();
		this.currentType=member.getCurrentType();
		this.taskCount = member.getTaskCount();
		MemberBasic basic=member.getBasic();
		if(basic!=null){
			this.sex=basic.getSex();
			this.headImg=basic.getMemberPhoto()==null?null:basic.getMemberPhoto().getPath();
			this.memberSign=basic.getMemberSign();
			this.areaName=basic.getAreaName();
		}
		if(MemberIdentity_Type.company.name().equals(member.getCurrentType())){
			MemberCompany mc=member.getCompany();
			this.company=new RMemberCompany(mc);
			this.stared=mc.getStared();
			this.status=mc.getStatus().name();
		}else if(MemberIdentity_Type.cto.name().equals(member.getCurrentType())||MemberIdentity_Type.elite.name().equals(member.getCurrentType())){
			MemberElite me=member.getElite();
			this.stared=me.isStared();
			this.ctoed=me.isCtoed();
			this.status=me.getStatus().name();
			this.elite=new RMemberElite(me);
			List<MemberProjects> proList=member.getProjects();
			List<MemberEducation> eduList=member.getEducations();
			this.firstProject=proList.isEmpty()?null:new RMemberProjects(proList.get(0));
			this.firstEducation=eduList.isEmpty()?null:new RMemberEducation(eduList.get(0));
		}
		this.credit=new RMemberCredit(member.getCredit());
	}

}
