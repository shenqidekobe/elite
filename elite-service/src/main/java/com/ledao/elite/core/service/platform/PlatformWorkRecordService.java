package com.ledao.elite.core.service.platform;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目的工作记录服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface PlatformWorkRecordService {

	/**
	 * 新建一条工作记录
	 * 
	 * @param PlatformWorkRecord
	 * @return PlatformWorkRecord
	 */
	PlatformWorkRecord createPlatformWorkRecord(PlatformWorkRecord obj) throws EliteServiceException;

	/**
	 * 更新工作记录
	 * 
	 * @param PlatformWorkRecord
	 * @return PlatformWorkRecord
	 */
	PlatformWorkRecord updatePlatformWorkRecord(PlatformWorkRecord obj) throws EliteServiceException;

	/**
	 * 按ID查询
	 * 
	 * @param id
	 * @return PlatformWorkRecord
	 */
	PlatformWorkRecord findPlatformWorkRecordById(Long id) throws EliteServiceException;

	/**
	 * 按ID删除
	 * 
	 * @param id
	 */
	void removePlatformWorkRecordById(Long id) throws EliteServiceException;

	/**
	 * 分页查询查询工作记录列表
	 * 
	 * @param type
	 * @param foreignId
	 * @param userId
	 * @param pager
	 */
	SearchResult<PlatformWorkRecord> findPlatformWorkRecordList(String type, Long foreignId, Long userId, Pager pager)
			throws EliteServiceException;

	/**
	 * 查询记录
	 */
	List<PlatformWorkRecord> findPlatFromWorkRecords(String type, Long foreignId);
}
