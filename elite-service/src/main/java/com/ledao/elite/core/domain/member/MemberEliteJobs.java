package com.ledao.elite.core.domain.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.utils.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 精英会员的工作职能(擅长的技术领域或者角色)
 * 关联精英表
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberEliteJobs extends IdentifiedEntity{
	
	private static final long serialVersionUID = -8794920793442348460L;
	
	private Long eliteId;//精英ID
	@Column(length=16)
	private String jobRole;//职位角色
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateRoleDate;
	@Column(length=256)
	private String jobAdept;//职位下所擅长的技能，多个按逗号分割存储
	private Integer level=1;//职位角色等级

	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//最后审核评级时间
	private Long auditId;//审核ID
	@Column(length=256)
	private String auditReason;//审核原因
	
	public MemberEliteJobs(Long eliteId, String jobRole,String jobAdept) {
		super();
		this.eliteId = eliteId;
		this.jobRole = jobRole;
		this.jobAdept = jobAdept;
	}
	
	// 技能中文名称
	@Transient
	public List<StringKeyValue> getJobAdeptValList() {
		List<String> list=CommonUtils.separatorStrToList(jobAdept, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		List<StringKeyValue> rel=new ArrayList<>();
		StringKeyValue skv=null;
		for(String s:list){
			if(StringUtils.isEmpty(s))continue;
			String value=DictCache.getParamDesc(Dict_Type.job_role.name(), s);
			skv=new StringKeyValue(s, value);
			rel.add(skv);
		}
		return rel;
	}
	
	// 技能中文名称
	@Transient
	public List<String> getJobAdeptValStrList() {
		List<String> list=CommonUtils.separatorStrToList(jobAdept, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		List<String> rel=new ArrayList<>();
		for(String s:list){
			if(StringUtils.isEmpty(s))continue;
			String value=DictCache.getParamDesc(Dict_Type.job_role.name(), s);
			rel.add(value);
		}
		return rel;
	}
	
    //职位角色名称
	public String getJobRoleVal(){
		return DictCache.getParamDesc(Dict.Dict_Type.job_role.name(), this.jobRole);
	}


	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "eliteId", insertable=false, updatable=false)
    private MemberElite elite;//精英

}
