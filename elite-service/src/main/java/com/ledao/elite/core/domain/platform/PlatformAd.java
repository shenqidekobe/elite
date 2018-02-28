package com.ledao.elite.core.domain.platform;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台广告位对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformAd extends IdentifiedEntity {

	private static final long serialVersionUID = 6628042455821711983L;
	
	@Column(length=64)
	private String adTitle;//广告名称
	private Long adImg;//广告图片
	@Column(length=256)
	private String adHref;//广告链接
	private int orders;//排序号
	@Column(length=64)
	private String adPosition;//广告位置
	@Column(length=32)
	private String status;//状态
	private Long createId;//创建者
	private Long updateId;//修改者

}
