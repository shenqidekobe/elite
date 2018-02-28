package com.ledao.elite.core.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.sys.SysDept;
import com.ledao.elite.core.domain.sys.SysOrgan;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.dto.manager.OrganDeptTree;
import com.ledao.elite.core.repository.sys.SysDeptRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysDeptService;
import com.ledao.elite.core.service.sys.SysOrganService;
import com.ledao.elite.core.service.sys.SysUserService;

/**
 * @author zhiyu cao
 **/
@Service
public class SysDeptServiceImpl extends BaseSerivceImpl implements SysDeptService {

	@Resource
	private SysDeptRepository sysDeptRepository;
	
	@Resource
	private SysOrganService sysOrganService;
	
	@Resource
	private SysUserService sysUserService;

	@Override
	public SysDept create(SysDept obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getName(),obj.getOrganId());
		obj.setStatus(GlobalDefinedConstant.System_Status.normal.name());
		this.sysDeptRepository.save(obj);
		return obj;
	}

	@Override
	public SysDept findById(Long Id) throws EliteServiceException {
		this.verifyParams(Id);
		return this.sysDeptRepository.find(Id);
	}

	@Override
	public SysDept findByOrganId(Long organId) throws EliteServiceException {
		this.verifyParams(organId);
		return this.sysDeptRepository.searchUnique(new Search().addFilterEqual("organId", organId));
	}

	@Override
	public SysDept removePhysicalById(Long Id) throws EliteServiceException {
		this.verifyParams(Id);
		SysDept obj = this.sysDeptRepository.find(Id);
		if (obj == null)
			throw new EliteServiceException("未找到部门",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		List<SysUser> userList=sysUserService.findSysUserByDeptId(Id);
		if(!userList.isEmpty()){
			throw new EliteServiceException("此部门下存在用户不能删除",ErrorCodeEnum.OBJECT_EXIST.code);
		}
		if(!obj.getChildren().isEmpty()){
			throw new EliteServiceException("此部门下存在子部门不能删除",ErrorCodeEnum.OBJECT_EXIST.code);
		}
		return this.sysDeptRepository.remove(obj) ? obj : null;
	}

	@Override
	public SysDept removeLogicById(Long Id) throws EliteServiceException {
		this.verifyParams(Id);
		SysDept obj = this.sysDeptRepository.find(Id);
		if (obj == null)
			throw new EliteServiceException("未找到部门",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		List<SysUser> userList=sysUserService.findSysUserByDeptId(Id);
		if(!userList.isEmpty()){
			throw new EliteServiceException("此部门下存在用户不能删除",ErrorCodeEnum.OBJECT_EXIST.code);
		}
		if(!obj.getChildren().isEmpty()){
			throw new EliteServiceException("此部门下存在子部门不能删除",ErrorCodeEnum.OBJECT_EXIST.code);
		}
		obj.setStatus(GlobalDefinedConstant.System_Status.deleted.name());
		obj.setParentId(null);
		return this.sysDeptRepository.save(obj) ? obj : null;
	}

	@Override
	public SysDept updateSysDept(Long deptId,SysDept sysDept) throws EliteServiceException {
		this.verifyParams(deptId,sysDept);
		SysDept obj = this.sysDeptRepository.find(deptId);
		if (obj == null)
			throw new EliteServiceException("未找到相应部门",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setName(sysDept.getName());
		this.sysDeptRepository.save(obj);
		return obj;
	}
	
	@Override
	public SysDept findSysDeptByOrganIdAndName(Long organId,String name)throws EliteServiceException{
		Search search=new Search();
		search.addFilterEqual("organId", organId);
		search.addFilterEqual("name", name);
		return this.sysDeptRepository.searchUnique(search);
	}

	@Override
	public List<OrganDeptTree> findDeptTreeChildren(boolean isAllOrgan,Long organId, Long parentId) throws EliteServiceException {
		List<OrganDeptTree> resultList=new ArrayList<OrganDeptTree>();
		if(isAllOrgan){
			List<SysOrgan> organList=this.sysOrganService.findSysOrganAll();
			if(parentId==null){
				for(SysOrgan organ:organList){
					resultList.add(new OrganDeptTree(organ.getId(),organ.getId(),0L, organ.getName(), 1, OrganDeptTree.ORGAN_DEPT_TYPE.organ.name(), true, true));
					getOrganDeptList(organ.getId(), parentId, resultList);
				}
			}else{
				getOrganDeptList(organId, parentId, resultList);
			}
		}else{
			if(parentId==null){
				SysOrgan organ=this.sysOrganService.findSysOrganById(organId);
				if(organ==null){
					throw new EliteServiceException("找不到相应单位",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
				}
				resultList.add(new OrganDeptTree(organId,organId,0L, organ.getName(), 1, OrganDeptTree.ORGAN_DEPT_TYPE.organ.name(), true, true));
			}
			getOrganDeptList(organId, parentId, resultList);
		}
		return resultList;
	}
	
	private void getOrganDeptList(Long organId,Long parentId,List<OrganDeptTree> resultList){
		List<SysDept> depts=this.sysDeptRepository.querySysDeptByOrganIdAndParentId(organId, parentId);
		if(!depts.isEmpty())
			for(SysDept dept:depts){
				Long treePid=dept.getParentId()==null?organId:dept.getParentId();
				boolean isParent=!dept.getChildren().isEmpty();
				resultList.add(new OrganDeptTree(dept.getId(),organId,treePid, dept.getName(), 2, OrganDeptTree.ORGAN_DEPT_TYPE.dept.name(), isParent, isParent));
			}
	}

	@Override
	public List<SysDept> findAll() throws EliteServiceException {
		
		return this.sysDeptRepository.findAll();
	}

}
