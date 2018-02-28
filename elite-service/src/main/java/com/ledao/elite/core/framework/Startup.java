package com.ledao.elite.core.framework;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.shiro.sso.SSOToken;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.sys.DictService;

import lombok.extern.slf4j.Slf4j;

/**
 * 框架启动入口
 * */
@Slf4j
@Component
public class Startup {

	@Resource
	private RedisTemplate<String, SSOToken> redisTemplate;
	
	@Resource
	private DictService dictService;
	
	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;
	
	@PostConstruct
	@Async
	public void startup() {
		log.info("com.ledao.elite.core.framework.Startup 启动************************");
		Set<String> allSet=redisTemplate.keys("*");
		allSet=allSet!=null?allSet:new HashSet<>();
		log.info("redis服务器总共有:"+allSet.size()+"个key*******************************");
		
	}
	
}
