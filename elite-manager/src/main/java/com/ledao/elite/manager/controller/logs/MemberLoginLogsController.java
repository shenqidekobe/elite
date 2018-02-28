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
import com.ledao.elite.core.domain.member.MemberLoginLogs;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.member.MemberLoginLogsService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 会员登录日志控制器
 * 
 */
@Controller("memberLoginLogsController")
@RequestMapping("/logs")
public class MemberLoginLogsController extends BaseController{

	@Resource
	private MemberLoginLogsService memberLoginLogsService;

	/**
	 * 会员日志首页
	 */
	@RequestMapping("/memberLoginLogs")
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
	@RequestMapping(value = "/memberLoginLogs/ListData", method = RequestMethod.POST)
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
		SearchResult<MemberLoginLogs> loginLogsResult = this.memberLoginLogsService.findMemberLoginLogsList(account,
				startDate, endDate, pager);
		List<MemberLoginLogs> loginLogsList = loginLogsResult.getResult();
		pager.calPageCount((long) loginLogsResult.getTotalCount());
		model.addAttribute("list", loginLogsList);
		model.addAttribute("pager", pager);
		return "/logs/member/list_loginLogs_frag";
	}

	/**
	 * 根据id查询登录日志
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/memberLoginLogs/view")
	public String viewLoginLog(Long id) {
		MemberLoginLogs loginLogs = this.memberLoginLogsService.findMemberLoginLogsById(id);
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer.serialize(loginLogs);
	}

}
