package com.ledao.elite.site.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 联想数据
 * @author Chenghao
 *
 */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberMap {
		
	private String label;
	private String account;
	private String name;
	private Long value;
	private String desc;
}
