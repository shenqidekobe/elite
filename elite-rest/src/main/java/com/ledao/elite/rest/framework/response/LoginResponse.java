package com.ledao.elite.rest.framework.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 登录返回对象
 * */
@Data
public class LoginResponse{


	@Getter@Setter
	private Long memberId;//会员ID
	@Getter@Setter
	private String currentType;//当前身份
	@Getter@Setter
	private String ssoToken;//标识码
	@Getter@Setter
	private Long expireTime;//过期时间{分钟}
}
