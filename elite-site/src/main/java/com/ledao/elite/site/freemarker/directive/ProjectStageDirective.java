package com.ledao.elite.site.freemarker.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.service.project.ProjectService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目阶段查询指令
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@SuppressWarnings("rawtypes")
@Slf4j
@Component
public class ProjectStageDirective implements TemplateDirectiveModel{
	
	
	public static final String PROJECT_ID="projectId";//项目ID
	
	@Resource
	private ProjectService projectService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		try {
			List<ProjectDefineStage> list=getProjectStageList(env,params);
			env.setVariable("stageList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
			env.getConfiguration().setSharedVariable("stageList", list);
			if(body!=null){
				body.render(env.getOut());
			}
		} catch (Exception e) {
			log.error("解析项目阶段指令异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<ProjectDefineStage> getProjectStageList(Environment env,Map params) throws TemplateModelException {
		Object idParamObj = params.get(PROJECT_ID);
		Long projectId = (idParamObj == null||idParamObj=="") ? null : (Long.valueOf(idParamObj.toString()));
		Project project=this.projectService.findProjectById(projectId);
		if(project!=null&&project.getDefineId()!=null){
			return project.getProjectDefine().getStages();
		}
		return new ArrayList<>();
	}

}
