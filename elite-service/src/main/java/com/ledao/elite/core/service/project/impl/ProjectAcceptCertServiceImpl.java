package com.ledao.elite.core.service.project.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.project.ProjectAcceptCert;
import com.ledao.elite.core.domain.project.ProjectAcceptCert.AcceptCert_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.project.ProjectAcceptCertRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectAcceptCertService;

@Service("projectAcceptCertService")
public class ProjectAcceptCertServiceImpl extends BaseSerivceImpl implements ProjectAcceptCertService{
	
	@Resource
	private ProjectAcceptCertRepository projectAcceptCertRepository;

	@Override
	public ProjectAcceptCert createProjectAcceptCert(ProjectAcceptCert obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getProjectId());
		obj.setStatus(AcceptCert_Status.wait.name());
		this.projectAcceptCertRepository.save(obj);
		return obj;
	}

}
