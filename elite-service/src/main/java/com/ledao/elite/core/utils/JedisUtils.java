package com.ledao.elite.core.utils;

import redis.clients.jedis.Jedis;

public class JedisUtils {

	private static Jedis jedis=null;
	static{
		 jedis = new Jedis("121.40.26.111", 6379);
		 jedis.auth("1qaz2wsx"); 
	}
	
	public static Object get(String key){
		return jedis.get(key);
	}

	public static void main(String[] args) {
		long st=System.currentTimeMillis();
		for(int i=0;i<9999999;i++){
			System.out.println(jedis.asking());
		}
		System.out.println("*****************耗时："+(System.currentTimeMillis()-st)/(1000)+"秒");
	}
}
