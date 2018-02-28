package com.ledao.elite.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 会员标签对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberTags extends IdentifiedEntity {

	private static final long serialVersionUID = 8392174041852117519L;
	
	private Long memberId;//会员ID
	@Column(length=16)
	private String tagType;//标签类型
	@Column(length=16)
	private String tagValue;//标签值
	private int orders;//排序号

}
