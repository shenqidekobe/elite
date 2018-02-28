package com.ledao.elite.core.service.project;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectAttention;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员关注项目接口
 * @author Chenghao
 *
 */
public interface ProjectAttentionService {
	
	
	SearchResult<ProjectAttention> findAttenTionProjects(Long memberId,Pager pager)throws EliteServiceException;
}
