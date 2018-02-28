package com.ledao.elite.manager.controller.settings;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysMenu;
import com.ledao.elite.core.domain.sys.SysRole;
import com.ledao.elite.core.domain.sys.SysRoleMenu;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.sys.SysMenuService;
import com.ledao.elite.core.service.sys.SysRoleService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 系统角色管理
 * 
 */
@Controller("sysRoleController")
@RequestMapping("/settings")
public class SysRoleController extends BaseController {

	@Resource
	private SysRoleService sysRoleService;

	@Resource
	private SysMenuService sysMenuService;

	/**
	 * 角色首页
	 * 
	 * @return
	 */
	@RequestMapping("/role")
	public String index() {
		return "/settings/role/list";
	}

	/**
	 * 分页查询角色
	 * 
	 * @param model
	 * @param status
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/role/listData", method = RequestMethod.POST)
	public String listdata(Model model, String status, Pager pager) {

		SearchResult<SysRole> roleResult = this.sysRoleService.findSysRoleList(status, pager);
		List<SysRole> roleList = roleResult.getResult();
		pager.calPageCount((long) roleResult.getTotalCount());
		model.addAttribute("list", roleList);
		model.addAttribute("pager", pager);
		return "/settings/role/list_frag";
	}

	/**
	 * 根据id查询角色
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/role/view")
	public String view(Long id) {
		SysRole role = this.sysRoleService.findSysRoleById(id);
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer.serialize(role);
	}

	/**
	 * 保存角色菜单权限信息
	 */
	@SystemHandleLog(description = "保存角色权限", type = LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping("/role/saveMenus")
	public ResponseBase saveRoleMemus(Long roleId, String menuIds) {
		this.sysRoleService.createSysRoleMenu(roleId, menuIds);
		return new ResponseBase();
	}

	/***
	 * 获取所有的菜单
	 */
	@RequestMapping(value = "/role/menus", method = RequestMethod.POST)
	@ResponseBody
	public List<SysMenu> menus(Long roleId) {
		List<SysMenu> list = sysMenuService.findSysMenuByType(SysMenu.SYSMENU_TYPE.menu.name());
		return list;
	}

	/**
	 * 获取当前角色的权限
	 */
	@RequestMapping(value = "/role/roleMenus", method = RequestMethod.POST)
	@ResponseBody
	public List<SysMenu> roleMenus(@RequestParam(value = "roleId", required = false) Long roleId) {
		try {
			List<SysMenu> jfqPowerLs = new ArrayList<>();
			if (roleId != null) {
				SysRole role = sysRoleService.findSysRoleById(roleId);
				List<SysRoleMenu> roleMenu = role.getMenus();
				for (SysRoleMenu rm : roleMenu) {
					SysMenu menu = rm.getSysMenu();
					jfqPowerLs.add(menu);
				}
			} else {
				jfqPowerLs = sysMenuService.findSysMenuByType(null);
			}
			return jfqPowerLs;
		} catch (Exception e) {
			logger.error("查询权限列表异常", e);
		}
		return null;
	}

	/**
	 * 判断角色名是否存在
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/role/check/roleName")
	public Boolean checkLoginName(Long id, String name) {
		if (id != null) {
			SysRole role = this.sysRoleService.findSysRoleById(id);
			if (role.getName().equals(name)) {
				return true;
			}
		}
		SysRole role = this.sysRoleService.findSysRoleByIdAndName(id, name);
		return role == null;
	}

	/**
	 * 创建修改
	 * 
	 * @param
	 * @param
	 */
	@SystemHandleLog(description = "创建修改角色", type = LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value = "/role/save", method = RequestMethod.POST)
	public ResponseBase saveSysRole(SysRole role) {
		if (role.getId() == null) {
			this.sysRoleService.createSysRole(role);
		} else {
			this.sysRoleService.updateSysRole(role.getId(), role);
		}
		return new ResponseBase();
	}

	/**
	 * 删除角色
	 * 
	 * @param
	 * @param
	 */
	@SystemHandleLog(description = "删除角色", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/role/remove")
	public ResponseBase remove(Long id) {
		this.sysRoleService.removeSysRoleById(id);
		return new ResponseBase();
	}

}
