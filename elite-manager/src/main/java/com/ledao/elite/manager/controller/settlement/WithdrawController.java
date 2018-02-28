package com.ledao.elite.manager.controller.settlement;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 提现管理控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("withdrawController")
@RequestMapping("/settlement")
public class WithdrawController extends BaseController {

	@Resource
	private MemberWithdrawService memberWithdrawService;

	/**
	 * 提现管理列表
	 */
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public String list() {
		return "/settlement/withdraw/list";
	}

	/**
	 * 提现管理列表查询
	 */
	@RequestMapping(value = "/withdraw/listData", method = RequestMethod.POST)
	public String listData(Model model, String keyword, Date startTime, Date endTime, Pager pager) {
		SearchResult<MemberWithdraw> sr = this.memberWithdrawService.findMemberWithdrawList(keyword, startTime, endTime,
				null, pager);
		List<MemberWithdraw> list = sr.getResult();
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", list);
		return "/settlement/withdraw/list_frag";
	}

	/**
	 * 发票管理 显示
	 */
	@RequestMapping(value = "/withdraw/invoice/view", method = RequestMethod.POST)
	public String viewInovice(Model model, Long id) {
		MemberWithdraw withdraw = this.memberWithdrawService.findMemberWithdrawById(id);
		model.addAttribute("it", withdraw);
		return "/settlement/withdraw/invoice";
	}

	/**
	 * 上传发票信息
	 */
	@SystemHandleLog(description = "提现处理发票上传", type = LogsEnum.update)
	@RequestMapping(value = "/withdraw/invoice/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBase saveInvoice(MemberWithdraw withdraw) {
		MemberWithdraw obj = this.memberWithdrawService.findMemberWithdrawById(withdraw.getId());
		obj.setInvoiceAmount(withdraw.getInvoiceAmount());
		obj.setReceiptAmount(withdraw.getReceiptAmount());
		obj.setFinanceId(getUserId());
		obj.setInvoiceAttaId(withdraw.getInvoiceAttaId());
		this.memberWithdrawService.updateMemberWithdraw(obj);
		return new ResponseBase();
	}

	/**
	 * 确认提现页面显示
	 */
	@RequestMapping(value = "/withdraw/pay/view", method = RequestMethod.POST)
	public String viewPay(Model model, Long id) {
		MemberWithdraw withdraw = this.memberWithdrawService.findMemberWithdrawById(id);
		model.addAttribute("it", withdraw);
		return "/settlement/withdraw/pay";
	}

	/**
	 * 确认打款
	 */
	@SystemHandleLog(description = "处理提现 确认打款", type = LogsEnum.update)
	@RequestMapping(value = "/withdraw/pay/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBase saveInovice(MemberWithdraw withdraw) {
		int minute=appConfig.getIntervalMinute();//7天后到账：7*24*60-120：7天后的两小时前开始转账"单位分钟
		this.memberWithdrawService.updateMemberWithdrawAsFinanceAffirm(withdraw.getId(),withdraw.getInvoiceAttaId(),withdraw.getWithdrawAttaId(),true, null, minute);
		return new ResponseBase();
	}

	/**
	 * 提现记录
	 */
	@RequestMapping(value = "/withdraw/invoicelog/view", method = RequestMethod.POST)
	public String viewInoviceLog(Model model, Long id) {
		MemberWithdraw withdraw = this.memberWithdrawService.findMemberWithdrawById(id);
		model.addAttribute("it", withdraw);
		return "/settlement/withdraw/invoice_log";
	}
}
