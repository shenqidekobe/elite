package com.ledao.elite.site.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ledao.elite.core.framework.config.LocalCoreConfig;

/**
 * 系统初始化
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app.xml")
public class StartUp {
	
	@Resource
	private LocalCoreConfig localCoreConfig;
	
	
	
	@Test
	public void test(){
		System.out.println(localCoreConfig.getSmsAccount());
	}

}
