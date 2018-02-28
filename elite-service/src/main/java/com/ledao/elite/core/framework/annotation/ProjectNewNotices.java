package com.ledao.elite.core.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ledao.elite.core.framework.constant.NoticeTypeEnum;

/**
 * 新通知（小红点）操作日志
 * 
 * @author kobe.liu
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProjectNewNotices {

	// 通知类型
	NoticeTypeEnum noticeType();

	// 操作类型
	String controllerType();
	
}
