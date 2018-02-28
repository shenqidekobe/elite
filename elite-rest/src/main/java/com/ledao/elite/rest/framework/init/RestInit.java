package com.ledao.elite.rest.framework.init;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.ledao.elite.rest.config.AppConfig;

/**
 * rest初始化
 * */
@Component
public class RestInit implements InitializingBean {

	@Resource
	private AppConfig appConfig;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		appConfig.getPublicKey();
	}

}
