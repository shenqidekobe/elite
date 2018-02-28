package com.ledao.elite.manager.controller.settlement;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformOutOrder;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Status;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 出账订单控制器
 * @author Zhiyu Cao
 *
 */
@Controller("platformOutOrdeController")
@RequestMapping("/settlement")
public class PlatformOutOrderController extends BaseController{

	@Resource
	private PlatformOutOrderService platformOutOrderService;
	/**
	 * 首页
	 */
	@RequestMapping(value = "/platformOutOrder")
	public String index(Model model) {
		model.addAttribute("status", PlatformOutOrder_Status.values());
		model.addAttribute("type", PlatformOutOrder_Type.values());
		return "/settlement/outorder/list";
	}
	

	/**
	 * 查询
	 */
	@RequestMapping(value = "/platfromOutOrder/listData")
	public String listData(Model model, String keyword, String status, String type, Pager pager) {
		SearchResult<PlatformOutOrder> sr = this.platformOutOrderService.findPlatformOutOrderList(keyword, status, type,
				 pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pager", pager);
		return "/settlement/outorder/list_frag";
	}
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/platfromOutOrder/detail")
	public String detail(Model model,Long id) {
		PlatformOutOrder order=this.platformOutOrderService.findPlatformOutOrderById(id);
		model.addAttribute("it", order);
		return "/settlement/outorder/detail";
	}
}
