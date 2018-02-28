package com.ledao.elite.rest.framework.request;


import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectForEliteRequest  extends RequestBaseRest{

	private static final long serialVersionUID = 4919605192456038851L;

	private Long projectId;
	private String status;
	private String type;
	private Integer pageth = 1; 
	private String recordStages;//竞标各阶段时间，费用集合
}
