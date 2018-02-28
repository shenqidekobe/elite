package com.ledao.elite.core.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.sys.SysRoleMenu;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.sys.SysRoleMenuRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysRoleMenuService;

/**
 *
 *
 * @author zhiyu cao
 **/
@Service
public class SysRoleMenuServiceImpl extends BaseSerivceImpl implements SysRoleMenuService {

	@Resource
	private SysRoleMenuRepository sysRoleMenuRepository;

	@Override
	public SysRoleMenu create(Long roleId,Long menuId) throws EliteServiceException {
		this.verifyParams(roleId,menuId);
		SysRoleMenu srm=new SysRoleMenu();
		srm.setRoleId(roleId);
		srm.setMenuId(menuId);
		this.sysRoleMenuRepository.save(srm);
		return srm;
	}


	@Override
	public void removeById(Long roleId) throws EliteServiceException {
		this.verifyParams(roleId);
		this.sysRoleMenuRepository.deleteSysRoleMenu(roleId);
	}

	@Override
	public List<SysRoleMenu> findAll() throws EliteServiceException {
		
		return this.sysRoleMenuRepository.findAll();
	}

}
