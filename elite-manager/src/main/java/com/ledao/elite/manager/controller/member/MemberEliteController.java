package com.ledao.elite.manager.controller.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 精英管理中心
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller
@RequestMapping("/member")
public class MemberEliteController extends BaseController{
	
	
	@Resource
	private MemberEliteService memberEliteService;
	
	@RequestMapping(value="/listData",method=RequestMethod.POST)
	public String listData(){
		return "";
	}
	
}
