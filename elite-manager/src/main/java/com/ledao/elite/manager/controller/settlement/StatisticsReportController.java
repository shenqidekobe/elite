package com.ledao.elite.manager.controller.settlement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ledao.elite.manager.controller.BaseController;

/**
 * 统计报表控制器
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("statisticsReportController")
@RequestMapping("/settlement")
public class StatisticsReportController extends BaseController{
	
	
	/**
	 * 应收统计表
	 * */
	@RequestMapping(value="/receivable/report",method=RequestMethod.GET)
	public String list(){
		return "/settlement/report/receivableList";
	}
	
	/**
	 * 应收统计表查询
	 * */
	@RequestMapping(value="/receivable/report/listData",method=RequestMethod.POST)
	public String listData(){
		return "/settlement/report/receivableList_frag";
	}

}
