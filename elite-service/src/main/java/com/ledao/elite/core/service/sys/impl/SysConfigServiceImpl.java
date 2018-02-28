package com.ledao.elite.core.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.sys.SysConfig;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.sys.SysConfigRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysConfigService;

@Service
public class SysConfigServiceImpl extends BaseSerivceImpl implements SysConfigService {

	@Resource
	private SysConfigRepository sysConfigRepository;

	@Override
	public SysConfig createSysConfig(SysConfig obj) throws EliteServiceException {

		this.verifyParams(obj.getConfigKey(), obj.getVersion());
		this.sysConfigRepository.save(obj);
		return obj;
	}

	@Override
	public SysConfig findSysConfigByKey(String configKey) throws EliteServiceException {
		this.verifyParams(configKey);
		return this.sysConfigRepository.searchUnique(new Search().addFilterEqual("configKey", configKey));
	}

	@Override
	public SysConfig removePhysicalById(Long Id) throws EliteServiceException {
		this.verifyParams(Id);
		SysConfig obj = this.sysConfigRepository.find(Id);
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return this.sysConfigRepository.remove(obj) ? obj : null;
	}

	@Override
	public SysConfig removePhysicalByKey(String configKey) throws EliteServiceException {
		this.verifyParams(configKey);
		SysConfig obj = this.sysConfigRepository.searchUnique(new Search().addFilterEqual("configKey", configKey));
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return this.sysConfigRepository.remove(obj) ? obj : null;
	}

	@Override
	public List<SysConfig> queryAll() throws EliteServiceException {

		return this.sysConfigRepository.findAll();
	}

}
