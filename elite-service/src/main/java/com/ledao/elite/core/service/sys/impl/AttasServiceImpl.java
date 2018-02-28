package com.ledao.elite.core.service.sys.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.repository.sys.AttasRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.AttasService;

@Service
public class AttasServiceImpl extends BaseSerivceImpl implements AttasService{
	
	@Resource
	private AttasRepository attasRepository;

	@Override
	public Attas createAttas(Attas obj) throws EliteServiceException {
		this.verifyParams(obj);
		obj.setStatus(GlobalDefinedConstant.System_Status.normal.name());
		return this.attasRepository.save(obj)?obj:null;
	}

	@Override
	public Attas removeLogicById(Long attaId) throws EliteServiceException {
		this.verifyParams(attaId);
		Attas obj=this.attasRepository.find(attaId);
		if(obj==null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setStatus(GlobalDefinedConstant.System_Status.deleted.name());
		obj.setRemoveTime(new Date());
		return this.attasRepository.save(obj)?obj:null;
	}

	@Override
	public Attas removePhysicalById(Long attaId) throws EliteServiceException {
		this.verifyParams(attaId);
		Attas obj=attasRepository.find(attaId);
		if(obj==null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return this.attasRepository.remove(obj)?obj:null;
	}

	@Override
	public Attas updateAttas(Long attaId, Attas attas) throws EliteServiceException {
		this.verifyParams(attaId,attas);
		Attas obj=attasRepository.find(attaId);
		if(obj==null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		BeanUtils.copyProperties(attas, obj);
		return this.attasRepository.save(obj)?obj:null;
	}

	@Override
	public Attas findById(Long attaId) throws EliteServiceException {
		this.verifyParams(attaId);
		return this.attasRepository.find(attaId);
	}

	@Override
	public List<Attas> findByIds(String attaIds) throws EliteServiceException {
		this.verifyParams(attaIds);
		return null;
	}

}
