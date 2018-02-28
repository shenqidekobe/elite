package com.ledao.elite.manager.controller.settlement;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.platform.PlatformOutOrder;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Status;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectStageTaskService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 应付管理控制器
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("payableController")
@RequestMapping("/settlement")
public class PayableController extends BaseController{
	
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectStageTaskService projectStageTaskService;
	@Resource
	private PlatformOutOrderService platformOutOrderService;
	@Resource
	private MemberCreditService memberCreditService;
	/**
	 * 应付管理列表
	 * */
	@RequestMapping(value="/payable",method=RequestMethod.GET)
	public String list(){
		return "/settlement/payable/list";
	}
	
	/**
	 * 应付管理列表查询
	 * */
	@RequestMapping(value="/payable/listData",method=RequestMethod.POST)
	public String listData(Model model,String keyword,Pager pager){
		SearchResult<Project> sr = this.projectService.findProjectListByPayable(keyword, pager);
		List<Project> list = sr.getResult();
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		return "/settlement/payable/list_frag";
	}
	/**
	 * 应付管理详情
	 */
	@RequestMapping(value = "/payable/detail", method = RequestMethod.POST)
	public String detail(Model model, Long id) {
		Project obj = this.projectService.findProjectDetailByPayable(id);
		model.addAttribute("it", obj);
		return "/settlement/payable/detail";
	}
    
	/**
	 * 应付管理  详细资金走向列表查询
	 */
	@RequestMapping(value = "/payable/detail/listData", method = RequestMethod.POST)
	public String detailListData(Model model,Long id,Date startTime,Date endTime,String keyword,Pager pager){
		SearchResult<PlatformOutOrder> sr=this.platformOutOrderService.findPlatformOutOrderListByProjectId(PlatformOutOrder_Status.success.name(), id, pager,keyword,startTime,endTime);
	    List<PlatformOutOrder> orders=sr.getResult();
	    for(int i=0;i<orders.size();i++){
	       MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(orders.get(i).getMemberId());
	       if(credit!=null){
	    	   orders.get(i).setCredit(credit);
	       }
	    }
		
		model.addAttribute("list", orders);
	    pager.calPageCount((long)sr.getTotalCount());
	    model.addAttribute("pager",pager);
		return "/settlement/payable/detail_list_frag";
	}
}
