package com.ledao.elite.core.repository.project.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectMaterialRepository;

@Repository
public class ProjectMaterialRepositoryImpl extends GenericRepositoryImpl<ProjectMaterial, Long> implements ProjectMaterialRepository {

}
