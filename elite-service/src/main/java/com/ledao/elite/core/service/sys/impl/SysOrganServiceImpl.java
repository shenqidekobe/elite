package com.ledao.elite.core.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysOrgan;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.repository.sys.SysOrganRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysOrganService;

@Service
public class SysOrganServiceImpl extends BaseSerivceImpl implements SysOrganService {

	@Resource
	private SysOrganRepository sysOrganRepository;

	@Override
	public SysOrgan create(SysOrgan obj) throws EliteServiceException {
		this.verifyParams(obj);
	    int order=this.sysOrganRepository.querySysOrganMaxOrders();
		obj.setStatus(GlobalDefinedConstant.System_Status.normal.name());
		obj.setOrders(order);
		this.sysOrganRepository.save(obj);
		return obj;
	}

	@Override
	public SearchResult<SysOrgan> findSysOrganList(String name, String status, Long parentId, Long areaId, Pager pager)
			throws EliteServiceException {

		return this.sysOrganRepository.fuzzySearchSysOrgans(name, status, parentId, areaId, pager);
	}

	@Override
	public SysOrgan removeLogicById(Long id) throws EliteServiceException {

		this.verifyParams(id);
		SysOrgan obj = this.sysOrganRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("单位信息不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setStatus(GlobalDefinedConstant.System_Status.deleted.name());
		return this.sysOrganRepository.save(obj) ? obj : null;
	}

	@Override
	public SysOrgan findSysOrganById(Long organId) throws EliteServiceException {
		this.verifyParams(organId);
		return this.sysOrganRepository.find(organId);
	}

	@Override
	public SysOrgan updateSysOrgan(long id, SysOrgan sysOrgan) throws EliteServiceException {
		this.verifyParams(sysOrgan);
		SysOrgan obj = this.sysOrganRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("单位信息不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setName(sysOrgan.getName());
		obj.setIntro(sysOrgan.getIntro());
		this.sysOrganRepository.save(obj);
		return obj;
	}

	@Override
	public List<SysOrgan> findSysOrganAll() throws EliteServiceException {
		Search search = new Search();
		search.addFilterEqual("status", GlobalDefinedConstant.System_Status.normal.name());
		search.addSort("orders", false);
		return this.sysOrganRepository.search(search);
	}

	@Override
	public SysOrgan findSysOrganByIdAndName(Long id, String name) throws EliteServiceException {

		Search search = new Search();
		search.addFilterEqual("id", id);
		search.addFilterEqual("name", name);
		return this.sysOrganRepository.searchUnique(search);
	}

}
