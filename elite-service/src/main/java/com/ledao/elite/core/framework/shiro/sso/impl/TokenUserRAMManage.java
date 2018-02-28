package com.ledao.elite.core.framework.shiro.sso.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.shiro.sso.ITokenUserManage;
import com.ledao.elite.core.framework.shiro.sso.SSOToken;

/**
 * 内存缓存用户token实现
 * 
 * @Author:kobe.liu
 * @Version:1.0
 * 
 */
@Component("tokenUserRAMManage")
public class TokenUserRAMManage implements ITokenUserManage {

	private static Map<String, SSOToken> coreUserContainer=new ConcurrentHashMap<>();
	private static Map<String, Long> coreUserLastRefreshContainer=new ConcurrentHashMap<>();
	
	@Override
	public void persistenceUser(String token, SSOToken user) {
		coreUserContainer.put(token, user);
		refreshLastRequest(token);
	}

	@Override
	public SSOToken getUser(String token) {
		if (token == null)
			return null;
		return coreUserContainer.get(token);
	}

	@Override
	public void deleteUser(String token) {
		coreUserContainer.remove(token);
		coreUserLastRefreshContainer.remove(token);
	}

	@Override
	public void refreshLastRequest(String token) {
		coreUserLastRefreshContainer.put(token, System.currentTimeMillis());
	}

	@Override
	public Boolean isExpire(String token) {
		try {
			long lastRefreshTime = coreUserLastRefreshContainer.get(token);
			long expireTimes = SSOToken.expireTime * 60 * 1000;
			if ((System.currentTimeMillis() - lastRefreshTime) > expireTimes) {
				deleteUser(token); // 失效清除token
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 每隔半小时清理一次用户token
	 * */
	@Scheduled(cron="0 0/30 * * * ?")
	@Override
	public void scheduleRemoveExpireToken() {
		Iterator<Entry<String, Long>> it = coreUserLastRefreshContainer.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Long> entry = it.next();
			long lastRefreshTime = entry.getValue();
			long expireTimes = SSOToken.expireTime * 60 * 1000;
			if ((System.currentTimeMillis() - lastRefreshTime) > expireTimes) {
				it.remove(); // OK
				coreUserContainer.remove(entry.getKey());
			}
		}
	}
	
	@Override
	public Integer onlineSize(){
		return coreUserContainer.size();
	}
	
	

}
