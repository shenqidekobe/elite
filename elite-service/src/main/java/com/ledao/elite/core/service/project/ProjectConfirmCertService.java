package com.ledao.elite.core.service.project;

import com.ledao.elite.core.domain.project.ProjectConfirmCert;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 项目相关确认单服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectConfirmCertService {

	/**
	 * 新建确认单
	 * 
	 * @param projectConfirmCert
	 * @return projectConfirmCert
	 * */
	ProjectConfirmCert createProjectConfirmCert(ProjectConfirmCert obj)throws EliteServiceException;
}
