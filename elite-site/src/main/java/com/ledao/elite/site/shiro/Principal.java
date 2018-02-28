package com.ledao.elite.site.shiro;

import java.io.Serializable;
import java.util.Map;

import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户登录信息
 * */
@Data
@EqualsAndHashCode(callSuper = false, of = { "memberId" })
@AllArgsConstructor
@NoArgsConstructor
public class Principal implements Serializable {

	private static final long serialVersionUID = 817992555595698050L;
	
	private Long memberId;//会员ID
	private String currentType;//会员当前的类型
	private String memberNum;//会员编号
	private String nickName;//会员昵称
	private String header;//会员头像
	private String token;//在线用户token
	private Member_Status status;//用户状态
	private Map<String,String> identityMap;//会员身份map
	
	public Principal(String header){
		this.header=header;
	}
}

