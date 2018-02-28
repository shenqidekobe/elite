package com.ledao.elite.core.repository.project;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.ProjectTender;

/**
 * 项目招标书、招标整个项目接口
 * */
public interface ProjectTenderRepository extends GenericDAO<ProjectTender, Long>{

	/**
	 * 按id查询
	 * */
	ProjectTender queryProjectTenderById(Long id);
}
