package com.ledao.elite.core.domain.partner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.utils.CommonUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 合作伙伴推荐的项目对象
 * 
 * @author kobe.liu
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class PartnerProject extends IdentifiedEntity {

	private static final long serialVersionUID = 4672854990600411981L;

	// 推荐的项目状态
	public enum PartnerProject_Status {
		wait_audit("待入驻"), audit_pass("入驻成功"), audit_nopass("入驻失败");
		public String label;

		PartnerProject_Status(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	private String phone;// 手机号
	private String name;// 名字
	private Long partnerId;// 合作伙伴ID
	@Column(length = 64)
	private String projectName;// 项目名称
	@Lob
	private String projectIntro;// 项目简介
	private Long attaId;// 项目附件
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private PartnerProject_Status status;// 状态
	private Long areaId;// 地区ID
	@Column(length = 32)
	private String areaName;// 地区名
	@Column(length = 128)
	private String productIndustry;// 项目行业
	@Column(length = 32)
	private String productStage;// 项目当前阶段{如：天使轮}

	private Integer enterCount = 0;// 入驻的项目数量{0表示没入驻过，大于0表示入驻过}
	private Long projectId;// 备案成功的项目ID
	@Column(length = 256)
	private String reason;// 处理原因
	private Long responsibleId;// 负责人
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;// 后台处理时间
	private Long auditId;// 负责人

	@Column(length = 128)
	private String projectSolution;// 项目解决方案类型
	@Transient
	private Project project;// 项目详情

	@Type(type = "yes_no")
	private boolean contactpartner;// 是否已partner 的名义联系
	@Transient
	private BigDecimal partnerAmount = new BigDecimal(0);// 合作伙伴收益

	@Transient
	private String partnerNickName;
	@Transient
	private String partnerRealName;

	// 项目当前阶段Val
	@Transient
	public String getProductStageVal() {
		return DictCache.getParamDesc(Dict.Dict_Type.project_filing_stage.name(), productStage);
	}

	// 项目所属行业
	@Transient
	public List<String> getIndustryList() {
		return CommonUtils.separatorStrToList(productIndustry, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
	}

	// 项目所属行业Val
	@Transient
	public List<String> getIndustryValList() {
		List<String> keyList = getIndustryList();
		List<String> valList = new ArrayList<>();
		for (String key : keyList) {
			valList.add(DictCache.getParamDesc(Dict.Dict_Type.choice_industry.name(), key));
		}
		return valList;
	}

	@Transient
	public String getIndustryVals() {
		List<String> keyList = getIndustryList();
		String result = "";
		for (String key : keyList) {
			String value = DictCache.getParamDesc(Dict.Dict_Type.choice_industry.name(), key);
			if (result == "") {
				result = value;
			} else {
				result = result + "," + value;
			}
		}
		return result;
	}

	@Transient
	public String getSolutionVals() {
		List<String> list = CommonUtils.separatorStrToList(projectSolution,
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		String result = "";
		for (String s : list) {
			if (StringUtils.isEmpty(s))
				continue;
			String value = DictCache.getParamDesc(Dict_Type.project_type.name(), s);
			if (result == "") {
				result = value;
			} else {
				result = result + "+" + value;
			}
		}
		return result;
	}

	/************************
	 * hibernate many one config
	 **************************/
	@ManyToOne
	@JoinColumn(name = "partnerId", insertable = false, updatable = false)
	private MemberPartnerCompany partner;// 合作伙伴
	@ManyToOne
	@JoinColumn(name = "projectId", insertable = false, updatable = false)
	private Project checkedProject;// 合作伙伴

}
