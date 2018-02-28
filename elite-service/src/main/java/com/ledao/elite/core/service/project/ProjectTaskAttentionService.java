package com.ledao.elite.core.service.project;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectTaskAttention;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员关注任务接口
 * @author Chenghao
 *
 */
public interface ProjectTaskAttentionService {
	
	/**
	 * 分页查询我关注的任务
	 * @param memberId
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<ProjectTaskAttention> findAttenTionProjectTasks(Long memberId,Pager pager)throws EliteServiceException;
}
