package com.ledao.elite.core.utils.encry;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * base加解密工具
 * 
 * */
@SuppressWarnings("restriction")
@Slf4j
public class EncryptBase64 {

	
	/**
	 * 加密字符串
	 * @param value
	 * @return
	 */
	public static String encode(String value) {
		if(value != null) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < value.length(); i++) {
				String tmp = String.valueOf(value.charAt(i));
				if(tmp.matches("[0-9]")) {
					sb.append(9 - Integer.parseInt(tmp));
				} else {
					sb.append(value.charAt(i));
				}
			}
			return new BASE64Encoder().encode(sb.reverse().toString().getBytes());
		}
		return null;
	}

	/**
	 * 解密字符串
	 * @param value
	 * @return
	 */
	public static String decode(String value) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			value = new String((decoder.decodeBuffer(value)));
		} catch(IOException e) {
			log.error(e.getMessage());
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < value.length(); i++) {
			String tmp = String.valueOf(value.charAt(i));
			if(tmp.matches("[0-9]")) {
				sb.append(9 - Integer.parseInt(tmp));
			} else {
				sb.append(value.charAt(i));
			}
		}
		return sb.reverse().toString();
	}
	
	
	/**
	 * 加密字符串
	 * @param value
	 * @return
	 */
	public static String encodeSSOOA(String value) {
		if(value != null) {
			return new BASE64Encoder().encode(value.getBytes());
		}
		return null;
	}

	/**
	 * 解密字符串
	 * @param value
	 * @return
	 */
	public static String decodeSSOOA(String value) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			value = new String((decoder.decodeBuffer(value)));
		} catch(IOException e) {
			log.error(e.getMessage());
		}
		return value;
	}
	
	
	public static void main(String[] args) {
		String msg="爱你一万年";
		System.out.println(encode(msg));
		System.out.println(decode("5YiY54yb5a2Q5aSn5YK75a2Q"));
	}
}
