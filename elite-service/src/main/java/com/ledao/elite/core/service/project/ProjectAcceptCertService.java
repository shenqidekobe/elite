package com.ledao.elite.core.service.project;

import com.ledao.elite.core.domain.project.ProjectAcceptCert;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 项目相关的验收单服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectAcceptCertService {
	
	/**
	 * 创建新的验收单
	 * 
	 * @param projectAcceptCert
	 * @return projectAcceptCert
	 * */
	ProjectAcceptCert createProjectAcceptCert(ProjectAcceptCert obj)throws EliteServiceException;

}
