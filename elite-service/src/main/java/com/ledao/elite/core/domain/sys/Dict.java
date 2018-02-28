package com.ledao.elite.core.domain.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.framework.dto.ChildrenDict;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 数据字典对象
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = true,of={})
@ToString(callSuper = true, of = {})
public class Dict extends IdentifiedEntity{

	private static final long serialVersionUID = 6133391516614101921L;
	
	public enum Dict_Type{
		project_stage("项目阶段"),
		partnerCompany_option("项目渠道设置"),
		partnerElite_option("人才渠道设置"),
		job_role("职业类型"),
		job_age("相关工作年限"),
		good_job("擅长职位"),
		project_budget("项目预算"),
		company_scale("公司规模"),
		allot_duration("每周可分配时长"),
		choice_industry("可选的关注行业"),
		project_type("项目开发类型"),
		project_func("项目功能类型"),
		task_type("任务类型"),
		evaluate_tags("评价标签"),
		education_type("学历类型"),
		project_filing_stage("项目备案阶段");

		public String label;
		Dict_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	/**
	 * 项目质保设置
	 */
	
	public enum PartnerIncome_Option_Key
	{
		PartnerCompanyIncomeRule("项目渠道收益规则"),
		PartnerEliteIncomeRule("人才渠道收益规则");
		public String label;
		PartnerIncome_Option_Key(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	private Long parentId;//上级ID
	@Column(length=32)
	private String dictType;//类型
	@Column(length=32)
	private String dictKey;//key
	@Column(length=32)
	private String dictVal;//value
	@Column(length=64)
	private String dictDesc;//描述信息
	private Integer orders;//排序号
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parentId", insertable = false, updatable = false)
	private Dict parent;
	
	@JsonIgnore
	@OneToMany(mappedBy = "parent",fetch=FetchType.LAZY)
	@OrderBy("orders ASC")
	private List<Dict> children = new ArrayList<Dict>();
	
	@Transient
	public List<ChildrenDict> getChildrenDict(){
		List<ChildrenDict> childrenList = new ArrayList<ChildrenDict>();
		for(Dict dict:children){
			childrenList.add(new ChildrenDict(dict));
		}
		return childrenList;
	}
}
