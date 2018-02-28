package com.ledao.elite.core.domain.partner;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberPartnerElite.MemberPartnerElite_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Way;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Cooperate_Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 会员合作伙伴- 推荐渠道记录
 * 
 * @author kobe.liu
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class PartnerRecord extends IdentifiedEntity {

	
	//会员的状态定义
	public enum PartnerRecord_Status{
		no_register("待注册"),
		registered("已注册"),
		audit_pass("已入驻"),
		audit_nopass("备案失败"),
		normal("已入驻");
		public String label;
		PartnerRecord_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	//渠道类别
	public enum PartnerRecord_Type{
		partnerElite("人才渠道方"),
		partnerCompany("项目渠道方");
		public String label;
		PartnerRecord_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	// 管理精英 查询类别
    public enum PartnerRecord_SearchType {
		all("全部"), 
		last_login_over_7days("七天未登录"),
		no_task("注册后从未备案"),
		no_task_over_14days("近14日未备案操作"),
		no_register("未注册");
		public String label;
		PartnerRecord_SearchType(String label) {
			this.label = label;
		}
		public String getLabel() {
			return label;
		}
	}
	private static final long serialVersionUID = -3795968724190797463L;
	private Long memberId;// 会员ID
	private String memberPhone;//会员电话
	private Long parentId;//上级合作伙伴ID{他的推荐人}
	@Column(length = 32)
	private String name;// 姓名
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private PartnerRecord_Status status;// 状态
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private PartnerRecord_Type partnerType;// 状态
	@Enumerated(EnumType.STRING)
	private MemberPartnerElite_Type type;// 类别
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private PartnerElite_Way enterWay;// 入驻方式
	@Column(length = 32)
	private String defineRole;// 角色定位
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private Cooperate_Type companyType;// 项目渠道类型
	@Column(length = 32)
	private String areaName;// 地区
	@Column(length = 32)
	private String companyName;//公司
	@Transient
	private MemberBasic memberBasic;// 会员基本内容
	@Transient
	private Integer putCount;//备案数
	@Transient
	private Integer enterCount;//入驻案数
	@Transient
	private Integer partnerPutCount;//备案渠道数
	@Transient
	private Integer partnerEnterCount;//渠道入驻案数
	@Transient
	private BigDecimal channelIncome;//渠道收益
	@Transient
	private BigDecimal parentIncome;//父收益
	@Transient
	public String getDefineRoleVal() {
		return DictCache.getParamDesc(Dict.Dict_Type.job_role.name(), this.defineRole);
	}
	/************************ * hibernate many one config**************************/
	@ManyToOne
	@JoinColumn(name = "memberId", insertable = false, updatable = false)
	private MemberPassport memberPassport;// 账户信息
}
