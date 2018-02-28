package com.ledao.elite.core.service.project;

import com.ledao.elite.core.domain.project.ProjectChangeCto;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 项目负责CTO更换服务接口
 * */
public interface ProjectChangeCtoService {
	
	
	/**
	 * 申请更换cto
	 * 
	 * @param projectChangeCto
	 * @return projectChangeCto
	 * */
	ProjectChangeCto createProjectChangeCto(ProjectChangeCto obj)throws EliteServiceException;
	
	/**
	 * 更新申请变更CTO的状态
	 * 
	 * @param projectChangeCto
	 * @return projectChangeCto
	 * */
	ProjectChangeCto updateProjectChangeCtoStatus(ProjectChangeCto obj)throws EliteServiceException;
	
	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return projectChangeCto
	 * */
	ProjectChangeCto findProjectChangeCtoById(Long id)throws EliteServiceException;

}
