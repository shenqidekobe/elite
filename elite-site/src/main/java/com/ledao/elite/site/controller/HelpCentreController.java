package com.ledao.elite.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 帮助中心控制器
 * */
@Controller("helpCentreController")
@RequestMapping("/help")
public class HelpCentreController extends BaseController{
	
	
	/**
	 * 帮助中心首页
	 * */
	@RequestMapping(value={"","/"},method=RequestMethod.GET)
	public String index(Model model,String rps){
		examineIsUnreadMessage(model, false);//检查是否有未读消息
		model.addAttribute("rps", rps);
		return "/index/help/list";
	}
	
	/**
	 * 帮助中心列表搜索
	 * */
	@RequestMapping(value="/listData",method=RequestMethod.POST)
	public String listData(Model model){
		
		return "/index/help/list_frag";
	}

}
