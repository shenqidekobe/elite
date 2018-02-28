package com.ledao.elite.core.service.project;

import com.ledao.elite.core.domain.project.ProjectPause;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 项目服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectPauseService {
	
	
	/**
	 * 申请延期
	 * 
	 * @param projectPause
	 * @return projectPause
	 * */
	ProjectPause createProjectPause(ProjectPause obj)throws EliteServiceException;
	
	/**
	 * 更新延期申请状态
	 * 
	 * @param projectPause
	 * @return projectPause
	 * */
	ProjectPause updateProjectPauseStatus(ProjectPause obj)throws EliteServiceException;
	
	/**
	 * 按ID查询
	 * 
	 * @param id
	 * @return projectPause
	 * */
	ProjectPause findProjectPauseById(Long id)throws EliteServiceException;

}
