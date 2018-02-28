package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员基础信息对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberBasic extends IdentifiedEntity{
	
	private static final long serialVersionUID = -8794920793442348460L;
	
	public enum Sex_Enum{
		M("男"),
		F("女");
		public String label;
		Sex_Enum(String label){
			this.label=label;
		}
	}
	
	private Long memberId;//会员ID
	@Column(length=32)
	private String email;//绑定邮箱
	@Column(length=16)
	private String sex=Sex_Enum.M.name();//性别,默认男
	@Temporal(TemporalType.DATE)
	private Date birthday;//会员生日
	private Long areaId;//地区ID
	@Column(length=16)
	private String areaName;//地区名称
	private Long photoId;//会员头像{附件ID}
	@Column(length=512)
	private String memberSign;//个性签名
	private double integrity=5.0;//诚信度{默认5分}
	
	
	@Transient
	private String nickName;
	
	@Transient
	private boolean copyed=false;//是否复制帐号
	@Transient
	private boolean asynced=true;//是否同步到其他帐号
	
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "photoId", insertable=false, updatable=false)
    private Attas memberPhoto;//会员头像
    
    @ManyToOne
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport member;//会员帐号

}
