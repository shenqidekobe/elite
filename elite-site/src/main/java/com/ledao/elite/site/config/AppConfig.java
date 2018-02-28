package com.ledao.elite.site.config;

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
	
}
