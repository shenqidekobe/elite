package com.ledao.elite.manager.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysMenu;
import com.ledao.elite.core.domain.sys.SysMessage;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.service.sys.SysMenuService;
import com.ledao.elite.core.service.sys.SysMessageService;
import com.ledao.elite.core.service.sys.SysUserService;

/**
 * 系统首页控制器
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("indexController")
@RequestMapping("/index")
public class IndexController extends BaseController{
	
	@Resource
	private SysUserService sysUserService;
	
	@Resource
	private SysMenuService sysMenuService;

	@Resource
	private SysMessageService sysMessageService;
	
	/**
	 * 系统首页
	 * */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(Model model,HttpServletRequest request){
		List<SysMenu> menus=sysMenuService.findSysUserRootMenus(getUserId());
		request.getSession().setAttribute("menus", menus);
		SearchResult<SysMessage> sr = sysMessageService.findSysMessagesByUserId(getUserId(), "true", null);
		int unreadCount = sr.getTotalCount();
		model.addAttribute("unreadCount", unreadCount);
		SysUser user=this.sysUserService.findSysUserById(getUserId());

        String userRolesStr="";
        for(int i=0;i<user.getRoles().size();i++){
        	userRolesStr=userRolesStr+user.getRoles().get(i).getSysRole().getName();
        }
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("userRoles", userRolesStr);
		return "index";
	}
	/**
	 * 用户更新密码
	 * */
	@ResponseBody
	@RequestMapping(value="/update/pass",method=RequestMethod.POST)
	public ResponseBase updatePass(String oldPass,String newPass){
		this.sysUserService.updateUserPassword(getUserId(), oldPass, newPass);
		return new ResponseBase();
	}

}
