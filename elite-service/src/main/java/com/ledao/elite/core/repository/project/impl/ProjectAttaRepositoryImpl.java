package com.ledao.elite.core.repository.project.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectAtta;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectAttaRepository;

@Repository
public class ProjectAttaRepositoryImpl extends GenericRepositoryImpl<ProjectAtta, Long> implements ProjectAttaRepository {

	@Override
	public void deleteProjectAtta(Long projectId){
		String hql="delete from ProjectAtta where projectId=?";
		Query query=getSession().createQuery(hql);
		query.setLong(0, projectId);
		query.executeUpdate();
	}
}
