package com.ledao.elite.core.repository.project.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectTaskRecruitRepository;

@Repository
public class ProjectTaskRecruitRepositoryImpl extends GenericRepositoryImpl<ProjectTaskRecruit, Long> implements ProjectTaskRecruitRepository {
	private static String QUERY_LIST_PREFIX="select r.* ";
	private static String QUERY_COUNT_PREFIX="select count(r.id) ";
	private static String QUERY_LIST_SQL=" from t_project_task_recruit r "
			+ "left join t_project_stage_task t on r.task_id=t.id "
			+ "left join t_project p on r.project_id=p.id ";
		

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectTaskRecruit> queryProjectStageTaskHallList(Long memberId, Long taskId, String status, Pager pager) {
		String sql=QUERY_LIST_PREFIX+QUERY_LIST_SQL+" where ";
		sql=createQuerySql(sql, memberId,taskId, status);
		Query query=getSession().createSQLQuery(sql).addEntity(ProjectTaskRecruit.class);
		if(StringUtils.isNotEmpty(status)){
			query.setString("status", status);
		}
		
		if(memberId!=null){
			query.setLong("memberId", memberId);
		}
		if(taskId!=null){
			query.setLong("taskId", taskId);
		}
		
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryProjectStageTaskHallCount(Long memberId, Long taskId, String status) {
		String sql=QUERY_COUNT_PREFIX+QUERY_LIST_SQL+" where ";
		sql=createQuerySql(sql, memberId,taskId, status);
		Query query=getSession().createSQLQuery(sql);
		if(StringUtils.isNotEmpty(status)){
			query.setString("status", status);
		}
		if(memberId!=null){
			query.setLong("memberId", memberId);
		}
		if(taskId!=null){
			query.setLong("taskId", taskId);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	

	@Override
	public ProjectTaskRecruit findProjectTaskRecruitByTaskIdAndMemberId(Long memberId, Long taskId,String status) {
		String sql=QUERY_LIST_PREFIX+QUERY_LIST_SQL+" where ";
		sql=createQuerySql(sql, memberId,taskId, status);
		
		Query query=getSession().createSQLQuery(sql).addEntity(ProjectTaskRecruit.class);
		if(StringUtils.isNotEmpty(status)){
			query.setString("status", status);
		}
		if(memberId!=null){
			query.setLong("memberId", memberId);
		}
		if(taskId!=null){
			query.setLong("taskId", taskId);
		}
		return (ProjectTaskRecruit) query.uniqueResult();
	}
	
	private String createQuerySql(String sql, Long memberId,Long taskId, String status){
		if(StringUtils.isNotEmpty(status)){
			sql+=" r.`status`=:status";
		}else{
			sql+=" r.`status` in('recruit_in','starting','finish')";
		}
		
		if(memberId!=null){
			sql+=" and r.member_id=:memberId";
		}
		if(taskId!=null){
			sql+=" and r.task_id=:taskId";
		}
		sql += " order by r.create_time desc ";
		return sql;
	}

	
	@Override
	public Integer findProjectTaskCount(Long memberId,String status) {
		String sql = "select count(t.id) from t_project_task_recruit t left join t_project_stage_task ps on t.task_id = ps.id where "
				+ " t.member_id = :memberId and ps.status =:status";
		Query query=getSession().createSQLQuery(sql);
		query.setLong("memberId", memberId);
		query.setString("status", status);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public Integer memberProjectTaskCount(Long memberId) {
		String sql = "select count(t.id) from t_project_task_recruit t left join t_project_stage_task ps on t.task_id = ps.id where "
				+ " t.member_id = :memberId and t.status='recruit_win' and ps.status!='finish'";
		Query query=getSession().createSQLQuery(sql);
		query.setLong("memberId", memberId);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public void updateProjectStageTaskByTaskId(Long taskId, String status) {
		String sql = "update t_project_task_recruit t set t.status=:status where t.task_id=:taskId";
		Query query=getSession().createSQLQuery(sql);
		query.setLong("taskId", taskId);
		query.setString("status", status);
		query.executeUpdate();
	}

	@Override
	public void updateExpireRecruitTask() {
		String sql = "update t_project_task_recruit set status='task_closed' where task_id in(select t.id from t_project_stage_task t where t.status='closed')";
		Query query=getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
}
