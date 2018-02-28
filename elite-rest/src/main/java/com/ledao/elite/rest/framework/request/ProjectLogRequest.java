package com.ledao.elite.rest.framework.request;

import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectLogRequest extends RequestBaseRest{
	
	private static final long serialVersionUID = 4919605192456038851L;
	
	private Long projectId;
	private Long taskId;
	private String stageCode;
	private String startTime;
	private String endTime;
	private String keyword;
	private Integer pageth = 1; 
	
	public Pager getPager(){
		Pager page = new Pager(0,50);
		page.setPageth(pageth);
		return page;
	}
}
