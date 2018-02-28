package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.cache.custom.DictCache;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员教育和培训经历对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberEducation extends IdentifiedEntity {

	private static final long serialVersionUID = -3542953663277308218L;
	
	//教育or培训
	public enum MemberEducation_Type{
		edu,train
	}
	private Long memberId;//会员ID
	@Column(length=32)
	private String type;//教育或培训
	@Column(length=32)
	private String organ;//机构
	@Column(length=32)
	private String major;//专业
	@Column(length=32)
	private String education;//学历
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;//开始时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;//结束时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date graduateTime;//毕业时间
	private int orders;//排序号
	
	public String getEducationVal(){
		return DictCache.getParamDesc(Dict_Type.education_type.name(), education);
	}
}
