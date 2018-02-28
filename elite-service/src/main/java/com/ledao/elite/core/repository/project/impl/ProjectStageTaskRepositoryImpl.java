package com.ledao.elite.core.repository.project.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectStageTaskRepository;
import com.ledao.elite.core.utils.DateTimeUtils;

@Repository
public class ProjectStageTaskRepositoryImpl extends GenericRepositoryImpl<ProjectStageTask, Long> implements ProjectStageTaskRepository {


	private static String QUERY_LIST_PREFIX="select t.* ";
	private static String QUERY_COUNT_PREFIX="select count(t.id) ";
	private static String QUERY_LIST_SQL=" from t_project_stage_task t "
			+ "left join t_project p on t.project_id=p.id "
			+ "left join t_project_define_stage s on t.stage_id=s.id ";

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectStageTask> queryProjectStageTaskHallList(Long projectId, String taskType,String demandType,
			BigDecimal minAmount, BigDecimal maxAmount, Long stageId, String status, Pager pager) {
		String sql=QUERY_LIST_PREFIX+QUERY_LIST_SQL+" where ";
		sql=createQuerySql(sql, projectId, taskType,demandType,minAmount, maxAmount, stageId, status);
		Query query=getSession().createSQLQuery(sql).addEntity(ProjectStageTask.class);
		if(StringUtils.isNotEmpty(status)&&!status.equals("starting")){
			query.setString("status", status);
		}
		if(StringUtils.isNotEmpty(taskType)){
			query.setString("taskType", taskType);
		}
		if(StringUtils.isNotEmpty(demandType)){
			query.setString("demandType", demandType);
		}
		if(projectId!=null){
			query.setLong("projectId", projectId);
		}
		if(stageId!=null){
			query.setLong("stageId", stageId);
		}
		if(minAmount!=null){
			query.setBigDecimal("minAmount", minAmount);
		}
		if(maxAmount!=null){
			query.setBigDecimal("maxAmount", maxAmount);
		}
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}

	@Override
	public Integer queryProjectStageTaskHallCount(Long projectId, String taskType,String demandType,BigDecimal minAmount,
			BigDecimal maxAmount, Long stageId, String status) {
		String sql=QUERY_COUNT_PREFIX+QUERY_LIST_SQL+" where ";
		sql=createQuerySql(sql, projectId, taskType,demandType,minAmount, maxAmount, stageId, status);
		Query query=getSession().createSQLQuery(sql);
		if(StringUtils.isNotEmpty(status)&&!status.equals("starting")){
			query.setString("status", status);
		}
		if(StringUtils.isNotEmpty(taskType)){
			query.setString("taskType", taskType);
		}
		if(StringUtils.isNotEmpty(demandType)){
			query.setString("demandType", demandType);
		}
		if(projectId!=null){
			query.setLong("projectId", projectId);
		}
		if(stageId!=null){
			query.setLong("stageId", stageId);
		}
		if(minAmount!=null){
			query.setBigDecimal("minAmount", minAmount);
		}
		if(maxAmount!=null){
			query.setBigDecimal("maxAmount", maxAmount);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	
	private String createQuerySql(String sql,Long projectId, String taskType,String demandType,BigDecimal minAmount,
			BigDecimal maxAmount, Long stageId, String status){
		if(StringUtils.isNotEmpty(status)){
			if(status.equals("starting")){
				sql+=" t.`status` in('wait_accept','accept_success','starting')";
			}else{
				sql+=" t.`status`=:status";
			}
		}else{
			sql+=" t.`status` in('accept_success','wait_accept','wait_recruit','recruit_in','starting','quality','finish')";
		}
		
		if(projectId!=null){
			sql+=" and t.project_id=:projectId";
		}
		if(stageId!=null){
			sql+=" and t.stage_id=:stageId";
		}
		if(minAmount!=null){
			sql+=" and t.amount>=:minAmount";
		}
		if(maxAmount!=null){
			sql+=" and t.amount<=:maxAmount";
		}
		if(StringUtils.isNotEmpty(taskType)){
			sql+=" and t.task_type=:taskType";
		}
		if(StringUtils.isNotEmpty(demandType)){
			sql+=" and t.demand_type=:demandType";
		}
		sql+=" order by t.create_time desc";
		return sql;
	}

	@Override
	public void updateExpireTask() {
		String date = DateTimeUtils.formatDate(new Date(), "yyyy-MM-dd");
		String sql = "update t_project_stage_task set status='closed' where end_time<'"+date+"' and status in('wait_recruit','recruit_in')";
		Query query=getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
}
