package com.ledao.elite.core.domain.member;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.partner.PartnerElite;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员通行证
 * 
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class MemberPassport extends IdentifiedEntity {

	private static final long serialVersionUID = -8794920793442348460L;

	// 会员帐号后缀标识
	public enum Member_Suffix {
		_C("项目方"), _E("精英"), _G("项目渠道"), _F("人才渠道");
		public String label;

		Member_Suffix(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	// 会员的状态定义
	public enum Member_Status {
		waitAduit("待完善"), aduitIn("申请审核"), normal("正常状态"), auditNotPass("审核未通过"), disabled("已禁用"), deleted("已删除");
		public String label;

		Member_Status(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	// 会员的来源渠道定义
	public enum Member_Channel {
		PC("PC网站"), PARTNER("渠道推荐"), Android("Adnroid客户端"), IOS("iOS客户端");
		public String label;

		Member_Channel(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	@Column(length = 32)
	private String memberNum;// 会员编号
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private Member_Status status;// 会员状态
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private Member_Channel channel = Member_Channel.PC;// 会员来源渠道
	@Column(length = 32)
	private String account;// 手机号码,登录用户名
	@Column(length = 32)
	private String password;// 登录密码
	@Column(length = 32)
	private String email;// 绑定邮箱{用于登录}
	@Column(length = 32)
	private String passSalt;// 密码盐串
	@Column(length = 32)
	private String nickName;// 昵称

	@Column(length = 32)
	private String recommendNum;// 推荐人帐号
	@Column(length = 32)
	private String inviteCode;// 邀请码
	@Type(type = "yes_no")
	private boolean homeShow = false;// 是否首页显示
	private Integer viewCount = 0;// 被查看的次数

	private double closeTime;// 关停时间
	@Column(length = 128)
	private String closeReason;// 关停理由
	@Temporal(TemporalType.TIMESTAMP)
	private Date disabledTime;// 禁用时间
	private Long disabledId;// 禁用者ID
	@Temporal(TemporalType.TIMESTAMP)
	private Date removeTime;// 删除时间
	private Long removeId;// 删除者ID

	@Column(length = 64)
	private String lastLoginIp;// 最后登录IP
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;// 最后登录时间
	@Column(length = 32)
	private String currentType;// 会员当前类型
	private Integer loginOrder = 0;// 多帐号模式下的登录次序(每次登录时和切换身份时更新为1，其他身份为0)

	@Transient
	private MemberIdentity currentIdentity;// 当前身份
	@Transient
	private MemberBasic basic;// 会员基础信息
	@Transient
	private MemberCredit credit;// 会员征信信息
	@Transient
	private MemberAccount memberAccount;// 会员账户信息
	@Transient
	private MemberElite elite;// 精英信息
	@Transient
	private MemberCompany company;// 项目方信息
	@Transient
	private MemberPartnerCompany partnerCompany;// 渠道项目方
	@Transient
	private MemberPartnerElite partnerElite;// 渠道人才方
	@Transient
	private PartnerElite parElite;// 渠道人才方精英
	@Transient
	private List<MemberProjects> projects;// 会员项目经验
	@Transient
	private List<MemberEducation> educations;// 会员教育经历
	@Transient
	private List<MemberSkill> skills;// 会员技能
	@Transient
	private int projectDoCount;// 进行中的项目数，后台使用
	@Transient
	private Boolean attentioned = false;// 是否关注
	@Transient
	private Integer fansCount = 0;// 粉丝数量
	@Transient
	private String invitePhone;// 邀请手机号
	@Transient
	private boolean disposePass = true;// 是否处理密码字符串(用于复制帐号时的特殊处理)

	/**
	 * 格式化手机号码
	 */
	public String getFormatPhone() {
		String account=getAccountOffSuffix();
		if (StringUtils.isEmpty(account)) {
			return "***********";
		}
		String corbet = account;
		try {
			corbet = corbet.substring(0, 3) + "****" + corbet.substring(corbet.length() - 4);
		} catch (Exception e) {
		}
		return corbet;
	}

	/**
	 * 格式化邮箱
	 */
	public String getFormatEmail() {
		if (StringUtils.isEmpty(email)) {
			return "***********";
		}
		String corbet = email;
		try {
			int i = corbet.indexOf("@");
			corbet = "*****" + corbet.substring(i, corbet.length());
		} catch (Exception e) {
		}
		return corbet;
	}

	/**
	 * 去除后缀的账户字符
	 */
	public String getAccountOffSuffix() {
		String fix = account;
		String rsp = fix.split("_")[0];
		return rsp;
	}

	/**
	 * 去除account的账户字符
	 */
	@Transient
	public static String accountOffSuffix(String account) {
		String fix = account;
		String rsp = fix.split("_")[0];
		return rsp;
	}

	/**
	 * 去除account的账户字符
	 */
	@Transient
	public static Member_Suffix typeToSuffix(String type) {
		Member_Suffix fix=null;
		MemberIdentity_Type mit = MemberIdentity_Type.valueOf(MemberIdentity_Type.class, type);
		switch (mit) {
		case company:
			fix=Member_Suffix._C;
			break;
		case cto:
			fix=Member_Suffix._E;
			break;
		case elite:
			fix=Member_Suffix._E;
			break;
		case partnerCompany:
			fix=Member_Suffix._G;
			break;
		case partnerElite:
			fix=Member_Suffix._F;
			break;
		}
		return fix;
	}

	/************************
	 * hibernate many one config
	 **************************/
	@OneToMany(mappedBy = "memberPassport")
	private List<MemberRole> roles;// 用户的角色列表

	@OneToMany(mappedBy = "memberPassport")
	private List<MemberIdentity> identitys;// 用户的身份列表

}
