package com.ledao.elite.core.framework.shiro.sso;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

import com.ledao.elite.core.utils.encry.EncryptBase64;

import lombok.extern.slf4j.Slf4j;

/**
 * token生产工厂,采用BASE64加密解密   
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Slf4j
public class TokenGenerateFactory {
	
	
    private static final long DEFAULT_START_SECOND = 1262275200;
	
    /**
     * 解密token，生成对应的map
     * */
	public static Map<String,String> getMap(String token){
		StringBuffer buffer = new StringBuffer();
    	token = new String(Base64.decodeBase64(token.getBytes()));
    	char[] chr = token.toCharArray();
    	for (char c : chr) {
    		if(!Character.isDigit(c)){
    			buffer.append(c);
				continue;
			}
			int ins = 9-Integer.parseInt(String.valueOf(c));
			buffer.append(ins);
		}
    	token = buffer.reverse().toString();
    	log.debug(token);
    	String[] tokens = token.split("\\.");
    	long time = Long.parseLong(tokens[0]);
    	String account = tokens[3];
    	String channel = tokens[1];
    	Map<String,String> mapToken = new HashMap<String, String>();
    	mapToken.put("time", String.valueOf((time+DEFAULT_START_SECOND)*1000));
    	mapToken.put("account", account);
    	mapToken.put("channel", channel);
    	return mapToken;
	}
	
	/**
	 * 按照map参数、进行加密生成token(时间戳生成)
	 * 
	 * @param channel:渠道{PC/iOS/And}
	 * @param account:帐号{admin_kb}
	 * 
	 * @return token
	 * */
	public static String genToken(Map<String,String> userMap){
		StringBuilder sb = new StringBuilder();
		long time = System.currentTimeMillis() / 1000 - DEFAULT_START_SECOND;
		sb.append(time).append(".").append(userMap.get("channel")).append(".").append(new Random().nextInt(9999)).append(".").append(userMap.get("account"));
		String token = EncryptBase64.encode(sb.toString()).replaceAll("\r\n", "");
		return token;
	}
         
    public static void main(String[] args) throws Exception  {    
//    	例子：Njk3NS4uLjA4LDg4LDQsNTgsNSw2OCw3OCwxLDk3LDcsOCwzOC4wNDEuMzAwNDMxMDk4
//    	1.base64 解码后得到6975...08,88,4,58,5,68,78,1,97,7,8,38.041.300431098　它们以.号分(不是逗号)为一组
//    	2.得到每一组中字符串，如第一组6975　　第二组08,88,4,58,5,68,78,1,97,7,8,38
//    	3.从第一次开始，每天每个字符取与９的差值后再组成一个新的字符串，如6975这4个数字分别取与9的差值为3024,最后的新串为3024...91,11,5,41,4,31,21,8,02,2,1,61.958.699568901.
//    	4.把得到的新串反向取值，得到109865996.859.16,1,2,20,8,12,13,4,14,5,11,19...4203
//    	5.以.号分割成数组，第一组为登录时间戳，第二个是userid,第三个是用户等级userlevel，第三个是区id
    	//---117035246.1132.26.SH..8723.lrlz1132
    	/*Map<String,String> userMap = new HashMap<String, String>();
    	userMap.put("channel", "PC");
    	userMap.put("user_name", "lrlz1132");
    	String token = genToken(userMap);
    	System.out.println(token);
    	Map<String,String> map = getTokens(token);
    	long cur =  System.currentTimeMillis();
    	long time = cur/ 1000 - DEFAULT_START_SECOND;
    	long time2 = (time+DEFAULT_START_SECOND)*1000;
    	System.out.println(cur+"----"+time+"==="+time2);*/
    	
    	System.out.println(getMap("OTE3MTI3Mjg2NDguNTcxNi5TT2kuMTI0MDMxNDg3").toString());
    	
    }     

}
