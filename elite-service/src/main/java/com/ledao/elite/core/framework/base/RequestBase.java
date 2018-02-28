package com.ledao.elite.core.framework.base;

import java.io.Serializable;

import lombok.Data;


/**
 * 请求基类
 * */
@Data
public class RequestBase implements Serializable{

	private static final long serialVersionUID = 5578374817585124855L;

	private String ssoToken;//安全访问码
}
