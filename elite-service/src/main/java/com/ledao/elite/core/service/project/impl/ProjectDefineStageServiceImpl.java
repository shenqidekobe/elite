package com.ledao.elite.core.service.project.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;
import com.ledao.elite.core.repository.project.ProjectDefineStageRepository;
import com.ledao.elite.core.repository.project.ProjectRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectDefineStageService;

@Service("projectDefineStageService")
public class ProjectDefineStageServiceImpl extends BaseSerivceImpl implements ProjectDefineStageService {

	@Resource
	private ProjectDefineStageRepository projectDefineStageRepository;
	@Resource
	private ProjectRepository projectRepository;
	@Resource
	private EventPublishService eventPublishService;

	@Override
	public ProjectDefineStage createProjectDefineStage(ProjectDefineStage obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getProjectId(), obj.getStageCode());
		obj.setStatus(ProjectDefineStage.Stage_Status.wait_start);
		this.projectDefineStageRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectDefineStage updateProjectDefineStage(ProjectDefineStage obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getId(), obj.getProjectId(), obj.getStageCode());
		this.projectDefineStageRepository.mergeProjectDefineStage(obj);
		return obj;
	}
	
	@Override
	public void updateProjectDefineStagePayWay(Long id,GlobalDefinedConstant.Pay_Way payWay)throws EliteServiceException{
		this.verifyParams(id,payWay);
		ProjectDefineStage pds=this.projectDefineStageRepository.find(id);
		pds.setPayWay(payWay);
		this.projectDefineStageRepository.save(pds);
	}
	
	@Override
	public void updateProjectDefineStageAccept(Long id,boolean acceptFlag,String acceptReason)throws EliteServiceException{
		this.verifyParams(id);
		ProjectDefineStage stage=this.projectDefineStageRepository.find(id);
		if(stage==null){
			throw new EliteServiceException("未找到该项目阶段", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}
		if(!stage.isTrusted()){
			throw new EliteServiceException("该项目阶段未托管金额，不能验收", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		}
		if(!Stage_Status.wait_accept.equals(stage.getStatus())){
			throw new EliteServiceException("该项目阶段已验收过", ErrorCodeEnum.OBJECT_EXIST.code);
		}
		//启动线程开始清算费用
		eventPublishService.publishAcceptSettleEvent(id, acceptFlag, acceptReason);
		/*AcceptSettlementThread acceptSettlementThread=new AcceptSettlementThread();
		acceptSettlementThread.setStageId(id);
		acceptSettlementThread.setAcceptFlag(acceptFlag);
		acceptSettlementThread.setAcceptReason(acceptReason);
		new Thread(acceptSettlementThread).start();*/
	}
	
	@Override
	public void updateProjectDefineStageAsCTO(Long id,boolean currented,boolean trusted,Pay_Status payStatus) throws EliteServiceException{
		this.projectDefineStageRepository.updateProjectDefineStageAsPay(id, currented, trusted, payStatus);
	}

	@Override
	public ProjectDefineStage findProjectDefineStageById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectDefineStageRepository.find(id);
	}
	
	@Override
	public ProjectDefineStage findProjectDefineStageByCode(Long projectId,Long defineId,String stageCode)throws EliteServiceException{
		this.verifyParams(projectId,defineId,stageCode);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("defineId", defineId);
		search.addFilterEqual("stageCode", stageCode);
		return this.projectDefineStageRepository.searchUnique(search);
	}

	@Override
	public List<ProjectDefineStage> findProjectDefineStagesByProjectIdAndCreateTime(Long projectId, Date createTime)
			throws EliteServiceException {
		this.verifyParams(projectId);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("createTime", createTime);
		return this.projectDefineStageRepository.search(search);
	}

	@Override
	public List<ProjectDefineStage> findProjectDefineStageByType(ProjectDefine_Type type,Stage_Status status)throws EliteServiceException{
		this.verifyParams(type,status);
		return this.projectDefineStageRepository.queryProjectDefineStageByType(type,status);
	}
	
	@Override
	public List<ProjectDefineStage> findProjectDefineStageByDefinedId(Long definedId)throws EliteServiceException{
		this.verifyParams(definedId);
		Search search=new Search();
		search.addFilterEqual("defineId", definedId);
		search.addSort("orders", false);//正序
		return this.projectDefineStageRepository.search(search);
	}

	@Override
	public List<ProjectDefineStage> findProjectDefineStageByProjectAndStageCode(long projectId, String stageCode)
			throws EliteServiceException {
		this.verifyParams(projectId,stageCode);
		Search search=new Search();
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("stageCode", stageCode);
		return this.projectDefineStageRepository.search(search);
	}

	@Override
	public ProjectDefineStage findProjectDefineWaitPayStageById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		ProjectDefineStage stage=this.findProjectDefineStageById(id);
		Project project=this.projectRepository.find(stage.getProjectId());
		//判断是否是第一阶段
		if(id.equals(project.getFirstStage().getId())){
			return project.getFirstStage();
		}
		else{
			return stage;
		}
	}

}
