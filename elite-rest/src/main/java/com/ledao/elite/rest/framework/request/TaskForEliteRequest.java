package com.ledao.elite.rest.framework.request;


import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class TaskForEliteRequest extends RequestBaseRest{
	
	private static final long serialVersionUID = 4919605192456038851L;

	private Long taskId;
	private Long eliteId;
	private Long memberId;
	private String password;
	private String type;
	private Integer pageth = 1; 
	private String oper;//是否报名（N,Y）
}
