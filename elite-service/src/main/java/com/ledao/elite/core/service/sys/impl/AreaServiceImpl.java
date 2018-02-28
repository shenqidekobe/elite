package com.ledao.elite.core.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.sys.Area;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.sys.AreaRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.AreaService;

@Service
public class AreaServiceImpl extends BaseSerivceImpl implements AreaService{
	
	@Resource
	private AreaRepository areaRepository;

	@Override
	public Area findById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.areaRepository.find(id);
	}

	@Override
	public List<Area> findRootAreaList() throws EliteServiceException {
		return this.areaRepository.queryRootAreaList();
	}
	
	@Override
	public List<Area> findAll()throws EliteServiceException{
		return this.areaRepository.findAll();
	}

}
