package com.ledao.elite.core.repository.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectDefineStageRepository;

@Repository
public class ProjectDefineStageRepositoryImpl extends GenericRepositoryImpl<ProjectDefineStage, Long> implements ProjectDefineStageRepository {


	@Override
	public void updateProjectDefineStageAsPay(Long id,boolean currented,boolean trusted,Pay_Status payStatus){
		String sql="update t_project_define_stage set currented=?,trusted=?,pay_status=? where id=?";
		Query query=getSession().createSQLQuery(sql);
		query.setString(0, currented?"Y":"N");
		query.setString(1, trusted?"Y":"N");
		query.setString(2, payStatus.name());
		query.setLong(3, id);
		query.executeUpdate();
	}
	
	@Override
	public void deleteProjectDefineStageByProejctId(Long projectId) {
		String hql="delete from ProjectDefineStage where projectId=?";
		Query query=getSession().createQuery(hql);
		query.setLong(0,projectId);
		query.executeUpdate();
	}

	@Override
	public Integer queryProjectStageMaxOrders(Long projectId) {
		String hql="select max(orders) from ProjectDefineStage where projectId=?";
		Query query=getSession().createQuery(hql);
		query.setLong(0,projectId);
		return (Integer) query.uniqueResult();
	}
	
	@Override
	public void mergeProjectDefineStage(ProjectDefineStage obj){
		this.merge(obj);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDefineStage> queryProjectDefineStageByType(ProjectDefine_Type type,Stage_Status status){
		String sql="select * from t_project_define_stage t left join t_project_define d "
				+ "on t.define_id=d.id where d.define_type=:type and t.status=:status";
		Query query=getSession().createSQLQuery(sql).addEntity(ProjectDefineStage.class);
		query.setString("type", type.name());
		query.setString("status", status.name());
		return query.list();
	}
	
}
