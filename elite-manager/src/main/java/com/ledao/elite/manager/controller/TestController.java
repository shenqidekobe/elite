package com.ledao.elite.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.annotation.SystemLoginLog;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.sys.SysUserService;

/**
 * 用户控制器test
 * */
@Controller
@RequestMapping()
public class TestController {
	
	@Resource
	private SysUserService sysUserService;

	@RequestMapping("/test")
	public String login(){
		return "test";
	}
	
	@RequestMapping("/test/info.html")
	@ResponseBody
	public String userInfo(){
		SysUser user=this.sysUserService.findSysUserById(1l);
		System.out.println(user.getSysDept());
		return user.toString();
	}
	
	/**
	 * 查询用户列表
	 * */
	@RequiresPermissions({"sys.user.list"})
	@SystemHandleLog(description="会员列表查询",type=LogsEnum.other)
	@RequestMapping("/test/list")
	public String userList(Model model){
		List<SysUser> list=this.sysUserService.queryAll();
		model.addAttribute("list",list);
		return "test/list";
	}
	
	/**
	 * 新增用户页面
	 * */
	@SystemLoginLog(description="添加新会员")
	@RequestMapping("/test/add")
	public String addUser(Model model){
		this.sysUserService.findSysUserById(10L);
		return "test/add";
	}
	
	/**
	 * 保存用户
	 * */
	@RequestMapping("/test/save")
	@ResponseBody
	public String save(SysUser user){
		this.sysUserService.createSysUser(user);
		return GlobalDefinedConstant.JS_DEFINED.JS_RESULT.SUCCESS;
	}
}
