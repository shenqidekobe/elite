package com.ledao.elite.core.framework.cache.custom;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 短信发送频率缓存记录
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class SmsSendCache implements InitializingBean,DisposableBean {

	private static long sendInterval=20*1000;// 发送间隔时间{单位:毫秒}

	private static Map<String, Long> sendMap = new ConcurrentHashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
	
	@Override
	public void destroy() throws Exception {
		sendMap.clear();
	} 

	/**
	 * 验证发送的间隔时间
	 * 频率过高直接返回false
	 * */
	public static boolean valid(String id) {
		if (setSendTime(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 将发送时间修改为当前时间. 如果距离上次发送的时间间隔大于{@link #sendInterval}则设置发送时间为当前时间.
	 * 否则不修改任何内容.
	 *
	 * @param id
	 *            发送手机号 或 ip
	 * @return 如果成功将发送时间修改为当前时间, 则返回true. 否则返回false
	 */
	private static boolean setSendTime(String id) {
		long currentTime = System.currentTimeMillis();

		Long sendTime = sendMap.putIfAbsent(id, currentTime);
		if (sendTime == null) {
			return true;
		}
		long nextCanSendTime = sendTime + sendInterval;
		if (currentTime < nextCanSendTime) {
			return false;
		}
		return sendMap.replace(id, sendTime, currentTime);
	}

	private long cleanMapInterval=60*60*1000;//间隔时间执行清空过期key的map
	private Timer timer = new Timer("sms_cache_filter_clear_data_thread");
	public void init() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				cleanSendAddressMap();
			}
		}, cleanMapInterval, cleanMapInterval);
	}

	/**
	 * 将sendMap中的所有过期数据删除
	 */
	private void cleanSendAddressMap() {
		long currentTime = System.currentTimeMillis();
		long expireSendTime = currentTime - sendInterval;

		for (String key : sendMap.keySet()) {
			Long sendTime = sendMap.get(key);
			if (sendTime < expireSendTime) {
				sendMap.remove(key, sendTime);
			}
		}
	}

}
