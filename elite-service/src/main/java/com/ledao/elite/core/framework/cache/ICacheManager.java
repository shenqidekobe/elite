package com.ledao.elite.core.framework.cache;

import java.util.concurrent.TimeUnit;

/**
 * cacheManager interface
 * */
public interface ICacheManager {
	
	public Object get(Object key);
	
	public void set(Object key,Object value);
	
	public void set(Object key,Object value,TimeUnit unit,long expireTime);

}
