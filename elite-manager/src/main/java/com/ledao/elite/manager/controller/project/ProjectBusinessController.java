package com.ledao.elite.manager.controller.project;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.project.ProjectBusiness;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.constant.RoleEnum;
import com.ledao.elite.core.service.project.ProjectBusinessService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 商务记录控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectBusinessController")
@RequestMapping("/project")
public class ProjectBusinessController extends BaseController {

	@Resource
	private ProjectBusinessService projectBusinessService;
	@Resource
	private ProjectService projectService;

	/**
	 * 查询商务记录
	 */
	@RequestMapping(value = "{type}/business/view")
	public String businessView(@PathVariable String type, Model model, Long projectId) {
		List<ProjectBusiness> businessList = this.projectBusinessService.findProjectBusinessListByProject(projectId,
				null);
		this.projectService.updateProjectStatusInById(projectId);
		RoleEnum role=null;
		if (getRoleId().equals(RoleEnum.super_admin.roleId)) {
			role = RoleEnum.super_admin;
		}
	    model.addAttribute("role",role);
		model.addAttribute("list", businessList);
		return "/project/" + type + "/business";
	}

	/**
	 * 添加商务记录
	 */
	@SystemHandleLog(description = "添加商务记录", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/business/save",method = RequestMethod.POST)
	public ResponseBase save(ProjectBusiness obj) {

		this.projectBusinessService.createProjectBusiness(obj);
		return new ResponseBase();
	}
	/**
	 * 添加商务记录
	 */
	@SystemHandleLog(description = "更新商务记录", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/business/update",method = RequestMethod.POST)
	public ResponseBase save(Long id,String content) {
		
		ProjectBusiness obj=this.projectBusinessService.findProjectBusinessById(id);
		obj.setContent(content);
		this.projectBusinessService.updateProjectBusiness(obj);
		return new ResponseBase();
	}
	

	/**
	 * 删除商务记录
	 */
	@SystemHandleLog(description = "删除商务记录", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/business/remove")
	public ResponseBase remove(Long id) {
		this.projectBusinessService.removeLogicById(id);
		return new ResponseBase();
	}
}
