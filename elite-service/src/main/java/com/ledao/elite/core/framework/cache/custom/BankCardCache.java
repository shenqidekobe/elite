package com.ledao.elite.core.framework.cache.custom;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 银行卡验证接口频率缓存记录
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class BankCardCache implements InitializingBean,DisposableBean{
	
	private static final Integer MAX_COUNT=5;

	private static Map<Long, Integer> sendMap = new ConcurrentHashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
	
	@Override
	public void destroy() throws Exception {
		sendMap.clear();
	}  


	/**
	 * 验证次数是否超过最大限制数量
	 * 频率过高直接返回false
	 * */
	public static boolean valid(Long memberId) {
		Integer count = sendMap.get(memberId);
		count=count==null?0:count;
		sendMap.put(memberId, count+1);
		if (count>MAX_COUNT) {
			return false;
		}
		return true;
	}


	private long cleanMapInterval=24*60*60*1000;//间隔时间执行清空过期key的map
	private Timer timer = new Timer("idcard_cache_filter_clear_data_thread");
	public void init() {
		Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, 2); //凌晨2点  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 0);  
        Date date=calendar.getTime(); //第一次执行定时任务的时间  
        //如果第一次执行定时任务的时间 小于当前的时间  
        //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。  
        if (date.before(new Date())) {  
            date = this.addDay(date, 1);  
        }  
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				cleanSendAddressMap();
			}
		}, date, cleanMapInterval);//安排指定的任务在指定的时间开始进行重复的固定延迟执行。  
	}

	/**
	 * 将sendMap中的所有过期数据删除
	 */
	private void cleanSendAddressMap() {
		for (Long key : sendMap.keySet()) {
			Integer count = sendMap.get(key);
			count=count==null?0:count;
			if (count>MAX_COUNT) {
				sendMap.remove(key);
			}
		}
	}
	
	  // 增加或减少天数  
    public Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    }

}
