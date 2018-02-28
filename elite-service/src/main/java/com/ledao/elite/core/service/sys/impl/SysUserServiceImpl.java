package com.ledao.elite.core.service.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.domain.sys.SysUserRole;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.System_Status;
import com.ledao.elite.core.framework.constant.RoleEnum;
import com.ledao.elite.core.repository.sys.SysUserAreaRepository;
import com.ledao.elite.core.repository.sys.SysUserRepository;
import com.ledao.elite.core.repository.sys.SysUserRoleRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.core.utils.encry.PasswordUtils;

@Service
public class SysUserServiceImpl extends BaseSerivceImpl implements SysUserService {

	@Resource
	private SysUserRepository sysUserRepository;

	@Resource
	private SysUserRoleRepository sysUserRoleRepository;
	@Resource
	private SysUserAreaRepository sysUserAreaRepository;

	@Override
	public SysUser createSysUser(SysUser user) throws EliteServiceException {
		this.verifyParams(user, user.getPassword(), user.getRoleId());

		String passSalt = PasswordUtils.getAccountSalt();
		user.setPassSalt(passSalt);
		user.setPassword(PasswordUtils.getMD5PassWord(user.getPassword(), passSalt));
		user.setStatus(GlobalDefinedConstant.System_Status.normal.name());
		this.sysUserRepository.save(user);
		// 插入用戶角色關係
		SysUserRole sur = new SysUserRole();
		sur.setUserId(user.getId());
		sur.setRoleId(user.getRoleId());
		this.sysUserRoleRepository.save(sur);

		return user;
	}

	@Override
	public SysUser findSysUserById(Long userId) throws EliteServiceException {
		this.verifyParams(userId);
		return this.sysUserRepository.find(userId);
	}

	@Override
	public SysUser findSysUserByLoginName(String loginName) throws EliteServiceException {
		this.verifyParams(loginName);
		Search search = new Search();
		search.addFilterEqual("loginName", loginName);
		return this.sysUserRepository.searchUnique(search);
	}

	@Override
	public List<SysUser> queryAll() throws EliteServiceException {
		return sysUserRepository.findAll();
	}

	@Override
	public List<SysUser> findSysUserByDeptId(Long deptId) throws EliteServiceException {
		this.verifyParams(deptId);
		return this.sysUserRepository.search(new Search().addFilterEqual("deptId", deptId));
	}

