package com.ledao.elite.core.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysRole;
import com.ledao.elite.core.domain.sys.SysUserRole;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.sys.SysRoleRepository;
import com.ledao.elite.core.repository.sys.SysUserRoleRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysRoleMenuService;
import com.ledao.elite.core.service.sys.SysRoleService;

/**
 *
 *
 * @author zhiyu cao
 **/
@Service
public class SysRoleServiceImpl extends BaseSerivceImpl implements SysRoleService {

	@Resource
	private SysRoleRepository sysRoleRepository;

	@Resource
	private SysUserRoleRepository sysUserRoleRepository;

	@Resource
	private SysRoleMenuService sysRoleMenuService;

	@Override
	public SysRole createSysRole(SysRole obj) throws EliteServiceException {
		this.verifyParams(obj);
		this.sysRoleRepository.save(obj);
		return obj;
	}

	@Override
	public void createSysRoleMenu(Long roleId, String menuIds) throws EliteServiceException {
		this.verifyParams(roleId, menuIds);
		String[] menuArray = StringUtils.split(menuIds, ",");
		this.sysRoleMenuService.removeById(roleId);
		for (String menus : menuArray) {
			Long menuId = Long.parseLong(menus);
			this.sysRoleMenuService.create(roleId, menuId);
		}
	}

	@Override
	public SysRole removePhysicalById(Long Id) throws EliteServiceException {

		this.verifyParams(Id);
		SysRole obj = this.sysRoleRepository.find(Id);
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return this.sysRoleRepository.remove(obj) ? obj : null;
	}

	@Override
	public SysRole findSysRoleById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.sysRoleRepository.find(id);
	}

	@Override
	public List<SysRole> findAll() throws EliteServiceException {

		return this.sysRoleRepository.findAll();
	}

	@Override
	public SearchResult<SysRole> findSysRoleList(String status, Pager pager) throws EliteServiceException {

		if (pager == null)
			pager = new Pager();
		Search search = new Search(SysRole.class);
		search.addFilterEqual("status", status);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.sysRoleRepository.searchAndCount(search);
	}

	@Override
	public SysRole findSysRoleByIdAndName(Long id, String name) throws EliteServiceException {

		Search search = new Search();
		search.addFilterEqual("id", id);
		search.addFilterEqual("name", name);
		return this.sysRoleRepository.searchUnique(search);
	}

	@Override
	public SysRole updateSysRole(Long id, SysRole role) throws EliteServiceException {

		this.verifyParams(role);
		SysRole obj = this.sysRoleRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("角色信息不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		// 待更新内容
		obj.setName(role.getName());
		obj.setIntro(role.getIntro());

		return this.sysRoleRepository.save(obj) ? obj : null;
	}

	@Override
	public SysRole removeSysRoleById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		SysRole obj = this.sysRoleRepository.find(id);
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		List<SysUserRole> userRolelist = this.sysUserRoleRepository.search(new Search().addFilterEqual("roleId", id));
		if (userRolelist.isEmpty()) {
			return this.sysRoleRepository.remove(obj) ? obj : null;
		} else {
			throw new EliteServiceException("该角色正在被使用,无法删除", ErrorCodeEnum.OBJECT_USE.code);
		}

	}

}
