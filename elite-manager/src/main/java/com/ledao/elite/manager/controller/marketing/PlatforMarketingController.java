package com.ledao.elite.manager.controller.marketing;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatforMarketing;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Marketing_Platform;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Marketing_Type;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Role_Channel;
import com.ledao.elite.core.service.platform.PlatforMarketingService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 平台营销控制器
 * 
 */
@Controller("platforMarketingController")
@RequestMapping("/marketing")
public class PlatforMarketingController extends BaseController {
	@Resource
	private PlatforMarketingService platforMarketingService;

	/**
	 * 广告管理首页
	 */
	@RequestMapping(value = "/ad/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("typeList", Marketing_Type.values());
		return "/marketing/ad/list";
	}

	/**
	 * 列表查询
	 */
	@RequestMapping(value = "/ad/listData", method = RequestMethod.POST)
	public String listData(Model model, String keyword, String type, String usePlatform, Pager pager) {
		SearchResult<PlatforMarketing> sr = this.platforMarketingService.findPlatforMarketings(keyword, usePlatform,
				type, pager);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		return "/marketing/ad/list_frag";
	}

	/**
	 * 物理删除广告
	 */
	@SystemHandleLog(description = "删除广告", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/ad/remove")
	public ResponseBase remove(Long id) {
		this.platforMarketingService.removePhysicalById(id);
		return new ResponseBase();
	}

	/**
	 * 上下移动
	 * 
	 */
	@SystemHandleLog(description = "广告上下移动", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/ad/move")
	public ResponseBase move(String moveType, Long id) {
		this.platforMarketingService.updatePlatforMarketing(id, moveType);
		return new ResponseBase();
	}

	/**
	 * 添加页面显示
	 */
	@RequestMapping(value = "/ad/addview")
	public String addview(Model model) {
		model.addAttribute("typeList", Marketing_Type.values());
		model.addAttribute("platformlist", Marketing_Platform.values());
		model.addAttribute("roleChannellist", Role_Channel.values());
		return "/marketing/ad/create_ad";

	}

	/**
	 * 添加广告
	 * 
	 */
	@SystemHandleLog(description = "添加广告", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/ad/add", method = RequestMethod.POST)
	public ResponseBase add(PlatforMarketing obj) {
		this.platforMarketingService.createPlatForMarketing(obj);
		return new ResponseBase();
	}

	/**
	 * 查看详情
	 */
	@RequestMapping(value = "/ad/detail")
	public String detail(Model model, Long id) {
		PlatforMarketing obj = this.platforMarketingService.findPlatforMarketingById(id);
		model.addAttribute("it", obj);
		return "/marketing/ad/detail";

	}

	/**
	 * 更新页面显示
	 */
	@RequestMapping(value = "/ad/updateview")
	public String updateview(Model model, Long id) {
		model.addAttribute("typeList", Marketing_Type.values());
		model.addAttribute("platformlist", Marketing_Platform.values());
		model.addAttribute("roleChannellist", Role_Channel.values());
		PlatforMarketing obj = this.platforMarketingService.findPlatforMarketingById(id);
		model.addAttribute("it", obj);
		return "/marketing/ad/update_ad";
	}

	/**
	 * 更新广告
	 * 
	 */
	@SystemHandleLog(description = "更新广告信息", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/ad/update", method = RequestMethod.POST)
	public ResponseBase update(PlatforMarketing obj) {
		obj.setUpdateId(getUserId());
		this.platforMarketingService.updatePlatforMarketingInfo(obj);
		return new ResponseBase();
	}
}
