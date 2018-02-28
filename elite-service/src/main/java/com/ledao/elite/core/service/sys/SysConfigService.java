package com.ledao.elite.core.service.sys;

import java.util.List;

import com.ledao.elite.core.domain.sys.SysConfig;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 系统常用配置接口
 *
 * @author zhiyu cao
 **/

public interface SysConfigService {

	/**
	 * 创建系统常用配置
	 *
	 **/
	SysConfig createSysConfig(SysConfig obj) throws EliteServiceException;

	/**
	 * 根据Key查询
	 * 
	 */
	SysConfig findSysConfigByKey(String configKey) throws EliteServiceException;

	/**
	 * 按ID物理删除
	 * 
	 */
	SysConfig removePhysicalById(Long Id) throws EliteServiceException;

	/**
	 * 根据key删除
	 * 
	 */
	SysConfig removePhysicalByKey(String configKey) throws EliteServiceException;

	/**
	 * 查询全部
	 * 
	 */
	List<SysConfig> queryAll() throws EliteServiceException;
}
