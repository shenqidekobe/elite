package com.ledao.elite.rest.framework.response.project;

import java.util.List;

import lombok.Data;

@Data
public class RProjectLogInfo {
	
	private String title;
	private List<RProjectLog> list;
	
	public RProjectLogInfo(){}
	
	public RProjectLogInfo(String title,List<RProjectLog> list){
		this.title=title;
		this.list=list;
	}
}
