package com.ledao.elite.core.domain.platform;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台邀请码信息接口
 * 包含(会员邀请码、项目方邀请码、合作伙伴渠道邀请码)
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformInviteCode extends IdentifiedEntity{

	private static final long serialVersionUID = 5113995637448598461L;
	
	//邀请码类型
	public enum InviteCode_Type{
		channel_company("渠道项目方邀请码"),
		channel_elite("渠道猎头方邀请码"),
		member("会员邀请码"),
		platform_one("平台邀请码一次"),
		platform_more("平台邀请码N次"),
		platform_infinite("平台邀请码不限次");
		public String label;
		InviteCode_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private InviteCode_Type type;//邀请码类型
	
	@Column(length=32)
	private String inviteCode;//邀请码
	
	private Long userId;//所属用户ID
	
	@Column(length=128)
	private String href;//链接
	
	private int inviteCount=0;//邀请数量
	
	private int maxCount=1;//最大限制的数量(默认1)
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expireTime;//过期时间
	
	/************************hibernate many one config**************************/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private MemberPassport memberPassport;// 用户的具体信息
}
