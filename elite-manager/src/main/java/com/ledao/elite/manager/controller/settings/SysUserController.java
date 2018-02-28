package com.ledao.elite.manager.controller.settings;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysRole;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.domain.sys.SysUserRole;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.sys.SysDeptService;
import com.ledao.elite.core.service.sys.SysRoleService;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 后台用户管理控制器
 * 
 */
@Controller("sysUserController")
@RequestMapping("/settings")
public class SysUserController extends BaseController {

	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysRoleService sysRoleService;

	@Resource
	private SysDeptService sysDeptService;

	/**
	 * 跳转到角色管理
	 * 
	 * @param
	 * @param
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model) {
		try {
			List<SysRole> roleList = this.sysRoleService.findAll();
			model.addAttribute("roleList", roleList);
		} catch (Exception e) {
		}
		return "/settings/user/list";
	}

	/**
	 * 分页根据phone,部门,用户名，查询用户
	 * 
	 * @param
	 * @param
	 */
	@RequestMapping(value = "/user/listData", method = RequestMethod.POST)
	public String listData(Model model, String keyword, Long roleId, String status, Pager pager) {
		SearchResult<SysUser> userResult = sysUserService.findSysUserList(keyword, status, roleId, pager);
		List<SysUser> userList = userResult.getResult();
		pager.calPageCount((long) userResult.getTotalCount());
		model.addAttribute("list", userList);
		model.addAttribute("pager", pager);
		return "/settings/user/list_frag";
	}

	/**
	 * 逻辑删除用户
	 * 
	 * @param
	 * @param
	 */
	@SystemHandleLog(description = "逻辑删除后台用户", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/user/remove", method = RequestMethod.POST)
	public ResponseBase removeUser(Long id) {
		this.sysUserService.removeLogicById(id);
		return new ResponseBase();
	}
	
	/**
	 * 禁用用户
	 * 
	 * @param
	 * @param
	 */
	@SystemHandleLog(description = "禁用后台用户", type = LogsEnum.disable)
	@ResponseBody
	@RequestMapping(value = "/user/disable", method = RequestMethod.POST)
	public ResponseBase disable(Long id) {
		this.sysUserService.updateSysUserStatus(id, GlobalDefinedConstant.System_Status.disabled.name());
		return new ResponseBase();
	}
	
	/**
	 * 禁用用户
	 * 
	 * @param
	 * @param
	 */
	@SystemHandleLog(description = "启用后台用户", type = LogsEnum.enable)
	@ResponseBody
	@RequestMapping(value = "/user/enable", method = RequestMethod.POST)
	public ResponseBase enable(Long id) {
		this.sysUserService.updateSysUserStatus(id, GlobalDefinedConstant.System_Status.normal.name());
		return new ResponseBase();
	}

	/**
	 * 添加修改用户
	 * 
	 * @param
	 * @param
	 */
	@ResponseBody
	@SystemHandleLog(description = "添加或者修改后台用户", type = LogsEnum.saveOrUpdate)
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ResponseBase addUser(SysUser user, Boolean changePassWord) {
		
		if (user.getId() == null) {
			this.sysUserService.createSysUser(user);
		} else {
			this.sysUserService.updateUser(user.getId(), user, changePassWord);
		}
		return new ResponseBase();
	}

	/**
	 * 根据Id查询用户
	 * 
	 * @param
	 * @param
	 */
	@ResponseBody
	@RequestMapping(value = "/user/view", method = RequestMethod.POST)
	public String findbyId(Long id) {
		SysUser sysUser = this.sysUserService.findSysUserById(id);
		List<SysUserRole> roles = sysUser.getRoles();
		if (!roles.isEmpty()) {
			Long roleId = null;
			for (SysUserRole ur : roles) {
				roleId = ur.getRoleId();
				break;
			}
			sysUser.setRoleId(roleId);
		}
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude( "roles", "*.class");
		return serializer.serialize(sysUser);
	}

	/**
	 * 判断登录名是否存在
	 * 
	 * @param
	 * @param
	 */
	@ResponseBody
	@RequestMapping(value = "/user/check/loginName")
	public Boolean checkLoginName(Long id, String loginName) {
		if (id != null) {
			SysUser user = this.sysUserService.findSysUserById(id);
			if (user.getLoginName().equals(loginName)) {
				return true;
			}
		}
		SysUser user = this.sysUserService.findSysUserByLoginName(loginName);
		return user == null;
	}

}
