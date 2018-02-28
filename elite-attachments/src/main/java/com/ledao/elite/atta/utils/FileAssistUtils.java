package com.ledao.elite.atta.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.ledao.elite.core.domain.sys.Attas;

/**
 * 文件辅助工具类型
 * */
public class FileAssistUtils {

	/**
	 * 获取需要存储文件的根目录
	 * */
	public static String getPath(HttpServletRequest request, String append) {
		String pathString = request.getSession().getServletContext().getRealPath("/") + Attas.UPLOAD_ROOT_PATH
				+ File.separator + append;
		return pathString;
	}

	/**
	 * 获取服务器地址
	 * */
	public static String getServerUrl(HttpServletRequest req, String append) {
		String pathString = null;
		StringBuffer url = new StringBuffer();
		String scheme = req.getScheme();
		int port = req.getServerPort();
		url.append(scheme);
		url.append("://");
		url.append(req.getServerName());
		if ((scheme.equals("http") && port != 80) || (scheme.equals("https") && port != 443)) {
			url.append(':');
			url.append(req.getServerPort());
		}
		url.append(req.getContextPath());
		url.append(append);
		pathString = url.toString();
		return pathString;
	}
}
