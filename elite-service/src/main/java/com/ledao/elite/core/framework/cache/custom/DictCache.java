package com.ledao.elite.core.framework.cache.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.sys.Area;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.service.sys.AreaService;
import com.ledao.elite.core.service.sys.DictService;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据字典缓存
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Slf4j
@Component
public class DictCache implements InitializingBean,DisposableBean{
	
	static Map<String, Map<String, String>> param = new HashMap<String, Map<String, String>>();
	static Map<String, Long> areaMap = new HashMap<String, Long>();
	static Map<String, String> noticeFileSet = new HashMap<String, String>();
	
	@Resource
	private DictService dictService;
	
	@Resource
	private AreaService areaService;

	@Resource
	private LocalCoreConfig localCoreConfig;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("******************************数据字典内存缓存开始初始化******************************");
		init();
		log.info("******************************数据字典内存缓存初始化完毕******************************");
	}
	
	@Override
	public void destroy() throws Exception {
		param.clear();
		areaMap.clear();
		noticeFileSet.clear();
	} 
	
	public void init(){
		//加载各个类型的数据
		for(Dict.Dict_Type dt:Dict.Dict_Type.values()){
			List<Dict> list=dictService.findDictListByDictType(dt.name());
			Map<String, String> valmap=new HashMap<>();
			for(Dict d:list){
				valmap.put(d.getDictKey(), d.getDictVal());
			}
			param.put(dt.name(), valmap);
		}
		//加载地区数据
		List<Area> areaList=this.areaService.findAll();
		for(Area area:areaList){
			//ps:由于前端城市没有后缀市字，故这里需去掉市字
			String name=area.getName();
			if(name.endsWith("市")){
				name=StringUtils.chop(name);
			}
			areaMap.put(name,area.getId());
		}
		//初始化配置信息
		this.localCoreConfig.getUploadServer();
		this.localCoreConfig.getSmsSendSwitch();
		noticeFileSet.put("noticeFilePath", this.localCoreConfig.getNoticeFilePath());
		noticeFileSet.put("noticeFileName", this.localCoreConfig.getNoticeFileName());
	}
	
	
	/**
	 * 通过参数代码获得参数字典map
	 * @param dictType 数据类型
	 * @return
	 */
	public static Map<String, String> getParamMap(String dictType){
		return param.get(dictType);
	}
	
	/**
	 * 通过参数代码,参数key获得参数值
	 * @param dictType 数据类型
	 * @return
	 */
	public static String getParamDesc(String dictType,String key){
		Map<String,String> map = DictCache.getParamMap(dictType);
		String val = "";
		if(null != map){
			val = map.get(key);
		}
		return val;
	}
	
	/**
	 * 根据地区名称获取地区ID
	 * 
	 * @param areaName
	 * @return areaId
	 * */
	public static Long getAreaMap(String areaName){
		return areaMap.get(areaName);
	}
    public static  String getNoticeFileValue(String noticeType){
    	return noticeFileSet.get(noticeType);
    }
}
