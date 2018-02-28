package com.ledao.elite.core.repository.sys;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统用户接口
 * 
 * @author kobe.liu
 */
public interface SysUserRepository extends GenericDAO<SysUser, Long> {
	
	/**
	 * 
	 * @param keyword(用户名，姓名，手机号）
	 * @param Status
	 * @param roleId
	 * @param pager
	 * @return
	 */
	List<SysUser> querSearchSysUsersList(String keyword,String Status,Long roleId,Pager pager);
    /**
    * 
    * @param keyword(用户名，姓名，手机号）
    * @param Status
    * @param roleId
    * @return
    */
	Integer querSearchSysUsersListCount(String keyword,String Status,Long roleId);
	/**
	 * 根据roleId,status,username和loginname模糊，查询
	 * 
	 */
	List<SysUser> querSearchSysUsersByRoleId(String keyword,String Status,Long roleId,Pager pager);
	
	/**
	 * 根据roleId,status,username和loginname模糊，查询 数量
	 */
	Integer querSearchSysUsersByRoleIdCount(String keyword,String Status,Long roleId);

}
