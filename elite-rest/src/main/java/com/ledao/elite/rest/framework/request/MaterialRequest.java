package com.ledao.elite.rest.framework.request;

import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MaterialRequest extends RequestBaseRest{

	private static final long serialVersionUID = 4919605192456038851L;

	private Long projectId;
	private Long taskId;
	private Long stageId;
	private String queryType; 
	private Integer pageth = 1; 
}
