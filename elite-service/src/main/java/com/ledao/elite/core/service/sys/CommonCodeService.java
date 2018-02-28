package com.ledao.elite.core.service.sys;


/**
 * 系统公共编码生成接口
 * 
 * @author kobe.liu
 * */
public interface CommonCodeService {
	
	/**
	 * 编码生成,自带前缀
	 * @param func   功能点
	 * @param preStr  前缀
	 * @param patten 时间格式
	 * @param glideLen 流水长度
	 * @param sperator 分隔符
	 * 
	 * @return code
	 */
	public String disposeOddNumber(String func,String preStr,String patten,Integer glideLen,String sperator);

}
