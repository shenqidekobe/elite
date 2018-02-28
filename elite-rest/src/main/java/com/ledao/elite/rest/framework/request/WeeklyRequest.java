package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class WeeklyRequest  extends RequestBaseRest{
	
	private static final long serialVersionUID = 4919605192456038851L;

	private Long taskId;
	private Long projectId;
	private String weeklyType;
	private String content;
	private Long memberId;
	private int month;
	private int week;
	private Integer pageth = 1; 
}
