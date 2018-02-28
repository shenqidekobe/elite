package com.ledao.elite.rest.framework.request;

import com.ledao.elite.core.domain.member.MemberPassport.Member_Channel;

import lombok.Data;

/**
 * 登录请求参数
 * */
@Data
public class RegisterRequest {

	private String currentType;//注册类型
	private String nickName;//昵称
	private String account;//帐号
	private String password;//密码
	private String verifyCode;//验证码
	private String inviteCode;//邀请码
	private String client=Member_Channel.Android.name();//客户端类型{默认Android}
}
