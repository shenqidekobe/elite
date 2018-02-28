package com.ledao.elite.manager.controller.settlement;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Status;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Channel;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.platform.PlatformInOrderService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 入账订单控制器
 * 
 * @author Zhiyu Cao
 *
 */
@Controller("platformInOrderController")
@RequestMapping("/settlement")
public class PlatformInOrderController extends BaseController {

	@Resource
	private PlatformInOrderService platformInOrderService;
	@Resource
	private MemberCreditService memberCreditService;

	/**
	 * 首页
	 */
	@RequestMapping(value = "/platformInOrder")
	public String index(Model model) {
		model.addAttribute("status", PlatformInOrder_Status.values());
		model.addAttribute("type", PlatformInOrder_Type.values());
		model.addAttribute("payType", Pay_Channel.values());
		return "/settlement/inorder/list";
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "/platfromInOrder/listData")
	public String listData(Model model, String keyword, String status, String type, String payType, Pager pager) {
		SearchResult<PlatformInOrder> sr = this.platformInOrderService.findPlatformInOrdersByKey(keyword, status, type,
				payType, pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pager", pager);
		return "/settlement/inorder/list_frag";
	}
	/**
	 * 详情
	 */
	@RequestMapping(value = "/platfromInOrder/detail")
	public String detail(Model model,Long id) {
		PlatformInOrder order=this.platformInOrderService.findPlatformInOrderById(id);
		if(order.getMemberId()!=null){
			MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(order.getMemberId());
			order.setMemberName(credit.getRealName());
		}
		model.addAttribute("it", order);
		return "/settlement/inorder/detail";
	}
	
}
