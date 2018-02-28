package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberCircleRequest  extends RequestBaseRest{
	
	private static final long serialVersionUID = 4919605192456038851L;
	
	private Long memberId;
	
	private String role;
	
	private Integer pageth = 1; //页码
	
	private String keyword;//查询参数

}
