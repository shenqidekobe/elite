package com.ledao.elite.core.domain.platform;

import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 平台已认证银行卡号
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class PlatformAuthBank extends IdentifiedEntity {

	private static final long serialVersionUID = 6628042455821711983L;
	
	private String name;//姓名
	private String idCard;//身份证
	private String bankCard;//银行卡号
	private String phone;//手机号

}
