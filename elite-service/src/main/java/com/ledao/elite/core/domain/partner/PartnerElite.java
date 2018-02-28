package com.ledao.elite.core.domain.partner;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.cache.custom.DictCache;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 合作伙伴推荐的精英对象
 * 
 * @author kobe.liu
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class PartnerElite extends IdentifiedEntity {

	private static final long serialVersionUID = 5807432800106237137L;
	public static final Integer MAX_JOBAGE = 20;// 代表最大的工作年限(十年以上)

	// 推荐的人才状态
	public enum PartnerElite_Status {
		wait_audit("待审核"), audit_pass("成功入驻"), audit_nopass("入驻失败"),
		noRegister("未注册"),registered("已注册");
		public String label;
		PartnerElite_Status(String label) {
			this.label = label;
		}
		public String getLabel() {
			return label;
		}
	}

	// 管理精英 查询类别
	public enum PartnerElite_Search_Type {
		all("全部"), last_login_over_7days("七天未登录"), no_task_ever("注册后从未接单"), no_recevieandcurrenttask_in_14days(
				"近14日内未接单且无正在进行中项目"),no_enter("已注册未入驻"),no_register("未注册");
		public String label;

		PartnerElite_Search_Type(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	// 入驻方式
	public enum PartnerElite_Way {
		put("手动备案"), invite("邀请码备案");
		public String label;

		PartnerElite_Way(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	private Long partnerId;// 合作伙伴ID
	@Column(length = 32)
	private String name;// 姓名
	@Column(length = 11)
	private String phone;// 电话
	@Column(length = 20)
	private String idCard;// 身份证
	@Column(length = 32)
	private String email;// 邮箱
	private Integer jobAge;// 工作年限(2016-09-12更新为数值类型)
	private Long areaId;// 地区ID
	@Column(length = 16)
	private String areaName;// 地区名称
	@Column(length = 32)
	private String eliteProperty;// 人才属性
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private PartnerElite_Status status;// 状态
	@Column(length = 32)
	private String defineRole;// 角色定位
	@Column(length = 512)
	private String intro;// 精英描述
	private Long attaId;// 附件简历
	private Date expireTime;// 备案失效时间
	private Long memberId;// 备案成功的会员ID

	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private PartnerElite_Way enterWay;// 入驻方式

	@Column(length = 256)
	private String reason;// 处理原因
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;// 后台处理时间
	private Long auditId;// 负责人
	private BigDecimal income = new BigDecimal(0);// 对其累计的收益

	@Transient
	private String creditStatus;// 征信信息状态

	@Transient
	private int tasksInCount;// 进行中的任务或者项目数量
	@Transient
	private BigDecimal tasksInAmount = new BigDecimal(0);// 进行中的任务或者项目金额

	@Transient
	private int tasksFinishCount;// 已完成的任务或者项目数量
	@Transient
	private BigDecimal tasksFinishAmount = new BigDecimal(0);// 已完成的任务或者项目金额
	@Transient
	private BigDecimal withdrawAmount = new BigDecimal(0);// 本月已经提现金额
	@Transient
	private BigDecimal partnerAmount = new BigDecimal(0);// 合伙人收益
	@Transient
	private Date lastRecruitTime;// 最后一次接单时间

	@Transient
	public String getJobAgeVal() {
		if (jobAge == null) {
			return "";
		}
		if (jobAge == MAX_JOBAGE) {
			return "10年以上";
		}
		return jobAge + "年";
	}

	// 角色定位
	@Transient
	public String getDefineRoleVal() {
		return DictCache.getParamDesc(Dict.Dict_Type.job_role.name(), this.defineRole);
	}

	// 入驻精英状态
	@Transient
	private Member_Status eliteStatus;// 状态
	// 入驻精英详情
	@Transient
	private MemberElite memberElite;// 备案精英
	@Transient
	private MemberBasic memberBasic;// 备案精英基本信息
	@Transient
	private MemberCredit memberCredit;//备案精英征信信息
	
	/************************ * hibernate many one config**************************/
	@ManyToOne
	@JoinColumn(name = "partnerId", insertable = false, updatable = false)
	private MemberPartnerElite partner;// 合作伙伴
	@ManyToOne
	@JoinColumn(name = "memberId", insertable = false, updatable = false)
	private MemberPassport memberPassport;// 账户信息

	@ManyToOne
	@JoinColumn(name = "attaId", insertable = false, updatable = false)
	private Attas atta;

}
