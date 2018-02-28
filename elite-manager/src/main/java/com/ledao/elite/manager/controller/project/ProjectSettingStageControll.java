package com.ledao.elite.manager.controller.project;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.project.ProjectSettingStage;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.project.ProjectSettingStageService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 项目阶段设置控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectSettingStageControll")
@RequestMapping("/project")
public class ProjectSettingStageControll extends BaseController {

	@Resource
	private DictService dictService;
	@Resource
	private ProjectSettingStageService projectSettingStageService;

	/**
	 * 项目设置阶段首页
	 */

	@RequestMapping(value = "/stage/setting/view", method = RequestMethod.POST)
	public String view(Model model, Long id) {
		List<Dict> dicts = this.dictService.findDictListByDictType(Dict_Type.project_stage.name());
		model.addAttribute("list", dicts);
		List<ProjectSettingStage> stages = this.projectSettingStageService.findSettingStagesByProjectId(id);
		model.addAttribute("stages", stages);
		return "/project/pm/set_stage";
	}
	/**
	 * 设置项目阶段
	 */
	@ResponseBody
	@RequestMapping(value="/stage/save",method=RequestMethod.POST)
	@SystemHandleLog(description = "设置项目阶段", type = LogsEnum.saveOrUpdate)
	public ResponseBase save(String dicts,Long projectId){
		if(dicts==null){
			this.projectSettingStageService.removeSettingStagesByPorjectId(projectId);
		}else{
			String dict[]=dicts.split(",");
			this.projectSettingStageService.updateSettingStageByProjectId(projectId, dict, getUserId());
		}
		return new ResponseBase();
	}
}
