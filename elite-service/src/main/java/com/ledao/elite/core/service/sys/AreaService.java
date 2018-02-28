package com.ledao.elite.core.service.sys;

import java.util.List;

import com.ledao.elite.core.domain.sys.Area;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 地区查询接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface AreaService {

	/**
	 * 按ID查询区域
	 * 
	 * @param id
	 * @return area
	 * */
	Area findById(Long id)throws EliteServiceException;

	/**
	 * 查询地区树结构
	 * */
	List<Area> findRootAreaList()throws EliteServiceException;
	
	/**
	 * 查询所有的地区
	 * */
	List<Area> findAll()throws EliteServiceException;
}
