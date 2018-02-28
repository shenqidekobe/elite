package com.ledao.elite.core.framework.listener.event;

import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.framework.dto.MemberUpdatePass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员身份数据事件
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberIdentityDataTask extends BaseTask{
	
	private MemberBasic basic;//基本信息
	private MemberBank bank;//银行卡信息
	private MemberCredit credit;//证件信息
	private MemberUpdatePass updatePass;//登陆密码
}
