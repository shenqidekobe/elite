package com.ledao.elite.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.CommandCache;

/**
 * 公用处理控制器
 * */
@Controller("commonController")
@RequestMapping("/common")
public class CommonController extends BaseController{
	
	
	/**
	 * 错误页
	 * */
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public String error(){
		return "common/error";
	}
	
	/**
	 * 无权限页
	 * */
	@RequestMapping(value="/unauthorized",method=RequestMethod.GET)
	public String unauthorized(){
		return "common/unauthorized";
	}
	
	/**
	 * 生成命令
	 * */
	@ResponseBody
	@RequestMapping(value="/command",method=RequestMethod.POST)
	public ResponseBase createCommand(String key){
		Long id=getUserId();
		CommandCache.create(id+"-"+key);
		return new ResponseBase();
	}

}
