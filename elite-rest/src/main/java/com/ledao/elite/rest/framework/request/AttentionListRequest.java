package com.ledao.elite.rest.framework.request;

import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.rest.framework.RequestBaseRest;

import lombok.Getter;
import lombok.Setter;

/**
 * 关注列表请求参数
 * */
public class AttentionListRequest extends RequestBaseRest{

	private static final long serialVersionUID = -2795640036931729109L;
	
	@Setter@Getter
	private String keyword;//关键词
	@Setter@Getter
	private String type=MemberAttention.ME_ATTENTION;//关注的类型{关注我的或我关注的}，默认：我关注的
	@Setter@Getter
	private Integer pageth;//关键词

}
