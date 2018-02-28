package com.ledao.elite.core.framework.shiro.sso;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ssoToken
 * */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SSOToken implements Serializable{
	
	private static final long serialVersionUID = 9097463307117829773L;
	
	
	public static Long expireTime = 14400l;//失效时间 分钟
	
	private Long id;//用户凭证ID
	private String accessToken;//用户凭证token
	private Date produceTime;//token产生时间

}
