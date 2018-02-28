package com.ledao.elite.core.framework.cache.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.cache.ICacheManager;

/**
 * redis cache manager impl
 * 
 * @datetime 2016-11-01
 * */
@Component
public class CacheRedisManager implements ICacheManager{
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public Object get(Object key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(Object key, Object value) {
		redisTemplate.opsForValue().set(key.toString(), value);
	}

	@Override
	public void set(Object key, Object value, TimeUnit unit, long expireTime) {
		redisTemplate.opsForValue().set(key.toString(), value, expireTime, unit);
	}

}
