package com.ledao.elite.core.repository.project;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.ProjectWeekly;

/**
 * 项目周报接口
 * 分为CTO给客户的周报和精英提交给CTO的周报、每周的周报至少一个
 * */
public interface ProjectWeeklyRepository extends GenericDAO<ProjectWeekly, Long>{
	
	/**
	 * 删除周报	
	 * @param id
	 * @return
	 */
	int deleteWeeklyById(Long id);
}
