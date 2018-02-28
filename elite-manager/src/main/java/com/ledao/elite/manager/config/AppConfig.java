package com.ledao.elite.manager.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 加载项目资源配置文件
 * 
 * @author kobe.liu
 */

@Component
@Configuration
@PropertySource(value = {"classpath:/profile/app.properties"})
public class AppConfig {

	@Resource
	Environment env;
	
	/**
	 * 确认提现后的时间，单位：分钟
	 * */
	public Integer getIntervalMinute(){
		return env.getProperty("withdraw_affirm_interval",Integer.class);
	}
	
}
