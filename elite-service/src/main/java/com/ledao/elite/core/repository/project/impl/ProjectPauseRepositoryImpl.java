package com.ledao.elite.core.repository.project.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectPause;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectPauseRepository;

@Repository
public class ProjectPauseRepositoryImpl extends GenericRepositoryImpl<ProjectPause, Long> implements ProjectPauseRepository {

}
