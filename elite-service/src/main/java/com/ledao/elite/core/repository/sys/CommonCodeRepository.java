package com.ledao.elite.core.repository.sys;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.CommonCode;

/**
 * 系统公用流水号生成记录接口
 * 
 * @author kobe.liu
 * */
public interface CommonCodeRepository extends GenericDAO<CommonCode, Long> {
	
	/**
	 * 获取满足obj条件的列表
	 * 通过hibernate悲观锁机制同步
	 * 
	 * @param obj
	 * @return List<CommonCode>
	 * */
	List<CommonCode> queryCommonCodeList(CommonCode obj);
	
	/**
	 * 更新公用编码
	 * */
	void updateCommonCode(CommonCode obj);

}
