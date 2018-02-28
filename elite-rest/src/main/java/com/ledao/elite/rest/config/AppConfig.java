package com.ledao.elite.rest.config;

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
	 * 私钥key
	 * */
	public String getPrivateKey(){
		return env.getProperty("rest_private_key");
	}
	
	/**
	 * 公钥key
	 * */
	public String getPublicKey(){
		String key= env.getProperty("rest_public_key");
		GlobalData.RSA_PUBLIC_KEY=key;
		return key;
	}

}
