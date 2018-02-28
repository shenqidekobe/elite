package com.ledao.elite.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;

/**
 * 工具类，提供常用的方法
 */
public class CommonUtils {

	/**
	 * 创建一个随机的字符串，基于UUID
	 */
	public static String createNonceStr() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 创建当前时间戳的字符串
	 */
	public static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 生成一个基于大小写数据的随机字符串
	 * 
	 * @param length
	 * 
	 * @return String
	 */
	public static String randomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		base = "0123456789";
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(base.length());
			buf.append(base.charAt(num));
		}
		return buf.toString();
	}

	/**
	 * 解析request inputStream成字符串
	 */
	public static String readStreamParameter(InputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			return new String(buffer.toString().getBytes(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * xml字符串转换为map
	 */
	public static Map<String, String> parseXml(String xmlStr) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlStr);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for (Element ele : list) {
				map.put(ele.getName(), ele.getText().trim());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 请求参数转换成 map<string,string>
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> requestParameterToMap(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// valueStr = strEnCoding(valueStr);
			params.put(name, valueStr);
		}
		return params;
	}

	/**
	 * 字符串编码格式化｛iso->gbk｝
	 */
	public static String strEnCoding(String str) {
		String result = str;
		try {
			result = new String(str.getBytes(GlobalDefinedConstant.Encoding_Type.iso.val),
					GlobalDefinedConstant.Encoding_Type.utf8.val);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 字符串采用分隔符转换成list
	 */
	public static List<String> separatorStrToList(String str, String separator) {
		if (StringUtils.isEmpty(str))
			return new ArrayList<>();
		String[] arrs = str.split(separator);
		return Arrays.asList(arrs);
	}

	/**
	 * 验证时间是否过期，在expireTime时间内(分钟)
	 * 
	 * @return true:已过期/false:未过期
	 */
	public static boolean validTimeIsExpire(Date validTime, Integer expireTime) {
		if (validTime == null)
			return true;
		Long time = validTime.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -expireTime);// 过期时间设置
		Long ct = calendar.getTime().getTime();
		if (ct <= time) {
			return false;
		}
		return true;
	}

	/**
	 * 文件大小转为为字符串
	 */
	public static String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else
			return String.format("%d B", size);
	}

	/**
	 * 验证手机号是否合法
	 */
	public static boolean checkPhone(String phone) {
		String phoneRegex = "^[1][3,4,5,7,8][0-9]{9}$";
		return (null != phone) && Pattern.compile(phoneRegex).matcher(phone).matches();
	}

	/**
	 * 验证邮箱是否合法
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 生成随机颜色
	 */
	public static String getRandColorCode() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;

		return r + g + b;
	}

	/**
	 * 计算金额的税费,规则如下： A<=800 =0 800<A<=4000 =(A-800) *20% 4000<A<=20000 =A*0.8*20%
	 * 20000<A<=50000 =A*0.8*30% A>50000 =A*0.8*40%
	 */
	public static BigDecimal calculateAmountTax(BigDecimal amount) {
		BigDecimal zero = new BigDecimal(0);
		if (amount == null)
			return zero;
		BigDecimal factor = new BigDecimal("0.8");// 因数
		BigDecimal taxRate1 = new BigDecimal("0.2");// 税率1
		BigDecimal taxRate2 = new BigDecimal("0.3");// 税率2
		BigDecimal taxRate3 = new BigDecimal("0.4");// 税率3

		BigDecimal level1 = new BigDecimal(800);
		BigDecimal level2 = new BigDecimal(4000);
		BigDecimal level3 = new BigDecimal(20000);
		BigDecimal level4 = new BigDecimal(50000);
		if (amount.compareTo(level1) == -1 || amount.compareTo(level1) == 0) {
			return zero;
		} else if (amount.compareTo(level1) == 1 && (amount.compareTo(level2) == -1 || amount.compareTo(level2) == 0)) {
			BigDecimal result = amount.subtract(level1);
			return result.multiply(taxRate1);
		} else if (amount.compareTo(level2) == 1 && (amount.compareTo(level3) == -1 || amount.compareTo(level3) == 0)) {
			return amount.multiply(factor).multiply(taxRate1);
		} else if (amount.compareTo(level3) == 1 && (amount.compareTo(level4) == -1 || amount.compareTo(level4) == 0)) {
			return amount.multiply(factor).multiply(taxRate2);
		} else if (amount.compareTo(level4) == 1) {
			return amount.multiply(factor).multiply(taxRate3);
		}
		return zero;
	}

	/**
	 * 去除字符中的html标记
	 * 
	 * @param htmlStr
	 */
	public static String delHTMLTag(String htmlStr) {
		if(StringUtils.isEmpty(htmlStr)){return "";}
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	public static void main(String[] args) {
		//System.out.println(convertFileSize(5240000));
		System.out.println(calculateAmountTax(new BigDecimal(6800)));
	}

}
