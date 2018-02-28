package com.ledao.elite.manager.controller.settings;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysOrgan;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.sys.SysOrganService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 单位管理控制器
 * 
 */
@Controller("sysOrganController")
@RequestMapping("/settings")
public class SysOrganController extends BaseController {

	@Resource
	private SysOrganService sysOrganService;

	/**
	 * 单位管理
	 * 
	 */
	@RequestMapping("/organ")
	public String user(Model model) {
		return "/settings/organ/list";
	}

	/**
	 * 分页查询
	 * 
	 * @param name
	 * @param parentId
	 * @param aredId
	 * @param status
	 */
	@RequestMapping(value = "/organ/listData", method = RequestMethod.POST)
	public String listData(Model model, String name, Long parentId, Long areaId, String status, Pager pager) {
		SearchResult<SysOrgan> organResult = this.sysOrganService.findSysOrganList(name, status, parentId, areaId,
				pager);
		List<SysOrgan> organList = organResult.getResult();
		pager.calPageCount((long) organResult.getTotalCount());
		model.addAttribute("list", organList);
		model.addAttribute("pager", pager);
		return "/settings/organ/list_frag";
	}

	/**
	 * 创建修改单位信息
	 * 
	 * @param organ
	 */
	@SystemHandleLog(description = "保存修改单位信息", type = LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value = "/organ/save", method = RequestMethod.POST)
	public String save(SysOrgan organ) {
		SysOrgan obj = null;
		if (organ.getId() == null) {
			obj = this.sysOrganService.create(organ);
		} else {
			obj = this.sysOrganService.updateSysOrgan(organ.getId(), organ);
		}
		JSONSerializer serializer = new JSONSerializer();
		return serializer.exclude("*.class","depts").serialize(obj);
	}

	/**
	 * 根据id逻辑删除单位信息
	 * 
	 * @param id
	 * @return
	 */

	@SystemHandleLog(description = "删除单位信息", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/organ/remove", method = RequestMethod.POST)
	public ResponseBase remove(Long id) {
		this.sysOrganService.removeLogicById(id);
		return new ResponseBase();
	}

	/**
	 * 根据Id查询用户
	 * 
	 * @param id
	 * @param
	 */
	@ResponseBody
	@RequestMapping(value = "/organ/view")
	public String findbyId(Long id) {
		SysOrgan sysOrgan = this.sysOrganService.findSysOrganById(id);
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("depts", "*.class");
		return serializer.serialize(sysOrgan);
	}

	/**
	 * 查询单位名称是否存在
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organ/check/organName")
	public Boolean checkName(Long id, String name) {
		if (id != null) {
			SysOrgan organ = this.sysOrganService.findSysOrganById(id);
			if (organ.getName().equals(name)) {
				return true;
			}
		}
		SysOrgan organ = this.sysOrganService.findSysOrganByIdAndName(id, name);
		return organ == null;
	}

}
