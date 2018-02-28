package com.ledao.elite.core.repository.sys;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.Area;

/**
 * 地区接口
 * 
 * @author kobe.liu
 * */
public interface AreaRepository extends GenericDAO<Area, Long> {
	
	/**
	 * 检索顶级地区
	 * 
	 * @return List<Area>
	 * */
	List<Area> queryRootAreaList();

}
