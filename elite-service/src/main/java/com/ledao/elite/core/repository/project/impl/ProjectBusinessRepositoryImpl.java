package com.ledao.elite.core.repository.project.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectBusiness;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectBusinessRepository;

@Repository
public class ProjectBusinessRepositoryImpl extends GenericRepositoryImpl<ProjectBusiness, Long> implements ProjectBusinessRepository {

}
