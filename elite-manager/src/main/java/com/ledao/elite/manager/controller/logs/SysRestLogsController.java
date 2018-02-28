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
import com.ledao.elite.core.domain.sys.SysRestLogs;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.sys.SysRestLogsService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 系统日志控制器
 * 
 */
@Controller("sysRestLogsController")
@RequestMapping("/logs")
public class SysRestLogsController extends BaseController{

	@Resource
	private SysRestLogsService sysRestLogsServcie;

	/**
	 * 系统接口日志首页
	 */
	@RequestMapping("/sysRestLogs")
	public String list(Model model) {
		return "/logs/rest/list";
	}

	/**
	 * 分页查询
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @param userName
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/sysRestLogs/listData", method = RequestMethod.POST)
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
		SearchResult<SysRestLogs> logsResult = this.sysRestLogsServcie.findSysRestLogsList(userName, startDate, endDate,
				pager);
		List<SysRestLogs> logsList = logsResult.getResult();
		pager.calPageCount((long) logsResult.getTotalCount());
		model.addAttribute("list", logsList);
		model.addAttribute("pager", pager);
		return "/logs/rest/list_frag";
	}

	/**
	 *根据id查询
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/sysRestLogs/view",method=RequestMethod.POST)
	public String serarchById( Long id) {
		SysRestLogs logs = this.sysRestLogsServcie.findSysRestLogById(id);
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer.serialize(logs);
	}

}
