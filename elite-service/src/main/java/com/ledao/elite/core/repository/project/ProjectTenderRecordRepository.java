package com.ledao.elite.core.repository.project;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目阶段性需求的招标记录接口
 * */
public interface ProjectTenderRecordRepository extends GenericDAO<ProjectTenderRecord, Long>{

	
	/**
	 * 查询招标记录
	 * @param projectId
	 * @param pager
	 * @return
	 */
	SearchResult<ProjectTenderRecord> findProjectTenderRecordsByProjectId(Long projectId, Pager pager);
	
	/**
	 * 查询该CTO竟标的项目
	 * @param memberId
	 * @param status
	 * @param pager
	 * @return
	 */
	SearchResult<ProjectTenderRecord> findCtoProjectTender(Long memberId,Integer status,Pager pager);
	
	/**
	 * 查询招标记录 根据projectId tenderId status
	 * @param projectId
	 * @param pager
	 * @return
	 */
	SearchResult<ProjectTenderRecord> findProjectTenderRecordsByProjectIdAndTender(Long projectId,Long tenderId,String status,Pager pager);
	
}
