package com.ledao.elite.core.framework.thread;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.utils.SpringContextUtil;

import cn.jpush.api.utils.StringUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 托管费用同步更新CTO的项目阶段
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Slf4j
public class SyncCTOProjectStageThread implements Runnable{
	
	@Setter
	public Long projectId;
	@Setter
	public boolean confirmDefine=false;//CTO确认立项书的时候
	@Setter
	public String currentStageCode=null;//当前同步的阶段code
	
	private ProjectDefineService projectDefineService;
	private ProjectDefineStageService projectDefineStageService;
	
	public SyncCTOProjectStageThread(){
		projectDefineService=(ProjectDefineService)SpringContextUtil.getBean("projectDefineService");
		projectDefineStageService=(ProjectDefineStageService)SpringContextUtil.getBean("projectDefineStageService");
	}

	@Override
	public void run() {
		if(projectId==null)return;
		//第一步：查询CTO的立项书，没有则不更新，获取其对应的阶段，开始同步
		ProjectDefine ctoDefine=this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.cto,confirmDefine?null:ProjectDefine_Status.normal);
		log.info("同步项目"+projectId+"的CTO的项目阶段，CTO立项书："+ctoDefine);
		if(ctoDefine==null)return;
		List<ProjectDefineStage> ctoStageList=projectDefineStageService.findProjectDefineStageByDefinedId(ctoDefine.getId());
		if(ctoStageList.isEmpty())return;
		
		//第二步：查询项目方的立项书，得到其阶段，验证其阶段的费用缴纳情况，开始进行同步
		ProjectDefine companyDefine=this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.company,ProjectDefine_Status.normal);
		if(companyDefine==null)return;
		List<ProjectDefineStage> companyStageList=projectDefineStageService.findProjectDefineStageByDefinedId(companyDefine.getId());
		if(companyStageList.isEmpty())return;
		
		Set<String> stageCodeSet=new HashSet<>();
		for(ProjectDefineStage stage:companyStageList){
			if(Pay_Status.success.equals(stage.getPayStatus())){
				stageCodeSet.add(stage.getStageCode());
			}
		}
		if(StringUtils.isNotEmpty(currentStageCode)){
			stageCodeSet.add(currentStageCode);
		}
		log.info("项目阶段已经开发了【"+stageCodeSet.toString()+"】");
		if(stageCodeSet.size()==0)return;
		//第三部：同步阶段的状态和当前第几阶段的字段
		for(ProjectDefineStage stage:ctoStageList){
			if(stageCodeSet.contains(stage.getStageCode())
					&&!Pay_Status.success.equals(stage.getPayStatus())){
				log.info("开始同步更新CTO的阶段Code："+stage.getStageCode());
				this.projectDefineStageService.updateProjectDefineStageAsCTO(stage.getId(),true,true,Pay_Status.success);
			}
		}
		
	}

}
