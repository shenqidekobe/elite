package com.ledao.elite.core.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ledao.elite.core.framework.constant.LogsEnum;

/**
 * 系统登录日志
 * 
 * @author kobe.liu
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLoginLog {

	//登录登出
	LogsEnum type() default LogsEnum.other;
	// 描述
	String description() default "";
}
