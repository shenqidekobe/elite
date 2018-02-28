package com.ledao.elite.core.framework.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 加载本地资源配置文件
 * 
 * @author kobe.liu
 */
@Component("templateConfig")
@Configuration
@PropertySource(value = {"classpath:/profile/smsTemplate.properties"})
public class TemplateConfig {

	@Resource
	Environment env;
	
	/**
	 * 注册短信模版
	 * */
	public String getRegisterSmsTemplate(){
		return env.getProperty("sms_register_template");
	}
	
	/**
	 * 找回密码短信模版
	 * */
	public String getForgetPassSmsTemplate(){
		return env.getProperty("sms_forgetpass_template");
	}
	
	
}
