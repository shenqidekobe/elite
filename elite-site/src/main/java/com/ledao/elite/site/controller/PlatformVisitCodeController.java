package com.ledao.elite.site.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.platform.PlatformVisitCode;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.service.platform.PlatformVisitCodeService;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 平台邀请码控制中心
 * */
@Controller("platformVisitCodeController")
@RequestMapping("visit")
public class PlatformVisitCodeController extends BaseController{
	
	public static final String VISIT_SESSION="visitCodeSession";

	@Resource
	private PlatformVisitCodeService platformVisitCodeService;
	
	/**
	 * 输入邀请码首页
	 * */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(Model model){
		return REDIRECT_COMMAND+"/";
	}
	
	/**
	 * 开始访问平台
	 * */
	@ResponseBody
	@RequestMapping(value="/start",method=RequestMethod.POST)
	public ResponseResult<String> start(String visitName, String visitPhone, String visitCode,HttpServletRequest request){
		PlatformVisitCode vcode=this.platformVisitCodeService.findPlatformVisitCode(visitName, visitPhone, visitCode);
		String result=GlobalDefinedConstant.SUCCESS;
		if(vcode==null){
			result=GlobalDefinedConstant.FAILURE;
		}else{
			vcode.setUsed(true);
			vcode.setLastVisitTime(new Date());
			vcode.setVisitCount(vcode.getVisitCount()+1);
			PlatformVisitCode obj=this.platformVisitCodeService.updatePlatformVisitCode(vcode);
			request.getSession().setAttribute(VISIT_SESSION, obj.getVisitCode());
		}
		return new ResponseResult<>(result,null);
	}
}