	@Override
	public SearchResult<SysUser> findSysUserList(String keyword, String status, Long roleId, Pager pager)
			throws EliteServiceException {
		SearchResult<SysUser> sr = new SearchResult<SysUser>();
		List<SysUser> memberList = this.sysUserRepository.querSearchSysUsersList(keyword, status, roleId, pager);
		Integer totalCount = this.sysUserRepository.querSearchSysUsersListCount(keyword, status, roleId);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public SysUser updateUser(Long userId, SysUser sysUser, Boolean changePassWord) throws EliteServiceException {
		this.verifyParams(userId, sysUser);
		SysUser obj = this.sysUserRepository.find(userId);
		if (obj == null)
			throw new EliteServiceException("未找到用户", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		// 更新内容
		obj.setLoginName(sysUser.getLoginName());
		obj.setUserName(sysUser.getUserName());
		obj.setEmail(sysUser.getEmail());
		obj.setPassword(obj.getPassword());
		obj.setDeptId(sysUser.getDeptId());
		obj.setPhone(sysUser.getPhone());
		obj.setUserPhoto(sysUser.getUserPhoto());
		if (changePassWord) {
			String passSalt = PasswordUtils.getAccountSalt();

			obj.setPassSalt(passSalt);
			obj.setPassword(PasswordUtils.getMD5PassWord(sysUser.getPassword(), passSalt));
		}
		if (obj.getRoles().size() > 0) {
			if (!obj.getRoles().get(0).getRoleId().equals(sysUser.getRoleId())) {
				Search search = new Search();
				search.addFilterEqual("userId", obj.getId());
				SysUserRole role = this.sysUserRoleRepository.searchUnique(search);
				role.setRoleId(sysUser.getRoleId());
				this.sysUserRoleRepository.save(role);
			}
		}
		this.sysUserRepository.save(obj);
		return obj;
	}

	@Override
	public void updateUserLastLogin(Long userId, String lastLoginIp) throws EliteServiceException {
		this.verifyParams(userId);
		SysUser obj = this.sysUserRepository.find(userId);
		if (obj == null)
			return;
		obj.setLastLoginIp(lastLoginIp);
		obj.setLastLoginTime(new Date());
		this.sysUserRepository.save(obj);
	}

	@Override
	public void updateUserPassword(Long userId, String oldPass, String newPass) throws EliteServiceException {
		this.verifyParams(userId, oldPass, newPass);
		SysUser user = this.findSysUserById(userId);
		if (user == null)
			throw new EliteServiceException("未找到用户", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		String oldPassSalt = user.getPassSalt();
		String userPass = PasswordUtils.getMD5PassWord(oldPass, oldPassSalt);
		if (!user.getPassword().equals(userPass))
			throw new EliteServiceException("旧密码错误", ErrorCodeEnum.ACCOUNT_PASS_FAULT.code);
		String newPassSalt = PasswordUtils.getAccountSalt();
		userPass = PasswordUtils.getMD5PassWord(newPass, newPassSalt);
		user.setPassSalt(newPassSalt);
		user.setPassword(userPass);
		this.sysUserRepository.save(user);

	}

	@Override
	public SysUser removeLogicById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		SysUser obj = this.sysUserRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到用户", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setStatus(GlobalDefinedConstant.System_Status.deleted.name());
		/*
		 * SysUserRole userRole = this.sysUserRoleRepository.searchUnique(new
		 * Search().addFilterEqual("userId", id)); if (userRole != null) {
		 * this.sysUserRoleRepository.removeById(userRole.getId()); }
		 * SysUserArea sysUserArea = this.sysUserAreaRepository.searchUnique(new
		 * Search().addFilterEqual("userId", id)); if (sysUserArea != null) {
		 * this.sysUserAreaRepository.removeById(userRole.getId()); }
		 */
		return this.sysUserRepository.save(obj) ? obj : null;
	}

	public SysUser updateSysUserStatus(Long id, String status) throws EliteServiceException {
		this.verifyParams(id, status);
		SysUser obj = this.sysUserRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到用户", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setStatus(status);
		return this.sysUserRepository.save(obj) ? obj : null;
	}

	@Override
	public Boolean checkLoginName(String loginName) throws EliteServiceException {
		Boolean returnMess = false;
		this.verifyParams(loginName);
		SysUser obj = this.sysUserRepository.searchUnique(new Search().addFilterEqual("loginName", loginName));
		if (obj == null) {
			returnMess = true;
		}
		return returnMess;
	}

	@Override
	public SysUser findSysUserByLoginNameAndStatus(String loginName, String status) throws EliteServiceException {
		this.verifyParams(loginName);
		Search search = new Search();
		search.addFilterEqual("loginName", loginName);
		search.addFilterEqual("status", status);
		return this.sysUserRepository.searchUnique(search);
	}

	@Override
	public Long findSysUserAsRole(RoleEnum role) throws EliteServiceException {
		this.verifyParams(role, role.roleId);
		List<SysUser> userList = this.sysUserRepository.querSearchSysUsersByRoleId(null, System_Status.normal.name(),
				role.roleId, new Pager(0, 100));
		if (userList.isEmpty())
			return null;
		int size = userList.size();
		Random r = new Random();
		int i = r.nextInt(size);
		SysUser user = userList.get(i);
		if (user == null)
			return null;
		return user.getId();
	}

	@Override
	public SearchResult<SysUser> findSysUserListByKeyWordAndType(String keyword, Long roleId, Pager pager,
			String status) throws EliteServiceException {
		SearchResult<SysUser> sr = new SearchResult<SysUser>();
		List<SysUser> memberList = this.sysUserRepository.querSearchSysUsersByRoleId(keyword, status, roleId, pager);
		Integer totalCount = this.sysUserRepository.querSearchSysUsersByRoleIdCount(keyword, status, roleId);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

}
