package com.ledao.elite.rest.framework.response;

import com.ledao.elite.core.domain.platform.PlatforMarketing;

import lombok.Data;

/**
 * 首页图
 * @author Chenghao
 *
 */
@Data
public class PlatforMarketingResponse {
	
	private String title;//标题
	private String imgPath;//图片ID
	private String href;//链接地址
	
	public PlatforMarketingResponse(){}
	
	public PlatforMarketingResponse(PlatforMarketing marketing){
		this.title=marketing.getTitle();
		this.imgPath=marketing.getAtta().getPath();
		this.href=marketing.getHref();
	}
}
