package com.ledao.elite.core.repository.project.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectLog;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectLogRepository;

@Repository
public class ProjectLogRepositoryImpl extends GenericRepositoryImpl<ProjectLog, Long> implements ProjectLogRepository {

}
