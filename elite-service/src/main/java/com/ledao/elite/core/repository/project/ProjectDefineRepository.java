package com.ledao.elite.core.repository.project;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.ProjectDefine;

/**
 * 项目立项书接口
 */
public interface ProjectDefineRepository extends GenericDAO<ProjectDefine, Long> {

	/**
	 * 根据ctoId查询定标书
	 */
	List<ProjectDefine> findProjectDefineListByCtoId(Long ctoId);

}
