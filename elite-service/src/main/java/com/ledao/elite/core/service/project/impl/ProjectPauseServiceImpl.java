package com.ledao.elite.core.service.project.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.project.ProjectChangeCto.ChangeCto_Status;
import com.ledao.elite.core.domain.project.ProjectPause;
import com.ledao.elite.core.domain.project.ProjectPause.Pause_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.project.ProjectPauseRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectPauseService;

@Service
public class ProjectPauseServiceImpl extends BaseSerivceImpl implements ProjectPauseService{
	
	@Resource
	private ProjectPauseRepository projectPauseRepository;

	@Override
	public ProjectPause createProjectPause(ProjectPause obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getProjectId());
		obj.setStatus(Pause_Status.wait_process);
		this.projectPauseRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectPause updateProjectPauseStatus(ProjectPause obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId(),obj.getStatus());
		ProjectPause pojo=this.findProjectPauseById(obj.getId());
		pojo.setStatus(obj.getStatus());
		this.projectPauseRepository.save(pojo);
		//TODO:审核发送消息通知cto和项目方
		if(obj.getStatus().equals(ChangeCto_Status.agree)){
			
		}
		return pojo;
	}

	@Override
	public ProjectPause findProjectPauseById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectPauseRepository.find(id);
	}

}
