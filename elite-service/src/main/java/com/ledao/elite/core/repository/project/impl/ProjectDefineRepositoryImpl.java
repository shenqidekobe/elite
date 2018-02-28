package com.ledao.elite.core.repository.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectDefineRepository;

@Repository
public class ProjectDefineRepositoryImpl extends GenericRepositoryImpl<ProjectDefine, Long> implements ProjectDefineRepository {
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDefine> findProjectDefineListByCtoId(Long ctoId) {
		String sql="select d.* from t_project_define d, t_project p"
                +" where p.id= d.project_id and d.define_type ='cto' and p.cto_id="+ctoId;
		sql += " order by d.affirm_Time desc";
		Query query = getSession().createSQLQuery(sql).addEntity(ProjectDefine.class);
		return query.list();
	}

}
