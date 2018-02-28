package com.ledao.elite.manager.controller.settlement;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberInvoice;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Status;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord.WorkRecord_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberInvoiceService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.platform.PlatformInOrderService;
import com.ledao.elite.core.service.platform.PlatformWorkRecordService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 应收管理控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("receivableController")
@RequestMapping("/settlement")
public class ReceivableController extends BaseController {

	@Resource
	private ProjectService projectService;
	@Resource
	private PlatformWorkRecordService platformWorkRecordService;
	@Resource
	private PlatformInOrderService platformInOrderService;
	@Resource
	private MemberInvoiceService memberInvoiceService;
	@Resource
	private MemberCreditService memberCrditService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private MemberInvoiceService memberInvoiceStageService;
	@Resource
	private MemberCompanyService memberCompanyService;

	/**
	 * 应收管理列表
	 */
	@RequestMapping(value = "/receivable", method = RequestMethod.GET)
	public String list() {
		return "/settlement/receivable/list";
	}

	/**
	 * 应收管理列表查询
	 */
	@RequestMapping(value = "/receivable/listData", method = RequestMethod.POST)
	public String listData(Model model, String keyword, Pager pager) {
		SearchResult<Project> sr = this.projectService.findProjectListByReceivable(keyword, pager);
		List<Project> list = sr.getResult();
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		return "/settlement/receivable/list_frag";
	}

	/**
	 * 应收管理详情
	 */
	@RequestMapping(value = "/receivable/detail", method = RequestMethod.POST)
	public String detail(Model model, Long id) {
		Project obj = this.projectService.findProjectById(id);
		// 已开发票总额
		BigDecimal invoiceAmount = new BigDecimal(0);
		if (obj.getProjectDefine() != null) {
			List<ProjectDefineStage> stages = obj.getProjectDefine().getStages();
			// 判断是否已经开发票
			for (int i = 0; i < stages.size(); i++) {
				ProjectDefineStage stage = stages.get(i);
				MemberInvoice invoice = this.memberInvoiceService.findMemberInvoiceByStageId(stage.getId());
				if (invoice != null) {
					// 已开发票
					stages.get(i).setInvoiced(true);
					if (invoice.getAmount() != null) {
						invoiceAmount = invoiceAmount.add(invoice.getAmount());
					}
				}
				List<PlatformInOrder> orders = this.platformInOrderService.findPlatformInOrderByStageId(stage.getId(),
						PlatformInOrder_Status.wait_pay);
				for (int j = 0; j < orders.size(); j++) {
					if (StringUtils.isNotEmpty(orders.get(j).getInvoiceRise())) {
						stages.get(i).setAskInvoice(true);
					}
				}

				// 发票信息
				stages.get(i).setInvoice(invoice);
			}
			obj.getProjectDefine().setStages(stages);
		}
		model.addAttribute("it", obj);
		model.addAttribute("invoiceAmount", invoiceAmount);
		return "/settlement/receivable/detail";
	}

	/**
	 * 项目备注查询
	 */
	@RequestMapping(value = "/receivable/view/remarks", method = RequestMethod.POST)
	public String viewRemarks(Model model, Long id,String type) {
		
		if(type==null){
			 type=WorkRecord_Type.project.name();
		}
		List<PlatformWorkRecord> list = this.platformWorkRecordService
				.findPlatFromWorkRecords(type, id);
		model.addAttribute("list", list);
		model.addAttribute("foreignId", id);
		model.addAttribute("type", type);
		return "/settlement/receivable/remarks";
	}

	/**
	 * 查看发票
	 */
	@RequestMapping(value = "/receivable/invoice/view", method = RequestMethod.POST)
	public String viewInvoice(Model model, Long id) {
		MemberInvoice obj = this.memberInvoiceService.findMemberInvoiceByStageId(id);
		model.addAttribute("it", obj);
		return "/settlement/receivable/invoice_detail";
	}

	/**
	 * 开发票页面显示
	 */
	@RequestMapping(value = "/receivable/addInvoice/view")
	public String viewAddInvoice(Model model, Long id) {
		List<PlatformInOrder> orders = this.platformInOrderService.findPlatformInOrderByStageId(id,
				PlatformInOrder_Status.success);
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getMemberId() != null) {
				MemberPassport member = this.memberPassportService
						.findMemberDetailInfoById(orders.get(i).getMemberId());
				if (member != null) {
					orders.get(i).setMemberName(member.getCredit().getRealName());
					orders.get(i).setMemberPhone(member.getAccount());
					if (member.getCompany() != null) {
						orders.get(i).setCompanyName(member.getCompany().getCompanyName());
					}
				}
			}
		}
		model.addAttribute("list", orders);
		return "/settlement/receivable/addInvoice";
	}

	/**
	 * 开发票
	 */
	@SystemHandleLog(description = "开发票", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/receivable/addInvoice/save", method = RequestMethod.POST)
	public ResponseBase saveAddInvoice(MemberInvoice obj) {
		obj.setProcessId(getUserId());
		// 已开发票
		obj.setStatus("done");
		this.memberInvoiceService.createMemberInvoice(obj);
		return new ResponseBase();
	}

	/**
	 * 查看已收款信息
	 */
	@RequestMapping(value = "/receivable/inOrder/view", method = RequestMethod.POST)
	public String viewInOrder(Model model, Long id) {
		List<PlatformInOrder> orders = this.platformInOrderService.findPlatformInOrderByStageId(id,
				PlatformInOrder_Status.success);
		for (int i = 0; i < orders.size(); i++) {
			MemberCredit credit = this.memberCrditService.findMemberCreditByMemberId(orders.get(i).getMemberId());
			if (credit != null) {
				orders.get(i).setMemberName(credit.getRealName());
			}
		}
		model.addAttribute("list", orders);
		return "/settlement/receivable/inOrder_detail";
	}

	/**
	 * 显示确认收款信息
	 */
	@RequestMapping(value = "/receivable/confirmReceipt/view", method = RequestMethod.POST)
	public String viewConfirmReceipt(Model model, Long id) {
		ProjectDefineStage stage = this.projectDefineStageService.findProjectDefineWaitPayStageById(id);
		model.addAttribute("it", stage);
		return "/settlement/receivable/receipt_detail";
	}

	/**
	 * 确认收款
	 */
	@SystemHandleLog(description = "确认收款", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/receivable/confirmReceipt/save", method = RequestMethod.POST)
	public ResponseBase saveReceipt(Model model, Long stageId, ProjectDefineStage stage, PlatformInOrder order) {
		stage.setId(stageId);
		this.platformInOrderService.createPlatformInOrderOffline(order, stage);
		return new ResponseBase();
	}
}
