package com.ledao.elite.manager.shiro;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录信息
 * */
@Data
@EqualsAndHashCode(callSuper = false, of = { "userId" })
public class Principal implements Serializable {

	private static final long serialVersionUID = 817992555595698050L;
	
	private Long userId;//用户ID
	private String userName;//用户名称
	private String loginName;//登录名称
	private Long roleId;//角色ID
	private String roleName;//角色名称
	private Long photoId;//头像ID
	private String photoPath;//头像地址
}

