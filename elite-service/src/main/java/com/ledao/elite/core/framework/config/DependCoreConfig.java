package com.ledao.elite.core.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 加载外部依赖资源的核心配置文件
 * 
 * @author kobe.liu
 * */
@Component("dependCoreConfig")
@Configuration
@PropertySource(value = {"classpath:/profile/dependRes.properties"})
public class DependCoreConfig {
	
	@Autowired
	Environment env;
	
	/**
	 * 获取启动定时任务的标识
	 * */
	public boolean getScheduleTaskFlag(){
		boolean flag=env.getProperty("schedule_task_start_flag", Boolean.class);
		return flag;
	}

}
