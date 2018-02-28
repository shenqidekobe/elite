package com.ledao.elite.site.dto;

import lombok.Data;

/**
 * 会员登录返回对象
 * 
 * @author kobe.liu
 * @date 2016-09-13
 * */
@Data
public class LoginResponse {
	
	private String token;//安全token码
	private String currentType;//当前的身份类型
	private String redirect;//登录后的去向

}
