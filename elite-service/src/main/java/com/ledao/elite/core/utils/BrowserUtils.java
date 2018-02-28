package com.ledao.elite.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public abstract class BrowserUtils {

	/**
	 * 获取客户端类型
	 * */
	public static String getClientBrowser(HttpServletRequest request){
		try {
			String e = getClienttAgent(request);
			boolean android = e.contains("Android") || e.contains("Linux");
			boolean iPhone = e.contains("iPhone") || e.contains("Mac");
			boolean iPad = e.contains("iPad");
			boolean winPhone = e.contains("Windows Phone") || e.contains("NOKIA");
			if( android || iPhone || winPhone) {
				return "手机";
			}else if(iPad){
				return "平板";
			}
		} catch (Exception e) {
		}
		
		return "电脑";
	}
	
	/**
	 * 获取浏览器信息
	 * */
	public static String getClienttAgent(HttpServletRequest request){
		return request.getHeader("User-Agent");
	}
	
	/**
	 * 获取手机型号
	 * */
	public static String getMobileVersion(HttpServletRequest request){
		try {
			String userAgent=getClienttAgent(request);
			for (int i = 0;i < mobileUserAgents.length; i++) {
				if(userAgent.contains(mobileUserAgents[i])){
					return mobileUserAgents[i];
				}
			}
		} catch (Exception e) {
		}
	
		return "";
	}
	
	/**
	 * 获取操作系统
	 * */
	public static String getOS(HttpServletRequest request){
		try {
			String userAgent=getClienttAgent(request);
			for (int i = 0;i < pcHeaders.length; i++) {
				if(userAgent.contains(pcHeaders[i])){
					return pcHeaders[i];
				}
			}
		} catch (Exception e) {
		}
		
		return "";
	}
	
	public enum BrowserType {
		IE10, IE9, IE8, IE7, IE6, Firefox, Safari, Chrome, Opera, Camino, Gecko,Other
	}

	/** 判断是否是IE */
	public static boolean isIE(HttpServletRequest request) {
		return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 ? true
				: false;
	}

	/**
	 * 获取IE版本
	 * 
	 * @param request
	 * @return
	 */
	public static Double getIEversion(HttpServletRequest request) {
		Double version = 0.0;
		if (getBrowserType(request, "msie 10.0")) {
			version = 10.0;
		}
		if (getBrowserType(request, "msie 9.0")) {
			version = 9.0;
		}
		if (getBrowserType(request, "msie 8.0")) {
			version = 8.0;
		}
		if (getBrowserType(request, "msie 7.0")) {
			version = 7.0;
		}
		if (getBrowserType(request, "msie 6.0")) {
			version = 6.0;
		}
		return version;
	}

	/**
	 * 获取浏览器类型
	 * 
	 * @param request
	 * @return
	 */
	public static BrowserType getBrowserType(HttpServletRequest request) {
		BrowserType browserType = null;
		if (getBrowserType(request, "msie 10.0")) {
			browserType = BrowserType.IE9;
		}
		if (getBrowserType(request, "msie 9.0")) {
			browserType = BrowserType.IE9;
		}
		if (getBrowserType(request, "msie 8.0")) {
			browserType = BrowserType.IE8;
		}
		if (getBrowserType(request, "msie 7.0")) {
			browserType = BrowserType.IE7;
		}
		if (getBrowserType(request, "msie 6.0")) {
			browserType = BrowserType.IE6;
		}
		if (getBrowserType(request, "Firefox")) {
			browserType = BrowserType.Firefox;
		}
		if (getBrowserType(request, "Safari")) {
			browserType = BrowserType.Safari;
		}
		if (getBrowserType(request, "Chrome")) {
			browserType = BrowserType.Chrome;
		}
		if (getBrowserType(request, "Opera")) {
			browserType = BrowserType.Opera;
		}
		if (getBrowserType(request, "Camino")) {
			browserType = BrowserType.Camino;
		}
		return browserType;
	}

	private static boolean getBrowserType(HttpServletRequest request,
			String brosertype) {
		return request.getHeader("USER-AGENT").toLowerCase()
				.indexOf(brosertype) > 0 ? true : false;
	}

	private final static String IE10 = "MSIE 10.0";
	private final static String IE9 = "MSIE 9.0";
	private final static String IE8 = "MSIE 8.0";
	private final static String IE7 = "MSIE 7.0";
	private final static String IE6 = "MSIE 6.0";
	private final static String MAXTHON = "Maxthon";
	private final static String QQ = "QQBrowser";
	private final static String GREEN = "GreenBrowser";
	private final static String SE360 = "360SE";
	private final static String FIREFOX = "Firefox";
	private final static String OPERA = "Opera";
	private final static String CHROME = "Chrome";
	private final static String SAFARI = "Safari";
	private final static String OTHER = "其它";

	public static String checkBrowse(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT");
		if (regex(OPERA, userAgent))
			return OPERA;
		if (regex(CHROME, userAgent))
			return CHROME;
		if (regex(FIREFOX, userAgent))
			return FIREFOX;
		if (regex(SAFARI, userAgent))
			return SAFARI;
		if (regex(SE360, userAgent))
			return SE360;
		if (regex(GREEN, userAgent))
			return GREEN;
		if (regex(QQ, userAgent))
			return QQ;
		if (regex(MAXTHON, userAgent))
			return MAXTHON;
		if (regex(IE10, userAgent))
			return IE10;
		if (regex(IE9, userAgent))
			return IE9;
		if (regex(IE8, userAgent))
			return IE8;
		if (regex(IE7, userAgent))
			return IE7;
		if (regex(IE6, userAgent))
			return IE6;
		return OTHER;
	}

	public static boolean regex(String regex, String str) {
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	/**电脑上的IE或Firefox浏览器等的User-Agent关键词*/
	public static String[] pcHeaders=new String[]{"Windows 98","Windows ME","Windows 2000","Windows XP","Windows NT","Ubuntu"};
	
	/**手机浏览器的User-Agent里的关键词*/
	public static String[] mobileUserAgents=new String[]{"Nokia","SAMSUNG","MIDP-2","CLDC1.1","SymbianOS","MAUI","UNTRUSTED/1.0","Windows CE",
		"BlackBerry","UCWEB","ucweb","BREW","J2ME","YULONG","YuLong","COOLPAD","TIANYU","TY-","K-Touch","Haier","DOPOD","Lenovo",
		"LENOVO","HUAQIN","AIGO-","CTC/1.0","CTC/2.0","CMCC","DAXIAN","MOT-","SonyEricsson","GIONEE","HTC","ZTE","HUAWEI",
		"webOS","GoBrowser","IEMobile","WAP2.0","iPhone","iPad","Android",
	};

}
