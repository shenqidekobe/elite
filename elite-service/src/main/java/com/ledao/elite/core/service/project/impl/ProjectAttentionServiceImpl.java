package com.ledao.elite.core.service.project.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectAttention;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.project.ProjectAttentionRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectAttentionService;

@Service
public class ProjectAttentionServiceImpl  extends BaseSerivceImpl implements ProjectAttentionService{
	
	@Resource
	private ProjectAttentionRepository projectAttentionRepository;
	
	@Override
	public SearchResult<ProjectAttention> findAttenTionProjects(Long memberId, Pager pager) throws EliteServiceException{
		this.verifyParams(memberId);
		Search search = new Search(ProjectAttention.class);
		search.addFilterEqual("memberId", memberId);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return projectAttentionRepository.searchAndCount(search);
	}
	
	
	
}
