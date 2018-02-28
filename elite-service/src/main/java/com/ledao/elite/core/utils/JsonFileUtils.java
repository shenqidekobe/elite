package com.ledao.elite.core.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.dto.NoticeJsonBean;

public class JsonFileUtils {

	public static  String FilePath=DictCache.getNoticeFileValue("noticeFilePath");
	public static String FileName=DictCache.getNoticeFileValue("noticeFileName");

	// json写入文件
	public synchronized static void write2File(NoticeJsonBean bean, String fileName) {
		BufferedWriter writer = null;
		File filePath = new File(FilePath);
		List<Object> eJSONlist = null;

		if (!filePath.exists() && !filePath.isDirectory()) {
			filePath.mkdirs();
		}
		File file = new File(FilePath + File.separator + fileName + ".xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			eJSONlist = (List<Object>) read2JSON(fileName);
		}

		try {
			writer = new BufferedWriter(new FileWriter(file));
			if (eJSONlist == null) {
				eJSONlist = new ArrayList<Object>();
			}
			eJSONlist.add(bean);
			writer.write(JSON.toJSONString(eJSONlist));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// json写入文件
	public synchronized static void saveFile(List<Object> eJSONlist, String fileName) {
		BufferedWriter writer = null;
		File filePath = new File(FilePath);
		if (!filePath.exists() && !filePath.isDirectory()) {
			filePath.mkdirs();
		}

		File file = new File(FilePath + File.separator + fileName + ".xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(JSON.toJSONString(eJSONlist));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 读文件到json
	public static List<Object> read2JSON(String fileName) {
		File file = new File(FilePath + File.separator + fileName + ".xml");
		if (!file.exists()) {
			return null;
		}

		BufferedReader reader = null;
		String laststr = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Object> list = JSON.parseArray(laststr);
		return list;
	}

	// 通过key值获取文件中的value
	public static Object getValue(String fileName, String key) {
		JSONObject eJSON = null;
		eJSON = (JSONObject) read2JSON(fileName);
		if (null != eJSON && eJSON.containsKey(key)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> values = JSON.parseObject(eJSON.toString(), Map.class);
			return values.get(key);
		} else {
			return null;
		}
	}


	// 添加通知
	public static void addNotice(String projectId, String noticeType) {
		if (!JsonFileUtils.selectNoticeByProjectAndNoticeType(projectId, noticeType)) {
			NoticeJsonBean bean = new NoticeJsonBean();
			bean.setProjectId(projectId);
			bean.setNoticeType(noticeType);
			JsonFileUtils.write2File(bean, FileName);
		}
	}

	// 删除通知
	public static void removeNotice(String projectId, String noticeType) {
		List<Object> list = JsonFileUtils.read2JSON(FileName);
		boolean change = false;
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = (JSONObject) list.get(i);
			if (null != obj && obj.containsKey("noticeType")) {
				@SuppressWarnings("unchecked")
				Map<String, Object> values = JSON.parseObject(obj.toString(), Map.class);
				String selProject = (String) values.get("projectId");
				String selnoticeType = (String) values.get("noticeType");
				if (projectId.equals(selProject) && noticeType.equals(selnoticeType)) {
					list.remove(i);
					change = true;
					break;
				}
			}
		}
		if (change) {
			JsonFileUtils.saveFile(list, FileName);
		}
	}

	// 查找通知是否存在
	public static boolean selectNoticeByProjectAndNoticeType(String projectId, String noticeType) {
		List<Object> list = JsonFileUtils.read2JSON(FileName);
		boolean result = false;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = (JSONObject) list.get(i);
				if (null != obj && obj.containsKey("noticeType")) {
					@SuppressWarnings("unchecked")
					Map<String, Object> values = JSON.parseObject(obj.toString(), Map.class);
					String selProject = (String) values.get("projectId");
					String selnoticeType = (String) values.get("noticeType");
					if (projectId.equals(selProject) && noticeType.equals(selnoticeType)) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	// 根据项目id查询
	public static List<NoticeJsonBean> selectNoticeByProject(String projectId) {
		List<Object> list = JsonFileUtils.read2JSON(FileName);
		List<NoticeJsonBean> resultlist = new ArrayList<NoticeJsonBean>();
		if(list!=null){
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = (JSONObject) list.get(i);
			if (null != obj && obj.containsKey("noticeType")) {
				@SuppressWarnings("unchecked")
				Map<String, Object> values = JSON.parseObject(obj.toString(), Map.class);
				String selProject = (String) values.get("projectId");
				if (projectId.equals(selProject)) {
					String selnoticeType = (String) values.get("noticeType");
					NoticeJsonBean bean = new NoticeJsonBean();
					bean.setProjectId(projectId);
					bean.setNoticeType(selnoticeType);
					resultlist.add(bean);
				}
			}
		}
		}
		return resultlist;
	}

}
