package com.ledao.elite.core.domain.platform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台新闻内容对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformNews extends IdentifiedEntity {

	private static final long serialVersionUID = -7846617526136222217L;

	@Column(length=128)
	private String title;//标题
	private Long pic;//头图
	@Column(length=256)
	private String intro;//简介
	@Lob
	private String content;//正文
	@Column(length=32)
	private String status;//状态
	private Long createId;//创建者
	private Long updateId;//修改者
}
