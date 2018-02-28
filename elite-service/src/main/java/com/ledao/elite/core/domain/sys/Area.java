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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 区域对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class Area extends IdentifiedEntity{
	
	private static final long serialVersionUID = -469348445419640719L;
	
	public static final Long CHNAL_PARENT_ID=0L;
	
	@NotNull
	@Column(length=32)
	private String name;//名称
	@Column(length=2)
	private String initiaLetter;//首字母
	private Long parentId;//上级ID
	@Column(length=16)
	private String zipCode;//邮政编码
	@Type(type = "yes_no")
	private boolean isHot=false;//是否热门城市
	private Integer level;//级别
	
	
	/************************hibernate many one config**************************/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parentId", insertable = false, updatable = false)
	private Area parent;
	
	@JsonIgnore
	@OneToMany(mappedBy = "parent",fetch=FetchType.LAZY)
	@OrderBy("id ASC")
	private List<Area> childs = new ArrayList<>();

	
}
