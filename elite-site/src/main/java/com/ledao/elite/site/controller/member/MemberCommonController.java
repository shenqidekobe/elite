package com.ledao.elite.site.controller.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ledao.elite.core.domain.sys.Area;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.service.sys.AreaService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.site.controller.BaseController;

/**
 * 会员公用统一的接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberCommonController")
@RequestMapping("/member")
public class MemberCommonController extends BaseController{
	
	
	@Resource
	private DictService dictService;
	
	@Resource
	private AreaService areaService;
	
	/**
	 * 会员擅长的技能树形查询
	 * */
	@SSOToken
	@RequestMapping(value="/common/adept",method=RequestMethod.POST)
	public List<Dict> adeptJobTree(){
		return this.dictService.findRootDictListByDictType(Dict_Type.job_role.name());
	}
	
	/**
	 * 地区树形查询
	 * */
	@SSOToken
	@RequestMapping(value="/common/area",method=RequestMethod.POST)
	public List<Area> areaTree(){
		return this.areaService.findRootAreaList();
	}
	

}
