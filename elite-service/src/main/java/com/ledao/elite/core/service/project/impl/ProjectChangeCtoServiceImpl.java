package com.ledao.elite.core.service.project.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.project.ProjectChangeCto;
import com.ledao.elite.core.domain.project.ProjectChangeCto.ChangeCto_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.project.ProjectChangeCtoRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectChangeCtoService;

@Service
public class ProjectChangeCtoServiceImpl extends BaseSerivceImpl implements ProjectChangeCtoService{
	
	
	@Resource
	private ProjectChangeCtoRepository projectChangeCtoRepository;

	@Override
	public ProjectChangeCto createProjectChangeCto(ProjectChangeCto obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getProjectId(),obj.getNewCtoId());
		obj.setStatus(ChangeCto_Status.wait_process);
		this.projectChangeCtoRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectChangeCto updateProjectChangeCtoStatus(ProjectChangeCto obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId(),obj.getStatus(),obj.getProcessId());
		ProjectChangeCto pojo=this.findProjectChangeCtoById(obj.getId());
		pojo.setStatus(obj.getStatus());
		pojo.setProcessId(obj.getProcessId());
		pojo.setProcessTime(new Date());
		//TODO:审核发送消息通知cto
		if(obj.getStatus().equals(ChangeCto_Status.agree)){
			
		}
		return pojo;
	}

	@Override
	public ProjectChangeCto findProjectChangeCtoById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectChangeCtoRepository.find(id);
	}

}
