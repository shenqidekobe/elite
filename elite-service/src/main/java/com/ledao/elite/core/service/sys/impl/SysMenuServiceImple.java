package com.ledao.elite.core.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.sys.SysMenu;
import com.ledao.elite.core.domain.sys.SysRole;
import com.ledao.elite.core.domain.sys.SysRoleMenu;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.repository.sys.SysMenuRepository;
import com.ledao.elite.core.repository.sys.SysRoleRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysMenuService;

/**
 *
 *
 * @author zhiyu cao
 **/
@Service
public class SysMenuServiceImple extends BaseSerivceImpl implements SysMenuService {

	@Resource
	private SysMenuRepository sysMenuRepository;
	
	@Resource
	private SysRoleRepository sysRoleRepository;


	@Override
	public SysMenu findById(Long Id) throws EliteServiceException {
		this.verifyParams(Id);
		return this.sysMenuRepository.find(Id);
	}


	@Override
	public List<SysMenu> findSysMenuByType(String type) throws EliteServiceException {
		Search search=new Search();
		if(StringUtils.isNotEmpty(type)){
			search.addFilterEqual("type", type);
		}
		search.addFilterEqual("status", GlobalDefinedConstant.System_Status.normal.name());
		search.addSort("orders",false);//正序
		return this.sysMenuRepository.search(search);
	}


	@Override
	public List<SysMenu> findSysMenuByRoleId(Long roleId) throws EliteServiceException {
		SysRole role=this.sysRoleRepository.find(roleId);
		List<SysMenu>  rolePowers = new ArrayList<>();
		List<SysRoleMenu> roleMenu = role.getMenus();
		for (SysRoleMenu rm : roleMenu) {
			SysMenu menu = rm.getSysMenu();
			rolePowers.add(menu);
		}
		return rolePowers;
	}


	@Override
	public List<SysMenu> findSysUserRootMenus(Long userId) throws EliteServiceException {
		this.verifyParams(userId);
		List<SysMenu> list=this.sysMenuRepository.queryUserRootMenu(userId);
		for(SysMenu menu:list){
			List<SysMenu> haveChilds=this.sysMenuRepository.queryUserChildMenu(userId, menu.getId());
			menu.setHaveChilds(haveChilds);
		}
		return list;
	}
	
	@Override
	public List<SysMenu> findSysUserAllMenus(Long userId)throws EliteServiceException{
		this.verifyParams(userId);
		return this.sysMenuRepository.queryUserAllMenu(userId);
	}


}
