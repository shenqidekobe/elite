package com.ledao.elite.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 日志打印工具
 * */
public class LogHelper {

	public static String titleOutput(String title) {
		int maxLen = 80;
		List<String> output = new ArrayList<String>();
		output.add("\n" + StringUtils.repeat("-", maxLen));
		output.add("\n" + StringUtils.center(title, maxLen));
		output.add("\n" + StringUtils.repeat("-", maxLen));

		return StringUtils.join(output, "");
	}
}
