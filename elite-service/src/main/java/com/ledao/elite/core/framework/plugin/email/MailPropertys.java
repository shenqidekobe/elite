package com.ledao.elite.core.framework.plugin.email;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailPropertys implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String mailToAddress;
	
	private String mailFormAddress;
	
	private String mailToEncoding;

	
}
