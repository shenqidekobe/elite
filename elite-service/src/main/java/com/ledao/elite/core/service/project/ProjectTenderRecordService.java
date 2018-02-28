package com.ledao.elite.core.service.project;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.domain.project.ProjectTenderRecord.ProjectTenderRecord_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目竞标服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectTenderRecordService {
	
	/**
	 * 新增竞标记录
	 * 
	 * @param projectTenderRecord
	 * @param recordStages
	 * @return projectTenderRecord
	 * */
	ProjectTenderRecord createProjectTenderRecord(ProjectTenderRecord obj,String recordStages)throws EliteServiceException;

	
	/**
	 * 确定CTO的投标
	 * 
	 * @param id
	 * @param record
	 */
	void updateProjectTenderRecordById(Long id)throws EliteServiceException;
	
	/**
	 * 更新竞标记录
	 * 
	 * @param obj
	 * */
	void updateProjectTenderRecord(ProjectTenderRecord obj)throws EliteServiceException;
	
	/**
     * 根据ID查询
     * 
     * @param id
     */
	ProjectTenderRecord findProjectTenderRecordById(Long id)throws EliteServiceException;
	
	/**
     * 根据项目ID和会员ID查询
     * 
     * @param recordId
     * @param projectId
     * @param memberId
     * @return projectTenderRecord
     */
	ProjectTenderRecord findProjectTenderRecordByProject(Long tenderId,Long projectId,Long memberId)throws EliteServiceException;
	
	
	/**
	 * 根据projectId 查询
	 */
	List<ProjectTenderRecord>findProjectTenderRecordsByProjectId(Long projectId)throws EliteServiceException;
	
	/**
	 * 根据projectId 分页查询竞标记录
	 */
	SearchResult<ProjectTenderRecord> findProjectTenderRecordsByProjectId(Long projectId,Long tenderId,String status,Pager pager)throws EliteServiceException;

	/**
	 * 根据memberId 分页查询该CTO竞标的记录
	 */
	SearchResult<ProjectTenderRecord> findCtoProjectTender(Long memberId,Integer status,Pager pager)throws EliteServiceException;
	
	/**
	 * 根据memberId和status查询该CTO竞标的总数
	 */
	Integer findCtoProjectTenderInCount(Long memberId,Integer status)throws EliteServiceException;
	
	/**
	 * 根据项目ID和会员ID查询
	 * @param status
	 * @param projectId
	 * @param memberId
	 * @return
	 * @throws EliteServiceException
	 */
	ProjectTenderRecord findProjectTenderRecordByMemberId(Long projectId,Long memberId)throws EliteServiceException;
	
	/**
	 * 查找cto竞标成功的项目的竞标记录
	 * 
	 * @param projectId
	 * @param memberId
	 * */
	ProjectTenderRecord findProjectTenderRecord(Long projectId,Long memberId)throws EliteServiceException;
	
	/**
	 * 是否定标
	 * 
	 */
	Boolean findProjectTenderWin(Long tenderId,ProjectTenderRecord_Status status)throws EliteServiceException;
	
}
