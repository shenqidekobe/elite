package com.ledao.elite.core.repository.project.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectWeekly;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectWeeklyRepository;

@Repository
public class ProjectWeeklyRepositoryImpl extends GenericRepositoryImpl<ProjectWeekly, Long> implements ProjectWeeklyRepository {

	@Override
	public int deleteWeeklyById(Long id) {
		String hql="delete from ProjectWeekly where id=:id";
		Query query=getSession().createQuery(hql);
		query.setLong("id", id);
		return query.executeUpdate();
	}
	
}
