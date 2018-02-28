package com.ledao.elite.core.framework.cache.custom;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.RandomStringUtils;

import com.ledao.elite.core.utils.CommonUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 命令生成器缓存
 * 
 * @author kobe.liu
 * */
@Slf4j
public class CommandCache {
	
	private static Map<String, Command> commandMap = new ConcurrentHashMap<>();
	
	private static final Integer expireTime=5;
	private static final Integer maxCount=1;
	
	/**
	 * 生成
	 * */
	public static Command create(String key){
		Command obj=new Command();
		String code=RandomStringUtils.randomAlphabetic(10);
		obj.setCount(0);
		obj.setCode(code);
		obj.setCreateTime(new Date());
		commandMap.putIfAbsent(key, obj);
		log.info("++++++++++++生成相应的对象："+key+"的指令为："+code);
		return obj;
	}
	
	/**
	 * 比较
	 * */
	public static boolean compare(String key,String code){
		Command obj=commandMap.get(key);
		if(obj==null||obj.getCode().isEmpty())return false;
		if(obj.getCode().equals(code)){
			//超过使用次数
			if(obj.getCount()>=maxCount)return false;
			//已过期
			if(CommonUtils.validTimeIsExpire(obj.getCreateTime(), expireTime)){
				commandMap.remove(key);
				return false;
			}
			commandMap.remove(key);
			return true;
		}
		return false;
	}
	
	@Data
	static class Command{
		private String code;
		private Integer count;
		private Date createTime;
	}
	
	public static void main(String[] args) {
		System.out.println(RandomStringUtils.randomAlphabetic(10));
		System.out.println(RandomStringUtils.randomAlphanumeric(10));
		System.out.println(RandomStringUtils.randomNumeric(10));
		System.out.println(RandomStringUtils.randomAscii(10));
		System.out.println(RandomStringUtils.random(10));
	}

}
