package com.ledao.elite.core.service.project.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.project.ProjectSettingStage;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.project.ProjectSettingStageRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectSettingStageService;
import com.ledao.elite.core.service.sys.DictService;

/**
 *
 *
 * @author zhiyu cao
 **/
@Service
public class ProjectSettingStageServiceImpl extends BaseSerivceImpl implements ProjectSettingStageService {

	@Resource
	private ProjectSettingStageRepository projectSettingStageRepository;
	@Resource
	private DictService dictService;

	@Override
	public List<ProjectSettingStage> findSettingStagesByProjectId(Long projectId) throws EliteServiceException {
		this.verifyParams(projectId);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		search.addSort("orders", false);
		return this.projectSettingStageRepository.search(search);
	}

	@Override
	public void removeSettingStagesByPorjectId(Long projectId) throws EliteServiceException {
		this.verifyParams(projectId);
		List<ProjectSettingStage> stages = this.findSettingStagesByProjectId(projectId);
		if (stages.size() > 0) {
			Long ids[] = new Long[stages.size()];
			for (int i = 0; i < stages.size(); i++) {
				ids[i] = stages.get(i).getId();
			}
			this.projectSettingStageRepository.removeByIds(ids);
		}
	}

	@Override
	public void updateSettingStageByProjectId(Long projectId, String[] dictKeys, Long userId)
			throws EliteServiceException {
		this.verifyParams(projectId);

		for (int i = 0; i < dictKeys.length; i++) {
			Dict dict = this.dictService.findDictByKeyAndType(Dict_Type.project_stage.name(), dictKeys[i]);
			ProjectSettingStage stage = this.findSettingStageByPorjectIdAndStageCode(projectId, dictKeys[i]);
			if (stage == null) {
				stage = new ProjectSettingStage();
				stage.setCreateId(userId);
			}
			if(dict!=null){
			stage.setOrders(dict.getOrders());
			stage.setStageCode(dict.getDictKey());
			stage.setTitle(dict.getDictVal());}
			stage.setProjectId(projectId);
			this.projectSettingStageRepository.save(stage);
		}
		List<ProjectSettingStage> stages = this.findSettingStagesByProjectId(projectId);

		for (int i = 0; i < stages.size(); i++) {
			Boolean include = false;
			for (int j = 0; j < dictKeys.length; j++) {
				if (stages.get(i).getStageCode().equals(dictKeys[j])) {
					include = true;
					break;
				}
			}
			if (!include) {
				this.projectSettingStageRepository.removeById(stages.get(i).getId());
			}

		}

	}

	@Override
	public ProjectSettingStage findSettingStageByPorjectIdAndStageCode(Long projectId, String stageCode)
			throws EliteServiceException {

		this.verifyParams(projectId, stageCode);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("stageCode", stageCode);
		search.addSort("orders", false);
		return this.projectSettingStageRepository.searchUnique(search);
	}

}
