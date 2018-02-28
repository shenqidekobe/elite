package com.ledao.elite.core.repository.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectTenderRepository;

@Repository
public class ProjectTenderRepositoryImpl extends GenericRepositoryImpl<ProjectTender, Long>
		implements ProjectTenderRepository {

	@SuppressWarnings("unchecked")
	@Override
	public ProjectTender queryProjectTenderById(Long id) {
		String hql = "from ProjectTender where id=?";
		Query query = getSession().createQuery(hql);
		query.setLong(0, id);
		List<ProjectTender> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		ProjectTender obj = list.get(0);
		return obj;
	}
}
