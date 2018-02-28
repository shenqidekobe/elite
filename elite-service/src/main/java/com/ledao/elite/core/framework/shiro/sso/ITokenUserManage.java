package com.ledao.elite.core.framework.shiro.sso;

/**
 *
 * @Author:kobe.liu
 * @Version:1.0
 *
 */
public interface ITokenUserManage {
	
	/**
	 * 存储token
	 * */
	void persistenceUser(String token, SSOToken user);
	
	/**
	 * 根据token获取用户
	 * */
	SSOToken  getUser(String token);
	
	/**
	 * 移除token用户
	 * */
	void deleteUser(String token);
	
	/**
	 * 请求刷新token时间
	 * */
	void refreshLastRequest(String token);
	
	/**
	 * 验证token是否失效
	 * */
	Boolean isExpire(String token);
	
	/**
	 * 定时移除失效TOKEN
	 */
	void scheduleRemoveExpireToken();
	
	/**
	 * 在线token数量
	 * */
	Integer onlineSize();
}
