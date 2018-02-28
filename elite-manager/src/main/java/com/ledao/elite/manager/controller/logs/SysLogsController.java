package com.ledao.elite.manager.controller.logs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysLogs;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.sys.SysLogsService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 系统日志控制器
 * 
 */
@Controller("sysLogsController")
@RequestMapping("/logs")
public class SysLogsController extends BaseController{

	@Resource
	private SysLogsService sysLogsServcie;

	/**
	 * 系统日志首页
	 */
	@RequestMapping("/sysLogs")
	public String list(Model model) {
		return "/logs/sys/list";
	}

	/**
	 * 查询log
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value="/sysLogs/listData",method = RequestMethod.POST)
	public String listData(Model model, String startTime, String endTime, Pager pager, String userName)
			throws ParseException {
		Date startDate = null;
		Date endDate = null;
		if (null != startTime) {
			startTime+=" 00:00:00";
			DateFormat dataformat = new SimpleDateFormat(DATE_FORMAT_DEFAULT_PATTEN);
			startDate = dataformat.parse(startTime);
		}
		if (null != endTime) {
			endTime += " 23:59:59";
			DateFormat dataformat = new SimpleDateFormat(DATE_FORMAT_DEFAULT_PATTEN);
			endDate = dataformat.parse(endTime);
		}
		pager.setPageSize(15);
		SearchResult<SysLogs> logsResult = this.sysLogsServcie.findSysLogsList(userName, startDate, endDate, pager);
		List<SysLogs> logsList = logsResult.getResult();
		pager.calPageCount((long) logsResult.getTotalCount());
		model.addAttribute("list", logsList);
		model.addAttribute("pager", pager);
		return "/logs/sys/list_frag";
	}

	/**
	 * 查询log
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/sysLogs/view")
	public String serarchById(Long id) {
		SysLogs logs = this.sysLogsServcie.findSysLogById(id);
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer.serialize(logs);
	}

}
