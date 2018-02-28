package com.ledao.elite.core.service.sys;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.RoleEnum;

/**
 * 系统用户服务接口
 * 
 * @author kobe.liu
 */
public interface SysUserService {

	/**
	 * 创建一个用户
	 * 
	 * @param user
	 * @return SysUser
	 */
	SysUser createSysUser(SysUser user) throws EliteServiceException;

	/**
	 * 更新用户信息
	 * 
	 * @param sysUser
	 * @return SysUser
	 */
	SysUser updateUser(Long userId, SysUser sysUser,Boolean changPassWord) throws EliteServiceException;

	/**
	 * 更新用户信息
	 * 
	 * @param userId
	 * @param lastLoginIp
	 */
	void updateUserLastLogin(Long userId, String lastLoginIp) throws EliteServiceException;
	
	/**
	 * 更新用户密码
	 * 
	 * @param userId
	 * @param oldPass
	 * @param newPass
	 */
	void updateUserPassword(Long userId, String oldPass,String newPass) throws EliteServiceException;

	/**
	 * 按ID逻辑删除
	 * 
	 * @param Id
	 * @return SysUser
	 */
	SysUser removeLogicById(Long id) throws EliteServiceException;
	
	/**
	 * 更新用户状态
	 * 
	 * @param Id
	 * @param status
	 * @return SysUser
	 */
	SysUser updateSysUserStatus(Long id,String status) throws EliteServiceException;
	
	/**
	 * 查询用户是否存在
	 * 
	 * @param loginName
	 * @return Boolean
	 */
	Boolean checkLoginName(String loginName)throws EliteServiceException;
	
	/**
	 * 根据用户名，状态查询
	 * @param loginName
	 * @param status
	 * @return Sysuser
	 * @throws EliteServiceException
	 */
	SysUser findSysUserByLoginNameAndStatus(String loginName,String status) throws EliteServiceException;
	
	/**
	 * 随机获取一个指定角色的用户
	 * 
	 * @param role
	 * @return userId
	 * */
	Long findSysUserAsRole(RoleEnum role)throws EliteServiceException;
	
	/**
	 * 按ID查询用户
	 * 
	 * @param userId
	 * @return SysUser
	 */
	SysUser findSysUserById(Long userId) throws EliteServiceException;

	/**
	 * 按loginName查询用户
	 * 
	 * @param loginName
	 * @return SysUser
	 */
	SysUser findSysUserByLoginName(String loginName) throws EliteServiceException;

	/**
	 * 查询所有用户信息
	 * 
	 * @return List<SysUser>
	 */
	List<SysUser> queryAll() throws EliteServiceException;
	
	/**
	 * 按部门ID查询用户
	 * 
	 * @param deptId
	 * */
	List<SysUser> findSysUserByDeptId(Long deptId)throws EliteServiceException;

	/**
	 * 按条件分页查询用户列表
	 * 
	 * @param phone
	 * @param status
	 * @param pager
	 * 
	 * @return SearchResult<SysUser>
	 */
	 SearchResult<SysUser> findSysUserList(String keyword, String status, Long roleId, Pager pager)
			throws EliteServiceException;
	
	/**
	 * 根据role 关键字查询用户列表
	 */
	SearchResult<SysUser>findSysUserListByKeyWordAndType(String keyWord,Long RoleId,Pager pager,String Status)throws EliteServiceException;
}
