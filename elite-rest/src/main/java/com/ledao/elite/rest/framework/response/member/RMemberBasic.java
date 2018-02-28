package com.ledao.elite.rest.framework.response.member;

import java.util.Date;

import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberBasic.Sex_Enum;

import lombok.Data;

/**
 * rest返回的项目方对象
 * 
 * @author kobe.liu
 * */
@Data
public class RMemberBasic {
	
	private String sex=Sex_Enum.M.name();//性别,默认男
	private Date birthday;//会员生日
	private String areaName;//地区名称
	private Long photoId;//会员头像ID
	private String photoPath;//会员头像
	private String memberSign;//个性签名
	private String nickName;//用户昵称
	
	public RMemberBasic(){}
	public RMemberBasic(MemberBasic basic){
		this.sex=basic.getSex();
		this.birthday=basic.getBirthday();
		this.areaName=basic.getAreaName();
		this.memberSign=basic.getMemberSign();
		this.photoId=basic.getPhotoId();
		this.photoPath=basic.getPhotoId()==null?"":basic.getMemberPhoto().getPath();
	}

}
