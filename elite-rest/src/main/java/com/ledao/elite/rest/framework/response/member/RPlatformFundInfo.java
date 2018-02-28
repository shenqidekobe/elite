package com.ledao.elite.rest.framework.response.member;

import java.util.List;

import lombok.Data;

@Data
public class RPlatformFundInfo {
	
	private String createDate;

	private List<RPlatformFund> fundlist;
	
	public RPlatformFundInfo(){}
	public RPlatformFundInfo(String createDate,List<RPlatformFund> fundlist){
		this.createDate=createDate;
		this.fundlist=fundlist;
	}
}
