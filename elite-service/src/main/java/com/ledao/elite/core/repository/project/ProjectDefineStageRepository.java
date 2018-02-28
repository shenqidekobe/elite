package com.ledao.elite.core.repository.project;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;

/**
 * 项目立项阶段记录接口
 * */
public interface ProjectDefineStageRepository extends GenericDAO<ProjectDefineStage, Long>{

	
	/**
	 * 更新阶段的支付状态
	 * */
	void updateProjectDefineStageAsPay(Long id,boolean currented,boolean trusted,Pay_Status payStatus);
	
	/**
	 * 按项目ID删除项目阶段记录
	 * 
	 * @param projectId
	 * */
	void deleteProjectDefineStageByProejctId(Long projectId);
	
	/**
	 * 按项目ID查询最大的排序号
	 * */
	Integer queryProjectStageMaxOrders(Long projectId);
	
	/**
	 * 更新
	 * */
	void mergeProjectDefineStage(ProjectDefineStage obj);
	
	/**
	 * 查询项目阶段
	 * */
	List<ProjectDefineStage> queryProjectDefineStageByType(ProjectDefine_Type type,Stage_Status status);
	
}
