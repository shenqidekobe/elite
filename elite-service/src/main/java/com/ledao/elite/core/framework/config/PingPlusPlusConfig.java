package com.ledao.elite.core.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 加载Ping++的配置信息
 * 
 * @author kobe.liu
 * */
@Component("pingPlusPlusConfig")
@Configuration
@PropertySource(value = {"classpath:/profile/pingplusplus.properties"})
public class PingPlusPlusConfig {
	
	@Autowired
	Environment env;
	
	/**
	 * 商户App ID
	 * */
	public String getAppId(){
		return env.getProperty("app_id");
	}
	
	/**
	 * 商户live key
	 * */
	public String getLiveSecretKey(){
		return env.getProperty("live_secret_key");
	}
	
	/**
	 * Ping++公钥key
	 * */
	public String getPingPublicKey(){
		return env.getProperty("ping_public_key");
	}
	
	/**
	 * Ping++私钥key
	 * */
	public String getPingPrivateKey(){
		return env.getProperty("ping_private_key");
	}
	
	/**
	 * Ping++回调的Webhooks通知地址
	 * */
	public String getPingResultUrl(){
		return env.getProperty("ping_result_url");
	}
	
	/**
	 * 身份证银行卡验证开关
	 */
	public boolean getValidCard() {
		boolean onoff = env.getProperty("valid_card_switch", Boolean.class);
		return onoff;
	}

}
