package com.ledao.elite.core.repository.project;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.ProjectAtta;

/**
 * 项目附件接口
 * 
 * @author kobe.liu
 * */
public interface ProjectAttaRepository extends GenericDAO<ProjectAtta, Long> {
	
	void deleteProjectAtta(Long projectId);

}
