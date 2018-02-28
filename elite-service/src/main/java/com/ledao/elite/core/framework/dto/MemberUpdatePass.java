package com.ledao.elite.core.framework.dto;

import lombok.Data;

/**
 * 会员更新密码对象
 * */
@Data
public class MemberUpdatePass {
	
	private Long memberId;
	private String nickName=null;
	private String email=null;
	private String account=null;
	private String passSalt;
	private String password;

}
