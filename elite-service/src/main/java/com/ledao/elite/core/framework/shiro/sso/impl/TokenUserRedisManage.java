package com.ledao.elite.core.framework.shiro.sso.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.shiro.sso.ITokenUserManage;
import com.ledao.elite.core.framework.shiro.sso.SSOToken;

import lombok.extern.slf4j.Slf4j;

/**
 * redis缓存用户token实现
 * 
 * @Author:kobe.liu
 * @Version:1.0
 * 
 */
@Slf4j
@Component("tokenUserRedisManage")
public class TokenUserRedisManage implements ITokenUserManage {
	
	public static final String SSOTOKEN_REDIS_KEY="ssoToken_key";
	public static final String SSOTOKEN_REDIS_KEY_SIZE="ssoToken_key_size";
	
	@Resource
	private RedisTemplate<String, SSOToken> redisTemplate;

	@Override
	public void persistenceUser(String token, SSOToken user) {
		redisTemplate.opsForValue().set(token, user, SSOToken.expireTime, TimeUnit.MINUTES);
	}

	@Override
	public SSOToken getUser(String token) {
		if (token == null)
			return null;
		SSOToken obj=redisTemplate.opsForValue().get(token);
		return obj;
	}

	@Override
	public void deleteUser(String token) {
		redisTemplate.delete(token);
	}

	@Override
	public void refreshLastRequest(String token) {
		redisTemplate.expire(token,SSOToken.expireTime,TimeUnit.MINUTES);
	}

	@Override
	public Boolean isExpire(String token) {
		long expireTime=redisTemplate.getExpire(token, TimeUnit.MINUTES);
		log.debug("从redis服务器刷新token："+token+"的过期时间还有："+expireTime+"分钟");
		if (expireTime<=0) {
			deleteUser(token); // 失效清除token
			return true;
		}
		return false;
	}

	@Override
	public void scheduleRemoveExpireToken() {
	}
	
	@Override
	public Integer onlineSize(){
		return 0;
	}
	

}
