package com.ledao.elite.core.service.project.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectTaskAttention;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.project.ProjectTaskAttentionRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectTaskAttentionService;

@Service
public class ProjectTaskAttentionServiceImpl extends BaseSerivceImpl implements ProjectTaskAttentionService{
	
	@Resource
	private ProjectTaskAttentionRepository projectTaskAttentionRepository;
	
	
	@Override
	public SearchResult<ProjectTaskAttention> findAttenTionProjectTasks(Long memberId, Pager pager)
			throws EliteServiceException {
		this.verifyParams(memberId);
		
		Search search = new Search(ProjectTaskAttention.class);
		search.addFilterEqual("memberId", memberId);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return projectTaskAttentionRepository.searchAndCount(search);
	}
		
	
	
}
