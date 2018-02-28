package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.cache.custom.DictCache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * CEO创业属性对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberCompany extends IdentifiedEntity {

	private static final long serialVersionUID = -5236613955600317882L;
	
	private Long memberId;//会员ID
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Member_Status status;//状态
	
	@Type(type = "yes_no")
	private boolean stared=false;//是否金盘雇主
	@Type(type = "yes_no")
	private boolean companyed=true;//是否注册公司
	@Column(length=32)
	private String companyPosition;//职位
	@Column(length=32)
	private String companyName;//公司名
	@Column(length=32)
	private String companyScale;//公司规模
	@Column(length=4000)
	private String companyIntro;//公司简介
	@Column(length=32)
	private String teamNum;//团队规模人数
	@Column(length=4000)
	private String teamIntro;//团队简介
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间
	private Long auditId;//审核ID
	@Column(length=256)
	private String auditReason;//审核原因
	
	public MemberCompany(Long memberId) {
		super();
		this.memberId = memberId;
	}

	public boolean getStared() {
		return stared;
	}
	public void setStared(boolean stared) {
		this.stared = stared;
	}
	public boolean getCompanyed() {
		return companyed;
	}
	public void setCompanyed(boolean companyed) {
		this.companyed = companyed;
	}
	
	//公司规模
	@Transient
	public String getCompanyScaleVal(){
		return DictCache.getParamDesc(Dict.Dict_Type.company_scale.name(), this.companyScale);
	}
	//团队规模
	@Transient
	public String getTeamNumVal(){
		return DictCache.getParamDesc(Dict.Dict_Type.company_scale.name(), this.teamNum);
	}
	
}
