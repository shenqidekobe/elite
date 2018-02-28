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
import com.ledao.elite.core.domain.member.MemberLogs;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.member.MemberLogsService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 会员操作日志控制器
 * 
 */
@Controller("memberLogsController")
@RequestMapping("/logs")
public class MemberLogsController extends BaseController{

	@Resource
	private MemberLogsService memberLogsService;

	/**
	 * 会员日志首页
	 */
	@RequestMapping("/memberLogs")
	public String list(Model model) {
		return "/logs/member/list";
	}

	/**
	 * 会员登录日志分页查询
	 * 
	 * @param model
	 * @param memberId
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/memberLogs/ListData", method = RequestMethod.POST)
	public String loginListData(Model model, String account, String startTime, String endTime, Pager pager)
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
		SearchResult<MemberLogs> logsResult = this.memberLogsService.findMemberLogsList(account, startDate, endDate,
				pager);
		List<MemberLogs> logsList = logsResult.getResult();
		pager.calPageCount((long) logsResult.getTotalCount());
		model.addAttribute("list", logsList);
		model.addAttribute("pager", pager);
		return "/logs/member/list_logs_frag";
	}

	/**
	 * 根据id查询会员操作日志
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/memberLogs/view")
	public String viewLoginLog(Long id) {
		MemberLogs Logs = this.memberLogsService.findMemberLogs(id);
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer.serialize(Logs);
	}

}
