package com.ledao.elite.core.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ledao.elite.core.framework.constant.StatisticsEnum;


/**
 * 数量统计注解
 * */
@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
public @interface CountStatistics {

	//统计的类型
	StatisticsEnum type();
	
	//统计的参数
	String[] param();
}
