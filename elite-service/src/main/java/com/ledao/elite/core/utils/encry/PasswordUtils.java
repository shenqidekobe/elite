package com.ledao.elite.core.utils.encry;

import org.apache.commons.lang3.StringUtils;

/**
 * 密码加密工具
 * */
public class PasswordUtils {
	
	public static final int SALT_SIZE = 8;
	
	public static String getAccountSalt() {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		return Encodes.encodeHex(salt);
	}

	public static String getMD5PassWord(String pwd) {
		return getMD5PassWord(pwd, null);
	}

	public static String getMD5PassWord(String pwd, String salt) {
		byte[] md5Password;
		if (StringUtils.isNotEmpty(salt)) {
			md5Password = Digests.md5(pwd.getBytes(), Encodes.decodeHex(salt));
		} else {
			md5Password = Digests.md5(pwd.getBytes());
		}
		return Encodes.encodeHex(md5Password);
	}
	
	public static void main(String[] args) {
		String salts="8905353238e1518d";
		String password="e10adc3949ba59abbe56e057f20f883e";
		String pwd=Md5.getMD5(password);
		
		byte[] salt = Encodes.decodeHex(salts);
		byte[] psalt = pwd.getBytes();
		String inpwd = Encodes.encodeHex(Digests.md5(psalt, salt));
		System.out.println("salts = "+salts+",pwd = "+inpwd);
		
		String o=PasswordUtils.getMD5PassWord("e10adc3949ba59abbe56e057f20f883e", "8905353238e1518d");
		System.out.println(o);
		
	}


}
