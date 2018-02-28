package com.ledao.elite.core.framework.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * string key-value
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StringKeyValue implements Serializable {

	private static final long serialVersionUID = 6204950415287962323L;

	private String key;
	private String value;
	

}
