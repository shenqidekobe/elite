package com.ledao.elite.core.domain.platform;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台访问码
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformVisitCode extends IdentifiedEntity{

	private static final long serialVersionUID = -307838251701833479L;
	
	@Column(length=32)
	private String visitName;//访问姓名
	@Column(length=15)
	private String visitPhone;//访问电话号码
	@Column(length=32)
	private String visitCode;//访问代码
	@Type(type="yes_no")
	private boolean used=false;//是否已访问
	private Integer visitCount=0;//访问次数
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastVisitTime;//最后访问事件
	
	private Long memberId;//会员帐号ID
}
