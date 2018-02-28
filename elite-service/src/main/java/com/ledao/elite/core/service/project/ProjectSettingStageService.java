package com.ledao.elite.core.service.project;

import java.util.List;

import com.ledao.elite.core.domain.project.ProjectSettingStage;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 项目阶段设置接口
 */
public interface ProjectSettingStageService {

	/**
	 * 根据项目id查询项目阶段
	 */
	List<ProjectSettingStage> findSettingStagesByProjectId(Long projectId) throws EliteServiceException;

	/**
	 * 根据projectId 物理删除
	 */
	void removeSettingStagesByPorjectId(Long ProjectId) throws EliteServiceException;

	/**
	 * 更新项目阶段
	 * 
	 */
	void updateSettingStageByProjectId(Long projectId, String[] dictKeys, Long userId) throws EliteServiceException;

	/**
	 * 根据projectId，stageCode查询
	 */
	ProjectSettingStage findSettingStageByPorjectIdAndStageCode(Long porjectId, String stageCode)
			throws EliteServiceException;

}
