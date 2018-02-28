package com.ledao.elite.core.service.project.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.project.ProjectConfirmCert;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.project.ProjectConfirmCertRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectConfirmCertService;

@Service
public class ProjectConfirmCertServiceImpl extends BaseSerivceImpl implements ProjectConfirmCertService{
	
	@Resource
	private ProjectConfirmCertRepository projectConfirmCertRepository;

	@Override
	public ProjectConfirmCert createProjectConfirmCert(ProjectConfirmCert obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getProjectId());
		projectConfirmCertRepository.save(obj);
		return obj;
	}

}
